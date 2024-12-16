/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import it.csi.vifo.vifoapi.business.be.exception.ServiceException;
import it.csi.vifo.vifoapi.dto.FileUploadForm;

@Path("/private/documento")
@Produces({ "application/json" })
public interface DocumentoApi {

	@POST
	@Path("/upload/domanda")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ "application/json" })
	public Response uploadDocumentiDomanda(@QueryParam("idDomanda") int idDomanda, @QueryParam("tipo") int tipo,
			 @MultipartForm FileUploadForm form, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/upload/imgSpecie")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ "application/json" })
	public Response uploadImgSpecie(@QueryParam("idSpecie") int idSpecie, @QueryParam("fotoType") int fotoType,
			 @MultipartForm FileUploadForm form, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	
	@DELETE
	@Path("/delete/{idAllegato}")
	public Response deleteDocumentoById(@PathParam("idAllegato") Integer idAllegato,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws IOException;
	
	@GET
	@Path("/download/allegato/{idAllegato}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadPortalDocumento(@PathParam("idAllegato") int idAllegato,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException;
	
	@GET
	@Path("/download/image/{idSpecie}/{fotoType}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadPortalImage(@PathParam("idSpecie")Integer idSpecie, @PathParam("fotoType")Integer fotoType,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException;
	
	@GET
	@Path("/domanda/{idDomanda}")
	@Produces({ "application/json" })
	public Response getAllegatiByIdDomanda(@PathParam("idDomanda") Integer idDomanda,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException;
	
	@GET
	@Path("/download/pagamento/avviso/{idDomanda}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadAvvisoPagamento(@PathParam("idDomanda") int idDomanda,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException;
	
	@GET
	@Path("/download/pagamento/ricevuta/{idDomanda}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadRicevutaPagamento(@PathParam("idDomanda") int idDomanda,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException;
	
	
}
