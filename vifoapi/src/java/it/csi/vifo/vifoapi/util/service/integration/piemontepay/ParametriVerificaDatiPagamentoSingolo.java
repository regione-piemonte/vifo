/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/**
 * ParametriVerificaDatiPagamentoSingolo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

public class ParametriVerificaDatiPagamentoSingolo  implements java.io.Serializable {
    private java.lang.String applicationId;

    private java.lang.String iuv;

    private java.lang.String codiceFiscalePIVA;

    public ParametriVerificaDatiPagamentoSingolo() {
    }

    public ParametriVerificaDatiPagamentoSingolo(
           java.lang.String applicationId,
           java.lang.String iuv,
           java.lang.String codiceFiscalePIVA) {
           this.applicationId = applicationId;
           this.iuv = iuv;
           this.codiceFiscalePIVA = codiceFiscalePIVA;
    }


    /**
     * Gets the applicationId value for this ParametriVerificaDatiPagamentoSingolo.
     * 
     * @return applicationId
     */
    public java.lang.String getApplicationId() {
        return applicationId;
    }


    /**
     * Sets the applicationId value for this ParametriVerificaDatiPagamentoSingolo.
     * 
     * @param applicationId
     */
    public void setApplicationId(java.lang.String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * Gets the iuv value for this ParametriVerificaDatiPagamentoSingolo.
     * 
     * @return iuv
     */
    public java.lang.String getIuv() {
        return iuv;
    }


    /**
     * Sets the iuv value for this ParametriVerificaDatiPagamentoSingolo.
     * 
     * @param iuv
     */
    public void setIuv(java.lang.String iuv) {
        this.iuv = iuv;
    }


    /**
     * Gets the codiceFiscalePIVA value for this ParametriVerificaDatiPagamentoSingolo.
     * 
     * @return codiceFiscalePIVA
     */
    public java.lang.String getCodiceFiscalePIVA() {
        return codiceFiscalePIVA;
    }


    /**
     * Sets the codiceFiscalePIVA value for this ParametriVerificaDatiPagamentoSingolo.
     * 
     * @param codiceFiscalePIVA
     */
    public void setCodiceFiscalePIVA(java.lang.String codiceFiscalePIVA) {
        this.codiceFiscalePIVA = codiceFiscalePIVA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametriVerificaDatiPagamentoSingolo)) return false;
        ParametriVerificaDatiPagamentoSingolo other = (ParametriVerificaDatiPagamentoSingolo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationId==null && other.getApplicationId()==null) || 
             (this.applicationId!=null &&
              this.applicationId.equals(other.getApplicationId()))) &&
            ((this.iuv==null && other.getIuv()==null) || 
             (this.iuv!=null &&
              this.iuv.equals(other.getIuv()))) &&
            ((this.codiceFiscalePIVA==null && other.getCodiceFiscalePIVA()==null) || 
             (this.codiceFiscalePIVA!=null &&
              this.codiceFiscalePIVA.equals(other.getCodiceFiscalePIVA())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getApplicationId() != null) {
            _hashCode += getApplicationId().hashCode();
        }
        if (getIuv() != null) {
            _hashCode += getIuv().hashCode();
        }
        if (getCodiceFiscalePIVA() != null) {
            _hashCode += getCodiceFiscalePIVA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametriVerificaDatiPagamentoSingolo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriVerificaDatiPagamentoSingolo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "applicationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iuv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iuv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFiscalePIVA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceFiscalePIVA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
