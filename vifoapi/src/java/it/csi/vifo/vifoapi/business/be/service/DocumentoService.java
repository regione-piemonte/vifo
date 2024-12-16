/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service;

import java.util.List;

import it.csi.vifo.vifoapi.business.be.exception.ServiceException;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.DocumentoAllegato;
import it.csi.vifo.vifoapi.dto.FileUploadForm;
import it.csi.vifo.vifoapi.dto.FotoSpecie;
import it.csi.vifo.vifoapi.util.TipoFotoEnum;

public interface DocumentoService {
	DocumentoAllegato uploadDocumentiDomanda(int idDomanda, int tipo, FileUploadForm form, ConfigUtente configUtente)throws ServiceException;
	FotoSpecie uploadImgSpecie(int idSpecie, TipoFotoEnum fotoType, FileUploadForm form, ConfigUtente configUtente)throws ServiceException;
	int deleteAllegatoById(int idAllegato)throws ServiceException;
	List<DocumentoAllegato> getAllegatiByIdDomanda(int idDomanda);
	DocumentoAllegato getAllegatoById(int idAllegato);
	int deleteAllegatoByIdDomanda(int idDomanda)throws ServiceException;	
	void getPortalFile(Integer idDocumento);
	byte[] getPortalFotoSpecie(Integer idSpecie, int fotoType);
	byte[] getPortalAllegato(Integer idAllegato);
}
