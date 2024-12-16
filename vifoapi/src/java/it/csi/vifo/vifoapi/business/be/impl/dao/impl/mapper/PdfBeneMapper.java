/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.PdfBene;
import it.csi.vifo.vifoapi.util.ConvertUtil;
import it.csi.vifo.vifoapi.util.PdfGeneratorUtil;

public class PdfBeneMapper implements RowMapper<PdfBene> {

	@Override
	public PdfBene mapRow(ResultSet rs, int arg1) throws SQLException {

		PdfBene bene = new PdfBene();
		bene.setNomeSpecie(rs.getString("nomeSpecie"));
		String specieLatino = rs.getString("specie_latino");
		bene.setNomeSpecie(bene.getNomeSpecie() + (specieLatino == null?"": " - " + specieLatino));
		
		bene.setQuantita(rs.getInt("quantita"));
		bene.setIdPartita(rs.getInt("id_partita"));
		String siglaVivaio = rs.getString("sigla_vivaio");
		if(siglaVivaio != null) {
			bene.setCodiceTracciabilita(PdfGeneratorUtil.getCodiceVivaioBySigle(siglaVivaio)
					+ "-" + bene.getIdPartita());
		}
		bene.setCertificatoProv(rs.getString("certificato_provenienza"));
		Byte finiForestali = rs.getByte("flg_no_fini_forestali");
		if(bene.getCertificatoProv() == null && finiForestali == 1) {
			bene.setCertificatoProv("NON PER FINI FORESTALI");
		}
		bene.setAllevamento(rs.getString("allevamento"));
		bene.setEta(rs.getString("eta"));
		bene.setProvenienza(rs.getString("provenienza"));
		Double prezzoUnitario = rs.getDouble("prezzo_unitario");
		if(prezzoUnitario != null) {
			bene.setPrezzoUnitario(ConvertUtil.convertToString(new BigDecimal(prezzoUnitario), 2));
			bene.setPrezzoTotale(ConvertUtil.convertToString(new BigDecimal(prezzoUnitario*bene.getQuantita()), 2));
		}
		return bene;
	}
}
