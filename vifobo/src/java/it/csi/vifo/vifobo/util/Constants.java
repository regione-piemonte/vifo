/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.util;

/**
 * The type Constants.
 *
 * @author CSI PIEMONTE
 */
public class Constants {
    /**
     * The constant COMPONENT_NAME.
     */
    public final static String COMPONENT_NAME = "vifobo";
    /**
     * The constant LOGGER_NAME.
     */
    public final static String LOGGER_NAME = "vifobo";

    /**
     * The constant HEADER_ATTORE_GESTIONE.
     */
    public static final String HEADER_ATTORE_GESTIONE = "Attore-Gestione";

    /**
     * The constant KEY_FILE_FORMAT.
     */
    public final static String KEY_FILE_FORMAT = "formati_allegato";
    /**
     * The constant KEY_FILE_SIZE_MAX.
     */
    public final static String KEY_FILE_SIZE_MAX = "max_dim_allegato";

    /**
     * The constant DATE_FORMAT.
     */
    public final static String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * The constant DATE_FORMAT_2.
     */
    public final static String DATE_FORMAT_2 = "dd/MM/yyyy";

    /**
     * The constant CONFIG_KEY_ALLEGATI_TENANT_UUID.
     */
    public static final String CONFIG_KEY_ALLEGATI_TENANT_UUID = "VIFO_INDEX_TENANT_UUID";
    /**
     * The constant CSV_ALLEGATI_COD_INFORMAZIONE_VIFO.
     */
    public final static String CSV_ALLEGATI_COD_INFORMAZIONE_VIFO = "CSV_ALLEGATI";
    /**
     * The constant CSV_ALLEGATI_KEY.
     */
    public final static String CSV_ALLEGATI_KEY = "Query";
    /**
     * The constant CSV_NOME_FILE.
     */
    public final static String CSV_NOME_FILE = "NomeFile";
    /**
     * The constant CSV_ALLEGATI_DELIMITER.
     */
    public final static String CSV_ALLEGATI_DELIMITER = ";";
    /**
     * The constant COD_CATEGORIA_ALLEGATI_SISTEMA.
     */
    public final static String COD_CATEGORIA_ALLEGATI_SISTEMA = "SYS"; // SISTEMA
    /**
     * The constant COD_TIPOLOGIA_ALLEGATI_ELENCO.
     */
    public final static String COD_TIPOLOGIA_ALLEGATI_ELENCO = "ELENCO_ALLEGATI"; // ELENCO ALLEGATI
    /**
     * The constant COD_TIPOLOGIA_ALLEGATI_MODULO_ISTANZA_FIRMATO.
     */
    public final static String COD_TIPOLOGIA_ALLEGATI_MODULO_ISTANZA_FIRMATO = "MOD_IST"; // MODULO ISTANZA FIRMATA


    /**
     * The constant CATEOGIRA_MODIFICA_REGIONALE_KEY.
     */
    public final static String CATEOGIRA_MODIFICA_REGIONALE_KEY = "CategoriaModificaRegionale";

    /**
     * The constant CATEOGIRA_MODIFICA_PROVINCIALE_KEY.
     */
    public final static String CATEOGIRA_MODIFICA_PROVINCIALE_KEY = "CategoriaModificaProvinciale";

    /**
     * The constant CATEOGIRA_MODIFICA_COMUNALE_KEY.
     */
    public final static String CATEOGIRA_MODIFICA_COMUNALE_KEY = "CategoriaModificaComunale";
    
    
 // Generic
 	public final static String UNO = "1";
 	public final static String ZERO = "0";
 	public final static String FLAG_TRUE = "1";
 	public final static String FLAG_FALSE = "0";
 	
 	// Applicativo
 	
