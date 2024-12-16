/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.PlainUtenteApi;
import it.csi.vifo.vifoapi.business.be.exception.ValidationException;
import it.csi.vifo.vifoapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.SoggettoDAO;
import it.csi.vifo.vifoapi.business.be.service.WsService;
import it.csi.vifo.vifoapi.business.be.service.impl.WsServiceImpl;
import it.csi.vifo.vifoapi.business.be.thread.UpdateComuniRunnable;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.PlainUtenteSoggetto;
import it.csi.vifo.vifoapi.dto.TSoggetto;
import it.csi.vifo.vifoapi.dto.UserInfo;
import it.csi.vifo.vifoapi.dto.Utente;
import it.csi.vifo.vifoapi.errors.ErrorConstants;
import it.csi.vifo.vifoapi.rest.BaseResponses;
import it.csi.vifo.vifoapi.util.Constants;
import it.csi.vifo.vifoapi.util.ProfiloUtenteEnum;
import it.csi.vifo.vifoapi.util.ValidationUtil;

@Component
public class PlainUtenteApiServiceImpl extends BaseApiServiceImpl implements PlainUtenteApi {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	public static final String UPDATE_USER_DONE = "updateUserDone";
	
	private static boolean primoCaricamento = true;

	@Autowired
	private SoggettoDAO soggettoDAO;

	@Autowired
	private ConfigUtenteDAO configUtenteDAO;
	
	@Autowired
	private WsService wsService;


