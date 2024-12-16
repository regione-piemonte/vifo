/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetadatiCosmo {
	private String id;
	private String Riassunto;
	private String Intestatario;
	private String titolo;
	private String anno_serie_fascicoli;
	private String parola_chiave_fascicolo;
	private String id_domanda;
	private String denominazione_richiedente;
	private String cognome;
	private String nome;
	private String data_invio;
	private String PG_partita_iva_richiedente;
	private String PG_codice_fiscale_richiedente;
	private String PG_indirizzo_pec_richiedente;
	private String PF_codice_fiscale_richiedente;
	private String PF_indirizzo_mail_richiedente;
	private String codice_IPA;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty("Riassunto")
	public String getRiassunto() {
		return Riassunto;
	}
	public void setRiassunto(String riassunto) {
		Riassunto = riassunto;
	}
	
	@JsonProperty("Intestatario")
	public String getIntestatario() {
		return Intestatario;
	}
	public void setIntestatario(String intestatario) {
		Intestatario = intestatario;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAnno_serie_fascicoli() {
		return anno_serie_fascicoli;
	}
	public void setAnno_serie_fascicoli(String anno_serie_fascicoli) {
		this.anno_serie_fascicoli = anno_serie_fascicoli;
	}
	public String getParola_chiave_fascicolo() {
		return parola_chiave_fascicolo;
	}
	public void setParola_chiave_fascicolo(String parola_chiave_fascicolo) {
		this.parola_chiave_fascicolo = parola_chiave_fascicolo;
	}
	public String getId_domanda() {
		return id_domanda;
	}
	public void setId_domanda(String id_domanda) {
		this.id_domanda = id_domanda;
	}
	public String getDenominazione_richiedente() {
		return denominazione_richiedente;
	}
	public void setDenominazione_richiedente(String denominazione_richiedente) {
		this.denominazione_richiedente = denominazione_richiedente;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData_invio() {
		return data_invio;
	}
	public void setData_invio(String data_invio) {
		this.data_invio = data_invio;
	}
	
	@JsonProperty("PG_partita_iva_richiedente")
	public String getPG_partita_iva_richiedente() {
		return PG_partita_iva_richiedente;
	}
	public void setPG_partita_iva_richiedente(String pG_partita_iva_richiedente) {
		PG_partita_iva_richiedente = pG_partita_iva_richiedente;
	}
	
	@JsonProperty("PG_codice_fiscale_richiedente")
	public String getPG_codice_fiscale_richiedente() {
		return PG_codice_fiscale_richiedente;
	}
	public void setPG_codice_fiscale_richiedente(String pG_codice_fiscale_richiedente) {
		PG_codice_fiscale_richiedente = pG_codice_fiscale_richiedente;
	}
	
	@JsonProperty("PG_indirizzo_pec_richiedente")
	public String getPG_indirizzo_pec_richiedente() {
		return PG_indirizzo_pec_richiedente;
	}
	public void setPG_indirizzo_pec_richiedente(String pG_indirizzo_pec_richiedente) {
		PG_indirizzo_pec_richiedente = pG_indirizzo_pec_richiedente;
	}
	
	@JsonProperty("PF_codice_fiscale_richiedente")
	public String getPF_codice_fiscale_richiedente() {
		return PF_codice_fiscale_richiedente;
	}
	public void setPF_codice_fiscale_richiedente(String pF_codice_fiscale_richiedente) {
		PF_codice_fiscale_richiedente = pF_codice_fiscale_richiedente;
	}
	
	@JsonProperty("PF_indirizzo_mail_richiedente")
	public String getPF_indirizzo_mail_richiedente() {
		return PF_indirizzo_mail_richiedente;
	}
	public void setPF_indirizzo_mail_richiedente(String pF_indirizzo_mail_richiedente) {
		PF_indirizzo_mail_richiedente = pF_indirizzo_mail_richiedente;
	}
	public String getCodice_IPA() {
		return codice_IPA;
	}
	public void setCodice_IPA(String codice_IPA) {
		this.codice_IPA = codice_IPA;
	}
	
	

}
