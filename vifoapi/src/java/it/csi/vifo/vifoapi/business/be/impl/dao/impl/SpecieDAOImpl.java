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

import it.csi.vifo.vifoapi.business.be.impl.dao.SpecieDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.DomandaRiepilogoMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.SpecieMapper;
import it.csi.vifo.vifoapi.dto.Specie;
import it.csi.vifo.vifoapi.dto.SpecieResultSearch;
import it.csi.vifo.vifoapi.util.AIKeyHolderUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;
import it.csi.vifo.vifoapi.util.PaginatedList;

@Component
public class SpecieDAOImpl extends GenericDAO implements SpecieDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	private static String SQL_SPECIE = "select s.*,g.descr_gruppo, att.attitudine, foto.tipi_foto  " +
			"from vivai_t_specie s " + 
			"inner join vivai_d_gruppo g on s.fk_gruppo = g.id_gruppo " + 
			"left join (select id_specie, STRING_AGG(LEFT(descr_attitudine, 1) ,' - ') as attitudine "+
			"	from vivai_r_att_specie spAtt " + 
			"	inner join vivai_d_attitudine att on spAtt.id_attitudine = att.id_attitudine " + 
			"	group by spatt.id_specie) att on att.id_specie = s.id_specie " + 
			"left join (select  fk_specie, STRING_AGG(''||fk_tipo_foto,', ') as tipi_foto from vivai_t_foto_specie " + 
			"	where uid_index is not null group by fk_specie ) foto on foto.fk_specie = s.id_specie ";

	@Override
	public List<Specie> getAllSpecie(boolean all) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder( SQL_SPECIE );
			if(!all) {
				sql.append("where flg_specie_pubblicabile = 1 ");
			}
			sql.append( "order by s.nome_comune");
			return jdbcTemplate.query(sql.toString(), new SpecieMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<Specie> getAllSpecieByGroup(boolean all) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder( SQL_SPECIE );
			if(!all) {
				sql.append("where flg_specie_pubblicabile = 1 ");
			}
			sql.append( "order by g.descr_gruppo , s.nome_comune");
			return jdbcTemplate.query(sql.toString(), new SpecieMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PaginatedList<Specie> getPaginatedListSpecie(boolean all, int page, int limit, String sort, String filter) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder(SQL_SPECIE);
			sql.append("where 1=1 "); 
			List<Object> parameters = new ArrayList<>();
			
			if(!all) {
				sql.append("and flg_specie_pubblicabile = 1 ");
			}
			
			if(filter != null && filter.length() > 0) {
				filter = "%" + filter.toUpperCase() + "%";
				sql.append("and upper(nome_comune) like ? or  upper(nome_scientifico) like ? ");
				parameters.add(filter);
				parameters.add(filter);
			}
			sort = orderMapping(sort);
			sql.append(super.getSqlSort(sort,"+nome_comune"));
			
			return super.paginatedList(sql.toString(), parameters, new SpecieMapper(), page, limit);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	
	}

	@Override
	public Specie getSpecieById(Integer idSpecie) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = SQL_SPECIE  + " where s.id_specie = ? ";
			return jdbcTemplate.queryForObject(sql, new SpecieMapper(), idSpecie);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int save(Specie specie) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_specie " ); 
			sql.append("(codice_specie, nome_comune, nome_scientifico, flg_prodotta, flg_specie_zp, ");
			sql.append("flg_specie_386, flg_tipo_controllo, densita, famiglia, zona_origine, dimensione, foglia, ");
			sql.append("fiore_frutto, ecologia, impiego, note_caratteristiche, flg_scheda_pubblicabile, ");
			sql.append("flg_specie_pubblicabile, data_inserimento, fk_config_utente, fk_gruppo) "); 
			sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(specie.getCodiceSpecie());
			parameters.add(specie.getNomeComune());
			parameters.add(specie.getNomeScientifico());
			parameters.add(specie.isFlgProdotta()?1:0);
			parameters.add(specie.isFlgSpecieZp()?1:0);
			parameters.add(specie.isFlgSpecie386()?1:0);
			parameters.add(specie.getFlgTipoControllo());
			parameters.add(specie.getDensita());
			parameters.add(specie.getFamiglia());
			parameters.add(specie.getZonaOrigine());
			parameters.add(specie.getDimensione());
			parameters.add(specie.getFoglia());
			parameters.add(specie.getFioreFrutto());
			parameters.add(specie.getEcologia());
			parameters.add(specie.getImpiego());
			parameters.add(specie.getNoteCaratteristiche());
			parameters.add(specie.isFlgSchedaPubblicabile()?1:0);
			parameters.add(specie.isFlgSpeciePubblicabile()?1:0);
			parameters.add(now);
			parameters.add(specie.getFkConfigUtente());
			parameters.add(specie.getFkGruppo());
			
			return AIKeyHolderUtil.updateWithGenKey("id_specie", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int update(Specie specie) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_specie ");
			sql.append("SET codice_specie  = ?, nome_comune  = ?, nome_scientifico  = ?, flg_prodotta  = ?, flg_specie_zp  = ?, ");
			sql.append("flg_specie_386  = ?, flg_tipo_controllo  = ?, densita  = ?, famiglia  = ?, zona_origine  = ?, ");
			sql.append("dimensione  = ?, foglia  = ?, fiore_frutto  = ?, ecologia  = ?, impiego  = ?, note_caratteristiche  = ?, ");
			sql.append("flg_scheda_pubblicabile  = ?, flg_specie_pubblicabile  = ?,");
			sql.append("data_aggiornamento  = ?, fk_config_utente  = ?, fk_gruppo  = ?");
			sql.append("WHERE id_specie = ? " );
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(specie.getCodiceSpecie());
			parameters.add(specie.getNomeComune());
			parameters.add(specie.getNomeScientifico());
			parameters.add(specie.isFlgProdotta()?1:0);
			parameters.add(specie.isFlgSpecieZp()?1:0);
			parameters.add(specie.isFlgSpecie386()?1:0);
			parameters.add(specie.getFlgTipoControllo());
			parameters.add(specie.getDensita());
			parameters.add(specie.getFamiglia());
			parameters.add(specie.getZonaOrigine());
			parameters.add(specie.getDimensione());
			parameters.add(specie.getFoglia());
			parameters.add(specie.getFioreFrutto());
			parameters.add(specie.getEcologia());
			parameters.add(specie.getImpiego());
			parameters.add(specie.getNoteCaratteristiche());
			parameters.add(specie.isFlgSchedaPubblicabile()?1:0);
			parameters.add(specie.isFlgSpeciePubblicabile()?1:0);
			parameters.add(now);
			parameters.add(specie.getFkConfigUtente());
			parameters.add(specie.getFkGruppo());
			parameters.add(specie.getIdSpecie());
		
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<String> getListFamiglie() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select distinct famiglia from vivai_t_specie where famiglia is not null order by famiglia";
			return jdbcTemplate.queryForList(sql, String.class);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private String orderMapping(String value) {
		if(value != null) {
			value = value.replace("gruppoDescr", "descr_gruppo");
			value = value.replace("flgSpecie386", "flg_specie_386");
		}
		return value;
	}
}
