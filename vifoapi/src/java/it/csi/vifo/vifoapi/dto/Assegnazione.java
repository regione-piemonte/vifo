/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class Assegnazione {
	private Integer idAssegnata;
	private Integer idSpecie;
	private String nomeSpecie;
	private Integer idPartita;
	private Byte flgSpecie386;
	private Integer qtaRichiesta;
	private Integer fkVivaio;
	private String nomeVivaio;
	private String certificato;
	private Byte flgNoFiniForestali;
	private String descrTipoAllevamento;
	private String eta;
	private String altezza;
	private Double prezzoUnitario;
	private Integer qtaAssegnata;
	private Integer qtaDisponibile;
	private Integer qtaRitirata;
	private Integer tipoDomanda;
	private Double importo;
	
	
	
	public Integer getIdAssegnata() {
		return idAssegnata;
	}
	public void setIdAssegnata(Integer idAssegnata) {
		this.idAssegnata = idAssegnata;
	}
	public Integer getIdSpecie() {
		return idSpecie;
	}
	public String getNomeSpecie() {
		return nomeSpecie;
	}
	public Integer getIdPartita() {
		return idPartita;
	}
	public Byte getFlgSpecie386() {
		return flgSpecie386;
	}
	public Integer getQtaRichiesta() {
		return qtaRichiesta;
	}
	public Integer getFkVivaio() {
		return fkVivaio;
	}
	public String getNomeVivaio() {
		return nomeVivaio;
	}
	public String getCertificato() {
		return certificato;
	}
	public Byte getFlgNoFiniForestali() {
		return flgNoFiniForestali;
	}
	public String getDescrTipoAllevamento() {
		return descrTipoAllevamento;
	}
	public String getEta() {
		return eta;
	}
	public String getAltezza() {
		return altezza;
	}
	public Double getPrezzoUnitario() {
		return prezzoUnitario;
	}
	public Integer getQtaAssegnata() {
		return qtaAssegnata;
	}
	public void setIdSpecie(Integer idSpecie) {
		this.idSpecie = idSpecie;
	}
	public void setNomeSpecie(String nomeSpecie) {
		this.nomeSpecie = nomeSpecie;
	}
	public void setIdPartita(Integer idPartita) {
		this.idPartita = idPartita;
	}
	public void setFlgSpecie386(Byte flgSpecie386) {
		this.flgSpecie386 = flgSpecie386;
	}
	public void setQtaRichiesta(Integer qtaRichiesta) {
		this.qtaRichiesta = qtaRichiesta;
	}
	public void setFkVivaio(Integer fkVivaio) {
		this.fkVivaio = fkVivaio;
	}
	public void setNomeVivaio(String nomeVivaio) {
		this.nomeVivaio = nomeVivaio;
	}
	public void setCertificato(String certificato) {
		this.certificato = certificato;
	}
	public void setFlgNoFiniForestali(Byte flgNoFiniForestali) {
		this.flgNoFiniForestali = flgNoFiniForestali;
	}
	public void setDescrTipoAllevamento(String descrTipoAllevamento) {
		this.descrTipoAllevamento = descrTipoAllevamento;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public void setAltezza(String altezza) {
		this.altezza = altezza;
	}
	public void setPrezzoUnitario(Double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	public void setQtaAssegnata(Integer qtaAssegnata) {
		this.qtaAssegnata = qtaAssegnata;
	}
	public Integer getQtaDisponibile() {
		return qtaDisponibile;
	}
	public void setQtaDisponibile(Integer qtaDisponibile) {
		this.qtaDisponibile = qtaDisponibile;
	}
	public Integer getQtaRitirata() {
		return qtaRitirata;
	}
	public void setQtaRitirata(Integer qtaRitirata) {
		this.qtaRitirata = qtaRitirata;
	}
	public Integer getTipoDomanda() {
		return tipoDomanda;
	}
	public void setTipoDomanda(Integer tipoDomanda) {
		this.tipoDomanda = tipoDomanda;
	}
	public Double getImporto() {
		return importo;
	}
	public void setImporto(Double importo) {
		this.importo = importo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPartita == null) ? 0 : idPartita.hashCode());
		result = prime * result + ((qtaAssegnata == null) ? 0 : qtaAssegnata.hashCode());
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
		Assegnazione other = (Assegnazione) obj;
		if (idPartita == null) {
			if (other.idPartita != null)
				return false;
		} else if (!idPartita.equals(other.idPartita))
			return false;
		if (qtaAssegnata == null) {
			if (other.qtaAssegnata != null)
				return false;
		} else if (!qtaAssegnata.equals(other.qtaAssegnata))
			return false;
		return true;
	}
	
	
	
	
	
}
