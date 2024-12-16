/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.vifo.vifobo.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifobo.business.be.impl.dao.ConfigurationDAO;
import it.csi.vifo.vifobo.business.be.impl.dao.CosmoDAO;
import it.csi.vifo.vifobo.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.vifo.vifobo.business.be.service.SchedulerCosmoService;
import it.csi.vifo.vifobo.business.be.service.helper.ApiManagerServiceHelper;
import it.csi.vifo.vifobo.business.be.service.helper.IndexServiceHelper;
import it.csi.vifo.vifobo.dto.AllegatoDTO;
import it.csi.vifo.vifobo.dto.ApiManagerDto;
import it.csi.vifo.vifobo.dto.BoProcLog;
import it.csi.vifo.vifobo.dto.DocumentiDTO;
import it.csi.vifo.vifobo.dto.DocumentoDTO;
import it.csi.vifo.vifobo.dto.MetadatiCosmo;
import it.csi.vifo.vifobo.dto.PraticaDTO;
import it.csi.vifo.vifobo.dto.ResponseDTO;
import it.csi.vifo.vifobo.util.CallRestJsonUtils;
import it.csi.vifo.vifobo.util.Constants;
import it.csi.vifo.vifobo.util.IndexFileTypeEnum;
import it.csi.vifo.vifobo.util.ParamsActaEnum;
import it.csi.vifo.vifobo.util.TipoAllegatoCosmoEnum;
import it.csi.vifo.vifobo.util.VifoUtils;

@Component
@Configuration
@EnableScheduling
public class SchedulerCosmoServiceImpl extends BaseApiServiceImpl implements SchedulerCosmoService {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	private Map<String,String> actaParamsMap = new HashMap<String,String>();
	
	private String jsonInputString = null;
	
	@Autowired
	ConfigurationDAO configurationDAO;
	
	@Autowired
	CosmoDAO cosmoDAO;
	
	@Autowired
	ApiManagerServiceHelper apiManagerServiceHelper;
	
	@Autowired
	DocumentoAllegatoDAO documentoAllegatoDAO;
	
	@Autowired
	ApplicationContext applicationContext;

	@Override
	@Scheduled(fixedDelay = 360000)//6 minuti
	public void startSchedule() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
		
		boolean isScheduleActive = cosmoDAO.isScheduleActive();

        try {
        	 boolean isSemaforoGreen = cosmoDAO.startSemaforo(Constants.SEMAFORO);
			
        	 if (isScheduleActive && isSemaforoGreen) {	
			
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,getClassFunctionDebugString(CLASSNAME, methodName, "scheduler abilitato")));

				String codiceIpaEnte = configurationDAO.getParamActaByCode(ParamsActaEnum.CODICEIPAENTE_COSMO);
				String codiceTipologia = configurationDAO.getParamActaByCode(ParamsActaEnum.CODICETIPOLOGIA_COSMO);
				String oggetto = configurationDAO.getParamActaByCode(ParamsActaEnum.OGGETTO_COSMO); 
				
				List<BoProcLog> listPratiche = cosmoDAO.getProcToWorkByStep(Constants.STEP.STEP_1);
				if (listPratiche != null && listPratiche.size() > 0) {
					for (BoProcLog pratica : listPratiche) {
						inviaPraticaStep1(pratica, codiceIpaEnte, codiceTipologia, oggetto);
					}
				}
					
				listPratiche = cosmoDAO.getProcToWorkByStep(Constants.STEP.STEP_2);
				if(listPratiche != null && listPratiche.size() > 0){
					for (BoProcLog pratica : listPratiche) {
						inviaPraticaStep2(pratica);
					}
				}
				
				listPratiche = cosmoDAO.getProcToWorkByStep(Constants.STEP.STEP_3);
				if(listPratiche != null && listPratiche.size() > 0){
					for (BoProcLog pratica : listPratiche) {
						inviaPraticaStep3(pratica, codiceIpaEnte);
					}
				}
				
