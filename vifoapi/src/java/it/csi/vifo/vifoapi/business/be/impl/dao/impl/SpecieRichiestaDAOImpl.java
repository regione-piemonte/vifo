/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.SpecieRichiestaDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.PdfAttitudineMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.SpecieRichiestaMapper;
import it.csi.vifo.vifoapi.dto.PdfAttitudine;
import it.csi.vifo.vifoapi.dto.SpecieRichiesta;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class SpecieRichiestaDAOImpl extends GenericDAO implements SpecieRichiestaDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public void insert(SpecieRichiesta item) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_r_domanda_specie(");
			sql.append("id_domanda, id_specie, qta_richiesta,data_inserimento,fk_config_utente)");
			sql.append(" VALUES(?,?,?,?,?)");
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			jdbcTemplate.update(sql.toString(), new Object[] {item.getIdDomanda(),item.getIdSpecie(),
					item.getQtaRichiesta(),now,item.getFkConfigUtente()});
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<SpecieRichiesta> getByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select id_domanda,ds.id_specie,qta_richiesta,nome_comune,nome_scientifico,ds.fk_config_utente "
					+ "from vivai_r_domanda_specie ds "
					+ "inner join vivai_t_specie sp on ds.id_specie = sp.id_specie "
					+ "where id_domanda = ? ";
			
			return jdbcTemplate.query(sql, new SpecieRichiestaMapper(), idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int deleteByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "delete from vivai_r_domanda_specie where id_domanda = ?";
			return jdbcTemplate.update(sql,idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<PdfAttitudine> getAttitudiniByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select sp.nome_comune as nome_specie, sp.nome_scientifico as specie_latino, max(invp.altezza) as max_h, min(invp.altezza) as min_h,  ");
			sql.append("string_agg(DISTINCT  att.descr_attitudine ,', ' order by att.descr_attitudine) as attitudine ");
			sql.append("from vivai_r_domanda_specie ds ");
			sql.append("inner join vivai_t_specie sp on ds.id_specie = sp.id_specie ");
			sql.append("inner join vivai_t_provenienza prov on prov.fk_specie = sp.id_specie 	 ");
			sql.append("inner join vivai_t_inventario inv on prov.id_provenienza = inv.fk_provenienza  	 ");
			sql.append("inner join vivai_t_invent_piante invp on invp.id_inventario_piante = inv.id_inventario  ");
			sql.append("inner join vivai_r_att_specie ratt on ratt.id_specie = sp.id_specie  ");
			sql.append("inner join vivai_d_attitudine att on att.id_attitudine = ratt.id_attitudine  ");
			sql.append("where id_domanda = ? ");
			sql.append("group by sp.id_specie order by sp.nome_comune ");
			return jdbcTemplate.query(sql.toString(), new PdfAttitudineMapper(), idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
