/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

public class InventarioSemi {
	private Integer idInventarioSemi;
	private Integer fkInventarioFrutti;
	private Double qtaScaricoFruttiKg;
	private Double qtaCaricoKg;
	private Double qtaScaricoVenditaKg;
	private Double qtaDistruzioneKg;
	
	public Integer getIdInventarioSemi() {
		return idInventarioSemi;
	}
	public Integer getFkInventarioFrutti() {
		return fkInventarioFrutti;
	}
	public Double getQtaScaricoFruttiKg() {
		return qtaScaricoFruttiKg;
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
	public void setIdInventarioSemi(Integer idInventarioSemi) {
		this.idInventarioSemi = idInventarioSemi;
	}
	public void setFkInventarioFrutti(Integer fkInventarioFrutti) {
		this.fkInventarioFrutti = fkInventarioFrutti;
	}
	public void setQtaScaricoFruttiKg(Double qtaScaricoFruttiKg) {
		this.qtaScaricoFruttiKg = qtaScaricoFruttiKg;
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
