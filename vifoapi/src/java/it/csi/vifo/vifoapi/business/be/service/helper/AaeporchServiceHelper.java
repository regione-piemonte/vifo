/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang3.StringUtils;

import it.csi.aaep.aaeporch.business.Comune;
import it.csi.aaep.aaeporch.business.Impresa;
import it.csi.aaep.aaeporch.business.OrchestratoreImplService;
import it.csi.aaep.aaeporch.business.OrchestratoreIntf;
import it.csi.aaep.aaeporch.business.Persona;
import it.csi.aaep.aaeporch.business.PersonaINFOC;
import it.csi.aaep.aaeporch.business.Provincia;
import it.csi.aaep.aaeporch.business.Regione;
import it.csi.aaep.aaeporch.business.StatoCessazione;
import it.csi.aaep.aaeporch.business.TipologiaFonte;
import it.csi.aaep.aaeporch.business.Utente;

/**
 * The type Aaep service helper.
 *
 * @author CSI PIEMONTE
 */
public class AaeporchServiceHelper extends AbstractServiceHelper {

    private static final String AAEP_NO_RECORD_FOUND_EXCEPTION_MSG = "Nessun record trovato";

    private final OrchestratoreIntf service;

    /**
     * The Url service.
     */
    protected String urlService = "";

    /**
     * Instantiates a new Aaep service helper.
     *
     * @param urlService url del servizio
     */
    public AaeporchServiceHelper(String urlService) {
        this.urlService = urlService;
        this.service = this.getService(urlService);
    }

    private OrchestratoreIntf getService(String urlService) {
        LOGGER.debug("[AaeporchServiceHelper::getService] BEGIN");
        OrchestratoreIntf aaepService = null;
        try {
            OrchestratoreImplService orchestratoreImplService = new OrchestratoreImplService(new URL(urlService));
            aaepService = orchestratoreImplService.getOrchestratoreImplPort();

            LOGGER.debug("[AaeporchServiceHelper::getService] Service 'JavaServiceDesc' INITIALIZED");
            LOGGER.debug("[AaeporchServiceHelper::getService] END");
        } catch (MalformedURLException e) {
            LOGGER.error("[AaeporchServiceHelper::getService] ERROR : invalid url [" + urlService + "]");
        } catch (Exception e) {
            LOGGER.error("[AaeporchServiceHelper::getService] ERROR : ", e);
        }
        return aaepService;
    }


    /**
     * Gets dettaglio impresa.
     *
     * @param codiceFiscaleSoggetto cf soggetto
     * @return Impresa dettaglio impresa
     * @throws ServiceException ServiceException
     */
    public Impresa getDettaglioImpresa(String codiceFiscaleSoggetto) throws ServiceException {
        LOGGER.debug("[AaeporchServiceHelper::getDettaglioImpresa] BEGIN");
        Impresa result = null;
        if(service == null) {
            throw new ServiceException("[AaeporchServiceHelper::getDettaglioImpresa] ERROR : service unavailable");
        }

        try {
            result = this.service.getDettaglioImpresa(null, "INFOC", codiceFiscaleSoggetto);
        } catch (Exception e) {
            if (StringUtils.startsWithIgnoreCase(e.getMessage(), AAEP_NO_RECORD_FOUND_EXCEPTION_MSG)) {
                return null;
            } else {
                throw new ServiceException("[Errore AAEP getDettaglioImpresa] " + e.getMessage());
            }
        } finally {
            LOGGER.debug("[AaeporchServiceHelper::getDettaglioImpresa] END");
        }
        return result;
    }

   

    /**
     * Cerca persone infoc list.
     *
     * @param utente                   utente
     * @param codiceFiscaleImpresa     codiceFiscaleImpresa
     * @param soloRappresentantiLegali soloRappresentantiLegali
     * @return List<Persona> list
     * @throws ServiceException ServiceException
     */
    public List<Persona> cercaPersoneInfoc(Utente utente, String codiceFiscaleImpresa, Boolean soloRappresentantiLegali) throws ServiceException {
        LOGGER.debug("[AaeporchServiceHelper::cercaPersoneInfoc] BEGIN");
        if(service == null) {
            throw new ServiceException("[AaeporchServiceHelper::cercaPersoneInfoc] ERROR : service unavailable");
        }
        try {
            return this.service.cercaPersoneInfoc(null, codiceFiscaleImpresa, soloRappresentantiLegali);
        } catch (Exception e) {
            if (StringUtils.startsWithIgnoreCase(e.getMessage(), AAEP_NO_RECORD_FOUND_EXCEPTION_MSG)) {
                return null;
            } else {
                throw new ServiceException("[Errore AAEP cercaPersoneInfoc] " + e.getMessage());
            }
        } finally {
            LOGGER.debug("[AaeporchServiceHelper::cercaPersoneInfoc] END");
        }
    }


   


