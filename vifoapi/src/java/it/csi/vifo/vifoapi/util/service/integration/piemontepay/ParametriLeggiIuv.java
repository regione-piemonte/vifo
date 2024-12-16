/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/**
 * ParametriLeggiIuv.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

public class ParametriLeggiIuv  implements java.io.Serializable {
    private java.lang.String idApplicativo;

    private java.lang.String codiceFiscalePIVA;

    private java.lang.String dataInizioSelezione;

    private java.lang.String dataFineSelezione;

    public ParametriLeggiIuv() {
    }

    public ParametriLeggiIuv(
           java.lang.String idApplicativo,
           java.lang.String codiceFiscalePIVA,
           java.lang.String dataInizioSelezione,
           java.lang.String dataFineSelezione) {
           this.idApplicativo = idApplicativo;
           this.codiceFiscalePIVA = codiceFiscalePIVA;
           this.dataInizioSelezione = dataInizioSelezione;
           this.dataFineSelezione = dataFineSelezione;
    }


    /**
     * Gets the idApplicativo value for this ParametriLeggiIuv.
     * 
     * @return idApplicativo
     */
    public java.lang.String getIdApplicativo() {
        return idApplicativo;
    }


    /**
     * Sets the idApplicativo value for this ParametriLeggiIuv.
     * 
     * @param idApplicativo
     */
    public void setIdApplicativo(java.lang.String idApplicativo) {
        this.idApplicativo = idApplicativo;
    }


    /**
     * Gets the codiceFiscalePIVA value for this ParametriLeggiIuv.
     * 
     * @return codiceFiscalePIVA
     */
    public java.lang.String getCodiceFiscalePIVA() {
        return codiceFiscalePIVA;
    }


    /**
     * Sets the codiceFiscalePIVA value for this ParametriLeggiIuv.
     * 
     * @param codiceFiscalePIVA
     */
    public void setCodiceFiscalePIVA(java.lang.String codiceFiscalePIVA) {
        this.codiceFiscalePIVA = codiceFiscalePIVA;
    }


    /**
     * Gets the dataInizioSelezione value for this ParametriLeggiIuv.
     * 
     * @return dataInizioSelezione
     */
    public java.lang.String getDataInizioSelezione() {
        return dataInizioSelezione;
    }


    /**
     * Sets the dataInizioSelezione value for this ParametriLeggiIuv.
     * 
     * @param dataInizioSelezione
     */
    public void setDataInizioSelezione(java.lang.String dataInizioSelezione) {
        this.dataInizioSelezione = dataInizioSelezione;
    }


    /**
     * Gets the dataFineSelezione value for this ParametriLeggiIuv.
     * 
     * @return dataFineSelezione
     */
    public java.lang.String getDataFineSelezione() {
        return dataFineSelezione;
    }


    /**
     * Sets the dataFineSelezione value for this ParametriLeggiIuv.
     * 
     * @param dataFineSelezione
     */
    public void setDataFineSelezione(java.lang.String dataFineSelezione) {
        this.dataFineSelezione = dataFineSelezione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametriLeggiIuv)) return false;
        ParametriLeggiIuv other = (ParametriLeggiIuv) obj;
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
            ((this.dataInizioSelezione==null && other.getDataInizioSelezione()==null) || 
             (this.dataInizioSelezione!=null &&
              this.dataInizioSelezione.equals(other.getDataInizioSelezione()))) &&
            ((this.dataFineSelezione==null && other.getDataFineSelezione()==null) || 
             (this.dataFineSelezione!=null &&
              this.dataFineSelezione.equals(other.getDataFineSelezione())));
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
        if (getDataInizioSelezione() != null) {
            _hashCode += getDataInizioSelezione().hashCode();
        }
        if (getDataFineSelezione() != null) {
            _hashCode += getDataFineSelezione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametriLeggiIuv.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriLeggiIuv"));
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
        elemField.setFieldName("dataInizioSelezione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataInizioSelezione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFineSelezione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataFineSelezione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
