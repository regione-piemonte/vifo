/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.util.service.integration.piemontepay;

import java.io.Serializable;

public class Esito implements Serializable {

	private static final long serialVersionUID = 5632039858243879276L;

	protected String esito;

	public Esito() {

	}

	public Esito(String esito) {
		this.esito = esito;
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}
}
