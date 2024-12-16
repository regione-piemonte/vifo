/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class Provenienza {
	
	Integer idProvenienza;
	Integer fkSpecie;
	Integer fkCategMateriale;
	String provenienza;
	String produttore;
	Date dataInserimento;
	Date dataAggiornamento;
	Integer fkConfigUtente;
	Byte flgNoFiniForestali;
	
	public Integer getIdProvenienza() {
		return idProvenienza;
	}
	public Integer getFkSpecie() {
		return fkSpecie;
	}
	public Integer getFkCategMateriale() {
		return fkCategMateriale;
	}
	public String getProvenienza() {
		return provenienza;
	}
	public String getProduttore() {
		return produttore;
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
	public Byte getFlgNoFiniForestali() {
		return flgNoFiniForestali;
	}
	public void setIdProvenienza(Integer idProvenienza) {
		this.idProvenienza = idProvenienza;
	}
	public void setFkSpecie(Integer fkSpecie) {
		this.fkSpecie = fkSpecie;
	}
	public void setFkCategMateriale(Integer fkCategMateriale) {
		this.fkCategMateriale = fkCategMateriale;
	}
	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}
	public void setProduttore(String produttore) {
		this.produttore = produttore;
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
	public void setFlgNoFiniForestali(Byte flgNoFiniForestali) {
		this.flgNoFiniForestali = flgNoFiniForestali;
	}
	
	
}
