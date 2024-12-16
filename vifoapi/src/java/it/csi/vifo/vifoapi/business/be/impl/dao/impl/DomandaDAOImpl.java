/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.DomandaDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.DomandaMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.DomandaRiepilogoMapper;
import it.csi.vifo.vifoapi.dto.Domanda;
import it.csi.vifo.vifoapi.dto.DomandaRiepilogo;
import it.csi.vifo.vifoapi.dto.RicercaDomanda;
import it.csi.vifo.vifoapi.dto.Specie;
import it.csi.vifo.vifoapi.util.AIKeyHolderUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;
import it.csi.vifo.vifoapi.util.PaginatedList;

@Component
public class DomandaDAOImpl extends GenericDAO implements DomandaDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	private static final String SQL_RIEPILOGO_DOMANDE_BY_SOGGETTO = "select d.id_domanda , d.fk_tipo_domanda ," +
			"CASE WHEN S.ragione_sociale is null " +
			"THEN s.cognome ||' ' || s.nome " +
			" ELSE s.ragione_sociale " +
			"END as richiedente, " +
			" vi.nome as vivaio, stato.descr_stato_domanda, rs2.data_inserimento as data_invio " + 
			" ,conf.fk_vivaio as vivaio_compilatore, pay.stato_pagamento, s.flg_ente_pubblico " +
			"from vivai_t_domanda d " + 
			"inner join vivai_t_soggetto s on s.id_soggetto = d.fk_soggetto " + 
			"inner join vivai_r_stato_domanda rs on d.id_domanda = rs.id_domanda and data_fine_stato is null " + 
			"left join vivai_r_stato_domanda rs2 on rs2.id_domanda = d.id_domanda and rs2.id_stato_domanda = 2 " +
			"inner join vivai_d_stato_domanda stato  on rs.id_stato_domanda = stato.id_stato_domanda " + 
			"inner join vivai_t_vivaio vi on vi.id_vivaio = d.fk_vivaio " + 
			"inner join  vivai_cnf_config_utente conf on conf.id_config_utente = d.fk_config_utente " +
			"left join (select fk_domanda, CASE WHEN stato_pagamento is null then 'Pending' else stato_pagamento end as stato_pagamento " +
			"from  vivai_t_pagamento where id_pagamento in " + 
			"(select max(id_pagamento) from vivai_t_pagamento where fk_tipo_pagamento = 1 group by fk_domanda )) " +
			"as pay on pay.fk_domanda = d.id_domanda " +			
			"where s.id_soggetto  = ? or s.id_soggetto in (" + 
			"select pfpg.id_soggetto_pg from vivai_r_pf_pg pfpg where pfpg.id_soggetto_pf = ?" + 
			") ";

	@Override
	public List<DomandaRiepilogo> getRiepilogoDomandeBySoggetto(Integer idSoggetto) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			List<DomandaRiepilogo> cnft = jdbcTemplate.query(SQL_RIEPILOGO_DOMANDE_BY_SOGGETTO, new DomandaRiepilogoMapper(), idSoggetto,idSoggetto);
			
			return cnft;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PaginatedList<DomandaRiepilogo> getPaginatedRiepilogoDomandeBySoggetto(Integer idSoggetto, int page, int limit, String sort) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			List<Object> parameters = new ArrayList<>();
			parameters.add(idSoggetto);
			parameters.add(idSoggetto);
			
			StringBuilder sql = new StringBuilder(SQL_RIEPILOGO_DOMANDE_BY_SOGGETTO);
			sort = sort != null? sort.replace("tipoDomanda","fkTipoDomanda"):null;
			sql.append(super.getSqlSort(sort,"-id_domanda"));
			
			return super.paginatedList(sql.toString(), parameters, new DomandaRiepilogoMapper(), page, limit);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int createDomanda(Domanda domanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "insert into vivai_t_domanda (fk_soggetto,fk_tipo_domanda,fk_titolarita,fk_scopo,fk_vivaio,"
					+ "fk_config_utente,note,n_marca_bollo,data_inserimento) "
					+ "values (?,?,?,?,?,?,?,?,?)";
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(domanda.getFkSoggetto());
			parameters.add(domanda.getFkTipoDomanda());
			parameters.add(domanda.getFkTitolarita());
			parameters.add(domanda.getFkScopo());
			parameters.add(domanda.getFkVivaio());
			parameters.add(domanda.getFkConfigUtente());
			parameters.add(domanda.getNote());
			parameters.add(domanda.getNumMarcaBollo());
			parameters.add(now);
			return AIKeyHolderUtil.updateWithGenKey("id_domanda", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int updateDomanda(Domanda domanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder("update vivai_t_domanda set ");
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			
			sql.append("data_aggiornamento = ? ");
			parameters.add(now);
			
			if (domanda.getFkSoggetto() != null) {
				sql.append(", fk_soggetto = ?");
				parameters.add(domanda.getFkSoggetto());
			}
			
			if (domanda.getFkTitolarita() != null) {
				sql.append(", fk_titolarita = ?");
				parameters.add(domanda.getFkTitolarita());
			}
			
			if (domanda.getFkScopo() != null) {
				sql.append(", fk_scopo = ?");
				parameters.add(domanda.getFkScopo());
			}
			
			if (domanda.getFkVivaio() != null) {
				sql.append(", fk_vivaio = ?");
				parameters.add(domanda.getFkVivaio());
			}
			
			if (domanda.getFkConfigUtente() != null) {
				sql.append(", fk_config_utente = ?");
				parameters.add(domanda.getFkConfigUtente());
			}
			
			if (domanda.getNote() != null) {
				sql.append(", note = ?");
				parameters.add(domanda.getNote());
			}
			
			if (domanda.getNumMarcaBollo() != null) {
				sql.append(", n_marca_bollo = ?");
				parameters.add(domanda.getNumMarcaBollo());
			}
			
			if(domanda.getFkTipoDomanda() == 2){
				if (domanda.getTotImportiEuro() != null) {
					sql.append(", totale_importi_euro = ?");
					parameters.add(domanda.getTotImportiEuro());
				}

				if (domanda.getScontoApplicato() != null) {
					sql.append(", sconto_applicato = ?");
					parameters.add(domanda.getScontoApplicato());
				}

				if (domanda.getTotImponibileEuro() != null) {
					sql.append(", totale_imponibile_euro = ?");
					parameters.add(domanda.getTotImponibileEuro());
				}

				if (domanda.getAliquotaIva() != null) {
					sql.append(", aliquota_iva = ?");
					parameters.add(domanda.getAliquotaIva());
				}

				if (domanda.getImportoIva() != null) {
					sql.append(", importo_iva = ?");
					parameters.add(domanda.getImportoIva());
				}

				if (domanda.getTotFattura() != null) {
					sql.append(", totale_fattura = ?");
					parameters.add(domanda.getTotFattura());
				}

			}
			sql.append(" where id_domanda = ?");
			parameters.add(domanda.getIdDomanda());
			
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Domanda loadDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select d.*, s.descr_stato_domanda, s.id_stato_domanda as fkStatoDomanda " + 
					" from vivai_t_domanda d " + 
					"inner join vivai_r_stato_domanda sd on d.id_domanda =sd.id_domanda and data_fine_stato is null " + 
					"inner join vivai_d_stato_domanda s on s.id_stato_domanda = sd.id_stato_domanda " + 
					"where d.id_domanda = ?";
			return jdbcTemplate.queryForObject(sql, new DomandaMapper(), idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int deleteDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "delete from vivai_t_domanda where id_domanda = ?";
			return jdbcTemplate.update(sql,idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private StringBuilder getSqlDomandaRiepilogo() {
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct * from (");
		sb.append("select distinct vtd.id_domanda, r_stato2.data_inserimento as data_invio, td.descr_tipo_domanda as tipo_domanda,"); 
		sb.append("CASE WHEN sog.ragione_sociale is null or sog.ragione_sociale = '' ");
		sb.append("			   THEN sog.cognome ||' ' || sog.nome "); 
		sb.append("            ELSE sog.ragione_sociale "); 
		sb.append("       end as richiedente, des_stato.descr_stato_domanda as stato,"); 
		sb.append("viv.nome as vivaio, des_stato.descr_stato_domanda, vtd.fk_tipo_domanda  "); 
		sb.append(",cnf.fk_vivaio as vivaio_compilatore, pay.stato_pagamento, sog.flg_ente_pubblico ");
		sb.append("from vivai_t_domanda vtd  "); 
		sb.append("inner join vivai_cnf_config_utente cnf on vtd.fk_config_utente = cnf.id_config_utente "); 
		sb.append("inner join vivai_t_vivaio viv on vtd.fk_vivaio = viv.id_vivaio  "); 
		sb.append("inner join vivai_d_tipo_domanda td on td.id_tipo_domanda = vtd.fk_tipo_domanda "); 
		sb.append("left join vivai_r_stato_domanda r_stato on r_stato.id_domanda = vtd.id_domanda and r_stato.data_fine_stato is null ");
		sb.append("left join (select id_domanda, max(data_inserimento) as data_inserimento from vivai_r_stato_domanda where id_stato_domanda = 2 group by id_domanda ) r_stato2 on r_stato2.id_domanda = vtd.id_domanda ");
		sb.append("left join vivai_d_stato_domanda des_stato on r_stato.id_stato_domanda = des_stato.id_stato_domanda  "); 
		sb.append("inner join vivai_t_soggetto sog on sog.id_soggetto = vtd.fk_soggetto  "); 
		sb.append("left join vivai_t_catasto cat on cat.fk_domanda = vtd.id_domanda  "); 
		sb.append("left join vivai_r_domanda_specie specie on specie.id_domanda = vtd.id_domanda  "); 
		sb.append("left join (select fk_domanda, CASE WHEN stato_pagamento is null then 'Pending' else stato_pagamento end as stato_pagamento "); 
		sb.append( "from  vivai_t_pagamento where id_pagamento in ");
		sb.append("(select max(id_pagamento) from vivai_t_pagamento where fk_tipo_pagamento = 1 group by fk_domanda )) ");
		sb.append("as pay on pay.fk_domanda = vtd.id_domanda");
		return sb;
	}
	
	@Override
	public DomandaRiepilogo getDomandaRiepilogoByIdDomanda(int idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sb = getSqlDomandaRiepilogo();			
			sb.append(" where vtd.id_domanda = ?");	
			sb.append(") as foo ");
			
			List<DomandaRiepilogo> list =  jdbcTemplate.query(sb.toString(), new DomandaRiepilogoMapper(), idDomanda);
			return list.size()>0?list.get(0):null;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PaginatedList<DomandaRiepilogo> getPaginatedSearchDomande(RicercaDomanda ricerca, int page, int limit, String sort) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sb = getSqlDomandaRiepilogo();
			
			
			String plus = " where ";
			List<Object> parameters = new ArrayList<>();
			if(ricerca.getIdDomanda() != null) {
				sb.append(plus).append("vtd.id_domanda = ?");
				parameters.add(ricerca.getIdDomanda());
				plus = " and ";
			}
			if(ricerca.getIdSoggetto() != null) {
				sb.append(plus).append("sog.id_soggetto = ?");
				parameters.add(ricerca.getIdSoggetto());
				plus = " and ";
			}
			if(ricerca.getIdVivaio() != null) {
				sb.append(plus).append("vtd.fk_vivaio = ?");
				parameters.add(ricerca.getIdVivaio());
				plus = " and ";
				if(ricerca.getDomandaVivaio()) {
					sb.append(plus).append(" cnf.fk_vivaio = vtd.fk_vivaio ");
				}else {
					sb.append(plus).append(" (des_stato.id_stato_domanda > 1 or cnf.fk_vivaio = vtd.fk_vivaio)");
				}
			}else {
				sb.append(plus).append(" des_stato.id_stato_domanda > 1 ");
				plus = " and ";
			}
			if(ricerca.getTipo() != null) {
				sb.append(plus).append("vtd.fk_tipo_domanda = ?");
				parameters.add(ricerca.getTipo());
				plus = " and ";
			}
			if(ricerca.getScopo() != null) {
				sb.append(plus).append("vtd.fk_scopo = ?");
				parameters.add(ricerca.getScopo());
				plus = " and ";
			}
			if(ricerca.getDataInvioDa() != null) {
				sb.append(plus).append("r_stato2.data_inserimento >= TO_DATE(?,'YYYYMMDD')");
				parameters.add(ricerca.getDataInvioDa());
				plus = " and ";
			}
			if(ricerca.getDataInvioA() != null) {
				sb.append(plus).append("r_stato2.data_inserimento <= TO_DATE(?,'YYYYMMDD')");
				parameters.add(ricerca.getDataInvioA());
				plus = " and ";
			}
			if(ricerca.getStato() != null) {
				sb.append(plus).append("r_stato.id_stato_domanda = ?");
				parameters.add(ricerca.getStato());
				plus = " and ";
			}
			if(ricerca.getCognome() != null) {
				sb.append(plus).append("upper(sog.cognome) = ?");
				parameters.add(ricerca.getCognome().toUpperCase());
				plus = " and ";
			}
			if(ricerca.getDenominazione() != null) {
				sb.append(plus).append("upper(sog.ragione_sociale) = ?");
				parameters.add(ricerca.getDenominazione().toUpperCase());
				plus = " and ";
			}
			if(ricerca.getComune() != null) {
				sb.append(plus).append("cat.istat_comune = ?");
				parameters.add(ricerca.getComune());
				plus = " and ";
			}
			if(ricerca.isEscludiDomandeChiuse()) {
				sb.append(plus).append("r_stato.id_stato_domanda not in(8,9)");
				plus = " and ";
			}
			Specie[] vetSpecie = ricerca.getSpecieRichieste();
			if(vetSpecie != null && vetSpecie.length > 0) {
				sb.append(plus).append("specie.id_specie in ");
				 
				StringBuilder inClausole = new StringBuilder("(").append(vetSpecie[0].getIdSpecie());
				for(int i=1;i<vetSpecie.length;i++) {
					inClausole.append(",").append(vetSpecie[i].getIdSpecie());
				}
				sb.append(inClausole).append(")");
			}
			sb.append(") as foo ");
			sb.append(super.getSqlSort(sort,"-id_domanda"));
			
			return super.paginatedList(sb.toString(), parameters, new DomandaRiepilogoMapper(), page, limit);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int getNumDomandeBySoggetto(Integer idSoggetto, Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        List<Object> parameters = new ArrayList<>();
	        parameters.add(idSoggetto);
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from vivai_t_domanda vtd ");
			sql.append(" left join vivai_r_stato_domanda r_stato on r_stato.id_domanda = vtd.id_domanda and r_stato.data_fine_stato is null ");
			sql.append(" where fk_soggetto = ? and r_stato.id_stato_domanda > 1");
			if(idVivaio !=null && idVivaio > 0) {
				sql.append(" and fk_vivaio = ? ");
				parameters.add(idVivaio);
			}
			
			return jdbcTemplate.queryForObject(sql.toString(), parameters.toArray(), Integer.class);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int resetImporti(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("update vivai_t_domanda set totale_importi_euro = null, sconto_applicato = null, ");
			sql.append("totale_imponibile_euro = null, aliquota_iva = null, importo_iva = null, totale_fattura = null ");
			sql.append("where id_domanda = ? ");
						
			return jdbcTemplate.update(sql.toString(), idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public int insertBoprocLog(String idPratica, int step) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select COUNT(fk_pratica) from vivai_cnf_boproc_log where fk_step_boproc = ? and fk_pratica = ?");
			int res = jdbcTemplate.queryForObject(sql.toString(), Integer.class,step, idPratica);
			if(res > 0) {
				return res;
			}
			
			sql = new StringBuilder();
			sql.append("INSERT INTO vivai_cnf_boproc_log ");
			sql.append("(fk_pratica, data_inizio, fk_step_boproc, count_tentativo)");
			sql.append(" VALUES (?, ?, ?, 0)");
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object>parameters = new ArrayList<>();
			parameters.add(idPratica);
			parameters.add(now);
			parameters.add(step);
			
			return AIKeyHolderUtil.updateWithGenKey("id_cnf_boproc_log", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	
}
