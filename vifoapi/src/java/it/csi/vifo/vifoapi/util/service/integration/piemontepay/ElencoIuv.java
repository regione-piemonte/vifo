/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/**
 * ElencoIuv.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

public class ElencoIuv  implements java.io.Serializable {
    private java.lang.String iuv;

    private java.lang.String codiceSpecificaFruitore;

    private java.lang.String descrizioneSpecificaFruitore;

    private java.lang.String codiceEsito;

    private java.lang.String esito;

    private java.util.Calendar dataEsito;

    private java.util.Calendar dataRichiestaPagamento;

    private java.util.Calendar dataScadenza;

    private java.math.BigDecimal importoDovuto;

    private java.math.BigDecimal importoPagato;

    private java.lang.String tipoPagamento;

    public ElencoIuv() {
    }

    public ElencoIuv(
           java.lang.String iuv,
           java.lang.String codiceSpecificaFruitore,
           java.lang.String descrizioneSpecificaFruitore,
           java.lang.String codiceEsito,
           java.lang.String esito,
           java.util.Calendar dataEsito,
           java.util.Calendar dataRichiestaPagamento,
           java.util.Calendar dataScadenza,
           java.math.BigDecimal importoDovuto,
           java.math.BigDecimal importoPagato,
           java.lang.String tipoPagamento) {
           this.iuv = iuv;
           this.codiceSpecificaFruitore = codiceSpecificaFruitore;
           this.descrizioneSpecificaFruitore = descrizioneSpecificaFruitore;
           this.codiceEsito = codiceEsito;
           this.esito = esito;
           this.dataEsito = dataEsito;
           this.dataRichiestaPagamento = dataRichiestaPagamento;
           this.dataScadenza = dataScadenza;
           this.importoDovuto = importoDovuto;
           this.importoPagato = importoPagato;
           this.tipoPagamento = tipoPagamento;
    }


    /**
     * Gets the iuv value for this ElencoIuv.
     * 
     * @return iuv
     */
    public java.lang.String getIuv() {
        return iuv;
    }


    /**
     * Sets the iuv value for this ElencoIuv.
     * 
     * @param iuv
     */
    public void setIuv(java.lang.String iuv) {
        this.iuv = iuv;
    }


    /**
     * Gets the codiceSpecificaFruitore value for this ElencoIuv.
     * 
     * @return codiceSpecificaFruitore
     */
    public java.lang.String getCodiceSpecificaFruitore() {
        return codiceSpecificaFruitore;
    }


    /**
     * Sets the codiceSpecificaFruitore value for this ElencoIuv.
     * 
     * @param codiceSpecificaFruitore
     */
    public void setCodiceSpecificaFruitore(java.lang.String codiceSpecificaFruitore) {
        this.codiceSpecificaFruitore = codiceSpecificaFruitore;
    }


    /**
     * Gets the descrizioneSpecificaFruitore value for this ElencoIuv.
     * 
     * @return descrizioneSpecificaFruitore
     */
    public java.lang.String getDescrizioneSpecificaFruitore() {
        return descrizioneSpecificaFruitore;
    }


    /**
     * Sets the descrizioneSpecificaFruitore value for this ElencoIuv.
     * 
     * @param descrizioneSpecificaFruitore
     */
    public void setDescrizioneSpecificaFruitore(java.lang.String descrizioneSpecificaFruitore) {
        this.descrizioneSpecificaFruitore = descrizioneSpecificaFruitore;
    }


    /**
     * Gets the codiceEsito value for this ElencoIuv.
     * 
     * @return codiceEsito
     */
    public java.lang.String getCodiceEsito() {
        return codiceEsito;
    }


    /**
     * Sets the codiceEsito value for this ElencoIuv.
     * 
     * @param codiceEsito
     */
    public void setCodiceEsito(java.lang.String codiceEsito) {
        this.codiceEsito = codiceEsito;
    }


    /**
     * Gets the esito value for this ElencoIuv.
     * 
     * @return esito
     */
    public java.lang.String getEsito() {
        return esito;
    }


    /**
     * Sets the esito value for this ElencoIuv.
     * 
     * @param esito
     */
    public void setEsito(java.lang.String esito) {
        this.esito = esito;
    }


    /**
     * Gets the dataEsito value for this ElencoIuv.
     * 
     * @return dataEsito
     */
    public java.util.Calendar getDataEsito() {
        return dataEsito;
    }


    /**
     * Sets the dataEsito value for this ElencoIuv.
     * 
     * @param dataEsito
     */
    public void setDataEsito(java.util.Calendar dataEsito) {
        this.dataEsito = dataEsito;
    }


    /**
     * Gets the dataRichiestaPagamento value for this ElencoIuv.
     * 
     * @return dataRichiestaPagamento
     */
    public java.util.Calendar getDataRichiestaPagamento() {
        return dataRichiestaPagamento;
    }


    /**
     * Sets the dataRichiestaPagamento value for this ElencoIuv.
     * 
     * @param dataRichiestaPagamento
     */
    public void setDataRichiestaPagamento(java.util.Calendar dataRichiestaPagamento) {
        this.dataRichiestaPagamento = dataRichiestaPagamento;
    }


    /**
     * Gets the dataScadenza value for this ElencoIuv.
     * 
     * @return dataScadenza
     */
    public java.util.Calendar getDataScadenza() {
        return dataScadenza;
    }


    /**
     * Sets the dataScadenza value for this ElencoIuv.
     * 
     * @param dataScadenza
     */
    public void setDataScadenza(java.util.Calendar dataScadenza) {
        this.dataScadenza = dataScadenza;
    }


    /**
     * Gets the importoDovuto value for this ElencoIuv.
     * 
     * @return importoDovuto
     */
    public java.math.BigDecimal getImportoDovuto() {
        return importoDovuto;
    }


    /**
     * Sets the importoDovuto value for this ElencoIuv.
     * 
     * @param importoDovuto
     */
    public void setImportoDovuto(java.math.BigDecimal importoDovuto) {
        this.importoDovuto = importoDovuto;
    }


    /**
     * Gets the importoPagato value for this ElencoIuv.
     * 
     * @return importoPagato
     */
    public java.math.BigDecimal getImportoPagato() {
        return importoPagato;
    }


    /**
     * Sets the importoPagato value for this ElencoIuv.
     * 
     * @param importoPagato
     */
    public void setImportoPagato(java.math.BigDecimal importoPagato) {
        this.importoPagato = importoPagato;
    }


    /**
     * Gets the tipoPagamento value for this ElencoIuv.
     * 
     * @return tipoPagamento
     */
    public java.lang.String getTipoPagamento() {
        return tipoPagamento;
    }


    /**
     * Sets the tipoPagamento value for this ElencoIuv.
     * 
     * @param tipoPagamento
     */
    public void setTipoPagamento(java.lang.String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElencoIuv)) return false;
        ElencoIuv other = (ElencoIuv) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.iuv==null && other.getIuv()==null) || 
             (this.iuv!=null &&
              this.iuv.equals(other.getIuv()))) &&
            ((this.codiceSpecificaFruitore==null && other.getCodiceSpecificaFruitore()==null) || 
             (this.codiceSpecificaFruitore!=null &&
              this.codiceSpecificaFruitore.equals(other.getCodiceSpecificaFruitore()))) &&
            ((this.descrizioneSpecificaFruitore==null && other.getDescrizioneSpecificaFruitore()==null) || 
             (this.descrizioneSpecificaFruitore!=null &&
              this.descrizioneSpecificaFruitore.equals(other.getDescrizioneSpecificaFruitore()))) &&
            ((this.codiceEsito==null && other.getCodiceEsito()==null) || 
             (this.codiceEsito!=null &&
              this.codiceEsito.equals(other.getCodiceEsito()))) &&
            ((this.esito==null && other.getEsito()==null) || 
             (this.esito!=null &&
              this.esito.equals(other.getEsito()))) &&
            ((this.dataEsito==null && other.getDataEsito()==null) || 
             (this.dataEsito!=null &&
              this.dataEsito.equals(other.getDataEsito()))) &&
            ((this.dataRichiestaPagamento==null && other.getDataRichiestaPagamento()==null) || 
             (this.dataRichiestaPagamento!=null &&
              this.dataRichiestaPagamento.equals(other.getDataRichiestaPagamento()))) &&
            ((this.dataScadenza==null && other.getDataScadenza()==null) || 
             (this.dataScadenza!=null &&
              this.dataScadenza.equals(other.getDataScadenza()))) &&
            ((this.importoDovuto==null && other.getImportoDovuto()==null) || 
             (this.importoDovuto!=null &&
              this.importoDovuto.equals(other.getImportoDovuto()))) &&
            ((this.importoPagato==null && other.getImportoPagato()==null) || 
             (this.importoPagato!=null &&
              this.importoPagato.equals(other.getImportoPagato()))) &&
            ((this.tipoPagamento==null && other.getTipoPagamento()==null) || 
             (this.tipoPagamento!=null &&
              this.tipoPagamento.equals(other.getTipoPagamento())));
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
        if (getIuv() != null) {
            _hashCode += getIuv().hashCode();
        }
        if (getCodiceSpecificaFruitore() != null) {
            _hashCode += getCodiceSpecificaFruitore().hashCode();
        }
        if (getDescrizioneSpecificaFruitore() != null) {
            _hashCode += getDescrizioneSpecificaFruitore().hashCode();
        }
        if (getCodiceEsito() != null) {
            _hashCode += getCodiceEsito().hashCode();
        }
        if (getEsito() != null) {
            _hashCode += getEsito().hashCode();
        }
        if (getDataEsito() != null) {
            _hashCode += getDataEsito().hashCode();
        }
        if (getDataRichiestaPagamento() != null) {
            _hashCode += getDataRichiestaPagamento().hashCode();
        }
        if (getDataScadenza() != null) {
            _hashCode += getDataScadenza().hashCode();
        }
        if (getImportoDovuto() != null) {
            _hashCode += getImportoDovuto().hashCode();
        }
        if (getImportoPagato() != null) {
            _hashCode += getImportoPagato().hashCode();
        }
        if (getTipoPagamento() != null) {
            _hashCode += getTipoPagamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ElencoIuv.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "ElencoIuv"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iuv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "iuv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceSpecificaFruitore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceSpecificaFruitore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneSpecificaFruitore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneSpecificaFruitore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceEsito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceEsito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataEsito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataEsito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRichiestaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataRichiestaPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataScadenza");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataScadenza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoDovuto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "importoDovuto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoPagato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "importoPagato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoPagamento"));
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
