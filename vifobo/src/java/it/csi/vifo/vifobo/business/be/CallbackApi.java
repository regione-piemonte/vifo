/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import it.csi.vifo.vifobo.dto.cosmo.AggiornaStatoPraticaRequest;
import it.csi.vifo.vifobo.util.service.integration.piemontepay.PayloadRicevuta;

@Path("/callback")
@Produces({ "application/json" })
public interface CallbackApi {
	 
	@POST
	@Path("/pagamenti/riceviRT")
	@Produces({ "application/json" })
	public Response postRiceviRT(@Context HttpServletRequest request, PayloadRicevuta payload)
	     throws Exception; 
	
	@GET
	@Path("/cosmo")
	@Produces({ "application/json" })
	public Response cosmo(@QueryParam("idPratica") String idPratica, 
			@Context HttpServletRequest request) throws Exception; 
	
	@GET
	@Path("/test")
	@Produces({ "application/json" })
	public Response test(@Context HttpServletRequest request)
	     throws Exception; 
	
	@PUT
	@Path("/cosmo/pratica/stato-pratica/{idPratica}")
	@Consumes("application/json")
	public Response updateStatoPraticaCosmo(@PathParam("idPratica") String idPratica,
			@RequestBody AggiornaStatoPraticaRequest payload,
			@Context HttpServletRequest request) throws Exception;


}