	@Override
	public Response searchUtente(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
		
			if(primoCaricamento) {
				primoCaricamento = false;
				Thread t = new Thread(new UpdateComuniRunnable(wsService));
				   t.start();
			}
	
			PlainUtenteSoggetto plainUtenteSoggetto = new PlainUtenteSoggetto();
	
			UserInfo userInfo = (UserInfo)httpRequest.getSession().getAttribute(Constants.USERINFO_SESSIONATTR);
	
		
				TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(userInfo.getCodFisc());
				
				if(soggetto != null) {
					ConfigUtente configUtente;
					Integer tipoProfilo = null;
					configUtente = configUtenteDAO.getConfigUtenteBySoggettoId(soggetto.getIdSoggetto());
					tipoProfilo = configUtente.getFkTipoProfilo();
					plainUtenteSoggetto.setFlgPrivacy(configUtente.getFlgTrattamentoDatiPersonali());
					plainUtenteSoggetto.setFkVivaio(configUtente.getFkVivaio());
					
					plainUtenteSoggetto.setIdSoggetto(soggetto.getIdSoggetto());
					plainUtenteSoggetto.setIdConfigUtente(soggetto.getFkConfigUtente());
					plainUtenteSoggetto.setCodiceFiscale(soggetto.getCodiceFiscale());
					plainUtenteSoggetto.setCognome(soggetto.getCognome());
					plainUtenteSoggetto.setNome(soggetto.getNome());
					plainUtenteSoggetto.setRuolo(userInfo.getRuolo());
					plainUtenteSoggetto.setProfile(tipoProfilo);
					plainUtenteSoggetto.setProvider(userInfo.getCommunity());				
					plainUtenteSoggetto.setNrTelefonico(soggetto.getTelefono());
					plainUtenteSoggetto.setEmail(soggetto.getMail());
				} else {
					Utente utente = new Utente();
					utente.setCodiceFiscale(userInfo.getCodFisc());
					utente.setNome(userInfo.getNome());
					utente.setCognome(userInfo.getCognome());
					utente.setRuolo(userInfo.getRuolo());
					// Added on 02.07.2020 - customer instruction
					utente.setProvider(userInfo.getCommunity());
					return Response.ok(utente).build();
				}
				return Response.ok(plainUtenteSoggetto).build();
			} catch (Exception e) {
				LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
				return errorResponse(e);
			}finally {
				LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
			}
			
	}

	@Override
	public Response createUtente(PlainUtenteSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));			
			utenteValidation(body);
			TSoggetto soggetto = new TSoggetto();		
			
			soggetto.setCodiceFiscale(body.getCodiceFiscale());
			soggetto.setCognome(body.getCognome());
			soggetto.setNome(body.getNome());
			soggetto.setTelefono(body.getNrTelefonico());
			soggetto.setMail(body.getEmail());
			soggetto.setDataInserimento(LocalDate.now());
			soggetto.setFlgEntePubblico((byte) 0);

			int idSoggetto = soggettoDAO.createSoggettoFisica(soggetto);
			
			createConfigUtente(body.getFlgPrivacy(), idSoggetto);
			
			checkCaricamenti();
			httpRequest.getSession().setAttribute(UPDATE_USER_DONE, true);
			return Response.ok().build();
		} catch(Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e));
			return errorResponse(e);
		}
		finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
		
	}
	
	private void createConfigUtente(byte privacy, int idSoggetto) {
		ConfigUtente configUtente = new ConfigUtente();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		configUtente.setDataPrimoAccesso(now);
		configUtente.setDataUltimoAccesso(now);
		configUtente.setNrAccessi(1);
		configUtente.setFkTipoProfilo(ProfiloUtenteEnum.RICHIEDENTE.getValue());
		configUtente.setFlgTrattamentoDatiPersonali(privacy);
		configUtente.setFkSoggetto(idSoggetto);
		configUtenteDAO.createConfigUtente(configUtente);
	}

	@Override
	public Response updateUtente(PlainUtenteSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			
			if (body.getIdSoggetto() == null) {			
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName," missing requiredField: idSoggetto"));
				return BaseResponses.requiredField();
			}

			utenteValidation(body);

			TSoggetto soggetto = soggettoDAO.findSoggettoById(body.getIdSoggetto());

			soggetto.setTelefono(body.getNrTelefonico());
			soggetto.setMail(body.getEmail());
			soggetto.setDataAggiornamento(LocalDate.now());
			soggettoDAO.updateSoggetto(soggetto);

			//estraggo configUtente per utente vifoacc ossia con profilo 0
			ConfigUtente configUtente = configUtenteDAO.getConfigUtenteBySoggettoId(soggetto.getIdSoggetto());
			if(configUtente == null) {
				createConfigUtente(body.getFlgPrivacy(), body.getIdSoggetto());
			}else {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				configUtente.setDataUltimoAccesso(now);
				configUtente.setNrAccessi(configUtente.getNrAccessi() + 1);
				configUtente.setFlgTrattamentoDatiPersonali(body.getFlgPrivacy());
				configUtenteDAO.updateConfigUtente(configUtente);
			}

			checkCaricamenti();
			httpRequest.getSession().setAttribute(UPDATE_USER_DONE, true);
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}		
	}
	
	private void checkCaricamenti() throws InterruptedException {
		int count = 0;
		while(WsServiceImpl.mapComuniPerIstat == null && count++ < 30) {
			Thread.sleep(3000);
		}
	}

	private void utenteValidation(PlainUtenteSoggetto utenteSoggetto) throws ValidationException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		if (utenteSoggetto.getCodiceFiscale() == null 
				|| utenteSoggetto.getCognome() == null
				|| utenteSoggetto.getNome() == null 
				|| utenteSoggetto.getRuolo() == null 
				//|| utenteSoggetto.getProfile() == null 
				|| utenteSoggetto.getProvider() == null 
				|| utenteSoggetto.getNrTelefonico() == null
				|| utenteSoggetto.getEmail() == null 
				|| utenteSoggetto.getFlgPrivacy() != 1) {
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"utenteSoggetto:"+ utenteSoggetto ));
			throw new ValidationException(ErrorConstants.OGGETTO_OBBLIGATORIO);
		}

		if (utenteSoggetto.getNrTelefonico().length() > 20
				|| utenteSoggetto.getEmail().length() > 50) {
			throw new ValidationException(ErrorConstants.STRINGA_DIGITATA_ERROR);
		}

		if (!ValidationUtil.isEMail(utenteSoggetto.getEmail())) {
			throw new ValidationException(ErrorConstants.MAIL_NON_VALIDO);
		}
	}

	@Override
	public Response isUtenteUpdate(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			Map<String,Object> resp = new HashMap<String,Object>();
			if("localhost".equals(httpRequest.getServerName())) {
				resp.put("isUpdate", true);
			}else {
				resp.put("isUpdate", httpRequest.getSession().getAttribute(UPDATE_USER_DONE));
			}
			return  Response.ok(resp).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response logout(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			httpRequest.getSession().invalidate();
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return errorResponse(e);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}	
	}
}
