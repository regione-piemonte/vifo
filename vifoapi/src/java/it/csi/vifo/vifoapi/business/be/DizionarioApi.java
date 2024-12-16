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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.jaxrs.QueryParam;

@Path("/private/dizionario")
@Produces({ "application/json" })
public interface DizionarioApi {
	
	@GET
	@Path("/sconto")
	@Produces({ "application/json" })
	public Response getScontoByImporto(@QueryParam("importo") double importo, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);	
	
	@GET
	@Path("/vivaio/list")
	@Produces({ "application/json" })
	public Response getVivaioList(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);	
	
	@GET
	@Path("/vivaio/{idVivaio}")
	@Produces({ "application/json" })
	public Response getVivaioById( @PathParam("idVivaio") Integer idVivaio,@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	
	@GET
	@Path("/scopo/list")
	@Produces({ "application/json" })
	public Response getScopoList(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);	
	
	@GET
	@Path("/scopo/{idScopo}")
	@Produces({ "application/json" })
	public Response getScopoById( @PathParam("idScopo") Integer idScopo,@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/titolarita/list")
	@Produces({ "application/json" })
	public Response getTitolaritaList(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);	
	
	@GET
	@Path("/titolarita/{idTitolarita}")
	@Produces({ "application/json" })
	public Response getTitolaritaById( @PathParam("idTitolarita") Integer idTitolarita,@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/comuni/list/{istatProv}")
	@Produces({ "application/json" })
	public Response getComuniListByIstatProv(@PathParam("istatProv") String istatProv,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/comuni/list")
	@Produces({ "application/json" })
	public Response getComuniList(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/comuni/piemonte")
	@Produces({ "application/json" })
	public Response getComuniPiemonteList(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/province/list")
	@Produces({ "application/json" })
	public Response getProvinceList(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);	
	
	@GET
	@Path("/province/piemonte")
	@Produces({ "application/json" })
	public Response getProvincePiemonteList(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);	
	
	@GET
	@Path("/comune/{istat}")
	@Produces({ "application/json" })
	public Response getComuneByIstat( @PathParam("istat") String istat,@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/specie/list")
	@Produces({ "application/json" })
	public Response getSpecieList(@QueryParam("all") boolean all, @Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/specie/famiglie/list")
	@Produces({ "application/json" })
	public Response getListFamiglieSpecie(@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/specie/byGroup")
	@Produces({ "application/json" })
	public Response getListSpecieByGroup(@QueryParam("all") boolean all, @Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/specie/paginated")
	@Produces({ "application/json" })
	public Response getPaginatedListSpecie(@QueryParam("all") boolean all, @QueryParam("page") int page, 
			@QueryParam("limit") int limit, @QueryParam("sort") String sort, @QueryParam("filter") String filter,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, 
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/specie/{id}")
	@Produces({ "application/json" })
	public Response getSpecieById( @PathParam("id") Integer id,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/statoDomanda/list")
	@Produces({ "application/json" })
	public Response getStatoDomanda(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);	
	
	@GET
	@Path("/tipoDomanda/list")
	@Produces({ "application/json" })
	public Response getTipoDomanda(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);	
	
	@GET
	@Path("/cognomeRichiedente/list")
	@Produces({ "application/json" })
	public Response getCognomeRichiedente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/denominazioneRichiedente/list")
	@Produces({ "application/json" })
	public Response getDenominazioneRichiedente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/materialiMoltiplicazione/list")
	@Produces({ "application/json" })
	public Response getListMaterialiDiMoltiplicazione(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/categoriaMateriale/list")
	@Produces({ "application/json" })
	public Response getListCategorieMateriale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/tipiMateriale/list")
	@Produces({ "application/json" })
	public Response getListTipiMaterialeBase(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/popolamentoSeme/list")
	@Produces({ "application/json" })
	public Response getListPopolamentoDaSeme(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/origini/list")
	@Produces({ "application/json" })
	public Response getListOrigini(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/udmMateriale/list")
	@Produces({ "application/json" })
	public Response getListUdmMateriale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/tipiAllevamento/list")
	@Produces({ "application/json" })
	public Response getListTipiAllevamento(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/gruppoSpecie/list")
	@Produces({ "application/json" })
	public Response getListGruppoSpecie(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/piantine/eta/list")
	@Produces({ "application/json" })
	public Response getListEta(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/frutti/range/anni")
	@Produces({ "application/json" })
	public Response getFruttiRangeAnni(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/piantine/range/anni")
	@Produces({ "application/json" })
	public Response getPiantineRangeAnni(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/semi/range/anni")
	@Produces({ "application/json" })
	public Response getSemiRangeAnni(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/certificati/list/anni")
	@Produces({ "application/json" })
	public Response getListAnniCertificati(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

}
