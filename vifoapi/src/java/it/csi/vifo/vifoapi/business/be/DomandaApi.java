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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.web.bind.annotation.RequestBody;

import it.csi.vifo.vifoapi.dto.Domanda;
import it.csi.vifo.vifoapi.dto.Pagamento;
import it.csi.vifo.vifoapi.dto.RicercaDomanda;

@Path("/private/domanda")
@Produces({ "application/json" })
public interface DomandaApi {
	
	@GET
	@Path("/mine")
	@Produces({ "application/json" })
	public Response getMyDomande(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/pagined/mine")
	@Produces({ "application/json" })
	public Response getPaginatedMyDomande(@QueryParam("page") int page, @QueryParam("limit") int limit, 
			 @QueryParam("sort") String sort, @Context SecurityContext securityContext, 
			 @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idDomanda}")
	@Produces({ "application/json" })
	public Response loadDomanda(@PathParam("idDomanda") int idDomanda, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveDomanda(Domanda body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@PUT
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response updateDomanda(Domanda body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/assegna")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response updateDomandaToAssegnata(Domanda body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/preassegna")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response updateDomandaPreassegnata(Domanda body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/assegna/reject")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response annullaAssegnazione(Domanda body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	
	@PUT
	@Path("/invia")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response inviaDomanda(Domanda body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/close")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response updateDomandaToChiusa(Domanda body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/rifiutata")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response updateDomandaToRifiutata(Domanda body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);

	
	@DELETE
	@Path("/{idDomanda}")
	@Produces({ "application/json" })
	public Response deleteDomanda(@PathParam("idDomanda") int idDomanda, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/{idDomanda}/accettazione")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response confermaAccettazione(@PathParam("idDomanda") int idDomanda, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/variazione")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response confermaVariazione(Domanda body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/search")
	//@Produces({ "application/json" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPaginatedSearchDomande(@RequestBody RicercaDomanda body,@QueryParam("page") int page, 
			@QueryParam("limit") int limit, @QueryParam("sort") String sort,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/search")
	//@Produces({ "application/json" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPaginatedSearchDomande2(@QueryParam("page") int page, 
			@QueryParam("limit") int limit,  @QueryParam("sort") String sort,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/info/{idDomanda}")
	@Produces({ "application/json" })
	public Response getInfoDomanda(@PathParam("idDomanda") int idDomanda, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/count/{idSoggetto}")
	@Produces({ "application/json" })
	public Response getNumDomandeBySoggetto(@PathParam("idSoggetto") int idSoggetto, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/pagamento/piantine/annulla")
	@Produces({ "application/json" })
	public Response annullaPagamentoPiantine(Domanda body, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/pagamento/piantine/add")
	@Produces({ "application/json" })
	public Response inserisciPagamentoPiantine(Pagamento body, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/pagamento/piantine/update")
	@Produces({ "application/json" })
	public Response updatePagamentoPiantine(Pagamento body, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	
	
}
