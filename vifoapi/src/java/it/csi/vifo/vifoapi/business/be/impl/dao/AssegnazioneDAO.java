/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.Assegnazione;

public interface AssegnazioneDAO {
	
	List<Assegnazione> getAssegnazioniByIdDomanda(Integer idDomanda, Integer idVivaio);
	List<Assegnazione> getAssegnazioniNRByIdDomanda(Integer idDomanda);
	List<Assegnazione> getRiepilogoAssegnazioniByIdDomanda(Integer idDomanda);
	int insertAssegnata(Assegnazione item, Integer idDomanda, Integer idConfUtente);
	int deleteAssegnata(Integer idDomanda);
	List<Assegnazione> getPartiteBySpecieAndVivaio(int idSpecie, int idVivaio);
	int updateRitiroAssegnazioni(Assegnazione assegnazione, Integer idConfUtente);
	int updateQtaRitirataAssegnazioni(Integer idAssegnata);
}
