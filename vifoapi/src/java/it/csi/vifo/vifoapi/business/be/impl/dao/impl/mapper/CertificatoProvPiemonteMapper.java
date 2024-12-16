/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.CertificatoProvPiemonte;

public class CertificatoProvPiemonteMapper implements RowMapper<CertificatoProvPiemonte> {

	@Override
	public CertificatoProvPiemonte mapRow(ResultSet rs, int arg1) throws SQLException {
		CertificatoProvPiemonte certificato = new CertificatoProvPiemonte();
		certificato.setIdProvenienza(rs.getInt("id_provenienza"));
		certificato.setNumeroCertificato(rs.getString("numero_certificato"));
		certificato.setFlgTipoProduzione(rs.getByte("flg_tipo_produzione"));
		certificato.setFkNaturaMateriale(rs.getInt("fk_natura_materiale"));
		certificato.setFkSpecie(rs.getInt("fk_specie"));
		certificato.setFkCategMateriale(rs.getInt("fk_categ_materiale"));
		certificato.setFkTipoMateriale(rs.getInt("fk_tipo_materiale"));
		certificato.setFkPopolamento(rs.getInt("fk_popolamento"));
		certificato.setMiscuglio(rs.getString("miscuglio"));
		certificato.setFkOrigine(rs.getInt("fk_origine"));
		certificato.setDestinazione(rs.getInt("flg_no_fini_forestali"));
		certificato.setProvenienza(rs.getString("provenienza"));
		certificato.setProduttore(rs.getString("produttore"));
		certificato.setAltitudine(rs.getString("altitudine"));
		certificato.setAnnoMaturazioneSemi(rs.getInt("anno_maturazione_semi"));
		certificato.setQtaMaterialeBase(rs.getDouble("qta_materiale_base"));
		certificato.setFkUdmMateriale(rs.getInt("fk_udm_materiale"));
		certificato.setFlgPartitaPrecedente(rs.getString("flg_partita_precedente"));
		certificato.setnCertificatoPrecedente(rs.getString("n_certificato_precedente"));
		certificato.setQtaPartitaIniziale(rs.getDouble("qta_partita_iniziale"));
		certificato.setDurataAllevamento(rs.getString("durata_allevamento"));
		certificato.setFlgPropagazione(rs.getString("flg_propagazione"));
		certificato.setMetodoPropagazione(rs.getString("metodo_propagazione"));
		certificato.setnCicliPropagazione(rs.getString("n_cicli_propagazione"));
		certificato.setDataCertificato(rs.getDate("data_certificato"));
		certificato.setNote(rs.getString("note"));
		certificato.setOrigineNonAutoctoni(rs.getString("origine_non_autoctoni"));
		
		return certificato;
	}

}
