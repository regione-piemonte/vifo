/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.FotoSpecie;

public interface FotoSpecieDAO {
	
	int insert(FotoSpecie fotoSpecie);
	int update(FotoSpecie fotoSpecie);
	int updateUidIndex(FotoSpecie fotoSpecie);
	int deleteById(int idFotoSpecie);
	FotoSpecie findByIdSpecieAndType(int idSpecie, int idType);
}
