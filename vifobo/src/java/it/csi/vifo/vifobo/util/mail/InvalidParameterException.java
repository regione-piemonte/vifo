/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.util.mail;

import it.csi.vifo.vifobo.business.be.exception.GenericException;

/**
 * The type Invalid parameter exception.
 *
 * @author CSI PIEMONTE
 */
public class InvalidParameterException extends GenericException {

    /**
     * Instantiates a new Invalid parameter exception.
     *
     * @param msg msg
     */
    public InvalidParameterException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new Invalid parameter exception.
     *
     * @param arg0 arg0
     */
    public InvalidParameterException(Throwable arg0) {
        super(arg0);

    }

}