/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service;

import java.rmi.RemoteException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.Destinazione;
import it.csi.vifo.vifoapi.dto.Domanda;
import it.csi.vifo.vifoapi.dto.DomandaRiepilogo;
import it.csi.vifo.vifoapi.dto.InfoDomanda;
import it.csi.vifo.vifoapi.dto.Pagamento;
import it.csi.vifo.vifoapi.dto.RStatoDomanda;
import it.csi.vifo.vifoapi.dto.RicercaDomanda;
import it.csi.vifo.vifoapi.dto.SpecieRichiesta;
import it.csi.vifo.vifoapi.util.PaginatedList;
import it.csi.vifo.vifoapi.util.StatoDomandaEnum;

public interface DomandaService {

	List<DomandaRiepilogo> getRiepilogoDomandeBySoggetto(Integer idSoggetto);
	
	PaginatedList<DomandaRiepilogo> getPaginatedRiepilogoDomandeBySoggetto(Integer idSoggetto, int page, int limit, String sort);
	
	List<SpecieRichiesta> getSpecieRichiestaByIdDomanda(Integer idDomanda);
	
	List<Destinazione> getDestinazioniByIdDomanda(Integer idDomanda);
	
	int insertUpdateDomandaStep1(Domanda domanda, ConfigUtente configUtente) throws Exception;
	
	int updateDomandaToAssegnata(Domanda domanda, ConfigUtente configUtente) throws Exception;
	
	Domanda updateDomandaPreassegnata(Domanda domanda, ConfigUtente configUtente) throws Exception;
	
	void updateDomandaToChiusa(Domanda domanda, ConfigUtente configUtente) throws Exception;
	
	void updateDomandaToRifiutata(Domanda domanda, ConfigUtente configUtente) throws Exception;
	
	void annullaAssegnazione(Integer idDomanda, ConfigUtente configUtente);
	
	Domanda loadDomanda(Integer idDomanda) throws Exception;
	
	void inviaDomanda(Domanda domanda, ConfigUtente configUtente, HttpServletRequest httpRequest);
	
	void deleteDomanda(Integer idDomanda);
	
	void confermaAccettazione(Integer idDomanda, ConfigUtente configUtente);
	
	void confermaVariazione(Domanda domanda, ConfigUtente configUtente);
	
	void updateStatoDomanda(Integer idDomanda, StatoDomandaEnum statoDomanda, String note,ConfigUtente configUtente);
	
	PaginatedList<DomandaRiepilogo> getPaginatedSearchDomande(RicercaDomanda ricerca, int page, int limit, String sort);
	
	InfoDomanda getInfoDomanda(Integer idDomanda) throws RemoteException;
	
	int getNumDomandeBySoggetto(Integer idSoggetto, Integer idVivaio);
	
	int insertPagamento(Pagamento pagamento);
	
	int updatePagamento(Pagamento pagamento);
	
	RStatoDomanda getLastStatoByType(Integer idDomanda, StatoDomandaEnum statoDomanda);

}
