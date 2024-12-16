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

import it.csi.vifo.vifoapi.business.be.InventarioApi;
import it.csi.vifo.vifoapi.business.be.exception.RecordNotUniqueException;
import it.csi.vifo.vifoapi.business.be.service.AdpforHomeService;
import it.csi.vifo.vifoapi.business.be.service.InventarioService;
import it.csi.vifo.vifoapi.dto.CertificatoProvPiemonte;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.InventarioFilter;
import it.csi.vifo.vifoapi.dto.PartitaFrutti;
import it.csi.vifo.vifoapi.dto.PartitaPiante;
import it.csi.vifo.vifoapi.dto.PartitaSemi;
import it.csi.vifo.vifoapi.dto.RichiestaTrasferimentoPiante;
import it.csi.vifo.vifoapi.dto.Vivaio;
import it.csi.vifo.vifoapi.util.ErrorResponseEnum;
import it.csi.vifo.vifoapi.util.ProfiloUtenteEnum;

@Component
public class InventarioApiServiceImpl extends BaseApiServiceImpl implements InventarioApi {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	InventarioService inventarioService;
	
	@Autowired
	AdpforHomeService adpforHomeService;

	@Override
	public Response getListInventarioFrutti(int page, int limit, String sort,Integer numeroPartita, String specie, int annata, boolean attive,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			if(!isUserGestore(httpRequest, adpforHomeService)) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, "user not allowed"));
				return getErrorResponse(ErrorResponseEnum.UNAUTHORIZED);
			}
			InventarioFilter filter = new InventarioFilter(numeroPartita,specie, annata, attive);
			return Response.ok(inventarioService.getListInventarioFrutti(page, limit, sort, filter)).build();
		} catch (RecordNotUniqueException e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response getInventarioFruttiRiepilogo(int id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(inventarioService.getInventarioFruttiRiepilogoById(id)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getDetailsInventarioFrutti(int id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return Response.ok(inventarioService.getPartitaFruttaById(id)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response savePartitaFrutti(PartitaFrutti partitaFrutti, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return Response.ok(inventarioService.savePartitaFrutta(partitaFrutti, confUtente)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response updatePartitaFrutti(PartitaFrutti partitaFrutti, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return Response.ok(inventarioService.savePartitaFrutta(partitaFrutti, confUtente)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response deleteInventarioFruttiById(int id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			inventarioService.deletePartitaFrutta(id);
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	

	@Override
	public Response getListInventarioSemi(int page, int limit, String sort,Integer numeroPartita, String specie, int annata, boolean attive,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			if(!isUserGestore(httpRequest,adpforHomeService)) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"user not allowed"));
				return getErrorResponse(ErrorResponseEnum.UNAUTHORIZED);
			}
			InventarioFilter filter = new InventarioFilter(numeroPartita,specie, annata, attive);
			return Response.ok(inventarioService.getListInventarioSemi(page, limit, sort, filter)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getDetailsInventarioSemi(int id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(inventarioService.getPartitaSemiById(id)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}		
	}
	
	@Override
	public Response getRiepilogoSemiById(int id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(inventarioService.getInventarioSemiRiepilogoById(id)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response savePartitaSemi(PartitaSemi partitaSemi, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return Response.ok(inventarioService.savePartitaSemi(partitaSemi, confUtente)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response updatePartitaSemi(PartitaSemi partitaSemi, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return Response.ok(inventarioService.savePartitaSemi(partitaSemi, confUtente)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response deleteInventarioSemiById(int id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			inventarioService.deletePartitaSemi(id);
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getListInventarioPiantine(int page, int limit, String sort, int idVivaio,Integer numeroPartita, String specie, int annata,
			boolean attive, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			if(!isUserGestore(httpRequest,adpforHomeService)) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"user not allowed"));
				return getErrorResponse(ErrorResponseEnum.UNAUTHORIZED);
			}
			InventarioFilter filter = new InventarioFilter(idVivaio,numeroPartita, specie, annata, attive);
			return Response.ok(inventarioService.getListInventarioPiante(page, limit, idVivaio, sort, filter)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response getRiepilogoPiantineById(int id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return Response.ok(inventarioService.getRiepilogoPiantineById(id)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getDetailsInventarioPiantine(int id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return Response.ok(inventarioService.getPartitaPianteById(id)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response savePartitaPiantine(PartitaPiante partitaPiante, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return Response.ok(inventarioService.savePartitaPiante(partitaPiante, confUtente)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response updatePartitaPiantine(PartitaPiante partitaPiante, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return Response.ok(inventarioService.savePartitaPiante(partitaPiante, confUtente)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response deleteInventarioPiantineById(int id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			inventarioService.deletePartitaPiante(id);
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	
	public static boolean isUserGestore(HttpServletRequest httpRequest, AdpforHomeService adpforHomeService) throws RecordNotUniqueException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo("InventarioApiServiceImpl", methodName));
	        ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return ProfiloUtenteEnum.GESTORE.getValue() == confUtente.getFkTipoProfilo() 
					|| ProfiloUtenteEnum.VIVAIO.getValue() == confUtente.getFkTipoProfilo();
		
		}finally {
			LOGGER.debug(getClassFunctionEndInfo("InventarioApiServiceImpl", methodName));
		}
	}

	@Override
	public Response saveCertificatoPiemonte(CertificatoProvPiemonte certificato, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return Response.ok(inventarioService.saveCertificatoPiemonte(certificato, confUtente)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
		
	}
	
	@Override
	public Response updateCertificatoPiemonte(CertificatoProvPiemonte certificato, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return Response.ok(inventarioService.saveCertificatoPiemonte(certificato, confUtente)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getCertificatoById(int id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(inventarioService.getCertificatoPiemonteById(id)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getListCertificatiPiemonte(int page, int limit, String sort, String specie, int annata,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        InventarioFilter filter = new InventarioFilter(specie, annata, false);
	        return Response.ok(inventarioService.getPaginatedCertificati(page, limit, sort, filter)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response saveRichiestaTrasferimentoPiante(RichiestaTrasferimentoPiante richiestaTrasf, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return Response.ok(inventarioService.saveRichiestaTrasferimentoPiante(richiestaTrasf, confUtente)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getListTrasferimentoPiantine(int page, int limit, String sort, 
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return Response.ok(inventarioService.getListTrasferimentoPiantine(page, limit, sort, confUtente)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getCountTrasferimentoPiantine(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			return Response.ok(inventarioService.getCountTrasferimentoPiantine(confUtente.getFkVivaio())).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getRichiestaTrasferimentoById(int id, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(inventarioService.getRichiestaTrasferimentoById(id)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response accettaRifiutaTrasferimento(RichiestaTrasferimentoPiante richiesta, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			richiesta.setFkConfigUtenteValidTrasf(confUtente.getIdConfigUtente());
			return Response.ok(inventarioService.accettaRifiutaTrasferimento(richiesta)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getInfoAnnataInventario(int idVivaio, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			if(ProfiloUtenteEnum.VIVAIO.getValue() == confUtente.getFkTipoProfilo()) {
				idVivaio = confUtente.getFkVivaio();
			}else if(ProfiloUtenteEnum.GESTORE.getValue() != confUtente.getFkTipoProfilo()) {
				return getErrorResponse(ErrorResponseEnum.UNAUTHORIZED);
			}
			return Response.ok(inventarioService.getInfoAnnataInventario(idVivaio)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getCreaAnnataInventario(Vivaio body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			if(ProfiloUtenteEnum.VIVAIO.getValue() == confUtente.getFkTipoProfilo()) {
				body.setIdVivaio(confUtente.getFkVivaio());
			}else if(ProfiloUtenteEnum.GESTORE.getValue() != confUtente.getFkTipoProfilo()) {
				return getErrorResponse(ErrorResponseEnum.UNAUTHORIZED);
			}
			return Response.ok(inventarioService.creaNuovaAnnataInventario(body.getIdVivaio(), confUtente)).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
