/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.business.be.exception.RecordNotFoundException;
import it.csi.vifo.vifoapi.business.be.exception.RecordNotUniqueException;
import it.csi.vifo.vifoapi.dto.SoggettoResultSearch;
import it.csi.vifo.vifoapi.dto.SoggettoSearch;
import it.csi.vifo.vifoapi.dto.TSoggetto;
import it.csi.vifo.vifoapi.util.PaginatedList;

public interface SoggettoDAO {

	TSoggetto findSoggettoById(Integer idSoggetto) throws RecordNotFoundException;

	TSoggetto findSoggettoByCodiceFiscale(String codiceFiscale) throws RecordNotUniqueException;
	
	TSoggetto findAziendaByCodiceFiscale(String codiceFiscale, Boolean isEntePubblico) throws RecordNotUniqueException;

	List<TSoggetto> findByPartialCodiceFiscale(String codiceFiscale);

	int createSoggetto(TSoggetto soggetto);

	int createSoggettoFisica(TSoggetto soggetto);

	int updateSoggetto(TSoggetto soggetto);

	void updateFkConfigUtente(int idSoggetto, int configUtenteId);
	
	List<TSoggetto> getPersonaGiurdicaByOwnerCodiceFiscale(String codiceFiscale, boolean isEntePubblico);
	
	TSoggetto getSoggettoByIdConfigUtente(Integer idConfigUtente) throws RecordNotFoundException;
	
	PaginatedList<SoggettoResultSearch> getPaginatedListSoggetti(int page, int limit, String sort,SoggettoSearch filter);

}
