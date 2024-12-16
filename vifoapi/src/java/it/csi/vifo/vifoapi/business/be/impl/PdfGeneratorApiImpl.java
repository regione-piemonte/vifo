/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.PdfGeneratorApi;
import it.csi.vifo.vifoapi.business.be.service.PdfGeneratorService;
import it.csi.vifo.vifoapi.util.TipoPdfEnum;
import it.csi.vifo.vifoapi.util.templating.TemplateUtil;

@Component
public class PdfGeneratorApiImpl extends BaseApiServiceImpl implements PdfGeneratorApi {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	private PdfGeneratorService pdfGeneratorService;


	@Override
	public Response generatePdf(String pdfType, Integer id, 
			SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			TipoPdfEnum tipoPdfEnum = TipoPdfEnum.valueOf(pdfType);
			
			byte[] bytes = pdfGeneratorService.generateDichiarazione(tipoPdfEnum, id, httpRequest);
			String fileName = TemplateUtil.getFileName(tipoPdfEnum);
		
			ResponseBuilder response = null;
			 response = Response.ok(bytes,"application/pdf").header("Content-disposition", "attachment; filename=" + fileName);
			return response.build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
