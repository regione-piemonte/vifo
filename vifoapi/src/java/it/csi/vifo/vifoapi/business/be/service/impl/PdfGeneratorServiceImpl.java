/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.business.be.service.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.vifo.vifoapi.business.be.impl.BaseApiServiceImpl;
import it.csi.vifo.vifoapi.business.be.impl.dao.ConfigurationDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.DestinazioneDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.PdfDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.RStatoDomandaDAO;
import it.csi.vifo.vifoapi.business.be.impl.dao.SpecieRichiestaDAO;
import it.csi.vifo.vifoapi.business.be.service.PdfGeneratorService;
import it.csi.vifo.vifoapi.dto.PdfAssegnazione;
import it.csi.vifo.vifoapi.dto.PdfCertificato;
import it.csi.vifo.vifoapi.dto.PdfDdt;
import it.csi.vifo.vifoapi.dto.PdfDomanda;
import it.csi.vifo.vifoapi.dto.PdfEtichetta;
import it.csi.vifo.vifoapi.dto.RStatoDomanda;
import it.csi.vifo.vifoapi.dto.SpecieRichiesta;
import it.csi.vifo.vifoapi.util.StatoDomandaEnum;
import it.csi.vifo.vifoapi.util.TipoParametroApplEnum;
import it.csi.vifo.vifoapi.util.TipoPdfEnum;
import it.csi.vifo.vifoapi.util.templating.TemplateUtil;

@Component
public class PdfGeneratorServiceImpl extends BaseApiServiceImpl implements PdfGeneratorService {
	
	private final String CLASSNAME = this.getClass().getSimpleName();
	public static final String TEMPLATES_RESOURCES_CLASSPATH = "classpath:templates/";
	public static final String TEMPLATE_EXTENSION = ".docx";
	
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	SpecieRichiestaDAO specieRichiestaDAO;
	
	@Autowired
	DestinazioneDAO destinazioneDAO;
	
	@Autowired
	private ConfigurationDAO  configurationDAO;
	
	@Autowired
	private RStatoDomandaDAO rStatoDomandaDAO;
	
	@Autowired
	private PdfDAO pdfDAO;

	@Override
	public byte[] generateDichiarazione(TipoPdfEnum tipoPdf, Integer id, HttpServletRequest httpRequest) throws Exception {
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {			
	        LOGGER.debug(getClassFunctionBeginInfo(CLASSNAME, methodName));
			Resource res = resourceLoader.getResource(TEMPLATES_RESOURCES_CLASSPATH.concat(tipoPdf.getValue()).concat(TEMPLATE_EXTENSION));
			String pathTemplate = res.getFile().getAbsolutePath();
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"pathTemplate: " + pathTemplate));
			
			String json = null;
			ObjectMapper jsonMapper = new ObjectMapper();  
			
			PdfAssegnazione assegnazione = null;
			PdfDomanda domanda = null;
			
