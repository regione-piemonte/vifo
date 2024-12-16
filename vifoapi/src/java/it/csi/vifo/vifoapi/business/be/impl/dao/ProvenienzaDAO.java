/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import it.csi.vifo.vifoapi.dto.Provenienza;

public interface ProvenienzaDAO {

	int insert(Provenienza provenienza);
	int update(Provenienza provenienza);
}
