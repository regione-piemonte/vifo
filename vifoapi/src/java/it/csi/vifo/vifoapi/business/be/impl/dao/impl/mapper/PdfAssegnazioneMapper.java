/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.business.be.service.impl.WsServiceImpl;
import it.csi.vifo.vifoapi.dto.PdfAssegnazione;
import it.csi.vifo.vifoapi.util.CalendarUtils;
import it.csi.vifo.vifoapi.util.ConvertUtil;
import it.csi.vifo.vifoapi.util.service.integration.svista.Comune;
import it.csi.vifo.vifoapi.util.service.integration.svista.Provincia;

public class PdfAssegnazioneMapper implements RowMapper<PdfAssegnazione> {

	@Override
	public PdfAssegnazione mapRow(ResultSet rs, int arg1) throws SQLException {
		
		PdfAssegnazione assegnazione = new PdfAssegnazione();
		int tipoDomanda = rs.getInt("fk_tipo_domanda");
		
		assegnazione.setDirezione(notNullString(rs.getString("direzione")));
		assegnazione.setSettore(notNullString(rs.getString("settore")));		
		assegnazione.setIndirizzoSettore(notNullString(rs.getString("indirizzo_settore")));
		assegnazione.setCapSettore(notNullString(rs.getString("cap_settore")));
		assegnazione.setComuneSettore(notNullString(rs.getString("comune_settore")));
		assegnazione.setTelefonoSettore(notNullString(rs.getString("telefono_settore")));		
		assegnazione.setDirigenteSettore(notNullString(rs.getString("direttore_settore")));
		assegnazione.setNumeroAssegnazione(notNullString(rs.getString("id_domanda")) + " / " + rs.getInt("anno_domanda"));
		Double totImporti = rs.getDouble("totale_importi_euro");
		assegnazione.setTotaleImporti(setDecimals(totImporti,2));
		Double totImponibile = rs.getDouble("totale_imponibile_euro");
		assegnazione.setTotaleImponibile(setDecimals(totImponibile,2));
		assegnazione.setTotalFattura(setDecimals(rs.getDouble("totale_fattura"),2));
		Double sconto = rs.getDouble("sconto_applicato");
		if(sconto != null) {
			assegnazione.setScontoApplicato(setDecimals(sconto, 1));
			assegnazione.setTotaleSconto(setDecimals(totImporti - totImponibile, 2));
		}
		assegnazione.setAliquotaIva(setDecimals(rs.getDouble("aliquota_iva"), 0));
		assegnazione.setTotaleIva(setDecimals(rs.getDouble("importo_iva"),2));
		assegnazione.setTotalFattura(setDecimals(rs.getDouble("totale_fattura"), 2));	
		
		assegnazione.setScopoAssegnazioneGratuita(notNullString(rs.getString("descr_scopo")));
		assegnazione.setNumeroProtocollo(notNullString(rs.getString("numero_protocollo")) == null?"":notNullString(rs.getString("numero_protocollo")));
		String data = CalendarUtils.convertDateToString(CalendarUtils.DATE_FORMAT_DDMMYYYY, rs.getDate("data_protocollo"));
		assegnazione.setDataProtocollo(data == null?"":data);
		Date dataAssegnazione = rs.getDate("data_inserimento");
		assegnazione.setDataAssegnazione(CalendarUtils.convertDateToString(CalendarUtils.DATE_FORMAT_DDMMYYYY, dataAssegnazione));
		assegnazione.setBeneficiario(rs.getString("ragione_sociale"));
		if(assegnazione.getBeneficiario() ==  null) {
			assegnazione.setBeneficiario(notNullString(rs.getString("cognome")) + " " + notNullString(rs.getString("nome")));
		}else {
			assegnazione.setPecBeneficiario(notNullString(rs.getString("pec")));
		}
		assegnazione.setIndirizzoBeneficiario(notNullString(rs.getString("indirizzo")));
		assegnazione.setCapBeneficiario(notNullString(rs.getString("cap")));

		Comune comune = WsServiceImpl.mapComuniPerIstat.get(rs.getString("istat_comune"));
		assegnazione.setComuneBeneficiario(notNullString(comune == null?null:comune.getNome()));
		assegnazione.setTelefonoBeneficiario(notNullString(rs.getString("telefono")));
		assegnazione.setMailBeneficiario(notNullString(rs.getString("mail")));
		
		assegnazione.setVivaio(notNullString(rs.getString("nome_vivaio")));
		comune = WsServiceImpl.mapComuniPerIstat.get(rs.getString("comune_vivaio"));
		assegnazione.setComuneVivaio(notNullString(comune == null?null:comune.getNome()));
		Provincia provincia = WsServiceImpl.mapProvincePiemontePerId.get(comune == null? null:comune.getIdProvincia());
		assegnazione.setSiglaProvVivaio(notNullString(provincia == null?null:provincia.getSigla()));
		assegnazione.setCapVivaio(notNullString(rs.getString("cap_vivaio")));
		assegnazione.setIndirizzoVivaio(notNullString(rs.getString("indirizzo_vivaio")));
		assegnazione.setTelefonoVivaio(notNullString(rs.getString("telefono_vivaio")));
		assegnazione.setMailVivaio(notNullString(rs.getString("mail_vivaio")));
		assegnazione.setPecVivaio(notNullString(rs.getString("pec_vivaio")));
		if(tipoDomanda == 1) {
			assegnazione.setOrariAperturaVivaio(rs.getString("periodo_apertura") 
					+ " (orari: " +rs.getString("orari_vivaio") + ")");
		}else {
			assegnazione.setOrariAperturaVivaio("orari: " +rs.getString("orari_vivaio"));
			Calendar c = Calendar.getInstance(); 
			c.setTime(dataAssegnazione); 
			c.add(Calendar.DATE, 30);
			assegnazione.setDataScadenzaRitiro(CalendarUtils.convertDateToString(CalendarUtils.DATE_FORMAT_DDMMYYYY, c.getTime()));
		}
		assegnazione.setOperatoreAssegnazione(notNullString(rs.getString("cognome_operatore")) + " " + notNullString(rs.getString("nome_operatore")));
		
		return assegnazione;
	}
	
	private String setDecimals(Double value, int dimDecimal) {
		if(value != null) {
			return ConvertUtil.convertToString(new BigDecimal(value), dimDecimal);
		}else {
			return "";
		}
	}
	
	private String notNullString(String str) {
		return str == null?"":str;
	}
}
