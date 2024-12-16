/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 * SecurityFilter class
 *
 * @author CSI PIEMONTE
 */
public class SecurityFilter implements Filter {
    private static final String ENABLE_SECURITY_INIT_PARAM = "enablesecurity";
    private boolean enableSecurity = false;

    /**
     * The Context.
     */
    @Context
    ContainerRequestContext context;

    /**
     * The Istanza dao.
     */
//    IstanzaDAO istanzaDAO;

    /**
     * doFilter
     *
     * @param servletRequest  servletRequest
     * @param servletResponse servletResponse
     * @param filterChain     filterChain
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (enableSecurity) {
            HttpServletResponse res = (HttpServletResponse) servletResponse;
            res.setHeader("Access-Control-Allow-Origin", "*");
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        }
        //List<IstanzaExtendedDTO> istanzaExtendedDTOList = istanzaDAO.loadIstanza(1L);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * init
     *
     * @param filterConfig filterConfig
     * @throws ServletException ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String sEnableSecurity = filterConfig.getInitParameter(ENABLE_SECURITY_INIT_PARAM);
        enableSecurity = "true".equals(sEnableSecurity);
        //istanzaDAO = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext()).getBean(IstanzaDAO.class);
    }

    /**
     * destroy
     */
    @Override
    public void destroy() {
    }

}