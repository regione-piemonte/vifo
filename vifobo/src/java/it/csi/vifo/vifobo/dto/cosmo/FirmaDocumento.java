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

package it.csi.vifo.vifobo.dto.cosmo;

import java.util.Date;
import java.util.Objects;


public class FirmaDocumento {
  private Date data = null;
  private String firmatario = null;
  private String organizzazione = null;

  public FirmaDocumento data(Date data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  
  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public FirmaDocumento firmatario(String firmatario) {
    this.firmatario = firmatario;
    return this;
  }

   /**
   * Get firmatario
   * @return firmatario
  **/
  
  public String getFirmatario() {
    return firmatario;
  }

  public void setFirmatario(String firmatario) {
    this.firmatario = firmatario;
  }

  public FirmaDocumento organizzazione(String organizzazione) {
    this.organizzazione = organizzazione;
    return this;
  }

   /**
   * Get organizzazione
   * @return organizzazione
  **/
  
  public String getOrganizzazione() {
    return organizzazione;
  }

  public void setOrganizzazione(String organizzazione) {
    this.organizzazione = organizzazione;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirmaDocumento firmaDocumento = (FirmaDocumento) o;
    return Objects.equals(this.data, firmaDocumento.data) &&
        Objects.equals(this.firmatario, firmaDocumento.firmatario) &&
        Objects.equals(this.organizzazione, firmaDocumento.organizzazione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, firmatario, organizzazione);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmaDocumento {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    firmatario: ").append(toIndentedString(firmatario)).append("\n");
    sb.append("    organizzazione: ").append(toIndentedString(organizzazione)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}