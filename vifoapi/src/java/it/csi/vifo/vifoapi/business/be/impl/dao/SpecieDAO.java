/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.Specie;
import it.csi.vifo.vifoapi.util.PaginatedList;

public interface SpecieDAO {
	List<Specie> getAllSpecie(boolean all);
	List<Specie> getAllSpecieByGroup(boolean all);
	Specie getSpecieById(Integer idSpecie);
	PaginatedList<Specie> getPaginatedListSpecie(boolean all, int page, int limit, String sort, String filter);
	int save(Specie specie);
	int update(Specie specie);
	List<String>getListFamiglie();
}