 	// Index
 	public static final String INDEX_USERNAME_ADMIN = "admin@vifo";
 	public static final String INDEX_PSW = "vifo";
 	public static final String INDEX_UTENTE = "Utente Vifo";
 	public static final String INDEX_FRUITORE = "vifo";
 	public static final String INDEX_REPOSITORY = "primary";
 	
 	public static final String INDEX_ROOT_vifo = "/app:company_home/cm:vifo";
 	
 	public static final String INDEX_TYPE_FOTO_SPECIE = "/cm:foto_specie";
 	public static final String INDEX_DEFAULT_PREFIX_FOTO_SPECIE = "cm:1";
 	
 	public static final String INDEX_TYPE_DOMANDA = "/cm:domanda";
 	
 	public static final String INDEX_CM_NAME_VIFO ="@vifo";
 	
 	public static final String INDEX_PREFIX_MODEL_VIFO = "vifo:model";
 	
 	public static final String INDEX_PREFIX_TYPE_DOMANDA = "vifo:domanda";
 	public static final String INDEX_PREFIX_PROP_DOMANDA = "vifo:idAllegato";
 	
 	public static final String INDEX_PREFIX_TYPE_FOTO = "vifo:fotoSpecie";
 	public static final String INDEX_PREFIX_PROP_FOTO = "vifo:idFotoSpecie";

 	public static final String INDEX_PREFIX_VIFO = "vifo:";
 	public static final String INDEX_PREFIX_NAME_SHORT = "cm:name";
 	public static final String INDEX_PREFIX_CONTAINS = "cm:contains";
 	public static final String INDEX_PREFIX_NAME = "cm:content";
 	public static final String INDEX_PREFIX_MODEL = "cm:contentmodel";
 	public static final String INDEX_PREFIX_FOLDER = "cm:folder";
 	public static final String INDEX_DEFAULT_PREFIX = "cm:";
 	public static final String INDEX_DEFAULT_FOLDER_PREFIX = "/cm:";
 	public static final String INDEX_ENCODING = "UTF-8";
 	
 	public static final String MIMETYPE_DEFAULT = null;
 	public static final String INDEX_TYPE_INT = "d:int";
 	
 	public static final String USERINFO_SESSIONATTR = "appDatacurrentUser";
	
 // COSMO
 	public final static int POST_TIME_OUT = 120000;
 	public final static String SEMAFORO = "COSMO_VIFO";
 	public final static int COD_ERRORE_INTERNO    = 999;
 	
 	public static class URL
 	  {
 	    //public final static String URL_COSMO    = "URL_COSMO";
 	    public final static String URL_COSMO_PRATICA = "URL_COSMO_PRATICA";
 	    public final static String URL_COSMO_UPLOAD = "URL_COSMO_UPLOAD";
 	    public final static String URL_COSMO_DOCUMENTI = "URL_COSMO_DOCUMENTI";
 	    public final static String URL_COSMO_PROCESSO = "URL_COSMO_PROCESSO";
 	    public final static String URL_TOKEN = "URL_TOKEN";
 	    public final static String URL_INDEX = "index.ws.url";
 	    
 	  }
 	
 	public static class STEP
 	  {
 	    public final static int STEP_1    = 1;
 	    public final static int STEP_2    = 2;
 	    public final static int STEP_3    = 3;
 	    public final static int STEP_4    = 4;
 	    public final static int STEP_5    = 5;
 	  }
    
 	public static class PARAMETER
 	  {
 	    public final static int NUM_TENTATIVI    = 6;
 	    public final static String CODICEIPAENTE_COSMO = "CODICEIPAENTE_COSMO";
 	    public final static String OGGETTO_COSMO = "OGGETTO_COSMO";
 	    public final static String CODICETIPOLOGIA_COSMO = "CODICETIPOLOGIA_COSMO";
 	    public final static int INVIATO_COSMO_FILE    = 1;
 	    public final static int DA_INVIARE_COSMO    = 0;
 	    public final static int INVIATO_COSMO_PRATICA    = 2;
 	  }
}