				listPratiche = cosmoDAO.getProcToWorkByStep(Constants.STEP.STEP_4);
				if(listPratiche != null && listPratiche.size() > 0){
					for (BoProcLog pratica : listPratiche) {
						inviaPraticaStep4(pratica, codiceIpaEnte);
					}
				}
				      
				      			
			
			}else {
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,
						"schedule NON abilitato : "+ (isScheduleActive?"isSemaforoGreen":"isScheduleActive") + " false"));
			}
        }finally {
			cosmoDAO.stopSemaforo(Constants.SEMAFORO);
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	public void inviaPraticaStep1(BoProcLog istanza, String codiceIpaEnte, String codiceTipologia,
		      String strOggetto){
       
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));

		     
		      ObjectMapper om = new ObjectMapper();
		      
		      PraticaDTO pratica = new PraticaDTO();
		      pratica.setIdPratica(istanza.getFkPratica());
		      String oggetto = strOggetto+"_" + VifoUtils.DATE.formatDateUnderscore(new Date())+"_"+istanza.getFkPratica();
		      pratica.setOggetto(oggetto);
		      pratica.setCodiceIpaEnte(codiceIpaEnte);
		      pratica.setCodiceTipologia(codiceTipologia);
		      
		      
		      String[] parts = istanza.getFkPratica().split("-");
		      int idDomanda = Integer.parseInt(parts[0]);
		      
		      MetadatiCosmo metadati = cosmoDAO.getMetadatiByIdDomanda(idDomanda);
		      metadati.setId(istanza.getFkPratica());
		      metadati.setData_invio(VifoUtils.DATE.formatDate(new Date()));	     
		      
		      pratica.setMetadati(om.writeValueAsString(metadati));
		  
		      jsonInputString = om.writeValueAsString(pratica);   
		      LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName," pre - json step 1"));
		      LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"json step 1: "+jsonInputString));
		      
		      Thread.sleep(2000);
		      ApiManagerDto apiManager = apiManagerServiceHelper.getApiCosmoManagerConfig().getApiManagerDto();
		      CallRestJsonUtils call = new CallRestJsonUtils(getActaParam(ParamsActaEnum.URL_TOKEN));
		      ResponseDTO respDTO = call.sendPost(getActaParam(ParamsActaEnum.URL_PRATICA), apiManager, jsonInputString);
		      if(respDTO == null || respDTO.getErrore() != null || respDTO.getIdPratica() == null){
		    	  int countErrore = cosmoDAO.getCountErrore(istanza.getFkPratica()) + 1;
		       
		    	  if(respDTO != null && respDTO.getErrore() != null){
		    		  cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErrore, 
		    				  Integer.parseInt(respDTO.getErrore().getStatus()), respDTO.getErrore().getTitle(), jsonInputString);
		    	  } else{
		    		  cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErrore, 
		    				  Constants.COD_ERRORE_INTERNO , "step1 - Oggetto riposta null!!!!", jsonInputString);
		        }
		        
		      }else{
		    	  cosmoDAO.storicizzaBoprocLog(istanza.getFkPratica(), jsonInputString);
		    	  cosmoDAO.insertBoprocLog(istanza.getFkPratica(), Constants.STEP.STEP_2);
		      }
		    }
		    catch(Exception ex)
		    {
		      String erroreStr = "Problemi con la gestione della pratica step 1:"+istanza.getFkPratica()+" - "+ex.getMessage();
		      if(erroreStr.length() > 1999)
		        erroreStr = erroreStr.substring(0, 1999);
		      int countErrore = cosmoDAO.getCountErrore(istanza.getFkPratica());
		      cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErrore + 1, 
		          Constants.COD_ERRORE_INTERNO, erroreStr, jsonInputString);
		      LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,erroreStr));
		    }   
		  	finally {
				LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
			}
		  }
	
	public void inviaPraticaStep2(BoProcLog istanza){

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
		    
	    try{

	    	CallRestJsonUtils call = new CallRestJsonUtils(getActaParam(ParamsActaEnum.URL_TOKEN));	      
	      
	    	String[] parts = istanza.getFkPratica().split("-");
	    	int idDomanda = Integer.parseInt(parts[0]);
	    	
	    	List<AllegatoDTO> listAllegati = cosmoDAO.getElencoAllegati(idDomanda, Constants.PARAMETER.DA_INVIARE_COSMO,
	    			parts[1].startsWith("D"));
	    	
	    	boolean erroreAllegato = false;
	    	if(listAllegati != null && listAllegati.size() > 0){
	    		cosmoDAO.updateDocumentoWithInvoCosmoTo2(idDomanda);
	          for(AllegatoDTO allegato : listAllegati){
	            if(!erroreAllegato) {	    		
	            	IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
	            
	            	byte[] fileIndex = indexSrvHelper.indexDownloadFile("" + allegato.getId(), IndexFileTypeEnum.ALLEGATO);  
	                     
	            	Thread.sleep(2000);
	            	ApiManagerDto apiManager = apiManagerServiceHelper.getApiCosmoManagerConfig().getApiManagerDto();
	             ResponseDTO respDTO = call.sendFilePost(getActaParam(ParamsActaEnum.URL_UPLOAD), apiManager, fileIndex, 
	            		 allegato.getTitolo());
	             if(respDTO == null || (respDTO.getErrore() == null && respDTO.getUploadUUID() == null)){
	            	int countErrore = cosmoDAO.getCountErrore(istanza.getFkPratica());
	 		        int countErr = countErrore + 1;
	 		        cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErr, 
	 		        		Constants.COD_ERRORE_INTERNO , "step1 - Oggetto riposta null!!!!", null); 
	 		       erroreAllegato = true;
	               break;
	             }else{
	               if(respDTO.getErrore() != null){
	            	   int countErrore = cosmoDAO.getCountErrore(istanza.getFkPratica());
	   		        	int countErr = countErrore + 1;
	   		        	
	   		        	cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErr, 
	   		                   Integer.parseInt(respDTO.getErrore().getStatus()), 
	   		                   "id_documento_allegato - " + allegato.getId()+": "+respDTO.getErrore().getTitle(), null);
	                 erroreAllegato = true;
	                 break;
	               }
	               else
	               {
	                 if(respDTO.getUploadUUID() != null  && !respDTO.getUploadUUID().equals(""))
	                 {
	                	 cosmoDAO.updateDocumentoWithUuidCosmo(allegato.getId(), respDTO.getUploadUUID());
	                   //resetto nel caso uno fosse andato male in qualche giro precedente
	                	 cosmoDAO.updateBoprocLog(istanza.getFkPratica(), 0, null,null, null);
	                   
	                 }else{
	                	 int countErrore = cosmoDAO.getCountErrore(istanza.getFkPratica()) + 1;
	     		        
		                   cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErrore, 
		                       Constants.COD_ERRORE_INTERNO , "UploadUUID  null", null);
		                   erroreAllegato = true;
	                 }
	               }
	             }
	           }
	         }
	         
	         if(!erroreAllegato)
	         {
	           cosmoDAO.storicizzaBoprocLog(istanza.getFkPratica(), null);
	           cosmoDAO.insertBoprocLog(istanza.getFkPratica(), Constants.STEP.STEP_3);
	         }
	       }
	       else
	       {
	         cosmoDAO.updateBoprocLog(istanza.getFkPratica(), 1, 
	             Constants.COD_ERRORE_INTERNO, "Non ci sono allegati", null);
	       }
	    }catch(Exception ex){
	      String erroreStr = "Problemi con la gestione della pratica step 2:"+istanza.getFkPratica()+" - "+ex.getMessage();
	      if(erroreStr.length() > 1999)
	        erroreStr = erroreStr.substring(0, 1999);
	      int countErrore = cosmoDAO.getCountErrore(istanza.getFkPratica()) + 1;
	      cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErrore, 
	          Constants.COD_ERRORE_INTERNO, erroreStr, null);
	      LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,erroreStr));
	    }     
	    finally
	    {
	    	LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName)); 
	    }
	  }
		  
	public void inviaPraticaStep3(BoProcLog istanza, String codiceIpaEnte){
			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			  LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			  String jsonInputString = null;

		    try{
		    	CallRestJsonUtils call = new CallRestJsonUtils(getActaParam(ParamsActaEnum.URL_TOKEN));
			      
		    	ObjectMapper om = new ObjectMapper();

		    	DocumentoDTO documento = new DocumentoDTO();
		    	documento.setIdPratica(istanza.getFkPratica());
		    	documento.setCodiceIpaEnte(codiceIpaEnte);
		    	
		    	 String[] parts = istanza.getFkPratica().split("-");
			     int idDomanda = Integer.parseInt(parts[0]);
		      
		      List<DocumentiDTO> lDocumenti = new ArrayList<DocumentiDTO>();
		      List<AllegatoDTO> listAllegati = cosmoDAO.getElencoAllegati(idDomanda, Constants.PARAMETER.INVIATO_COSMO_FILE,
		    		  parts[1].startsWith("D"));
		      if(listAllegati != null && listAllegati.size() > 0){
		    	  for(AllegatoDTO allegato:listAllegati){
			          DocumentiDTO doc = new DocumentiDTO();
			          doc.setId("" + allegato.getId());
			          if(allegato.getIdPadre() != null)
			            doc.setIdPadre("" + allegato.getIdPadre());
			          doc.setCodiceTipo(allegato.getCodiceCosmo());
			          doc.setTitolo(allegato.getTitolo());
			          doc.setDescrizione(allegato.getDescrizione());
			          doc.setIdPratica(istanza.getFkPratica());
			          MetadatiCosmo metadati = cosmoDAO.getMetadatiByIdDomanda(idDomanda);
			          doc.setAutore(metadati.getIntestatario());
			          doc.setUploadUUID(allegato.getUploaduuidCosmo());
			          lDocumenti.add(doc);
		    	  }
		        
		      
		          documento.setDocumenti(lDocumenti);
		          jsonInputString = om.writeValueAsString(documento);    
		          LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"json step 3: "+jsonInputString));
		          
		          Thread.sleep(2000);
		          ApiManagerDto apiManager = apiManagerServiceHelper.getApiCosmoManagerConfig().getApiManagerDto();
		          ResponseDTO respDTO = call.sendPostDocumento(getActaParam(ParamsActaEnum.URL_DOCUMENTI), apiManager, jsonInputString);
		        
			          
		          if(respDTO == null || (respDTO != null && respDTO.getErrore() != null)){
		        	  int countErrore = cosmoDAO.getCountErrore(istanza.getFkPratica()) + 1;
			          if(respDTO != null){
			        	  cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErrore, 
			              Integer.parseInt(respDTO.getErrore().getStatus()), respDTO.getErrore().getTitle(), jsonInputString);
			          }else{
			            cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErrore, 
			               Constants.COD_ERRORE_INTERNO , "Oggetto riposta null!!!!", jsonInputString);
			          }
		          
		          }else{
			          cosmoDAO.storicizzaBoprocLog(istanza.getFkPratica(), jsonInputString);
			          cosmoDAO.insertBoprocLog(istanza.getFkPratica(), Constants.STEP.STEP_4);
		          }
		          
		      }else{
		        cosmoDAO.updateBoprocLog(istanza.getFkPratica(), 1, 
		            Constants.COD_ERRORE_INTERNO, "Non ci sono allegati", jsonInputString);
		      }
		    }
		    catch(Exception ex)
		    {
		      String erroreStr = "Problemi con la gestione della pratica step 3:"+istanza.getFkPratica()+" - "+ex.getMessage();
		      if(erroreStr.length() > 1999)
		        erroreStr = erroreStr.substring(0, 1999);
		      int countErrore = cosmoDAO.getCountErrore(istanza.getFkPratica());
		      cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErrore +1 , 
		          Constants.COD_ERRORE_INTERNO, erroreStr, jsonInputString);
		      LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,erroreStr));
		    }       
		    finally
		    {
		    	LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName)); 
		    }
		  }
		  
		  public void inviaPraticaStep4(BoProcLog istanza, String codiceIpaEnte){

			  String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			  LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));

			  String jsonInputString = null;
		    
			  try{

			      CallRestJsonUtils call = new CallRestJsonUtils(getActaParam(ParamsActaEnum.URL_TOKEN));
			      
			      ObjectMapper om = new ObjectMapper(); 
			      
				      PraticaDTO praticaDTO = new PraticaDTO();
			      praticaDTO.setIdPratica(istanza.getFkPratica());
			      praticaDTO.setCodiceIpaEnte(codiceIpaEnte);
			      
			      jsonInputString = om.writeValueAsString(praticaDTO); 
			      LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "json step 4: "+jsonInputString));
			      
			      Thread.sleep(2000);
			      ApiManagerDto apiManager = apiManagerServiceHelper.getApiCosmoManagerConfig().getApiManagerDto();
			      ResponseDTO respDTO = call.sendPost(getActaParam(ParamsActaEnum.URL_PROCESSO), apiManager, jsonInputString);
			      
			      if(respDTO == null || respDTO.getErrore() != null || respDTO.getIdPratica() == null){
			        int countErrore = cosmoDAO.getCountErrore(istanza.getFkPratica()) + 1;
			        if(respDTO != null)
			        {
			          cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErrore, 
			            Integer.parseInt(respDTO.getErrore().getStatus()), respDTO.getErrore().getTitle(), jsonInputString);
			        }
			        else
			        {
			          cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErrore, 
			             Constants.COD_ERRORE_INTERNO , "Oggetto riposta null!!!!", jsonInputString);
			        }
			        
			      }
			      else
			      {
			        cosmoDAO.storicizzaBoprocLog(istanza.getFkPratica(), jsonInputString);
			        cosmoDAO.insertBoprocLog(istanza.getFkPratica(), Constants.STEP.STEP_5);
			      }
		    }
		    catch(Exception ex)
		    {
		      String erroreStr = "Problemi con la gestione della pratica step 4:"+istanza.getFkPratica()+" - "+ex.getMessage();
		      if(erroreStr.length() > 1999)
		        erroreStr = erroreStr.substring(0, 1999);
		      int countErrore = cosmoDAO.getCountErrore(istanza.getFkPratica());
		      int countErr = countErrore + 1;
		      cosmoDAO.updateBoprocLog(istanza.getFkPratica(), countErrore, 
		          Constants.COD_ERRORE_INTERNO, erroreStr, jsonInputString);
		      LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,erroreStr));
		    }        
		    finally{
		    	LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName)); 
		    }
		  }
		  
		  
		 
