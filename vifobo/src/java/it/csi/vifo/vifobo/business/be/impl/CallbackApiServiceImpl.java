/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.impl;

import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.vifo.vifobo.business.be.CallbackApi;
import it.csi.vifo.vifobo.business.be.impl.dao.ConfigurationDAO;
import it.csi.vifo.vifobo.business.be.impl.dao.CosmoDAO;
import it.csi.vifo.vifobo.business.be.impl.dao.RStatoDomandaDAO;
import it.csi.vifo.vifobo.business.be.impl.dao.impl.PagamentoDAO;
import it.csi.vifo.vifobo.business.be.service.MailService;
import it.csi.vifo.vifobo.business.be.service.SchedulerCosmoService;
import it.csi.vifo.vifobo.dto.RStatoDomanda;
import it.csi.vifo.vifobo.dto.cosmo.AggiornaStatoPraticaRequest;
import it.csi.vifo.vifobo.dto.cosmo.ProtocolloDocumento;
import it.csi.vifo.vifobo.util.AIKeyHolderUtil;
import it.csi.vifo.vifobo.util.CodicePagamentoEnum;
import it.csi.vifo.vifobo.util.CodicePraticaCosmoEnum;
import it.csi.vifo.vifobo.util.Constants;
import it.csi.vifo.vifobo.util.DateUtil;
import it.csi.vifo.vifobo.util.ParamsActaEnum;
import it.csi.vifo.vifobo.util.StatoDomandaEnum;
import it.csi.vifo.vifobo.util.mail.MailEnum;
import it.csi.vifo.vifobo.util.service.integration.piemontepay.Esito;
import it.csi.vifo.vifobo.util.service.integration.piemontepay.PayloadRicevuta;

@Component
public class CallbackApiServiceImpl extends BaseApiServiceImpl implements CallbackApi {

	private final String CLASSNAME = this.getClass().getSimpleName();

	@Autowired
	PagamentoDAO pagamentoDAO;

	@Autowired
	ConfigurationDAO configurationDAO;

	@Autowired
	SchedulerCosmoService schedulerCosmoService;

	@Autowired
	CosmoDAO cosmoDAO;

	@Autowired
	MailService mailService;

	@Autowired
	RStatoDomandaDAO rStatoDomandaDAO;

	@Override
	public Response postRiceviRT(HttpServletRequest request, PayloadRicevuta payload) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			Esito esito = new Esito();
			String ip = request.getRemoteAddr();
			LOGGER.info("[CallbackApiServiceImpl::postRiceviRT] payload: " + payload.toString());

