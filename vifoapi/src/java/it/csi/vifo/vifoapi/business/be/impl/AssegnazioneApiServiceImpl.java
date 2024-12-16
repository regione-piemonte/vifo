/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.AssegnazioneApi;
import it.csi.vifo.vifoapi.business.be.service.AdpforHomeService;
import it.csi.vifo.vifoapi.business.be.service.AssegnazioneService;
import it.csi.vifo.vifoapi.business.be.service.DomandaService;
import it.csi.vifo.vifoapi.dto.Assegnazione;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.Ddt;
import it.csi.vifo.vifoapi.dto.RicercaDdt;
import it.csi.vifo.vifoapi.util.ErrorResponseEnum;
import it.csi.vifo.vifoapi.util.ProfiloUtenteEnum;
import it.csi.vifo.vifoapi.util.StatoDomandaEnum;

@Component
public class AssegnazioneApiServiceImpl extends BaseApiServiceImpl implements AssegnazioneApi {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	AssegnazioneService assegnazioneService;
	
	@Autowired
	DomandaService domandaService;
	
	@Autowired
	AdpforHomeService adpforHomeService;

	@Override
	public Response getListAssegnazioniByDomanda(int idDomanda, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(assegnazioneService.getListAssegnazioniByDomanda(idDomanda)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response getListAssegnazioniNRByDomanda(int idDomanda, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(assegnazioneService.getListAssegnazioniNRByDomanda(idDomanda)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response getRiepilogoAssegnazioniByIdDomanda(int idDomanda, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(assegnazioneService.getRiepilogoAssegnazioniByIdDomanda(idDomanda)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response saveAssegnazione(Assegnazione[] body, int idDomanda, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			if(confUtente == null) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"user not found"));
				return getErrorResponse(ErrorResponseEnum.DATA_NOT_FOUND);
			}
			assegnazioneService.saveAssegnazioni(body, idDomanda, confUtente.getIdConfigUtente());
			domandaService.updateStatoDomanda(idDomanda, StatoDomandaEnum.PREASSEGNATA, null, confUtente);
			return Response.ok().build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getPartiteBySpecieAndVivaio(int idSpecie, int idVivaio, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(assegnazioneService.getPartiteBySpecieAndVivaio(idSpecie, idVivaio)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response updateAssegnazione(Assegnazione[] body, int idDomanda, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			if(confUtente == null) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"user not found"));
				getErrorResponse(ErrorResponseEnum.DATA_NOT_FOUND);
			}
			assegnazioneService.updateRitiroAssegnazioni(body, confUtente.getIdConfigUtente());
			domandaService.updateStatoDomanda(idDomanda, StatoDomandaEnum.RITIRO_IN_CORSO, null, confUtente);
			return Response.ok().build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getAvailableDdtByIdDomanda(int idDomanda, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(assegnazioneService.getAvailableDdtByIdDomanda(idDomanda)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response saveDdt(Ddt[] body, int idDomanda, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        if(body != null && body.length > 0) {
	        	int count = 0;
	        	for(Ddt item:body) {
	        		count+= item.getQtaInDdt();
	        	}
	        	if(count == 0) {
	        		return getErrorResponse(ErrorResponseEnum.GENERIC);
	        	}
	        }else {
	        	LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"empty DDT list"));
				return getErrorResponse(ErrorResponseEnum.DATA_NOT_FOUND);
	        }
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			if(confUtente == null) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"user not found"));
				return getErrorResponse(ErrorResponseEnum.DATA_NOT_FOUND);
			}
			assegnazioneService.saveDdt(body, confUtente.getIdConfigUtente());
			domandaService.updateStatoDomanda(idDomanda, StatoDomandaEnum.RITIRO_IN_CORSO, null, confUtente);
			return Response.ok().build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getListDdtByIdDomanda(int idDomanda, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(assegnazioneService.getListDdtByIdDomanda(idDomanda)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getInfoDdt(int numeroDdt, int anno, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(assegnazioneService.getInfoDdt(numeroDdt, anno)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getDdtDettailsByNumeroDdtAndAnnoDdt(int numeroDdt, int anno, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(assegnazioneService.getDdtByNumeroDdtAndAnno(numeroDdt, anno)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response annullaDdt(int numeroDdt, int anno, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			assegnazioneService.annullaDdt(numeroDdt, anno);
			return Response.ok().build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response updateDdt(Ddt[] body, int numeroDdt, int anno, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        if(body != null && body.length > 0) {
	        	int count = 0;
	        	for(Ddt item:body) {
	        		count+= item.getQtaInDdt();
	        	}
	        	if(count == 0) {
	        		return getErrorResponse(ErrorResponseEnum.GENERIC);
	        	}
	        }else {
	        	LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"empty DDT list"));
				return getErrorResponse(ErrorResponseEnum.DATA_NOT_FOUND);
	        }
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			if(confUtente == null) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"user not found"));
				getErrorResponse(ErrorResponseEnum.DATA_NOT_FOUND);
			}
			assegnazioneService.updateDdt(body, numeroDdt, anno, confUtente.getIdConfigUtente());
			return Response.ok().build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getPaginatedSearchDdt(RicercaDdt body, int page, int limit, String sort, 
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			if(ProfiloUtenteEnum.GESTORE.getValue() == confUtente.getFkTipoProfilo()
					|| ProfiloUtenteEnum.VIVAIO.getValue() == confUtente.getFkTipoProfilo()) {
				if(ProfiloUtenteEnum.VIVAIO.getValue() == confUtente.getFkTipoProfilo()) {
					 body.setIdVivaio(confUtente.getFkVivaio());
				}
				return Response.ok(assegnazioneService.getPaginatedSearchDdt(body, page, limit, sort)).build();
			}
			return Response.ok().build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
