/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.InventarioFilter;
import it.csi.vifo.vifoapi.dto.InventarioFrutti;
import it.csi.vifo.vifoapi.dto.InventarioFruttiRiepilogo;
import it.csi.vifo.vifoapi.dto.PartitaFrutti;
import it.csi.vifo.vifoapi.util.PaginatedList;

public interface InventarioFruttiDAO {
	List<InventarioFruttiRiepilogo> getListInventarioFrutti(String sort);
	InventarioFruttiRiepilogo getInventarioFruttiRiepilogoById(int id);
	PaginatedList<InventarioFruttiRiepilogo> getListInventarioFrutti(int page, int limit, String sort, InventarioFilter filter);
	int insert(InventarioFrutti inventarioFrutti);
	int update(InventarioFrutti inventarioFrutti);
	PartitaFrutti getPartitaFruttaById(Integer id);
	int deletePartitaFruttaById(Integer id);
	Integer[] getRangeAnniInventario();
}
