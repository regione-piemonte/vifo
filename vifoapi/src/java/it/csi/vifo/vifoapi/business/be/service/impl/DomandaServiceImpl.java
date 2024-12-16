/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.impl;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifoapi.business.be.exception.ValidationException;
import it.csi.vifo.vifoapi.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifoapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.DestinazioneDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.DomandaDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.PfPgDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.RStatoDomandaDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.ScopoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.SoggettoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.SpecieRichiestaDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.TitolaritaDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.PagamentoDAO;
import it.csi.vifo.vifoapi.business.be.service.DocumentoService;
import it.csi.vifo.vifoapi.business.be.service.DomandaService;
import it.csi.vifo.vifoapi.business.be.service.MailService;
import it.csi.vifo.vifoapi.business.be.service.PdfGeneratorService;
import it.csi.vifo.vifoapi.business.be.service.WsService;
import it.csi.vifo.vifoapi.business.be.service.helper.SiapPagoPAServiceHelper;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.Destinazione;
import it.csi.vifo.vifoapi.dto.DocumentoAllegato;
import it.csi.vifo.vifoapi.dto.Domanda;
import it.csi.vifo.vifoapi.dto.DomandaRiepilogo;
import it.csi.vifo.vifoapi.dto.InfoDomanda;
import it.csi.vifo.vifoapi.dto.Pagamento;
import it.csi.vifo.vifoapi.dto.PfPg;
import it.csi.vifo.vifoapi.dto.RStatoDomanda;
import it.csi.vifo.vifoapi.dto.RicercaDomanda;
import it.csi.vifo.vifoapi.dto.Scopo;
import it.csi.vifo.vifoapi.dto.SpecieRichiesta;
import it.csi.vifo.vifoapi.dto.TSoggetto;
import it.csi.vifo.vifoapi.dto.VoceDizionario;
import it.csi.vifo.vifoapi.util.Constants;
import it.csi.vifo.vifoapi.util.CodicePagamentoEnum;
import it.csi.vifo.vifoapi.util.CodicePraticaCosmoEnum;
import it.csi.vifo.vifoapi.util.PaginatedList;
import it.csi.vifo.vifoapi.util.ProfiloUtenteEnum;
import it.csi.vifo.vifoapi.util.StatoDomandaEnum;
import it.csi.vifo.vifoapi.util.TipoAllegatoEnum;
import it.csi.vifo.vifoapi.util.TipoDomandaEnum;
import it.csi.vifo.vifoapi.util.TipoPagamentoEnum;
import it.csi.vifo.vifoapi.util.mail.MailEnum;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.ElencoIuv;
import it.csi.vifo.vifoapi.util.service.integration.svista.Comune;

@Component
public class DomandaServiceImpl extends BaseApiServiceImpl implements DomandaService {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	@Autowired
	DomandaDAO domandaDAO;
	
	@Autowired
	SpecieRichiestaDAO specieRichiestaDAO;
	
	@Autowired
	DestinazioneDAO destinazioneDAO;
	
	@Autowired
	SoggettoDAO soggettoDAO;
	
	@Autowired
	ConfigUtenteDAO configUtenteDAO;
	
	@Autowired
	RStatoDomandaDAO rStatoDomandaDAO;
	
	@Autowired
	PfPgDAO pfPgDAO;
	
	@Autowired
	WsService wsService;
	
	@Autowired
	DocumentoAllegatoDAO documentoAllegatoDAO;
	
	@Autowired
	ScopoDAO scopoDAO;
	
	@Autowired
	TitolaritaDAO titolaritaDAO;
	
	@Autowired
	DocumentoService documentoService;
	
	@Autowired
	PdfGeneratorService pdfGeneratorService;
	
	@Autowired
	SiapPagoPAServiceHelper siapPagoPAServiceHelper;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	PagamentoDAO pagamentoDAO;
	

