/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/**
 * ParametriLeggiIuvByToken.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

public class ParametriLeggiIuvByToken  implements java.io.Serializable {
    private java.lang.String idApplicativo;

    private java.lang.String codiceFiscalePIVA;

    private java.lang.String userToken;

    public ParametriLeggiIuvByToken() {
    }

    public ParametriLeggiIuvByToken(
           java.lang.String idApplicativo,
           java.lang.String codiceFiscalePIVA,
           java.lang.String userToken) {
           this.idApplicativo = idApplicativo;
           this.codiceFiscalePIVA = codiceFiscalePIVA;
           this.userToken = userToken;
    }


    /**
     * Gets the idApplicativo value for this ParametriLeggiIuvByToken.
     * 
     * @return idApplicativo
     */
    public java.lang.String getIdApplicativo() {
        return idApplicativo;
    }


    /**
     * Sets the idApplicativo value for this ParametriLeggiIuvByToken.
     * 
     * @param idApplicativo
     */
    public void setIdApplicativo(java.lang.String idApplicativo) {
        this.idApplicativo = idApplicativo;
    }


    /**
     * Gets the codiceFiscalePIVA value for this ParametriLeggiIuvByToken.
     * 
     * @return codiceFiscalePIVA
     */
    public java.lang.String getCodiceFiscalePIVA() {
        return codiceFiscalePIVA;
    }


    /**
     * Sets the codiceFiscalePIVA value for this ParametriLeggiIuvByToken.
     * 
     * @param codiceFiscalePIVA
     */
    public void setCodiceFiscalePIVA(java.lang.String codiceFiscalePIVA) {
        this.codiceFiscalePIVA = codiceFiscalePIVA;
    }


    /**
     * Gets the userToken value for this ParametriLeggiIuvByToken.
     * 
     * @return userToken
     */
    public java.lang.String getUserToken() {
        return userToken;
    }


    /**
     * Sets the userToken value for this ParametriLeggiIuvByToken.
     * 
     * @param userToken
     */
    public void setUserToken(java.lang.String userToken) {
        this.userToken = userToken;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametriLeggiIuvByToken)) return false;
        ParametriLeggiIuvByToken other = (ParametriLeggiIuvByToken) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idApplicativo==null && other.getIdApplicativo()==null) || 
             (this.idApplicativo!=null &&
              this.idApplicativo.equals(other.getIdApplicativo()))) &&
            ((this.codiceFiscalePIVA==null && other.getCodiceFiscalePIVA()==null) || 
             (this.codiceFiscalePIVA!=null &&
              this.codiceFiscalePIVA.equals(other.getCodiceFiscalePIVA()))) &&
            ((this.userToken==null && other.getUserToken()==null) || 
             (this.userToken!=null &&
              this.userToken.equals(other.getUserToken())));
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
        if (getIdApplicativo() != null) {
            _hashCode += getIdApplicativo().hashCode();
        }
        if (getCodiceFiscalePIVA() != null) {
            _hashCode += getCodiceFiscalePIVA().hashCode();
        }
        if (getUserToken() != null) {
            _hashCode += getUserToken().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametriLeggiIuvByToken.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriLeggiIuvByToken"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idApplicativo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idApplicativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFiscalePIVA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceFiscalePIVA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userToken");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userToken"));
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
