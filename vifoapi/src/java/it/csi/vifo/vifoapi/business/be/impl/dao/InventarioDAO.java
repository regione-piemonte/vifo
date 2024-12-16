/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.impl.dao;

import it.csi.vifo.vifoapi.dto.Inventario;

public interface InventarioDAO {
	
	int insert(Inventario inventario);
	int update(Inventario inventario);
	int deleteById(Integer id);
}