    /**
     * Load dettaglio persone infoc persona infoc.
     *
     * @param utente  utente
     * @param persona persona
     * @return PersonaINFOC persona infoc
     * @throws ServiceException ServiceException
     */
    public PersonaINFOC loadDettaglioPersoneInfoc(Utente utente, Persona persona) throws ServiceException {
        LOGGER.debug("[AaeporchServiceHelper::loadDettaglioPersoneInfoc] BEGIN");
        if (service == null) {
            throw new ServiceException("[AaeporchServiceHelper::loadDettaglioPersoneInfoc] ERROR : service unavailable");
        }
        try {
            return (PersonaINFOC)this.service.getDettaglioPersonaInfoc(utente, persona);
        } catch (Exception e) {
            if (StringUtils.startsWithIgnoreCase(e.getMessage(), AAEP_NO_RECORD_FOUND_EXCEPTION_MSG)) {
                return null;
            } else {
                throw new ServiceException("[AaeporchServiceHelper::loadDettaglioPersoneInfoc] ERRORE : " + e.getMessage());
            }
        } finally {
            LOGGER.debug("[AaeporchServiceHelper::loadDettaglioPersoneInfoc] END");
        }
    }
    
    
    /**
     * cerca imprese by owner codiceFiscale.
     *
     * @param String  cfUtenteUtilizzatoreServizio
     * @param String String cfPersonaFisica
     * @return List<Impresa>
     * @throws ServiceException ServiceException
     */
    public List<Impresa> cercaImpreseByOwnerCodiceFiscale(String cfUtenteUtilizzatoreServizio, String cfPersonaFisica) throws ServiceException {
        LOGGER.debug("[AaeporchServiceHelper::cercaImpreseByOwnerCodiceFiscale] BEGIN");
        if (service == null) {
            throw new ServiceException("[AaeporchServiceHelper::cercaImpreseByOwnerCodiceFiscale] ERROR : service unavailable");
        }
        try {
        	Utente utente = new Utente();
        	utente.setCodiceFiscaleUtente(cfUtenteUtilizzatoreServizio);
        	
            return this.service.cercaImprese(utente, TipologiaFonte.AAEP, StatoCessazione.NON_CESSATA, null, null, null, null, null, null, null, cfPersonaFisica, 
            		null, null, null, null, null);
        } catch (Exception e) {
            if (AAEP_NO_RECORD_FOUND_EXCEPTION_MSG.equalsIgnoreCase(e.getMessage())) {
                return null;
            } else {
                throw new ServiceException("[AaeporchServiceHelper::cercaImpreseByOwnerCodiceFiscale] ERRORE : " + e.getMessage());
            }
        } finally {
            LOGGER.debug("[AaeporchServiceHelper::cercaImpreseByOwnerCodiceFiscale] END");
        }
    }
    
    /**
     * get elenco province in Regione.
     *
     * @param String  cfUtenteUtilizzatoreServizio
     * @return List<Provincia>
     * @throws ServiceException ServiceException
     */
    public List<Provincia> getElencoProvinceInRegione(String cfUtenteUtilizzatoreServizio) throws ServiceException {
        LOGGER.debug("[AaeporchServiceHelper::getElencoProvinceInRegione] BEGIN");
        if (service == null) {
            throw new ServiceException("[AaeporchServiceHelper::getElencoProvinceInRegione] ERROR : service unavailable");
        }
        try {
        	Utente utente = new Utente();
        	utente.setCodiceFiscaleUtente(cfUtenteUtilizzatoreServizio);
        	
        	List<Regione> listRegioni = this.service.getElencoRegioni(utente);
        	LOGGER.info("getElencoRegioni ok! Size: " + listRegioni.size());
        	
            return this.service.getElencoProvinceInRegione(utente, "01");
        } catch (Exception e) {
            if (AAEP_NO_RECORD_FOUND_EXCEPTION_MSG.equalsIgnoreCase(e.getMessage())) {
                return null;
            } else {
                throw new ServiceException("[AaeporchServiceHelper::getElencoProvinceInRegione] ERRORE : " + e.getMessage());
            }
        } finally {
            LOGGER.debug("[AaeporchServiceHelper::getElencoProvinceInRegione] END");
        }
    }
    
    /**
     * get elenco comuni in provincia
     *
     * @param String  cfUtenteUtilizzatoreServizio
     * @param String provincia
     * @return List<Comune>
     * @throws ServiceException ServiceException
     */
    public List<Comune> getElencoComuniByIstatProv(String cfUtenteUtilizzatoreServizio, String istatProv) throws ServiceException {
        LOGGER.debug("[AaeporchServiceHelper::getElencoComuniByIstatProv] BEGIN");
        if (service == null) {
            throw new ServiceException("[AaeporchServiceHelper::getElencoComuniByIstatProv] ERROR : service unavailable");
        }
        try {
        	Utente utente = new Utente();
        	utente.setCodiceFiscaleUtente(cfUtenteUtilizzatoreServizio);
        	LOGGER.info("getElencoComuniByIstatProv - istatProv: " + istatProv);
            return this.service.getElencoComuniInProvincia(utente, istatProv);
        } catch (Exception e) {
            if (AAEP_NO_RECORD_FOUND_EXCEPTION_MSG.equalsIgnoreCase(e.getMessage())) {
                return null;
            } else {
                throw new ServiceException("[AaeporchServiceHelper::getElencoComuniByIstatProv] ERRORE : " + e.getMessage());
            }
        } finally {
            LOGGER.debug("[AaeporchServiceHelper::getElencoComuniByIstatProv] END");
        }
    }

   


   
}