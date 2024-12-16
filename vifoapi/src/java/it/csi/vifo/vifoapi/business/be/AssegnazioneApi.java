/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import it.csi.vifo.vifoapi.dto.Assegnazione;
import it.csi.vifo.vifoapi.dto.Ddt;
import it.csi.vifo.vifoapi.dto.RicercaDdt;

@Path("/private/assegnazione")
@Produces({ "application/json" })
public interface AssegnazioneApi {
	
	@GET
	@Path("/{idDomanda}")
	@Produces({ "application/json" })
	public Response getListAssegnazioniByDomanda(@PathParam("idDomanda") int idDomanda, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/nonRichiesta/{idDomanda}")
	@Produces({ "application/json" })
	public Response getListAssegnazioniNRByDomanda(@PathParam("idDomanda") int idDomanda, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/riepilogo/{idDomanda}")
	@Produces({ "application/json" })
	public Response getRiepilogoAssegnazioniByIdDomanda(@PathParam("idDomanda") int idDomanda, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("save/{idDomanda}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveAssegnazione(Assegnazione[] body, @PathParam("idDomanda") int idDomanda,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("ddt/save/{idDomanda}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveDdt(Ddt[] body, @PathParam("idDomanda") int idDomanda,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);

	
	@GET
	@Path("partite/specie")
	@Produces({ "application/json" })
	public Response getPartiteBySpecieAndVivaio(@QueryParam("idSpecie") int idSpecie,@QueryParam("idVivaio") int idVivaio,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("ritiro/{idDomanda}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response updateAssegnazione(Assegnazione[] body, @PathParam("idDomanda") int idDomanda,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/ddt/available/{idDomanda}")
	@Produces({ "application/json" })
	public Response getAvailableDdtByIdDomanda(@PathParam("idDomanda") int idDomanda, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/ddt/list/{idDomanda}")
	@Produces({ "application/json" })
	public Response getListDdtByIdDomanda(@PathParam("idDomanda") int idDomanda, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/ddt/info/{numeroDdt}/{anno}")
	@Produces({ "application/json" })
	public Response getInfoDdt(@PathParam("numeroDdt") int numeroDdt, @PathParam("anno") int anno,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/ddt/details/{numeroDdt}/{anno}")
	@Produces({ "application/json" })
	public Response getDdtDettailsByNumeroDdtAndAnnoDdt(@PathParam("numeroDdt") int numeroDdt, @PathParam("anno") int anno,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@DELETE
	@Path("/ddt/annulla/{numeroDdt}/{anno}")
	@Produces({ "application/json" })
	public Response annullaDdt(@PathParam("numeroDdt") int numeroDdt, @PathParam("anno") int anno,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("ddt/update/{numeroDdt}/{anno}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response updateDdt(Ddt[] body,@PathParam("numeroDdt") int numeroDdt, @PathParam("anno") int anno,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);

	@POST
	@Path("ddt/search")
	@Produces({ "application/json" })
	public Response getPaginatedSearchDdt(RicercaDdt body,@QueryParam("page") int page, @QueryParam("limit") int limit, 
			@QueryParam("sort") String sort,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
}
