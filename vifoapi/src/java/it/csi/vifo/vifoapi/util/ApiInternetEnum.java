/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

public enum ApiInternetEnum {
	API_MANAGER(0),
	API_COSMO(1);
	
private int value;
	
	private ApiInternetEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
