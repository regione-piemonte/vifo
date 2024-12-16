/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.Destinazione;

public interface DestinazioneDAO {

	int insert(Destinazione destinazione);
	
	List<Destinazione> getDestinazioniByIdDomanda(Integer idDomanda);
	
	int deleteByIdDomanda(Integer idDomanda);
}
