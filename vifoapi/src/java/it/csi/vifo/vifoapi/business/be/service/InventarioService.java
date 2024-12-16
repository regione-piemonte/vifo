/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service;

import java.util.List;

import it.csi.vifo.vifoapi.business.be.exception.DAOException;
import it.csi.vifo.vifoapi.dto.CertificatoProvPiemonte;
import it.csi.vifo.vifoapi.dto.CertificatoRiepilogo;
import it.csi.vifo.vifoapi.dto.ConfigUtente;
import it.csi.vifo.vifoapi.dto.InfoNuovaAnnataInventario;
import it.csi.vifo.vifoapi.dto.InventarioFilter;
import it.csi.vifo.vifoapi.dto.InventarioFruttiRiepilogo;
import it.csi.vifo.vifoapi.dto.InventarioPiantineRiepilogo;
import it.csi.vifo.vifoapi.dto.InventarioSemiRiepilogo;
import it.csi.vifo.vifoapi.dto.PartitaFrutti;
import it.csi.vifo.vifoapi.dto.PartitaPiante;
import it.csi.vifo.vifoapi.dto.PartitaSemi;
import it.csi.vifo.vifoapi.dto.RichiestaTrasferimentoPiante;
import it.csi.vifo.vifoapi.dto.TrasferimentoPartita;
import it.csi.vifo.vifoapi.util.PaginatedList;
import org.postgresql.util.PSQLException;

public interface InventarioService {

	public CertificatoRiepilogo saveCertificatoPiemonte(CertificatoProvPiemonte certificato, ConfigUtente confUtente) throws DAOException;
	public CertificatoProvPiemonte getCertificatoPiemonteById(int idProvenienza);
	public List<CertificatoRiepilogo>getListCertificatiPiemonte();
	public PaginatedList<CertificatoRiepilogo> getPaginatedCertificati(int page, int limit, String sort, InventarioFilter filter);
	public Double getQtaResiduaByIdProvenienza(int idProvenienza);
	
	public PaginatedList<InventarioFruttiRiepilogo> getListInventarioFrutti(int page, int limit, String sort, InventarioFilter filter);
	public Integer savePartitaFrutta(PartitaFrutti partitaFrutti, ConfigUtente confUtente)throws Exception;
	public void deletePartitaFrutta(Integer idInventario)throws Exception;
	public PartitaFrutti getPartitaFruttaById(Integer id) throws DAOException;
	public InventarioFruttiRiepilogo getInventarioFruttiRiepilogoById(int id);
	
	
	public PaginatedList<InventarioSemiRiepilogo> getListInventarioSemi(int page, int limit, String sort, InventarioFilter filter);
	public Integer savePartitaSemi(PartitaSemi partitaSemi, ConfigUtente confUtente)throws Exception;
	public void deletePartitaSemi(Integer idInventario)throws Exception;
	public PartitaSemi getPartitaSemiById(Integer id) throws PSQLException;
	public InventarioSemiRiepilogo getInventarioSemiRiepilogoById(int id);
	
	public PaginatedList<InventarioPiantineRiepilogo> getListInventarioPiante(int page, int limit, int idVivaio, String sort, InventarioFilter filter);
	public InventarioPiantineRiepilogo getRiepilogoPiantineById(int id);
	public Integer savePartitaPiante(PartitaPiante partitaPiante, ConfigUtente confUtente)throws Exception;
	public void deletePartitaPiante(Integer idInventario)throws Exception;
	public PartitaPiante getPartitaPianteById(Integer id) throws DAOException;
	
	public int saveRichiestaTrasferimentoPiante(RichiestaTrasferimentoPiante richiestaTrasf, ConfigUtente confUtente);
	public PaginatedList<TrasferimentoPartita> getListTrasferimentoPiantine(int page, int limit, String sort, ConfigUtente confUtente);
	public int getCountTrasferimentoPiantine(Integer idVivaio);
	public RichiestaTrasferimentoPiante getRichiestaTrasferimentoById(Integer id);
	public int accettaRifiutaTrasferimento(RichiestaTrasferimentoPiante richiesta);
	public InfoNuovaAnnataInventario getInfoAnnataInventario(int idVivaio);
	public int creaNuovaAnnataInventario(int idVivaio, ConfigUtente confUtente);
}
