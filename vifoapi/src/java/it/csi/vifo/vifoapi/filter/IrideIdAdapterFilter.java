/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.iride2.policy.entity.Identita;
import it.csi.iride2.policy.exceptions.MalformedIdTokenException;
import it.csi.vifo.vifoapi.dto.ErrorDTO;
import it.csi.vifo.vifoapi.util.Constants;

/**
 * Inserisce in sessione:
 * <ul>
 *  <li>l'identit&agrave; digitale relativa all'utente autenticato.
 *  <li>l'oggetto <code>currentUser</code>
 * </ul>
 * Funge da adapter tra il filter del metodo di autenticaizone previsto e la
 * logica applicativa.
 *
 * @author CSI PIEMONTE
 */
public class IrideIdAdapterFilter implements Filter {

    /**
     * The constant IRIDE_ID_SESSIONATTR.
     */
    public static final String IRIDE_ID_SESSIONATTR = "iride2_id";

    /**
     * The constant AUTH_ID_MARKER.
     */
    public static final String AUTH_ID_MARKER = "Shib-Iride-IdentitaDigitale";

    /**
     * The constant USERINFO_SESSIONATTR.
     */
    public static final String USERINFO_SESSIONATTR = "appDatacurrentUser";

    private static final String DEVMODE_INIT_PARAM = "devmode";

    private boolean devmode = false;

    /**
     * The constant LOG.
     */
    protected static final Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME + ".security");

    /**
     * doFilter
     *
     * @param req req
     * @param resp resp
     * @param filterChain filterChain
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest) req;
        HttpServletResponse hresp = (HttpServletResponse)resp;
        String path = hreq.getRequestURL().toString();
        LOG.debug("[IrideIdAdapterFilter::doFilter] IP: " + req.getRemoteAddr());
        boolean error = false;
        if (hreq.getSession().getAttribute(IRIDE_ID_SESSIONATTR) == null && path.indexOf("/be/private/") > -1) {
			String marker = getToken(hreq);
	

//			DEMO 24: vivaio Carlo Alberto
//			String marker = "AAAAAA00A11E000M/CSI PIEMONTE/DEMO 24/SP//1//";
			
//			DEMO 23: vivaio Fenale
//			String marker = "AAAAAA00A11D000L/CSI PIEMONTE/DEMO 23/SP//1//";
			
//			DEMO 23: vivaio Gambarello
//			String marker = "AAAAAA00A11O000W/CSI PIEMONTE/DEMO 34/SP//1//";
			
//			DEMO 36: utente GESTORE
//			String marker = "AAAAAA00A11S000A/CSI PIEMONTE/DEMO 36/SP//1//";
			
//			DEMO 20: utente RICHIEDENTE		
//			String marker = "AAAAAA00B77B000F/CSI PIEMONTE/DEMO 20/SP//1//";
			
//			DEMO 22: utente RICHIEDENTE		
//			String marker = "AAAAAA00A11C000K/CSI PIEMONTE/DEMO 22/SP//1//";
			
//			DEMO 25: utente GESTORE
//			String marker = "AAAAAA00A11F000N/CSI PIEMONTE/DEMO 25/ACTALIS_EU/20200701155041/16/EJT4EDylzPizr452N3PZoQ==";
			
//			DEMO 35: VIVAIO TEST ANNATA
//			String marker = "AAAAAA00A11R000Z/CSI PIEMONTE/DEMO 35/SP//1//";
			
            if (marker != null) {
                try {
                    Identita identita = new Identita(normalizeToken(marker));
                    LOG.debug("[IrideIdAdapterFilter::doFilter] Inserito in sessione marcatore IRIDE:" + identita);
                    hreq.getSession().setAttribute(IRIDE_ID_SESSIONATTR, identita);
                    it.csi.vifo.vifoapi.dto.UserInfo userInfo = new it.csi.vifo.vifoapi.dto.UserInfo();
                    userInfo.setNome(identita.getNome());
                    userInfo.setCognome(identita.getCognome());
                    userInfo.setEnte("--");
                    userInfo.setRuolo("--");
                    userInfo.setCodFisc(identita.getCodFiscale());
                    userInfo.setLivAuth(identita.getLivelloAutenticazione());
                    userInfo.setCommunity(identita.getIdProvider());
                    hreq.getSession().setAttribute(USERINFO_SESSIONATTR, userInfo);
                } catch (MalformedIdTokenException e) {
                    LOG.error("[IrideIdAdapterFilter::doFilter] " + e.toString(), e);
                }
            } else {
                //il marcatore deve sempre essere presente altrimenti e' una
                // condizione di errore (escluse le pagine home e di servizio)
                if (mustCheckPage(hreq.getRequestURI())) {
                    LOG.error(
                            "[IrideIdAdapterFilter::doFilter] Tentativo di accesso senza token di sicurezza");
                    hresp.setStatus(500);
                    resp.getWriter().write(convertObjectToJson(new ErrorDTO(500+ "", "E100", "Tentativo di accesso a pagina senza token di sicurezza", null,null)));
                    error = true;
                }
            }
        }else if(path.indexOf("/be/callback/") > -1) {        	
        	LOG.info("[IrideIdAdapterFilter::doFilter] chiamata a callback da IP: " + req.getRemoteAddr()
        	+ " - method: " + hreq.getMethod() + " - url: " + hreq.getRequestURL().toString());
        }
        if(!error) {
        	filterChain.doFilter(req, resp);
        }
    }
    
    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    /**
     * mustCheckPage
     *
     * @param requestURI requestURI
     * @return booelan
     */
    private boolean mustCheckPage(String requestURI) {
        return true;
    }

    /**
     * destroy
     */
    public void destroy() {
        // NOP
    }

    /**
     * init
     *
     * @param fc fc
     * @throws ServletException ServletException
     */
    public void init(FilterConfig fc) throws ServletException {
        String sDevmode = fc.getInitParameter(DEVMODE_INIT_PARAM);
        devmode = "true".equals(sDevmode);
    }

    /**
     * getToken
     *
     * @param httpreq httpreq
     * @return string token
     */
    public String getToken(HttpServletRequest httpreq) {
        String marker = httpreq.getHeader(AUTH_ID_MARKER);
        if (marker == null && devmode) {
            return getTokenDevMode(httpreq);
        } else {
            // gestione dell'encoding
            String decodedMarker = new String(marker.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            return decodedMarker;
        }
    }

    /**
     * getTokenDevMode
     *
     * @param httpreq httpreq
     * @return string
     */
    private String getTokenDevMode(HttpServletRequest httpreq) {
        String marker = httpreq.getParameter(AUTH_ID_MARKER);
        return marker;
    }

    /**
     * normalizeToken
     *
     * @param token token
     * @return string
     */
    private String normalizeToken(String token) {
        return token;
    }

}
