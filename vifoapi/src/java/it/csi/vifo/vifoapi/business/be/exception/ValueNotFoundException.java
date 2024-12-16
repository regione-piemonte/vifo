/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.exception;

/**
 * The type Value not found exception.
 *
 * @author CSI PIEMONTE
 */
public class ValueNotFoundException extends GenericException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Value not found exception.
     *
     * @param msg the msg
     */
    public ValueNotFoundException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new Value not found exception.
     *
     * @param arg0 the arg 0
     */
    public ValueNotFoundException(Throwable arg0) {
        super(arg0);

    }
}