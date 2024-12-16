/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

public enum TipoFotoEnum {
	FOTO_SPECIE(1),
	FOTO_IN_VIVAIO(2),
	FOTO_DI_PARTICOLARE(3);
	
private int value;
	
	private TipoFotoEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static TipoFotoEnum getEnum(int instanceTypeValue) {
		for (TipoFotoEnum instance : TipoFotoEnum.values()) { 
		    if(instance.getValue() == instanceTypeValue) {
		    	return instance;
		    } 
		}
		throw new IllegalArgumentException("TipoFotoEnum does not have enum with provided parameter");
	}

}
