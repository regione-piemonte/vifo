/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.Domanda;
import it.csi.vifo.vifoapi.dto.DomandaRiepilogo;
import it.csi.vifo.vifoapi.dto.InfoDomanda;
import it.csi.vifo.vifoapi.dto.RicercaDomanda;
import it.csi.vifo.vifoapi.util.PaginatedList;

public interface DomandaDAO {
	
	List<DomandaRiepilogo> getRiepilogoDomandeBySoggetto(Integer idSoggetto);
	
	int getNumDomandeBySoggetto(Integer idSoggetto, Integer idVivaio);
	
	PaginatedList<DomandaRiepilogo> getPaginatedRiepilogoDomandeBySoggetto(Integer idSoggetto, int page, int limit, String sort);

	int createDomanda(Domanda domanda);
	
	int updateDomanda(Domanda domanda);
	
	Domanda loadDomanda(Integer idDomanda);
	
	int deleteDomanda(Integer idDomanda);
	
	PaginatedList<DomandaRiepilogo> getPaginatedSearchDomande(RicercaDomanda ricerca, int page, int limit, String sort);
	
	DomandaRiepilogo getDomandaRiepilogoByIdDomanda(int idDomanda);
	
	int resetImporti(Integer idDomanda);
	
	int insertBoprocLog(String idPratica, int step);

}
