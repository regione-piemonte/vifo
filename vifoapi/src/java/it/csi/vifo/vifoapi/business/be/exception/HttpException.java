/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.exception;

public class HttpException extends Exception{

	private static final long serialVersionUID = -7552634017421257809L;
	
	private Integer statusCode;
	
	public HttpException() {}

	public HttpException(String message, Throwable cause) {
		super(message, cause);
	}
	public HttpException(Throwable cause) {
		super(cause);
	}
	
	public HttpException(String message, Throwable cause, int statusCode) {
		super(message, cause);
		this.statusCode = statusCode;
	}
	
	public HttpException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusCode
	 */
	public Integer getStatusCode() {
		return statusCode;
	}
}
