/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.Domanda;

public class DomandaMapper implements RowMapper<Domanda> {

	@Override
	public Domanda mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Domanda domanda = new Domanda();
		
		domanda.setIdDomanda(rs.getInt("id_domanda"));
		domanda.setFkSoggetto(rs.getInt("fk_soggetto"));
		domanda.setFkTipoDomanda(rs.getInt("fk_tipo_domanda"));
		domanda.setFkTitolarita(rs.getInt("fk_titolarita"));
		domanda.setFkScopo(rs.getInt("fk_scopo"));
		domanda.setFkVivaio(rs.getInt("fk_vivaio"));
		domanda.setFkConfigUtente(rs.getInt("fk_config_utente"));
		domanda.setNote(rs.getString("note"));
		domanda.setNumMarcaBollo(rs.getString("n_marca_bollo"));
		domanda.setTotImportiEuro(rs.getDouble("totale_importi_euro"));
		domanda.setScontoApplicato(rs.getDouble("sconto_applicato"));
		domanda.setTotImponibileEuro(rs.getDouble("totale_imponibile_euro"));
		domanda.setAliquotaIva(rs.getDouble("aliquota_iva"));
		domanda.setImportoIva(rs.getDouble("importo_iva"));
		domanda.setTotFattura(rs.getDouble("totale_fattura"));
		domanda.setDataInserimento(rs.getDate("data_inserimento"));
		domanda.setDataAggiornamento(rs.getDate("data_aggiornamento"));
		domanda.setFkStatoDomanda(rs.getInt("fkStatoDomanda"));
		domanda.setStatoDomanda(rs.getString("descr_stato_domanda"));
		
		return domanda;
	}
}
