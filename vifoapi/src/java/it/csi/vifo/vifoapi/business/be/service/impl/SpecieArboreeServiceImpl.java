/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifoapi.business.be.impl.dao.AttitudineSpecieDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.SpecieDAO;
import it.csi.vifo.vifoapi.business.be.service.SpecieArboreeService;
import it.csi.vifo.vifoapi.dto.Specie;

@Component
public class SpecieArboreeServiceImpl extends BaseApiServiceImpl implements SpecieArboreeService {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	SpecieDAO specieDAO;
	
	@Autowired
	AttitudineSpecieDAO attitudineSpecieDAO;

	@Override
	public Specie saveSpecieArborea(Specie specie) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			Integer idSpecie = specie.getIdSpecie();
			if(idSpecie == null) {
				idSpecie = specieDAO.save(specie);
			}else {
				specieDAO.update(specie);
			}
			attitudineSpecieDAO.deleteByIdSpecie(specie.getIdSpecie());
			List<Integer> listAttitudine = new ArrayList<Integer>();
			if(specie.getAttitudine() != null) {
				if(specie.getAttitudine().indexOf("A") > -1) {
					listAttitudine.add(1);
				}
				if(specie.getAttitudine().indexOf("F") > -1) {
					listAttitudine.add(2);
				}
				if(specie.getAttitudine().indexOf("R") > -1) {
					listAttitudine.add(3);
				}
				if(listAttitudine.size() > 0) {
					attitudineSpecieDAO.insertList(idSpecie, listAttitudine, specie.getFkConfigUtente());
				}
			}
			return specieDAO.getSpecieById(idSpecie);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	

	

}
