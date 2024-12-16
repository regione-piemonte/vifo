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
import it.csi.vifo.vifoapi.dto.PdfDomanda;
import it.csi.vifo.vifoapi.util.CalendarUtils;
import it.csi.vifo.vifoapi.util.service.integration.svista.Comune;
import it.csi.vifo.vifoapi.util.service.integration.svista.Provincia;

public class PdfDomandaMapper implements RowMapper<PdfDomanda> {

	@Override
	public PdfDomanda mapRow(ResultSet rs, int arg1) throws SQLException {
		
		String valueStr;

		PdfDomanda domanda = new PdfDomanda();
		domanda.setTipoDomanda("1".equals(rs.getString("fk_tipo_domanda"))?"GRATUITA":"ONEROSA");
		domanda.setDirezione(rs.getString("direzione"));
		domanda.setSettore(rs.getString("settore"));
		domanda.setIndirizzoSettore(rs.getString("indirizzo_settore"));
		domanda.setCapSettore(rs.getString("cap_settore"));
		domanda.setComuneSettore(rs.getString("comune_settore"));
		domanda.setTelefonoSettore(rs.getString("telefono_settore"));
		domanda.setDirigenteSettore(rs.getString("direttore_settore"));
		domanda.setNumDomanda(rs.getString("id_domanda") + " / " + rs.getInt("anno_domanda"));
		valueStr = rs.getString("n_marca_bollo");
		domanda.setNumMarcaBollo(valueStr != null ? valueStr: "");
		valueStr = rs.getString("note_domanda");
		domanda.setNote(valueStr != null && valueStr.trim().length() > 0? valueStr: "nessuna");
		domanda.setDataDomanda(CalendarUtils.convertDateToString(CalendarUtils.DATE_FORMAT_DDMMYYYY, rs.getDate("data_domanda")));
		domanda.setOperatore(rs.getString("operatore"));
		domanda.setBeneficiario(rs.getString("nome_pf_beneficiario"));
		if(domanda.getBeneficiario() == null || domanda.getBeneficiario().trim().length() == 0) {
			domanda.setBeneficiario(rs.getString("nome_pg_beneficiario"));
			valueStr = rs.getString("piva_beneficiario");
			domanda.setPivaBeneficiario(valueStr != null?valueStr:"-");
		}
		domanda.setCfBeneficiario(rs.getString("cf_beneficiario"));
		domanda.setIsEntePubblico(rs.getByte("flg_ente_pubblico") == 1?"SI":"NO");
		domanda.setPecBeneficiario(rs.getString("pec_beneficiario"));
		domanda.setIndirizzoBeneficiario(rs.getString("indirizzo_beneficiario"));
		domanda.setCapBeneficiario(rs.getString("cap_beneficiario"));
		Comune comune = WsServiceImpl.mapComuniPerIstat.get(rs.getString("comune_beneficiario"));
		if(comune != null) {
			domanda.setComuneBeneficiario(comune.getNome());
		}else {
			domanda.setComuneBeneficiario("-");
		}
		domanda.setTelefonoBeneficiario(rs.getString("telefono_beneficiario"));
		domanda.setMailBeneficiario(rs.getString("mail_beneficiario"));
		
		domanda.setNomeVivaio(rs.getString("nome_vivaio"));
		domanda.setIndirizzoVivaio(rs.getString("indirizzo_vivaio"));
		domanda.setCapVivaio(rs.getString("cap_vivaio"));
		Comune comuneVivaio = WsServiceImpl.mapComuniPiemontePerIstat.get(rs.getString("comune_vivaio"));
		if(comuneVivaio != null) {
			domanda.setComuneVivaio(comuneVivaio.getNome());
			Provincia prov = WsServiceImpl.mapProvincePiemontePerId.get(comuneVivaio.getIdProvincia());
			if(prov != null) {
			domanda.setSiglaProvVivaio(prov.getSigla());
			}else {
				domanda.setSiglaProvVivaio("");
			}
		}else {
			domanda.setComuneVivaio("");
			domanda.setSiglaProvVivaio("");
		}
		domanda.setRichiestoBollo("5".equals(rs.getString("fk_scopo")) 
				&& "1".equals(rs.getString("fk_tipo_domanda")) 
				&& rs.getByte("flg_ente_pubblico") != 1?"SI":"NO");
		domanda.setScopoAssegnazione(rs.getString("scopo"));
		domanda.setTitolarita(rs.getString("titolarita"));	
		domanda.setTelefonoVivaio(rs.getString("tel_vivaio"));
		
		return domanda;
	}

}
