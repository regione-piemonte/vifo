/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import java.sql.Timestamp;
import java.util.Objects;


public class ConfigUtente {

	private Integer idConfigUtente;
	private Integer fkTipoProfilo;
	private Integer nrAccessi;
	private Timestamp dataPrimoAccesso;
	private Timestamp dataUltimoAccesso;
	private Byte flgTrattamentoDatiPersonali;
	private Integer fkSoggetto;
	private Integer fkProcedura;
	private Integer fkVivaio;

	public Integer getIdConfigUtente() {
		return idConfigUtente;
	}

	public void setIdConfigUtente(Integer idConfigUtente) {
		this.idConfigUtente = idConfigUtente;
	}

	public Integer getNrAccessi() {
		return nrAccessi;
	}

	public Integer getFkTipoProfilo() {
		return fkTipoProfilo;
	}

	public void setFkTipoProfilo(Integer fkTipoProfilo) {
		this.fkTipoProfilo = fkTipoProfilo;
	}

	public void setNrAccessi(Integer nrAccessi) {
		this.nrAccessi = nrAccessi;
	}

	public Timestamp getDataPrimoAccesso() {
		return dataPrimoAccesso;
	}

	public void setDataPrimoAccesso(Timestamp dataPrimoAccesso) {
		this.dataPrimoAccesso = dataPrimoAccesso;
	}

	public Timestamp getDataUltimoAccesso() {
		return dataUltimoAccesso;
	}

	public void setDataUltimoAccesso(Timestamp dataUltimoAccesso) {
		this.dataUltimoAccesso = dataUltimoAccesso;
	}

	public Byte getFlgTrattamentoDatiPersonali() {
		return flgTrattamentoDatiPersonali;
	}

	public void setFlgTrattamentoDatiPersonali(Byte flgTrattamentoDatiPersonali) {
		this.flgTrattamentoDatiPersonali = flgTrattamentoDatiPersonali;
	}

	public Integer getFkSoggetto() {
		return fkSoggetto;
	}

	public void setFkSoggetto(Integer fkSoggetto) {
		this.fkSoggetto = fkSoggetto;
	}

	public Integer getFkProcedura() {
		return fkProcedura;
	}

	public void setFkProcedura(Integer fkProcedura) {
		this.fkProcedura = fkProcedura;
	}

	public Integer getFkVivaio() {
		return fkVivaio;
	}

	public void setFkVivaio(Integer fkVivaio) {
		this.fkVivaio = fkVivaio;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ConfigUtente configUtente = (ConfigUtente) o;
		return Objects.equals(idConfigUtente, configUtente.idConfigUtente)
				&& Objects.equals(fkTipoProfilo, configUtente.fkTipoProfilo)
				&& Objects.equals(nrAccessi, configUtente.nrAccessi)
				&& Objects.equals(dataPrimoAccesso, configUtente.dataPrimoAccesso)
				&& Objects.equals(dataUltimoAccesso, configUtente.dataUltimoAccesso)
				&& Objects.equals(flgTrattamentoDatiPersonali, configUtente.flgTrattamentoDatiPersonali)
				&& Objects.equals(fkVivaio, configUtente.fkVivaio)
				&& Objects.equals(fkSoggetto, configUtente.fkSoggetto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idConfigUtente, fkTipoProfilo, fkVivaio, nrAccessi, dataPrimoAccesso,
				dataUltimoAccesso, flgTrattamentoDatiPersonali, fkSoggetto);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ConfigUtente {\n");

		sb.append("    idConfigUtente: ").append(idConfigUtente).append("\n");
		sb.append("    fkProfiloUtente: ").append(fkTipoProfilo).append("\n");
		sb.append("    nrAccessi: ").append(nrAccessi).append("\n");
		sb.append("    dataPrimoAccesso: ").append(dataPrimoAccesso).append("\n");
		sb.append("    dataUltimoAccesso: ").append(dataUltimoAccesso).append("\n");
		sb.append("    flgTrattamentoDatiPersonali: ").append(flgTrattamentoDatiPersonali).append("\n");
		sb.append("    fkSoggetto: ").append(fkSoggetto).append("\n");
		sb.append("    fkVivaio: ").append(fkVivaio).append("\n");
		sb.append("    fkProcedura: ").append(fkProcedura).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
