/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.PartitaPiante;

public class PartitaPiantineMapper extends BaseMapper implements RowMapper<PartitaPiante> {

	@Override
	public PartitaPiante mapRow(ResultSet rs, int arg1) throws SQLException {
		PartitaPiante partitaPiante = new PartitaPiante();
		
		partitaPiante.setIdInventarioPiante(getInteger(rs,"id_inventario"));
		partitaPiante.setFkInventarioPiante(getInteger(rs,"fk_inventario_piante"));
		partitaPiante.setFkInventarioSemi(getInteger(rs,"fk_inventario_semi"));
		partitaPiante.setIdProvenienza(getInteger(rs,"id_provenienza"));
		partitaPiante.setCodiceCertificato(rs.getString("codice"));
		partitaPiante.setFkSpecie(getInteger(rs,"fk_specie"));
		partitaPiante.setDataCertificato(rs.getDate("data_certificato"));
		partitaPiante.setProvenienza(rs.getString("provenienza"));
		partitaPiante.setFkCategMateriale(getInteger(rs,"fk_categ_materiale"));
		partitaPiante.setProduttore(rs.getString("produttore"));
		partitaPiante.setDataCarico(rs.getDate("data_carico"));
		partitaPiante.setEta(rs.getString("eta"));
		partitaPiante.setAltezza(rs.getString("altezza"));
		partitaPiante.setAnnoRadicazioneSeme(getInteger(rs,"anno_radicazione_seme"));
		partitaPiante.setFkVivaio(getInteger(rs,"fk_vivaio"));
		partitaPiante.setFkTipoAllevamento(getInteger(rs,"fk_tipo_allevamento"));
		partitaPiante.setPosizionePartita(rs.getString("posizione_partita"));
		partitaPiante.setPrezzoUnitario(rs.getDouble("prezzo_unitario"));
		partitaPiante.setQtaCarico(getInteger(rs,"qta_carico"));
		partitaPiante.setQtaDistruzione(getInteger(rs,"qta_distruzione"));
		partitaPiante.setQtaNonAssegnabile(getInteger(rs,"qta_non_assegnabile"));
		partitaPiante.setQtaScaricoSemiKg(rs.getDouble("qta_scarico_semi_kg"));
		partitaPiante.setCertificatoType(rs.getString("certificato_type"));
		partitaPiante.setIsPianteFather(rs.getString("child") != null);
		partitaPiante.setNumAssegnazioni(rs.getInt("num_assegnazioni"));
		partitaPiante.setFlgTipoTrasferimento(rs.getString("flg_tipo_trasferimento"));
		
		return partitaPiante;
	}

}
