/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import java.util.List;

import it.csi.vifo.vifoapi.dto.InventarioFilter;
import it.csi.vifo.vifoapi.dto.InventarioSemi;
import it.csi.vifo.vifoapi.dto.InventarioSemiRiepilogo;
import it.csi.vifo.vifoapi.dto.PartitaSemi;
import it.csi.vifo.vifoapi.util.PaginatedList;

public interface InventarioSemiDAO {

	PaginatedList<InventarioSemiRiepilogo> getListInventarioSemi(int page, int limit,String sort, InventarioFilter filter);
	List<InventarioSemiRiepilogo> getListInventarioSemi(String sort);
	InventarioSemiRiepilogo getInventarioSemiRiepilogoById(int id);
	int insert(InventarioSemi inventarioSemi);
	int update(InventarioSemi inventarioSemi);
	public PartitaSemi getPartitaSemiById(Integer id);
	int deletePartitaSemiById(Integer id);
	Integer[] getRangeAnniInventario();
}
