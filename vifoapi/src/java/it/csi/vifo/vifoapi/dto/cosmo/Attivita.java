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
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Attivita {
  private String nome = null;
  private String descrizione = null;
  private Date dataInizio = null;
  private Date dataFine = null;
  private List<Assegnazione> assegnazione = null;
  private List<SottoAttivita> sottoAttivita = null;
  private List<Messaggio> messaggiCollaboratori = null;

  public Attivita nome(String nome) {
    this.nome = nome;
    return this;
  }

   /**
   * Get nome
   * @return nome
  **/
  
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Attivita descrizione(String descrizione) {
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

  public Attivita dataInizio(Date dataInizio) {
    this.dataInizio = dataInizio;
    return this;
  }

   /**
   * Get dataInizio
   * @return dataInizio
  **/
  
  public Date getDataInizio() {
    return dataInizio;
  }

  public void setDataInizio(Date dataInizio) {
    this.dataInizio = dataInizio;
  }

  public Attivita dataFine(Date dataFine) {
    this.dataFine = dataFine;
    return this;
  }

   /**
   * Get dataFine
   * @return dataFine
  **/
  
  public Date getDataFine() {
    return dataFine;
  }

  public void setDataFine(Date dataFine) {
    this.dataFine = dataFine;
  }

  public Attivita assegnazione(List<Assegnazione> assegnazione) {
    this.assegnazione = assegnazione;
    return this;
  }

  public Attivita addAssegnazioneItem(Assegnazione assegnazioneItem) {
    if (this.assegnazione == null) {
      this.assegnazione = new ArrayList<Assegnazione>();
    }
    this.assegnazione.add(assegnazioneItem);
    return this;
  }

   /**
   * Get assegnazione
   * @return assegnazione
  **/
  
  public List<Assegnazione> getAssegnazione() {
    return assegnazione;
  }

  public void setAssegnazione(List<Assegnazione> assegnazione) {
    this.assegnazione = assegnazione;
  }

  public Attivita sottoAttivita(List<SottoAttivita> sottoAttivita) {
    this.sottoAttivita = sottoAttivita;
    return this;
  }

  public Attivita addSottoAttivitaItem(SottoAttivita sottoAttivitaItem) {
    if (this.sottoAttivita == null) {
      this.sottoAttivita = new ArrayList<SottoAttivita>();
    }
    this.sottoAttivita.add(sottoAttivitaItem);
    return this;
  }

   /**
   * l&#x27;oggetto sottotask ha un assegnatario
   * @return sottoAttivita
  **/
  public List<SottoAttivita> getSottoAttivita() {
    return sottoAttivita;
  }

  public void setSottoAttivita(List<SottoAttivita> sottoAttivita) {
    this.sottoAttivita = sottoAttivita;
  }

  public Attivita messaggiCollaboratori(List<Messaggio> messaggiCollaboratori) {
    this.messaggiCollaboratori = messaggiCollaboratori;
    return this;
  }

  public Attivita addMessaggiCollaboratoriItem(Messaggio messaggiCollaboratoriItem) {
    if (this.messaggiCollaboratori == null) {
      this.messaggiCollaboratori = new ArrayList<Messaggio>();
    }
    this.messaggiCollaboratori.add(messaggiCollaboratoriItem);
    return this;
  }

   /**
   * Get messaggiCollaboratori
   * @return messaggiCollaboratori
  **/
  
  public List<Messaggio> getMessaggiCollaboratori() {
    return messaggiCollaboratori;
  }

  public void setMessaggiCollaboratori(List<Messaggio> messaggiCollaboratori) {
    this.messaggiCollaboratori = messaggiCollaboratori;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Attivita attivita = (Attivita) o;
    return Objects.equals(this.nome, attivita.nome) &&
        Objects.equals(this.descrizione, attivita.descrizione) &&
        Objects.equals(this.dataInizio, attivita.dataInizio) &&
        Objects.equals(this.dataFine, attivita.dataFine) &&
        Objects.equals(this.assegnazione, attivita.assegnazione) &&
        Objects.equals(this.sottoAttivita, attivita.sottoAttivita) &&
        Objects.equals(this.messaggiCollaboratori, attivita.messaggiCollaboratori);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome, descrizione, dataInizio, dataFine, assegnazione, sottoAttivita, messaggiCollaboratori);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Attivita {\n");
    
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    dataInizio: ").append(toIndentedString(dataInizio)).append("\n");
    sb.append("    dataFine: ").append(toIndentedString(dataFine)).append("\n");
    sb.append("    assegnazione: ").append(toIndentedString(assegnazione)).append("\n");
    sb.append("    sottoAttivita: ").append(toIndentedString(sottoAttivita)).append("\n");
    sb.append("    messaggiCollaboratori: ").append(toIndentedString(messaggiCollaboratori)).append("\n");
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
