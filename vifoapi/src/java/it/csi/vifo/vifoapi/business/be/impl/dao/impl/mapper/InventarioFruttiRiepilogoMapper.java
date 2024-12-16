/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.InventarioFruttiRiepilogo;

public class InventarioFruttiRiepilogoMapper extends BaseMapper implements RowMapper<InventarioFruttiRiepilogo> {

	@Override
	public InventarioFruttiRiepilogo mapRow(ResultSet rs, int arg1) throws SQLException {
		InventarioFruttiRiepilogo item = new InventarioFruttiRiepilogo();
		
		item.setIdInventarioFrutti(getInteger(rs,"id_inventario_frutti"));
		item.setCertificatoProvenienza(rs.getString("certificato_provenienza"));
		item.setDataCertificato(rs.getString("data_certificato"));
		item.setFlgNoFiniForestali(rs.getBoolean("flg_no_fini_forestali"));
		item.setFlgSpecie386(rs.getBoolean("flg_specie_386"));
		if(item.getCertificatoProvenienza() == null && item.isFlgNoFiniForestali()) {
			item.setCertificatoProvenienza("NON PER FINI FORESTALI");
		}
		item.setSpecie(rs.getString("nome_comune"));
		String specieLatino = rs.getString("specie_latino");
		item.setSpecie(item.getSpecie() + (specieLatino == null?"": " - " + specieLatino));
		
		item.setProvenienza(rs.getString("provenienza"));
		item.setProduttore(rs.getString("produttore"));
		item.setCodCategoriaMateriale(rs.getString("cod_categ_materiale"));
		item.setDescrCategoriaMaterialie(rs.getString("descr_categ_materiale"));
		item.setDataCarico(rs.getString("data_carico"));
		item.setQtaCaricoKg(rs.getDouble("qta_carico_kg"));
		item.setQtaScaricoSemiKg(rs.getDouble("qta_scarico_semi_kg"));
		item.setQtaScaricoVenditaKg(rs.getDouble("qta_scarico_vendita_kg"));
		item.setQtaDistruzioneKg(rs.getDouble("qta_distruzione_kg"));
		item.setQtaResidua(rs.getDouble("qta_residua"));
		return item;
	}
}
