/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import it.csi.vifo.vifoapi.util.Constants;


public class BaseMapper {
	
	protected static final Logger LOG = Logger.getLogger(Constants.LOGGER_NAME);
	
	private static final SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

	Integer getInteger(ResultSet rs, String fieldName) throws SQLException{
		if(rs.getString(fieldName) != null) {
			return rs.getInt(fieldName);
		}
		return null;
	}
	
	String getFormatedDate(Date date) {
		if(date == null) {
			return "";
		}
		return sf.format(date);
	}
	
	public final java.util.Map<String, String> columnsToReadMap = new java.util.HashMap<String, String>();

	/**
	 * questo flag, se impostato a true nell'istanza del mapper, fa in modo che
	 * siano lette tutte le colonne.
	 * Serve per ottimizzare la logica ed evitare che vengano testate inutilmente
	 * le condizioni sulle varie colonne effettive.
	 * @generated
	 */
	public boolean mapAllColumns = true;

	/**
	 * La classe del DTO che il mapper deve istanziare durante la ricostruzione del
	 * risultato
	 * @generated
	 */
	public Class dtoClass;

	/**
	 * Costruttore del RowMapper. 
	 * Permette di specificare:
	 * - l'elenco delle colonne da in cludere nel mapping (per query incomplete,
	 *   esempio: distinct, custom select, ....)
	 * - la classe del DTO da istanziare al momento della ricostruzione del risultato
	 * @generated
	 * @param columnsToRead elenco delle colonne da includere nel mapping (per query
	 *        incomplete, esempio distinct, custom select...)
	 * @param dtoClass la classe del DTO da istanziare al momento della ricostruzione del 
	 *        risultato
	 */
	public BaseMapper(String[] columnsToRead, Class dtoClass) {
		if (columnsToRead != null) {
			mapAllColumns = false;
			for (int i = 0; i < columnsToRead.length; i++)
				columnsToReadMap.put(columnsToRead[i], columnsToRead[i]);
		}
		this.dtoClass = dtoClass;
	}
	
	public BaseMapper() {}
	
	/**
	 * Istanzia un DTO nuovo (vuoto) del tipo specificato al momento della creazione del 
	 * RowMapper
	 * @return l'oggetto istanziato (del tipo specificato in dtoClass)
	 * @generated
	 */
	public Object getNewDto() {
		Object dtoInstance = null;

		try {
			dtoInstance = dtoClass.newInstance();
			return dtoInstance;
		} catch (InstantiationException e) {
			LOG.error("Si e' verificato un errore nella chiamata al dao supplier: ", e);
			throw new RuntimeException("Impossibile istanziare la classe " + dtoClass.getName() + " ," + e.getCause());
		} catch (IllegalAccessException e) {
			LOG.error("Si e' verificato un errore nella chiamata al dao supplier: ", e);
			throw new RuntimeException("Impossibile accedere alla classe " + dtoClass.getName() + " ," + e.getCause());
		}
	}
}