//		  private boolean updateProtocollo(String idIstanza, String numeroProtocollo, String dataProtocollo, String json) 
//		  {
//		    boolean trovato = false;
//		    int result = cosmoDAO.updateProtocollo(Long.valueOf(idIstanza), numeroProtocollo, dataProtocollo);
//		    if(result > 0){
//		      cosmoDAO.storicizzaBoprocLogNoData(Long.valueOf(idIstanza).toString(), json);
//		      trovato = true;
//		    }
//		    return trovato;
//		  }
		  
//		  public void updateBoprocLog(String idIstanza, int countTentativo, Integer codErrore, String noteErrore,
//		      String json) {
//		    cosmoDAO.updateBoprocLog(idIstanza, countTentativo, codErrore, noteErrore, json);
//		  }
//		  
//		  public void storicizzaBoprocLog(String idIstanza, String json) {
//		    cosmoDAO.storicizzaBoprocLog(idIstanza, json);
//		  }
//		  
//		  public void inviaMailProtocollo(long idIstanza) throws ServiceException {
//			  logger.info("INVIO 1");
//			  
//			  IstanzaInfoMailByIstanzaDto istanza = cosmoDAO.findInfoMailByIstanza(idIstanza);
//			  logger.info("INVIO 2");
//			  MailConfigDto mailConfig = configDAO.findMailConfigByPrimaryKey(new MailConfigPk(Constants.MAIL.MAIL_NO_PEC));
//			  logger.info("INVIO 3");
//			  TipoMailDto tipoMail = configDAO.findTipoMailByPrimaryKey(new TipoMailPk(Constants.MAIL.PROTOCOLLO));
//			  logger.info("INVIO 4");
//			  
//			  this.mailServiceHelper.invioMail(DettaglioEmailFactory.createDettaglioEmail(mailConfig, tipoMail, istanza));
//			  logger.info("INVIO 5");
//			  
//			  
//		  }
		  
		  
		  private String getActaParam(ParamsActaEnum actaParam) {
			  String val = actaParamsMap.get(actaParam.toString());
			  if(val == null) {
				  val = configurationDAO.getParamActaByCode(actaParam);
				  actaParamsMap.put(actaParam.toString(),val);
			  }
			  return val;
		  }

		  private TipoAllegatoCosmoEnum getTipoAllegatoCosmoByCode(String code) {
			  if("DG".equals(code)) {
				  return TipoAllegatoCosmoEnum.DOMANDA_GRATUITA ;
			  }else if("AG".equals(code)) {
				  return TipoAllegatoCosmoEnum.ASSEGNAZIONE_GRATUITA ;
			  }else if("DV".equals(code)) {
				  return TipoAllegatoCosmoEnum.DOMANDA_VENDITA ;
			  }else if("AV".equals(code)) {
				  return TipoAllegatoCosmoEnum.ACCETTAZIONE_VENDITA ;
			  }
			  return null;
		  }  
}
