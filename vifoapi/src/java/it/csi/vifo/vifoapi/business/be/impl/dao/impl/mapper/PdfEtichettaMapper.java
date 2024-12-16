/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.business.be.service.impl.WsServiceImpl;
import it.csi.vifo.vifoapi.dto.PdfEtichetta;
import it.csi.vifo.vifoapi.util.PdfGeneratorUtil;
import it.csi.vifo.vifoapi.util.service.integration.svista.Comune;

public class PdfEtichettaMapper implements RowMapper<PdfEtichetta> {

	@Override
	public PdfEtichetta mapRow(ResultSet rs, int arg1) throws SQLException {

		PdfEtichetta etichetta = new PdfEtichetta();
		etichetta.setDirezione(rs.getString("direzione"));
		etichetta.setSettore(rs.getString("settore"));
		etichetta.setNomeVivaio(rs.getString("nome_vivaio"));
		etichetta.setComuneVivaio(rs.getString("comune_viviaio"));
		etichetta.setCapVivaio(rs.getString("cap_vivaio"));
		Comune comune = WsServiceImpl.mapComuniPerIstat.get(rs.getString("comune_viviaio"));
		etichetta.setIndirizzoVivaio(rs.getString("indirizzo_vivaio") + ", " 
				+ etichetta.getCapVivaio() + ", " + (comune == null?"-":comune.getNome()));
		etichetta.setIdPartita(rs.getInt("id_partita"));
		etichetta.setCertificatoProvenienza(rs.getString("certificato_altro"));
		if(etichetta.getCertificatoProvenienza() == null) {
			etichetta.setCertificatoProvenienza(rs.getString("certificato_piemonte"));
			Byte finiForestali = rs.getByte("flg_no_fini_forestali");
			if(etichetta.getCertificatoProvenienza() == null && finiForestali == 1) {
				etichetta.setCertificatoProvenienza("NON PER FINI FORESTALI");
			}
		}		
		etichetta.setAllevamento(rs.getString("allevamento"));
		String val = rs.getString("eta");
		etichetta.setEta(val == null?"-":val);
		val = rs.getString("altezza");
		etichetta.setAltezza(val == null?"-":val);
		etichetta.setNomeSpecie(rs.getString("nomeSpecie"));
		etichetta.setQuantita(rs.getInt("qta_assegnata"));
		etichetta.setProvenienza(rs.getString("provenienza"));
		val = rs.getString("tipo_materiale");
		etichetta.setMaterialeBase(val == null?"-":val);
		etichetta.setFlag386(rs.getInt("flg_specie_386") == 1);
		etichetta.setFlagZP(rs.getInt("flg_specie_zp") == 1);

		etichetta.setSiglaVivaio(rs.getString("sigla_vivaio"));
		etichetta.setCodiceAutorizzativo(rs.getString("codice_autorizzativo"));

		StringBuilder codiceTracciabilita = new StringBuilder(
				PdfGeneratorUtil.getCodiceVivaioBySigle(etichetta.getSiglaVivaio()));
		codiceTracciabilita.append("-");
		codiceTracciabilita.append(etichetta.getIdPartita() != null ? etichetta.getIdPartita(): "");
		etichetta.setCodiceTracciabilita(codiceTracciabilita.toString());
		return etichetta;
	}

}
