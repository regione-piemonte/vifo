/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.SoggettoApi;
import it.csi.vifo.vifoapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.SoggettoDAO;
import it.csi.vifo.vifoapi.business.be.service.AdpforHomeService;
import it.csi.vifo.vifoapi.business.be.service.WsService;
import it.csi.vifo.vifoapi.business.be.service.aaep.AziendaAAEP;
import it.csi.vifo.vifoapi.business.be.service.aaep.ListaAttEconProd;
import it.csi.vifo.vifoapi.business.be.service.aaep.ListaSediAAEP;
import it.csi.vifo.vifoapi.business.be.service.helper.AaepServiceHelper;
import it.csi.vifo.vifoapi.business.be.service.impl.WsServiceImpl;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.SoggettoSearch;
import it.csi.vifo.vifoapi.dto.TSoggetto;
import it.csi.vifo.vifoapi.dto.UserInfo;
import it.csi.vifo.vifoapi.rest.BaseResponses;
import it.csi.vifo.vifoapi.util.Constants;
import it.csi.vifo.vifoapi.util.ProfiloUtenteEnum;
import it.csi.vifo.vifoapi.util.ValidationUtil;
import it.csi.vifo.vifoapi.util.service.integration.svista.Comune;

@Component
public class SoggettoApiServiceImpl extends BaseApiServiceImpl implements SoggettoApi {

	private final String CLASSNAME = this.getClass().getSimpleName();

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	private SoggettoDAO soggettoDAO;
	
	@Autowired
	private ConfigUtenteDAO ConfigUtenteDAO;
	
	@Autowired
	WsService wsService;
	
