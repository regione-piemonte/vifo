/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.csi.vifo.vifoapi.util.service.integration.svista.Comune;

public class PlainUtenteSoggetto {
	
	private Integer idSoggetto;
	private Integer idConfigUtente;
	private String codiceFiscale;
	private String cognome;
	private String nome;
	private String ruolo;
	private Comune comune;
	private Integer profile;
	private String provider;
	private String nrTelefonico;
	private String email;
	private Integer fkVivaio;
	private Byte flgPrivacy;

	public Integer getIdSoggetto() {
		return idSoggetto;
	}
	public void setIdSoggetto(Integer idSoggetto) {
		this.idSoggetto = idSoggetto;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public Integer getProfile() {
		return profile;
	}
	public void setProfile(Integer profile) {
		this.profile = profile;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getNrTelefonico() {
		return nrTelefonico;
	}
	public void setNrTelefonico(String nrTelefonico) {
		this.nrTelefonico = nrTelefonico;
	}
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Byte getFlgPrivacy() {
		return flgPrivacy;
	}
	public void setFlgPrivacy(Byte flgPrivacy) {
		this.flgPrivacy = flgPrivacy;
	}
	public Integer getIdConfigUtente() {
		return idConfigUtente;
	}
	public void setIdConfigUtente(Integer idConfigUtente) {
		this.idConfigUtente = idConfigUtente;
	}	
	public Integer getFkVivaio() {
		return fkVivaio;
	}
	public void setFkVivaio(Integer fkVivaio) {
		this.fkVivaio = fkVivaio;
	}
	public Comune getComune() {
		return comune;
	}
	public void setComune(Comune comune) {
		this.comune = comune;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceFiscale == null) ? 0 : codiceFiscale.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((comune == null) ? 0 : comune.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fkVivaio == null) ? 0 : fkVivaio.hashCode());
		result = prime * result + ((flgPrivacy == null) ? 0 : flgPrivacy.hashCode());
		result = prime * result + ((idConfigUtente == null) ? 0 : idConfigUtente.hashCode());
		result = prime * result + ((idSoggetto == null) ? 0 : idSoggetto.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nrTelefonico == null) ? 0 : nrTelefonico.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlainUtenteSoggetto other = (PlainUtenteSoggetto) obj;
		if (codiceFiscale == null) {
			if (other.codiceFiscale != null)
				return false;
		} else if (!codiceFiscale.equals(other.codiceFiscale))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (comune == null) {
			if (other.comune != null)
				return false;
		} else if (!comune.equals(other.comune))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fkVivaio == null) {
			if (other.fkVivaio != null)
				return false;
		} else if (!fkVivaio.equals(other.fkVivaio))
			return false;
		if (flgPrivacy == null) {
			if (other.flgPrivacy != null)
				return false;
		} else if (!flgPrivacy.equals(other.flgPrivacy))
			return false;
		if (idConfigUtente == null) {
			if (other.idConfigUtente != null)
				return false;
		} else if (!idConfigUtente.equals(other.idConfigUtente))
			return false;
		if (idSoggetto == null) {
			if (other.idSoggetto != null)
				return false;
		} else if (!idSoggetto.equals(other.idSoggetto))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nrTelefonico == null) {
			if (other.nrTelefonico != null)
				return false;
		} else if (!nrTelefonico.equals(other.nrTelefonico))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		if (ruolo == null) {
			if (other.ruolo != null)
				return false;
		} else if (!ruolo.equals(other.ruolo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PlainUtenteSoggetto [idSoggetto=" + idSoggetto + ", idConfigUtente=" + idConfigUtente
				+ ", codiceFiscale=" + codiceFiscale + ", cognome=" + cognome + ", nome=" + nome + ", ruolo=" + ruolo
				+ ", comune=" + comune + ", profile=" + profile + ", provider=" + provider + ", nrTelefonico="
				+ nrTelefonico + ", email=" + email + ", fkVivaio=" + fkVivaio + ", flgPrivacy=" + flgPrivacy + "]";
	}
	
	
}
