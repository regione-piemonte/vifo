/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.exception;

/**
 * The type Incorrect vifoapi request exception.
 *
 * @author CSI PIEMONTE
 */
public class IncorrectVifobesrvRequestException extends GenericException{
    /**
     * Instantiates a new Incorrect vifoapi request exception.
     *
     * @param msg the msg
     */
    public IncorrectVifobesrvRequestException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new Incorrect vifoapi request exception.
     *
     * @param arg0 the arg 0
     */
    public IncorrectVifobesrvRequestException(Throwable arg0) {
        super(arg0);

    }
}