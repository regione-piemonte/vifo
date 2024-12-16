/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
/*
 * StatoPratica
 * Aggiornamento dello stato pratica per IDF
 *
 * OpenAPI spec version: 1.0
 * Contact: info@cosmo.csi.it
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package it.csi.vifo.vifoapi.dto.cosmo;

import java.util.ArrayList;
import java.util.List;


public class Documento {
  private String id = null;
  private String idPadre = null;
  private String titolo = null;
  private String descrizione = null;
  private String autore = null;
  private String mimeType = null;
  private List<FirmaDocumento> firme = null;
  private ArchiviazioneDocumento archiviazione = null;
  private String refURL = null;
  private TipoDocumento tipo = null;
  private StatoDocumento stato = null;
  private String nomeFile = null;
  private Long dimensione = null;
  private String idCosmo = null;

  public Documento id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Documento idPadre(String idPadre) {
    this.idPadre = idPadre;
    return this;
  }

   /**
   * Get idPadre
   * @return idPadre
  **/
  public String getIdPadre() {
    return idPadre;
  }

  public void setIdPadre(String idPadre) {
    this.idPadre = idPadre;
  }

  public Documento titolo(String titolo) {
    this.titolo = titolo;
    return this;
  }

   /**
   * Get titolo
   * @return titolo
  **/
  public String getTitolo() {
    return titolo;
  }

  public void setTitolo(String titolo) {
    this.titolo = titolo;
  }

  public Documento descrizione(String descrizione) {
    this.descrizione = descrizione;
    return this;
  }

   /**
   * Get descrizione
   * @return descrizione
  **/
  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public Documento autore(String autore) {
    this.autore = autore;
    return this;
  }

   /**
   * Get autore
   * @return autore
  **/
  public String getAutore() {
    return autore;
  }

  public void setAutore(String autore) {
    this.autore = autore;
  }

  public Documento mimeType(String mimeType) {
    this.mimeType = mimeType;
    return this;
  }

   /**
   * Get mimeType
   * @return mimeType
  **/
  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public Documento firme(List<FirmaDocumento> firme) {
    this.firme = firme;
    return this;
  }

  public Documento addFirmeItem(FirmaDocumento firmeItem) {
    if (this.firme == null) {
      this.firme = new ArrayList<FirmaDocumento>();
    }
    this.firme.add(firmeItem);
    return this;
  }

   /**
   * Get firme
   * @return firme
  **/
  public List<FirmaDocumento> getFirme() {
    return firme;
  }

  public void setFirme(List<FirmaDocumento> firme) {
    this.firme = firme;
  }

  public Documento archiviazione(ArchiviazioneDocumento archiviazione) {
    this.archiviazione = archiviazione;
    return this;
  }

   /**
   * Get archiviazione
   * @return archiviazione
  **/
  public ArchiviazioneDocumento getArchiviazione() {
    return archiviazione;
  }

  public void setArchiviazione(ArchiviazioneDocumento archiviazione) {
    this.archiviazione = archiviazione;
  }

  public Documento refURL(String refURL) {
    this.refURL = refURL;
    return this;
  }

   /**
   * Get refURL
   * @return refURL
  **/
  public String getRefURL() {
    return refURL;
  }

  public void setRefURL(String refURL) {
    this.refURL = refURL;
  }

  public Documento tipo(TipoDocumento tipo) {
    this.tipo = tipo;
    return this;
  }

   /**
   * Get tipo
   * @return tipo
  **/
  public TipoDocumento getTipo() {
    return tipo;
  }

  public void setTipo(TipoDocumento tipo) {
    this.tipo = tipo;
  }

  public Documento stato(StatoDocumento stato) {
    this.stato = stato;
    return this;
  }

   /**
   * Get stato
   * @return stato
  **/
  public StatoDocumento getStato() {
    return stato;
  }

  public void setStato(StatoDocumento stato) {
    this.stato = stato;
  }

  public String getNomeFile()
  {
    return nomeFile;
  }

  public void setNomeFile(String nomeFile)
  {
    this.nomeFile = nomeFile;
  }

  public Long getDimensione()
  {
    return dimensione;
  }

  public void setDimensione(Long dimensione)
  {
    this.dimensione = dimensione;
  }

  public String getIdCosmo()
  {
    return idCosmo;
  }

  public void setIdCosmo(String idCosmo)
  {
    this.idCosmo = idCosmo;
  }


  

}