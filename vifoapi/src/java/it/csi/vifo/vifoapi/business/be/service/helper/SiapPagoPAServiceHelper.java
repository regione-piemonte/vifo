/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.helper;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.vifo.vifoapi.business.be.impl.dao.ConfigurationDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.DomandaDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.SoggettoDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.impl.PagamentoDAO;
import it.csi.vifo.vifoapi.dto.ConfigPagamenti;
import it.csi.vifo.vifoapi.dto.Domanda;
import it.csi.vifo.vifoapi.dto.Pagamento;
import it.csi.vifo.vifoapi.dto.TSoggetto;
import it.csi.vifo.vifoapi.util.CodicePagamentoEnum;
import it.csi.vifo.vifoapi.util.Constants;
import it.csi.vifo.vifoapi.util.TipoDocumentoPagamentoEnum;
import it.csi.vifo.vifoapi.util.TipoPagamentoEnum;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.AnnullaPagamentoException;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.ElencoIuv;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.EsitoAnnullaPagamento;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.EsitoLeggiDocumento;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.EsitoLeggiIuv;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.EsitoVerificaDatiPagamentoSingolo;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.LeggiDocumentoException;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.PagoPAWSProxy;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.ParametriAnnullaPagamento;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.ParametriLeggiDocumento;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.ParametriLeggiIuvByToken;
import it.csi.vifo.vifoapi.util.service.integration.piemontepay.ParametriVerificaDatiPagamentoSingolo;

public class SiapPagoPAServiceHelper extends AbstractServiceHelper {
	
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(Constants.LOGGER_NAME + ".service");
	
	
	private PagoPAWSProxy proxy = null;
	private long idApplicativo;
	
	@Autowired
	DomandaDAO domandaDAO;
	
	@Autowired
	SoggettoDAO soggettoDAO;
	
	@Autowired
	PagamentoDAO pagamentoDAO;
	
	@Autowired
	ConfigurationDAO configurationDAO;
	
	
	
//	public SiapPagoPAServiceHelper(String url, long idApplicativo, String wsUser, String wsPwd) {
//		super();
//		this.idApplicativo = idApplicativo;
//		
//		proxy = new PagoPAWSProxy(url,wsUser,wsPwd);
//	}
	
	private PagoPAWSProxy getProxy() {
		if(proxy == null) {
			ConfigPagamenti conf = configurationDAO.getConfigPagamentiBe();
			proxy = new PagoPAWSProxy(conf.getWebServiceUrl(),conf.getWebServiceUser(),conf.getWebServicePwd());
			idApplicativo = conf.getIdProcedimento();
		}
		return proxy;
	}

	public EsitoLeggiDocumento getAvvisoPagamento(int idDomanda) throws LeggiDocumentoException, RemoteException {
		return getDocumentoByIdDomandaAndTipo(idDomanda,TipoDocumentoPagamentoEnum.AVVISO_PAGAMENTO);
	}
	
	public EsitoLeggiDocumento getRicevutaPagamento(int idDomanda) throws LeggiDocumentoException, RemoteException {
		return getDocumentoByIdDomandaAndTipo(idDomanda,TipoDocumentoPagamentoEnum.RICEVUTA_TELEMATICA);
	}
	
	public EsitoAnnullaPagamento annullaPagamento(int idDomanda) throws AnnullaPagamentoException, RemoteException {
		Domanda domanda = domandaDAO.loadDomanda(idDomanda);
		TSoggetto soggetto = soggettoDAO.findSoggettoById(domanda.getFkSoggetto());
		String codiceFiscalePIVA = soggetto.getRagioneSociale() == null?soggetto.getCodiceFiscale():soggetto.getPartitaIva();
		String iuv = getUivByToken(codiceFiscalePIVA, idDomanda + "");
		BigDecimal importo = new BigDecimal(domanda.getTotFattura().toString());
		ParametriAnnullaPagamento params = new ParametriAnnullaPagamento(idApplicativo , iuv, codiceFiscalePIVA, importo);
		EsitoAnnullaPagamento res = getProxy().annullaPagamento(params);
		pagamentoDAO.updateByIuvIfStatoPagamentoChanged(iuv, CodicePagamentoEnum.CANCELED);
		return res;
	}

	private EsitoLeggiDocumento getDocumentoByIdDomandaAndTipo(int idDomanda, TipoDocumentoPagamentoEnum tipoDoc ) throws LeggiDocumentoException, RemoteException {
		ParametriLeggiDocumento param = new ParametriLeggiDocumento();
		Domanda domanda = domandaDAO.loadDomanda(idDomanda);
		TSoggetto soggetto = soggettoDAO.findSoggettoById(domanda.getFkSoggetto());
		String codiceFiscalePIVA = soggetto.getRagioneSociale() == null?soggetto.getCodiceFiscale():soggetto.getPartitaIva();
		String iuv = getUivByToken(codiceFiscalePIVA, idDomanda + "");
		param.setCodiceFiscalePIVA(codiceFiscalePIVA);
		param.setIdApplicativo(idApplicativo);
		param.setImporto(new BigDecimal(domanda.getTotFattura().toString()));
		param.setIuv(iuv);
		param.setTipoDocumento(tipoDoc.getCodice());
		return getProxy().leggiDocumento(param);
	}
	
