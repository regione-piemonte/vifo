/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.dto;

public class AllegatoDTO
{
  private long id;
  private long idPratica;
  private long idTipoAllegato;
  private String descrizione;
  private Long idPadre;
  private String titolo;
  private String uidIndex;
  private String uploaduuidCosmo;
  private int flgInvioCosmo;
  private String codiceCosmo;
  
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdPratica() {
		return idPratica;
	}
	public void setIdPratica(long idPratica) {
		this.idPratica = idPratica;
	}
	public long getIdTipoAllegato() {
		return idTipoAllegato;
	}
	public void setIdTipoAllegato(long idTipoAllegato) {
		this.idTipoAllegato = idTipoAllegato;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Long getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getUidIndex() {
		return uidIndex;
	}
	public void setUidIndex(String uidIndex) {
		this.uidIndex = uidIndex;
	}
	public String getUploaduuidCosmo() {
		return uploaduuidCosmo;
	}
	public void setUploaduuidCosmo(String uploaduuidCosmo) {
		this.uploaduuidCosmo = uploaduuidCosmo;
	}
	public int getFlgInvioCosmo() {
		return flgInvioCosmo;
	}
	public void setFlgInvioCosmo(int flgInvioCosmo) {
		this.flgInvioCosmo = flgInvioCosmo;
	}
	public String getCodiceCosmo() {
		return codiceCosmo;
	}
	public void setCodiceCosmo(String codiceCosmo) {
		this.codiceCosmo = codiceCosmo;
	}
  
  
}
