/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/**
 * EsitoGeneraAvvisoPagamento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

public class EsitoGeneraAvvisoPagamento  implements java.io.Serializable {
    private java.lang.String appId;

    private java.lang.String iuv;

    private byte[] file;

    private java.lang.String idErrore;

    private java.lang.String descErrore;

    public EsitoGeneraAvvisoPagamento() {
    }

    public EsitoGeneraAvvisoPagamento(
           java.lang.String appId,
           java.lang.String iuv,
           byte[] file,
           java.lang.String idErrore,
           java.lang.String descErrore) {
           this.appId = appId;
           this.iuv = iuv;
           this.file = file;
           this.idErrore = idErrore;
           this.descErrore = descErrore;
    }


    /**
     * Gets the appId value for this EsitoGeneraAvvisoPagamento.
     * 
     * @return appId
     */
    public java.lang.String getAppId() {
        return appId;
    }


    /**
     * Sets the appId value for this EsitoGeneraAvvisoPagamento.
     * 
     * @param appId
     */
    public void setAppId(java.lang.String appId) {
        this.appId = appId;
    }


    /**
     * Gets the iuv value for this EsitoGeneraAvvisoPagamento.
     * 
     * @return iuv
     */
    public java.lang.String getIuv() {
        return iuv;
    }


    /**
     * Sets the iuv value for this EsitoGeneraAvvisoPagamento.
     * 
     * @param iuv
     */
    public void setIuv(java.lang.String iuv) {
        this.iuv = iuv;
    }


    /**
     * Gets the file value for this EsitoGeneraAvvisoPagamento.
     * 
     * @return file
     */
    public byte[] getFile() {
        return file;
    }


    /**
     * Sets the file value for this EsitoGeneraAvvisoPagamento.
     * 
     * @param file
     */
    public void setFile(byte[] file) {
        this.file = file;
    }


    /**
     * Gets the idErrore value for this EsitoGeneraAvvisoPagamento.
     * 
     * @return idErrore
     */
    public java.lang.String getIdErrore() {
        return idErrore;
    }


    /**
     * Sets the idErrore value for this EsitoGeneraAvvisoPagamento.
     * 
     * @param idErrore
     */
    public void setIdErrore(java.lang.String idErrore) {
        this.idErrore = idErrore;
    }


    /**
     * Gets the descErrore value for this EsitoGeneraAvvisoPagamento.
     * 
     * @return descErrore
     */
    public java.lang.String getDescErrore() {
        return descErrore;
    }


    /**
     * Sets the descErrore value for this EsitoGeneraAvvisoPagamento.
     * 
     * @param descErrore
     */
    public void setDescErrore(java.lang.String descErrore) {
        this.descErrore = descErrore;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EsitoGeneraAvvisoPagamento)) return false;
        EsitoGeneraAvvisoPagamento other = (EsitoGeneraAvvisoPagamento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.appId==null && other.getAppId()==null) || 
             (this.appId!=null &&
              this.appId.equals(other.getAppId()))) &&
            ((this.iuv==null && other.getIuv()==null) || 
             (this.iuv!=null &&
              this.iuv.equals(other.getIuv()))) &&
            ((this.file==null && other.getFile()==null) || 
             (this.file!=null &&
              java.util.Arrays.equals(this.file, other.getFile()))) &&
            ((this.idErrore==null && other.getIdErrore()==null) || 
             (this.idErrore!=null &&
              this.idErrore.equals(other.getIdErrore()))) &&
            ((this.descErrore==null && other.getDescErrore()==null) || 
             (this.descErrore!=null &&
              this.descErrore.equals(other.getDescErrore())));
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
        if (getAppId() != null) {
            _hashCode += getAppId().hashCode();
        }
        if (getIuv() != null) {
            _hashCode += getIuv().hashCode();
        }
        if (getFile() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFile());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFile(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdErrore() != null) {
            _hashCode += getIdErrore().hashCode();
        }
        if (getDescErrore() != null) {
            _hashCode += getDescErrore().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EsitoGeneraAvvisoPagamento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoGeneraAvvisoPagamento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iuv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iuv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("file");
        elemField.setXmlName(new javax.xml.namespace.QName("", "file"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idErrore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idErrore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descErrore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descErrore"));
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
