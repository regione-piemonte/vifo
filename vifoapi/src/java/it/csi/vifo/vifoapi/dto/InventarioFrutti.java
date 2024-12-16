/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class InventarioFrutti {

	Integer idInventarioFrutti;
	Double qtaCaricoKg;
	Double qtaScaricoVenditaKg;
	Double qtaDistruzioneKg;
	
	public Integer getIdInventarioFrutti() {
		return idInventarioFrutti;
	}
	public Double getQtaCaricoKg() {
		return qtaCaricoKg;
	}
	public Double getQtaScaricoVenditaKg() {
		return qtaScaricoVenditaKg;
	}
	public Double getQtaDistruzioneKg() {
		return qtaDistruzioneKg;
	}
	public void setIdInventarioFrutti(Integer idInventarioFrutti) {
		this.idInventarioFrutti = idInventarioFrutti;
	}
	public void setQtaCaricoKg(Double qtaCaricoKg) {
		this.qtaCaricoKg = qtaCaricoKg;
	}
	public void setQtaScaricoVenditaKg(Double qtaScaricoVenditaKg) {
		this.qtaScaricoVenditaKg = qtaScaricoVenditaKg;
	}
	public void setQtaDistruzioneKg(Double qtaDistruzioneKg) {
		this.qtaDistruzioneKg = qtaDistruzioneKg;
	}
	
	
}
