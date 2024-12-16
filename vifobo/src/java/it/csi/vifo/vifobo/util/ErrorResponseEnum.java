/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.util;

public enum ErrorResponseEnum {
	GENERIC(500, "E100", "ERRORE GENERICO"),
	DATA_INSERT(409, "", "Errore inserimento elemento"),
	DATA_UPDATE(404, "", "errore aggiornamento elemento"),
	DATA_DELETE(404, "", "Errore cancelazione elemento"),
	DATA_NOT_FOUND(404, "", "Elemento non trovato"),
	DATA_NOT_UNIQUE(404, "", "Piu' elementi trovati invece che uno"),
	UNAUTHORIZED(401,"","Utente non autorizzato"),
	INDEX_UPLOAD(400, "I011", "Errore caricamento file su Index"),
	DIGITAL_SIGNATURE(400, "E015", "Risultano delle anomalie nel file firmato digitalmente");
	
	private int status;
	private String code;
	private String title;
	
	private ErrorResponseEnum(int status, String code, String title) {
		this.status = status;
		this.code = code;
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}
	
}
