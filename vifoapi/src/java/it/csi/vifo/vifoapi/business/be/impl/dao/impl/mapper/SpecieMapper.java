/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.Specie;

public class SpecieMapper implements RowMapper<Specie>{

	@Override
	public Specie mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Specie specie = new Specie();
		specie.setIdSpecie(rs.getInt("id_specie"));
		specie.setCodiceSpecie(rs.getString("codice_specie"));
		specie.setNomeComune(rs.getString("nome_comune"));
		specie.setNomeScientifico(rs.getString("nome_scientifico"));
		specie.setFlgProdotta(rs.getInt("flg_prodotta")==1);
		specie.setFlgSpecieZp(rs.getInt("flg_specie_zp")==1);
		specie.setFlgSpecie386(rs.getInt("flg_specie_386")==1);
		specie.setFlgTipoControllo(rs.getString("flg_tipo_controllo"));
		specie.setDensita(rs.getDouble("densita"));
		specie.setFamiglia(rs.getString("famiglia"));
		specie.setZonaOrigine(rs.getString("zona_origine"));
		specie.setDimensione(rs.getString("dimensione"));
		specie.setFoglia(rs.getString("foglia"));
		specie.setFioreFrutto(rs.getString("fiore_frutto"));
		specie.setEcologia(rs.getString("ecologia"));
		specie.setImpiego(rs.getString("impiego"));
		specie.setNoteCaratteristiche(rs.getString("note_caratteristiche"));
		specie.setFlgSchedaPubblicabile(rs.getInt("flg_scheda_pubblicabile")==1);
		specie.setFlgSpeciePubblicabile(rs.getInt("flg_specie_pubblicabile")==1);
		specie.setDataInserimento(rs.getDate("data_inserimento"));
		specie.setDataAggiornamento(rs.getDate("data_aggiornamento"));
		specie.setFkConfigUtente(rs.getInt("fk_config_utente"));
		specie.setFkGruppo(rs.getInt("fk_gruppo"));
		specie.setGruppoDescr(rs.getString("descr_gruppo"));
		specie.setAttitudine(rs.getString("attitudine"));
		specie.setTipiFoto(rs.getString("tipi_foto")); 
		
		return specie;
	}

}