			String ips = configurationDAO.getConfigPagamentiBe().getIpsEnabledForCallbackRiceviRt();
			ips = ips == null ? "" : ips;
			if (ips.length() > 0 && !Arrays.asList(ips.split(",")).contains(ip)) {
				esito.setEsito("KO");
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "User not allowed!"));
			} else {

				CodicePagamentoEnum statoPagamento = null;

				int codEsitoPagamento = Integer.parseInt(payload.getCodiceEsitoPagamento());

				if (codEsitoPagamento == 0 || codEsitoPagamento == 9) {
					statoPagamento = CodicePagamentoEnum.SUCCESSFULL;
				} else {
					statoPagamento = CodicePagamentoEnum.UNSUCCESFUL;
				}

				int res = pagamentoDAO.updateByIuvIfStatoPagamentoChanged(payload.getIuv(), statoPagamento);
				esito.setEsito(res == 1 ? "OK" : "KO");
			}

			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "Esito '" + esito.getEsito()));

			return Response.ok(esito).build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			Esito esito = new Esito();
			esito.setEsito("KO");
			return Response.ok(esito).build();
		} finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response test(HttpServletRequest request) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));

			return Response.ok("OK TEST").build();
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return Response.serverError().build();
		} finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public Response cosmo(String idPratica,HttpServletRequest request) throws Exception {
		String[] parts = idPratica.split("-");
		int idDomanda = Integer.parseInt(parts[0]);
		String tipoInvioCosmo = parts[1];
		String res = invioMailCallbackCosmo(idDomanda, tipoInvioCosmo) ;
		return Response.ok(res == null?"DONE!!!": res).build();
	}

	@Override
	public Response updateStatoPraticaCosmo(String idPratica, AggiornaStatoPraticaRequest payload,
			HttpServletRequest request) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
		
		String credenziali = configurationDAO.getParamActaByCode(ParamsActaEnum.USER_CALLBACK) + ":" 
				+ configurationDAO.getParamActaByCode(ParamsActaEnum.PWD_CALLBACK);
		if(!isBaseAutenticatonOk(request, credenziali)) {
			return getResponseError(401,"Unauthorized","Credenziali non valide");
		}
		Response response = null;
		try {
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "idPratica = " + idPratica));
			
			if(cosmoDAO.isPraticaClosed(idPratica)) {	
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "Pratica chiusa: idPratica = " + idPratica));
				return Response.ok(HttpStatus.OK).build();
			}
			
			if(idPratica.endsWith("AG")) {//ASSEGNAZIONE GRATUITA
				cosmoDAO.storicizzaBoprocLog(idPratica,null);
				return Response.ok(HttpStatus.OK).build();
			}

			String[] parts = idPratica.split("-");
			int idDomanda = Integer.parseInt(parts[0]);
			String tipoInvioCosmo = parts[1];

			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(payload);

			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "idPratica = " + idPratica + " " + json));

			if (payload != null && payload.getDocumenti() != null && payload.getDocumenti().size() > 0
					&& payload.getTipo() != null) {

				boolean trovato = false;
				String error;
				
				List<Integer> idsDocsList = new ArrayList<Integer>();
				ProtocolloDocumento protocollo;
				for (int i = 0; i < payload.getDocumenti().size(); i++) {
					if (payload.getDocumenti().get(i).getIdPadre() == null
							|| (payload.getDocumenti().get(i).getIdPadre() != null
									&& payload.getDocumenti().get(i).getIdPadre().equals(""))) {
						if (payload.getDocumenti().get(i).getArchiviazione() != null
								&& payload.getDocumenti().get(i).getArchiviazione().getProtocollo() != null
								&& payload.getDocumenti().get(i).getArchiviazione().getProtocollo().getData() != null
								&& payload.getDocumenti().get(i).getArchiviazione().getProtocollo()
										.getNumero() != null) {
							trovato = true;

							protocollo = payload.getDocumenti().get(i).getArchiviazione().getProtocollo();

							// update stato pratica con numProtocollo e dataProtocollo
							updateStatoDomandaConDatiProtocollo(idDomanda, tipoInvioCosmo, protocollo.getNumero(),
									DateUtil.dateFormatter.parse(protocollo.getData()));
						}
						// solo uno ha idPadre == null

					} 
					idsDocsList.add(Integer.parseInt(payload.getDocumenti().get(i).getId()));
				}

				if (!trovato) {
					error = "per la pratica:" + idPratica
							+ " non e' stato trovato il documento padre o i dati del protocollo sono nulli.";
					LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, error));
					response = Response.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).entity(error).build();

					cosmoDAO.updateBoprocLog(idPratica, 1, Constants.COD_ERRORE_INTERNO, error, json);

				} else {
					for (Integer idDocumento : idsDocsList) {
						boolean updateOK = cosmoDAO.updateDocumentoWithInvoActa(idDocumento) == 1;

						if (!updateOK) {
							error = "Il documento con id: " + idDocumento + " della pratica: " + idPratica
									+ " non e' stata trovata";
							LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, error));
							response = Response.status(HttpStatus.NOT_FOUND.value()).entity(error).build();

						}
					}
				}

				if (response == null) {

					try {
						invioMailCallbackCosmo(idDomanda, tipoInvioCosmo);

						cosmoDAO.storicizzaBoprocLog(idPratica, json);

					} catch (Exception e) {

						String erroreStr = getClassFunctionErrorInfo(CLASSNAME, methodName, "per la pratica:"
								+ idPratica + " non e' stato possibile inviare la mail-" + e.getMessage());
						LOGGER.error(erroreStr);
						if (erroreStr.length() > 1999) {
							erroreStr = erroreStr.substring(0, 1999);
						}

						cosmoDAO.updateBoprocLog(idPratica, 1, Constants.COD_ERRORE_INTERNO, erroreStr, json);
					}
				}

				// Non ci sono stati errori
				if (response == null) {
					response = Response.ok(HttpStatus.OK).build();
				}

			}
			response = Response.ok("KO").build();

			// end *****************************************************
		} catch (Exception e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e), e);
			return Response.serverError().build();
		} finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
		return response;
	}
	
	private String invioMailCallbackCosmo(int idDomanda, String tipoInvioCosmo) {
		MailEnum tipoMail = null;
		if (tipoInvioCosmo.equals(CodicePraticaCosmoEnum.DOMANDA_GRATUITA.getCodice())) {
			tipoMail = MailEnum.INVIO_DOMANDA_GRATUITA_PROT;
		} else if (tipoInvioCosmo.equals(CodicePraticaCosmoEnum.DOMANDA_ONEROSA.getCodice())) {
			tipoMail = MailEnum.INVIO_DOMANDA_ONEROSA_PROT;
		} else if (tipoInvioCosmo.equals(CodicePraticaCosmoEnum.ACCETTAZIONE.getCodice())) {
			tipoMail = MailEnum.INVIO_ACCETTAZIONE_ONEROSA_PROT;
		}
		if (tipoMail != null) {
			return mailService.sendMail(idDomanda, tipoMail);
		}
		return "Nessuna mail associata al codice: " + tipoInvioCosmo;
	}

	private void updateStatoDomandaConDatiProtocollo(int idDomanda, String tipoInvioCosmo, String numeroProtocollo,
			Date dataProtocollo) {
		StatoDomandaEnum statoDomanda = StatoDomandaEnum.INVIATA;
		if (tipoInvioCosmo.equals(CodicePraticaCosmoEnum.ASSEGNAZIONE_GRATUITA.getCodice())) {
			statoDomanda = StatoDomandaEnum.ASSEGNATA;
		} else if (tipoInvioCosmo.equals(CodicePraticaCosmoEnum.ACCETTAZIONE.getCodice())) {
			statoDomanda = StatoDomandaEnum.ACCETTATA;
		}

		RStatoDomanda rStatoDomanda = new RStatoDomanda();
		rStatoDomanda.setIdDomanda(idDomanda);
		rStatoDomanda.setDataProtocollo(dataProtocollo);
		rStatoDomanda.setNumeroProtocollo(numeroProtocollo);
		rStatoDomanda.setIdStatoDomanda(statoDomanda.getValue());
		rStatoDomandaDAO.updateProtocolData(rStatoDomanda);
	}
	
	private boolean isBaseAutenticatonOk(HttpServletRequest request, String userPasswor) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
		try {
			final String authorization = request.getHeader("Authorization");
			boolean resp = false;
			if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
			    // Authorization: Basic base64credentials
			    String base64Credentials = authorization.substring("Basic".length()).trim();
			    byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
			    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
			    resp = credentials.equals(userPasswor);
			    if(!resp) {
			    	LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName, "resp: " + resp +
			    		 "credentials in request: " + credentials));
			    }
			    
			}
			return resp;
		} finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	

}
