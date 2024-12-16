/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.InventarioPiantineRiepilogo;

public class InventarioPiantineRiepilogoMapper extends BaseMapper implements RowMapper<InventarioPiantineRiepilogo> {
	
	SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public InventarioPiantineRiepilogo mapRow(ResultSet rs, int arg1) throws SQLException {
		InventarioPiantineRiepilogo item = new InventarioPiantineRiepilogo();
		
		item.setIdInventarioPiante(getInteger(rs,"id_inventario_piante"));
		item.setFkInventarioPiante(getInteger(rs,"fk_inventario_piante"));
		item.setFkInventarioSemi(getInteger(rs,"fk_inventario_semi"));
		item.setCertificatoProvenienza(rs.getString("certificato_provenienza"));
		item.setDataCertificato(rs.getString("data_certificato"));
		item.setFlgNoFiniForestali(rs.getByte("flg_no_fini_forestali"));
		item.setFlgSpecie386(rs.getByte("flg_specie_386"));
		item.setSpecie(rs.getString("nome_comune"));
		String specieLatino = rs.getString("nome_scientifico");
		item.setSpecie(item.getSpecie() + (specieLatino == null?"": " - " + specieLatino));
		
		
		item.setProvenienza(rs.getString("provenienza"));
		item.setProduttore(rs.getString("produttore"));
		item.setCodCategMateriale(rs.getString("cod_categ_materiale"));
		item.setDescrCategMateriale(rs.getString("descr_categ_materiale"));
		item.setDescrTipoAllevamento(rs.getString("descr_tipo_allevamento"));
		item.setEta(rs.getString("eta"));
		item.setAltezza(rs.getString("altezza"));
		item.setAnnoRadicazioneSeme(getInteger(rs,"anno_radicazione_seme"));
		item.setPrezzoUnitario(rs.getDouble("prezzo_unitario"));
		item.setQtaScaricoSemiKg(rs.getDouble("qta_scarico_semi_kg"));
		item.setDataCarico(rs.getDate("data_carico"));
		item.setQtaCarico(getInteger(rs,"qta_carico"));
		item.setQtaNonAssegnabile(getInteger(rs,"qta_non_assegnabile"));
		item.setQtaScaricoAltrePiante(getInteger(rs,"qta_scarico_altre_piante"));
		item.setQtaAssegnabileTeorica(getInteger(rs,"qta_assegnabile_teorica"));
		//item.setQtaAssegnata(getInteger(rs,"qta_assegnata")); //chenged by richiesta Roberto Giolitto
		item.setQtaAssegnata(getInteger(rs,"qta_bloccata"));		
		item.setQtaRitirata(getInteger(rs,"qta_ritirata"));
		item.setQtaDistruzione(getInteger(rs,"qta_distruzione"));
		item.setQtaDisponibile(getInteger(rs,"qta_disponibile"));
		item.setPosizione(rs.getString("posizione_partita"));
		
		return item;
	}

}