	public CodicePagamentoEnum getStatoPagamentoByToken(int idDomanda) throws RemoteException {
		String codiceFiscalePIVA = getCodiceFiscalePivaByIdDomanda(idDomanda);
		ElencoIuv iuv = getLastUivObjectByToken(codiceFiscalePIVA, idDomanda + "");
		return CodicePagamentoEnum.getEnumByDescrizione(iuv.getEsito());
	}
	
	
	private String getUivByToken(String codiceFiscalePIVA, String userToken) throws RemoteException {
		return getLastUivObjectByToken(codiceFiscalePIVA, userToken).getIuv(); 
	}
	
	public ElencoIuv getLastUivObjectByToken(String codiceFiscalePIVA, String userToken) throws RemoteException {
		
		ElencoIuv iuvObject = getListIuvObjectByToken(codiceFiscalePIVA, userToken)[0]; // elemento 0 il piu' recente
		return iuvObject;
	}
	
	public ElencoIuv getLastPagamentoByIdDomandaAndTipo(int idDomanda, TipoPagamentoEnum tipoPagamento) throws RemoteException {
		String codiceFiscalePIVA = getCodiceFiscalePivaByIdDomanda(idDomanda);
		ElencoIuv resIuv = null;
		List<Pagamento> listPagamenti = pagamentoDAO.getPagamentiByIdDomandaAndTipo(idDomanda, tipoPagamento);
		if(listPagamenti != null && !listPagamenti.isEmpty()) {
			String iuvLastPagamento = listPagamenti.get(0).getIuv();
			ElencoIuv[] listIuv = getListIuvObjectByToken(codiceFiscalePIVA, idDomanda + "");
			if(iuvLastPagamento != null) {				
				resIuv = getIuvByList(iuvLastPagamento, listIuv);
			}else {
				Pagamento pagamento = fillPagamento(listIuv, tipoPagamento, idDomanda);
				pagamentoDAO.updateLastByIdDomandaAndTipo(pagamento);
				return listIuv != null && listIuv.length > 0?listIuv[0]:null;
			}
		}
		return resIuv;
	}
	
	private ElencoIuv getIuvByList(String iuvLastPagamento, ElencoIuv[] listIuv) {
		for(ElencoIuv item : listIuv) {
			if(iuvLastPagamento.equals(item.getIuv())) {
				pagamentoDAO.updateByIuvIfStatoPagamentoChanged(item.getIuv(), CodicePagamentoEnum.getEnumByDescrizione(item.getEsito()));
				return item;
			}
		}
		return null;
	}
	
	private Pagamento fillPagamento(ElencoIuv[] listIuv, TipoPagamentoEnum tipoPagamento, int idDomanda){
		Pagamento pagamento = new Pagamento();
		pagamento.setFkDomanda(idDomanda);
		if(listIuv!= null && listIuv.length > 0) {
			ElencoIuv resIuv = listIuv[0];			
			pagamento.setEsito("OK");
			pagamento.setFkTipoPagamento(tipoPagamento.getValue());
			pagamento.setIuv(resIuv.getIuv());
			pagamento.setStatoPagamento(CodicePagamentoEnum.getEnumByDescrizione(resIuv.getEsito()));
		}else {
			pagamento.setEsito("KO");
		}
		return pagamento;
	}
	
	public EsitoVerificaDatiPagamentoSingolo getEsitoPagamentoByIuv(String codiceFiscalePIVA, String iuv) throws RemoteException {
		ParametriVerificaDatiPagamentoSingolo params = new ParametriVerificaDatiPagamentoSingolo();
		params.setApplicationId(idApplicativo + "");
		params.setCodiceFiscalePIVA(codiceFiscalePIVA);
		params.setIuv(iuv);
		return getProxy().verificaDatiPagamentoSingolo(params);
	}
	
	private ElencoIuv[] getListIuvObjectByToken(String codiceFiscalePIVA, String userToken) throws RemoteException {
		EsitoLeggiIuv res = getProxy().leggiIuvByToken( new ParametriLeggiIuvByToken("" + idApplicativo, codiceFiscalePIVA, userToken));
		return res.getElencoIuv() != null ? res.getElencoIuv(): new ElencoIuv[0];
	}
	
	private String getCodiceFiscalePivaByIdDomanda(int idDomanda) {
		Domanda domanda = domandaDAO.loadDomanda(idDomanda);
		TSoggetto soggetto = soggettoDAO.findSoggettoById(domanda.getFkSoggetto());
		return soggetto.getRagioneSociale() == null || soggetto.getFlgEntePubblico() == 1?soggetto.getCodiceFiscale():soggetto.getPartitaIva();
	}

}
