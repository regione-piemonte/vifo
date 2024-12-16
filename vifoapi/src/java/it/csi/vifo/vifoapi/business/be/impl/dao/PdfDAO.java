/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.PdfAssegnazione;
import it.csi.vifo.vifoapi.dto.PdfBene;
import it.csi.vifo.vifoapi.dto.PdfCertificato;
import it.csi.vifo.vifoapi.dto.PdfDdt;
import it.csi.vifo.vifoapi.dto.PdfDomanda;
import it.csi.vifo.vifoapi.dto.PdfEtichetta;

public interface PdfDAO {
	PdfDdt getDdt(int numeroDdt, int annoDdt);
	List<PdfBene> getBeniDdt(int numeroDdt, int annoDdt);	
	List<PdfBene> getBeniAssegnazioneGratuita(int idDomanda);	
	List<PdfBene> getBeniAssegnazioneOnerosa(int idDomanda);
	PdfAssegnazione getAssegnazione(int idDomanda);
	List<PdfEtichetta>getEtichette(int idDomanda);
	PdfDomanda getDomanda(int idDomanda);
	PdfCertificato getCertificato(int idCertificato);
}
