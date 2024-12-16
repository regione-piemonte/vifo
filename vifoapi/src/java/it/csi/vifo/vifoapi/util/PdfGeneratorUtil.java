/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util;

public class PdfGeneratorUtil {
	public static String getCodiceVivaioBySigle(String sigla) {
		if("CA".equals(sigla)) {
			return "TO01";
		}else if("FE".equals(sigla)) {
			return "VC01";
		}else if("GA".equals(sigla)) {
			return "CN01";
		}		
		return "";
	}
}