	@Override
	public List<DomandaRiepilogo> getRiepilogoDomandeBySoggetto(Integer idSoggetto) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return domandaDAO.getRiepilogoDomandeBySoggetto(idSoggetto);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PaginatedList<DomandaRiepilogo> getPaginatedRiepilogoDomandeBySoggetto(Integer idSoggetto, int page,
			int limit, String sort) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			PaginatedList<DomandaRiepilogo> res = domandaDAO.getPaginatedRiepilogoDomandeBySoggetto(idSoggetto, page, limit, sort);
			FillListStatiPagamento(res.getContent());
			return res;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<SpecieRichiesta> getSpecieRichiestaByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return specieRichiestaDAO.getByIdDomanda(idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public List<Destinazione> getDestinazioniByIdDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return destinazioneDAO.getDestinazioniByIdDomanda(idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int insertUpdateDomandaStep1(Domanda domanda, ConfigUtente configUtente) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			TSoggetto sogLogged = soggettoDAO.findSoggettoById(configUtente.getFkSoggetto());
			ConfigUtente confUtente = configUtenteDAO.getConfigUtenteBySoggettoId(sogLogged.getIdSoggetto());
			domanda.setFkConfigUtente(configUtente.getIdConfigUtente());
			
			TSoggetto soggettoDomanda = domanda.getSoggetto();
			soggettoDomanda.setFkConfigUtente(confUtente.getIdConfigUtente());
			if(soggettoDomanda.getPartitaIva() == null && soggettoDomanda.getRagioneSociale() == null) {
				TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(soggettoDomanda.getCodiceFiscale());
				if(soggetto == null) {
					int idSoggetto = soggettoDAO.createSoggetto(soggettoDomanda);
					domanda.setFkSoggetto(idSoggetto);
				}else {
					if(!soggetto.getCodiceFiscale().equalsIgnoreCase(sogLogged.getCodiceFiscale())) {
						soggetto.setNome(soggettoDomanda.getNome());
						soggetto.setCognome(soggettoDomanda.getCognome());
						soggetto.setCodiceFiscale(soggettoDomanda.getCodiceFiscale());
					}
					soggetto.setComune(soggettoDomanda.getComune());
					soggetto.setCap(soggettoDomanda.getCap());
					soggetto.setIndirizzo(soggettoDomanda.getIndirizzo());
					soggetto.setMail(soggettoDomanda.getMail());
					soggetto.setTelefono(soggettoDomanda.getTelefono());
					soggettoDAO.updateSoggetto(soggetto);
					domanda.setFkSoggetto(soggetto.getIdSoggetto());
				}
			}else {
				TSoggetto soggettoPg = soggettoDAO.findAziendaByCodiceFiscale(soggettoDomanda.getCodiceFiscale(),null);
				if(soggettoPg == null) {
					int idSoggetto = soggettoDAO.createSoggetto(soggettoDomanda);
					domanda.setFkSoggetto(idSoggetto);
					soggettoDomanda.setIdSoggetto(idSoggetto);
				}else {
					soggettoPg.setFlgEntePubblico(soggettoDomanda.getFlgEntePubblico());
					soggettoPg.setCodiceFiscale(soggettoDomanda.getCodiceFiscale());
					soggettoPg.setPartitaIva(soggettoDomanda.getPartitaIva());
					soggettoPg.setRagioneSociale(soggettoDomanda.getRagioneSociale());
					soggettoPg.setIndirizzo(soggettoDomanda.getIndirizzo());
					soggettoPg.setComune(soggettoDomanda.getComune());
					soggettoPg.setMail(soggettoDomanda.getMail());
					soggettoPg.setCap(soggettoDomanda.getCap());
					soggettoPg.setPec(soggettoDomanda.getPec());
					soggettoPg.setCodiceBeneficiario(soggettoDomanda.getCodiceBeneficiario());
					soggettoDAO.updateSoggetto(soggettoPg);
					domanda.setFkSoggetto(soggettoPg.getIdSoggetto());
				}
				
				if(ProfiloUtenteEnum.RICHIEDENTE.getValue() == configUtente.getFkTipoProfilo() ) {
					PfPg pfPg = pfPgDAO.getByPfAndPg(sogLogged.getIdSoggetto(), domanda.getFkSoggetto());
					if(pfPg == null) {
						pfPg = new PfPg();
						pfPg.setIdSoggettoPf(sogLogged.getIdSoggetto());
						pfPg.setIdSoggettoPg(domanda.getFkSoggetto());
						pfPg.setFkConfigUtente(configUtente.getIdConfigUtente());
						pfPgDAO.createPgPg(pfPg);
					}
				}
				
			}
			Integer idDomanda = domanda.getIdDomanda();
			if(idDomanda == null) {
				if(domanda.getFkTipoDomanda() == 2) {
					domanda.setFkScopo(0);
					domanda.setFkTitolarita(0);
				}
				idDomanda = domandaDAO.createDomanda(domanda);
				RStatoDomanda statoDomanda = new RStatoDomanda();
				statoDomanda.setIdDomanda(idDomanda);
				statoDomanda.setIdStatoDomanda(StatoDomandaEnum.BOZZA.getValue());
				statoDomanda.setProgressivo(1);
				statoDomanda.setFkConfigUtente(configUtente.getIdConfigUtente());
				rStatoDomandaDAO.insert(statoDomanda);
			}else {
				domandaDAO.updateDomanda(domanda);
			}			
			
			destinazioneDAO.deleteByIdDomanda(idDomanda);
			for(Destinazione destinazione : domanda.getListDestinazione()) {
				destinazione.setFkDomanda(idDomanda);
				destinazione.setFkConfigUtente(configUtente.getIdConfigUtente());
				destinazioneDAO.insert(destinazione);
			}
			
			specieRichiestaDAO.deleteByIdDomanda(idDomanda);
			for(SpecieRichiesta specie : domanda.getListSpecie()) {
				specie.setIdDomanda(idDomanda);
				specie.setFkConfigUtente(configUtente.getIdConfigUtente());
				specieRichiestaDAO.insert(specie);
			}
			return idDomanda;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public int updateDomandaToAssegnata(Domanda domanda, ConfigUtente configUtente) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			int res = domandaDAO.updateDomanda(domanda);
			updateStatoDomanda(domanda.getIdDomanda(), StatoDomandaEnum.ASSEGNATA, domanda.getNote(), configUtente);
			
			// TODO chiamare servizio DOQUI-ACTA per l'archiviazione protocollazione 
			domanda  = domandaDAO.loadDomanda(domanda.getIdDomanda());
			MailEnum tipoMail = MailEnum.INVIO_ASSEGNAZIONE_GRATUITA_NO_FIRMA;
			if(domanda.getFkTipoDomanda() == TipoDomandaEnum.DOMANDA_ASSEGNAZIONE_ONEROSA.getValue()) {
				tipoMail = MailEnum.INVIO_ASSEGNAZIONE_ONEROSA_NO_FIRMA;
			}else {
				//protocolazione
				domandaDAO.insertBoprocLog(domanda.getIdDomanda() + "-" + CodicePraticaCosmoEnum.ASSEGNAZIONE_GRATUITA.getCodice(),1);
			}
			mailService.sendMail(domanda.getIdDomanda(), tipoMail);
			
			return res;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public Domanda updateDomandaPreassegnata(Domanda domanda, ConfigUtente configUtente) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			domandaDAO.updateDomanda(domanda);
			updateStatoDomanda(domanda.getIdDomanda(), StatoDomandaEnum.PREASSEGNATA, domanda.getNote(), configUtente);
			return domandaDAO.loadDomanda(domanda.getIdDomanda());
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public void updateDomandaToChiusa(Domanda domanda, ConfigUtente configUtente) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        List<Pagamento> listPagamenti = pagamentoDAO.getPagamentiByIdDomandaAndTipo(domanda.getIdDomanda(), TipoPagamentoEnum.PIANTINE);
	        if(listPagamenti.size() > 0 && listPagamenti.get(0).getStatoPagamento() == CodicePagamentoEnum.ATTESA_PAGAMENTO) {
	        	siapPagoPAServiceHelper.annullaPagamento(domanda.getIdDomanda());
	        }
			updateStatoDomanda(domanda.getIdDomanda(), StatoDomandaEnum.CHIUSA, null, configUtente);
			MailEnum tipoMail = MailEnum.DOMANDA_CHIUSA;
			mailService.sendMail(domanda.getIdDomanda(), tipoMail);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public void updateDomandaToRifiutata(Domanda domanda, ConfigUtente configUtente) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        
			updateStatoDomanda(domanda.getIdDomanda(), StatoDomandaEnum.RIFIUTATA, domanda.getNote(), configUtente);
			
			MailEnum tipoMail = MailEnum.DOMANDA_RIFIUTATA;
			mailService.sendMail(domanda.getIdDomanda(), tipoMail);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	@Override
	public void annullaAssegnazione(Integer idDomanda, ConfigUtente configUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			updateStatoDomanda(idDomanda, StatoDomandaEnum.PREASSEGNATA, null, configUtente);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}


	@Override
	public Domanda loadDomanda(Integer idDomanda) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			Domanda domanda = domandaDAO.loadDomanda(idDomanda);
			TSoggetto soggetto = soggettoDAO.findSoggettoById(domanda.getFkSoggetto());
			domanda.setSoggetto(soggetto);
			if(soggetto.getComune() != null) {
				soggetto.setComune(WsServiceImpl.mapComuniPerIstat.get(soggetto.getComune().getCodIstat()));	
			}
			List<Destinazione> listDestinazioni = destinazioneDAO.getDestinazioniByIdDomanda(idDomanda);
			if(listDestinazioni != null && listDestinazioni.size()>0) {
				Comune comune;
				for(Destinazione des : listDestinazioni) {
					comune = WsServiceImpl.mapComuniPerIstat.get(des.getIstatComune());
					des.setNomeComune(comune == null? null: comune.getNome());
				}
			}
			domanda.setListDestinazione(listDestinazioni.toArray(new Destinazione[listDestinazioni.size()]));
			List<SpecieRichiesta> listSpecie = specieRichiestaDAO.getByIdDomanda(idDomanda);
			domanda.setListSpecie(listSpecie.toArray(new SpecieRichiesta[listSpecie.size()]));
			domanda.setListDocumenti(documentoAllegatoDAO.getByIdDomanda(idDomanda));
			return domanda;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public void inviaDomanda(Domanda domanda, ConfigUtente configUtente, HttpServletRequest httpRequest) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));	
	        domanda = domandaDAO.loadDomanda(domanda.getIdDomanda());
	        TSoggetto soggetto = soggettoDAO.findSoggettoById(domanda.getFkSoggetto());
			updateStatoDomanda(domanda.getIdDomanda(), StatoDomandaEnum.INVIATA, domanda.getNote(), configUtente);
			domanda = domandaDAO.loadDomanda(domanda.getIdDomanda());
			MailEnum tipoMail = MailEnum.INVIO_DOMANDA_GRATUITA_FIRMATA;
			CodicePraticaCosmoEnum codice = CodicePraticaCosmoEnum.DOMANDA_GRATUITA;
			if(domanda.getFkTipoDomanda() == TipoDomandaEnum.DOMANDA_ASSEGNAZIONE_ONEROSA.getValue()) {
				tipoMail = MailEnum.INVIO_DOMANDA_ONEROSA_NO_FIRMA;
				codice = CodicePraticaCosmoEnum.DOMANDA_ONEROSA;
			}
			mailService.sendMail(domanda.getIdDomanda(), tipoMail);
			
