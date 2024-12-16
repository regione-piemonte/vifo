/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.DomandaRiepilogo;
import it.csi.vifo.vifoapi.util.DateUtil;

public class DomandaRiepilogoMapper implements RowMapper<DomandaRiepilogo> {

	@Override
	public DomandaRiepilogo mapRow(ResultSet rs, int arg1) throws SQLException {

		DomandaRiepilogo domanda = new DomandaRiepilogo();
		domanda.setIdDomanda(rs.getInt("id_domanda"));
		domanda.setTipoDomanda(rs.getInt("fk_tipo_domanda"));
		domanda.setRichiedente(rs.getString("richiedente"));
		domanda.setVivaio(rs.getString("Vivaio"));
		Date data = rs.getDate("data_invio");
		domanda.setDataInvio(data != null?DateUtil.dateFormatter.format(data):"");
		domanda.setStato(rs.getString("descr_stato_domanda"));
		domanda.setVivaioCompilatore(rs.getInt("vivaio_compilatore") > 0);
		domanda.setStatoPagamento(rs.getString("stato_pagamento"));
		domanda.setEntePubblico(rs.getInt("flg_ente_pubblico") == 1);
		return domanda;
	}
}
