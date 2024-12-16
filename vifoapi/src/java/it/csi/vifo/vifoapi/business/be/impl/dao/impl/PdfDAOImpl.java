/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.PdfDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.PdfAssegnazioneMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.PdfBeneMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.PdfCertificatoMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.PdfDdtMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.PdfDomandaMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.PdfEtichettaMapper;
import it.csi.vifo.vifoapi.dto.PdfAssegnazione;
import it.csi.vifo.vifoapi.dto.PdfBene;
import it.csi.vifo.vifoapi.dto.PdfCertificato;
import it.csi.vifo.vifoapi.dto.PdfDdt;
import it.csi.vifo.vifoapi.dto.PdfDomanda;
import it.csi.vifo.vifoapi.dto.PdfEtichetta;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class PdfDAOImpl extends GenericDAO implements PdfDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public PdfDdt getDdt(int numeroDdt, int annoDdt) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select distinct numero_ddt, anno_ddt, ass.fk_domanda,vtd.data_inserimento ,vtd.fk_config_utente, nr_fattura_acconto, data_fattura_acconto, ");
			sql.append("sog.cognome, sog.nome, sog.ragione_sociale, sog.indirizzo, sog.codice_fiscale, sog.partita_iva,");
			sql.append("sog.istat_comune,sog.cap,");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 2) as direzione,");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 3) as settore");
			sql.append(" from vivai_t_ddt vtd");
			sql.append(" inner join vivai_r_ddt_assegnata ddtass on vtd.id_ddt = ddtass.id_ddt");
			sql.append(" inner join vivai_t_assegnata ass on ass.id_assegnata = ddtass.id_assegnata");
			sql.append(" inner join vivai_t_domanda dom on dom.id_domanda = ass.fk_domanda");
			sql.append(" inner join vivai_t_soggetto sog on sog.id_soggetto = dom.fk_soggetto");
			sql.append(" where numero_ddt = ? and anno_ddt = ?");
			
			return jdbcTemplate.queryForObject(sql.toString(),new PdfDdtMapper(),numeroDdt,annoDdt);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}

	}

	@Override
	public PdfAssegnazione getAssegnazione(int idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 2) as direzione, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 3) as settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 5) as indirizzo_settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 6) as cap_settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 7) as comune_settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 8) as telefono_settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 12) as direttore_settore, ");
			sql.append("vtd.id_domanda, vtd.fk_tipo_domanda, date_part('year', vtd.data_inserimento) as anno_domanda, ");
			sql.append("vtd.totale_importi_euro, vtd.totale_fattura, vtd.sconto_applicato,");
			sql.append("vtd.totale_imponibile_euro, vtd.aliquota_iva, vtd.importo_iva,");
			sql.append("r_stato.numero_protocollo,r_stato.data_protocollo, r_stato.data_inserimento,  ");
			sql.append("sog.nome, sog.cognome, sog.ragione_sociale, sog.indirizzo, sog.cap, sog.istat_comune, sog.telefono, sog.mail, sog.pec, ");
			sql.append("viv.nome as nome_vivaio, viv.istat_comune as comune_vivaio, viv.cap as cap_vivaio, ");
			sql.append("viv.indirizzo as indirizzo_vivaio, viv.telefono as telefono_vivaio, viv.mail as mail_vivaio, ");
			sql.append("viv.pec as pec_vivaio, viv.periodo_apertura, viv.orario_apertura as orari_vivaio, ");
			sql.append("sog_op.nome as nome_operatore, sog_op.cognome as cognome_operatore, descr_scopo ");
			sql.append("from vivai_t_domanda vtd    ");
			sql.append("left join vivai_d_scopo scp on scp.id_scopo = vtd.fk_scopo ");
			sql.append("inner join vivai_t_vivaio viv on vtd.fk_vivaio = viv.id_vivaio    ");
			sql.append("inner join vivai_r_stato_domanda r_stato on r_stato.id_domanda = vtd.id_domanda and r_stato.id_stato_domanda = 3 ");
			sql.append("and progressivo = (select max(progressivo) from vivai_r_stato_domanda where id_domanda = vtd.id_domanda and id_stato_domanda = 3) ");
			sql.append("inner join vivai_t_soggetto sog on sog.id_soggetto = vtd.fk_soggetto    ");
			sql.append("inner join vivai_cnf_config_utente cnf on cnf.id_config_utente = r_stato.fk_config_utente  ");
			sql.append("inner join vivai_t_soggetto sog_op on sog_op.id_soggetto = cnf.fk_soggetto  ");
			sql.append("where vtd.id_domanda = ?");
			
			return jdbcTemplate.queryForObject(sql.toString(),new PdfAssegnazioneMapper(),idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<PdfBene> getBeniDdt(int numeroDdt, int annoDdt) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select  inv.nome_comune as nomeSpecie,nome_scientifico as specie_latino,inv.id_inventario_piante as id_partita, ");
			sql.append("certificato_provenienza, vrda.qta_ddt as quantita, ");
			sql.append(" flg_no_fini_forestali, null as allevamento, null as prezzo_unitario, viv.sigla as sigla_vivaio, ");
			sql.append(" null as eta, null as provenienza ");
			sql.append("from vivai_r_ddt_assegnata vrda inner join vivai_t_ddt ddt on vrda.id_ddt = ddt.id_ddt  ");
			sql.append("inner join vivai_t_assegnata ass on vrda.id_assegnata = ass.id_assegnata  ");
			sql.append("inner join vivai_t_domanda dom on  ass.fk_domanda = dom.id_domanda ");
			sql.append("inner join vivai_t_vivaio viv on viv.id_vivaio = dom.fk_vivaio ");
			sql.append("inner join v_inventario_piante inv on inv.id_inventario_piante  = ass.fk_inventario ");
			sql.append("where ddt.numero_ddt= ? and ddt.anno_ddt = ? order by nomeSpecie ");
					
			return jdbcTemplate.query(sql.toString(),new PdfBeneMapper(),numeroDdt,annoDdt);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<PdfBene> getBeniAssegnazioneGratuita(int idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select inv.nome_comune as nomeSpecie, nome_scientifico as specie_latino, ");
			sql.append("certificato_provenienza, '0' as id_partita, ");
			sql.append("eta, provenienza, ");			
			sql.append("ass.qta_assegnata as quantita, flg_no_fini_forestali, descr_tipo_allevamento as allevamento, ");
			sql.append("null as prezzo_unitario, null as sigla_vivaio  ");
			sql.append("from vivai_t_assegnata ass ");
			sql.append("inner join v_inventario_piante inv on inv.id_inventario_piante  = ass.fk_inventario ");
			sql.append("where ass.fk_domanda = ? order by nomeSpecie  ");
					
			return jdbcTemplate.query(sql.toString(),new PdfBeneMapper(),idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<PdfBene> getBeniAssegnazioneOnerosa(int idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select  inv.nome_comune as nomeSpecie,nome_scientifico as specie_latino,inv.id_inventario_piante as id_partita, ");
			sql.append("certificato_provenienza, ass.qta_assegnata as quantita, flg_no_fini_forestali, prezzo_unitario , eta, provenienza, ");
			sql.append("descr_tipo_allevamento as allevamento, null as sigla_vivaio  ");
			sql.append("from vivai_t_assegnata ass  ");
			sql.append("inner join v_inventario_piante inv on inv.id_inventario_piante  = ass.fk_inventario ");
			sql.append("where ass.fk_domanda = ? order by nomeSpecie  ");
					
			return jdbcTemplate.query(sql.toString(),new PdfBeneMapper(),idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<PdfEtichetta> getEtichette(int idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 2) as direzione, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 3) as settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 30) as codice_autorizzativo, ");
			sql.append("viv.nome as nome_vivaio, viv.sigla as sigla_vivaio , viv.indirizzo as indirizzo_vivaio , viv.istat_comune as comune_viviaio , viv.cap as cap_vivaio, ");
			sql.append("inv.id_inventario as id_partita, inv.certificato_altro, inv.certificato_piemonte, ");
			sql.append(" inv.flg_no_fini_forestali,inv.descr_tipo_allevamento as allevamento, inv.eta, inv.altezza, ");
			sql.append(" sp.nome_scientifico as nomeSpecie, sp.flg_specie_386, sp.flg_specie_zp, qta_assegnata, inv.provenienza, tipo_materiale ");
			sql.append("from vivai_t_assegnata ass ");
			sql.append("inner join vivai_t_domanda vtd on vtd.id_domanda  = ass.fk_domanda ");
			sql.append("inner join vivai_t_vivaio viv on viv.id_vivaio = vtd.fk_vivaio ");
			sql.append("right join( select  inv.id_inventario, prov.fk_specie, cert_a.codice as certificato_altro, ");
			sql.append("	cert_vp.codice_cert_proven as certificato_piemonte, prov.flg_no_fini_forestali, allev.descr_tipo_allevamento, invp.eta, ");
			sql.append("	invp.altezza, prov.provenienza, vdtm.descr_tipo_materiale as tipo_materiale ");
			sql.append("	from vivai_t_provenienza prov ");
			sql.append("	inner join vivai_t_inventario inv on prov.id_provenienza = inv.fk_provenienza ");
			sql.append("	inner join vivai_t_invent_piante invp on invp.id_inventario_piante = inv.id_inventario ");
			sql.append("	inner join vivai_d_tipo_allevamento allev on allev.id_tipo_allevamento = invp.fk_tipo_allevamento ");
			sql.append("	left join vivai_t_cert_proven_altro cert_a on cert_a.id_provenienza = prov.id_provenienza ");
			sql.append("	left join vivai_t_cert_proven_piem cert_p on cert_p.id_provenienza  = prov.id_provenienza ");
			sql.append("	left join v_cod_cert_provenienza cert_vp on cert_vp.id_provenienza  = prov.id_provenienza ");
			sql.append("	left join vivai_d_tipo_materiale vdtm on vdtm.id_tipo_materiale = cert_p.fk_natura_materiale  ");
			sql.append("	) as inv on ass.fk_inventario = inv.id_inventario ");
			sql.append("inner join vivai_t_specie sp on sp.id_specie = inv.fk_specie ");
			sql.append("where ass.fk_domanda = ?");
			return jdbcTemplate.query(sql.toString(),new PdfEtichettaMapper(),idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PdfDomanda getDomanda(int idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select (select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 2) as direzione, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 3) as settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 12) as direttore_settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 5) as indirizzo_settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 6) as cap_settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 7) as comune_settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 8) as telefono_settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 11) as pec_settore, ");
			sql.append("vtd.id_domanda, vtd.fk_tipo_domanda, date_part('year', vtd.data_inserimento) as anno_domanda, vtd.note as note_domanda ");
			sql.append(",vtd.data_inserimento as data_domanda, concat(oper.cognome,' ',oper.nome) as operatore, vtd.n_marca_bollo, ");
			sql.append("concat(benef.cognome,' ',benef.nome) as nome_pf_beneficiario, benef.ragione_sociale as nome_pg_beneficiario, ");
			sql.append("benef.flg_ente_pubblico, benef.pec as pec_beneficiario,benef.indirizzo as indirizzo_beneficiario, ");
			sql.append("benef.cap as cap_beneficiario, benef.istat_comune as comune_beneficiario, benef.telefono as telefono_beneficiario, ");
			sql.append("benef.mail as mail_beneficiario, benef.codice_fiscale as cf_beneficiario, benef.partita_iva as piva_beneficiario, ");
			sql.append("viv.nome as nome_vivaio, viv.indirizzo as indirizzo_vivaio, viv.cap as cap_vivaio, viv.telefono as tel_vivaio,  ");
			sql.append("viv.istat_comune as comune_vivaio,   ");
			sql.append("vtd.fk_scopo, scp.descr_scopo as scopo, tit.descr_titolarita as titolarita ");
			sql.append("from vivai_t_domanda vtd   ");
			sql.append("inner join vivai_cnf_config_utente cnfute on cnfute.id_config_utente = vtd.fk_config_utente  ");
			sql.append("inner join vivai_t_soggetto oper on oper.id_soggetto = cnfute.fk_soggetto  ");
			sql.append("inner join vivai_t_soggetto benef on benef.id_soggetto = vtd.fk_soggetto  ");
			sql.append("inner join vivai_t_vivaio viv on viv.id_vivaio = vtd.fk_vivaio  ");
			sql.append("left join vivai_d_scopo scp on scp.id_scopo = vtd.fk_scopo ");
			sql.append("left join vivai_d_titolarita tit on tit.id_titolarita = vtd.fk_titolarita  ");
			sql.append("where vtd.id_domanda = ? ");
			return jdbcTemplate.queryForObject(sql.toString(),new PdfDomandaMapper(),idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PdfCertificato getCertificato(int idCertificato) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select (select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 2) as direzione, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 3) as settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 12) as direttore_settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 5) as indirizzo_settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 6) as cap_settore, ");
			sql.append("(select parametro_appl_char from vivai_cnf_parametro_appl where id_parametro_appl = 7) as comune_settore, ");
			sql.append("cert.flg_tipo_produzione, specie.nome_scientifico as nome_scientifico_specie, ");
			sql.append("vcert.codice_cert_proven, fk_natura_materiale,fk_categ_materiale,fk_tipo_materiale,  ");
			sql.append("prov.flg_no_fini_forestali as destinazione, ");
			sql.append("concat(pop.cod_amm_popolamento ,' - ', pop.nome_popolamento ) as cod_amm_popolamento, ");
			sql.append("cert.miscuglio, cert.fk_origine as origine_materiali_base, origine_non_autoctoni as descrizione_origine, ");
			sql.append("prov.provenienza, cert.altitudine,cert.anno_maturazione_semi,  ");
			sql.append("cert.qta_materiale_base, udm.descr_udm_materiale, ");
			sql.append("cert.flg_partita_precedente,cert.n_certificato_precedente, ");
			sql.append("cert.qta_partita_iniziale, cert.durata_allevamento, ");
			sql.append("cert.flg_propagazione,cert.metodo_propagazione, ");
			sql.append("cert.n_cicli_propagazione,cert.note,cert.data_certificato, ");
			sql.append("sog.nome, sog.cognome  ");
			sql.append("from v_cod_cert_provenienza vcert  ");
			sql.append("inner join vivai_t_cert_proven_piem cert on vcert.id_provenienza = cert.id_provenienza  ");
			sql.append("inner join vivai_t_provenienza prov on prov.id_provenienza = cert.id_provenienza ");
			sql.append("inner join vivai_t_specie specie on specie.id_specie = prov.fk_specie  ");
			sql.append("left join vivai_d_popolamento pop on pop.id_popolamento = cert.fk_popolamento  ");
			sql.append("left join vivai_d_udm_materiale udm on udm.id_udm_materiale = cert.fk_udm_materiale ");
			sql.append("left join vivai_cnf_config_utente cnfutente on cnfutente.id_config_utente = prov.fk_config_utente  ");
			sql.append("left join vivai_t_soggetto sog on sog.id_soggetto = cnfutente.fk_soggetto  ");
			sql.append("where cert.id_provenienza = ? ");
			return jdbcTemplate.queryForObject(sql.toString(),new PdfCertificatoMapper(),idCertificato);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


}
