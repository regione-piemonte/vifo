/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifobo.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifobo.business.be.impl.dao.DomandaDAO;
import it.csi.vifo.vifobo.business.be.impl.dao.MailDAO;
import it.csi.vifo.vifobo.business.be.impl.dao.SoggettoDAO;
import it.csi.vifo.vifobo.business.be.impl.dao.VivaioDAO;
import it.csi.vifo.vifobo.business.be.service.MailService;
import it.csi.vifo.vifobo.dto.Domanda;
import it.csi.vifo.vifobo.dto.TSoggetto;
import it.csi.vifo.vifobo.dto.Vivaio;
import it.csi.vifo.vifobo.util.mail.EmailDTO;
import it.csi.vifo.vifobo.util.mail.MailEnum;
import it.csi.vifo.vifobo.util.mail.MailManager;

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
	

	@Override
	public String sendMail(int id, MailEnum tipoMail) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			EmailDTO mail = new EmailDTO();
			switch(tipoMail) {
			case INVIO_DOMANDA_GRATUITA_PROT:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_DOMANDA_GRATUITA_PROT.getValue());
				fillInvio(mail, id);
				break;
			case INVIO_DOMANDA_ONEROSA_PROT:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_DOMANDA_ONEROSA_PROT.getValue());
				fillInvio(mail, id);
				break;
			case INVIO_ACCETTAZIONE_ONEROSA_PROT:
				mail = mailDAO.getConfEmail(MailEnum.INVIO_ACCETTAZIONE_ONEROSA_PROT.getValue());
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


	
	
}
