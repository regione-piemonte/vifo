/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

public interface AttitudineSpecieDAO {

	int deleteByIdSpecie(Integer idSpecie);
	void insertList(Integer idSpecie, List<Integer> idsAttidudine, Integer idConfUtente);
}
