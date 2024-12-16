/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class Specie {
	
	private Integer idSpecie;
	private String codiceSpecie;
	private String nomeComune;
	private String nomeScientifico;
	private boolean flgProdotta;
	private boolean flgSpecieZp;
	private boolean flgSpecie386;
	private String flgTipoControllo;
	private Double densita;
	private String famiglia;
	private String zonaOrigine;
	private String dimensione;
	private String foglia;
	private String fioreFrutto;
	private String ecologia;
	private String impiego;
	private String noteCaratteristiche;
	private boolean flgSchedaPubblicabile;
	private boolean flgSpeciePubblicabile;
	private Date dataInserimento;
	private Date dataAggiornamento;
	private Integer fkConfigUtente;
	private Integer fkGruppo;
	private String gruppoDescr;
	private String attitudine;
	private String tipiFoto;
	public Integer getIdSpecie() {
		return idSpecie;
	}
	public String getCodiceSpecie() {
		return codiceSpecie;
	}
	public String getNomeComune() {
		return nomeComune;
	}
	public String getNomeScientifico() {
		return nomeScientifico;
	}
	public boolean isFlgProdotta() {
		return flgProdotta;
	}
	public boolean isFlgSpecieZp() {
		return flgSpecieZp;
	}
	public boolean isFlgSpecie386() {
		return flgSpecie386;
	}
	public String getFlgTipoControllo() {
		return flgTipoControllo;
	}
	public Double getDensita() {
		return densita;
	}
	public String getFamiglia() {
		return famiglia;
	}
	public String getZonaOrigine() {
		return zonaOrigine;
	}
	public String getDimensione() {
		return dimensione;
	}
	public String getFoglia() {
		return foglia;
	}
	public String getFioreFrutto() {
		return fioreFrutto;
	}
	public String getEcologia() {
		return ecologia;
	}
	public String getImpiego() {
		return impiego;
	}
	public String getNoteCaratteristiche() {
		return noteCaratteristiche;
	}
	public boolean isFlgSchedaPubblicabile() {
		return flgSchedaPubblicabile;
	}
	public boolean isFlgSpeciePubblicabile() {
		return flgSpeciePubblicabile;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public Date getDataAggiornamento() {
		return dataAggiornamento;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public Integer getFkGruppo() {
		return fkGruppo;
	}
	public String getGruppoDescr() {
		return gruppoDescr;
	}
	public String getAttitudine() {
		return attitudine;
	}
	public String getTipiFoto() {
		return tipiFoto;
	}
	public void setIdSpecie(Integer idSpecie) {
		this.idSpecie = idSpecie;
	}
	public void setCodiceSpecie(String codiceSpecie) {
		this.codiceSpecie = codiceSpecie;
	}
	public void setNomeComune(String nomeComune) {
		this.nomeComune = nomeComune;
	}
	public void setNomeScientifico(String nomeScientifico) {
		this.nomeScientifico = nomeScientifico;
	}
	public void setFlgProdotta(boolean flgProdotta) {
		this.flgProdotta = flgProdotta;
	}
	public void setFlgSpecieZp(boolean flgSpecieZp) {
		this.flgSpecieZp = flgSpecieZp;
	}
	public void setFlgSpecie386(boolean flgSpecie386) {
		this.flgSpecie386 = flgSpecie386;
	}
	public void setFlgTipoControllo(String flgTipoControllo) {
		this.flgTipoControllo = flgTipoControllo;
	}
	public void setDensita(Double densita) {
		this.densita = densita;
	}
	public void setFamiglia(String famiglia) {
		this.famiglia = famiglia;
	}
	public void setZonaOrigine(String zonaOrigine) {
		this.zonaOrigine = zonaOrigine;
	}
	public void setDimensione(String dimensione) {
		this.dimensione = dimensione;
	}
	public void setFoglia(String foglia) {
		this.foglia = foglia;
	}
	public void setFioreFrutto(String fioreFrutto) {
		this.fioreFrutto = fioreFrutto;
	}
	public void setEcologia(String ecologia) {
		this.ecologia = ecologia;
	}
	public void setImpiego(String impiego) {
		this.impiego = impiego;
	}
	public void setNoteCaratteristiche(String noteCaratteristiche) {
		this.noteCaratteristiche = noteCaratteristiche;
	}
	public void setFlgSchedaPubblicabile(boolean flgSchedaPubblicabile) {
		this.flgSchedaPubblicabile = flgSchedaPubblicabile;
	}
	public void setFlgSpeciePubblicabile(boolean flgSpeciePubblicabile) {
		this.flgSpeciePubblicabile = flgSpeciePubblicabile;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public void setDataAggiornamento(Date dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	public void setFkGruppo(Integer fkGruppo) {
		this.fkGruppo = fkGruppo;
	}
	public void setGruppoDescr(String gruppoDescr) {
		this.gruppoDescr = gruppoDescr;
	}
	public void setAttitudine(String attitudine) {
		this.attitudine = attitudine;
	}
	public void setTipiFoto(String tipiFoto) {
		this.tipiFoto = tipiFoto;
	}
	
	
	
}
