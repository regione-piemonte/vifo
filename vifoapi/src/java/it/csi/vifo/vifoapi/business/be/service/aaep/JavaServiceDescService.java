/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/**
 * JavaServiceDescService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.vifo.vifoapi.business.be.service.aaep;

public interface JavaServiceDescService extends javax.xml.rpc.Service {
    public java.lang.String getAAEPCSIAddress();

    public it.csi.vifo.vifoapi.business.be.service.aaep.JavaServiceDesc getAAEPCSI() throws javax.xml.rpc.ServiceException;

    public it.csi.vifo.vifoapi.business.be.service.aaep.JavaServiceDesc getAAEPCSI(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
