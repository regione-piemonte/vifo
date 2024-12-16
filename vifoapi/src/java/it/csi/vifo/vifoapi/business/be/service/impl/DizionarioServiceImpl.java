/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifoapi.business.be.impl.dao.CertificatoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.DizionarioDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioFruttiDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioPiantineDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioSemiDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.ScopoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.SpecieDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.TitolaritaDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.VivaioDAO;
import it.csi.vifo.vifoapi.business.be.service.DizionarioService;
import it.csi.vifo.vifoapi.dto.Scopo;
import it.csi.vifo.vifoapi.dto.Specie;
import it.csi.vifo.vifoapi.dto.Vivaio;
import it.csi.vifo.vifoapi.dto.VoceDizionario;
import it.csi.vifo.vifoapi.util.DateUtil;
import it.csi.vifo.vifoapi.util.PaginatedList;

@Component
public class DizionarioServiceImpl extends BaseApiServiceImpl implements DizionarioService {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	VivaioDAO vivaioDAO;
	
	@Autowired
	ScopoDAO scopoDAO;
	
	@Autowired
	TitolaritaDAO titolaritaDAO;
	
	@Autowired
	SpecieDAO specieDAO;
	
	@Autowired
	DizionarioDAO dizionarioDAO;
	
	@Autowired
	InventarioFruttiDAO inventarioFruttiDAO;
	
	@Autowired
	InventarioPiantineDAO inventarioPiantineDAO;
	
	@Autowired
	InventarioSemiDAO inventarioSemiDAO;
	
	@Autowired
	CertificatoDAO certificatoDAO;
	
	@Override
	public double getScontoByImporto(double importo) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getScontoByImporto(importo);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public List<Vivaio> getListVivaio() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return vivaioDAO.getListVivaio();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Vivaio getVivaioById(Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return vivaioDAO.getVivaioById(idVivaio);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<Scopo> getListScopo() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return scopoDAO.getListScopo();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Scopo getScopoById(Integer idScopo) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return scopoDAO.getScopoById(idScopo);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListTitolarita() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return titolaritaDAO.getListTitolarita();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public VoceDizionario getTitolaritaById(Integer idTitolarita) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return titolaritaDAO.getTitolaritaById(idTitolarita);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<Specie> getListSpecie(boolean all) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return specieDAO.getAllSpecie(all);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<Specie> getListSpecieByGroup(boolean all) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return specieDAO.getAllSpecieByGroup(all);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public PaginatedList<Specie> getPaginatedListSpecie(boolean all, int page, int limit, String sort, String filter) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return specieDAO.getPaginatedListSpecie(all, page, limit, sort, filter);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Specie getSpecieById(Integer idSpecie) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return specieDAO.getSpecieById(idSpecie);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<String> getListCognomeRichiedente(int vivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListCognomeRichiedente(vivaio);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<String> getListDenominazioneRichiedente(int vivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListDenominazioneRichiedente(vivaio);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListStatoDomanda() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListStatoDomanda();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListTipoDomanda() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListTipoDomanda();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListMaterialiDiMoltiplicazione() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListMaterialiDiMoltiplicazione();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListCategorieMateriale() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListCategorieMateriale();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListTipiMaterialeBase() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListTipiMaterialeBase();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListPopolamentoDaSeme() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListPopolamentoDaSeme();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListOrigini() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListOrigini();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListUdmMateriale() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListUdmMateriale();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListTipiAllevamento() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListTipiAllevamento();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<VoceDizionario> getListGruppoSpecie() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListGruppoSpecie();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<String> getListFamiglieSpecie() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return specieDAO.getListFamiglie();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<String> getListEta() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return dizionarioDAO.getListEta();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public List<VoceDizionario> getFruttiRangeAnni() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        Integer[] anni = inventarioFruttiDAO.getRangeAnniInventario();
	        return getIntervalliByAnni(anni[0], anni[1]);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public List<VoceDizionario> getPiantineRangeAnni() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        Integer[] anni = inventarioPiantineDAO.getRangeAnniInventario();
	        return getIntervalliByAnni(anni[0], anni[1]);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public List<VoceDizionario> getSemiRangeAnni() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
			Integer[] anni = inventarioSemiDAO.getRangeAnniInventario();
	        return getIntervalliByAnni(anni[0], anni[1]);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private List<VoceDizionario> getIntervalliByAnni(int annoFrom, int annoTo) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			List<VoceDizionario> list = new ArrayList<VoceDizionario>();
			VoceDizionario item;		
			for(int anno=annoTo;anno>=annoFrom;anno--) {
				item = new VoceDizionario();
				list.add(item);
				item.setId(anno);
				item.setDescrizione("dal 01/09/" + (anno-1) + " al " + "31/08/" + anno);
			}
			return list;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public List<Integer> getListAnniCertificati() {		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {	
			return certificatoDAO.getListAnniCertificati();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	
}
