/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioFruttiDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.InventarioFruttiRiepilogoMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.PartitaFruttiMapper;
import it.csi.vifo.vifoapi.dto.InventarioFilter;
import it.csi.vifo.vifoapi.dto.InventarioFrutti;
import it.csi.vifo.vifoapi.dto.InventarioFruttiRiepilogo;
import it.csi.vifo.vifoapi.dto.PartitaFrutti;
import it.csi.vifo.vifoapi.util.DateUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;
import it.csi.vifo.vifoapi.util.PaginatedList;

@Component
public class InventarioFruttiDAOImpl extends GenericDAO implements InventarioFruttiDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	private static final String sqlRiepilogoFrutti = " select *, nome_comune as specie, nome_scientifico as specie_latino,"
			+ "descr_categ_materiale as descr_categoria_materialie "
			+ "from v_inventario_frutti";
	
//	private String getSqlListInventarioFrutti(String sort, String filter) {
//		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
//		try {			
//	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
//			StringBuilder sql = new StringBuilder("select * from ");
//			sql.append(" (");
//			sql.append(sqlRiepilogoFrutti);
//			sql.append(") as foo "); 
//			if(filter != null && filter.length() > 0) {
//				sql.append(" where upper(specie) like ? ");
//			}
//			sql.append(getSqlSort(sort, "-id_inventario_frutti"));
//			return sql.toString();
//		}finally {
//			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
//		}
//	}
	
	@Override
	public List<InventarioFruttiRiepilogo> getListInventarioFrutti(String sort) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        StringBuilder sql = new StringBuilder(sqlRiepilogoFrutti);
	        sql.append(getSqlSort(sort, "-id_inventario_frutti"));
			return jdbcTemplate.query(sql.toString(), new InventarioFruttiRiepilogoMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public PaginatedList<InventarioFruttiRiepilogo> getListInventarioFrutti(int page, int limit, String sort,InventarioFilter filter) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        StringBuilder sql = new StringBuilder(sqlRiepilogoFrutti);
	        List<Object> params = new ArrayList<Object>();
	        sql.append(" where 1 = 1 ");
			if(filter.getNumeroPartita() != 0) {
				sql.append(" and id_inventario_frutti = ?");
				params.add(filter.getNumeroPartita());

			}
			if(filter.getSpecie() != null) {
				sql.append(" and (upper(nome_comune) like ? or upper(nome_scientifico) like ?)");
				String specie = "%" + filter.getSpecie().toUpperCase() + "%";
				params.add(specie);
				params.add(specie);
			}
			if(filter.getAnnata() > 0) {
				sql.append(" and data_carico between TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY') ");
				params.add("01/09/" + (filter.getAnnata()-1));
				params.add("31/08/" + (filter.getAnnata()));
			}
			if(filter.isAttive()) {
				sql.append(" and qta_residua > 0 ");
			}
			if(sort == null) {
				sql.append(" order by specie asc, id_inventario_frutti desc ");
			}else {
				sql.append(getSqlSort(sort, "-id_inventario_frutti"));
			}
			return super.paginatedList(sql.toString(), params.toArray(), new InventarioFruttiRiepilogoMapper(), page, limit);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public InventarioFruttiRiepilogo getInventarioFruttiRiepilogoById(int id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder(sqlRiepilogoFrutti);
			sql.append(" where id_inventario_frutti = ?");
			List<InventarioFruttiRiepilogo> res = jdbcTemplate.query(sql.toString(), new InventarioFruttiRiepilogoMapper(), id);
			if(res != null && res.size() > 0) {
				return res.get(0);
			}
			return null;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int insert(InventarioFrutti inventario) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_invent_frutti "); 
			sql.append("(id_inventario_frutti, qta_carico_kg, qta_scarico_vendita_kg, qta_distruzione_kg) "); 
			sql.append("VALUES(?, ?, ?, ?)");
	
			List<Object> parameters = new ArrayList<>();
			parameters.add(inventario.getIdInventarioFrutti());
			parameters.add(inventario.getQtaCaricoKg());		
			parameters.add(inventario.getQtaScaricoVenditaKg());
			parameters.add(inventario.getQtaDistruzioneKg());
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int update(InventarioFrutti inventario) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_invent_frutti "); 
			sql.append("SET qta_carico_kg= ?, qta_scarico_vendita_kg= ?, qta_distruzione_kg= ? ");
			sql.append("WHERE id_inventario_frutti= ?");
			
			List<Object> parameters = new ArrayList<>();
			parameters.add(inventario.getQtaCaricoKg());
			parameters.add(inventario.getQtaScaricoVenditaKg());
			parameters.add(inventario.getQtaDistruzioneKg());
			parameters.add(inventario.getIdInventarioFrutti());
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PartitaFrutti getPartitaFruttaById(Integer id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select inv.id_inventario, prov.id_provenienza, certaltro.codice, prov.fk_specie,"); 
			sql.append("COALESCE(certaltro.data_certificato,certpiem.data_certificato ) as data_certificato,"); 
			sql.append("prov.provenienza, prov.fk_categ_materiale, prov.produttore, inv.data_carico, invfru.qta_carico_kg, ");
			sql.append("invfru.qta_distruzione_kg, invfru.qta_scarico_vendita_kg,");
			sql.append("case WHEN certpiem.id_provenienza is not null THEN 'C' "); 
			sql.append("     WHEN certaltro.id_provenienza is not null THEN 'L' ");
			sql.append("     WHEN prov.flg_no_fini_forestali = '1' THEN 'N' "); 
			sql.append("     WHEN prov.flg_no_fini_forestali = 0 THEN 'A' "); 
			sql.append("     ELSE null "); 
			sql.append("end as certificato_type, invsem.fk_inventario_frutti ");
			sql.append("from vivai_t_invent_frutti invfru "); 
			sql.append("inner join vivai_t_inventario inv on inv.id_inventario = invfru.id_inventario_frutti "); 
			sql.append("inner join vivai_t_provenienza prov on inv.fk_provenienza = prov.id_provenienza "); 
			sql.append("left join vivai_t_cert_proven_altro certaltro on certaltro.id_provenienza = prov.id_provenienza "); 
			sql.append("left join vivai_t_cert_proven_piem certpiem on certpiem.id_provenienza = prov.id_provenienza "); 
			sql.append("left join (select distinct fk_inventario_frutti from vivai_t_invent_semi) invsem on invsem.fk_inventario_frutti = invfru.id_inventario_frutti ");
			sql.append("where inv.id_inventario = ?");
			
			return jdbcTemplate.queryForObject(sql.toString(), new PartitaFruttiMapper(), id);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
		

	@Override
	public int deletePartitaFruttaById(Integer id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "delete from vivai_t_invent_frutti where id_inventario_frutti = ?";
			return jdbcTemplate.update(sql,id);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Integer[] getRangeAnniInventario() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        String sql = "select min(data_carico) as data_from, max(data_carico) as data_to from v_inventario_frutti";
	        return jdbcTemplate.query(sql, new ResultSetExtractor<Integer[]>()
	        {
		    	@Override
				public Integer[] extractData(ResultSet rs) throws SQLException {
		    		Integer[] res = new Integer[2];
		    		while(rs.next()) {
		    			res[0] = DateUtil.getYearInventari(rs.getDate("data_from"));
		    			res[1] = DateUtil.getYearInventari(rs.getDate("data_to"));
		    		}
					return res ;
				}
	        });
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
