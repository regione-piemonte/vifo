/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifobo.dto.AllegatoDTO;
import it.csi.vifo.vifobo.dto.BoProcLog;
import it.csi.vifo.vifobo.dto.MetadatiCosmo;

public interface CosmoDAO {
	int insertBoprocLog(String idPratica, int step);
	List<BoProcLog> getProcToWorkByStep(int step);
	int getCountErrore(String idPratica);
	int updateBoprocLog(String idPratica, int countTentativo, Integer codErrore, String noteErrore, String json);
	int storicizzaBoprocLog(String idPratica, String json);
	int storicizzaBoprocLogNoData(String idPratica, String json);
	boolean startSemaforo(String codiceSemaforo);
	void stopSemaforo(String codiceSemaforo);
	boolean isScheduleActive();
	MetadatiCosmo getMetadatiByIdDomanda(int idDomanda);
	List<AllegatoDTO> getElencoAllegati(Integer idDomanda, Integer inviato, boolean isDomanda) ;
	int updateDocumentoWithUuidCosmo(long idDocumentoAllegato, String uuidCosmo);
	int updateDocumentoWithInvoCosmoTo2(Integer idDomanda);
	int updateDocumentoWithInvoActa(Integer idAllegato);
	boolean isPraticaClosed(String idPratica);
}
