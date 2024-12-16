/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifoapi.business.be.impl.dao.AssegnazioneDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.DdtDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.DomandaDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.SoggettoDAO;
import it.csi.vifo.vifoapi.business.be.service.AssegnazioneService;
import it.csi.vifo.vifoapi.dto.Assegnazione;
import it.csi.vifo.vifoapi.dto.BaseDdt;
import it.csi.vifo.vifoapi.dto.Ddt;
import it.csi.vifo.vifoapi.dto.DdtRiepilogo;
import it.csi.vifo.vifoapi.dto.Domanda;
import it.csi.vifo.vifoapi.dto.RicercaDdt;
import it.csi.vifo.vifoapi.util.PaginatedList;

@Component
public class AssegnazioneServiceImpl extends BaseApiServiceImpl implements AssegnazioneService {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired 
	AssegnazioneDAO assegnazioneDao;
	
	@Autowired 
	DomandaDAO domandaDAO;
	
	@Autowired
	SoggettoDAO soggettoDAO;
	
	@Autowired 
	DdtDAO ddtDAO;

	@Override
	public List<Assegnazione> getListAssegnazioniByDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			Domanda domanda = domandaDAO.loadDomanda(idDomanda);
			List<Assegnazione> listAssegnazioni = assegnazioneDao.getAssegnazioniByIdDomanda(idDomanda, domanda.getFkVivaio());
			List<Assegnazione> listSpecieVivaioDomanda = new ArrayList<Assegnazione>();
			List<Assegnazione> listSpecieNotVivaioDomanda = new ArrayList<Assegnazione>();
			List<Assegnazione> res = new ArrayList<Assegnazione>();
			Integer lastIdSpecie = null;
			for(Assegnazione ass: listAssegnazioni) {
				if(lastIdSpecie == null || !(ass.getIdSpecie() == lastIdSpecie)) {
					res.addAll(listSpecieVivaioDomanda);
					res.addAll(listSpecieNotVivaioDomanda);
					listSpecieVivaioDomanda = new ArrayList<Assegnazione>();
					listSpecieNotVivaioDomanda = new ArrayList<Assegnazione>();
					lastIdSpecie = ass.getIdSpecie();
				}
				if(domanda.getFkVivaio() == ass.getFkVivaio() || ass.getFkVivaio() ==  null) {
					listSpecieVivaioDomanda.add(ass);
				}else {
					listSpecieNotVivaioDomanda.add(ass);
				}
			}
			res.addAll(listSpecieVivaioDomanda);
			res.addAll(listSpecieNotVivaioDomanda);
			return res;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<Assegnazione> getListAssegnazioniNRByDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			Domanda domanda = domandaDAO.loadDomanda(idDomanda);
			List<Assegnazione> listAssegnazioni = assegnazioneDao.getAssegnazioniNRByIdDomanda(idDomanda);
			List<Assegnazione> listSpecieVivaioDomanda = new ArrayList<Assegnazione>();
			List<Assegnazione> listSpecieNotVivaioDomanda = new ArrayList<Assegnazione>();
			List<Assegnazione> res = new ArrayList<Assegnazione>();
			Integer lastIdSpecie = null;
			for(Assegnazione ass: listAssegnazioni) {
				if(lastIdSpecie == null || !(ass.getIdSpecie() == lastIdSpecie)) {
					lastIdSpecie  = ass.getIdSpecie();
					res.addAll(listSpecieVivaioDomanda);
					res.addAll(listSpecieNotVivaioDomanda);
					listSpecieVivaioDomanda = new ArrayList<Assegnazione>();
					listSpecieNotVivaioDomanda = new ArrayList<Assegnazione>();
				}
				if(domanda.getFkVivaio() == ass.getFkVivaio()) {
					listSpecieVivaioDomanda.add(ass);
				}else {
					listSpecieNotVivaioDomanda.add(ass);
				}
			}
			res.addAll(listSpecieVivaioDomanda);
			res.addAll(listSpecieNotVivaioDomanda);
			return res;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<Assegnazione> getRiepilogoAssegnazioniByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return assegnazioneDao.getRiepilogoAssegnazioniByIdDomanda(idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public void saveAssegnazioni(Assegnazione[] listAssegnazioni, int idDomanda, Integer idConfUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        if(assegnazioniChanged(listAssegnazioni,idDomanda)){
				assegnazioneDao.deleteAssegnata(idDomanda);
				for(Assegnazione item: listAssegnazioni) {
					if(item.getQtaAssegnata()!= null && item.getQtaAssegnata() > 0) {
						assegnazioneDao.insertAssegnata(item, idDomanda, idConfUtente);
					}
				}
				domandaDAO.resetImporti(idDomanda);
	        }
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private boolean assegnazioniChanged(Assegnazione[] vetAssegnazioni, int idDomanda){
		List<Assegnazione> listAssegnazioniOld = getListAssegnazioniByDomanda(idDomanda);
		listAssegnazioniOld.addAll(getListAssegnazioniNRByDomanda(idDomanda));
		listAssegnazioniOld = getListAssegnazioniNotNull(listAssegnazioniOld);		
		List<Assegnazione> listAssegnazioni = getListAssegnazioniNotNull(Arrays.asList(vetAssegnazioni));

		if(listAssegnazioniOld.size() == listAssegnazioni.size()) {
			for(Assegnazione item : listAssegnazioni) {
				if(!listAssegnazioniOld.contains(item)) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	List<Assegnazione> getListAssegnazioniNotNull(List<Assegnazione> listAssegnazioniAll){
		List<Assegnazione> listAssegnazioni = new ArrayList<Assegnazione>();
		for(Assegnazione item : listAssegnazioniAll) {
			if(item != null && item.getQtaAssegnata() != null && item.getQtaAssegnata() > 0) {
				listAssegnazioni.add(item);
			}
		}
		return listAssegnazioni;
	}

	@Override
	public List<Assegnazione> getPartiteBySpecieAndVivaio(int idSpecie, int idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return assegnazioneDao.getPartiteBySpecieAndVivaio(idSpecie, idVivaio);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public void updateRitiroAssegnazioni(Assegnazione[] listAssegnazioni, Integer idConfUtente){
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			for(Assegnazione item: listAssegnazioni) {
				assegnazioneDao.updateRitiroAssegnazioni(item,idConfUtente);
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<Ddt> getAvailableDdtByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return ddtDAO.getAvailableDdtByIdDomanda(idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public List<BaseDdt> getListDdtByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return ddtDAO.getListDdtByIdDomanda(idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public void saveDdt(Ddt[] listDdt, Integer idConfUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			int anno = Calendar.getInstance().get(Calendar.YEAR);
			int numeroDdt = ddtDAO.getNumeroDdtByAnno(anno);
			saveDdt(listDdt, numeroDdt, anno, idConfUtente);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	public void saveDdt(Ddt[] listDdt, Integer numeroDdt, Integer anno, Integer idConfUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        Ddt ddt = new Ddt();
	        ddt.setAnnoDdt(anno);
	        ddt.setNumeroDdt(numeroDdt);
	        ddt.setFkConfigUtente(idConfUtente);
	        ddt.setNumeroFatturaAcconto(listDdt[0].getNumeroFatturaAcconto());
	        ddt.setDataFatturaAcconto(listDdt[0].getDataFatturaAcconto());
	        System.out.println(ddt.getDataFatturaAcconto());
	        int idDdt = ddtDAO.saveDdt(ddt);
	        saveDdtDettails(listDdt, idDdt, idConfUtente);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public DdtRiepilogo getInfoDdt(Integer numeroDdt, Integer anno) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			DdtRiepilogo infoDdt = ddtDAO.getInfoDdt(numeroDdt, anno);
			infoDdt.setOperatore(soggettoDAO.getSoggettoByIdConfigUtente(infoDdt.getIdConfUtende()));
			return infoDdt;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<Ddt> getDdtByNumeroDdtAndAnno(Integer numeroDdt, Integer anno) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return ddtDAO.getByNumeroDdtAndAnno(numeroDdt, anno);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
			
	}

	private void deleteDdt(Integer numeroDdt, Integer anno) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			List<Ddt> listDdt = ddtDAO.getByNumeroDdtAndAnno(numeroDdt, anno);
			for(Ddt ddt : listDdt) {
				ddtDAO.delete(ddt.getIdDdt());
				assegnazioneDao.updateQtaRitirataAssegnazioni(ddt.getIdAssegnata());
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public void updateDdt(Ddt[] listDdt, Integer numeroDdt, Integer anno, Integer idConfUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        List<Ddt> oldListDdt = ddtDAO.getByNumeroDdtAndAnno(numeroDdt, anno);
	        if(oldListDdt != null && oldListDdt.size() > 0) {
	        	deleteDdtDettails(oldListDdt);
	        	int idDdt = oldListDdt.get(0).getIdDdt();
	        	ddtDAO.updateDdt(listDdt[0],idDdt);	        	
	        	saveDdtDettails(listDdt, idDdt, idConfUtente);
	        }
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PaginatedList<DdtRiepilogo> getPaginatedSearchDdt(RicercaDdt ricerca, int page, int limit, String sort) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return ddtDAO.getPaginatedSearchDdt(ricerca, page, limit, sort);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private void saveDdtDettails(Ddt[] listDdt, Integer idDdt, Integer idConfUtente) {
		
		for(Ddt item: listDdt) {
			if(item.getQtaInDdt() > 0) {
				item.setFkConfigUtente(idConfUtente);
				item.setIdDdt(idDdt);
				ddtDAO.saveDdtDettails(item);
				assegnazioneDao.updateQtaRitirataAssegnazioni(item.getIdAssegnata());
			}
		}
		
	}
	
	private void deleteDdtDettails(List<Ddt> listDdt) {
		for(Ddt ddt : listDdt) {
			ddtDAO.deleteDdtDettalis(ddt.getIdDdt());
			assegnazioneDao.updateQtaRitirataAssegnazioni(ddt.getIdAssegnata());
		}
	}
	
	

	@Override
	public void annullaDdt(Integer numeroDdt, Integer anno) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        List<Ddt> listDdt = ddtDAO.getByNumeroDdtAndAnno(numeroDdt, anno);
	        for(Ddt item:listDdt) {
	        	ddtDAO.annullaDdtDettalis(item.getIdDdt());
	        	assegnazioneDao.updateQtaRitirataAssegnazioni(item.getIdAssegnata());	
	        }
	        
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
}
