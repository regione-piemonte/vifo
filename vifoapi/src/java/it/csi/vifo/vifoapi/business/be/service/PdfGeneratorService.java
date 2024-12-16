/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service;

import javax.servlet.http.HttpServletRequest;

import it.csi.vifo.vifoapi.util.TipoPdfEnum;

public interface PdfGeneratorService {
	
	public byte[] generateDichiarazione(TipoPdfEnum tipoPdf, Integer id, HttpServletRequest httpRequest) throws Exception;
}
