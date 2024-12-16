/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util.templating;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import it.csi.vifo.vifoapi.util.Constants;
import it.csi.vifo.vifoapi.util.TipoPdfEnum;

/**
 * The type Template util.
 *
 * @author CSI PIEMONTE
 */
public class TemplateUtil {
    private static final Logger LOGGER = Logger.getLogger(Constants.LOGGER_NAME + ".util");
    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * Get compiled template pdf byte [ ].
     *
     * @param json         json
     * @param templatePath templatePath
     * @return byte[] byte [ ]
     * @throws Exception Exception
     */
    public static byte[] getCompiledTemplatePDF(String json, String templatePath) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IXDocReport report;
        InputStream in = TemplateUtil.class.getClassLoader().getResourceAsStream(templatePath);
        if(in == null) {
        	in = new FileInputStream(templatePath);
        }
        
        report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

        Map<String, Object> mappings = new HashMap<>();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(json);
        mappings.put("ist", obj);
        mappings.put("now", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        IContext context = report.createContext();
        context.put("d", mappings);

        Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
        
        report.convert(context, options, out); //error on test
        
        return out.toByteArray();
    }
    
    public static String getFileName(TipoPdfEnum tipoPdf) {
    	return tipoPdf.getValue().concat("_".concat(TIMESTAMP_FORMAT.format(new Date())).concat(".pdf"));
    }

}