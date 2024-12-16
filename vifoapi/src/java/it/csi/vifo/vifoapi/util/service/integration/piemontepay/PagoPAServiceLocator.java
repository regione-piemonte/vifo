/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

public class PagoPAServiceLocator extends org.apache.axis.client.Service implements PagoPAService {
	
	private String wsUser;
	private String wsPwd;

    public PagoPAServiceLocator(String wsUser, String wsPwd) {
		this.wsUser = wsUser;
		this.wsPwd = wsPwd;
    }


    public PagoPAServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PagoPAServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PagoPAWSPort
    private java.lang.String PagoPAWSPort_address = "http://www.example.com";

    public java.lang.String getPagoPAWSPortAddress() {
        return PagoPAWSPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PagoPAWSPortWSDDServiceName = "PagoPAWSPort";

    public java.lang.String getPagoPAWSPortWSDDServiceName() {
        return PagoPAWSPortWSDDServiceName;
    }

    public void setPagoPAWSPortWSDDServiceName(java.lang.String name) {
        PagoPAWSPortWSDDServiceName = name;
    }

    public PagoPAWS getPagoPAWSPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PagoPAWSPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPagoPAWSPort(endpoint);
    }

    public PagoPAWS getPagoPAWSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            PagoPAServiceSoapBindingStub _stub = new PagoPAServiceSoapBindingStub(portAddress, this, this.wsUser, this.wsPwd);
            _stub.setPortName(getPagoPAWSPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPagoPAWSPortEndpointAddress(java.lang.String address) {
        PagoPAWSPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (PagoPAWS.class.isAssignableFrom(serviceEndpointInterface)) {
                PagoPAServiceSoapBindingStub _stub = new PagoPAServiceSoapBindingStub(new java.net.URL(PagoPAWSPort_address), this, this.wsUser, this.wsPwd);
                _stub.setPortName(getPagoPAWSPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PagoPAWSPort".equals(inputPortName)) {
            return getPagoPAWSPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "PagoPAService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "PagoPAWSPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PagoPAWSPort".equals(portName)) {
            setPagoPAWSPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
