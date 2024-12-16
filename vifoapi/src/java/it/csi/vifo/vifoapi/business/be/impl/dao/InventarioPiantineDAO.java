/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.InfoNuovaAnnataInventario;
import it.csi.vifo.vifoapi.dto.InventarioFilter;
import it.csi.vifo.vifoapi.dto.InventarioPiantine;
import it.csi.vifo.vifoapi.dto.InventarioPiantineRiepilogo;
import it.csi.vifo.vifoapi.dto.PartitaPiante;
import it.csi.vifo.vifoapi.dto.RichiestaTrasferimentoPiante;
import it.csi.vifo.vifoapi.dto.TrasferimentoPartita;
import it.csi.vifo.vifoapi.dto.XlsCaricoScarico;
import it.csi.vifo.vifoapi.util.PaginatedList;

public interface InventarioPiantineDAO {
	PaginatedList<InventarioPiantineRiepilogo> getListInventarioPiantine(int page, int limit, String sort, InventarioFilter filter);
	List<InventarioPiantineRiepilogo> getListInventarioPiantine(String sort, int idVivaio);
	InventarioPiantineRiepilogo getRiepilogoPiantineById(int id);
	int insert(InventarioPiantine inventarioPiantine);
	int update(InventarioPiantine inventarioPiantine);

	int updateQuantitaNonAssegnabile(InventarioPiantine inventarioPiantine);
	PartitaPiante getPartitaPiantineById(Integer id);
	List<PartitaPiante> getListPartitaPiantineDisponibili(Integer idVivaio);
	int deletePartitaPianteById(Integer id);
	PaginatedList<TrasferimentoPartita> getListTrasferimentoPiantine(int page, int limit, String sort, Integer idVivaio);
	int getCountTrasferimentoPiantine(Integer idVivaio);
	RichiestaTrasferimentoPiante getRichiestaTrasferimentoById(Integer id);
	int accettaRifiutaTrasferimento(RichiestaTrasferimentoPiante richiesta);
	InfoNuovaAnnataInventario getInfoAnnataInventario(Integer idVivaio);
	List<XlsCaricoScarico> getListCaricoScarico(Integer idVivaio);
	Integer[] getRangeAnniInventario();
	int resetAccettazioneTrasferimento(int idPartia);
}
