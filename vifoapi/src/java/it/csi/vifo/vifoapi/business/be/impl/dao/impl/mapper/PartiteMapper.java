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

public class PartiteMapper implements RowMapper<Assegnazione> {

	@Override
	public Assegnazione mapRow(ResultSet rs, int arg1) throws SQLException {
		Assegnazione assegnazione = new Assegnazione();
		assegnazione.setNomeSpecie(rs.getString("specie"));
		assegnazione.setFlgSpecie386(rs.getByte("flg_specie_386"));
		assegnazione.setFkVivaio(rs.getInt("fk_Vivaio"));
		assegnazione.setIdPartita(rs.getInt("id_inventario"));
		assegnazione.setNomeVivaio(rs.getString("nome_Vivaio"));
		assegnazione.setFlgNoFiniForestali(rs.getByte("flg_no_fini_forestali"));
		assegnazione.setCertificato(rs.getString("certificato_altro"));
		if(assegnazione.getCertificato() == null) {
			assegnazione.setCertificato(rs.getString("certificato_piemonte"));
			if(assegnazione.getCertificato() == null) { 
				if(assegnazione.getFlgNoFiniForestali() == 1) {
					assegnazione.setCertificato("NON PER FINI FORESTALI");
				}else {
					assegnazione.setCertificato("SPECIE NO 386");
				}
			}
		}
		assegnazione.setDescrTipoAllevamento(rs.getString("descr_tipo_allevamento"));
		assegnazione.setEta(rs.getString("eta"));
		assegnazione.setAltezza(rs.getString("altezza"));
		assegnazione.setPrezzoUnitario(rs.getDouble("prezzo_unitario"));
		assegnazione.setQtaDisponibile(rs.getInt("qta_disponibile"));
		assegnazione.setQtaAssegnata(0);
		return assegnazione;
	}

}
