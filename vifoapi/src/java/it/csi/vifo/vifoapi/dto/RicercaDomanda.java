/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class RicercaDomanda {
	private Integer idDomanda;
	private Integer idSoggetto;
	private Integer idVivaio;
	private Integer tipo;
	private Integer scopo;
    private String dataInvioDa;
    private String dataInvioA;
    private Integer stato;
    private String cognome;
    private String denominazione;
    private String comune;
    private Specie[] specieRichieste;
    private boolean domandaVivaio;
    private boolean escludiDomandeChiuse;
    
	public Integer getIdDomanda() {
		return idDomanda;
	}
	public Integer getIdSoggetto() {
		return idSoggetto;
	}
	public Integer getIdVivaio() {
		return idVivaio;
	}
	public Integer getTipo() {
		return tipo;
	}
	public Integer getScopo() {
		return scopo;
	}
	public String getDataInvioDa() {
		return dataInvioDa;
	}
	public String getDataInvioA() {
		return dataInvioA;
	}
	public Integer getStato() {
		return stato;
	}
	public String getCognome() {
		return cognome;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public String getComune() {
		return comune;
	}
	public Specie[] getSpecieRichieste() {
		return specieRichieste;
	}
	public void setIdDomanda(Integer idDomanda) {
		this.idDomanda = idDomanda;
	}
	public void setIdSoggetto(Integer idSoggetto) {
		this.idSoggetto = idSoggetto;
	}
	public void setIdVivaio(Integer idVivaio) {
		this.idVivaio = idVivaio;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public void setScopo(Integer scopo) {
		this.scopo = scopo;
	}
	public void setDataInvioDa(String dataInvioDa) {
		this.dataInvioDa = dataInvioDa;
	}
	public void setDataInvioA(String dataInvioA) {
		this.dataInvioA = dataInvioA;
	}
	public void setStato(Integer stato) {
		this.stato = stato;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public void setSpecieRichieste(Specie[] specieRichieste) {
		this.specieRichieste = specieRichieste;
	}
	public boolean getDomandaVivaio() {
		return domandaVivaio;
	}
	public void setDomandaVivaio(boolean domandaVivaio) {
		this.domandaVivaio = domandaVivaio;
	}
	public boolean isEscludiDomandeChiuse() {
		return escludiDomandeChiuse;
	}
	public void setEscludiDomandeChiuse(boolean escludiDomandeChiuse) {
		this.escludiDomandeChiuse = escludiDomandeChiuse;
	}

    

}
