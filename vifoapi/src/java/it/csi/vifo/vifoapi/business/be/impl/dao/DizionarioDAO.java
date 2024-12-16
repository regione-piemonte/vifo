/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import it.csi.vifo.vifoapi.dto.VoceDizionario;
import java.util.List;

public interface DizionarioDAO {

	double getScontoByImporto(double importo);
	List<VoceDizionario> getListStatoDomanda();
	List<VoceDizionario> getListTipoDomanda();
	List<String> getListCognomeRichiedente(int vivaio);
	List<String> getListDenominazioneRichiedente(int vivaio);
	List<VoceDizionario> getListMaterialiDiMoltiplicazione();
	List<VoceDizionario> getListCategorieMateriale();
	List<VoceDizionario> getListTipiMaterialeBase();
	List<VoceDizionario> getListPopolamentoDaSeme();
	List<VoceDizionario> getListOrigini();
	List<VoceDizionario> getListUdmMateriale();
	List<VoceDizionario> getListTipiAllevamento();
	List<VoceDizionario> getListGruppoSpecie();
	List<String> getListEta();
}
