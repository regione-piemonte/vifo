/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.DizionarioDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.StringMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.VoceDizionarioMapper;
import it.csi.vifo.vifoapi.dto.VoceDizionario;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class DizionarioDAOImpl extends GenericDAO implements DizionarioDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	

	@Override
	public double getScontoByImporto(double importo) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select sconto*100 from vivai_cnf_sconto where prezzo_da <= ? and prezzo_a >= ?";
			List<Double> res = jdbcTemplate.queryForList(sql, Double.class, importo, importo);
			return res.size()>0 ? res.get(0):0d;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListStatoDomanda() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT id_stato_domanda as id, descr_stato_domanda as descrizione "
					+ "FROM vivai_d_stato_domanda order by mtd_ordinamento";
			return template.query(sql, new VoceDizionarioMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListTipoDomanda() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT id_tipo_domanda as id, descr_tipo_domanda as descrizione "
					+ "FROM vivai_d_tipo_domanda order by id_tipo_domanda";
			return template.query(sql, new VoceDizionarioMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<String> getListCognomeRichiedente(int vivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        StringBuilder sql = new StringBuilder() ;
	        sql.append("SELECT distinct cognome from vivai_t_domanda vtd ");
	        sql.append("inner join vivai_t_soggetto sog on vtd.fk_soggetto = sog.id_soggetto ");
			sql.append("inner join vivai_r_stato_domanda stato on stato.id_domanda = vtd.id_domanda and stato.data_fine_stato is null ");
	        sql.append("where cognome is not null and cognome <> ''");
			sql.append("and vtd.fk_vivaio" + (vivaio == 0?">":"=")).append(" ? ");
			sql.append("and (stato.id_stato_domanda > 1 or vtd.fk_vivaio = ?) ");
			sql.append("order by cognome");
			return jdbcTemplate.query(sql.toString(), new StringMapper(),vivaio,vivaio);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<String> getListDenominazioneRichiedente(int vivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder() ;
			sql.append("SELECT distinct ragione_sociale from vivai_t_domanda vtd ");
			sql.append("inner join vivai_t_soggetto sog on vtd.fk_soggetto = sog.id_soggetto  ");
			sql.append("inner join vivai_r_stato_domanda stato on stato.id_domanda = vtd.id_domanda and stato.data_fine_stato is null ");
			sql.append("where ragione_sociale is not null and ragione_sociale <> '' ");
			sql.append("and vtd.fk_vivaio" + (vivaio == 0?">":"=")).append(" ? ");
			sql.append("and (stato.id_stato_domanda > 1 or vtd.fk_vivaio = ?) ");
			sql.append("order by ragione_sociale ");
			return jdbcTemplate.query(sql.toString(), new StringMapper(),vivaio,vivaio);
			
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListMaterialiDiMoltiplicazione() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT id_natura_materiale as id, descr_natura_materiale as descrizione "
					+ "FROM vivai_d_natura_materiale where flg_attivo = 1 order by mtd_ordinamento";
			return template.query(sql, new VoceDizionarioMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListCategorieMateriale() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT id_categ_materiale as id, descr_categ_materiale as descrizione "
					+ "FROM vivai_d_categ_materiale where flg_attivo = 1 order by mtd_ordinamento";
			return template.query(sql, new VoceDizionarioMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListTipiMaterialeBase() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT id_tipo_materiale as id, descr_tipo_materiale as descrizione "
					+ "FROM vivai_d_tipo_materiale where flg_attivo = 1 order by mtd_ordinamento ";
			return template.query(sql, new VoceDizionarioMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListPopolamentoDaSeme() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT id_popolamento as id, concat(cod_amm_popolamento,' - ' , nome_popolamento) as descrizione "
					+ "FROM vivai_d_popolamento where flg_attivo = 1 order by mtd_ordinamento ";
			return template.query(sql, new VoceDizionarioMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListOrigini() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT id_origine as id, descr_origine as descrizione "
					+ "FROM vivai_d_origine where flg_attivo = 1 order by mtd_ordinamento ";
			return template.query(sql, new VoceDizionarioMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListUdmMateriale() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT id_udm_materiale as id, descr_udm_materiale as descrizione "
					+ "FROM vivai_d_udm_materiale where flg_attivo = 1 order by mtd_ordinamento ";
			return template.query(sql, new VoceDizionarioMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListTipiAllevamento() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT id_tipo_allevamento as id, descr_tipo_allevamento as descrizione "
					+ "FROM vivai_d_tipo_allevamento where flg_attivo = 1 order by mtd_ordinamento ";
			return template.query(sql, new VoceDizionarioMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListGruppoSpecie() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT id_gruppo as id, descr_gruppo as descrizione "
					+ "FROM vivai_d_gruppo where flg_attivo = 1 order by mtd_ordinamento ";
			return template.query(sql, new VoceDizionarioMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<String> getListEta() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        StringBuilder sql = new StringBuilder();
	        sql.append("select eta from (");
    		sql.append("	select distinct(eta),length(eta) as dim ");
    		sql.append("	from vivai_t_invent_piante where eta is not null  ");
    		sql.append(" 	order by dim, eta");
    		sql.append(")as foo");
    		return jdbcTemplate.queryForList(sql.toString(),String.class);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


}
