/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/**
 * PagoPAService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

public interface PagoPAService extends javax.xml.rpc.Service {
    public java.lang.String getPagoPAWSPortAddress();

    public PagoPAWS getPagoPAWSPort() throws javax.xml.rpc.ServiceException;

    public PagoPAWS getPagoPAWSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
