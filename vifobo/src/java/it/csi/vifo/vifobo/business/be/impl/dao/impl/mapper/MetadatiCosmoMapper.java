/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifobo.dto.MetadatiCosmo;

public class MetadatiCosmoMapper  implements RowMapper<MetadatiCosmo> {

	@Override
	public MetadatiCosmo mapRow(ResultSet rs, int arg1) throws SQLException {
		MetadatiCosmo metadato = new MetadatiCosmo();
		metadato.setIntestatario(rs.getString("intestatario")); 
		metadato.setTitolo(rs.getString("titolo")); 
		metadato.setAnno_serie_fascicoli(rs.getString("anno"));
		metadato.setParola_chiave_fascicolo(rs.getString("parola_chiave_fascicolo"));
		metadato.setId_domanda(rs.getString("id_domanda"));
		metadato.setDenominazione_richiedente(rs.getString("denominazione_richiedente"));
		metadato.setCognome(rs.getString("cognome"));
		metadato.setNome(rs.getString("nome"));
		metadato.setPG_partita_iva_richiedente(rs.getString("PG_partita_iva_richiedente"));
		metadato.setPG_codice_fiscale_richiedente(rs.getString("PG_codice_fiscale_richiedente"));
		metadato.setPG_indirizzo_pec_richiedente(rs.getString("PG_indirizzo_pec_richiedente"));
		metadato.setPF_codice_fiscale_richiedente(rs.getString("PF_codice_fiscale_richiedente"));
		metadato.setPF_indirizzo_mail_richiedente(rs.getString("PF_indirizzo_mail_richiedente"));
		metadato.setCodice_IPA(rs.getString("codice_IPA"));
		return metadato;
	}

}
