/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.Assegnazione;

public class AssegnazioneRiepilogoMapper implements RowMapper<Assegnazione> {

	@Override
	public Assegnazione mapRow(ResultSet rs, int arg1) throws SQLException {
		Assegnazione assegnazione = new Assegnazione();
		assegnazione.setNomeSpecie(rs.getString("nomeSpecie"));
		assegnazione.setFlgSpecie386(rs.getByte("flg_specie_386"));
		assegnazione.setQtaRichiesta(rs.getInt("qta_richiesta"));
		assegnazione.setFkVivaio(rs.getInt("fk_Vivaio"));
		assegnazione.setNomeVivaio(rs.getString("nome_Vivaio"));
		assegnazione.setFlgNoFiniForestali(rs.getByte("flg_no_fini_forestali"));
		assegnazione.setCertificato(rs.getString("certificato_altro"));
		if(assegnazione.getCertificato() == null) {
			assegnazione.setCertificato(rs.getString("certificato_piemonte"));
			if(assegnazione.getCertificato() == null && assegnazione.getFlgNoFiniForestali() == 1) {
				assegnazione.setCertificato("NON PER FINI FORESTALI");
			}
		}
		assegnazione.setDescrTipoAllevamento(rs.getString("descr_tipo_allevamento"));
		assegnazione.setQtaAssegnata(rs.getInt("qta_assegnata"));
		assegnazione.setQtaRitirata(rs.getInt("qta_ritirata"));
		assegnazione.setTipoDomanda(rs.getInt("fk_tipo_domanda"));
		assegnazione.setPrezzoUnitario(rs.getDouble("importo"));
		return assegnazione;
	}
}
