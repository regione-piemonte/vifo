/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.DdtDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.BaseDdtMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.DdtMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.DdtRiepilogoMapper;
import it.csi.vifo.vifoapi.dto.BaseDdt;
import it.csi.vifo.vifoapi.dto.Ddt;
import it.csi.vifo.vifoapi.dto.DdtRiepilogo;
import it.csi.vifo.vifoapi.dto.RicercaDdt;
import it.csi.vifo.vifoapi.util.AIKeyHolderUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;
import it.csi.vifo.vifoapi.util.PaginatedList;

@Component
public class DdtDAOImpl extends GenericDAO implements DdtDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Override
	public int getNumeroDdtByAnno(Integer anno) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT COUNT(numero_ddt )+1 from vivai_t_ddt  where anno_ddt = ? ";
			int res = jdbcTemplate.queryForObject(sql, new Object[] { anno }, Integer.class);
			if(res > 1) {
				sql = "SELECT MAX(numero_ddt )+1 from vivai_t_ddt  where anno_ddt = ? ";
				res = jdbcTemplate.queryForObject(sql, new Object[] { anno }, Integer.class);
			}
			return res;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<Ddt> getAvailableDdtByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			
			sql.append("select null as id_ddt, spec.nome_comune as specie, spec.nome_scientifico as specie_latino,inv.id_inventario as id_partita, ");
			sql.append("allev.descr_tipo_allevamento, viv.nome as nomeVivaio, vip.certificato_provenienza, "); 
			sql.append("invp.eta, invp.altezza, cert_a.codice as certificato_altro, cert_p.numero_certificato as certificato_piemonte, "); 
			sql.append("ass.qta_assegnata, ass.id_assegnata, ddt.qta_ddt, prov.flg_no_fini_forestali, ddt.data_fattura_acconto, ddt.nr_fattura_acconto "); 
			sql.append("from vivai_t_assegnata ass ");
			sql.append("inner join vivai_t_invent_piante invp on invp.id_inventario_piante = ass.fk_inventario "); 
			sql.append("inner join vivai_d_tipo_allevamento allev on allev.id_tipo_allevamento = invp.fk_tipo_allevamento ");
			sql.append("inner join vivai_t_inventario inv on inv.id_inventario = invp.id_inventario_piante  "); 
			sql.append("inner join vivai_t_provenienza prov on prov.id_provenienza = inv.fk_provenienza  "); 
			sql.append("inner join vivai_t_specie spec on prov.fk_specie = spec.id_specie  "); 
			sql.append("inner join vivai_t_vivaio viv on invp.fk_vivaio = viv.id_vivaio "); 
			sql.append("left join v_inventario_piante vip on vip.id_inventario_piante = inv.id_inventario "); 
			sql.append("left join vivai_t_cert_proven_altro cert_a on cert_a.id_provenienza = prov.id_provenienza   "); 
			sql.append("left join vivai_t_cert_proven_piem cert_p on cert_p.id_provenienza  = prov.id_provenienza "); 
			sql.append("left join (select id_assegnata, sum(qta_ddt) as qta_ddt, ddt.data_fattura_acconto, ");
			sql.append("ddt.nr_fattura_acconto from vivai_r_ddt_assegnata vrda ");
			sql.append("inner join vivai_t_ddt ddt on vrda.id_ddt = ddt.id_ddt  "); 
			sql.append("group by vrda.id_assegnata, ddt.data_fattura_acconto,ddt.nr_fattura_acconto) as ddt on ddt.id_assegnata = ass.id_assegnata  "); 
			sql.append("where ass.fk_domanda = ? ");
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName, "sql: " + sql.toString()));
			return jdbcTemplate.query(sql.toString(), new DdtMapper(), idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<BaseDdt> getListDdtByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder(); 
			sql.append("select numero_ddt, anno_ddt, vtd.data_inserimento, sum(qta_ddt) as qta_ddt ");
			sql.append("from vivai_t_ddt vtd "); 
			sql.append("inner join vivai_r_ddt_assegnata vtdass on vtd.id_ddt = vtdass.id_ddt ");
			sql.append("inner join vivai_t_assegnata ass on ass.id_assegnata = vtdass.id_assegnata "); 
			sql.append("where ass.fk_domanda = ? ");
			sql.append("group by numero_ddt, anno_ddt, vtd.data_inserimento ");
			sql.append("order by numero_ddt");
			return jdbcTemplate.query(sql.toString(), new BaseDdtMapper(), idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int saveDdt(Ddt ddt) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_ddt ");
			sql.append("(numero_ddt, anno_ddt, data_inserimento, fk_config_utente, nr_fattura_acconto, data_fattura_acconto) "); 
			sql.append("VALUES(?, ?, ?, ?, ?, ?)");
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<Object>();
			parameters.add(ddt.getNumeroDdt());
			parameters.add(ddt.getAnnoDdt());
			parameters.add(now);
			parameters.add(ddt.getFkConfigUtente());
			parameters.add(ddt.getNumeroFatturaAcconto().trim().equals("") ? null : ddt.getNumeroFatturaAcconto());
			parameters.add(ddt.getDataFatturaAcconto()!= null ? new Date(ddt.getDataFatturaAcconto().getTime()) : null);
	
			return  AIKeyHolderUtil.updateWithGenKey("id_ddt", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public int saveDdtDettails(Ddt ddt) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_r_ddt_assegnata ");
			sql.append("(id_ddt, id_assegnata, qta_ddt, data_inserimento, fk_config_utente) "); 
			sql.append("VALUES(?, ?, ?, ?, ?)");
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<Object>();
			parameters.add(ddt.getIdDdt());
			parameters.add(ddt.getIdAssegnata());
			parameters.add(ddt.getQtaInDdt());
			parameters.add(now);
			parameters.add(ddt.getFkConfigUtente());
			
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public DdtRiepilogo getInfoDdt(Integer numeroDdt, Integer anno) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select numero_ddt, anno_ddt, ass.fk_domanda,vtd.data_inserimento ,");
			sql.append("vtd.fk_config_utente, sum(qta_ddt) as qta_ddt "); 
			sql.append("from vivai_t_ddt vtd ");
			sql.append("inner join vivai_r_ddt_assegnata ddtass on vtd.id_ddt = ddtass.id_ddt "); 
			sql.append("inner join vivai_t_assegnata ass on ass.id_assegnata = ddtass.id_assegnata "); 
			sql.append("where numero_ddt = ? and anno_ddt = ? ");
			sql.append("group by numero_ddt, anno_ddt, ass.fk_domanda, vtd.data_inserimento,vtd.fk_config_utente ");
			return jdbcTemplate.queryForObject(sql.toString(),new DdtRiepilogoMapper(), numeroDdt, anno);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public List<Ddt> getByNumeroDdtAndAnno(Integer numeroDdt, Integer anno) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select ddt.id_ddt, spec.nome_comune as specie,spec.nome_scientifico as specie_latino, inv.id_inventario as id_partita, "
					+"ddt.nr_fattura_acconto, ddt.data_fattura_acconto, "
					+ "allev.descr_tipo_allevamento,viv.nome as nomeVivaio, vip.certificato_provenienza, " + 
					"invp.eta, invp.altezza, cert_a.codice as certificato_altro, cert_p.numero_certificato as certificato_piemonte," + 
					"ass.qta_assegnata, ass.id_assegnata, vrda.qta_ddt, prov.flg_no_fini_forestali " + 
					"from vivai_r_ddt_assegnata vrda inner join vivai_t_ddt ddt on vrda.id_ddt = ddt.id_ddt " + 
					"inner join vivai_t_assegnata ass on vrda.id_assegnata = ass.id_assegnata " + 
					"inner join vivai_t_invent_piante invp on invp.id_inventario_piante = ass.fk_inventario " + 
					"inner join vivai_d_tipo_allevamento allev on allev.id_tipo_allevamento = invp.fk_tipo_allevamento " + 
					"inner join vivai_t_inventario inv on inv.id_inventario = invp.id_inventario_piante " + 
					"inner join vivai_t_provenienza prov on prov.id_provenienza = inv.fk_provenienza " + 
					"inner join vivai_t_specie spec on prov.fk_specie = spec.id_specie " + 
					"inner join vivai_t_vivaio viv on invp.fk_vivaio = viv.id_vivaio " + 
					"left join v_inventario_piante vip on vip.id_inventario_piante = invp.id_inventario_piante " +
					"left join vivai_t_cert_proven_altro cert_a on cert_a.id_provenienza = prov.id_provenienza  " + 
					"left join vivai_t_cert_proven_piem cert_p on cert_p.id_provenienza  = prov.id_provenienza " + 
					"where ddt.numero_ddt= ? and ddt.anno_ddt = ?");
			return jdbcTemplate.query(sql.toString(), new DdtMapper(), numeroDdt, anno);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int deleteDdtDettalis(Integer idDdt) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "Delete from vivai_r_ddt_assegnata where id_ddt = ?";
			return jdbcTemplate.update(sql,idDdt);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int delete(Integer idDdt) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        deleteDdtDettalis(idDdt);			
			String sql = "Delete from vivai_t_ddt where id_ddt = ?";
			return jdbcTemplate.update(sql,idDdt);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public PaginatedList<DdtRiepilogo> getPaginatedSearchDdt(RicercaDdt ricerca, int page, int limit, String sort) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sb = new StringBuilder();
			sb.append("select * from (");
			sb.append("select numero_ddt, anno_ddt, ass.fk_domanda, vtd.data_inserimento ,vtd.fk_config_utente, ");
			sb.append("vtd.data_inserimento as data_creazione, ass.fk_domanda as id_domanda, "); 
			sb.append("sum(qta_ddt) as qta_ddt "); 
			sb.append("from vivai_t_ddt vtd "); 
			sb.append("inner join vivai_r_ddt_assegnata ddtass on vtd.id_ddt = ddtass.id_ddt "); 
			sb.append("inner join vivai_t_assegnata ass on ass.id_assegnata = ddtass.id_assegnata "); 
			sb.append("inner join vivai_t_domanda dom on dom.id_domanda = ass.fk_domanda "); 
			sb.append("inner join vivai_t_soggetto sog on sog.id_soggetto = dom.fk_soggetto "); 
	
			String plus = " where ";
			List<Object> parameters = new ArrayList<>();
			
			if(ricerca.getNumeroDdt() != null) {
				sb.append(plus).append("vtd.numero_ddt = ?");
				parameters.add(ricerca.getNumeroDdt());
				plus = " and ";
			}		
			if(ricerca.getAnnoDdt() != null) {
				sb.append(plus).append("vtd.anno_ddt = ?");
				parameters.add(ricerca.getAnnoDdt());
				plus = " and ";
			}		
			if(ricerca.getIdDomanda() != null) {
				sb.append(plus).append("dom.id_domanda = ?");
				parameters.add(ricerca.getIdDomanda());
				plus = " and ";
			}
			if(ricerca.getCognome() != null && ricerca.getCognome().length() > 0) {
				sb.append(plus).append("sog.cognome = ?");
				parameters.add(ricerca.getCognome());
				plus = " and ";
			}
			if(ricerca.getDenominazione() != null && ricerca.getDenominazione().length() > 0) {
				sb.append(plus).append("sog.ragione_sociale = ?");
				parameters.add(ricerca.getDenominazione());
				plus = " and ";
			}
			if(ricerca.getIdVivaio() != null) {
				sb.append(plus).append("dom.fk_vivaio = ?");
				parameters.add(ricerca.getIdVivaio());
			}
			sb.append(" group by numero_ddt, anno_ddt, ass.fk_domanda, vtd.data_inserimento ,");
			sb.append(" vtd.fk_config_utente, vtd.data_inserimento , ass.fk_domanda) as ddt ");
			sb.append(super.getSqlSort(sort,"-numero_ddt"));
	
			
			return super.paginatedList(sb.toString(), parameters, new DdtRiepilogoMapper(), page, limit);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public int annullaDdtDettalis(Integer idDdt) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "update vivai_r_ddt_assegnata set qta_ddt = 0 where id_ddt = ?";
			return jdbcTemplate.update(sql,idDdt);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public int updateDdt(Ddt ddt, Integer idDdt) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        List<Object> parameters = new ArrayList<>();
	        Timestamp now = new Timestamp(System.currentTimeMillis());
			String sql = "update vivai_t_ddt set data_aggiornamento = ?, nr_fattura_acconto = ?, data_fattura_acconto = ? where id_ddt = ?";
			parameters.add(now);
			parameters.add(ddt.getNumeroFatturaAcconto().trim().equals("") ? null : ddt.getNumeroFatturaAcconto());
			parameters.add(ddt.getDataFatturaAcconto() != null ? new Date(ddt.getDataFatturaAcconto().getTime()) : null);
			parameters.add(idDdt);
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
