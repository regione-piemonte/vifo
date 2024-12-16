/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.CertificatoRiepilogo;
import it.csi.vifo.vifoapi.util.DateUtil;

public class CertificatoRiepilogoMapper  implements RowMapper<CertificatoRiepilogo> {

	@Override
	public CertificatoRiepilogo mapRow(ResultSet rs, int arg1) throws SQLException {
		CertificatoRiepilogo certificato = new CertificatoRiepilogo();
		certificato.setIdProvenienza(rs.getInt("id_provenienza"));
		certificato.setFkUdmMateriale(rs.getInt("fk_udm_materiale"));
		certificato.setCodiceCertificatoProvenienza(rs.getString("codice_certificato_provenienza"));
		certificato.setNumeroCertificato(rs.getString("numero_certificato"));
		Date data = rs.getDate("data_certificato");
		certificato.setDataCertificato(data != null?DateUtil.dateFormatter.format(data):"");
		certificato.setNomeSpecie(rs.getString("nome_specie"));
		String specieLatino = rs.getString("specie_latino");
		certificato.setNomeSpecie(certificato.getNomeSpecie() + (specieLatino == null?"" : " - " + specieLatino));
		certificato.setDescrCategMateriale(rs.getString("descr_categ_materiale"));
		certificato.setPopolamentoSeme(rs.getString("popolamento_seme"));
		
		return certificato;
	}

}
