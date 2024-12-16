/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.BaseDdt;
import it.csi.vifo.vifoapi.dto.Ddt;
import it.csi.vifo.vifoapi.dto.DomandaRiepilogo;
import it.csi.vifo.vifoapi.dto.DdtRiepilogo;
import it.csi.vifo.vifoapi.dto.RicercaDdt;
import it.csi.vifo.vifoapi.util.PaginatedList;

public interface DdtDAO {
	
	List<Ddt> getAvailableDdtByIdDomanda(Integer idDomanda);
	
	List<Ddt> getByNumeroDdtAndAnno(Integer numeroDdt, Integer anno);
	
	List<BaseDdt> getListDdtByIdDomanda(Integer idDomanda);
	
	int getNumeroDdtByAnno(Integer anno);
	
	int saveDdt(Ddt ddt);
	
	int saveDdtDettails(Ddt ddt);
	
	int deleteDdtDettalis(Integer idDdt);
	
	DdtRiepilogo getInfoDdt(Integer numeroDdt, Integer anno);
	
	int delete(Integer idDdt);
	
	int annullaDdtDettalis(Integer idDdt);
	
	int updateDdt(Ddt ddt,Integer idDdt);
	
	public PaginatedList<DdtRiepilogo> getPaginatedSearchDdt(RicercaDdt ricerca, int page, int limit, String sort);

}
