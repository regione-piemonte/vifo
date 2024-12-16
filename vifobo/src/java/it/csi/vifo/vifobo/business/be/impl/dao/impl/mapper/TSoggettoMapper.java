/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifobo.business.be.service.impl.WsServiceImpl;
import it.csi.vifo.vifobo.dto.TSoggetto;


public class TSoggettoMapper implements RowMapper<TSoggetto>{	
			
	@Autowired
	ApplicationContext applicationContext;
	
	@Override
	public TSoggetto mapRow(ResultSet rs, int arg1) throws SQLException {
		

		TSoggetto soggetto = new TSoggetto();
		
		soggetto.setIdSoggetto(rs.getInt("id_soggetto"));
		soggetto.setCodiceFiscale(rs.getString("codice_fiscale"));
		soggetto.setCognome(rs.getString("cognome"));
		soggetto.setNome(rs.getString("nome"));
		soggetto.setRagioneSociale(rs.getString("ragione_sociale"));
		soggetto.setPartitaIva(rs.getString("partita_iva"));
		soggetto.setIndirizzo(rs.getString("indirizzo"));
		soggetto.setCap(rs.getString("cap"));
		if(WsServiceImpl.mapComuniPerIstat != null) {
			soggetto.setComune(WsServiceImpl.mapComuniPerIstat.get(rs.getString("istat_comune")));
		}
		soggetto.setTelefono(rs.getString("telefono"));
		soggetto.setMail(rs.getString("mail"));
		soggetto.setPec(rs.getString("pec"));
		soggetto.setCodiceBeneficiario(rs.getString("codice_beneficiario"));
		soggetto.setFlgEntePubblico(rs.getByte("flg_ente_pubblico"));
		soggetto.setDataInserimento(rs.getString("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		soggetto.setDataAggiornamento(rs.getString("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		soggetto.setFkConfigUtente(rs.getString("fk_config_utente") == null ? null : rs.getInt("fk_config_utente"));
		
		
		return soggetto;
	}
}
