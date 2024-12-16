/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifobo.dto.Pagamento;
import it.csi.vifo.vifobo.util.CodicePagamentoEnum;

public class PagamentoMapper implements RowMapper<Pagamento>{

	@Override
	public Pagamento mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Pagamento item = new Pagamento();
		item.setIdPagamento(rs.getInt("id_pagamento"));
		item.setFkDomanda(rs.getInt("fk_domanda"));
		item.setFkTipoPagamento(rs.getInt("fk_tipo_pagamento"));
		item.setFlgFattura(rs.getByte("flg_fattura"));
		item.setIuv(rs.getString("iuv"));
		item.setEsito(rs.getString("esito"));
		String statoPagamento = rs.getString("stato_pagamento");
		item.setStatoPagamento(statoPagamento != null? CodicePagamentoEnum.getEnumByDescrizione(statoPagamento):null);
		item.setDataInserimento(rs.getDate("data_inserimento"));
		item.setDataAggiornamento(rs.getDate("data_aggiornamento"));
		item.setFkConfigUtente(rs.getInt("fk_config_utente"));
		
		return item;
	}

}
