/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifobo.dto.DocumentoAllegato;

public interface DocumentoAllegatoDAO {
	int save(DocumentoAllegato documento);
	int updateUidIndex(DocumentoAllegato documento);
	List<DocumentoAllegato> getByIdDomanda(Integer idDomanda);
	DocumentoAllegato getByIdAllegato(Integer idAllegato);
	int deleteByIdAllegato(Integer idAllegato);
	int deleteByIdDomanda(Integer idDomanda);
}
