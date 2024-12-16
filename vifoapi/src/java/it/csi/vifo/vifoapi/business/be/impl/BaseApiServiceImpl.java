/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.core.Response;

import it.csi.vifo.vifoapi.business.be.exception.DAOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import it.csi.vifo.vifoapi.business.be.exception.RecordNotFoundException;
import it.csi.vifo.vifoapi.business.be.exception.RecordNotUniqueException;
import it.csi.vifo.vifoapi.dto.ErrorDTO;
import it.csi.vifo.vifoapi.util.Constants;
import it.csi.vifo.vifoapi.util.ErrorResponseEnum;
import org.springframework.dao.DataIntegrityViolationException;

public abstract class BaseApiServiceImpl {
	/**
     * The constant LOGGER.
     */
    protected static Logger LOGGER = Logger.getLogger(Constants.LOGGER_NAME + ".business");
    
    /**
     * Gets class function begin info.
     *
     * @param className  the class name
     * @param methodName the method name
     * @return the class function begin info
     */
    protected static String getClassFunctionBeginInfo(String className, String methodName) {
        return getClassFunctionDebugString(className, methodName, "BEGIN");
    }

    /**
     * Gets class function end info.
     *
     * @param className  the class name
     * @param methodName the method name
     * @return the class function end info
     */
    protected static String getClassFunctionEndInfo(String className, String methodName) {
        return getClassFunctionDebugString(className, methodName, "END");
    }

    /**
     * Gets class function error info.
     *
     * @param className  the class name
     * @param methodName the method name
     * @param error      the error
     * @return the class function error info
     */
    protected static String getClassFunctionErrorInfo(String className, String methodName, Object error) {
        return getClassFunctionDebugString(className, methodName, "ERROR : " + error);
    }

    /**
     * Gets class function debug string.
     *
     * @param className  the class name
     * @param methodName the method name
     * @param info       the info
     * @return the class function debug string
     */
    protected static String getClassFunctionDebugString(String className, String methodName, String info) {
        String functionIdentity = "[" + className + "::" + methodName + "] ";
        return functionIdentity + info;
    }
    
    /**
     * Get method name string.
     *
     * @param stack the stack
     * @return the string
     */
    public String getMethodName(int stack){
        return Thread.currentThread().getStackTrace()[stack].getMethodName();
    }
    
    /**
     * Get response error response.
     *
     * @param className    the class name
     * @param methodName   the method name
     * @param status       the status
     * @param codeError    the code error
     * @param titleError   the title error
     * @param detailsError the details error
     * @return the response
     */
    protected Response getResponseError(Integer status, String codeError, String title) {
        ErrorDTO error = new ErrorDTO(status+ "", codeError, "title", null,null);
        return Response.serverError().entity(error).status(status).build();
    }
    
   
    
    /**
     * Log begin.
     *
     * @param classname  the classname
     * @param methodName the method name
     */
    protected void logBegin(String classname, String methodName) {
        logDebug(classname, methodName, "BEGIN");
    }

    /**
     * Log begin.
     *
     * @param classname the classname
     */
    protected void logBegin(String classname) {
        logDebug(classname, getMethodName(3), "BEGIN");
    }

    /**
     * Log begin info.
     *
     * @param className  the class name
     * @param methodName the method name
     * @param obj        the obj
     */
    protected void logBeginInfo(String className, String methodName, Object obj) {
        logBegin(className, methodName);
        if (obj != null) {
            String inputParam = "Parametro in input " + obj.getClass().getSimpleName() + ":\n" + obj + "\n";
            logDebug(className, methodName, inputParam);
        }
    }

    /**
     * Log begin info.
     *
     * @param className the class name
     * @param obj       the obj
     */
    protected void logBeginInfo(String className, Object obj) {
        logBeginInfo(className, getMethodName(3), obj);
    }

    /**
     * Log begin info.
     *
     * @param className  the class name
     * @param methodName the method name
     */
    protected void logBeginInfo(String className, String methodName) {
        logBeginInfo(className, methodName, null);
    }

    /**
     * Log end.
     *
     * @param classname  the classname
     * @param methodName the method name
     */
    protected void logEnd(String classname, String methodName) {
        logDebug(classname, methodName, "END");
    }

    /**
     * Log end.
     *
     * @param classname the classname
     */
    protected void logEnd(String classname) {
        logDebug(classname, getMethodName(3), "END");
    }

    /**
     * Log debug.
     *
     * @param classname  the classname
     * @param methodName the method name
     * @param info       the info
     */
    protected void logDebug(String classname, String methodName, String info) {
        log(classname, methodName, info, "DEBUG");
    }

    /**
     * Log debug.
     *
     * @param classname the classname
     * @param info      the info
     */
    protected void logDebug(String classname, String info) {
        log(classname, getMethodName(3), info, "DEBUG");
    }

    /**
     * Log error.
     *
     * @param classname  the classname
     * @param methodName the method name
     * @param error      the error
     */
    protected void logError(String classname, String methodName, Object error) {
        log(classname, methodName, error, "ERROR");
    }

    /**
     * Log error.
     *
     * @param classname the classname
     * @param error     the error
     */
    protected void logError(String classname, Object error) {
        log(classname, getMethodName(3), error, "ERROR");
    }

    /**
     * Log info.
     *
     * @param classname  the classname
     * @param methodName the method name
     * @param info       the info
     */
    protected void logInfo(String classname, String methodName, String info) {
        log(classname, methodName, info, "INFO");
    }

    /**
     * Log info.
     *
     * @param classname the classname
     * @param info      the info
     */
    protected void logInfo(String classname, String info) {
        log(classname, getMethodName(3), info, "INFO");
    }

    /**
     * Log.
     *
     * @param classname  the classname
     * @param methodName the method name
     * @param info       the info
     * @param type       the type
     */
    protected void log(String classname, String methodName, Object info, String type) {
        if (StringUtils.isNotBlank(methodName) && info != null && StringUtils.isNotBlank(type)) {
            String log = "[" + classname + "::" + methodName + "] " + info;
            switch (type.toUpperCase(Locale.ROOT)) {
                case "DEBUG":
                    LOGGER.debug(log);
                    break;
                case "ERROR":
                    LOGGER.error(log);
                    break;
                default:
                    LOGGER.info(log);
                    break;
            }
        }
    }
    
 
    public static Response errorResponse(Exception e) {		
		if(e instanceof RecordNotFoundException) {
			return getErrorResponse(ErrorResponseEnum.DATA_NOT_FOUND, e);
		}
		else if(e instanceof RecordNotUniqueException) {
			return getErrorResponse(ErrorResponseEnum.DATA_NOT_FOUND,e);
		}
        else if(e instanceof DataIntegrityViolationException |e instanceof DAOException | e instanceof NullPointerException) {
            return getErrorResponse(ErrorResponseEnum.DATA_NOT_VALID);
        }
		else {
			return getErrorResponse(ErrorResponseEnum.GENERIC,e);
		}
	}
    
    public static Response getErrorResponse(ErrorResponseEnum error) {
    	return getErrorResponse(error, null);
    	
    }
    
    private static Response getErrorResponse(ErrorResponseEnum error, Exception e) {
    	Map<String, String> detail = null;
    	if(e != null) {
    		detail = new HashMap<String, String>();
    		detail.put("errorMessage",e.getMessage());
    	}
    	ErrorDTO dto = new ErrorDTO(error.getStatus() + "", error.getCode(), error.getTitle(), detail,null);
        return Response.serverError().entity(dto).status(error.getStatus()).build();
    	
    }
}
