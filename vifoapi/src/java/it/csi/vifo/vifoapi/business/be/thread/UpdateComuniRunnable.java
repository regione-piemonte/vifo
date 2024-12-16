/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.thread;

import it.csi.vifo.vifoapi.business.be.service.WsService;
public class UpdateComuniRunnable implements Runnable {

	public UpdateComuniRunnable(WsService ws) {
		super();
		this.wsService = ws;
	}

	private WsService wsService;
	
	@Override
	public void run() {
		try {
			wsService.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
