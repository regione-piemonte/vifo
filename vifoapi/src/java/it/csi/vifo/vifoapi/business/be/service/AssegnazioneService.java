/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service;

import java.util.List;

import it.csi.vifo.vifoapi.dto.Assegnazione;
import it.csi.vifo.vifoapi.dto.BaseDdt;
import it.csi.vifo.vifoapi.dto.Ddt;
import it.csi.vifo.vifoapi.dto.DdtRiepilogo;
import it.csi.vifo.vifoapi.dto.RicercaDdt;
import it.csi.vifo.vifoapi.util.PaginatedList;

public interface AssegnazioneService {
	
	public List<Assegnazione> getListAssegnazioniByDomanda(Integer idDomanda);
	
	public List<Assegnazione> getListAssegnazioniNRByDomanda(Integer idDomanda);
	
	public List<Assegnazione> getRiepilogoAssegnazioniByIdDomanda(Integer idDomanda);
	
	public void saveAssegnazioni(Assegnazione[] listAssegnazioni, int idDomanda, Integer idConfUtente);
	
	public List<Assegnazione>getPartiteBySpecieAndVivaio(int idSpecie, int idVivaio);
	
	public void updateRitiroAssegnazioni(Assegnazione[] listAssegnazioni, Integer idConfUtente);
	
	public List<Ddt> getAvailableDdtByIdDomanda(Integer idDomanda);
	
	public List<BaseDdt> getListDdtByIdDomanda(Integer idDomanda);
	
	public DdtRiepilogo getInfoDdt(Integer numeroDdt, Integer anno);
	
	public List<Ddt> getDdtByNumeroDdtAndAnno(Integer numeroDdt, Integer anno);
	
	public void saveDdt(Ddt[] listDdt, Integer idConfUtente);
	
	public void updateDdt(Ddt[] listDdt, Integer numeroDdt, Integer anno, Integer idConfUtente);
	
	public void annullaDdt(Integer numeroDdt, Integer anno);
	
	public PaginatedList<DdtRiepilogo> getPaginatedSearchDdt(RicercaDdt ricerca, int page, int limit, String sort);
}
