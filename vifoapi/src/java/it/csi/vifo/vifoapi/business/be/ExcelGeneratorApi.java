/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/private/excel")
@Produces(MediaType.APPLICATION_JSON)
public interface ExcelGeneratorApi {
	
	@GET
	@Path("semi")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response generateExcelSemi(@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("piantine/{idVivaio}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response generateExcelPiantine(@PathParam("idVivaio") Integer idVivaio,@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("frutti")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response generateExcelFrutti(@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("caricoScarico")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response generateExcelCaricoScarico(@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

}
