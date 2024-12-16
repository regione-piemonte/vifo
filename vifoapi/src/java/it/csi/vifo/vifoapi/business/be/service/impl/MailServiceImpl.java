/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifoapi.business.be.impl.dao.DomandaDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.InventarioPiantineDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.MailDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.SoggettoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.VivaioDAO;
import it.csi.vifo.vifoapi.business.be.service.MailService;
import it.csi.vifo.vifoapi.dto.Domanda;
import it.csi.vifo.vifoapi.dto.RichiestaTrasferimentoPiante;
import it.csi.vifo.vifoapi.dto.TSoggetto;
import it.csi.vifo.vifoapi.dto.Vivaio;
import it.csi.vifo.vifoapi.util.mail.EmailDTO;
import it.csi.vifo.vifoapi.util.mail.MailEnum;
import it.csi.vifo.vifoapi.util.mail.MailManager;

@Component
public class MailServiceImpl extends BaseApiServiceImpl implements MailService {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	@Autowired
	MailDAO mailDAO;
	
	@Autowired
	SoggettoDAO soggettoDAO;
	
	@Autowired
	VivaioDAO vivaioDAO;
	
	@Autowired
	DomandaDAO domandaDAO;
	
	@Autowired
	InventarioPiantineDAO inventarioPiantineDAO;

