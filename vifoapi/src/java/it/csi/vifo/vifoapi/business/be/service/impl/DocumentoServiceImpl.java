/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.exception.ServiceException;
import it.csi.vifo.vifoapi.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifoapi.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.FotoSpecieDAO;
import it.csi.vifo.vifoapi.business.be.service.DocumentoService;
import it.csi.vifo.vifoapi.business.be.service.helper.IndexServiceHelper;
import it.csi.vifo.vifoapi.business.be.service.helper.dto.MetadatiIndexDomanda;
import it.csi.vifo.vifoapi.business.be.service.helper.dto.MetadatiIndexFotoSpecie;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.DocumentoAllegato;
import it.csi.vifo.vifoapi.dto.FileUploadForm;
import it.csi.vifo.vifoapi.dto.FotoSpecie;
import it.csi.vifo.vifoapi.util.IndexFileTypeEnum;
import it.csi.vifo.vifoapi.util.TipoFotoEnum;

@Component
public class DocumentoServiceImpl extends BaseApiServiceImpl implements DocumentoService {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	DocumentoAllegatoDAO documentoAllegatoDAO;
	
	@Autowired
	FotoSpecieDAO fotoSpecieDAO;
	
	@Autowired
	ApplicationContext applicationContext;

	@Override
	public DocumentoAllegato uploadDocumentiDomanda(int idDomanda, int tipo, FileUploadForm form, 
			ConfigUtente configUtente) throws ServiceException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			byte[] uploadForm = form.getData();			
	
			//String descrTipoAllegato = tipoAllegatoDAO.getTipoById(tipoDocumento).getDescrTipoAllegato();
			DocumentoAllegato documentoAllegato = new DocumentoAllegato();
			documentoAllegato.setFkDomanda(idDomanda);
			documentoAllegato.setFkTipoAllegato(tipo);
			documentoAllegato.setNomeFile(form.getName());
			documentoAllegato.setEstensioneFile(getEstenzioneFile(form.getName()));			
			documentoAllegato.setDimensioneFileKb(Long.valueOf(uploadForm.length) / 1024);
			documentoAllegato.setFkConfigUtente(configUtente.getIdConfigUtente());
	
			deleteDocumentIfAllreadyExists(documentoAllegato);
			int idAllegato = documentoAllegatoDAO.save(documentoAllegato);
			
			IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
			MetadatiIndexDomanda metadatiIndexDoc = new MetadatiIndexDomanda();
			metadatiIndexDoc.setIdAllegato(String.valueOf(idAllegato));
			metadatiIndexDoc.setIdDomanda(String.valueOf(idDomanda));
			metadatiIndexDoc.setTipoAllegato(String.valueOf(tipo));
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"idAllegato: " + idAllegato));
			try {
				String fileUID = indexSrvHelper.indexUploadFile(form.getName(), uploadForm, metadatiIndexDoc);
				LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"fileUID: " + fileUID));
				documentoAllegato.setIdAllegato(idAllegato);
				documentoAllegato.setUidIndex(fileUID);
				documentoAllegatoDAO.updateUidIndex(documentoAllegato);
				return documentoAllegatoDAO.getByIdAllegato(idAllegato);
			}catch(ServiceException ex) {
				documentoAllegatoDAO.deleteByIdAllegato(idAllegato);
				throw ex;
			}
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public FotoSpecie uploadImgSpecie(int idSpecie, TipoFotoEnum fotoType, FileUploadForm form, ConfigUtente configUtente)
			throws ServiceException {
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			try {			
		        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));byte[] uploadForm = form.getData();			
			
				FotoSpecie fotoSpecie = new FotoSpecie();
				
				fotoSpecie.setFkSpecie(idSpecie);
				fotoSpecie.setFkTipoFoto(fotoType.getValue());
				fotoSpecie.setFkConfigUtente(configUtente.getIdConfigUtente());
				fotoSpecie.setNomeFile(form.getName());
				fotoSpecie.setEstensioneFile(getEstenzioneFile(form.getName()));
				fotoSpecie.setDimensioneFileKb(Long.valueOf(uploadForm.length) / 1024);
				//se deve esserci una  sola imagine per specie e fotoType:
				FotoSpecie fotoSpecieStored = fotoSpecieDAO.findByIdSpecieAndType(idSpecie, fotoType.getValue());
				if(fotoSpecieStored != null) {
					deleteImgSpecie(fotoSpecieStored);
				}
				int idFotoSpecie = fotoSpecieDAO.insert(fotoSpecie);
				
				
				IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
				MetadatiIndexFotoSpecie metadatiIndexFoto = new MetadatiIndexFotoSpecie();
				metadatiIndexFoto.setIdFotoSpecie(String.valueOf(idFotoSpecie));
				metadatiIndexFoto.setIdSpecie(String.valueOf(idSpecie));
				metadatiIndexFoto.setIdTipoSpecie("" + fotoType.getValue());
				LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"idFotoSpecie: " + idFotoSpecie));
				try {
					String fileUID = indexSrvHelper.indexUploadFile(form.getName(), uploadForm, metadatiIndexFoto);
					LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"fileUID: " + fileUID));
					fotoSpecie.setIdFotoSpecie(idFotoSpecie);
					fotoSpecie.setUidIndex(fileUID);
					fotoSpecieDAO.updateUidIndex(fotoSpecie);
					return fotoSpecie;
				}catch(ServiceException ex) {
					fotoSpecieDAO.deleteById(idFotoSpecie);
					throw ex;
				}
			}finally {
				LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
			}
	}
	
	private int deleteImgSpecie(FotoSpecie item) throws ServiceException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
			indexSrvHelper.indexDeleteFile(item.getUidIndex());
			return fotoSpecieDAO.deleteById(item.getIdFotoSpecie());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private String getEstenzioneFile(String fileName) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        if(fileName != null) {
				int idx = fileName.lastIndexOf(".") + 1;
				if(idx > 0) {
					return fileName.substring(idx);
				}
			}
			return null;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<DocumentoAllegato> getAllegatiByIdDomanda(int idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return documentoAllegatoDAO.getByIdDomanda(idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public DocumentoAllegato getAllegatoById(int idAllegato) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return documentoAllegatoDAO.getByIdAllegato(idAllegato);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public int deleteAllegatoById(int idAllegato) throws ServiceException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return deleteAllegato(documentoAllegatoDAO.getByIdAllegato(idAllegato));
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private int deleteAllegato(DocumentoAllegato doc) throws ServiceException {
		IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
		indexSrvHelper.indexDeleteFile(doc.getUidIndex());
		return documentoAllegatoDAO.deleteByIdAllegato(doc.getIdAllegato());
	}

	@Override
	public int deleteAllegatoByIdDomanda(int idDomanda) throws ServiceException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));List<DocumentoAllegato> listDocs = documentoAllegatoDAO.getByIdDomanda(idDomanda);
			for(DocumentoAllegato doc : listDocs) {
				deleteAllegato(doc);
			}
			return listDocs == null?0:listDocs.size();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public void getPortalFile(Integer idDocumento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] getPortalFotoSpecie(Integer idSpecie, int fotoType) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
			FotoSpecie fotoSpecie = fotoSpecieDAO.findByIdSpecieAndType(idSpecie, fotoType);
			if(fotoSpecie != null) {
				if(fotoSpecie.getUidIndex() != null) {
					return indexSrvHelper.indexDownloadFileByUid(fotoSpecie.getUidIndex());
				}else {
					return indexSrvHelper.indexDownloadFile("" + fotoSpecie.getIdFotoSpecie(),IndexFileTypeEnum.IMMAGINE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
		return null;
	}

	@Override
	public byte[] getPortalAllegato(Integer idAllegato) {		
		IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));			
			return indexSrvHelper.indexDownloadFile("" + idAllegato, IndexFileTypeEnum.ALLEGATO);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private void deleteDocumentIfAllreadyExists(DocumentoAllegato documentoAllegato)throws ServiceException {
		List<DocumentoAllegato> listAllegati = documentoAllegatoDAO.getByIdDomanda(documentoAllegato.getFkDomanda());
		for(DocumentoAllegato item: listAllegati) {
			if(item.getFkTipoAllegato() == documentoAllegato.getFkTipoAllegato()) {
				deleteAllegato(item);
			}
		}
	}

}
