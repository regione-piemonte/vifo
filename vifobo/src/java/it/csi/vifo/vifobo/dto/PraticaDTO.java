/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.dto;


public class PraticaDTO
{
  private String       idPratica;
  private String       oggetto;
  private String       codiceIpaEnte;
  private String       codiceTipologia;
  private String       riassunto;
  private String       metadati;
  
  public String getIdPratica()
  {
    return idPratica;
  }
  public void setIdPratica(String idPratica)
  {
    this.idPratica = idPratica;
  }
  public String getOggetto()
  {
    return oggetto;
  }
  public void setOggetto(String oggetto)
  {
    this.oggetto = oggetto;
  }
  public String getCodiceIpaEnte()
  {
    return codiceIpaEnte;
  }
  public void setCodiceIpaEnte(String codiceIpaEnte)
  {
    this.codiceIpaEnte = codiceIpaEnte;
  }
  public String getCodiceTipologia()
  {
    return codiceTipologia;
  }
  public void setCodiceTipologia(String codiceTipologia)
  {
    this.codiceTipologia = codiceTipologia;
  }
  public String getRiassunto()
  {
    return riassunto;
  }
  public void setRiassunto(String riassunto)
  {
    this.riassunto = riassunto;
  }
  public String getMetadati()
  {
    return metadati;
  }
  public void setMetadati(String metadati)
  {
    this.metadati = metadati;
  }
  
}
