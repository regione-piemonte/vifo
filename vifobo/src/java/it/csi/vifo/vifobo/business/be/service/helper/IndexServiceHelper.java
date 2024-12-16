/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.service.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import it.csi.vifo.vifobo.business.be.exception.ServiceException;
import it.csi.vifo.vifobo.business.be.service.helper.dto.MetadatiIndexDomanda;
import it.csi.vifo.vifobo.business.be.service.helper.dto.MetadatiIndexFotoSpecie;
import it.csi.vifo.vifobo.business.be.service.helper.factory.IndexDtoFactory;
import it.csi.vifo.vifobo.util.Constants;
import it.csi.vifo.vifobo.util.ConvertUtil;
import it.csi.vifo.vifobo.util.DateUtil;
import it.csi.vifo.vifobo.util.IndexFileTypeEnum;
import it.doqui.index.ecmengine.client.webservices.dto.Node;
import it.doqui.index.ecmengine.client.webservices.dto.OperationContext;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Content;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Mimetype;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Property;
import it.doqui.index.ecmengine.client.webservices.dto.engine.search.ResultContent;
import it.doqui.index.ecmengine.client.webservices.dto.engine.search.SearchParams;
import it.doqui.index.ecmengine.client.webservices.dto.engine.search.SearchResponse;
import it.doqui.index.ecmengine.client.webservices.engine.EcmEngineWebServiceDelegate;
import it.doqui.index.ecmengine.client.webservices.engine.EcmEngineWebServiceDelegateServiceLocator;
import it.doqui.index.ecmengine.client.webservices.exception.InvalidParameterException;
import it.doqui.index.ecmengine.client.webservices.exception.publishing.InvalidCredentialsException;
import it.doqui.index.ecmengine.client.webservices.exception.publishing.NoDataExtractedException;

