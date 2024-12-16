/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.service;

import java.util.List;

import it.csi.vifo.vifobo.dto.Comune;
import it.csi.vifo.vifobo.dto.Provincia;


public interface WsService {
	
	List<Provincia> getListProvince()throws Exception;
	List<Provincia> getListProvincePiemonte()throws Exception;
	List<Comune> getListComuniPiemonte()throws Exception;
	List<Comune> getListComuni()throws Exception;
	List<Comune> getListComuniByIstatProv(String istatProv)throws Exception;
	List<Comune> getListComuniByName(String name)throws Exception;
	Comune getComuneByIstat(String istat);
	void update()throws Exception;

}
