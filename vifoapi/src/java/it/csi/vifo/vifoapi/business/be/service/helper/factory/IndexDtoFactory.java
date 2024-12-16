/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.helper.factory;

import org.apache.commons.io.FilenameUtils;

import it.csi.vifo.vifoapi.business.be.service.helper.dto.MetadatiIndexDomanda;
import it.csi.vifo.vifoapi.business.be.service.helper.dto.MetadatiIndexFotoSpecie;
import it.csi.vifo.vifoapi.business.be.service.helper.dto.MetadatiIndexPrimpa;
import it.csi.vifo.vifoapi.util.Constants;
import it.csi.vifo.vifoapi.util.IndexFileTypeEnum;
import it.doqui.index.ecmengine.client.webservices.dto.Node;
import it.doqui.index.ecmengine.client.webservices.dto.OperationContext;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Content;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Mimetype;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Property;
import it.doqui.index.ecmengine.client.webservices.dto.engine.search.SearchParams;

/**
 * Classe factory per <code>DTO</code> utilizzati nel contesto delle operazioni
 * col servizio <code>INDEX</code>.
 *
 * @author 1346
 */
public final class IndexDtoFactory {

	/**
	 * Parametro di Ricerca, Limite = 0.
	 */
	public static final int SEARCH_PARAM_LIMIT_0 = 0;

	/**
	 * Parametro di Ricerca, Limite = 1.
	 */
	public static final int SEARCH_PARAM_LIMIT_1 = 1;

	/**
	 * Crea e restituisce un'istanza {@link OperationContext}.
	 *
	 * @param user nome utente
	 * @return un'istanza {@link OperationContext}
	 */
	public static OperationContext createOperationContext(String user) {
		final OperationContext ctx = new OperationContext();
		ctx.setUsername(user);
		ctx.setPassword(Constants.INDEX_PSW);
		ctx.setNomeFisico(Constants.INDEX_UTENTE);
		ctx.setFruitore(Constants.INDEX_FRUITORE);
		ctx.setRepository(Constants.INDEX_REPOSITORY);

		return ctx;
	}

	/**
	 * Crea e restituisce un'istanza {@link SearchParams}.
	 *
	 * @param limit limite
	 * @return un'istanza {@link SearchParams}.
	 */
	public static SearchParams createSearchParams(int limit) {
		final SearchParams params = new SearchParams();
		params.setLimit(limit);

		return params;
	}

	/**
	 * Crea e restituisce un'istanza {@link SearchParams}.
	 *
	 * @return un'istanza {@link SearchParams}
	 */
	public static SearchParams createSearchParamsForRootFolder(String rootFolderName) {
		final SearchParams params = createSearchParams(SEARCH_PARAM_LIMIT_1);
		final String luceneQuery = createLuceneQueryForRootFolder(rootFolderName);

		params.setXPathQuery(luceneQuery);

		return params;
	}

	/**
	 * Crea e restituisce un'istanza {@link SearchParams}.
	 *
	 * @param metadati istanza di tipo {@link MetadatiIndex}
	 * @return un'istanza {@link SearchParams}
	 */

	/**
	 * Crea e restituisce un'istanza {@link SearchParams}.
	 *
	 * @param metadati istanza di tipo {@link MetadatiIndex}
	 * @return un'istanza {@link SearchParams}
	 */
	public static SearchParams createSearchParamsForIndexFolder(String luceneQuery) {
		final SearchParams params = createSearchParams(SEARCH_PARAM_LIMIT_1);

		params.setXPathQuery(luceneQuery);

		return params;
	}

	/**
	 * Crea e restituisce un'istanza {@link SearchParams}.
	 *
	 * @param idAllegato identificativo dell'Allegato da scaricare
	 * @return un'istanza {@link SearchParams}.
	 */
	public static SearchParams createSearchParamsForIndexUid(String cmName, String id, IndexFileTypeEnum fileType) {
		final SearchParams params = createSearchParams(SEARCH_PARAM_LIMIT_0);
		final String luceneQuery = createLuceneQueryForIndexDownload(cmName, id, fileType);

		params.setLuceneQuery(luceneQuery);

		return params;
	}


	/**
	 *
	 * @param folderName
	 * @return
	 */
	public static Property[] createContentFolderProperties(String folderName) {
		final Property p = new Property();
//		p.setPrefixedName(Constants.INDEX_PREFIX_NAME_SHORT);
		p.setDataType("text");
		p.setValues(new String[] { folderName });

		return new Property[] { p };
	}

