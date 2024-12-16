/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import it.csi.vifo.vifoapi.business.be.ExcelGeneratorApi;
import it.csi.vifo.vifoapi.business.be.service.AdpforHomeService;
import it.csi.vifo.vifoapi.business.be.service.ExcelGeneratorService;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.util.ErrorResponseEnum;
import it.csi.vifo.vifoapi.util.ProfiloUtenteEnum;

@Component
public class ExcelGeneratorApiImpl extends BaseApiServiceImpl implements ExcelGeneratorApi {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	AdpforHomeService adpforHomeService;
	
	@Autowired
	ExcelGeneratorService excelGeneratorService;

	@Override
	public Response generateExcelSemi(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			if(!InventarioApiServiceImpl.isUserGestore(httpRequest, adpforHomeService)) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"user not allowed"));
				return getErrorResponse(ErrorResponseEnum.UNAUTHORIZED);
			}
			
			ByteArrayOutputStream baos = excelGeneratorService.generateExcelSemi();
			
			
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
			String formatDateTime = now.format(formatter);
			String filename = "Inventario_semi_" + formatDateTime;

			ResponseBuilder response = Response.ok(baos.toByteArray());
			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
			return response.build();
			
			
			
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response generateExcelPiantine(Integer idVivaio, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			ConfigUtente confUtente = adpforHomeService.getSessionConfigUtente(httpRequest);
			if(!InventarioApiServiceImpl.isUserGestore(httpRequest, adpforHomeService)) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"user not allowed"));
				return getErrorResponse(ErrorResponseEnum.UNAUTHORIZED);
			}
			
			ByteArrayOutputStream baos = excelGeneratorService.generateExcelPiantine(idVivaio);
			
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
			String formatDateTime = now.format(formatter);
			String filename = "Inventario_piantine_" + formatDateTime;

			ResponseBuilder response = Response.ok(baos.toByteArray());
			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
			return response.build();
		}  catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response generateExcelFrutti(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			if(!InventarioApiServiceImpl.isUserGestore(httpRequest, adpforHomeService)) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"user not allowed"));
				return getErrorResponse(ErrorResponseEnum.UNAUTHORIZED);
			}
			ByteArrayOutputStream baos = excelGeneratorService.generateExcelFrutti();
			
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
			String formatDateTime = now.format(formatter);
			String filename = "Inventario_frutti_" + formatDateTime;

			ResponseBuilder response = Response.ok(baos.toByteArray());
			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
			return response.build();
		}  catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response generateExcelCaricoScarico(SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			httpRequest.getParameter("idVivaio");
			Integer idVivaio = null;
			String param = httpRequest.getParameter("idVivaio");
			if(param != null) {
				try {
					idVivaio = Integer.parseInt(param);
				}catch(NumberFormatException ex) {
					LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,
							" can not convert string: "	+ param));
				}
			}
			if(!InventarioApiServiceImpl.isUserGestore(httpRequest, adpforHomeService)) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName,"user not allowed"));
				return getErrorResponse(ErrorResponseEnum.UNAUTHORIZED);
			}
			ByteArrayOutputStream baos = excelGeneratorService.generateExcelCaricoScarico(idVivaio);
			
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
			String formatDateTime = now.format(formatter);
			String filename = "CaricoScarico_" + formatDateTime;

			ResponseBuilder response = Response.ok(baos.toByteArray());
			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
			return response.build();
		}  catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	
}

