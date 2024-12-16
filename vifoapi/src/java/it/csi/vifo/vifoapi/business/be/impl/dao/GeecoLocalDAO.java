/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.business.be.exception.RecordNotFoundException;
import it.csi.vifo.vifoapi.dto.GeecoLayer;
import it.csi.vifo.vifoapi.dto.GeecoProfilo;

public interface GeecoLocalDAO {
	
	
	public GeecoProfilo findProfiloById(String idProfiloGeeco)throws RecordNotFoundException;
	public List<GeecoLayer> findLayerByIdProfilo(String idProfiloGeeco);
	
}
