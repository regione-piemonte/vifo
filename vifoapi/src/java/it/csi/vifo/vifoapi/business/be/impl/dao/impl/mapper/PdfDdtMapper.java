/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.PdfDdt;
import it.csi.vifo.vifoapi.util.CalendarUtils;

public class PdfDdtMapper implements RowMapper<PdfDdt> {

	@Override
	public PdfDdt mapRow(ResultSet rs, int arg1) throws SQLException {

		PdfDdt ddt = new PdfDdt();
		ddt.setDirezione(rs.getString("direzione"));
		ddt.setSettore(rs.getString("settore"));
		String beneficiario = rs.getString("ragione_sociale");
		boolean isSoggettoFisico = false;
		if(beneficiario == null || beneficiario.trim().length() == 0) {
			beneficiario = rs.getString("cognome") + " " + rs.getString("nome");
			isSoggettoFisico = true;
		}
		ddt.setBeneficiario(beneficiario);
		ddt.setIndirizzoBeneficiario(rs.getString("indirizzo"));
		ddt.setCapBeneficiario(rs.getString("cap"));
		ddt.setComuneBeneficiario(rs.getString("istat_comune"));
		ddt.setCfBeneficiario(rs.getString("codice_fiscale"));
		if(ddt.getCfBeneficiario() == null || ddt.getCfBeneficiario().strip().length() == 0) {
			ddt.setCfBeneficiario("-");
		}
		ddt.setPivaBeneficiario(rs.getString("partita_iva"));
		if(ddt.getPivaBeneficiario() == null || ddt.getPivaBeneficiario().strip().length() == 0) {
			ddt.setPivaBeneficiario("-");
		}
		ddt.setNumeroDdt(rs.getString("numero_ddt"));	
		ddt.setDataDdt(CalendarUtils.convertDateToString(CalendarUtils.DATE_FORMAT_DDMMYYYY, rs.getDate("data_inserimento")));
		ddt.setIdDomanda(rs.getInt("fk_domanda"));
		ddt.setNumeroFatturaAcconto(rs.getString("nr_fattura_acconto"));
		ddt.setDataFatturaAcconto(rs.getDate("data_fattura_acconto") != null ? CalendarUtils.convertDateToString(CalendarUtils.DATE_FORMAT_DDMMYYYY, rs.getDate("data_fattura_acconto")): null);
		return ddt;
	}

}
