/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.Scopo;

public interface ScopoDAO {
	
	List<Scopo> getListScopo();
	
	Scopo getScopoById(Integer idScopo);

}
