/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.csi.vifo.vifoapi.business.be.exception.DAOException;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.dao.CertificatoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.CertificatoProvPiemonteMapper;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper.CertificatoRiepilogoMapper;
import it.csi.vifo.vifoapi.dto.CertificatoProvAltro;
import it.csi.vifo.vifoapi.dto.CertificatoProvPiemonte;
import it.csi.vifo.vifoapi.dto.CertificatoRiepilogo;
import it.csi.vifo.vifoapi.dto.InventarioFilter;
import it.csi.vifo.vifoapi.util.GenericDAO;
import it.csi.vifo.vifoapi.util.PaginatedList;

@Component
public class CertificatoDAOImpl extends GenericDAO implements CertificatoDAO{
	
	private final String CLASSNAME = this.getClass().getSimpleName();

	@Override
	public int insertCertPiem(CertificatoProvPiemonte certificato) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_cert_proven_piem "); 
			sql.append("(id_provenienza, numero_certificato, flg_tipo_produzione, fk_natura_materiale, ");
			sql.append("fk_tipo_materiale, fk_popolamento, miscuglio, fk_origine, altitudine, anno_maturazione_semi, ");
			sql.append("qta_materiale_base, fk_udm_materiale, flg_partita_precedente, n_certificato_precedente, ");
			sql.append("qta_partita_iniziale, durata_allevamento, flg_propagazione, metodo_propagazione, ");
			sql.append("n_cicli_propagazione, data_certificato, note, origine_non_autoctoni) "); 
			sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	
			List<Object> parameters = new ArrayList<>();
			parameters.add(certificato.getIdProvenienza());
			parameters.add(certificato.getNumeroCertificato());		
			parameters.add(certificato.getFlgTipoProduzione());		
			parameters.add(certificato.getFkNaturaMateriale());		
			parameters.add(certificato.getFkTipoMateriale());		
			parameters.add(certificato.getFkPopolamento());		
			parameters.add(certificato.getMiscuglio());		
			parameters.add(certificato.getFkOrigine());		
			parameters.add(certificato.getAltitudine());		
			parameters.add(certificato.getAnnoMaturazioneSemi());		
			parameters.add(certificato.getQtaMaterialeBase());		
			parameters.add(certificato.getFkUdmMateriale());		
			parameters.add(certificato.getFlgPartitaPrecedente());		
			parameters.add(certificato.getnCertificatoPrecedente());		
			parameters.add(certificato.getQtaPartitaIniziale());		
			parameters.add(certificato.getDurataAllevamento());		
			parameters.add(certificato.getFlgPropagazione());		
			parameters.add(certificato.getMetodoPropagazione());		
			parameters.add(certificato.getnCicliPropagazione());		
			parameters.add(certificato.getDataCertificato());		
			parameters.add(certificato.getNote());	
			parameters.add(certificato.getOrigineNonAutoctoni());	
	
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int updateCertPiem(CertificatoProvPiemonte certificato) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_cert_proven_piem "); 
			sql.append("SET numero_certificato= ?, flg_tipo_produzione= ?, fk_natura_materiale= ?, ");
			sql.append("fk_tipo_materiale= ?, fk_popolamento= ?, miscuglio= ?, fk_origine= ?, altitudine= ?, ");
			sql.append("anno_maturazione_semi= ?, qta_materiale_base= ?, fk_udm_materiale= ?, flg_partita_precedente= ?, ");
			sql.append("n_certificato_precedente= ?, qta_partita_iniziale= ?, durata_allevamento= ?, flg_propagazione= ?, ");
			sql.append("metodo_propagazione= ?, n_cicli_propagazione= ?, data_certificato= ?, note= ?, origine_non_autoctoni = ? ");
			sql.append("WHERE id_provenienza= ?");
			
