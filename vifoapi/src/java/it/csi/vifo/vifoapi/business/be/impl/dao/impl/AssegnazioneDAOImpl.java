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

import it.csi.vifo.vifoapi.business.be.impl.dao.AssegnazioneDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.AssegnazioneMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.PartiteMapper;
import it.csi.vifo.vifoapi.dto.Assegnazione;
import it.csi.vifo.vifoapi.util.AIKeyHolderUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;

@Component
public class AssegnazioneDAOImpl extends GenericDAO implements AssegnazioneDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public List<Assegnazione> getAssegnazioniByIdDomanda(Integer idDomanda, Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder("select  distinct inv.*, vip.certificato_provenienza ,ass.qta_assegnata, ass.qta_ritirata, ass.id_assegnata from ( "); 
			sql.append("select vtd.fk_tipo_domanda, ds.id_domanda,ds.qta_richiesta, sp.nome_comune as specie, sp.nome_scientifico as specie_latino, ds.id_specie, ");
			sql.append("sp.flg_specie_386,   inv.id_inventario, inv.nome_vivaio, inv.fk_vivaio,inv.certificato_altro, ");
			sql.append("inv.certificato_piemonte, inv.flg_no_fini_forestali, inv.descr_tipo_allevamento, inv.eta, ");
			sql.append("inv.altezza, inv.prezzo_unitario, inv.qta_disponibile, 0 as importo  "); 
			sql.append("from vivai_t_domanda vtd inner join vivai_r_domanda_specie ds on ds.id_domanda = vtd.id_domanda ");
			sql.append("inner join vivai_t_specie sp on sp.id_specie = ds.id_specie  " ); 
			sql.append("left join( select  inv.id_inventario ,viv.nome as nome_vivaio,   invp.id_inventario_piante, ");
			sql.append("invp.fk_vivaio,prov.fk_specie, cert_a.codice as certificato_altro, ");
			sql.append("cert_p.numero_certificato as certificato_piemonte, prov.flg_no_fini_forestali, ");
			sql.append("allev.descr_tipo_allevamento, invp.eta, invp.altezza, invp.prezzo_unitario, disp.qta_disponibile ");
			sql.append("from vivai_t_provenienza prov inner join vivai_t_inventario inv on prov.id_provenienza = inv.fk_provenienza ");
			sql.append("inner join vivai_t_invent_piante invp on invp.id_inventario_piante = inv.id_inventario  and (flg_tipo_trasferimento is null or flg_valid_trasf = 'S') ");
			sql.append( "inner join vivai_t_vivaio viv on viv.id_vivaio = invp.fk_vivaio ");
			sql.append( "inner join vivai_d_tipo_allevamento allev on allev.id_tipo_allevamento = invp.fk_tipo_allevamento ");
			sql.append( "inner join V_DISPONIBILITA_PIANTE disp on disp.id_inventario_piante = inv.id_inventario ");
			sql.append("left join vivai_t_cert_proven_altro cert_a on cert_a.id_provenienza = prov.id_provenienza ");
			sql.append("left join vivai_t_cert_proven_piem cert_p on cert_p.id_provenienza  = prov.id_provenienza )  "); 
			sql.append("as inv on inv.fk_specie = ds.id_specie and inv.fk_vivaio = ?  ");
			sql.append("union all "); 
			sql.append("select vtd.fk_tipo_domanda, ds.id_domanda,ds.qta_richiesta, sp.nome_comune as specie, sp.nome_scientifico as specie_latino, ds.id_specie, ");
			sql.append("sp.flg_specie_386,   inv.id_inventario, inv.nome_vivaio, inv.fk_vivaio,inv.certificato_altro, ");
			sql.append("inv.certificato_piemonte, inv.flg_no_fini_forestali, inv.descr_tipo_allevamento, inv.eta, ");
			sql.append("inv.altezza, inv.prezzo_unitario, inv.qta_disponibile, 0 as importo  "); 
			sql.append("from vivai_t_domanda vtd inner join vivai_r_domanda_specie ds on ds.id_domanda = vtd.id_domanda ");
			sql.append("inner join vivai_t_specie sp on sp.id_specie = ds.id_specie  "); 
			sql.append("left join( select  inv.id_inventario ,viv.nome as nome_vivaio,   invp.id_inventario_piante, ");
			sql.append("invp.fk_vivaio,prov.fk_specie, cert_a.codice as certificato_altro, ");
			sql.append("cert_p.numero_certificato as certificato_piemonte, prov.flg_no_fini_forestali, ");
			sql.append("allev.descr_tipo_allevamento, invp.eta, invp.altezza, invp.prezzo_unitario, disp.qta_disponibile ");
			sql.append("from vivai_t_provenienza prov inner join vivai_t_inventario inv on prov.id_provenienza = inv.fk_provenienza ");
			sql.append("inner join vivai_t_invent_piante invp on invp.id_inventario_piante = inv.id_inventario ");
			sql.append("inner join vivai_t_vivaio viv on viv.id_vivaio = invp.fk_vivaio ");
			sql.append("inner join vivai_d_tipo_allevamento allev on allev.id_tipo_allevamento = invp.fk_tipo_allevamento ");
			sql.append("inner join V_DISPONIBILITA_PIANTE disp on disp.id_inventario_piante = inv.id_inventario ");
			sql.append("left join vivai_t_cert_proven_altro cert_a on cert_a.id_provenienza = prov.id_provenienza ");
			sql.append("left join vivai_t_cert_proven_piem cert_p on cert_p.id_provenienza  = prov.id_provenienza )  ");
			sql.append("as inv on inv.fk_specie = ds.id_specie and inv.fk_vivaio <> ? "); 
			sql.append(") as inv "); 
			sql.append("left join v_inventario_piante vip on vip.id_inventario_piante = inv.id_inventario "); 
			sql.append("left join vivai_t_assegnata ass on ass.fk_domanda = inv.id_domanda and ass.fk_inventario = inv.id_inventario  ");
			sql.append("where inv.id_domanda = ? and (inv.qta_disponibile > 0 or ass.qta_assegnata is not null)  ");
			sql.append("order by inv.specie,inv.fk_vivaio, inv.id_inventario");
			
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName," sql: " + sql.toString()));
			return jdbcTemplate.query(sql.toString(), new AssegnazioneMapper(),idVivaio, idVivaio, idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<Assegnazione> getAssegnazioniNRByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select vip.certificato_provenienza, null as fk_tipo_domanda, inv.fk_vivaio,  ");
			sql.append("null as id_domanda,null as qta_richiesta, inv.specie, specie_latino, inv.id_specie, inv.flg_specie_386 ,  ");
			sql.append("ass.qta_assegnata, ass.qta_ritirata,   inv.id_inventario, inv.nome_vivaio,  ");
			sql.append("inv.fk_vivaio,inv.certificato_altro, inv.certificato_piemonte,    ");
			sql.append("inv.flg_no_fini_forestali, inv.descr_tipo_allevamento, inv.eta, inv.altezza, ");
			sql.append(" inv.prezzo_unitario,inv.qta_disponibile, 0 as importo, null as id_assegnata  ");
			sql.append("FROM(    ");
			sql.append("select  inv.id_inventario ,viv.nome as nome_vivaio,sp.nome_comune as specie, sp.id_specie, sp.flg_specie_386,   ");
			sql.append(" sp.nome_scientifico as specie_latino, invp.id_inventario_piante, invp.fk_vivaio,prov.fk_specie,    ");
			sql.append("cert_a.codice as certificato_altro, cert_p.numero_certificato as certificato_piemonte,    ");
			sql.append("prov.flg_no_fini_forestali, allev.descr_tipo_allevamento, invp.eta, invp.altezza, invp.prezzo_unitario,   ");
			sql.append("disp.qta_disponibile    ");
			sql.append("from vivai_t_provenienza prov    ");
			sql.append("inner join vivai_t_specie sp on sp.id_specie = prov.fk_specie   ");
			sql.append("inner join vivai_t_inventario inv on prov.id_provenienza = inv.fk_provenienza   ");
			sql.append("inner join vivai_t_invent_piante invp on invp.id_inventario_piante = inv.id_inventario    ");
			sql.append("inner join vivai_t_vivaio viv on viv.id_vivaio = invp.fk_vivaio   ");
			sql.append("inner join vivai_d_tipo_allevamento allev on allev.id_tipo_allevamento = invp.fk_tipo_allevamento    ");
			sql.append("inner join V_DISPONIBILITA_PIANTE disp on disp.id_inventario_piante = inv.id_inventario    ");
			sql.append("left join vivai_t_cert_proven_altro cert_a on cert_a.id_provenienza = prov.id_provenienza    ");
			sql.append("left join vivai_t_cert_proven_piem cert_p on cert_p.id_provenienza  = prov.id_provenienza  ");
			sql.append("where prov.fk_specie in (select fk_specie from vivai_t_assegnata ass  ");
			sql.append("	inner join vivai_t_inventario inv on inv.id_inventario = ass.fk_inventario  ");
			sql.append("	inner join vivai_t_provenienza prov on prov.id_provenienza = inv.fk_provenienza where ass.fk_domanda = ? ) ");
			sql.append(") as inv ");
			sql.append("left join vivai_t_assegnata ass on ass.fk_inventario = inv.id_inventario and  ass.fk_domanda = ?   ");
			sql.append("inner join v_inventario_piante vip on vip.id_inventario_piante = inv.id_inventario   ");
			sql.append("where  fk_specie not in   ");
			sql.append("(select id_specie from vivai_r_domanda_specie ass where id_domanda = ?)   ");
			sql.append("and (inv.qta_disponibile > 0 or ass.qta_assegnata is not null)   ");
			sql.append("order by inv.specie, inv.fk_vivaio, inv.id_inventario ");
			
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName," sql: " + sql.toString()));
			return jdbcTemplate.query(sql.toString(), new AssegnazioneMapper(), idDomanda,idDomanda,idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public List<Assegnazione> getRiepilogoAssegnazioniByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("select vtd.fk_vivaio, vtd.fk_tipo_domanda, vtd.fk_vivaio,ds.id_domanda,");
			sql.append("ds.qta_richiesta, sp.id_specie, sp.nome_comune as specie, sp.nome_scientifico as specie_latino, sp.flg_specie_386 ,");
			sql.append("sum(ass.qta_assegnata) as qta_assegnata, sum(ass.qta_ritirata) as qta_ritirata,inv.id_inventario,");
			sql.append("viv.nome as nome_vivaio, inv.certificato_altro, inv.certificato_piemonte, inv.flg_no_fini_forestali,");
			sql.append("inv.descr_tipo_allevamento, inv.eta, inv.altezza, inv.prezzo_unitario, null as qta_disponibile ,");
			sql.append("inv.prezzo_unitario * ass.qta_assegnata as importo, ass.id_assegnata,inv.certificato_provenienza  ");
			sql.append("from vivai_t_assegnata ass "); 
			sql.append("inner join vivai_t_domanda vtd on vtd.id_domanda  = ass.fk_domanda "); 
			sql.append("inner join vivai_t_vivaio viv on viv.id_vivaio = vtd.fk_vivaio  "); 
			sql.append("right join( select vip.certificato_provenienza,  inv.id_inventario,   invp.id_inventario_piante, invp.fk_vivaio,prov.fk_specie, ");
			sql.append("cert_a.codice as certificato_altro, cert_p.numero_certificato as certificato_piemonte, ");
			sql.append("prov.flg_no_fini_forestali, allev.descr_tipo_allevamento, invp.eta, invp.altezza, ");
			sql.append( "invp.prezzo_unitario, disp.qta_disponibile  "); 
			sql.append("	from vivai_t_provenienza prov  "); 
			sql.append("	inner join vivai_t_inventario inv on prov.id_provenienza = inv.fk_provenienza  "); 
			sql.append("	inner join vivai_t_invent_piante invp on invp.id_inventario_piante = inv.id_inventario and (flg_tipo_trasferimento is null or flg_valid_trasf = 'S')  ");
			sql.append("	inner join vivai_d_tipo_allevamento allev on allev.id_tipo_allevamento = invp.fk_tipo_allevamento  "); 
			sql.append("	inner join V_DISPONIBILITA_PIANTE disp on disp.id_inventario_piante = inv.id_inventario  "); 
			sql.append("	left join v_inventario_piante vip on vip.id_inventario_piante = inv.id_inventario "); 
			sql.append("	left join vivai_t_cert_proven_altro cert_a on cert_a.id_provenienza = prov.id_provenienza  "); 
			sql.append("	left join vivai_t_cert_proven_piem cert_p on cert_p.id_provenienza  = prov.id_provenienza ) as inv  "); 
			sql.append("	on ass.fk_inventario = inv.id_inventario "); 
			sql.append("	inner join vivai_t_specie sp on sp.id_specie = inv.fk_specie  ");
			sql.append("	left join vivai_r_domanda_specie ds on ds.id_domanda = vtd.id_domanda and inv.fk_specie = ds.id_specie "); 
			sql.append("	where ass.fk_domanda = ?  "); 
			sql.append("	group by vtd.fk_vivaio, vtd.fk_tipo_domanda, vtd.fk_vivaio,ds.id_domanda,ds.qta_richiesta, ");
			sql.append("sp.nome_comune, sp.nome_scientifico, sp.id_specie, sp.flg_specie_386,inv.id_inventario, ass.id_assegnata ,viv.nome, ");
			sql.append("inv.certificato_altro, inv.certificato_piemonte, inv.flg_no_fini_forestali, inv.descr_tipo_allevamento,");
			sql.append(" inv.eta, inv.altezza, inv.prezzo_unitario, ass.qta_assegnata, inv.certificato_provenienza ");
			
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName," sql: " + sql.toString()));
			return jdbcTemplate.query(sql.toString(), new AssegnazioneMapper(), idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
		}

	@Override
	public int insertAssegnata(Assegnazione item, Integer idDomanda, Integer idConfUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO vivai_t_assegnata " + 
					"(fk_domanda, fk_inventario, qta_assegnata, qta_ritirata, " +
					"data_inserimento, data_aggiornamento, fk_config_utente_ins) " + 
					"VALUES(?, ?, ?, ?, ?, ?, ?)");
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(idDomanda);
			parameters.add(item.getIdPartita());
			parameters.add(item.getQtaAssegnata());
			parameters.add(0);
			parameters.add(now);
			parameters.add(now);
			parameters.add(idConfUtente);
	
			return AIKeyHolderUtil.updateWithGenKey("id_assegnata", sb.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int deleteAssegnata(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "delete from vivai_t_assegnata where fk_domanda = ? and qta_ritirata = 0";
			return jdbcTemplate.update(sql,idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<Assegnazione> getPartiteBySpecieAndVivaio(int idSpecie, int idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        List<Object> parameters = new ArrayList<>();
	        
			StringBuilder sql = new StringBuilder("select * from (("); 
			sql.append("select  inv.id_inventario, ");
			sql.append("viv.nome as nome_vivaio,invp.fk_vivaio,prov.fk_specie, "); 
			sql.append("sp.nome_comune as specie, sp.flg_specie_386, ");
			sql.append("vpia.certificato_provenienza as certificato_altro, null as certificato_piemonte, "); 
			sql.append("prov.flg_no_fini_forestali, allev.descr_tipo_allevamento, invp.eta, invp.altezza, invp.prezzo_unitario, "); 
			sql.append("disp.qta_disponibile "); 
			sql.append("from vivai_t_provenienza prov "); 
			sql.append("inner join vivai_t_specie sp on sp.id_specie = prov.fk_specie "); 
			sql.append("inner join vivai_t_inventario inv on prov.id_provenienza = inv.fk_provenienza "); 
			sql.append("inner join vivai_t_invent_piante invp on invp.id_inventario_piante = inv.id_inventario and (flg_tipo_trasferimento is null or flg_valid_trasf = 'S') "); 
			sql.append("inner join vivai_t_vivaio viv on viv.id_vivaio = invp.fk_vivaio "); 
			sql.append("inner join vivai_d_tipo_allevamento allev on allev.id_tipo_allevamento = invp.fk_tipo_allevamento "); 
			sql.append("inner join V_DISPONIBILITA_PIANTE disp on disp.id_inventario_piante = inv.id_inventario "); 
			sql.append("inner join v_inventario_piante vpia on vpia.id_inventario_piante = inv.id_inventario "); 
			if(idVivaio > 0) {
				sql.append("where invp.fk_vivaio = ? order by inv.id_inventario) ");
				sql.append("union all ");
				sql.append("(select  inv.id_inventario, ");
				sql.append("viv.nome as nome_vivaio,invp.fk_vivaio,prov.fk_specie, "); 
				sql.append("sp.nome_comune as specie, sp.flg_specie_386, ");
				sql.append("vpia.certificato_provenienza as certificato_altro, null as certificato_piemonte, "); 
				sql.append("prov.flg_no_fini_forestali, allev.descr_tipo_allevamento, invp.eta, invp.altezza, invp.prezzo_unitario, "); 
				sql.append("disp.qta_disponibile "); 
				sql.append("from vivai_t_provenienza prov "); 
				sql.append("inner join vivai_t_specie sp on sp.id_specie = prov.fk_specie "); 
				sql.append("inner join vivai_t_inventario inv on prov.id_provenienza = inv.fk_provenienza "); 
				sql.append("inner join vivai_t_invent_piante invp on invp.id_inventario_piante = inv.id_inventario and (flg_tipo_trasferimento is null or flg_valid_trasf = 'S') "); 
				sql.append("inner join vivai_t_vivaio viv on viv.id_vivaio = invp.fk_vivaio "); 
				sql.append("inner join vivai_d_tipo_allevamento allev on allev.id_tipo_allevamento = invp.fk_tipo_allevamento "); 
				sql.append("inner join V_DISPONIBILITA_PIANTE disp on disp.id_inventario_piante = inv.id_inventario "); 
				sql.append("inner join v_inventario_piante vpia on vpia.id_inventario_piante = inv.id_inventario ");
				sql.append("where invp.fk_vivaio <> ? order by fk_vivaio, inv.id_inventario ");
				parameters.add(idVivaio);
				parameters.add(idVivaio);
			}
			sql.append(" )) as foo ");
			sql.append("where fk_specie = ? and qta_disponibile > 0 ");
			parameters.add(idSpecie);
			
			if(idVivaio == 0) {
				sql.append("order by inv.id_inventario");
			}
	
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName," sql: " + sql.toString()));
			return jdbcTemplate.query(sql.toString(), new PartiteMapper(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int updateRitiroAssegnazioni(Assegnazione assegnazione, Integer idConfUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder("update vivai_t_assegnata vta set qta_ritirata = ?, data_ritiro = ?, ");
			sql.append("data_aggiornamento = ?, fk_config_utente_rit = ? where id_assegnata = ?"); 
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(assegnazione.getQtaRitirata());
			parameters.add(now);
			parameters.add(now);
			parameters.add(idConfUtente);
			parameters.add(assegnazione.getIdAssegnata());
			
			return jdbcTemplate.update(sql.toString(),parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public int updateQtaRitirataAssegnazioni(Integer idAssegnata) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("update vivai_t_assegnata vta set qta_ritirata = ");
			sql.append("(select sum(qta_ddt) from vivai_r_ddt_assegnata where id_assegnata = ?) ");
			sql.append("where id_assegnata = ? "); 
			List<Object> parameters = new ArrayList<>();
			parameters.add(idAssegnata);
			parameters.add(idAssegnata);
			
			return jdbcTemplate.update(sql.toString(),parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
