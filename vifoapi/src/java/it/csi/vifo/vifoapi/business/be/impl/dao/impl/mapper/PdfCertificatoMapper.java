/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.vifo.vifoapi.dto.PdfCertificato;
import it.csi.vifo.vifoapi.util.DateUtil;

public class PdfCertificatoMapper implements RowMapper<PdfCertificato> {

	@Override
	public PdfCertificato mapRow(ResultSet rs, int arg1) throws SQLException {

		PdfCertificato cert = new PdfCertificato();
		cert.setDirezione(notNullString(rs.getString("direzione")));
		cert.setSettore(notNullString(rs.getString("settore")));
		cert.setDirigenteSettore(notNullString(rs.getString("direttore_settore")));
		cert.setIndirizzoSettore(notNullString(rs.getString("indirizzo_settore")));
		cert.setCapSettore(notNullString(rs.getString("cap_settore")));
		cert.setComuneSettore(notNullString(rs.getString("comune_settore")));
		cert.setCodiceCertificato(notNullString(rs.getString("codice_cert_proven")));
		cert.setFlgTipoProduzione(notNullString(rs.getString("flg_tipo_produzione")));
		cert.setNomeScientificoSpecie(notNullString(rs.getString("nome_scientifico_specie")));
		cert.setNaturaMaterialiMoltiplicazione(notNullString(rs.getString("fk_natura_materiale")));
		cert.setCategoriaMaterialiMoltiplicazione(notNullString(rs.getString("fk_categ_materiale")));
		cert.setTipoMaterialiBase(notNullString(rs.getString("fk_tipo_materiale")));
		cert.setDestinazione(notNullString(rs.getString("destinazione")));
		cert.setCodicePopolamentoSeme(notNullString(rs.getString("cod_amm_popolamento")));
		cert.setMiscuglio(notNullString(rs.getString("miscuglio")));
		cert.setOrigineMaterialiBase(notNullString(rs.getString("origine_materiali_base")));
		cert.setDescrizioneOrigine(notNullString(rs.getString("descrizione_origine")));
		cert.setProvenienza(notNullString(rs.getString("provenienza")));
		cert.setAltitudine(notNullString(rs.getString("altitudine")));
		cert.setAnnoMaturazioneSementi(notNullString(rs.getString("anno_maturazione_semi")));
		cert.setQtaMaterialeBase(notNullString(rs.getString("qta_materiale_base")));
		cert.setUnitaMisuraMaterialeBase(notNullString(rs.getString("descr_udm_materiale")));
		cert.setFlgPartitaPrecedente(notNullString(rs.getString("flg_partita_precedente")));
		cert.setNroCertificatoPrecedente(notNullString(rs.getString("n_certificato_precedente")));
		cert.setQtaPartitaIniziale(notNullString(rs.getString("qta_partita_iniziale")));
		cert.setDurataAllevamento(notNullString(rs.getString("durata_allevamento")));
		cert.setFlgPropagazione(notNullString(rs.getString("flg_propagazione")));
		cert.setMetodoPropagazione(notNullString(rs.getString("metodo_propagazione")));
		cert.setNumeroCicli(notNullString(rs.getString("n_cicli_propagazione")));
		cert.setNote(notNullString(rs.getString("note")));
		cert.setDataCertificato(notNullString(DateUtil.dateFormatter.format(rs.getDate("data_certificato"))));
		cert.setCognomeNomeUtenteGestore(rs.getString("cognome")+ " " + rs.getString("nome"));
		return cert;
	}
	
	private String notNullString(String str) {
		return str == null?"":str;
	}

}