			List<Object> parameters = new ArrayList<>();
			parameters.add(certificato.getNumeroCertificato());
			parameters.add(certificato.getFlgTipoProduzione());
			parameters.add(certificato.getFkNaturaMateriale());
			parameters.add(certificato.getFkTipoMateriale());
			parameters.add(certificato.getFkPopolamento());
			parameters.add(certificato.getMiscuglio());
			parameters.add(certificato.getFkOrigine());
			parameters.add(certificato.getAltitudine());
			parameters.add(certificato.getAnnoMaturazioneSemi());
			parameters.add(certificato.getQtaMaterialeBase());
			parameters.add(certificato.getFkUdmMateriale());
			parameters.add(certificato.getFlgPartitaPrecedente());
			parameters.add(certificato.getnCertificatoPrecedente());
			parameters.add(certificato.getQtaPartitaIniziale());
			parameters.add(certificato.getDurataAllevamento());
			parameters.add(certificato.getFlgPropagazione());
			parameters.add(certificato.getMetodoPropagazione());
			parameters.add(certificato.getnCicliPropagazione());
			parameters.add(certificato.getDataCertificato());
			parameters.add(certificato.getNote());
			parameters.add(certificato.getOrigineNonAutoctoni());
			parameters.add(certificato.getIdProvenienza());
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int insertCertAltro(CertificatoProvAltro certificato) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO vivai_t_cert_proven_altro "); 
			sql.append("(id_provenienza, codice, data_certificato) "); 
			sql.append("VALUES(?, ?, ?)");
	
			List<Object> parameters = new ArrayList<>();
			parameters.add(certificato.getIdProvenienza());
			parameters.add(certificato.getCodice());		
			parameters.add(certificato.getDataCertificato());		
	
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int updateCertAltro(CertificatoProvAltro certificato) {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));StringBuilder sql = new StringBuilder();
			sql.append("UPDATE vivai_t_cert_proven_altro "); 
			sql.append("SET codice= ?, data_certificato= ? ");
			sql.append("WHERE id_provenienza= ?");
			
			List<Object> parameters = new ArrayList<>();
			parameters.add(certificato.getCodice());		
			parameters.add(certificato.getDataCertificato());
			parameters.add(certificato.getIdProvenienza());
			return jdbcTemplate.update(sql.toString(), parameters.toArray());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public CertificatoProvPiemonte getCertificatoPiemonteById(int idProvenienza) {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));StringBuilder sql = new StringBuilder();
			sql.append("select cert.id_provenienza, cert.numero_certificato, cert.flg_tipo_produzione, "); 
			sql.append("cert.fk_natura_materiale, prov.fk_specie, prov.fk_categ_materiale,");
			sql.append("cert.fk_tipo_materiale, cert.fk_popolamento, cert.miscuglio, cert.fk_origine,");
			sql.append("prov.provenienza, prov.produttore, cert.altitudine, cert.anno_maturazione_semi, "); 
			sql.append("cert.qta_materiale_base, cert.fk_udm_materiale, cert.flg_partita_precedente, ");
			sql.append("cert.n_certificato_precedente, cert.qta_partita_iniziale, cert.durata_allevamento, "); 
			sql.append("cert.flg_propagazione, cert.metodo_propagazione, cert.n_cicli_propagazione,");
			sql.append("cert.data_certificato, cert.note, prov.flg_no_fini_forestali, origine_non_autoctoni "); 
			sql.append("from vivai_t_cert_proven_piem cert ");
			sql.append("inner join vivai_t_provenienza prov on cert.id_provenienza = prov.id_provenienza "); 
			sql.append("where cert.id_provenienza = ?");
			return jdbcTemplate.queryForObject(sql.toString(), new CertificatoProvPiemonteMapper(), idProvenienza);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private StringBuilder getSqlCertificatoRiepilogo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  ");
        sql.append("certificato.id_provenienza, fk_udm_materiale,");
        sql.append("vccp.codice_cert_proven as codice_certificato_provenienza, ");
        sql.append("certificato.numero_certificato, ");
