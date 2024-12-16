/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.DomandaApi;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.PagamentoDAO;
import it.csi.vifo.vifoapi.business.be.service.AdpforHomeService;
import it.csi.vifo.vifoapi.business.be.service.DocumentoService;
import it.csi.vifo.vifoapi.business.be.service.DomandaService;
import it.csi.vifo.vifoapi.business.be.service.PdfGeneratorService;
import it.csi.vifo.vifoapi.business.be.service.helper.SiapPagoPAServiceHelper;
import it.csi.vifo.vifoapi.business.be.service.impl.PdfGeneratorServiceImpl;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.Domanda;
import it.csi.vifo.vifoapi.dto.FileUploadForm;
import it.csi.vifo.vifoapi.dto.Pagamento;
import it.csi.vifo.vifoapi.dto.RicercaDomanda;
import it.csi.vifo.vifoapi.util.ErrorResponseEnum;
import it.csi.vifo.vifoapi.util.ProfiloUtenteEnum;
import it.csi.vifo.vifoapi.util.StatoDomandaEnum;
import it.csi.vifo.vifoapi.util.TipoAllegatoEnum;
import it.csi.vifo.vifoapi.util.TipoPagamentoEnum;
import it.csi.vifo.vifoapi.util.TipoPdfEnum;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.EsitoAnnullaPagamento;
import it.csi.vifo.vifoapi.util.templating.TemplateUtil;

@Component
public class DomandaApiServiceImpl extends BaseApiServiceImpl implements DomandaApi {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	DomandaService domandaService;
	
	@Autowired
	DocumentoService documentoService;
		
	@Autowired
	AdpforHomeService adpforHomeService;
	
	@Autowired
	PdfGeneratorService pdfGeneratorService;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Value("${corsfilter.enablecors}")
	private String enablecors;
	
	@Autowired
	SiapPagoPAServiceHelper siapPagoPAServiceHelper;
	
