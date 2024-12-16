/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/**
 * PagoPAServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

//import javax.xml.namespace.QName;
//import javax.xml.soap.SOAPElement;

import org.apache.axis.message.SOAPHeaderElement;

public class PagoPAServiceSoapBindingStub extends org.apache.axis.client.Stub implements PagoPAWS {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();
    private String wsUser = "";
	private String wsPwd = "";

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[8];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("verificaDatiPagamentoSingolo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriVerificaDatiPagamentoSingolo"), ParametriVerificaDatiPagamentoSingolo.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoVerificaDatiPagamentoSingolo"));
        oper.setReturnClass(EsitoVerificaDatiPagamentoSingolo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "VerificaDatiPagamentoException"),
                      "VerificaDatiPagamentoException",
                      new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "VerificaDatiPagamentoException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("generaAvvisoPagamento");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriGeneraAvvisoPagamento"), ParametriGeneraAvvisoPagamento.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoGeneraAvvisoPagamento"));
        oper.setReturnClass(EsitoGeneraAvvisoPagamento.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "GeneraAvvisoPagamentoException"),
                      "GeneraAvvisoPagamentoException",
                      new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "GeneraAvvisoPagamentoException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("leggiDocumento");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriLeggiDocumento"), ParametriLeggiDocumento.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoLeggiDocumento"));
        oper.setReturnClass(EsitoLeggiDocumento.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "LeggiDocumentoException"),
                      "LeggiDocumentoException",
                      new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "LeggiDocumentoException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("leggiIuv");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriLeggiIuv"), ParametriLeggiIuv.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoLeggiIuv"));
        oper.setReturnClass(EsitoLeggiIuv.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("verificaDatiPagamentoMultiplo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriVerificaDatiPagamentoMultiplo"), ParametriVerificaDatiPagamentoSingolo[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "elencoPagamenti"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoVerificaDatiPagamentoMultiplo"));
        oper.setReturnClass(EsitoVerificaDatiPagamentoSingolo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "elencoEsiti"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "VerificaDatiPagamentoException"),
                      "VerificaDatiPagamentoException",
                      new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "VerificaDatiPagamentoException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("leggiIuvByToken");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriLeggiIuvByToken"), ParametriLeggiIuvByToken.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoLeggiIuv"));
        oper.setReturnClass(EsitoLeggiIuv.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("annullaPagamento");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriAnnullaPagamento"), ParametriAnnullaPagamento.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoAnnullaPagamento"));
        oper.setReturnClass(EsitoAnnullaPagamento.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "AnnullaPagamentoException"),
                      "AnnullaPagamentoException",
                      new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "AnnullaPagamentoException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVersion");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

    }

    public PagoPAServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public PagoPAServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service, String wsUser, String wsPwd) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
         this.wsUser = wsUser;
         this.wsPwd = wsPwd;
    }

    public PagoPAServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "AnnullaPagamentoException");
            cachedSerQNames.add(qName);
            cls = AnnullaPagamentoException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "datiSingoloVersamento");
            cachedSerQNames.add(qName);
            cls = DatiSingoloVersamento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "datiVersamento");
            cachedSerQNames.add(qName);
            cls = DatiSingoloVersamento[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "datiSingoloVersamento");
            qName2 = new javax.xml.namespace.QName("", "datiSingoloVersamento");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "ElencoIuv");
            cachedSerQNames.add(qName);
            cls = ElencoIuv.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoAnnullaPagamento");
            cachedSerQNames.add(qName);
            cls = EsitoAnnullaPagamento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoGeneraAvvisoPagamento");
            cachedSerQNames.add(qName);
            cls = EsitoGeneraAvvisoPagamento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoLeggiDocumento");
            cachedSerQNames.add(qName);
            cls = EsitoLeggiDocumento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoLeggiIuv");
            cachedSerQNames.add(qName);
            cls = EsitoLeggiIuv.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoVerificaDatiPagamentoMultiplo");
            cachedSerQNames.add(qName);
            cls = EsitoVerificaDatiPagamentoSingolo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoVerificaDatiPagamentoSingolo");
            qName2 = new javax.xml.namespace.QName("", "elencoEsiti");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoVerificaDatiPagamentoSingolo");
            cachedSerQNames.add(qName);
            cls = EsitoVerificaDatiPagamentoSingolo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "GeneraAvvisoPagamentoException");
            cachedSerQNames.add(qName);
            cls = GeneraAvvisoPagamentoException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "LeggiDocumentoException");
            cachedSerQNames.add(qName);
            cls = LeggiDocumentoException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriAnnullaPagamento");
            cachedSerQNames.add(qName);
            cls = ParametriAnnullaPagamento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriGeneraAvvisoPagamento");
            cachedSerQNames.add(qName);
            cls = ParametriGeneraAvvisoPagamento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriLeggiDocumento");
            cachedSerQNames.add(qName);
            cls = ParametriLeggiDocumento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriLeggiIuv");
            cachedSerQNames.add(qName);
            cls = ParametriLeggiIuv.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriLeggiIuvByToken");
            cachedSerQNames.add(qName);
            cls = ParametriLeggiIuvByToken.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriVerificaDatiPagamentoMultiplo");
            cachedSerQNames.add(qName);
            cls = ParametriVerificaDatiPagamentoSingolo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriVerificaDatiPagamentoSingolo");
            qName2 = new javax.xml.namespace.QName("", "elencoPagamenti");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriVerificaDatiPagamentoSingolo");
            cachedSerQNames.add(qName);
            cls = ParametriVerificaDatiPagamentoSingolo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "VerificaDatiPagamentoException");
            cachedSerQNames.add(qName);
            cls = VerificaDatiPagamentoException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public EsitoVerificaDatiPagamentoSingolo verificaDatiPagamentoSingolo(ParametriVerificaDatiPagamentoSingolo arg0) throws java.rmi.RemoteException, VerificaDatiPagamentoException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "verificaDatiPagamentoSingolo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {arg0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (EsitoVerificaDatiPagamentoSingolo) _resp;
            } catch (java.lang.Exception _exception) {
                return (EsitoVerificaDatiPagamentoSingolo) org.apache.axis.utils.JavaUtils.convert(_resp, EsitoVerificaDatiPagamentoSingolo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof VerificaDatiPagamentoException) {
              throw (VerificaDatiPagamentoException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public EsitoGeneraAvvisoPagamento generaAvvisoPagamento(ParametriGeneraAvvisoPagamento arg0) throws java.rmi.RemoteException, GeneraAvvisoPagamentoException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "generaAvvisoPagamento"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {arg0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (EsitoGeneraAvvisoPagamento) _resp;
            } catch (java.lang.Exception _exception) {
                return (EsitoGeneraAvvisoPagamento) org.apache.axis.utils.JavaUtils.convert(_resp, EsitoGeneraAvvisoPagamento.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof GeneraAvvisoPagamentoException) {
              throw (GeneraAvvisoPagamentoException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public EsitoLeggiDocumento leggiDocumento(ParametriLeggiDocumento arg0) throws java.rmi.RemoteException, LeggiDocumentoException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "leggiDocumento"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {arg0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (EsitoLeggiDocumento) _resp;
            } catch (java.lang.Exception _exception) {
                return (EsitoLeggiDocumento) org.apache.axis.utils.JavaUtils.convert(_resp, EsitoLeggiDocumento.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof LeggiDocumentoException) {
              throw (LeggiDocumentoException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public EsitoLeggiIuv leggiIuv(ParametriLeggiIuv arg0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "leggiIuv"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {arg0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (EsitoLeggiIuv) _resp;
            } catch (java.lang.Exception _exception) {
                return (EsitoLeggiIuv) org.apache.axis.utils.JavaUtils.convert(_resp, EsitoLeggiIuv.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public EsitoVerificaDatiPagamentoSingolo[] verificaDatiPagamentoMultiplo(ParametriVerificaDatiPagamentoSingolo[] arg0) throws java.rmi.RemoteException, VerificaDatiPagamentoException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "verificaDatiPagamentoMultiplo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {arg0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (EsitoVerificaDatiPagamentoSingolo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (EsitoVerificaDatiPagamentoSingolo[]) org.apache.axis.utils.JavaUtils.convert(_resp, EsitoVerificaDatiPagamentoSingolo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof VerificaDatiPagamentoException) {
              throw (VerificaDatiPagamentoException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public EsitoLeggiIuv leggiIuvByToken(ParametriLeggiIuvByToken arg0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "leggiIuvByToken"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {arg0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (EsitoLeggiIuv) _resp;
            } catch (java.lang.Exception _exception) {
                return (EsitoLeggiIuv) org.apache.axis.utils.JavaUtils.convert(_resp, EsitoLeggiIuv.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public EsitoAnnullaPagamento annullaPagamento(ParametriAnnullaPagamento arg0) throws java.rmi.RemoteException, AnnullaPagamentoException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "annullaPagamento"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {arg0});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (EsitoAnnullaPagamento) _resp;
            } catch (java.lang.Exception _exception) {
                return (EsitoAnnullaPagamento) org.apache.axis.utils.JavaUtils.convert(_resp, EsitoAnnullaPagamento.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof AnnullaPagamentoException) {
              throw (AnnullaPagamentoException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getVersion() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "getVersion"));

        setRequestHeaders(_call);
        setAttachments(_call);
		 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});
		
		        if (_resp instanceof java.rmi.RemoteException) {
		            throw (java.rmi.RemoteException)_resp;
		        }
		        else {
		            extractAttachments(_call);
		            try {
		                return (java.lang.String) _resp;
		            } catch (java.lang.Exception _exception) {
		                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
		            }
		        }
		  } catch (org.apache.axis.AxisFault axisFaultException) {
		  throw axisFaultException;
		}
    }
    
//    private void addWsSecurityHeader(org.apache.axis.client.Call _call)throws Exception {
//		if(wsUser != null && wsPwd != null){
//		    // Create the top-level WS-Security SOAP header XML name.
//		    QName headerName = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security");
//		    SOAPHeaderElement header = new SOAPHeaderElement(headerName);
//		    //  no intermediate actors are involved.
//		    header.setActor(null);
//		    // not important, "wsse" is standard
//		    header.setPrefix("wsse");
//		    //header.setMustUnderstand(true);
//	
//		    // Add the UsernameToken element to the WS-Security header
//		    SOAPElement utElem = header.addChildElement("UsernameToken");
//		    SOAPElement userNameElem = utElem.addChildElement("Username");
//		    userNameElem.removeContents();
//		    userNameElem.setValue(wsUser);
//	
//		    SOAPElement passwordElem = utElem.addChildElement("Password");
//		    //passwordElem.setValue(UtilCrypt.decrypt(wsPwd));
//		    passwordElem.setValue(wsPwd);
//		    // Finally, attach the header to the binding.
//		    //binding.setHeader(header);
//		    _call.addHeader(header);
//		}
//	}

}