//        sql.append("certificato.sigla_provincia, ");
        sql.append("certificato.data_certificato, ");
        sql.append("specie.nome_comune as nome_specie, specie.nome_scientifico as specie_latino, ");
        sql.append("materiale.descr_categ_materiale, ");
        sql.append("concat(popseme.cod_amm_popolamento,' - ',popseme.nome_popolamento) as popolamento_seme ");
        sql.append("FROM vivai_t_provenienza provenienza ");
        sql.append("JOIN vivai_t_cert_proven_piem certificato ON provenienza.id_provenienza = certificato.id_provenienza ");
        sql.append("JOIN vivai_t_specie specie ON provenienza.fk_specie = specie.id_specie ");
        sql.append("JOIN vivai_d_categ_materiale materiale ON provenienza.fk_categ_materiale = materiale.id_categ_materiale ");
        sql.append("JOIN vivai_d_popolamento popseme ON certificato.fk_popolamento = popseme.id_popolamento ");
        sql.append("join v_cod_cert_provenienza vccp on certificato.id_provenienza = vccp.id_provenienza  ");
        return sql;
	}

	@Override
	public List<CertificatoRiepilogo> getListCertificatiPiemonteRiepilogo() {		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        StringBuilder sql = getSqlCertificatoRiepilogo();
	        sql.append(getSqlSort(null, " certificato.numero_certificato"));
	        
	        return jdbcTemplate.query(sql.toString(), new CertificatoRiepilogoMapper());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public CertificatoRiepilogo getRiepilogoCertificatoById(int idProvenienza) throws PSQLException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        StringBuilder sql = getSqlCertificatoRiepilogo();
			sql.append(" where provenienza.id_provenienza = ? ");
            return jdbcTemplate.queryForObject(sql.toString(), new CertificatoRiepilogoMapper(), idProvenienza);
		} finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PaginatedList<CertificatoRiepilogo> getPaginatedCertificati(int page, int limit,
			String sort, InventarioFilter filter) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println(page);
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = getSqlCertificatoRiepilogo();
			List<Object> params = new ArrayList<Object>();
			sql.append(" where 1 = 1 ");
			if(filter.getSpecie() != null) {
				sql.append(" and (upper(nome_comune) like ? or upper(nome_scientifico) like ?)");
				String specie = "%" + filter.getSpecie().toUpperCase() + "%";
				params.add(specie);
				params.add(specie);
			}
			if(filter.getAnnata() > 0) {
				sql.append(" and date_part('year', certificato.data_certificato) = ? ");
				params.add( filter.getAnnata());
			}

			sql.append(getSqlSort(sort, " certificato.numero_certificato"));
			return super.paginatedList(sql.toString(), params.toArray() ,new CertificatoRiepilogoMapper(), page, limit);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Double getQtaResiduaByIdProvenienza(int idProvenienza) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			
			sql.append("select qta_materiale_base - sum(COALESCE(qta_carico_kg,0)) as qta_residua ");
			sql.append("from vivai_t_cert_proven_piem cer ");
			sql.append("left join vivai_t_inventario inv  on cer.id_provenienza = inv.fk_provenienza 			 ");
			sql.append("left join (	");
			sql.append("select qta_carico_kg, id_inventario_frutti as id_inventario, 2 as fk_udm_materiale from vivai_t_invent_frutti  ");
			sql.append("union all select qta_carico_kg, id_inventario_semi as id_inventario, 1 as fk_udm_materiale ");
			sql.append(" from vivai_t_invent_semi  where fk_inventario_frutti is null ");
			sql.append(")invf  on inv.id_inventario = invf.id_inventario and cer.fk_udm_materiale = invf.fk_udm_materiale ");
			sql.append("where cer.id_provenienza = ?  ");
			sql.append("group by cer.id_provenienza ");
			
			List<Double> res = jdbcTemplate.queryForList(sql.toString(), Double.class, idProvenienza);
			if(res.size() > 0) {
				return res.get(0);
			}
			return null;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<Integer> getListAnniCertificati() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			StringBuilder sql = new StringBuilder();
			
			sql.append("select distinct date_part('year', data_certificato) as anno ");
			sql.append("from vivai_t_cert_proven_piem order by anno desc ");
		
			return jdbcTemplate.queryForList(sql.toString(), Integer.class);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

}
