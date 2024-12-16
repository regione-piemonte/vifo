/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class Inventario {

	Integer idInventario;
	Integer fkProventienza;
	Date dataCarico;
	Date dataInserimento;
	Date dataAggiornamento;
	Integer fkConfigUtente;
	
	public Integer getIdInventario() {
		return idInventario;
	}
	public Integer getFkProventienza() {
		return fkProventienza;
	}
	public Date getDataCarico() {
		return dataCarico;
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
	public void setIdInventario(Integer idInventario) {
		this.idInventario = idInventario;
	}
	public void setFkProventienza(Integer fkProventienza) {
		this.fkProventienza = fkProventienza;
	}
	public void setDataCarico(Date dataCarico) {
		this.dataCarico = dataCarico;
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
	
	
}
