/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/**
 * ParametriGeneraAvvisoPagamento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.csi.vifo.vifoapi.util.service.integration.piemontepay;

public class ParametriGeneraAvvisoPagamento  implements java.io.Serializable {
    private java.lang.Long idIntermediario;

    private java.math.BigDecimal importoTotale;

    private java.util.Date dataScadenzaPagamento;

    private java.lang.String appId;

    private java.lang.String codicePagamento;

    private java.lang.String descrizionePagamento;

    private java.lang.String tipoIdentificativoUnivocoPagatore;

    private java.lang.String codiceIdentificativoUnivocoPagatore;

    private java.lang.String idAnagraficaPagatore;

    private java.lang.String anagraficaPagatore;

    private java.lang.String mailPagatore;

    private java.lang.String pecPagatore;

    private java.lang.String indirizzoPagatore;

    private DatiSingoloVersamento[] datiVersamento;

    private java.lang.String oggettoPagamento;

    public ParametriGeneraAvvisoPagamento() {
    }

    public ParametriGeneraAvvisoPagamento(
           java.lang.Long idIntermediario,
           java.math.BigDecimal importoTotale,
           java.util.Date dataScadenzaPagamento,
           java.lang.String appId,
           java.lang.String codicePagamento,
           java.lang.String descrizionePagamento,
           java.lang.String tipoIdentificativoUnivocoPagatore,
           java.lang.String codiceIdentificativoUnivocoPagatore,
           java.lang.String idAnagraficaPagatore,
           java.lang.String anagraficaPagatore,
           java.lang.String mailPagatore,
           java.lang.String pecPagatore,
           java.lang.String indirizzoPagatore,
           DatiSingoloVersamento[] datiVersamento,
           java.lang.String oggettoPagamento) {
           this.idIntermediario = idIntermediario;
           this.importoTotale = importoTotale;
           this.dataScadenzaPagamento = dataScadenzaPagamento;
           this.appId = appId;
           this.codicePagamento = codicePagamento;
           this.descrizionePagamento = descrizionePagamento;
           this.tipoIdentificativoUnivocoPagatore = tipoIdentificativoUnivocoPagatore;
           this.codiceIdentificativoUnivocoPagatore = codiceIdentificativoUnivocoPagatore;
           this.idAnagraficaPagatore = idAnagraficaPagatore;
           this.anagraficaPagatore = anagraficaPagatore;
           this.mailPagatore = mailPagatore;
           this.pecPagatore = pecPagatore;
           this.indirizzoPagatore = indirizzoPagatore;
           this.datiVersamento = datiVersamento;
           this.oggettoPagamento = oggettoPagamento;
    }


    /**
     * Gets the idIntermediario value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return idIntermediario
     */
    public java.lang.Long getIdIntermediario() {
        return idIntermediario;
    }


    /**
     * Sets the idIntermediario value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param idIntermediario
     */
    public void setIdIntermediario(java.lang.Long idIntermediario) {
        this.idIntermediario = idIntermediario;
    }


    /**
     * Gets the importoTotale value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return importoTotale
     */
    public java.math.BigDecimal getImportoTotale() {
        return importoTotale;
    }


    /**
     * Sets the importoTotale value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param importoTotale
     */
    public void setImportoTotale(java.math.BigDecimal importoTotale) {
        this.importoTotale = importoTotale;
    }


    /**
     * Gets the dataScadenzaPagamento value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return dataScadenzaPagamento
     */
    public java.util.Date getDataScadenzaPagamento() {
        return dataScadenzaPagamento;
    }


    /**
     * Sets the dataScadenzaPagamento value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param dataScadenzaPagamento
     */
    public void setDataScadenzaPagamento(java.util.Date dataScadenzaPagamento) {
        this.dataScadenzaPagamento = dataScadenzaPagamento;
    }


    /**
     * Gets the appId value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return appId
     */
    public java.lang.String getAppId() {
        return appId;
    }


    /**
     * Sets the appId value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param appId
     */
    public void setAppId(java.lang.String appId) {
        this.appId = appId;
    }


    /**
     * Gets the codicePagamento value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return codicePagamento
     */
    public java.lang.String getCodicePagamento() {
        return codicePagamento;
    }


    /**
     * Sets the codicePagamento value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param codicePagamento
     */
    public void setCodicePagamento(java.lang.String codicePagamento) {
        this.codicePagamento = codicePagamento;
    }


    /**
     * Gets the descrizionePagamento value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return descrizionePagamento
     */
    public java.lang.String getDescrizionePagamento() {
        return descrizionePagamento;
    }


    /**
     * Sets the descrizionePagamento value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param descrizionePagamento
     */
    public void setDescrizionePagamento(java.lang.String descrizionePagamento) {
        this.descrizionePagamento = descrizionePagamento;
    }


    /**
     * Gets the tipoIdentificativoUnivocoPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return tipoIdentificativoUnivocoPagatore
     */
    public java.lang.String getTipoIdentificativoUnivocoPagatore() {
        return tipoIdentificativoUnivocoPagatore;
    }


    /**
     * Sets the tipoIdentificativoUnivocoPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param tipoIdentificativoUnivocoPagatore
     */
    public void setTipoIdentificativoUnivocoPagatore(java.lang.String tipoIdentificativoUnivocoPagatore) {
        this.tipoIdentificativoUnivocoPagatore = tipoIdentificativoUnivocoPagatore;
    }


    /**
     * Gets the codiceIdentificativoUnivocoPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return codiceIdentificativoUnivocoPagatore
     */
    public java.lang.String getCodiceIdentificativoUnivocoPagatore() {
        return codiceIdentificativoUnivocoPagatore;
    }


    /**
     * Sets the codiceIdentificativoUnivocoPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param codiceIdentificativoUnivocoPagatore
     */
    public void setCodiceIdentificativoUnivocoPagatore(java.lang.String codiceIdentificativoUnivocoPagatore) {
        this.codiceIdentificativoUnivocoPagatore = codiceIdentificativoUnivocoPagatore;
    }


    /**
     * Gets the idAnagraficaPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return idAnagraficaPagatore
     */
    public java.lang.String getIdAnagraficaPagatore() {
        return idAnagraficaPagatore;
    }


    /**
     * Sets the idAnagraficaPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param idAnagraficaPagatore
     */
    public void setIdAnagraficaPagatore(java.lang.String idAnagraficaPagatore) {
        this.idAnagraficaPagatore = idAnagraficaPagatore;
    }


    /**
     * Gets the anagraficaPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return anagraficaPagatore
     */
    public java.lang.String getAnagraficaPagatore() {
        return anagraficaPagatore;
    }


    /**
     * Sets the anagraficaPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param anagraficaPagatore
     */
    public void setAnagraficaPagatore(java.lang.String anagraficaPagatore) {
        this.anagraficaPagatore = anagraficaPagatore;
    }


    /**
     * Gets the mailPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return mailPagatore
     */
    public java.lang.String getMailPagatore() {
        return mailPagatore;
    }


    /**
     * Sets the mailPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param mailPagatore
     */
    public void setMailPagatore(java.lang.String mailPagatore) {
        this.mailPagatore = mailPagatore;
    }


    /**
     * Gets the pecPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return pecPagatore
     */
    public java.lang.String getPecPagatore() {
        return pecPagatore;
    }


    /**
     * Sets the pecPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param pecPagatore
     */
    public void setPecPagatore(java.lang.String pecPagatore) {
        this.pecPagatore = pecPagatore;
    }


    /**
     * Gets the indirizzoPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return indirizzoPagatore
     */
    public java.lang.String getIndirizzoPagatore() {
        return indirizzoPagatore;
    }


    /**
     * Sets the indirizzoPagatore value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param indirizzoPagatore
     */
    public void setIndirizzoPagatore(java.lang.String indirizzoPagatore) {
        this.indirizzoPagatore = indirizzoPagatore;
    }


    /**
     * Gets the datiVersamento value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return datiVersamento
     */
    public DatiSingoloVersamento[] getDatiVersamento() {
        return datiVersamento;
    }


    /**
     * Sets the datiVersamento value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param datiVersamento
     */
    public void setDatiVersamento(DatiSingoloVersamento[] datiVersamento) {
        this.datiVersamento = datiVersamento;
    }


    /**
     * Gets the oggettoPagamento value for this ParametriGeneraAvvisoPagamento.
     * 
     * @return oggettoPagamento
     */
    public java.lang.String getOggettoPagamento() {
        return oggettoPagamento;
    }


    /**
     * Sets the oggettoPagamento value for this ParametriGeneraAvvisoPagamento.
     * 
     * @param oggettoPagamento
     */
    public void setOggettoPagamento(java.lang.String oggettoPagamento) {
        this.oggettoPagamento = oggettoPagamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametriGeneraAvvisoPagamento)) return false;
        ParametriGeneraAvvisoPagamento other = (ParametriGeneraAvvisoPagamento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idIntermediario==null && other.getIdIntermediario()==null) || 
             (this.idIntermediario!=null &&
              this.idIntermediario.equals(other.getIdIntermediario()))) &&
            ((this.importoTotale==null && other.getImportoTotale()==null) || 
             (this.importoTotale!=null &&
              this.importoTotale.equals(other.getImportoTotale()))) &&
            ((this.dataScadenzaPagamento==null && other.getDataScadenzaPagamento()==null) || 
             (this.dataScadenzaPagamento!=null &&
              this.dataScadenzaPagamento.equals(other.getDataScadenzaPagamento()))) &&
            ((this.appId==null && other.getAppId()==null) || 
             (this.appId!=null &&
              this.appId.equals(other.getAppId()))) &&
            ((this.codicePagamento==null && other.getCodicePagamento()==null) || 
             (this.codicePagamento!=null &&
              this.codicePagamento.equals(other.getCodicePagamento()))) &&
            ((this.descrizionePagamento==null && other.getDescrizionePagamento()==null) || 
             (this.descrizionePagamento!=null &&
              this.descrizionePagamento.equals(other.getDescrizionePagamento()))) &&
            ((this.tipoIdentificativoUnivocoPagatore==null && other.getTipoIdentificativoUnivocoPagatore()==null) || 
             (this.tipoIdentificativoUnivocoPagatore!=null &&
              this.tipoIdentificativoUnivocoPagatore.equals(other.getTipoIdentificativoUnivocoPagatore()))) &&
            ((this.codiceIdentificativoUnivocoPagatore==null && other.getCodiceIdentificativoUnivocoPagatore()==null) || 
             (this.codiceIdentificativoUnivocoPagatore!=null &&
              this.codiceIdentificativoUnivocoPagatore.equals(other.getCodiceIdentificativoUnivocoPagatore()))) &&
            ((this.idAnagraficaPagatore==null && other.getIdAnagraficaPagatore()==null) || 
             (this.idAnagraficaPagatore!=null &&
              this.idAnagraficaPagatore.equals(other.getIdAnagraficaPagatore()))) &&
            ((this.anagraficaPagatore==null && other.getAnagraficaPagatore()==null) || 
             (this.anagraficaPagatore!=null &&
              this.anagraficaPagatore.equals(other.getAnagraficaPagatore()))) &&
            ((this.mailPagatore==null && other.getMailPagatore()==null) || 
             (this.mailPagatore!=null &&
              this.mailPagatore.equals(other.getMailPagatore()))) &&
            ((this.pecPagatore==null && other.getPecPagatore()==null) || 
             (this.pecPagatore!=null &&
              this.pecPagatore.equals(other.getPecPagatore()))) &&
            ((this.indirizzoPagatore==null && other.getIndirizzoPagatore()==null) || 
             (this.indirizzoPagatore!=null &&
              this.indirizzoPagatore.equals(other.getIndirizzoPagatore()))) &&
            ((this.datiVersamento==null && other.getDatiVersamento()==null) || 
             (this.datiVersamento!=null &&
              java.util.Arrays.equals(this.datiVersamento, other.getDatiVersamento()))) &&
            ((this.oggettoPagamento==null && other.getOggettoPagamento()==null) || 
             (this.oggettoPagamento!=null &&
              this.oggettoPagamento.equals(other.getOggettoPagamento())));
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
        if (getIdIntermediario() != null) {
            _hashCode += getIdIntermediario().hashCode();
        }
        if (getImportoTotale() != null) {
            _hashCode += getImportoTotale().hashCode();
        }
        if (getDataScadenzaPagamento() != null) {
            _hashCode += getDataScadenzaPagamento().hashCode();
        }
        if (getAppId() != null) {
            _hashCode += getAppId().hashCode();
        }
        if (getCodicePagamento() != null) {
            _hashCode += getCodicePagamento().hashCode();
        }
        if (getDescrizionePagamento() != null) {
            _hashCode += getDescrizionePagamento().hashCode();
        }
        if (getTipoIdentificativoUnivocoPagatore() != null) {
            _hashCode += getTipoIdentificativoUnivocoPagatore().hashCode();
        }
        if (getCodiceIdentificativoUnivocoPagatore() != null) {
            _hashCode += getCodiceIdentificativoUnivocoPagatore().hashCode();
        }
        if (getIdAnagraficaPagatore() != null) {
            _hashCode += getIdAnagraficaPagatore().hashCode();
        }
        if (getAnagraficaPagatore() != null) {
            _hashCode += getAnagraficaPagatore().hashCode();
        }
        if (getMailPagatore() != null) {
            _hashCode += getMailPagatore().hashCode();
        }
        if (getPecPagatore() != null) {
            _hashCode += getPecPagatore().hashCode();
        }
        if (getIndirizzoPagatore() != null) {
            _hashCode += getIndirizzoPagatore().hashCode();
        }
        if (getDatiVersamento() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDatiVersamento());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDatiVersamento(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOggettoPagamento() != null) {
            _hashCode += getOggettoPagamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametriGeneraAvvisoPagamento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "parametriGeneraAvvisoPagamento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idIntermediario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idIntermediario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoTotale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "importoTotale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataScadenzaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataScadenzaPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codicePagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codicePagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizionePagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizionePagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoIdentificativoUnivocoPagatore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoIdentificativoUnivocoPagatore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceIdentificativoUnivocoPagatore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceIdentificativoUnivocoPagatore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAnagraficaPagatore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idAnagraficaPagatore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anagraficaPagatore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anagraficaPagatore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailPagatore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mailPagatore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pecPagatore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pecPagatore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indirizzoPagatore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indirizzoPagatore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datiVersamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datiVersamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://it/csi/smrcomms/agripagopasrv/business/pagopa", "datiSingoloVersamento"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "datiSingoloVersamento"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oggettoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oggettoPagamento"));
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