	/**
	 * Crea e restituisce un'istanza {@link Node} per l'<code>UID</code> dato.
	 *
	 * @param uid l'<code>UID</code>
	 * @return un'istanza {@link Node}
	 */
	public static Node createNode(String uid) {
		return new Node(uid);
	}

	/**
	 * Crea e restituisce un'istanza {@link Mimetype} per un file del quale &egrave;
	 * stato indicato il nome.
	 *
	 * @param fileName nome del file
	 * @return un'istanza {@link Mimetype}
	 */
	public static Mimetype createMimetypeFromFileName(String fileName) {
		final Mimetype mimeType = new Mimetype();
		mimeType.setFileExtension(FilenameUtils.getExtension(fileName));

		return mimeType;
	}
	
	/**
	 * Crea e restituisce un'istanza {@link Content} per il <em>file</em> del quale
	 * &egrave; stato specificato il nome, il contenuto ed il mimeType.
	 *
	 * @param metadati metadati
	 * @param fileName nome del file
	 * @param file     contenuto del file del quale &egrave; stato indicato il nome
	 * @param mimeType mimeType
	 * @return un'istanza {@link Content}
	 */
	public static Content createContent(MetadatiIndexFotoSpecie metadati, String prefixModelName, String prefixName,
			String typeName, String fileName, byte[] file, String mimeType) {
		final Content content = createContent(prefixName, typeName, fileName, mimeType);
		content.setModelPrefixedName(prefixModelName);
		content.setProperties(createContentProperties(metadati));
		content.setContent(file);

		return content;
	}

	public static Content createContent() {
		final Content content = new Content();
		content.setContentPropertyPrefixedName(Constants.INDEX_PREFIX_NAME);
		return content;
	}

	/**
	 * Crea e restituisce un'istanza {@link Content} per il <em>file</em> del quale
	 * &egrave; stato specificato il nome ed il mimeType.
	 *
	 * @param fileName nome del file
	 * @param mimeType mimeType
	 * @return un'istanza {@link Content}
	 */
	public static Content createContent(String prefixName, String typeName, String fileName, String mimeType) {
		final Content content = createContent();
		content.setPrefixedName(prefixName + fileName);
		content.setParentAssocTypePrefixedName(Constants.INDEX_PREFIX_CONTAINS);
		content.setTypePrefixedName(typeName);
		content.setMimeType(mimeType);
		content.setEncoding(Constants.INDEX_ENCODING);

		return content;
	}


	/**
	 *
	 * @param metadati
	 * @return
	 */
	public static Property[] createContentProperties(MetadatiIndexFotoSpecie metadati) {
		final Property[] result = new Property[1];

		result[0] = new Property();
		result[0].setDataType(Constants.INDEX_TYPE_INT);
		result[0].setPrefixedName(MetadatiIndexFotoSpecie.META_ID_FOTO_SPECIE);
		result[0].setValues(new String[] { metadati.getIdSpecie() });
		return result;
	}
	
	/**
	 *
	 * @param metadati
	 * @return
	 */
	public static Property[] createContentProperties(MetadatiIndexDomanda metadati) {
		final Property[] result = new Property[1];

		result[0] = new Property();
		result[0].setDataType(Constants.INDEX_TYPE_INT);
		result[0].setPrefixedName(MetadatiIndexDomanda.META_ID_ALLEGATO);
		result[0].setValues(new String[] { metadati.getIdAllegato() });
		return result;
	}

	/**
	 *
	 * @return
	 */
	public static String createLuceneQueryForRootFolder(String rootFolderName) {
		final StringBuilder luceneQuery = new StringBuilder(rootFolderName);

		return luceneQuery.toString();
	}

	

	/**
	 *
	 * @param idAllegato
	 * @return
	 */
	private static String createLuceneQueryForIndexDownload(String cmName, String id, IndexFileTypeEnum fileType) {
		final StringBuilder luceneQuery = new StringBuilder();

		if(IndexFileTypeEnum.ALLEGATO == fileType) {
			luceneQuery.append(cmName + "\\:idAllegato:\"").append(id).append("\"");
		}else if(IndexFileTypeEnum.IMMAGINE == fileType) {
			luceneQuery.append(cmName + "\\:idFotoSpecie:\"").append(id).append("\"");
		}
		

		return luceneQuery.toString();
	}

	/**
	 *
	 * @param metadati
	 * @param fileName
	 * @return
	 */

	/**
	 * Constructor.
	 */
	private IndexDtoFactory() {
		/* NOP */
	}

}
