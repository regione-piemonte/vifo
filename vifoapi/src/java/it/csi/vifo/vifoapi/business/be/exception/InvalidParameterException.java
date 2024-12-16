/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.exception;

/**
 * The type Invalid parameter exception.
 *
 * @author CSI PIEMONTE
 */
public class InvalidParameterException extends GenericException{


    /**
     * Instantiates a new Invalid parameter exception.
     *
     * @param msg the msg
     */
    public InvalidParameterException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new Invalid parameter exception.
     *
     * @param arg0 the arg 0
     */
    public InvalidParameterException(Throwable arg0) {
        super(arg0);

    }

}