/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.util.Date;

public class PfPg {
	Integer idSoggettoPf;
	Integer idSoggettoPg;
	Date dataInserimento;
	Date dataFineValidita;
	Integer fkConfigUtente;
	
	public Integer getIdSoggettoPf() {
		return idSoggettoPf;
	}
	public Integer getIdSoggettoPg() {
		return idSoggettoPg;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public Date getDataFineValidita() {
		return dataFineValidita;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setIdSoggettoPf(Integer idSoggettoPf) {
		this.idSoggettoPf = idSoggettoPf;
	}
	public void setIdSoggettoPg(Integer idSoggettoPg) {
		this.idSoggettoPg = idSoggettoPg;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	
	
}
