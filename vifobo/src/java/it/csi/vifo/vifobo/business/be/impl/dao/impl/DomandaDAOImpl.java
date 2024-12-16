/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.vifo.vifobo.business.be.impl.dao.DomandaDAO;
import it.csi.vifo.vifobo.business.be.impl.dao.impl.mapper.DomandaMapper;
import it.csi.vifo.vifobo.dto.Domanda;
import it.csi.vifo.vifobo.util.GenericDAO;


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

	
	
	
}
