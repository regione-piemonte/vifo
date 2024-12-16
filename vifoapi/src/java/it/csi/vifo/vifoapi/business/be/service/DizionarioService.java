/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service;

import java.util.List;

import it.csi.vifo.vifoapi.dto.Scopo;
import it.csi.vifo.vifoapi.dto.Specie;
import it.csi.vifo.vifoapi.dto.Vivaio;
import it.csi.vifo.vifoapi.dto.VoceDizionario;
import it.csi.vifo.vifoapi.util.PaginatedList;

public interface DizionarioService {
	
	double getScontoByImporto(double importo);
	List<Vivaio> getListVivaio();
	Vivaio getVivaioById(Integer idVivaio);	
	List<Scopo> getListScopo();
	Scopo getScopoById(Integer idScopo);	
	List<VoceDizionario> getListTitolarita();
	VoceDizionario getTitolaritaById(Integer idTitolarita);	
	List<Specie> getListSpecie(boolean all);	
	List<Specie> getListSpecieByGroup(boolean all);	
	PaginatedList<Specie> getPaginatedListSpecie(boolean all, int page, int limit, String sort, String filter);
	Specie getSpecieById(Integer idSpecie);	
	List<String> getListCognomeRichiedente(int vivaio);
	List<String> getListDenominazioneRichiedente(int vivaio);
	List<VoceDizionario> getListStatoDomanda();
	List<VoceDizionario> getListTipoDomanda();
	List<VoceDizionario> getListMaterialiDiMoltiplicazione();
	List<VoceDizionario> getListCategorieMateriale();
	List<VoceDizionario> getListTipiMaterialeBase();
	List<VoceDizionario> getListPopolamentoDaSeme();
	List<VoceDizionario> getListOrigini();
	List<VoceDizionario> getListUdmMateriale();
	List<VoceDizionario> getListTipiAllevamento();
	List<VoceDizionario> getListGruppoSpecie();
	List<String> getListFamiglieSpecie();
	List<String> getListEta();
	List<VoceDizionario> getFruttiRangeAnni();
	List<VoceDizionario> getPiantineRangeAnni();
	List<VoceDizionario> getSemiRangeAnni();
	List<Integer> getListAnniCertificati();
}
