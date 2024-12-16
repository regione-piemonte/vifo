/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayloadRicevuta implements Serializable {

	private static final long serialVersionUID = -3783373245831881867L;

	private String applicationId;
	private String transactionId;
	private Date dataOraMsgRicevuta;
	private String idMsgRicevuta;
	private String iuv;
	private String codiceEsitoPagamento;
	private String descEsitoPagamento;
	private String idMsgRichiesta;
	private Date timestamp;
	private byte[] rtData;
	private byte[] rtPDF;
	private BigDecimal importo;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Object getDataOraMsgRicevuta() {
		return dataOraMsgRicevuta;
	}

	public void setDataOraMsgRicevuta(Date dataOraMsgRicevuta) {
		this.dataOraMsgRicevuta = dataOraMsgRicevuta;
	}

	public String getIdMsgRicevuta() {
		return idMsgRicevuta;
	}

	public void setIdMsgRicevuta(String idMsgRicevuta) {
		this.idMsgRicevuta = idMsgRicevuta;
	}

	public String getIuv() {
		return iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public String getCodiceEsitoPagamento() {
		return codiceEsitoPagamento;
	}

	public void setCodiceEsitoPagamento(String codiceEsitoPagamento) {
		this.codiceEsitoPagamento = codiceEsitoPagamento;
	}

	public String getDescEsitoPagamento() {
		return descEsitoPagamento;
	}

	public void setDescEsitoPagamento(String descEsitoPagamento) {
		this.descEsitoPagamento = descEsitoPagamento;
	}

	public String getIdMsgRichiesta() {
		return idMsgRichiesta;
	}

	public void setIdMsgRichiesta(String idMsgRichiesta) {
		this.idMsgRichiesta = idMsgRichiesta;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public byte[] getRtData() {
		return rtData;
	}

	public void setRtData(byte[] rtData) {
		this.rtData = rtData;
	}

	public byte[] getRtPDF() {
		return rtPDF;
	}

	public void setRtPDF(byte[] rtPDF) {
		this.rtPDF = rtPDF;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}
	
	public String toString() {
		return "applicationId: "+this.applicationId+"\n"+
				 "transactionId: "+this.transactionId+"\n"+
				 "dataOraMsgRicevuta: "+(this.dataOraMsgRicevuta != null ? this.dataOraMsgRicevuta.toString():"")+"\n"+
				 "idMsgRicevuta: "+this.idMsgRicevuta+"\n"+
				 "iuv: "+this.iuv+"\n"+
				 "codiceEsitoPagamento: "+this.codiceEsitoPagamento+"\n"+
				 "descEsitoPagamento: "+this.descEsitoPagamento+"\n"+
				 "idMsgRichiesta: "+this.idMsgRichiesta+"\n"+
				 "timestamp: "+(this.timestamp != null?this.timestamp.toString():"")+"\n"+
				 "importo: "+this.importo+"\n"
				;
	}
}
