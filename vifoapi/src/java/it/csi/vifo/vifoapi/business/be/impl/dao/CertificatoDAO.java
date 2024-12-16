/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.business.be.exception.DAOException;
import it.csi.vifo.vifoapi.dto.CertificatoProvAltro;
import it.csi.vifo.vifoapi.dto.CertificatoProvPiemonte;
import it.csi.vifo.vifoapi.dto.CertificatoRiepilogo;
import it.csi.vifo.vifoapi.dto.InventarioFilter;
import it.csi.vifo.vifoapi.util.PaginatedList;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;

public interface CertificatoDAO {
	int insertCertPiem(CertificatoProvPiemonte certificato);
	int updateCertPiem(CertificatoProvPiemonte certificato);
	int insertCertAltro(CertificatoProvAltro certificato);
	int updateCertAltro(CertificatoProvAltro certificato);
	CertificatoProvPiemonte getCertificatoPiemonteById(int idProvenienza); 
	List<CertificatoRiepilogo> getListCertificatiPiemonteRiepilogo();
	CertificatoRiepilogo getRiepilogoCertificatoById(int idProvenienza) throws PSQLException;
	PaginatedList<CertificatoRiepilogo> getPaginatedCertificati(int page, int limit, String sort, InventarioFilter filter);
	Double getQtaResiduaByIdProvenienza(int idProvenienza);
	List<Integer> getListAnniCertificati();
}
