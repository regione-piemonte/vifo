/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be.service.helper;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.vifo.vifobo.util.Constants;

@Component
public abstract class AbstractServiceHelper {
	
	public static final Logger LOGGER = Logger.getLogger(Constants.COMPONENT_NAME + ".service");
	
}
