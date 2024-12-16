/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.Inventario;

public class InventarioMapper implements RowMapper<Inventario> {

	@Override
	public Inventario mapRow(ResultSet rs, int arg1) throws SQLException {
		Inventario inventario = new Inventario();
		inventario.setIdInventario(rs.getInt("id_inventario"));
		inventario.setFkProventienza(rs.getInt("fk_provenienza"));
		inventario.setDataCarico(rs.getDate("data_carico"));
		inventario.setDataInserimento(rs.getDate("data_inserimento"));
		inventario.setDataAggiornamento(rs.getDate("data_aggiornamento"));
		inventario.setFkConfigUtente(rs.getInt("fk_config_utente"));		
		return inventario;
	}

}
