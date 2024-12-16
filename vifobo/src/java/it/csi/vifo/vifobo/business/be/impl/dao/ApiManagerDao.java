/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao;

import it.csi.vifo.vifobo.business.be.exception.DAOException;
import it.csi.vifo.vifobo.dto.ApiManagerDto;



/**
 * Interfaccia pubblica del DAO ApiManager.
 * Espone le operazioni che possono essere eseguite per la gestione 
 * della tabella [Table[TRANSIENT]]
 * @generated
 */
public interface ApiManagerDao {

	/** 
	 * Implementazione del finder parametriValidi
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public ApiManagerDto findParametriByApiInternet(int id) throws DAOException;

}
