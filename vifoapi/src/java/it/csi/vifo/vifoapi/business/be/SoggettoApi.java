/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be;

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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.vifo.vifoapi.dto.TSoggetto;

@Path("/private/soggetti")
@Produces({ "application/json" })

public interface SoggettoApi {
	
	@GET
	@Path("/logged/user")
	@Produces({ "application/json" })
	public Response getLoggedUser(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idSoggetto}")
	@Produces({ "application/json" })
	public Response getSoggetto(@PathParam("idSoggetto") Integer idSoggetto, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Produces({ "application/json" })
	public Response getSoggettoByPartialCodiceFiscale(@QueryParam("codiceFiscale") String codiceFiscale, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveSoggetto(TSoggetto body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@PUT
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response updateSoggetto(TSoggetto body,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
	
	@GET
	@Path("/fisica")
	@Produces({ "application/json" })
	public Response getPersonaFisicaByCodiceFiscale(@QueryParam("codiceFiscale") String codiceFiscale);
	
	@GET
	@Path("/myGiuridica")
	@Produces({ "application/json" })
	public Response getPersonaGiurdicaByOwnerCodiceFiscale(@QueryParam("codiceFiscale") String codiceFiscale,
			@QueryParam("isEntePubblico")boolean isEntePubblico,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/giuridica")
	@Produces({ "application/json" })
	public Response getPersonaGiurdicaByCodiceFiscale(@QueryParam("codiceFiscale") String codiceFiscale,
			@QueryParam("isEntePubblico")Boolean isEntePubblico);
	
	@GET
	@Path("/ricerca")
	@Produces({ "application/json" })
	public Response getPaginatedListPersona(@QueryParam("page") int page, @QueryParam("limit") int limit, 
			@QueryParam("sort") String sort, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/config/{idConfig}")
	@Produces({ "application/json" })
	public Response getConfigById(@PathParam("idConfig") Integer idConfig, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


}
