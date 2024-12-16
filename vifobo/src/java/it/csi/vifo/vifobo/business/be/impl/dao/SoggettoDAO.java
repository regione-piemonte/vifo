/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao;


import it.csi.vifo.vifobo.business.be.exception.RecordNotFoundException;
import it.csi.vifo.vifobo.dto.TSoggetto;


public interface SoggettoDAO {

	TSoggetto findSoggettoById(Integer idSoggetto) throws RecordNotFoundException;


}
