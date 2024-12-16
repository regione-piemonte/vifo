/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * The type Custom read exception mapper.
 *
 * @author CSI PIEMONTE
 */
@Provider
public class CustomReadExceptionMapper implements ExceptionMapper<NotFoundException>{

    /**
     * @param arg0
     * @return Response
     */
    public Response toResponse(NotFoundException arg0) {
        Response r = Response.status(400,"Errore inserimento dati: "+arg0.getCause()).build();
        return r;
    }

}