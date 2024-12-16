/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioPiantineDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.InfoNuovaAnnataInventarioMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.InventarioPiantineRiepilogoMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.PartitaPiantineMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.RichiestaTrasferimentoPianteMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.TrasferimentoPartitaMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.XlsCaricoScaricoMapper;
import it.csi.vifo.vifoapi.dto.InfoNuovaAnnataInventario;
import it.csi.vifo.vifoapi.dto.InventarioFilter;
import it.csi.vifo.vifoapi.dto.InventarioPiantine;
import it.csi.vifo.vifoapi.dto.InventarioPiantineRiepilogo;
import it.csi.vifo.vifoapi.dto.PartitaPiante;
import it.csi.vifo.vifoapi.dto.RichiestaTrasferimentoPiante;
import it.csi.vifo.vifoapi.dto.TrasferimentoPartita;
import it.csi.vifo.vifoapi.dto.XlsCaricoScarico;
import it.csi.vifo.vifoapi.util.DateUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;
import it.csi.vifo.vifoapi.util.PaginatedList;

@Component
public class InventarioPiantineDAOImpl extends GenericDAO implements InventarioPiantineDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	private String getSqlListInventarioPiantine() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select  v.*, v_disp.qta_bloccata, nome_comune as specie, nome_scientifico as specie_latino, inv.posizione_partita  ");
			sql.append("from v_inventario_piante v ");
			sql.append("inner join v_disponibilita_piante v_disp on v_disp.id_inventario_piante = v.id_inventario_piante ");
			sql.append("inner join vivai_t_invent_piante inv on v.id_inventario_piante = inv.id_inventario_piante ");
			sql.append("where inv.fk_vivaio = ?");
			sql.append(" and (inv.flg_tipo_trasferimento is null or inv.flg_valid_trasf = 'S') ");
			return sql.toString();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<InventarioPiantineRiepilogo> getListInventarioPiantine(String sort,int idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = getSqlListInventarioPiantine();
			sql+= getSqlSort(sort, "-id_inventario_piante");
			return jdbcTemplate.query(sql, new InventarioPiantineRiepilogoMapper(), idVivaio);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName)); 
		}
	}

	@Override
	public PaginatedList<InventarioPiantineRiepilogo> getListInventarioPiantine(int page, int limit,String sort, InventarioFilter filter) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder(getSqlListInventarioPiantine());
			List<Object> params = new ArrayList<>();
			params.add(filter.getIdVivaio());

			if(filter.getNumeroPartita() != 0) {
				sql.append(" and v.id_inventario_piante = ? ");
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
				sql.append(" and (coalesce(v.qta_disponibile,0) + coalesce(v.qta_non_assegnabile,0)) > 0 ");
			}
			if(sort == null) {
				sql.append(" order by specie asc, id_inventario_piante desc ");
			}else {
				sql.append(getSqlSort(sort, "-id_inventario_piante"));
			}
	
			return super.paginatedList(sql.toString(), params.toArray(), new InventarioPiantineRiepilogoMapper(), page, limit);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public InventarioPiantineRiepilogo getRiepilogoPiantineById(int id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select v.*,  v_disp.qta_bloccata, nome_comune as specie, nome_scientifico as specie_latino, inv.posizione_partita  ");
			sql.append("from v_inventario_piante v ");
			sql.append("inner join v_disponibilita_piante v_disp on v_disp.id_inventario_piante = v.id_inventario_piante ");
			sql.append("inner join vivai_t_invent_piante inv on v.id_inventario_piante = inv.id_inventario_piante ");
			sql.append("where v.id_inventario_piante = ? ");
			return queryForObject(sql.toString(), new InventarioPiantineRiepilogoMapper(), id);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int insert(InventarioPiantine inventarioPiantine) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_invent_piante "); 
			sql.append("(id_inventario_piante, fk_inventario_piante, fk_inventario_semi,");
			sql.append("qta_scarico_semi_kg, eta, altezza, fk_tipo_allevamento, anno_radicazione_seme,");
			sql.append("prezzo_unitario, posizione_partita, qta_carico, qta_non_assegnabile, ");
			sql.append("qta_distruzione, flg_tipo_trasferimento, fk_vivaio, motivo_trasferimento,fk_config_utente_valid_trasf) "); 
			sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)");
	
			List<Object> parameters = new ArrayList<>();
			parameters.add(inventarioPiantine.getIdInventarioPiante());
			parameters.add(inventarioPiantine.getFkInventarioPiante());
			parameters.add(inventarioPiantine.getFkInventarioSemi());
			parameters.add(inventarioPiantine.getQtaScaricoSemiKg());
			parameters.add(inventarioPiantine.getEta());
			parameters.add(inventarioPiantine.getAltezza());
			parameters.add(inventarioPiantine.getFkTipoAllevamento());
			parameters.add(inventarioPiantine.getAnnoRadicazioneSeme());
			parameters.add(inventarioPiantine.getPrezzoUnitario());
			parameters.add(inventarioPiantine.getPosizionePartita());
			parameters.add(inventarioPiantine.getQtaCarico());
			parameters.add(inventarioPiantine.getQtaNonAssegnabile());
			parameters.add(inventarioPiantine.getQtaDistruzione());
			parameters.add(inventarioPiantine.getFlgTipoTrasferimento());
			parameters.add(inventarioPiantine.getFkVivaio());
			parameters.add(inventarioPiantine.getMotivoTrasferimento());
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int update(InventarioPiantine inventarioPiantine) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_invent_piante "); 
			sql.append("SET qta_scarico_semi_kg= ?, eta= ?, altezza= ?, fk_tipo_allevamento= ?, ");
			sql.append("anno_radicazione_seme= ?, prezzo_unitario= ?, posizione_partita= ?, qta_carico= ?, ");
			sql.append("qta_non_assegnabile= ?, qta_distruzione= ?, fk_vivaio= ? ");
		
			List<Object> parameters = new ArrayList<>();
			parameters.add(inventarioPiantine.getQtaScaricoSemiKg());
			parameters.add(inventarioPiantine.getEta());
			parameters.add(inventarioPiantine.getAltezza());
			parameters.add(inventarioPiantine.getFkTipoAllevamento());
			parameters.add(inventarioPiantine.getAnnoRadicazioneSeme());
			parameters.add(inventarioPiantine.getPrezzoUnitario());
			parameters.add(inventarioPiantine.getPosizionePartita());
			parameters.add(inventarioPiantine.getQtaCarico());
			parameters.add(inventarioPiantine.getQtaNonAssegnabile());
			parameters.add(inventarioPiantine.getQtaDistruzione());
			parameters.add(inventarioPiantine.getFkVivaio());
			
			//= ?, = ?, = ?, = ?, ");
			
			if(inventarioPiantine.getFlgTipoTrasferimento() != null) {
				sql.append(", flg_tipo_trasferimento = ? ");
				parameters.add(inventarioPiantine.getFlgTipoTrasferimento());
			}
			if(inventarioPiantine.getMotivoTrasferimento() != null) {
				sql.append(", motivo_trasferimento = ? ");
				parameters.add(inventarioPiantine.getMotivoTrasferimento());
			}
			if(inventarioPiantine.getFlgValidTrasf() != null) {
				sql.append(", flg_valid_trasf = ? ");
				parameters.add(inventarioPiantine.getFlgValidTrasf());
			}
			if(inventarioPiantine.getDataValidTrasf() != null) {
				sql.append(", data_valid_trasf = ? ");
				parameters.add(inventarioPiantine.getDataValidTrasf());
			}
			if(inventarioPiantine.getNoteValidTrasf() != null) {
				sql.append(", note_valid_trasf = ? ");
				parameters.add(inventarioPiantine.getNoteValidTrasf());
			}
			
			sql.append("WHERE id_inventario_piante= ?");
			parameters.add(inventarioPiantine.getIdInventarioPiante());
			
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	@Override
	public int updateQuantitaNonAssegnabile(InventarioPiantine inventarioPiantine) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_invent_piante ");
			sql.append("SET qta_non_assegnabile= ? ");
			sql.append("WHERE id_inventario_piante= ?");


			List<Object> parameters = new ArrayList<>();
			parameters.add(inventarioPiantine.getQtaNonAssegnabile());
			parameters.add(inventarioPiantine.getIdInventarioPiante());



			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	@Override
	public PartitaPiante getPartitaPiantineById(Integer id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select inv.id_inventario, invpia.fk_inventario_piante ,invpia.fk_inventario_semi, ");
			sql.append("prov.id_provenienza, certaltro.codice, prov.fk_specie, invpia.flg_tipo_trasferimento, ");
			sql.append("COALESCE(certaltro.data_certificato,certpiem.data_certificato ) as data_certificato, ");
			sql.append("prov.provenienza, prov.fk_categ_materiale, prov.produttore, inv.data_carico, "); 
			sql.append("invpia.altezza, invpia.anno_radicazione_seme, invpia.eta, invpia.fk_vivaio,");
			sql.append(" invpia.fk_tipo_allevamento, invpia.posizione_partita ,invpia.prezzo_unitario ,");
			sql.append("invpia.qta_carico ,invpia.qta_distruzione  "); 
			sql.append(",invpia.qta_non_assegnabile ,invpia.qta_scarico_semi_kg , "); 
			sql.append("case WHEN certpiem.id_provenienza is not null THEN 'C'  "); 
			sql.append("     WHEN certaltro.id_provenienza is not null THEN 'L'  "); 
			sql.append("     WHEN prov.flg_no_fini_forestali = '1' THEN 'N'  "); 
			sql.append("     WHEN prov.flg_no_fini_forestali = 0 THEN 'A'  "); 
			sql.append("     ELSE null  "); 
			sql.append("end as certificato_type, pianterel.fk_inventario_piante as child ");
			sql.append(", (select count(fk_inventario) from vivai_t_assegnata where fk_inventario = inv.id_inventario) as num_assegnazioni ");
			sql.append("from vivai_t_invent_piante invpia  ");
			sql.append("inner join vivai_t_inventario inv on inv.id_inventario = invpia.id_inventario_piante  "); 
			sql.append("inner join vivai_t_provenienza prov on inv.fk_provenienza = prov.id_provenienza  "); 
			sql.append("left join vivai_t_cert_proven_altro certaltro on certaltro.id_provenienza = prov.id_provenienza  "); 
			sql.append("left join vivai_t_cert_proven_piem certpiem on certpiem.id_provenienza = prov.id_provenienza  "); 
			sql.append("left join (select distinct fk_inventario_piante from vivai_t_invent_piante) pianterel ");
			sql.append("on pianterel.fk_inventario_piante = invpia.id_inventario_piante  "); 
			sql.append("where inv.id_inventario = ? ");
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName," sql: " + sql.toString() + " -- id_inventario = " + id));
			return jdbcTemplate.queryForObject(sql.toString(), new PartitaPiantineMapper(), id);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<PartitaPiante> getListPartitaPiantineDisponibili(Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select inv.id_inventario, invpia.fk_inventario_piante ,invpia.fk_inventario_semi, ");
			sql.append("prov.id_provenienza, certaltro.codice, prov.fk_specie, invpia.flg_tipo_trasferimento, ");
			sql.append("COALESCE(certaltro.data_certificato,certpiem.data_certificato ) as data_certificato, ");
			sql.append("prov.provenienza, prov.fk_categ_materiale, prov.produttore, inv.data_carico,  ");
			sql.append("invpia.altezza, invpia.anno_radicazione_seme, invpia.eta, invpia.fk_vivaio,");
			sql.append(" invpia.fk_tipo_allevamento, invpia.posizione_partita ,invpia.prezzo_unitario ,");
			sql.append(" (coalesce(vd.qta_disponibile,0) + coalesce(invpia.qta_non_assegnabile,0)) as qta_carico ,invpia.qta_distruzione   ");
			sql.append(",invpia.qta_non_assegnabile , invpia.qta_scarico_semi_kg ,  ");
			sql.append("case WHEN certpiem.id_provenienza is not null THEN 'C'   ");
			sql.append("     WHEN certaltro.id_provenienza is not null THEN 'L'   ");
			sql.append("     WHEN prov.flg_no_fini_forestali = '1' THEN 'N'   ");
			sql.append("     WHEN prov.flg_no_fini_forestali = 0 THEN 'A'   ");
			sql.append("     ELSE null   ");
			sql.append("end as certificato_type, pianterel.fk_inventario_piante as child ");
			sql.append(", 0 as num_assegnazioni ");
			sql.append("from vivai_t_invent_piante invpia  ");
			sql.append("inner join v_disponibilita_piante vd on vd.id_inventario_piante = invpia.id_inventario_piante ");
			sql.append("inner join vivai_t_inventario inv on inv.id_inventario = invpia.id_inventario_piante   ");
			sql.append("inner join vivai_t_provenienza prov on inv.fk_provenienza = prov.id_provenienza   ");
			sql.append("left join vivai_t_cert_proven_altro certaltro on certaltro.id_provenienza = prov.id_provenienza   ");
			sql.append("left join vivai_t_cert_proven_piem certpiem on certpiem.id_provenienza = prov.id_provenienza   ");
			sql.append("left join (select distinct fk_inventario_piante from vivai_t_invent_piante) pianterel ");
			sql.append("on pianterel.fk_inventario_piante = invpia.id_inventario_piante   ");
			sql.append("where invpia.fk_vivaio = ? and (coalesce(vd.qta_disponibile,0) + coalesce(invpia.qta_non_assegnabile,0)) > 0 ");
			sql.append("and (invpia.flg_tipo_trasferimento is null or invpia.flg_valid_trasf = 'S') ");
			return jdbcTemplate.query(sql.toString(), new PartitaPiantineMapper(), idVivaio);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public int deletePartitaPianteById(Integer id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "delete from vivai_t_invent_piante where id_inventario_piante = ?";
			return jdbcTemplate.update(sql,id);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PaginatedList<TrasferimentoPartita> getListTrasferimentoPiantine(int page, int limit, String sort, Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select * from (");
			sql.append("select invp1.id_inventario_piante as id_partita, invp2.id_inventario_piante as fk_partita_orig, "); 
			sql.append("specie.nome_comune as specie,specie.nome_scientifico as specie_latino, viv1.nome as vivaio_destinazione,viv2.nome as vivaio_origine, "); 
			sql.append("viv1.id_vivaio as fk_vivaio_destinazione,viv2.id_vivaio as fk_vivaio_origine, "); 
			sql.append("inv.data_inserimento as data_ins, invp1.qta_carico, invp1.flg_tipo_trasferimento, invp1.flg_valid_trasf, "); 
			sql.append("confute.id_config_utente, confute.fk_vivaio as vivaio_utente "); 
			sql.append("from vivai_t_invent_piante invp1 "); 
			sql.append("inner join vivai_t_inventario inv on inv.id_inventario = invp1.id_inventario_piante "); 
			sql.append("inner join vivai_t_provenienza prov on prov.id_provenienza = inv.fk_provenienza  "); 
			sql.append("inner join vivai_t_specie specie on prov.fk_specie = specie.id_specie  "); 
			sql.append("inner join vivai_t_vivaio viv1 on invp1.fk_vivaio = viv1.id_vivaio "); 
			sql.append("inner join vivai_t_invent_piante invp2 on invp1.fk_inventario_piante = invp2.id_inventario_piante  "); 
			sql.append("inner join vivai_t_vivaio viv2 on invp2.fk_vivaio = viv2.id_vivaio  "); 
			sql.append("inner join vivai_cnf_config_utente confute on confute.id_config_utente = inv.fk_config_utente  "); 
			sql.append("where (invp1.flg_tipo_trasferimento = 'R' or invp1.flg_tipo_trasferimento = 'T') "); 
			sql.append("and invp1.flg_valid_trasf is null ");
			if(idVivaio != null && idVivaio > 0) {
				sql.append("and ( viv2.id_vivaio = ? or viv1.id_vivaio = ?)) as foo ");
				sql.append(getSqlSort(sort, "-id_partita"));
				List<Object> parameters = new ArrayList<>();
				parameters.add(idVivaio);
				parameters.add(idVivaio);
				return super.paginatedList(sql.toString(), parameters, new TrasferimentoPartitaMapper(), page, limit);
			}
			sql.append(") as foo ");
			sql.append(getSqlSort(sort, "-id_partita"));
			return super.paginatedList(sql.toString(), new TrasferimentoPartitaMapper(), page, limit);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}

	}

	@Override
	public int getCountTrasferimentoPiantine(Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from vivai_t_invent_piante invp1 ");
			sql.append("inner join vivai_t_invent_piante invp2 on invp1.fk_inventario_piante = invp2.id_inventario_piante  "); 
			sql.append("where (invp1.flg_tipo_trasferimento = 'R' or invp1.flg_tipo_trasferimento = 'T') ");
			sql.append("and invp1.flg_valid_trasf is null ");
			if(idVivaio != null && idVivaio > 0) {
				sql.append("and (invp2.fk_vivaio = ? or invp1.fk_vivaio = ?) ");
				List<Object> parameters = new ArrayList<>();
				parameters.add(idVivaio);
				parameters.add(idVivaio);
				return jdbcTemplate.queryForObject(sql.toString(), parameters.toArray(), Integer.class);
			}
			return jdbcTemplate.queryForObject(sql.toString(), Integer.class);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public RichiestaTrasferimentoPiante getRichiestaTrasferimentoById(Integer id) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select invp1.id_inventario_piante, invp1.fk_inventario_piante,");
			sql.append("specie.id_specie,specie.nome_comune as specie_descr, nome_scientifico as specie_latino, ");
			sql.append("viv2.id_vivaio as fk_vivaio_from, viv2.nome as vivaio_from_name,");
			sql.append("viv1.id_vivaio as fk_vivaio_to, viv1.nome as vivaio_to_name,");
			sql.append("sog.nome, sog.cognome, sog.ragione_sociale, inv.data_inserimento as data_ins, invp1.qta_carico,");
			sql.append("invp1.motivo_trasferimento, invp1.flg_tipo_trasferimento ");
			sql.append("from vivai_t_invent_piante invp1 ");
			sql.append("inner join vivai_t_inventario inv on inv.id_inventario = invp1.id_inventario_piante ");
			sql.append("inner join vivai_t_provenienza prov on prov.id_provenienza = inv.fk_provenienza ");
			sql.append("inner join vivai_t_specie specie on prov.fk_specie = specie.id_specie  ");
			sql.append("inner join vivai_t_vivaio viv1 on invp1.fk_vivaio = viv1.id_vivaio ");
			sql.append("inner join vivai_t_invent_piante invp2 on invp1.fk_inventario_piante = invp2.id_inventario_piante ");  
			sql.append("inner join vivai_t_vivaio viv2 on invp2.fk_vivaio = viv2.id_vivaio ");
			sql.append("inner join vivai_cnf_config_utente confute on confute.id_config_utente = inv.fk_config_utente ");
			sql.append("inner join vivai_t_soggetto sog on sog.id_soggetto = confute.fk_soggetto ");
			sql.append("where invp1.id_inventario_piante = ? and viv2.id_vivaio <> viv1.id_vivaio");
			List<RichiestaTrasferimentoPiante> res = jdbcTemplate.query(sql.toString(), new RichiestaTrasferimentoPianteMapper(), id);
			return res.size() == 0?null:res.get(0);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int accettaRifiutaTrasferimento(RichiestaTrasferimentoPiante richiesta) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_invent_piante ");
			sql.append("set flg_valid_trasf = ?, ");
			sql.append("data_valid_trasf = ?, ");
			sql.append("fk_config_utente_valid_trasf = ?, ");
			sql.append("note_valid_trasf = ? ");
			sql.append("where id_inventario_piante = ? ");
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(richiesta.getFlgValid());
			parameters.add(now);
			parameters.add(richiesta.getFkConfigUtenteValidTrasf());
			parameters.add(richiesta.getNoteValidInvalidTrasf());
			parameters.add(richiesta.getIdInventario());
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public InfoNuovaAnnataInventario getInfoAnnataInventario(Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select id_vivaio, nome_vivaio, count(*) as totali, sum(validi) as disponibili from  ");
			sql.append("(select id_vivaio, viv.nome as nome_vivaio, ");
			sql.append("case when (coalesce(v.qta_disponibile,0) + coalesce(v.qta_non_assegnabile,0)) > 0 then 1 ");
			sql.append("when (coalesce(v.qta_disponibile,0) + coalesce(v.qta_non_assegnabile,0)) <= 0 then 0 ");
			sql.append("end as validi ");
			sql.append("from v_inventario_piante v ");
			sql.append("inner join vivai_t_invent_piante inv on v.id_inventario_piante = inv.id_inventario_piante ");
//			sql.append("inner join v_disponibilita_piante vd on vd.id_inventario_piante = invpia.id_inventario_piante ");
			sql.append("inner join vivai_t_vivaio viv on viv.id_vivaio = inv.fk_vivaio ");
			sql.append("where inv.fk_vivaio = ? and (inv.flg_tipo_trasferimento is null or inv.flg_valid_trasf = 'S') ) as tab ");
			sql.append("group by id_vivaio, nome_vivaio ");
			return jdbcTemplate.queryForObject(sql.toString(), new InfoNuovaAnnataInventarioMapper(), idVivaio);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<XlsCaricoScarico> getListCaricoScarico(Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select * from  ");
			sql.append("(select 1 as tipo, inv.fk_vivaio, vip.id_inventario_piante, vip.data_carico as data_movimento, ");
			sql.append("vip.nome_comune as specie, nome_scientifico as specie_latino, vip.provenienza, vip.certificato_provenienza,  ");
			sql.append(" vip.qta_carico, null as qta_ritirata, null as id_domanda,  ");
			sql.append("	null as numero_ddt, null as tipo_domanda, null as richiedente, inv.flg_tipo_trasferimento, inv.flg_valid_trasf ");
			sql.append("from v_inventario_piante vip ");
			sql.append("inner join vivai_t_invent_piante inv on vip.id_inventario_piante = inv.id_inventario_piante ");
			sql.append("union all ");
			sql.append("select 2 as tipo, inv.fk_vivaio, vip.id_inventario_piante, ass.data_ritiro as data_movimento, ");
			sql.append("vip.nome_comune as specie, nome_scientifico as specie_latino, vip.provenienza, vip.certificato_provenienza,  ");
			sql.append(" null as qta_carico,  ass.qta_ritirata, dom.id_domanda,  ");
			sql.append("	 ddt.numero_ddt, tdom.descr_tipo_domanda as tipo_domanda,  ");
			sql.append("	CASE WHEN sog.ragione_sociale is not null and length(sog.ragione_sociale) > 0 ");
			sql.append("	 	THEN sog.ragione_sociale ");
			sql.append("	    ELSE concat(sog.cognome,' ',sog.nome) ");
			sql.append("	END AS richiedente, inv.flg_tipo_trasferimento, inv.flg_valid_trasf ");
			sql.append("from v_inventario_piante vip  ");
			sql.append("inner join vivai_t_invent_piante inv on vip.id_inventario_piante = inv.id_inventario_piante ");
			sql.append("inner join vivai_t_assegnata ass on ass.fk_inventario = vip.id_inventario_piante  ");
			sql.append("inner join vivai_t_domanda dom on dom.id_domanda = ass.fk_domanda   ");
			sql.append("inner join vivai_t_soggetto sog on sog.id_soggetto = dom.fk_soggetto  ");
			sql.append("inner join vivai_d_tipo_domanda tdom on tdom.id_tipo_domanda = dom.fk_tipo_domanda  ");
			sql.append("left join (select * from vivai_r_ddt_assegnata ddtass  ");
			sql.append("inner join vivai_t_ddt ddt on ddt.id_ddt =ddtass.id_ddt ) as ddt on ddt.id_assegnata = ass.id_assegnata) as foo  ");
			sql.append("where (flg_tipo_trasferimento is null or flg_valid_trasf = 'S') ");
			if(idVivaio != null) {
				sql.append(" and fk_vivaio = ? order by tipo, id_inventario_piante ");
				return jdbcTemplate.query(sql.toString(),new XlsCaricoScaricoMapper(), idVivaio);
			}
			sql.append("order by tipo, id_inventario_piante ");
			return jdbcTemplate.query(sql.toString(),new XlsCaricoScaricoMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Integer[] getRangeAnniInventario() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        String sql = "select min(data_carico) as data_from, max(data_carico) as data_to from v_inventario_piante";
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

	@Override
	public int resetAccettazioneTrasferimento(int idPartita) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        StringBuilder sql = new StringBuilder();
	        sql.append("update vivai_t_invent_piante set flg_tipo_trasferimento = 'R', ");
	        sql.append("flg_valid_trasf = null, data_valid_trasf=null, note_valid_trasf=null, ");
	        sql.append("fk_config_utente_valid_trasf = 0 ");
	        sql.append("where  id_inventario_piante =? ");
	        return jdbcTemplate.update(sql.toString(),idPartita);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
