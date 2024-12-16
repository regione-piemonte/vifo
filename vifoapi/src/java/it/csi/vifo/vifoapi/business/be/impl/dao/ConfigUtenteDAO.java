/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.ConfigUtente;

public interface ConfigUtenteDAO {
	
	ConfigUtente getConfigUtenteById(int configUtenteId);
	
	int createConfigUtente(ConfigUtente configUtente);
	
	void updateConfigUtente(ConfigUtente configUtente);

	ConfigUtente getConfigUtenteBySoggettoId(int id);

	List<ConfigUtente> getConfigUtenteBySoggettoIdAnProfilo(int id, int profilo);

	List<ConfigUtente> getConfUtenteBySoggIdAnProfOrderByDate(int id, int profilo);

}
