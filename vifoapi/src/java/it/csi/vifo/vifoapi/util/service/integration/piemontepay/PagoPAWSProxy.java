/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

public class PagoPAWSProxy implements PagoPAWS {
  private String _endpoint = null;
  private PagoPAWS pagoPAWS = null;
  private String wsUser = null;
  private String wsPwd = null;
  
  public PagoPAWSProxy() {
    _initPagoPAWSProxy();
  }
  
  public PagoPAWSProxy(String endpoint, String wsUser, String wsPwd) {
    this._endpoint = endpoint;
    this.wsUser = wsUser;
    this.wsPwd = wsPwd;
    _initPagoPAWSProxy();
  }
  
  private void _initPagoPAWSProxy() {
    try {
      pagoPAWS = (new PagoPAServiceLocator(wsUser, wsPwd)).getPagoPAWSPort();
      if (pagoPAWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)pagoPAWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)pagoPAWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (pagoPAWS != null)
      ((javax.xml.rpc.Stub)pagoPAWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public PagoPAWS getPagoPAWS() {
    if (pagoPAWS == null)
      _initPagoPAWSProxy();
    return pagoPAWS;
  }
  
  public EsitoGeneraAvvisoPagamento generaAvvisoPagamento(ParametriGeneraAvvisoPagamento arg0) throws java.rmi.RemoteException, GeneraAvvisoPagamentoException{
    if (pagoPAWS == null)
      _initPagoPAWSProxy();
    return pagoPAWS.generaAvvisoPagamento(arg0);
  }
  
  public EsitoVerificaDatiPagamentoSingolo verificaDatiPagamentoSingolo(ParametriVerificaDatiPagamentoSingolo arg0) throws java.rmi.RemoteException, VerificaDatiPagamentoException{
    if (pagoPAWS == null)
      _initPagoPAWSProxy();
    return pagoPAWS.verificaDatiPagamentoSingolo(arg0);
  }
  
  public EsitoLeggiDocumento leggiDocumento(ParametriLeggiDocumento arg0) throws java.rmi.RemoteException, LeggiDocumentoException{
    if (pagoPAWS == null)
      _initPagoPAWSProxy();
    return pagoPAWS.leggiDocumento(arg0);
  }
  
  public EsitoLeggiIuv leggiIuv(ParametriLeggiIuv arg0) throws java.rmi.RemoteException{
    if (pagoPAWS == null)
      _initPagoPAWSProxy();
    return pagoPAWS.leggiIuv(arg0);
  }
  
  public EsitoVerificaDatiPagamentoSingolo[] verificaDatiPagamentoMultiplo(ParametriVerificaDatiPagamentoSingolo[] arg0) throws java.rmi.RemoteException, VerificaDatiPagamentoException{
    if (pagoPAWS == null)
      _initPagoPAWSProxy();
    return pagoPAWS.verificaDatiPagamentoMultiplo(arg0);
  }
  
  public EsitoLeggiIuv leggiIuvByToken(ParametriLeggiIuvByToken arg0) throws java.rmi.RemoteException{
    if (pagoPAWS == null)
      _initPagoPAWSProxy();
    return pagoPAWS.leggiIuvByToken(arg0);
  }
  
  public EsitoAnnullaPagamento annullaPagamento(ParametriAnnullaPagamento arg0) throws java.rmi.RemoteException, AnnullaPagamentoException{
    if (pagoPAWS == null)
      _initPagoPAWSProxy();
    return pagoPAWS.annullaPagamento(arg0);
  }
  
  public java.lang.String getVersion() throws java.rmi.RemoteException{
    if (pagoPAWS == null)
      _initPagoPAWSProxy();
    return pagoPAWS.getVersion();
  }
  
  
}