			String param;
			String tipoFirma = httpRequest.getParameter("tipoFirma");
			String isBozza = httpRequest.getParameter("isBozza");
			
			
			switch(tipoPdf) {
			case ASSEGNAZIONE_GRATUITA:
				assegnazione = pdfDAO.getAssegnazione(id);
				if(isBozza != null) {
					assegnazione.setIsBozza(true);
				}
				assegnazione.setNoteVivaista(getNoteAssegnazione(id));
				
				assegnazione.setListBeni(pdfDAO.getBeniAssegnazioneGratuita(id));
				System.out.println(assegnazione.getListBeni().get(0));				
				assegnazione.setListInformazioni(
						configurationDAO.getListTestiByTipoParametro(TipoParametroApplEnum.TEMPLATE_ASSEGNAZIONE_GRATUITA_INDICAZIONI));
				json = jsonMapper.writeValueAsString(assegnazione);
				break;
			case ASSEGNAZIONE_ONEROSA:
				assegnazione = pdfDAO.getAssegnazione(id);
				if(isBozza != null) {
					assegnazione.setIsBozza(true);
				}
				assegnazione.setNoteVivaista(getNoteAssegnazione(id));
				
				assegnazione.setTipoDocumento(tipoFirma == null?"NO_FIRMA":tipoFirma);//"FIRMA_DIGITALE"-"FIRMA_AUTOGRAFA"-"NO_FIRMA"
				assegnazione.setListBeni(pdfDAO.getBeniAssegnazioneOnerosa(id));
				assegnazione.setListInformazioni(
						configurationDAO.getListTestiByTipoParametro(TipoParametroApplEnum.TEMPLATE_ASSEGNAZIONE_INDICAZIONI));
				assegnazione.setCondizioniVenditaTitolo(
						configurationDAO.getConfigurationByTipoParametro(TipoParametroApplEnum.TEMPLATE_ASSEGNAZIONE_COND_VENDITA_TITOLO));
				assegnazione.setListCondizioniVenditaParagrafi(
						configurationDAO.getListTestiByTipoParametro(TipoParametroApplEnum.TEMPLATE_ASSEGNAZIONE_COND_VENDITA_PARAGRAFO));
				json = jsonMapper.writeValueAsString(assegnazione);
				break;
			case CERTIFICATO:
				PdfCertificato cert = pdfDAO.getCertificato(id);
				json = jsonMapper.writeValueAsString(cert);
				break;
			case DDT:
				param = httpRequest.getParameter("anno");
				int anno = Integer.parseInt(param);
				PdfDdt ddt = pdfDAO.getDdt(id, anno);
				ddt.setListBeni(pdfDAO.getBeniDdt(id, anno));
				json = jsonMapper.writeValueAsString(ddt);
				break;
			case DOMANDA_GRATUITA:
			case DOMANDA_ONEROSA:
				domanda = pdfDAO.getDomanda(id);
				LOGGER.info(getClassFunctionDebugString(CLASSNAME, methodName,"richiestoBollo: " + domanda.getRichiestoBollo()));
				domanda.setTipoDocumento(tipoFirma == null?"NO_FIRMA":tipoFirma);//"FIRMA_DIGITALE":"FIRMA_AUTOGRAFA"
				domanda.setListDestinazioni(destinazioneDAO.getDestinazioniByIdDomanda(id));
				domanda.setListSpecieRichieste(specieRichiestaDAO.getByIdDomanda(id));
				for(SpecieRichiesta item : domanda.getListSpecieRichieste()) {
					item.setNomeComune(item.getNomeComune() + (item.getNomeScientifico() == null? "" : " - " + item.getNomeScientifico()));
				}
				domanda.setListAttitudineSpecieRichieste(specieRichiestaDAO.getAttitudiniByIdDomanda(id));
				domanda.setListNoteDinamiche(configurationDAO.getListConfigurationsByTipoParametro(TipoParametroApplEnum.TEMPLATE_DOMANDA_NOTA));
				domanda.setTitlePrivacy(configurationDAO.getConfigurationByTipoParametro(TipoParametroApplEnum.TEMPLATE_DOMANDA_PRIVACY_TITOLO));
				domanda.setListParagrafiPrivacy(configurationDAO.getListConfigurationsByTipoParametro(TipoParametroApplEnum.TEMPLATE_DOMANDA_PRIVACY_PARAGRAFO));
				json = jsonMapper.writeValueAsString(domanda);
				break;
			case ETICHETTA:
				List<PdfEtichetta> listEtichette = pdfDAO.getEtichette(id);
	
				// lsita con flag 386
				List<PdfEtichetta> list386 = listEtichette.stream().filter(PdfEtichetta::isFlag386).collect(Collectors.toList());
	
				//lista solo passaporto
				List<PdfEtichetta> listPassport = listEtichette.stream()
						.filter(Predicate.not(PdfEtichetta::isFlag386))
						.sorted(Comparator.comparing(PdfEtichetta::isFlagZP))
						.collect(Collectors.toList());
	
				Map<String, Object> obj = new HashMap<String,Object>(); 
				obj.put("list",list386);
				obj.put("passports",listPassport);
				json = jsonMapper.writeValueAsString(obj);
				break;
			case ETICHETTA_QR_CODE:
				
				break;
			case REGISTRO_CARICO_SCARICO:
				
				break;
			
			}
			
			LOGGER.debug(getClassFunctionDebugString(CLASSNAME, methodName,"json: " + json));
			
			byte[] out = TemplateUtil.getCompiledTemplatePDF(json, pathTemplate);
			
			return out;
		}finally {
			LOGGER.debug(getClassFunctionEndInfo(CLASSNAME, methodName));
		}
	}
	
	private String getNoteAssegnazione(int idDomanda) {
		RStatoDomanda stato = rStatoDomandaDAO.getLastStatoByType(idDomanda, StatoDomandaEnum.ASSEGNATA);
		if(stato == null) {
			stato = rStatoDomandaDAO.getLastStatoByType(idDomanda, StatoDomandaEnum.PREASSEGNATA);
		}
		return 	stato.getNoteStato()!=null?stato.getNoteStato():"nessuna";
	}

}
