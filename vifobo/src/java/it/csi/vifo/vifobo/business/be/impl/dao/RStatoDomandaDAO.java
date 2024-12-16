/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao;

import it.csi.vifo.vifobo.dto.RStatoDomanda;
import it.csi.vifo.vifobo.util.StatoDomandaEnum;

public interface RStatoDomandaDAO {

	int insert(RStatoDomanda statoDomanda);
	int update(RStatoDomanda statoDomanda);
	int deleteByIdDomanda(Integer idDomanda);
	RStatoDomanda getLastStato(Integer idDomanda);
	RStatoDomanda getLastStatoByType(Integer idDomanda, StatoDomandaEnum statoDomanda);
	int updateProtocolData(RStatoDomanda statoDomanda);
}
