/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.PartitaSemi;

public class PartitaSemiMapper extends BaseMapper implements RowMapper<PartitaSemi> {

	@Override
	public PartitaSemi mapRow(ResultSet rs, int arg1) throws SQLException {
		PartitaSemi partitaSemi = new PartitaSemi();
		
		partitaSemi.setIdInventarioSemi(getInteger(rs,"id_inventario"));
		partitaSemi.setFkInventarioFrutti(getInteger(rs,"fk_inventario_frutti"));
		partitaSemi.setIdProvenienza(getInteger(rs,"id_provenienza"));
		partitaSemi.setCodiceCertificato(rs.getString("codice"));
		partitaSemi.setFkSpecie(getInteger(rs,"fk_specie"));
		partitaSemi.setDataCertificato(rs.getDate("data_certificato"));
		partitaSemi.setProvenienza(rs.getString("provenienza"));
		partitaSemi.setFkCategMateriale(getInteger(rs,"fk_categ_materiale"));
		partitaSemi.setProduttore(rs.getString("produttore"));
		partitaSemi.setDataCarico(rs.getDate("data_carico"));
		partitaSemi.setQtaCaricoKg(rs.getDouble("qta_carico_kg"));
		partitaSemi.setQtaDistruzioneKg(rs.getDouble("qta_distruzione_kg"));
		partitaSemi.setQtaScaricoFruttiKg(rs.getDouble("qta_scarico_frutti_kg"));
		partitaSemi.setQtaScaricoVenditaKg(rs.getDouble("qta_scarico_vendita_kg"));
		partitaSemi.setCertificatoType(rs.getString("certificato_type"));
		partitaSemi.setIsPianteFather(rs.getString("fk_inventario_semi") != null);
	
		return partitaSemi;
	}

}