public class IndexServiceHelper extends AbstractServiceHelper {

	
	EcmEngineWebServiceDelegate ecmEngineWebServiceDelegate;
	
	
	public String getSignatureType(byte[] doc) throws InvalidCredentialsException, InvalidParameterException, RemoteException {
		return this.ecmEngineWebServiceDelegate.getSignatureType(doc, indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN));
	}

	/**
	 * @return the ecmEngineWebServiceDelegate
	 */
	public EcmEngineWebServiceDelegate getEcmEngineWebServiceDelegate() {
		return ecmEngineWebServiceDelegate;
	}

	/**
	 * @param ecmEngineWebServiceDelegate 
	 *            the ecmEngineWebServiceDelegate to set
	 */
	public void setEcmEngineWebServiceDelegate(EcmEngineWebServiceDelegate ecmEngineWebServiceDelegate) {
		this.ecmEngineWebServiceDelegate = ecmEngineWebServiceDelegate;
	}

	public IndexServiceHelper(String url) {
		EcmEngineWebServiceDelegateServiceLocator ecmengineLocator = new EcmEngineWebServiceDelegateServiceLocator();

		try {
			ecmEngineWebServiceDelegate = ecmengineLocator.getEcmEngineManagement(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (javax.xml.rpc.ServiceException e) {
			e.printStackTrace();
		}
	}

	private OperationContext indexGetOperationContext(String user) {
		LOGGER.debug("[IndexServiceHelper::indexGetOperationContext] BEGIN");
		final OperationContext ctx = new OperationContext();
		ctx.setUsername(user);
		ctx.setPassword(Constants.INDEX_PSW);
		ctx.setNomeFisico(Constants.INDEX_UTENTE);
		ctx.setFruitore(Constants.INDEX_FRUITORE);
		ctx.setRepository(Constants.INDEX_REPOSITORY);
		LOGGER.debug("[IndexServiceHelper::indexGetOperationContext] END");
		return ctx;
	}

	private Content indexGetContent(String fileName, String typeName, String prefixName) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetContent] BEGIN");
		final Content c = new Content();
		c.setContentPropertyPrefixedName(Constants.INDEX_PREFIX_NAME);
		c.setPrefixedName(prefixName + fileName);
		c.setParentAssocTypePrefixedName(Constants.INDEX_PREFIX_CONTAINS);
		c.setTypePrefixedName(typeName);
		c.setMimeType(this.indexGetMimeType(fileName));
		c.setEncoding(Constants.INDEX_ENCODING);
		LOGGER.debug("[IndexServiceHelper::indexGetContent] END");
		return c;
	}

	private Content indexGetContentFolder(String folderName) {
		LOGGER.debug("[IndexServiceHelper::indexGetContentFolder] BEGIN");
		final Content myFolder = new Content();
		myFolder.setPrefixedName(Constants.INDEX_DEFAULT_PREFIX + folderName);
		myFolder.setParentAssocTypePrefixedName(Constants.INDEX_PREFIX_CONTAINS);
		myFolder.setModelPrefixedName(Constants.INDEX_PREFIX_MODEL);
		myFolder.setTypePrefixedName(Constants.INDEX_PREFIX_FOLDER);
		final Property p = new Property();
		p.setPrefixedName(Constants.INDEX_PREFIX_NAME_SHORT);
		p.setDataType("text");
		p.setValues(new String[] { folderName });
		myFolder.setProperties(new Property[] { p });
		LOGGER.debug("[IndexServiceHelper::indexGetContentFolder] END");
		return myFolder;
	}
	private String indexGetMimeType(String fileName) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetMimeType] BEGIN");
		final String estensione = StringUtils.substringAfterLast(fileName, ".");
		final Mimetype mt = new Mimetype();
		mt.setFileExtension(estensione);
		Mimetype[] mime = null;
		try {
			mime = this.getEcmEngineWebServiceDelegate().getMimetype(mt);
		} catch (InvalidParameterException e) {
			throw new ServiceException("[Errore INDEX indexGetMimeType InvalidParameterException] " + e.getMessage());
		} catch (RemoteException e) {
			throw new ServiceException("[Errore INDEX indexGetMimeType RemoteException] " + e.getMessage());
		} catch (Exception e) {
			throw new ServiceException("[Errore INDEX indexGetMimeType Exception] " + e.getMessage());
		}

		if (mime != null && mime.length > 0) {
			return mime[0].getMimetype();
		} else {
			return Constants.MIMETYPE_DEFAULT;
		}
	}

	private Content indexCreateContent(MetadatiIndexFotoSpecie metadati, String fileName, byte[] file) throws ServiceException {

		LOGGER.debug("[IndexServiceHelper::indexGetContent] BEGIN");
		final Content c = this.indexGetContent(fileName, Constants.INDEX_PREFIX_TYPE_FOTO,
				Constants.INDEX_PREFIX_VIFO);
		
		c.setModelPrefixedName(Constants.INDEX_PREFIX_MODEL_VIFO);
		
		c.setProperties(IndexDtoFactory.createContentProperties(metadati));
		c.setContent(file);
		LOGGER.debug("[IndexServiceHelper::indexGetContent] END");
		return c;
	}
	
	private Content indexCreateContent(MetadatiIndexDomanda metadati, String fileName, byte[] file) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetContent] BEGIN");
		final Content c = this.indexGetContent(fileName, Constants.INDEX_PREFIX_TYPE_DOMANDA,
				Constants.INDEX_PREFIX_VIFO);
		
		c.setModelPrefixedName(Constants.INDEX_PREFIX_MODEL_VIFO);
		
		c.setProperties(IndexDtoFactory.createContentProperties(metadati));
		c.setContent(file);
		LOGGER.debug("[IndexServiceHelper::indexGetContent] END");
		return c;
	}

	private String indexGetRootFolder(String rootFolderName) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetRootFolder] BEGIN");
		LOGGER.info("[IndexServiceHelper::indexGetRootFolder] rootFolderName: " + rootFolderName);
		try {
			final SearchParams params = new SearchParams();

			params.setLimit(1);

			final StringBuilder query = new StringBuilder();
			query.append(rootFolderName);

			params.setXPathQuery(query.toString());
			return this.ecmEngineWebServiceDelegate.nodeExists(params,
					indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("[Errore INDEX indexGetRootFolder Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexGetRootFolder] END");
		}
	}

	private String indexSearchFolder(SearchParams params, String folderName, String uidParent) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexSearchFolder] BEGIN");

		LOGGER.debug("[IndexServiceHelper::indexSearchFolder] folderName " + folderName);
		LOGGER.debug("[IndexServiceHelper::indexSearchFolder] uidParent " + uidParent);

		try {
			LOGGER.debug("[IndexServiceHelper::this.getEcmEngineWebServiceDelegate()] *"
					+ this.getEcmEngineWebServiceDelegate() + "*");
			return this.getEcmEngineWebServiceDelegate().nodeExists(params,
					indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN));
		} catch (NoDataExtractedException e) {
			e.printStackTrace();
			LOGGER.error("[IndexServiceHelper::indexSearchFolder] ERROR: " + e.getMessage(), e);

			if (uidParent == null) {
				throw new ServiceException("[Errore INDEX indexSearchFolder uidParent null ] " + e.getMessage());
			} else {
				return this.indexCreateFolder(folderName, uidParent);
			}
		} catch (RuntimeException e) {
			LOGGER.error("[IndexServiceHelper::indexSearchFolder] ERROR RUNTIME: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexGetMimeType RuntimeException] " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexSearchFolder] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexSearchFolder Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexSearchFolder] END");
		}
	}

	private String indexCreateFolder(String folderName, String uidParent) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexCreateFolder] BEGIN");

		String uidFolder = null;
		try {
			uidFolder = this.getEcmEngineWebServiceDelegate()
					.createContent(new Node(uidParent), this.indexGetContentFolder(folderName),
							this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN))
					.getUid();
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexCreateFolder] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexCreateFolder Exception] " + e.getMessage());
		}

		LOGGER.debug("[IndexServiceHelper::indexCreateFolder] END");

		return uidFolder;
	}
	
	public String indexGetFolder(MetadatiIndexDomanda metadati) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetFolder] BEGIN");
		try {
			final String annoCorrente = ConvertUtil.convertToString(DateUtil.getAnnoCorrente());
			final StringBuilder luceneQuery = new StringBuilder();
			String baseUrl;
			String uidFolder;
			// Path /app:company_home/cm:foto_specie
			luceneQuery.append(Constants.INDEX_ROOT_vifo);
			//cm:domanda
			luceneQuery.append(Constants.INDEX_TYPE_DOMANDA);
			baseUrl = luceneQuery.toString();
			//cm:<anno>
			luceneQuery.append(Constants.INDEX_DEFAULT_FOLDER_PREFIX).append(annoCorrente);
			
			uidFolder = this.indexSearchFolder(
				IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()), annoCorrente,
				this.indexGetRootFolder(baseUrl));
			
			baseUrl = luceneQuery.toString();
			//cm:<id_domanda>
			luceneQuery.append(Constants.INDEX_DEFAULT_FOLDER_PREFIX).append(metadati.getIdDomanda());
			uidFolder = this.indexSearchFolder(
				IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()), metadati.getIdDomanda(),
				this.indexGetRootFolder(baseUrl));
			
			baseUrl = luceneQuery.toString();
			//cm:<tipoAllegato>
			luceneQuery.append(Constants.INDEX_DEFAULT_FOLDER_PREFIX).append(metadati.getTipoAllegato());

			uidFolder = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()), metadati.getTipoAllegato(),
					this.indexGetRootFolder(baseUrl));
			
			return uidFolder;
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexGetFolder] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexGetFolder Exception] " + e.getMessage());

		} finally {
			LOGGER.debug("[IndexServiceHelper::indexGetFolder] END");
		}
	}

	public String indexGetFolder(MetadatiIndexFotoSpecie metadati) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetFolder] BEGIN");

		try {
			final StringBuilder luceneQuery = new StringBuilder();
			String baseUrl;
			String uidFolder;
			// Path /app:company_home/cm:vifo 
			luceneQuery.append(Constants.INDEX_ROOT_vifo);
			//cm:foto_specie 
			luceneQuery.append(Constants.INDEX_TYPE_FOTO_SPECIE);
			
			baseUrl = luceneQuery.toString();
			//cm:<id_specie>
			luceneQuery.append(Constants.INDEX_DEFAULT_FOLDER_PREFIX).append(metadati.getIdSpecie());
			uidFolder = this.indexSearchFolder(
				IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()), metadati.getIdSpecie(),
				this.indexGetRootFolder(baseUrl));
			
			baseUrl = luceneQuery.toString();
			//cm:<id_tipo_specie>
			luceneQuery.append(Constants.INDEX_DEFAULT_FOLDER_PREFIX).append(metadati.getIdTipoSpecie());

			uidFolder = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()), metadati.getIdTipoSpecie(),
					this.indexGetRootFolder(baseUrl));
			
			return uidFolder;
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexGetFolder] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexGetFolder Exception] " + e.getMessage());

		} finally {
			LOGGER.debug("[IndexServiceHelper::indexGetFolder] END");
		}
	}
	
	public String indexUploadFile(String fileName, byte[] file, MetadatiIndexDomanda metadati) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexUploadFile] BEGIN");
		String uidFile = null;
		try {
			final Node n = new Node(this.indexGetFolder(metadati));
			final Content c = this.indexCreateContent(metadati, fileName, file);
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);
			final Node nodoFile = this.ecmEngineWebServiceDelegate.createContent(n, c, oc);

			uidFile = nodoFile.getUid();
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexUploadFile] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexUploadFile Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexUploadFile] END");
		}
		return uidFile;
	}

	public String indexUploadFile(String fileName, byte[] file, MetadatiIndexFotoSpecie metadati) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexUploadFile] BEGIN");
		String uidFile = null;
		try {
			final Node n = new Node(this.indexGetFolder(metadati));
			final Content c = this.indexCreateContent(metadati, fileName, file);
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);
			final Node nodoFile = this.ecmEngineWebServiceDelegate.createContent(n, c, oc);

			uidFile = nodoFile.getUid();
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexUploadFile] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexUploadFile Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexUploadFile] END");
		}
		return uidFile;
	}

	public String indexDeleteFile(String uid) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDeleteFile] BEGIN");
		try {
			if (StringUtils.isNotEmpty(uid)) {
				this.getEcmEngineWebServiceDelegate().deleteContent(new Node(uid),
						this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN));
			}

			return uid;
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexDeleteFile] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexDeleteFile Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexDeleteFile] END");
		}
	}

	public void indexDeleteFileByIdAllegato(String cmName, String id, IndexFileTypeEnum fileType) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDeleteFileByIdAllegato] BEGIN");
		String uuidd = this.indexGetUidAllegato(cmName, id, fileType);
		String k = this.indexDeleteFile(uuidd);
		LOGGER.debug("[IndexServiceHelper::indexDeleteFileByIdAllegato] END");
	}

	private String indexGetUidAllegato(String cmName, String id, IndexFileTypeEnum fileType) throws ServiceException {
		return this.indexGetUidAllegato(IndexDtoFactory.createSearchParamsForIndexUid(cmName, id, fileType));
	}

	private String indexGetUidAllegato(SearchParams params) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetUidAllegato] BEGIN");

		String uid = null;
		try {
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);

			final SearchResponse response = this.getEcmEngineWebServiceDelegate().luceneSearch(params, oc);

			final ResultContent[] results = response.getResultContentArray();

			if (ArrayUtils.isNotEmpty(results)) {
				uid = results[0].getUid();

			}

			return uid;
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexGetUidAllegato] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexGetUidAllegato Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexGetUidAllegato] END");
		}
	}
	
	public byte[] indexDownloadFileByUid(String uid) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDownloadFileByUid] START");
		byte[] result = null;
		try {
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);

			result = this.ecmEngineWebServiceDelegate.retrieveContentData(IndexDtoFactory.createNode(uid),
					IndexDtoFactory.createContent(), oc);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("[IndexServiceHelper::indexDownloadFileByUid] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexDownloadFileByUid Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexDownloadFileByUid] END");
		}
		return result;
	}

	public byte[] indexDownloadFile(String id, IndexFileTypeEnum fileType) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDownloadFile]");
		return this.indexDownloadFile(Constants.INDEX_CM_NAME_VIFO, id, fileType);
	}

	
	private byte[] indexDownloadFile(String cmName, String id, IndexFileTypeEnum fileType) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDownloadImage] END");
		byte[] result = null;
		try {
			final String uidAllegato = this.indexGetUidAllegato(cmName, id, fileType);
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);

			result = this.ecmEngineWebServiceDelegate.retrieveContentData(IndexDtoFactory.createNode(uidAllegato),
					IndexDtoFactory.createContent(), oc);
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexDownloadImage] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexDownloadImage Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexDownloadImage] END");
		}
		return result;
	}
	

}
