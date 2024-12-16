/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.PdfAttitudine;
import it.csi.vifo.vifoapi.dto.SpecieRichiesta;

public interface SpecieRichiestaDAO {
	
	void insert(SpecieRichiesta item);

	List<SpecieRichiesta> getByIdDomanda(Integer idDomanda);
	List<PdfAttitudine> getAttitudiniByIdDomanda(Integer idDomanda);
	int deleteByIdDomanda(Integer idDomanda);
}
