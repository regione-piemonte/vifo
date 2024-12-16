/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.InventarioSemiRiepilogo;

public class InventarioSemiRiepilogoMapper extends BaseMapper  implements RowMapper<InventarioSemiRiepilogo> {
	
	SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public InventarioSemiRiepilogo mapRow(ResultSet rs, int arg1) throws SQLException {
		InventarioSemiRiepilogo item = new InventarioSemiRiepilogo();
		
		item.setIdInventario(getInteger(rs,"id_inventario_semi"));
		item.setFkInventarioFrutti(getInteger(rs,"fk_inventario_frutti"));
		item.setCertificatoProvenienza(rs.getString("certificato_provenienza"));
		item.setDataCertificato(rs.getString("data_certificato"));
//		item.set(rs.get("flg_no_fini_forestali"));
//		item.set(rs.get("flg_specie_386"));
		item.setSpecie(rs.getString("nome_comune"));
		String specieLatino = rs.getString("nome_scientifico");
		item.setSpecie(item.getSpecie() + (specieLatino == null?"": " - " + specieLatino));
		
		item.setProvenienza(rs.getString("provenienza"));
		item.setProduttore(rs.getString("produttore"));
		item.setCodCategoriaMateriale(rs.getString("cod_categ_materiale"));
		item.setDescrCategoriaMaterialie(rs.getString("descr_categ_materiale"));
		item.setQtaScaricoFruttiKg(rs.getDouble("qta_scarico_frutti_kg"));
		Date data = rs.getDate("data_carico");
		if(data != null) {
			item.setDataCarico(sf.format(data));	
		}
		item.setQtaCaricoKg(rs.getDouble("qta_carico_kg"));
		item.setQtaScaricoPiantineKg(rs.getDouble("qta_scarico_piante_kg"));
		item.setQtaScaricoVenditaKg(rs.getDouble("qta_scarico_vendita_kg"));
		item.setQtaDistruzioneKg(rs.getDouble("qta_distruzione_kg"));
		item.setQtaResiduaKg(rs.getDouble("qta_residua"));
		return item;
	}
	

}