	@Override
	public Response getMyDomande(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(domandaService.getRiepilogoDomandeBySoggetto(1)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getPaginatedMyDomande(int page, int limit, String sort, SecurityContext securityContext, HttpHeaders httpHeaders,
		HttpServletRequest httpRequest) {
	
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			if(confUtente == null) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"user not found"));
				return getErrorResponse(ErrorResponseEnum.DATA_NOT_FOUND);
			}
			return Response.ok(domandaService.getPaginatedRiepilogoDomandeBySoggetto(confUtente.getFkSoggetto(), page, limit, sort)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response loadDomanda(int idDomanda, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			return Response.ok(domandaService.loadDomanda(idDomanda)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response saveDomanda(Domanda body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			return Response.ok(domandaService.insertUpdateDomandaStep1(body, 
					adpforHomeService.getSessionConfigUtente(httpRequest))).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response updateDomanda(Domanda body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			return Response.ok(domandaService.insertUpdateDomandaStep1(body, 
					adpforHomeService.getSessionConfigUtente(httpRequest))).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response updateDomandaToAssegnata(Domanda body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        ConfigUtente configUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
	        TipoPdfEnum tipoPdf = body.getFkTipoDomanda() == 1?
					TipoPdfEnum.ASSEGNAZIONE_GRATUITA : TipoPdfEnum.ASSEGNAZIONE_ONEROSA;
			TipoAllegatoEnum tipoAllegato = body.getFkTipoDomanda() == 1?
					TipoAllegatoEnum.ASSEGNAZIONE_GRATUITA_CARICATA_AUTOMATICAMENTE : TipoAllegatoEnum.ASSEGNAZIONE_ONEROSA_CARICATA_AUTOMATICAMENTE;
			
			byte[] bytes = null;
	      //temp try catch fino a quando la generazione non funziona correttamente in test csi
			try {
				bytes = pdfGeneratorService.generateDichiarazione(tipoPdf, body.getIdDomanda(), httpRequest);
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "generazione esseguita correttamente per: " + tipoPdf.getValue()));
			}catch(Exception ex) {
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "Errore durante generazione documento: " + tipoPdf.getValue()), ex);
				Resource resource = resourceLoader.getResource(PdfGeneratorServiceImpl.TEMPLATES_RESOURCES_CLASSPATH.concat(tipoPdf.getValue())
						.concat(PdfGeneratorServiceImpl.TEMPLATE_EXTENSION));
				bytes = Files.readAllBytes(Paths.get(resource.getFile().getAbsolutePath()));
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, ex));
			}
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "documento size: " 
					+ bytes == null? "null": "" + bytes.length));
			FileUploadForm form = new FileUploadForm();
			form.setData(bytes);
			form.setName(TemplateUtil.getFileName(tipoPdf));
			
			documentoService.uploadDocumentiDomanda(body.getIdDomanda(),tipoAllegato.getValue(), form, configUtente);
			int res = domandaService.updateDomandaToAssegnata(body,configUtente);
	        
			return Response.ok(res).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response updateDomandaPreassegnata(Domanda body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			return Response.ok(domandaService.updateDomandaPreassegnata(body, 
					adpforHomeService.getSessionConfigUtente(httpRequest))).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response annullaAssegnazione(Domanda body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			domandaService.annullaAssegnazione(body.getIdDomanda(), adpforHomeService.getSessionConfigUtente(httpRequest));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response inviaDomanda(Domanda body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        Domanda domanda = domandaService.loadDomanda(body.getIdDomanda());
	        if(domanda.getFkTipoDomanda() == 2 && domanda.getSoggetto().getFlgEntePubblico() == 0) {
		        ConfigUtente configUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
		        TipoPdfEnum tipoPdf = TipoPdfEnum.DOMANDA_ONEROSA;
				TipoAllegatoEnum tipoAllegato = TipoAllegatoEnum.DOMANDA_ONEROSA_CARICATA_AUTOMATICAMENTE;
				
				byte[] bytes = null;
		      //temp try catch fino a quando la generazione non funziona correttamente in test csi
				try {
					bytes = pdfGeneratorService.generateDichiarazione(tipoPdf, domanda.getIdDomanda(), httpRequest);
					LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "generazione esseguita correttamente per: " + tipoPdf.getValue()));
				}catch(Exception ex) {
					LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "Errore durante generazione documento: " + tipoPdf.getValue()), ex);
					Resource resource = resourceLoader.getResource(PdfGeneratorServiceImpl.TEMPLATES_RESOURCES_CLASSPATH.concat(tipoPdf.getValue())
							.concat(PdfGeneratorServiceImpl.TEMPLATE_EXTENSION));
					bytes = Files.readAllBytes(Paths.get(resource.getFile().getAbsolutePath()));
					LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, ex));
				}
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "documento size: " 
						+ bytes == null? "null": "" + bytes.length));
				FileUploadForm form = new FileUploadForm();
				form.setData(bytes);
				form.setName(TemplateUtil.getFileName(tipoPdf));
				documentoService.uploadDocumentiDomanda(body.getIdDomanda(),tipoAllegato.getValue(), form, configUtente);
	        }
			
			domandaService.inviaDomanda(body, adpforHomeService.getSessionConfigUtente(httpRequest), httpRequest);
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response deleteDomanda(int idDomanda, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			documentoService.deleteAllegatoByIdDomanda(idDomanda);
			domandaService.deleteDomanda(idDomanda);
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response confermaAccettazione(int idDomanda, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			domandaService.confermaAccettazione(idDomanda, adpforHomeService.getSessionConfigUtente(httpRequest));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response confermaVariazione(Domanda domanda, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));			
			domandaService.confermaVariazione(domanda, adpforHomeService.getSessionConfigUtente(httpRequest));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getPaginatedSearchDomande(RicercaDomanda body, int page, int limit, String sort,
			SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			
			if(ProfiloUtenteEnum.GESTORE.getValue() == confUtente.getFkTipoProfilo()
					|| ProfiloUtenteEnum.VIVAIO.getValue() == confUtente.getFkTipoProfilo()) {
				if(ProfiloUtenteEnum.VIVAIO.getValue() == confUtente.getFkTipoProfilo()) {
					body.setIdVivaio(confUtente.getFkVivaio());
				}
				return Response.ok(domandaService.getPaginatedSearchDomande(body, page, limit, sort)).build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response getPaginatedSearchDomande2(int page, int limit, String sort,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(domandaService.getPaginatedSearchDomande(new RicercaDomanda(), page, limit, sort)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	

	@Override
	public Response getInfoDomanda(int idDomanda, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			return Response.ok(domandaService.getInfoDomanda(idDomanda)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response updateDomandaToChiusa(Domanda body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			ConfigUtente configUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			domandaService.updateDomandaToChiusa(body, configUtente);
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response updateDomandaToRifiutata(Domanda body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			ConfigUtente configUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			domandaService.updateDomandaToRifiutata(body, configUtente);
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getNumDomandeBySoggetto(int idSoggetto, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));			
			ConfigUtente configUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return Response.ok(domandaService.getNumDomandeBySoggetto(idSoggetto, configUtente.getFkVivaio())).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response annullaPagamentoPiantine(Domanda body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        
	        EsitoAnnullaPagamento res = siapPagoPAServiceHelper.annullaPagamento(body.getIdDomanda());
	        if(res.getIdErrore() !=  null || res.getDescErrore() != null) {
	        	throw new Exception("Error on SiapPagoPAServiceHelper - id: " + res.getIdErrore() 
	        	+ " - descrizione: " + res.getDescErrore());
	        }else {
	        	//salva su DB STATO PAGAMENTO
	        }
	        return Response.ok().build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response inserisciPagamentoPiantine(Pagamento body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));	 
	        ConfigUtente configUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
	        body.setFkConfigUtente(configUtente.getIdConfigUtente());
	        body.setFkTipoPagamento(TipoPagamentoEnum.PIANTINE.getValue());
	       domandaService.insertPagamento(body);	        
	        return Response.ok(domandaService.getLastStatoByType(body.getFkDomanda(), StatoDomandaEnum.ASSEGNATA)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response updatePagamentoPiantine(Pagamento body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));	 
	        body.setFkTipoPagamento(TipoPagamentoEnum.PIANTINE.getValue());
	       domandaService.updatePagamento(body);	        
	        return Response.ok().build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
}