			//protocolazione
			if(domanda.getFkTipoDomanda() == 1 || soggetto.getFlgEntePubblico() == 1) {
				domandaDAO.insertBoprocLog(domanda.getIdDomanda() + "-" + codice.getCodice(),1);
			}
			
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
		
	}
	
	@Override
	public void updateStatoDomanda(Integer idDomanda, StatoDomandaEnum statoDomanda, String note, ConfigUtente configUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {		
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        RStatoDomanda rStatoDomanda = rStatoDomandaDAO.getLastStato(idDomanda);
	        if(rStatoDomanda.getIdStatoDomanda() == statoDomanda.getValue()) {
	        	rStatoDomanda.setNoteStato(note);
	        	rStatoDomandaDAO.update(rStatoDomanda);
	        }else {
				rStatoDomanda = new RStatoDomanda();
				rStatoDomanda.setIdDomanda(idDomanda);
				rStatoDomanda.setNoteStato(note);
				rStatoDomanda.setIdStatoDomanda(statoDomanda.getValue());
				rStatoDomanda.setFkConfigUtente(configUtente.getIdConfigUtente());
				rStatoDomandaDAO.insert(rStatoDomanda);
	        }
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public void deleteDomanda(Integer idDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			destinazioneDAO.deleteByIdDomanda(idDomanda);
			rStatoDomandaDAO.deleteByIdDomanda(idDomanda);
			specieRichiestaDAO.deleteByIdDomanda(idDomanda);
			domandaDAO.deleteDomanda(idDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public void confermaAccettazione(Integer idDomanda, ConfigUtente configUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {	
			System.out.println("dentro");
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			List<DocumentoAllegato> listAllegati = documentoAllegatoDAO.getByIdDomanda(idDomanda);
			Domanda domanda = domandaDAO.loadDomanda(idDomanda);
			if(!checkDocuments(listAllegati, domanda)) {
				throw new ValidationException("Documento allegato mancante");
			}
			updateStatoDomanda(domanda.getIdDomanda(), StatoDomandaEnum.ACCETTATA, null, configUtente);
			
			//protocolazione
			domandaDAO.insertBoprocLog(domanda.getIdDomanda() + "-" + CodicePraticaCosmoEnum.ACCETTAZIONE.getCodice(),1);
			
			MailEnum tipoMail = MailEnum.INVIO_ACCETTAZIONE_ONEROSA_FIRMATA;
			mailService.sendMail(domanda.getIdDomanda(), tipoMail);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
		
	}
	
	private boolean checkDocuments(List<DocumentoAllegato> listAllegati, Domanda domanda) {
		boolean result = false;
		if(domanda.getFkTipoDomanda() == TipoDomandaEnum.DOMANDA_ASSEGNAZIONE_ONEROSA.getValue()) {
			Integer[] docsType = new Integer[] {
					TipoAllegatoEnum.ACCETTAZIONE_FIRMA_AUTOGRAFA.getValue(),
					TipoAllegatoEnum.ACCETTAZIONE_FIRMA_DIGITALE.getValue()} ;
			for(DocumentoAllegato item:listAllegati) {
				if(ArrayUtils.contains(docsType, item.getFkTipoAllegato())) {
					return true;
				}
			}
		}		
		return result;
	}

	@Override
	public void confermaVariazione(Domanda domanda, ConfigUtente configUtente) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			if(domanda.getNote() == null || domanda.getNote().length() < 20) {
				throw new ValidationException("Campo note non valido");
			}
			domandaDAO.updateDomanda(domanda);
			updateStatoDomanda(domanda.getIdDomanda(), StatoDomandaEnum.RICHIESTA_VARIAZIONI, domanda.getNote(), configUtente);
			
			MailEnum tipoMail = MailEnum.INVIO_RICHIESTA_VARIAZIONI;
			mailService.sendMail(domanda.getIdDomanda(), tipoMail);
			
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public PaginatedList<DomandaRiepilogo> getPaginatedSearchDomande(RicercaDomanda ricerca, int page, int limit, String sort) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        PaginatedList<DomandaRiepilogo> res = domandaDAO.getPaginatedSearchDomande(ricerca, page, limit, sort);
	        FillListStatiPagamento(res.getContent());
			return res;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private void FillListStatiPagamento(List<DomandaRiepilogo> list) {
		if(list != null && list.size() > 0) {
			for(DomandaRiepilogo item : list) {
				fillStatoPagamento(item);
			}
		}
	}
	
	private void fillStatoPagamento(DomandaRiepilogo item) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		ElencoIuv iuvObj;
		String stato;
		String statoPagamento = null;
		
		try {					
			if(StatoDomandaEnum.ACCETTATA.toString().equals(item.getStato()) && item.getStatoPagamento() != null 
				&& !CodicePagamentoEnum.CANCELED.getDescrizione().equals(item.getStatoPagamento()) 
				&& !CodicePagamentoEnum.SUCCESSFULL.getDescrizione().equals(item.getStatoPagamento())) {						
				iuvObj = siapPagoPAServiceHelper.getLastPagamentoByIdDomandaAndTipo(item.getIdDomanda(), TipoPagamentoEnum.PIANTINE);
				if(iuvObj!=null) {
					statoPagamento = iuvObj.getEsito();
				}else {
					item.getStatoPagamento();
					LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, "Nessun pagamento trovato su SIAPPAGOPA per domanda: " + item.getIdDomanda()));
					pagamentoDAO.deletePagamentoPendingByIdDomanda(item.getIdDomanda());
				}					
			}else {
				statoPagamento = item.getStatoPagamento();
			}
			stato = item.getStato() + 
					(getDescrPagamento(statoPagamento) != null? getDescrPagamento(statoPagamento) : "");

			item.setStato(stato);
			
		} catch (RemoteException e) {
			LOGGER.error(getClassFunctionErrorInfo(CLASSNAME, methodName, e),e);
		}
	}
	
	private String getDescrPagamento(String value) {
		 if(value != null){
		      value = value.toLowerCase();
		      if(value.indexOf("pagamento") > -1){
		        return " - " + value;
		      }else if(value.indexOf("attesa") > -1){
		        return " - pagamento in " + value;
		      }else{
		        return " - pagamento " + value;
		      }
		    }
		    return "";
	}

	@Override
	public InfoDomanda getInfoDomanda(Integer idDomanda) throws RemoteException {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			InfoDomanda infoDomanda = new InfoDomanda();
			RStatoDomanda statoDomanda = rStatoDomandaDAO.getLastStatoByType(idDomanda, StatoDomandaEnum.INVIATA);
			if(statoDomanda != null) {
				infoDomanda.setDataInvio(formatDate(statoDomanda.getDataInserimento()));
				infoDomanda.setProtocolloInvio(getProtocollo(statoDomanda));
				infoDomanda.setNoteInvio(statoDomanda.getNoteStato());
			}
			statoDomanda = rStatoDomandaDAO.getLastStatoByType(idDomanda, StatoDomandaEnum.RIFIUTATA);
			if(statoDomanda != null) {
				infoDomanda.setOperatoreRifiuto(soggettoDAO.getSoggettoByIdConfigUtente(statoDomanda.getFkConfigUtente()));
				infoDomanda.setDataRifiuto(formatDate(statoDomanda.getDataInserimento()));
				infoDomanda.setNoteRifiuto(statoDomanda.getNoteStato());
			}else {
				Domanda domanda = domandaDAO.loadDomanda(idDomanda);
				if(domanda.getFkScopo() != null) {
					Scopo scopo = scopoDAO.getScopoById(domanda.getFkScopo());
					if(scopo != null) {
						infoDomanda.setScopoAssegnGratuita(scopo.getDescrizione());
					}
				}
				if(infoDomanda.getScopoAssegnGratuita() == null) {
					infoDomanda.setScopoAssegnGratuita(" - ");
				}
				if(domanda.getFkTitolarita() != null) {
					VoceDizionario titolarita = titolaritaDAO.getTitolaritaById(domanda.getFkTitolarita());
					if(titolarita != null) {
						infoDomanda.setTitolarita(titolarita.getDescrizione());
					}
				}
				if(domanda.getFkStatoDomanda() == StatoDomandaEnum.RICHIESTA_VARIAZIONI.getValue()) {
					statoDomanda = rStatoDomandaDAO.getLastStatoByType(idDomanda, StatoDomandaEnum.RICHIESTA_VARIAZIONI);
					if(statoDomanda != null) {
						infoDomanda.setDataRichVariazioni(formatDate(statoDomanda.getDataInserimento()));
						infoDomanda.setNoteRichVariazioni(statoDomanda.getNoteStato());
					}
				}
				statoDomanda = rStatoDomandaDAO.getLastStatoByType(idDomanda, StatoDomandaEnum.PREASSEGNATA);
				if(statoDomanda != null) {
					infoDomanda.setNotePreassegnata(statoDomanda.getNoteStato());
				}
				statoDomanda = rStatoDomandaDAO.getLastStatoByType(idDomanda, StatoDomandaEnum.ASSEGNATA);
				if(statoDomanda != null) {
					infoDomanda.setOperatoreAssegnazione(soggettoDAO.getSoggettoByIdConfigUtente(statoDomanda.getFkConfigUtente()));
					infoDomanda.setDataAssegnazione(formatDate(statoDomanda.getDataInserimento()));
					infoDomanda.setProtocolloAssegnazione(getProtocollo(statoDomanda));
					infoDomanda.setNoteAssegnazione(statoDomanda.getNoteStato());
				}
				statoDomanda = rStatoDomandaDAO.getLastStatoByType(idDomanda, StatoDomandaEnum.ACCETTATA);
				if(statoDomanda != null) {			
					infoDomanda.setDataAccettazione(formatDate(statoDomanda.getDataInserimento()));
					infoDomanda.setProtocolloAccettazione(getProtocollo(statoDomanda));
					
					DomandaRiepilogo item = domandaDAO.getDomandaRiepilogoByIdDomanda(idDomanda);
					if(item != null) {
						fillStatoPagamento(item);
						infoDomanda.setStatoPagamento(item.getStatoPagamento());
					}
				}
				statoDomanda = rStatoDomandaDAO.getLastStatoByType(idDomanda, StatoDomandaEnum.RITIRO_IN_CORSO);
				if(statoDomanda != null) {
					infoDomanda.setOperatoreRitiro(soggettoDAO.getSoggettoByIdConfigUtente(statoDomanda.getFkConfigUtente()));
					infoDomanda.setDataRitiro(formatDate(statoDomanda.getDataInserimento()));
				}
				statoDomanda = rStatoDomandaDAO.getLastStatoByType(idDomanda, StatoDomandaEnum.CHIUSA);
				if(statoDomanda != null) {
					infoDomanda.setOperatoreChiusura(soggettoDAO.getSoggettoByIdConfigUtente(statoDomanda.getFkConfigUtente()));
					infoDomanda.setDataChiusura(formatDate(statoDomanda.getDataInserimento()));
				}
			}
			return infoDomanda;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private String getProtocollo(RStatoDomanda statoDomanda) {
		if(statoDomanda.getDataProtocollo() != null) {
			return statoDomanda.getNumeroProtocollo() + " del " + formatDate(statoDomanda.getDataProtocollo());
		}else {
			return "-";
		}
	}
	
	private String formatDate(Date data) {
		if(data != null) {
			return sdf.format(data);
		}
		return "-";
	}

	@Override
	public int getNumDomandeBySoggetto(Integer idSoggetto, Integer idVivaio) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			return domandaDAO.getNumDomandeBySoggetto(idSoggetto, idVivaio);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int insertPagamento(Pagamento pagamento) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        pagamentoDAO.deletePagamentoPendingByIdDomanda(pagamento.getFkDomanda());
	        return pagamentoDAO.insert(pagamento);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public int updatePagamento(Pagamento pagamento) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return pagamentoDAO.updateLastByIdDomandaAndTipo(pagamento);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}

	@Override
	public RStatoDomanda getLastStatoByType(Integer idDomanda, StatoDomandaEnum statoDomanda) {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
	        return rStatoDomandaDAO.getLastStatoByType(idDomanda, statoDomanda);
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	

}
