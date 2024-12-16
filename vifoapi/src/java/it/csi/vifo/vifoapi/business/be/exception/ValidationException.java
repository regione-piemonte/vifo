/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.csi.vifo.vifoapi.util.CodiceEnum;
import it.csi.vifo.vifoapi.util.ErrorEnum;
import it.csi.vifo.vifoapi.validation.BaseException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends BaseException {   /// extends Exception{

	private static final long serialVersionUID = 8702644029945350717L;
	
	public ValidationException() {
		this.setCodice(CodiceEnum.E03);
		this.setTesto(ErrorEnum.VALIDATION_ERROR.toString());		
	}

	public ValidationException(String testo) {
		this.setCodice(CodiceEnum.E03);
		this.setTesto(testo);
	}
	
	public ValidationException(CodiceEnum codice, String testo) {
		this.setCodice(codice);
		this.setTesto(testo);
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return null;
	}
}
