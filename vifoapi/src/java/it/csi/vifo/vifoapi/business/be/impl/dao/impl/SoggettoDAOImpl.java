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

import it.csi.vifo.vifoapi.business.be.exception.RecordNotFoundException;
import it.csi.vifo.vifoapi.business.be.exception.RecordNotUniqueException;
import it.csi.vifo.vifoapi.business.be.impl.dao.SoggettoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.SoggettoResultSearchMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.TSoggettoMapper;
import it.csi.vifo.vifoapi.dto.SoggettoResultSearch;
import it.csi.vifo.vifoapi.dto.SoggettoSearch;
import it.csi.vifo.vifoapi.dto.TSoggetto;
import it.csi.vifo.vifoapi.util.AIKeyHolderUtil;
import it.csi.vifo.vifoapi.util.GenericDAO;
import it.csi.vifo.vifoapi.util.PaginatedList;

@Component
public class SoggettoDAOImpl extends GenericDAO implements SoggettoDAO {
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	private final TSoggettoMapper soggettoMapper = new TSoggettoMapper();

	@Override
	public TSoggetto findSoggettoById(Integer idSoggetto) throws RecordNotFoundException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_t_soggetto s where s.id_soggetto=?";
			List<TSoggetto> soggettos = jdbcTemplate.query(sql, soggettoMapper, idSoggetto);
			if (soggettos.isEmpty()) {
				throw new RecordNotFoundException();
			} else {
				return soggettos.get(0);
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public TSoggetto findSoggettoByCodiceFiscale(String codiceFiscale) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_t_soggetto s where s.codice_fiscale= ? "
					+ " and nome is not null and nome <> '' and cognome is not null and cognome <> ''";
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName," sql: " 
					+ sql + " - cf: " + codiceFiscale));
			List<TSoggetto> soggettos = jdbcTemplate.query(sql, soggettoMapper, codiceFiscale);
			if (soggettos.isEmpty()) {
				return null;
			}else {
				return soggettos.get(0);
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public TSoggetto findAziendaByCodiceFiscale(String codiceFiscale, Boolean isEntePubblico) throws RecordNotUniqueException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM vivai_t_soggetto s where s.codice_fiscale= ? ");
			sql.append(" and ragione_sociale is not null and ragione_sociale <> '' ");
			List<Object> params = new ArrayList<Object>();
			params.add(codiceFiscale);
			if(isEntePubblico != null) {
				sql.append(" and flg_ente_pubblico = ? ");
				params.add(isEntePubblico?1:0);
			}
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName," sql: " 
			+ sql + " -- cf: " + codiceFiscale + " - isEntePubblico: " + isEntePubblico));
			List<TSoggetto> soggettos = jdbcTemplate.query(sql.toString(), soggettoMapper, params.toArray());
			if (soggettos.isEmpty()) {
				return null;
//			} else if (soggettos.size() > 1) {
//				throw new RecordNotUniqueException();
			} else {
				return soggettos.get(0);
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<TSoggetto> findByPartialCodiceFiscale(String codiceFiscale) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "SELECT * FROM vivai_t_soggetto s where s.codice_fiscale LIKE '" + codiceFiscale + "%' ";
	
			return jdbcTemplate.query(sql, soggettoMapper);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int createSoggetto(TSoggetto soggetto) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_soggetto(");
			sql.append("codice_fiscale, cognome, nome, ragione_sociale, partita_iva, indirizzo, cap, "
					+ "istat_comune, telefono, mail, pec, codice_beneficiario, flg_ente_pubblico, data_inserimento, "
					+ " fk_config_utente)");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(soggetto.getCodiceFiscale()!=null?soggetto.getCodiceFiscale().toUpperCase():null);
			parameters.add(soggetto.getCognome());
			parameters.add(soggetto.getNome());
			parameters.add(soggetto.getRagioneSociale());
			parameters.add(soggetto.getPartitaIva()!=null?soggetto.getPartitaIva().toLowerCase():null);
			parameters.add(soggetto.getIndirizzo());
			parameters.add(soggetto.getCap());
			parameters.add(soggetto.getComune()!=null?soggetto.getComune().getCodIstat():null);
			parameters.add(soggetto.getTelefono());
			parameters.add(soggetto.getMail()!=null?soggetto.getMail().toLowerCase():null);
			parameters.add(soggetto.getPec()!=null?soggetto.getPec().toLowerCase():null);
			parameters.add(soggetto.getCodiceBeneficiario());
			parameters.add(soggetto.getFlgEntePubblico());
			parameters.add(now);
			parameters.add(soggetto.getFkConfigUtente());
	
			return AIKeyHolderUtil.updateWithGenKey("id_soggetto", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}

	}

	@Override
	public int createSoggettoFisica(TSoggetto soggetto) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_soggetto(");
			sql.append("codice_fiscale, cognome, nome, partita_iva, indirizzo, cap, "
					+ "istat_comune, telefono, mail, pec, codice_beneficiario, flg_ente_pubblico, data_inserimento, "
					+ " fk_config_utente)");
			sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			List<Object> parameters = new ArrayList<>();
			parameters.add(soggetto.getCodiceFiscale()!=null?soggetto.getCodiceFiscale().toUpperCase():null);
			parameters.add(soggetto.getCognome());
			parameters.add(soggetto.getNome());
			parameters.add(soggetto.getPartitaIva()!=null?soggetto.getPartitaIva().toLowerCase():null);
			parameters.add(soggetto.getIndirizzo());
			parameters.add(soggetto.getCap());
			parameters.add(soggetto.getComune() == null?null:soggetto.getComune().getCodIstat());
			parameters.add(soggetto.getTelefono());
			parameters.add(soggetto.getMail()!=null?soggetto.getMail().toLowerCase():null);
			parameters.add(soggetto.getPec()!=null?soggetto.getPec().toLowerCase():null);
			parameters.add(soggetto.getCodiceBeneficiario());
			parameters.add(0);
			parameters.add(now);
			parameters.add(soggetto.getFkConfigUtente());
	
			return AIKeyHolderUtil.updateWithGenKey("id_soggetto", sql.toString(), parameters, jdbcTemplate);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int updateSoggetto(TSoggetto soggetto) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			List<Object> parameters = new ArrayList<>();
	
			StringBuilder update = new StringBuilder();
			update.append("UPDATE vivai_t_soggetto SET");
			
			if (soggetto.getCodiceFiscale() != null) {
				update.append(" codice_fiscale = ?");
				parameters.add(soggetto.getCodiceFiscale()!=null?soggetto.getCodiceFiscale().toUpperCase():null);
			}
			if (soggetto.getCognome() != null) {
				update.append(", cognome = ?");
				parameters.add(soggetto.getCognome());
			}
			if (soggetto.getNome() != null) {
				update.append(", nome = ?");
				parameters.add(soggetto.getNome());
			}
			if (soggetto.getRagioneSociale() != null) {
				update.append(", ragione_sociale = ?");
				parameters.add(soggetto.getRagioneSociale());
			}
			if (soggetto.getPartitaIva() != null) {
				update.append(", partita_iva = ?");
				parameters.add(soggetto.getPartitaIva()!=null?soggetto.getPartitaIva().toLowerCase():null);
			}
			if (soggetto.getIndirizzo() != null) {
				update.append(", indirizzo = ?");
				parameters.add(soggetto.getIndirizzo());
			}
			if (soggetto.getCap() != null) {
				update.append(", cap = ?");
				parameters.add(soggetto.getCap());
			}
			if (soggetto.getComune() != null && soggetto.getComune().getCodIstat() != null) {
				update.append(", istat_comune = ?");
				parameters.add(soggetto.getComune().getCodIstat());
			}
			if (soggetto.getTelefono() != null) {
				update.append(", telefono = ?");
				parameters.add(soggetto.getTelefono());
			}
			if (soggetto.getMail() != null) {
				update.append(", mail = ?");
				parameters.add(soggetto.getMail()!=null?soggetto.getMail().toLowerCase():null);
			}
			if (soggetto.getPec() != null) {
				update.append(", pec = ?");
				parameters.add(soggetto.getPec()!=null?soggetto.getPec().toLowerCase():null);
			}
			if (soggetto.getCodiceBeneficiario() != null) {
				update.append(", codice_beneficiario = ?");
				parameters.add(soggetto.getCodiceBeneficiario());
			}
			if(soggetto.getFlgEntePubblico() != null) {
				update.append(", flg_ente_pubblico = ?");
				parameters.add(soggetto.getFlgEntePubblico());
			}
			if (soggetto.getFkConfigUtente() != null) {
				update.append(", fk_config_utente = ?");
				parameters.add(soggetto.getFkConfigUtente());
			}
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			update.append(", data_aggiornamento = ?");
			parameters.add(now);
			
			update.append(" where id_soggetto = ?");
			parameters.add(soggetto.getIdSoggetto());
	
			return jdbcTemplate.update(update.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	public boolean validation(TSoggetto soggetto) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return soggetto.getCodiceFiscale() != null && soggetto.getNome() != null && soggetto.getCognome() != null
					&& soggetto.getTelefono() != null && soggetto.getMail() != null;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public void updateFkConfigUtente(int idSoggetto, int configUtenteId) {

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "Update vivai_t_soggetto SET fk_config_utente = ? WHERE id_soggetto = ?";
			jdbcTemplate.update(sql, configUtenteId, idSoggetto);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<TSoggetto> getPersonaGiurdicaByOwnerCodiceFiscale(String codiceFiscale, boolean isEntePubblico) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select s2.* from vivai_r_pf_pg vrpp " + 
					"inner join vivai_t_soggetto s1 on vrpp.id_soggetto_pf = s1.id_soggetto " + 
					"inner join vivai_t_soggetto s2 on vrpp.id_soggetto_pg = s2.id_soggetto " + 
					"where s1.codice_fiscale = ? and s2.flg_ente_pubblico = ? ";
			return jdbcTemplate.query(sql, soggettoMapper, codiceFiscale, isEntePubblico?1:0);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public TSoggetto getSoggettoByIdConfigUtente(Integer idConfigUtente) throws RecordNotFoundException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			String sql = "select sog.* from vivai_cnf_config_utente conf " + 
					"inner join vivai_t_soggetto sog on conf.fk_soggetto = sog.id_soggetto " + 
					"where conf.id_config_utente =? ";
			return jdbcTemplate.queryForObject(sql, soggettoMapper, idConfigUtente);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PaginatedList<SoggettoResultSearch> getPaginatedListSoggetti(int page, int limit, String sort, SoggettoSearch filter) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			List<Object> parameters = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			sql.append("select *, nome as cognome_rag_sociale, istat_comune as comune from (");
			sql.append("select sog.id_soggetto,");
			sql.append("case WHEN sog.cognome is not null THEN CONCAT(sog.cognome, ' ', sog.nome) ELSE sog.ragione_sociale END  AS nome,");
			sql.append("case WHEN sog.codice_fiscale is not null THEN sog.codice_fiscale ELSE sog.partita_iva END  AS cf_piva,");
			sql.append("sog.istat_comune, sog.telefono, sog.mail,");
			sql.append("case WHEN dom.num is not null THEN dom.num ELSE 0 END  AS num_domande ");
			sql.append("from vivai_t_soggetto sog ");
			sql.append("left join vivai_cnf_config_utente cfg on cfg.fk_soggetto = sog.id_soggetto ");
			sql.append("left join ( ");
			sql.append(" 	select vtd.fk_soggetto, count(*) as num from vivai_t_domanda vtd ");
			sql.append("	inner join vivai_r_stato_domanda r_stato on r_stato.id_domanda = vtd.id_domanda ");
			sql.append("	and r_stato.data_fine_stato is null ");
			sql.append("	inner join vivai_cnf_config_utente conf on conf.id_config_utente = vtd.fk_config_utente ");
			if(filter.getIdVivaio() != null) {
				sql.append(" where (r_stato.id_stato_domanda > 1 or conf.fk_vivaio = ? )  ");
				sql.append(" and vtd.fk_vivaio = ? ");
				parameters.add(filter.getIdVivaio());
				parameters.add(filter.getIdVivaio());
			}else {
				sql.append("where r_stato.id_stato_domanda > 1");
			}
					
			sql.append(" group by vtd.fk_soggetto) as dom ");
			sql.append("on dom.fk_soggetto = sog.id_soggetto where 1 = 1 ");
			
			
			
			String params = "";
			
			if(filter.getCognome() != null) {
				sql.append("and upper(sog.cognome) like ? ");
				parameters.add("%" + filter.getCognome().toUpperCase() + "%");
				params+="cognome: %" + filter.getCognome().toUpperCase() + "% - "; 
			}else if(filter.getRagoneSociale() != null) {
				sql.append("and upper(sog.ragione_sociale) like ? ");
				parameters.add("%" + filter.getRagoneSociale().toUpperCase() + "%");
				params+="ragione_sociale: %" + filter.getRagoneSociale().toUpperCase() + "% - ";
			}
			if(filter.getCodFiscalePiva() != null) {
				sql.append("and (upper(sog.codice_fiscale) like ? OR  upper(sog.partita_iva) like ?) ");
				parameters.add("%" + filter.getCodFiscalePiva().toUpperCase() + "%");
				parameters.add("%" + filter.getCodFiscalePiva().toUpperCase() + "%");
				params+="codice_fiscale/partita_iva: %" + filter.getCodFiscalePiva().toUpperCase() + "% - ";
			}
			if(filter.getProvincia() != null && filter.getComune() == null) { 
				sql.append("and sog.istat_comune like ? ");
				parameters.add(filter.getProvincia()+"%");
				params+="istat_comune: " + filter.getProvincia()+ "% - ";
			}
			if(filter.getComune() != null) {
				sql.append("and sog.istat_comune = ? ");
				parameters.add(filter.getComune());
				params+="istat_comune: " + filter.getCognome();
			}
			sql.append(") as foo");
			sql.append(getSqlSort(sort, "+nome"));
			
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"params: " + params));
			
			return super.paginatedList(sql.toString(), parameters, new SoggettoResultSearchMapper(), page, limit);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
