/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.DocumentoApi;
import it.csi.vifo.vifoapi.business.be.exception.ServiceException;
import it.csi.vifo.vifoapi.business.be.service.AdpforHomeService;
import it.csi.vifo.vifoapi.business.be.service.DocumentoService;
import it.csi.vifo.vifoapi.business.be.service.helper.IndexServiceHelper;
import it.csi.vifo.vifoapi.business.be.service.helper.SiapPagoPAServiceHelper;
import it.csi.vifo.vifoapi.dto.DocumentoAllegato;
import it.csi.vifo.vifoapi.dto.ErrorDTO;
import it.csi.vifo.vifoapi.dto.FileUploadForm;
import it.csi.vifo.vifoapi.util.SignatureTypeEnum;
import it.csi.vifo.vifoapi.util.TipoAllegatoEnum;
import it.csi.vifo.vifoapi.util.TipoDocumentoPagamentoEnum;
import it.csi.vifo.vifoapi.util.TipoFotoEnum;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.EsitoLeggiDocumento;

@Component
public class DocumentoApiServiceImpl extends BaseApiServiceImpl implements DocumentoApi {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	DocumentoService documentoService;
	
	@Autowired
	AdpforHomeService adpforHomeService;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	SiapPagoPAServiceHelper siapPagoPAServiceHelper;


	@Override
	public Response uploadDocumentiDomanda(int idDomanda, int tipo, FileUploadForm form,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        
	        IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");			
			String signatureType = indexSrvHelper.getSignatureType(form.getData());
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "signatureType: " + signatureType));
			TipoAllegatoEnum tipoAllegato = TipoAllegatoEnum.getEnum(tipo);
			String errore = null;
			if(signatureType.equals(SignatureTypeEnum.UNSIGNED.getValue()) && (tipoAllegato == TipoAllegatoEnum.ASSEGNAZIONE_GRATUITA_FIRMA_DIGITALE 
					|| tipoAllegato == TipoAllegatoEnum.ACCETTAZIONE_FIRMA_DIGITALE
					|| tipoAllegato == TipoAllegatoEnum.DOMANDA_GRATUITA_FIRMA_DIGITALE 
					|| tipoAllegato == TipoAllegatoEnum.DOMANDA_ONEROSA_FIRMA_DIGITALE)){
				errore = "Documento con firma digitale non valida";
			}else if(!signatureType.equals(SignatureTypeEnum.UNSIGNED.getValue()) && !(tipoAllegato == TipoAllegatoEnum.ASSEGNAZIONE_GRATUITA_FIRMA_DIGITALE 
					|| tipoAllegato == TipoAllegatoEnum.ACCETTAZIONE_FIRMA_DIGITALE
					|| tipoAllegato == TipoAllegatoEnum.DOMANDA_GRATUITA_FIRMA_DIGITALE 
					|| tipoAllegato == TipoAllegatoEnum.DOMANDA_ONEROSA_FIRMA_DIGITALE)){
				errore = "Documento non valido: e' presente una firma digitale, che non e' prevista per questa tipologia di documento";
			}
			if(errore != null) {
				Map<String,String> res = new HashMap<String,String>();
				res.put("error",errore);
				return Response.ok(res).build();
			}
			return Response.ok(documentoService.uploadDocumentiDomanda(idDomanda, tipo, form,
					adpforHomeService.getSessionConfigUtente(httpRequest))).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response uploadImgSpecie(int idSpecie, int fotoType, FileUploadForm form, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(documentoService.uploadImgSpecie(idSpecie, TipoFotoEnum.getEnum(fotoType), form,
					adpforHomeService.getSessionConfigUtente(httpRequest))).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response deleteDocumentoById(Integer idAllegato, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return Response.ok(documentoService.deleteAllegatoById(idAllegato)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response downloadPortalDocumento(int idAllegato, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServiceException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			DocumentoAllegato documentoAllegato = documentoService.getAllegatoById(idAllegato);
			return Response.ok(documentoService.getPortalAllegato(idAllegato))
				.header("Content-disposition", "attachment; filename=" + documentoAllegato.getNomeFile()).build();
	}catch (Exception e) {
		LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
		return errorResponse(e);
	}finally {
		LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
	}
	}

	@Override
	public Response downloadPortalImage(Integer idSpecie, Integer fotoType, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServiceException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return Response.ok(documentoService.getPortalFotoSpecie(idSpecie, fotoType)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response getAllegatiByIdDomanda(Integer idDomanda, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServiceException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return Response.ok(documentoService.getAllegatiByIdDomanda(idDomanda)).build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response downloadAvvisoPagamento(int idDomanda, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServiceException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        
	        EsitoLeggiDocumento res = siapPagoPAServiceHelper.getAvvisoPagamento(idDomanda);
	        if(res.getIdErrore() !=  null || res.getDescErrore() != null) {
//	        	 ErrorDTO error = new ErrorDTO("404", "KMJ", "Elemento non trovato", null, null);
//	             return Response.serverError().entity(error).status(404).build();
	        	throw new Exception("Error on SiapPagoPAServiceHelper - id: " + res.getIdErrore() 
	        	+ " - descrizione: " + res.getDescErrore());
	        }
	        return Response.ok( res.getFile())
	        		.header("Content-disposition", "attachment; filename=AvvisoPagamentoDomanda_"
	        				+ idDomanda + ".pdf").build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response downloadRicevutaPagamento(int idDomanda, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServiceException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        
	        EsitoLeggiDocumento res = siapPagoPAServiceHelper.getRicevutaPagamento(idDomanda);
	        if(res.getIdErrore() !=  null || res.getDescErrore() != null) {
	        	throw new Exception("Error on SiapPagoPAServiceHelper - id: " + res.getIdErrore() 
	        	+ " - descrizione: " + res.getDescErrore());
	        }
	        return Response.ok( res.getFile())
	        		.header("Content-disposition", "attachment; filename=RicevutaPagamentoDomanda_"
	        				+ idDomanda + ".pdf").build();
		}catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
