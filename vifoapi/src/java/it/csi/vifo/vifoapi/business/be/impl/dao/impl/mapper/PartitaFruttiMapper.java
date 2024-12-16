/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.PartitaFrutti;

public class PartitaFruttiMapper extends BaseMapper implements RowMapper<PartitaFrutti> {

	@Override
	public PartitaFrutti mapRow(ResultSet rs, int arg1) throws SQLException {
		PartitaFrutti partitaFrutti = new PartitaFrutti();
		
		partitaFrutti.setIdInventarioFrutti(getInteger(rs,"id_inventario"));
		partitaFrutti.setIdProvenienza(getInteger(rs,"id_provenienza"));
		partitaFrutti.setCodiceCertificato(rs.getString("codice"));
		partitaFrutti.setFkSpecie(getInteger(rs,"fk_specie"));
		partitaFrutti.setDataCertificato(rs.getDate("data_certificato"));
		partitaFrutti.setProvenienza(rs.getString("provenienza"));
		partitaFrutti.setFkCategMateriale(getInteger(rs,"fk_categ_materiale"));
		partitaFrutti.setProduttore(rs.getString("produttore"));
		partitaFrutti.setDataCarico(rs.getDate("data_carico"));
		partitaFrutti.setQtaCaricoKg(rs.getDouble("qta_carico_kg"));
		partitaFrutti.setQtaDistruzioneKg(rs.getDouble("qta_distruzione_kg"));
		partitaFrutti.setQtaScaricoVenditaKg(rs.getDouble("qta_scarico_vendita_kg"));
		partitaFrutti.setCertificatoType(rs.getString("certificato_type"));
		partitaFrutti.setIsSemiFather(rs.getString("fk_inventario_frutti") != null);

		return partitaFrutti;
	}

}
