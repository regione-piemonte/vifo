/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.util;

public enum CodicePagamentoEnum {
	ATTESA_PAGAMENTO(9,"Attesa Pagamento"),
	 SUCCESSFULL(0,"Pagamento eseguito"),
	 CANCELED(1,"Annullato"),
	 UNSUCCESFUL(1,"Annullato"),
	 INITIALIZED(9,"In Corso"),
	 CONFIGURED(9,"In Corso"),
	 STARTED(9,"In Corso"),
	 ATTESA_RT(9,"Attesa RT");
	
	private int value;
	private String descrizione;
	
	private CodicePagamentoEnum(int value,String descrizione) {
		this.value = value;
		this.descrizione = descrizione;
	}
	
	public int getValue() {
		return value;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public static CodicePagamentoEnum getEnumByDescrizione(String instanceTypeValue) {
		for (CodicePagamentoEnum instance : CodicePagamentoEnum.values()) { 
		    if(instance.getDescrizione().equalsIgnoreCase(instanceTypeValue)) {
		    	return instance;
		    } 
		}
		throw new IllegalArgumentException("CodicePagamentoEnum does not have enum with descrizione:" + instanceTypeValue);
	}
	
	public static CodicePagamentoEnum getEnumByValue(int value) {
		for (CodicePagamentoEnum instance : CodicePagamentoEnum.values()) { 
		    if(instance.getValue() == value) {
		    	return instance;
		    } 
		}
		throw new IllegalArgumentException("CodicePagamentoEnum does not have enum with provided value:" + value);
	}
}
