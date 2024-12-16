/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.validation;

import java.util.HashMap;

import it.csi.vifo.vifoapi.business.be.exception.ValidationException;

public class UtilValidator {

	
	public static final String MANDATORY_FIELD_MESSAGE = "Campo obligatorio!";
	
	public static void checkNotNullFiledValidity(String fieldName, HashMap<String,String> errorMap, Object value, Object defaultValue, String defaultMessage, Object... messageArgs) {
		if (value ==null || (defaultValue!=null && defaultValue.equals(value))) {
			errorMap.put(fieldName, String.format(defaultMessage, messageArgs));
		}
	}
	
	public static void checkNotNullFiledValidity(String fieldName, HashMap<String,String> errorMap, Object value, String defaultMessage, Object... messageArgs) throws ValidationException {
		if (value ==null ) {
			errorMap.put(fieldName, String.format(defaultMessage, messageArgs));
		}
	}
}
