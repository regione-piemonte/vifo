/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl;

import java.io.InputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.vifo.vifoapi.business.be.SpecieArboreeApi;
import it.csi.vifo.vifoapi.business.be.service.AdpforHomeService;
import it.csi.vifo.vifoapi.business.be.service.DizionarioService;
import it.csi.vifo.vifoapi.business.be.service.DocumentoService;
import it.csi.vifo.vifoapi.business.be.service.SpecieArboreeService;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.FileUploadForm;
import it.csi.vifo.vifoapi.dto.Specie;
import it.csi.vifo.vifoapi.util.InputPartUtils;
import it.csi.vifo.vifoapi.util.TipoFotoEnum;

@Component
public class SpecieArboreeApiImpl extends BaseApiServiceImpl implements SpecieArboreeApi {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	SpecieArboreeService specieArboreeService;
	
	@Autowired
	AdpforHomeService adpforHomeService;
	
	@Autowired
	DocumentoService documentoService;
	
	@Autowired
	DizionarioService dizionarioService;

	@Override
	public Response saveSpecie( MultipartFormDataInput data, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return saveSpecieImpl(data, httpRequest);
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Response updateSpecie( MultipartFormDataInput data, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return saveSpecieImpl(data, httpRequest);
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	Response saveSpecieImpl( MultipartFormDataInput data, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			Map<String, List<InputPart>> mapData = data.getFormDataMap();
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);

			InputPart inputPart = mapData.get("data").get(0);
			String specieObjAsString = URLDecoder.decode(inputPart.getBodyAsString(), "UTF-8");
			
			ObjectMapper objectMapper = new ObjectMapper();
	        Specie datiSpecie = objectMapper.readValue(specieObjAsString, Specie.class);
	        
	        
	        datiSpecie.setFkConfigUtente(confUtente.getIdConfigUtente());
	        
	        Specie res = specieArboreeService.saveSpecieArborea(datiSpecie);
			
			if(mapData.get(TipoFotoEnum.FOTO_SPECIE.toString()) != null) {
				saveFoto(mapData, res.getIdSpecie(), TipoFotoEnum.FOTO_SPECIE, confUtente);		
			}
			if(mapData.get(TipoFotoEnum.FOTO_IN_VIVAIO.toString()) != null) {
				saveFoto(mapData, res.getIdSpecie(), TipoFotoEnum.FOTO_IN_VIVAIO, confUtente);
			}
			if(mapData.get(TipoFotoEnum.FOTO_DI_PARTICOLARE.toString()) != null) {
				saveFoto(mapData, res.getIdSpecie(), TipoFotoEnum.FOTO_DI_PARTICOLARE, confUtente);
			}
	        
			return Response.ok(dizionarioService.getSpecieById(res.getIdSpecie())).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private void saveFoto(Map<String, List<InputPart>> mapData, Integer idSpecie,
			TipoFotoEnum tipoFoto, ConfigUtente confUtente) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));FileUploadForm fileUpload = new FileUploadForm();
			InputPart inputPart = mapData.get(tipoFoto.toString()).get(0);
			
			InputStream is = inputPart.getBody(InputStream.class, null);
			fileUpload.setData(IOUtils.toByteArray(is));
			Integer fileSize = fileUpload.getData().length;
			fileUpload.setName(InputPartUtils.getFileNameWithExtension(inputPart));
		
			documentoService.uploadImgSpecie(idSpecie, tipoFoto, fileUpload, confUtente);

		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


}
