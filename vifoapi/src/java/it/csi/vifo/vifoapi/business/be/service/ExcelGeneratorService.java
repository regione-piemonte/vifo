/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service;

import java.io.ByteArrayOutputStream;

import it.csi.vifo.vifoapi.dto.ConfigUtente;

public interface ExcelGeneratorService {

	ByteArrayOutputStream generateExcelSemi();
	ByteArrayOutputStream generateExcelPiantine(Integer vivaio);
	ByteArrayOutputStream generateExcelFrutti();
	ByteArrayOutputStream generateExcelCaricoScarico(Integer idVivaio);
	
}