	@Autowired
	AdpforHomeService adpforHomeService;
	
	
	@Override
	public Response getLoggedUser(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        UserInfo userInfo = (UserInfo)httpRequest.getSession().getAttribute(Constants.USERINFO_SESSIONATTR);
			return Response.ok(soggettoDAO.findSoggettoByCodiceFiscale(userInfo.getCodFisc())).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public Response getSoggetto(@PathParam("idSoggetto") Integer idSoggetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		TSoggetto soggetto;
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			soggetto = soggettoDAO.findSoggettoById(idSoggetto);
			return Response.ok(soggetto).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response updateSoggetto(TSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			if (validationObligatory(body)) {
				LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"validationObligatory faild"));
				return BaseResponses.requiredField();
			}

			if (validateRequiredFields(body) != null) {
				LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"validateRequiredFields faild"));
				return validateRequiredFields(body);
			}

			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			body.setFkConfigUtente(confUtente.getIdConfigUtente());
			return Response.ok(soggettoDAO.updateSoggetto(body)).build();

		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response saveSoggetto(TSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			if (validationObligatory(body)) {
				return BaseResponses.requiredField();
			}

			if (validateRequiredFields(body) != null)
				return validateRequiredFields(body);
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			body.setFkConfigUtente(confUtente.getIdConfigUtente());
			return Response.ok(soggettoDAO.createSoggetto(body)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getSoggettoByPartialCodiceFiscale(String codiceFiscale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			if (codiceFiscale == null || codiceFiscale.length() > 16) {
				return BaseResponses.badInputParameters();
			}
			return Response.ok(soggettoDAO.findByPartialCodiceFiscale(codiceFiscale)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	

	private boolean validationObligatory(TSoggetto input) {
		return input.getTelefono() == null || input.getMail() == null || input.getCodiceFiscale() == null
				|| ((input.getNome() == null || input.getCognome() == null) 
					&& (input.getRagioneSociale() == null || input.getPec() == null));
	}

	private Response validateRequiredFields(TSoggetto input) {
		if (input.getTelefono().length() < 7 || input.getTelefono().length() > 20) {
			return BaseResponses.invalidLength();
		}

		if (!ValidationUtil.isEMail(input.getMail())) {
			return BaseResponses.invalidEmail();
		}
		return null;
	}

	@Override
	public Response getPersonaFisicaByCodiceFiscale(String codiceFiscale) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			TSoggetto sogFisico = soggettoDAO.findSoggettoByCodiceFiscale(codiceFiscale);
			if(sogFisico == null) {
				sogFisico = new TSoggetto();
				sogFisico.setCodiceFiscale(codiceFiscale);
			}
			return Response.ok(sogFisico).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response getPersonaGiurdicaByCodiceFiscale(String codiceFiscale, Boolean isEntePubblico){
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			TSoggetto azienda = soggettoDAO.findAziendaByCodiceFiscale(codiceFiscale, isEntePubblico);
			azienda = fillAziendaByAaepService(codiceFiscale, azienda==null?new TSoggetto():azienda);
			azienda.setFlgEntePubblico((byte)(isEntePubblico?1:0));
			return Response.ok(azienda).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}
	}


	@Override
	public Response getPersonaGiurdicaByOwnerCodiceFiscale(String codiceFiscale, boolean isEntePubblico,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,	@Context HttpServletRequest httpRequest) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			List<TSoggetto> res = soggettoDAO.getPersonaGiurdicaByOwnerCodiceFiscale(codiceFiscale,isEntePubblico);
			AaepServiceHelper aaepService = (AaepServiceHelper) applicationContext.getBean("aaepServiceHelper");
			if(res == null) {
				res = new ArrayList<TSoggetto>();
			}
			try {
		 		ListaAttEconProd[] aziendaList = null;
		 		if(!isEntePubblico) {
		 			aziendaList = aaepService.cercaAziendeAAEPByCodiceFiscale(codiceFiscale);
		 		}
		 		res = mapNewAziendeAAEP(res,aziendaList);
			} catch (Exception ex) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, ex));
				return errorResponse(ex);
			}
			return Response.ok(res).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
		
		
	}
	
	private List<TSoggetto> mapNewAziendeAAEP(List<TSoggetto> aziendeList, ListaAttEconProd[] aziendaAAEPList){
		Map<String,TSoggetto> mapAziende = new HashMap<String,TSoggetto>();
		if(!aziendeList.isEmpty()) {
			for(TSoggetto item: aziendeList) {
				mapAziende.put(item.getCodiceFiscale().toUpperCase(),item);
			}
		}
		if(aziendaAAEPList != null && aziendaAAEPList.length > 0) {
			for(ListaAttEconProd item: aziendaAAEPList) {
				//logListaAttEconProd(item);
				if(mapAziende.get(item.getCodiceFiscale().toUpperCase()) == null) {
					aziendeList.add(fillAziendaByAaepService(item.getCodiceFiscale(),new TSoggetto()));
				}else {
					fillAziendaByAaepService(item.getCodiceFiscale(),mapAziende.get(item.getCodiceFiscale().toUpperCase()));
				}
			}
		}
		return aziendeList;
	}
	
	private TSoggetto fillAziendaByAaepService(String codiceFiscale, TSoggetto azienda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		AaepServiceHelper aaepService = (AaepServiceHelper) applicationContext.getBean("aaepServiceHelper");
		try {
 			azienda.setCodiceFiscale(codiceFiscale);
			AziendaAAEP aziendaAAEP = aaepService.getAziendaByCodiceFiscale(codiceFiscale);
	 		if(aziendaAAEP != null) {
	 			if(aziendaAAEP.getPartitaIva() != null) {
		 			azienda.setPartitaIva(aziendaAAEP.getPartitaIva());
	 			}
	 			azienda.setRagioneSociale(aziendaAAEP.getRagioneSociale());
	 			if(aziendaAAEP.getListaSediAAEP() != null && aziendaAAEP.getListaSediAAEP().length > 0) {
	 				for(ListaSediAAEP item : aziendaAAEP.getListaSediAAEP()) {
	 					LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName," getIdAAEPSede: " 
	 				+ item.getIdAAEPSede() + " - indirizzo: " + item.getIndirizzo()));
	 					if("1".equals(item.getIdAAEPSede())) {
	 						azienda.setIndirizzo(item.getIndirizzo());
	 						try {
	 							List<Comune> comuni = wsService.getListComuniByName(item.getDescrComuneUL());
	 							if(comuni != null && comuni.size() > 0) {
		 							Comune comune = null;
		 							for(Comune com:comuni) {
		 								comune = WsServiceImpl.mapComuniPiemontePerIstat.get(com.getCodIstat());
		 								if(comune != null) {
		 									azienda.setComune(com);
		 									break;
		 								}
		 							}
		 							if(comune == null) {
		 								azienda.setComune(comuni.get(0));
		 							}
	 							}
	 							
	 						}catch(Exception ex) {
	 							LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, ex));
	 						}
	 					}
	 				}
	 			}
	 		}
		} catch (Exception ex) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, ex));
		}
		return azienda;
	}


	@Override
	public Response getPaginatedListPersona(int page, int limit, String sort,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			SoggettoSearch sog = new SoggettoSearch();
			sog.setCognome(httpRequest.getParameter("cognome"));
			sog.setRagoneSociale(httpRequest.getParameter("ragioneSociale"));
			sog.setCodFiscalePiva(httpRequest.getParameter("codFiscalePiva"));
			sog.setProvincia(httpRequest.getParameter("provincia"));
			sog.setComune(httpRequest.getParameter("comune"));
			if(ProfiloUtenteEnum.VIVAIO.getValue() == confUtente.getFkTipoProfilo()) {
				sog.setIdVivaio(confUtente.getFkVivaio());
			}
			if(ProfiloUtenteEnum.RICHIEDENTE.getValue() == confUtente.getFkTipoProfilo()) {
				throw new Exception("unauthorized");
			}
			return Response.ok(soggettoDAO.getPaginatedListSoggetti(page, limit, sort, sog)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public Response getConfigById(Integer idConfig, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return Response.ok(ConfigUtenteDAO.getConfigUtenteById(idConfig)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
}