	@Override
	public String sendMail(int id, MailEnum tipoMail) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			EmailDTO mail = new EmailDTO();
			switch(tipoMail) {
			case INVIO_DOMANDA_GRATUITA_FIRMATA:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_DOMANDA_GRATUITA_FIRMATA.getValue());
				fillInvio(mail, id);
				break;
			case INVIO_DOMANDA_GRATUITA_PROT:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_DOMANDA_GRATUITA_PROT.getValue());
				fillInvio(mail, id);
				break;
			case INVIO_DOMANDA_ONEROSA_NO_FIRMA:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_DOMANDA_ONEROSA_NO_FIRMA.getValue());
				fillInvio(mail, id);
				break;
			case INVIO_DOMANDA_ONEROSA_FIRMATA:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_DOMANDA_ONEROSA_FIRMATA.getValue());
				fillInvio(mail, id);
				break;
			case INVIO_DOMANDA_ONEROSA_PROT:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_DOMANDA_ONEROSA_PROT.getValue());
				fillInvio(mail, id);
				break;
			case INVIO_ASSEGNAZIONE_GRATUITA_NO_FIRMA:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_ASSEGNAZIONE_GRATUITA_NO_FIRMA.getValue());
				fillInvio(mail, id);
				break;
			case INVIO_ASSEGNAZIONE_ONEROSA_NO_FIRMA:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_ASSEGNAZIONE_ONEROSA_NO_FIRMA.getValue());
				fillInvio(mail, id);
				break;
			case INVIO_ACCETTAZIONE_ONEROSA_FIRMATA:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_ACCETTAZIONE_ONEROSA_FIRMATA.getValue());
				fillInvio(mail, id);
				break;
			case INVIO_ACCETTAZIONE_ONEROSA_PROT:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_ACCETTAZIONE_ONEROSA_PROT.getValue());
				fillInvio(mail, id);
				break;
			case INVIO_RICHIESTA_VARIAZIONI:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_RICHIESTA_VARIAZIONI.getValue());
				fillInvio(mail, id);
				break;
			case TRASFERIMENTO_PARTITA_TIPO_R:
				mail = mailDAO.getConfEmail(MailEnum.TRASFERIMENTO_PARTITA_TIPO_R.getValue());
				fillTrasferimento(mail, id, true);
				break;
			case TRASFERIMENTO_PARTITA_TIPO_T:
				mail = mailDAO.getConfEmail(MailEnum.TRASFERIMENTO_PARTITA_TIPO_T.getValue());
				fillTrasferimento(mail, id, false);
				break;
			case TRASFERIMENTO_PARTITA_TIPO_R_ACCETTAZIONE:
				mail = mailDAO.getConfEmail(MailEnum.TRASFERIMENTO_PARTITA_TIPO_R_ACCETTAZIONE.getValue());
				fillTrasferimento(mail, id, false);
				break;
			case TRASFERIMENTO_PARTITA_TIPO_T_ACCETTAZIONE:
				mail = mailDAO.getConfEmail(MailEnum.TRASFERIMENTO_PARTITA_TIPO_T_ACCETTAZIONE.getValue());
				fillTrasferimento(mail, id, true);
				break;
			case TRASFERIMENTO_PARTITA_TIPO_R_RIFIUTO:
				mail = mailDAO.getConfEmail(MailEnum.TRASFERIMENTO_PARTITA_TIPO_R_RIFIUTO.getValue());
				fillTrasferimento(mail, id, false);
				break;
			case TRASFERIMENTO_PARTITA_TIPO_T_RIFIUTO:
				mail = mailDAO.getConfEmail(MailEnum.TRASFERIMENTO_PARTITA_TIPO_T_RIFIUTO.getValue());
				fillTrasferimento(mail, id, true);
				break;
			case DOMANDA_RIFIUTATA:
				mail = mailDAO.getConfEmail(MailEnum.DOMANDA_RIFIUTATA.getValue());
				fillInvio(mail, id);
				break;
			case DOMANDA_CHIUSA:
				mail = mailDAO.getConfEmail(MailEnum.DOMANDA_CHIUSA.getValue());
				fillInvio(mail, id);
				break;
			}
			MailManager.sendMail(mail);
			LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,
					getClassFunctionDebugString(CLASSNAME, methodName, "Sent mail: " + tipoMail + " - to: " + mail.getDestinatari().toString())));
		}catch(Exception ex){
			return ex.getMessage();
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
		return null;
	}
	
	private void fillInvio(EmailDTO mail, Integer idDomanda) {
		Domanda domanda = domandaDAO.loadDomanda(idDomanda);
		TSoggetto richiedente = soggettoDAO.findSoggettoById(domanda.getFkSoggetto());
		Vivaio vivaio = vivaioDAO.getVivaioById(domanda.getFkVivaio());
		
		List<String> destinatari = new ArrayList<String>();
		destinatari.add(richiedente.getMail());
		mail.setDestinatari(destinatari);
		
		destinatari = new ArrayList<String>();
		destinatari.add(vivaio.getMail());
		mail.setDestinatariCC(destinatari);
		
		mail.setOggetto(mail.getOggetto().replace("##id_domanda##", idDomanda.toString()));
		
		String corpo = mail.getCorpo()
				.replace("##id_domanda##",domanda.getIdDomanda().toString())
				.replace("##valueVivaioDenominazione##", vivaio.getNome())
				.replace("##valueVivaioTelefono##", vivaio.getTelefono())
				.replace("##valueVivaioMail##", vivaio.getMail());
		
		mail.setCorpo(corpo);
	}
	
	private void fillTrasferimento(EmailDTO mail, Integer idTrasferimento, boolean isToPossesore) {
		RichiestaTrasferimentoPiante  trasf =inventarioPiantineDAO.getRichiestaTrasferimentoById(idTrasferimento);
		Vivaio vivaioPossesore = vivaioDAO.getVivaioById(trasf.getFkVivaioFrom());
		Vivaio vivaioDestinatario = vivaioDAO.getVivaioById(trasf.getFkVivaioTo());
		List<String> destinatari = new ArrayList<String>();
		if(isToPossesore) {
			destinatari.add(vivaioPossesore.getMail());
			mail.setDestinatari(destinatari);
			
			destinatari = new ArrayList<String>();
			destinatari.add(vivaioDestinatario.getMail());
			mail.setDestinatariCC(destinatari);
		}else {
			destinatari.add(vivaioDestinatario.getMail());
			mail.setDestinatari(destinatari);
			
			destinatari = new ArrayList<String>();
			destinatari.add(vivaioPossesore.getMail());
			mail.setDestinatariCC(destinatari);
		}
		mail.setOggetto(mail.getOggetto().replace("##id_partita##",idTrasferimento.toString()));
		mail.setCorpo(mail.getCorpo().replace("##id_partita##",idTrasferimento.toString()));
	}

}
