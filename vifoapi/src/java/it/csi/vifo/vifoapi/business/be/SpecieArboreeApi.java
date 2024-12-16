/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import it.csi.vifo.vifoapi.dto.Specie;

@Path("/private/specieArboree")
@Produces({ "application/json" })
public interface SpecieArboreeApi {
	
	
		
	@POST
    @Produces({ "application/json" })
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response saveSpecie(@MultipartForm MultipartFormDataInput data, 
			@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@PUT
    @Produces({ "application/json" })
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateSpecie(@MultipartForm MultipartFormDataInput data, 
			@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);


}
