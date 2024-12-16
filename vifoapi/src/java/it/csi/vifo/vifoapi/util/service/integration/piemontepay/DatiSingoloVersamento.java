/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/**
 * DatiSingoloVersamento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

public class DatiSingoloVersamento  implements java.io.Serializable {
    private java.lang.String descrizionePagamento;

    private java.math.BigDecimal importoSingoloVersamento;

    public DatiSingoloVersamento() {
    }

    public DatiSingoloVersamento(
           java.lang.String descrizionePagamento,
           java.math.BigDecimal importoSingoloVersamento) {
           this.descrizionePagamento = descrizionePagamento;
           this.importoSingoloVersamento = importoSingoloVersamento;
    }


    /**
     * Gets the descrizionePagamento value for this DatiSingoloVersamento.
     * 
     * @return descrizionePagamento
     */
    public java.lang.String getDescrizionePagamento() {
        return descrizionePagamento;
    }


    /**
     * Sets the descrizionePagamento value for this DatiSingoloVersamento.
     * 
     * @param descrizionePagamento
     */
    public void setDescrizionePagamento(java.lang.String descrizionePagamento) {
        this.descrizionePagamento = descrizionePagamento;
    }


    /**
     * Gets the importoSingoloVersamento value for this DatiSingoloVersamento.
     * 
     * @return importoSingoloVersamento
     */
    public java.math.BigDecimal getImportoSingoloVersamento() {
        return importoSingoloVersamento;
    }


    /**
     * Sets the importoSingoloVersamento value for this DatiSingoloVersamento.
     * 
     * @param importoSingoloVersamento
     */
    public void setImportoSingoloVersamento(java.math.BigDecimal importoSingoloVersamento) {
        this.importoSingoloVersamento = importoSingoloVersamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatiSingoloVersamento)) return false;
        DatiSingoloVersamento other = (DatiSingoloVersamento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descrizionePagamento==null && other.getDescrizionePagamento()==null) || 
             (this.descrizionePagamento!=null &&
              this.descrizionePagamento.equals(other.getDescrizionePagamento()))) &&
            ((this.importoSingoloVersamento==null && other.getImportoSingoloVersamento()==null) || 
             (this.importoSingoloVersamento!=null &&
              this.importoSingoloVersamento.equals(other.getImportoSingoloVersamento())));
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
        if (getDescrizionePagamento() != null) {
            _hashCode += getDescrizionePagamento().hashCode();
        }
        if (getImportoSingoloVersamento() != null) {
            _hashCode += getImportoSingoloVersamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatiSingoloVersamento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "datiSingoloVersamento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizionePagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizionePagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoSingoloVersamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "importoSingoloVersamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
