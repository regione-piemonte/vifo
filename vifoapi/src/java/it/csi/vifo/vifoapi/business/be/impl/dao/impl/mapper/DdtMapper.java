/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.Ddt;

public class DdtMapper implements RowMapper<Ddt> {

		@Override
		public Ddt mapRow(ResultSet rs, int arg1) throws SQLException {
			
			Ddt ddt = new Ddt();
			ddt.setIdDdt(rs.getInt("id_ddt"));
			ddt.setIdAssegnata(rs.getInt("id_assegnata"));
			ddt.setSpecie(rs.getString("specie"));
			String specieLatino = rs.getString("specie_latino");
			ddt.setSpecie(ddt.getSpecie() + (specieLatino == null?"": " - " + specieLatino));
			ddt.setVivaio(rs.getString("nomeVivaio"));
			ddt.setIdPartita(rs.getInt("id_partita"));
			ddt.setAllevamento(rs.getString("descr_tipo_allevamento"));
			ddt.setEta(rs.getString("eta"));
			ddt.setAltezza(rs.getString("altezza"));
			ddt.setCertificato(rs.getString("certificato_altro"));
			
			ddt.setCertificato(rs.getString("certificato_provenienza"));
//			Byte finiForestali = rs.getByte("flg_no_fini_forestali");
//			if(ddt.getCertificato() == null) {
//				ddt.setCertificato(rs.getString("certificato_piemonte"));
//				if(ddt.getCertificato() == null && finiForestali == 1) {
//					ddt.setCertificato("NON PER FINI FORESTALI");
//				}
//			}
			ddt.setQtaInDdt(rs.getInt("qta_ddt"));
			ddt.setQtaAssegnataRimanente(rs.getInt("qta_assegnata")-ddt.getQtaInDdt());
			ddt.setNumeroFatturaAcconto(rs.getString("nr_fattura_acconto"));
			ddt.setDataFatturaAcconto(rs.getDate("data_fattura_acconto"));
			
			return ddt;
			
		}
}
