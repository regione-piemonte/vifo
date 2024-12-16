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

import it.csi.vifo.vifoapi.dto.CertificatoProvPiemonte;
import it.csi.vifo.vifoapi.dto.PartitaFrutti;
import it.csi.vifo.vifoapi.dto.PartitaPiante;
import it.csi.vifo.vifoapi.dto.PartitaSemi;
import it.csi.vifo.vifoapi.dto.RichiestaTrasferimentoPiante;
import it.csi.vifo.vifoapi.dto.Vivaio;

@Path("/private/inventario")
@Produces({ "application/json" })
public interface InventarioApi {
	
	@GET
	@Path("/frutti/list")
	@Produces({ "application/json" })
	public Response getListInventarioFrutti(@QueryParam("page") int page, 
			@QueryParam("limit") int limit, @QueryParam("sort") String sort,@QueryParam("numero") Integer numeroPartita, @QueryParam("specie") String specie,
			@QueryParam("annata") int annata ,@QueryParam("attive") boolean attive,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/frutti/{id}")
	@Produces({ "application/json" })
	public Response getDetailsInventarioFrutti(@PathParam("id") int id, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/frutti/riepilogo/{id}")
	@Produces({ "application/json" })
	public Response getInventarioFruttiRiepilogo(@PathParam("id") int id, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/frutti/save")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response savePartitaFrutti(PartitaFrutti body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	
	@PUT
	@Path("/frutti/update")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response updatePartitaFrutti(PartitaFrutti body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@DELETE
	@Path("/frutti/delete/{id}")
	@Produces({ "application/json" })
	public Response deleteInventarioFruttiById(@PathParam("id") int id, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/semi/list")
	@Produces({ "application/json" })
	public Response getListInventarioSemi(@QueryParam("page") int page, 
			@QueryParam("limit") int limit, @QueryParam("sort") String sort,@QueryParam("numero") Integer numeroPartita, @QueryParam("specie") String specie,
			@QueryParam("annata") int annata ,@QueryParam("attive") boolean attive,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/semi/{id}")
	@Produces({ "application/json" })
	public Response getDetailsInventarioSemi(@PathParam("id") int id, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/semi/riepilogo/{id}")
	@Produces({ "application/json" })
	public Response getRiepilogoSemiById(@PathParam("id") int id, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/semi/save")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response savePartitaSemi(PartitaSemi body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	
	@PUT
	@Path("/semi/update")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response updatePartitaSemi(PartitaSemi body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@DELETE
	@Path("/semi/delete/{id}")
	@Produces({ "application/json" })
	public Response deleteInventarioSemiById(@PathParam("id") int id, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/piantine/list")
	@Produces({ "application/json" })
	public Response getListInventarioPiantine(@QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort, @QueryParam("idVivaio") int idVivaio,@QueryParam("numero") Integer numeroPartita, @QueryParam("specie") String specie,
			@QueryParam("annata") int annata ,@QueryParam("attive") boolean attive,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/piantine/riepilogo/{id}")
	@Produces({ "application/json" })
	public Response getRiepilogoPiantineById(@PathParam("id") int id, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/piantine/{id}")
	@Produces({ "application/json" })
	public Response getDetailsInventarioPiantine(@PathParam("id") int id, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/piantine/save")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response savePartitaPiantine(PartitaPiante body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	
	@PUT
	@Path("/piantine/update")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response updatePartitaPiantine(PartitaPiante body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@DELETE
	@Path("/piantine/delete/{id}")
	@Produces({ "application/json" })
	public Response deleteInventarioPiantineById(@PathParam("id") int id, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/certificatoPiemonte/save")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveCertificatoPiemonte(CertificatoProvPiemonte body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/certificatoPiemonte/update")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response updateCertificatoPiemonte(CertificatoProvPiemonte body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);

	
	@GET
	@Path("/certificatoPiemonte/{id}")
	@Produces({ "application/json" })
	public Response getCertificatoById(@PathParam("id") int id, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
//	@GET
//	@Path("/certificatoPiemonte/list")
//	@Produces({ "application/json" })
//	public Response getListCertificatiPiemonte(@Context SecurityContext securityContext, 
//			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/certificatoPiemonte/list")
	@Produces({ "application/json" })
	public Response getListCertificatiPiemonte(@QueryParam("page") int page, 
			@QueryParam("limit") int limit,	@QueryParam("sort") String sort, 
			@QueryParam("specie") String specie, @QueryParam("annata") int annata,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	
	@POST
	@Path("/richiestaTrasferimento/save")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveRichiestaTrasferimentoPiante(RichiestaTrasferimentoPiante body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/richiestaTrasferimento/list")
	@Produces({ "application/json" })
	public Response getListTrasferimentoPiantine(@QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort, @Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/richiestaTrasferimento/count")
	@Produces({ "application/json" })
	public Response getCountTrasferimentoPiantine(@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/richiestaTrasferimento/{id}")
	@Produces({ "application/json" })
	public Response getRichiestaTrasferimentoById(@PathParam("id") int id, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/richiestaTrasferimento/accettaRifiuta")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response accettaRifiutaTrasferimento(RichiestaTrasferimentoPiante body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);

	@GET
	@Path("/infoAnnata/vivaio/{idVivaio}")
	@Produces({ "application/json" })
	public Response getInfoAnnataInventario(@PathParam("idVivaio") int idVivaio, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/creaAnnata/inventario")
	@Produces({ "application/json" })
	public Response getCreaAnnataInventario(Vivaio body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}
