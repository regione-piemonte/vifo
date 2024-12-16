/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/**
 * PagoPAWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

public interface PagoPAWS extends java.rmi.Remote {
    public EsitoGeneraAvvisoPagamento generaAvvisoPagamento(ParametriGeneraAvvisoPagamento arg0) throws java.rmi.RemoteException, GeneraAvvisoPagamentoException;
    public EsitoVerificaDatiPagamentoSingolo verificaDatiPagamentoSingolo(ParametriVerificaDatiPagamentoSingolo arg0) throws java.rmi.RemoteException, VerificaDatiPagamentoException;
    public EsitoLeggiDocumento leggiDocumento(ParametriLeggiDocumento arg0) throws java.rmi.RemoteException, LeggiDocumentoException;
    public EsitoLeggiIuv leggiIuv(ParametriLeggiIuv arg0) throws java.rmi.RemoteException;
    public EsitoVerificaDatiPagamentoSingolo[] verificaDatiPagamentoMultiplo(ParametriVerificaDatiPagamentoSingolo[] arg0) throws java.rmi.RemoteException, VerificaDatiPagamentoException;
    public EsitoLeggiIuv leggiIuvByToken(ParametriLeggiIuvByToken arg0) throws java.rmi.RemoteException;
    public EsitoAnnullaPagamento annullaPagamento(ParametriAnnullaPagamento arg0) throws java.rmi.RemoteException, AnnullaPagamentoException;
    public java.lang.String getVersion() throws java.rmi.RemoteException;
}
