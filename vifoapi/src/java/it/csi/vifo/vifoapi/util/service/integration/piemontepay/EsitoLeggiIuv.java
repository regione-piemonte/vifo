/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/**
 * EsitoLeggiIuv.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

public class EsitoLeggiIuv  implements java.io.Serializable {
    private java.lang.String idApplicativo;

    private java.lang.String codiceFiscalePIVA;

    private ElencoIuv[] elencoIuv;

    private java.lang.String idErrore;

    private java.lang.String descErrore;

    public EsitoLeggiIuv() {
    }

    public EsitoLeggiIuv(
           java.lang.String idApplicativo,
           java.lang.String codiceFiscalePIVA,
           ElencoIuv[] elencoIuv,
           java.lang.String idErrore,
           java.lang.String descErrore) {
           this.idApplicativo = idApplicativo;
           this.codiceFiscalePIVA = codiceFiscalePIVA;
           this.elencoIuv = elencoIuv;
           this.idErrore = idErrore;
           this.descErrore = descErrore;
    }


    /**
     * Gets the idApplicativo value for this EsitoLeggiIuv.
     * 
     * @return idApplicativo
     */
    public java.lang.String getIdApplicativo() {
        return idApplicativo;
    }


    /**
     * Sets the idApplicativo value for this EsitoLeggiIuv.
     * 
     * @param idApplicativo
     */
    public void setIdApplicativo(java.lang.String idApplicativo) {
        this.idApplicativo = idApplicativo;
    }


    /**
     * Gets the codiceFiscalePIVA value for this EsitoLeggiIuv.
     * 
     * @return codiceFiscalePIVA
     */
    public java.lang.String getCodiceFiscalePIVA() {
        return codiceFiscalePIVA;
    }


    /**
     * Sets the codiceFiscalePIVA value for this EsitoLeggiIuv.
     * 
     * @param codiceFiscalePIVA
     */
    public void setCodiceFiscalePIVA(java.lang.String codiceFiscalePIVA) {
        this.codiceFiscalePIVA = codiceFiscalePIVA;
    }


    /**
     * Gets the elencoIuv value for this EsitoLeggiIuv.
     * 
     * @return elencoIuv
     */
    public ElencoIuv[] getElencoIuv() {
        return elencoIuv;
    }


    /**
     * Sets the elencoIuv value for this EsitoLeggiIuv.
     * 
     * @param elencoIuv
     */
    public void setElencoIuv(ElencoIuv[] elencoIuv) {
        this.elencoIuv = elencoIuv;
    }

    public ElencoIuv getElencoIuv(int i) {
        return this.elencoIuv[i];
    }

    public void setElencoIuv(int i, ElencoIuv _value) {
        this.elencoIuv[i] = _value;
    }


    /**
     * Gets the idErrore value for this EsitoLeggiIuv.
     * 
     * @return idErrore
     */
    public java.lang.String getIdErrore() {
        return idErrore;
    }


    /**
     * Sets the idErrore value for this EsitoLeggiIuv.
     * 
     * @param idErrore
     */
    public void setIdErrore(java.lang.String idErrore) {
        this.idErrore = idErrore;
    }


    /**
     * Gets the descErrore value for this EsitoLeggiIuv.
     * 
     * @return descErrore
     */
    public java.lang.String getDescErrore() {
        return descErrore;
    }


    /**
     * Sets the descErrore value for this EsitoLeggiIuv.
     * 
     * @param descErrore
     */
    public void setDescErrore(java.lang.String descErrore) {
        this.descErrore = descErrore;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EsitoLeggiIuv)) return false;
        EsitoLeggiIuv other = (EsitoLeggiIuv) obj;
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
            ((this.elencoIuv==null && other.getElencoIuv()==null) || 
             (this.elencoIuv!=null &&
              java.util.Arrays.equals(this.elencoIuv, other.getElencoIuv()))) &&
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
        if (getIdApplicativo() != null) {
            _hashCode += getIdApplicativo().hashCode();
        }
        if (getCodiceFiscalePIVA() != null) {
            _hashCode += getCodiceFiscalePIVA().hashCode();
        }
        if (getElencoIuv() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getElencoIuv());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getElencoIuv(), i);
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
        new org.apache.axis.description.TypeDesc(EsitoLeggiIuv.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "esitoLeggiIuv"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idApplicativo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idApplicativo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFiscalePIVA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceFiscalePIVA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elencoIuv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "elencoIuv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "ElencoIuv"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
