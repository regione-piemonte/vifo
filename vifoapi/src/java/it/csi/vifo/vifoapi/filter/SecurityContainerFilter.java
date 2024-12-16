/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Priority;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.springframework.context.annotation.ComponentScan;

import it.csi.iride2.policy.entity.Identita;
import it.csi.vifo.vifoapi.dto.UserInfo;
import it.csi.vifo.vifoapi.util.Constants;

/**
 * The type Security container filter.
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
@ComponentScan(basePackages = "it.csi.vifo.vifoapi.business.be.impl.dao")
public class SecurityContainerFilter implements ContainerRequestFilter {

    /**
     * The constant LOGGER.
     */
    protected static final Logger LOGGER = Logger.getLogger(Constants.COMPONENT_NAME + ".security");
    private static final String ENABLE_SECURITY_INIT_PARAM = "enablesecurity";
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401, new Headers<Object>());
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());
    private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<Object>());

    /**
     * The Web request.
     */
    @Context
    HttpServletRequest webRequest;

    /**
     * The Istanza dao.
     */
//    IstanzaDAO istanzaDAO;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        //Get request headers
        /*
        LOGGER.info(getMethodName(containerRequestContext));
        getHeaders(containerRequestContext);
        getParameterNames(getMethod(containerRequestContext));
        */
        //setSessionAttributeUserInfo();

        /*
        istanzaDAO = (IstanzaDAO) WebApplicationContextUtils.getRequiredWebApplicationContext(webRequest.getSession().getServletContext()).getBean(IstanzaDAO.class);
        List<IstanzaExtendedDTO> istanzaExtendedDTOList = istanzaDAO.loadIstanza(1L);
        */

        /*
        if (!method.isAnnotationPresent(PermitAll.class)) {
            //Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                containerRequestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }

            //Get request headers
            final MultivaluedMap<String, String> headers = containerRequestContext.getHeaders();

            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

            //If no authorization information present; block access
            if (authorization == null || authorization.isEmpty()) {
                containerRequestContext.abortWith(ACCESS_DENIED);
                return;
            }

            //Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

            //Decode username and password
            String usernameAndPassword = null;
            try {
                usernameAndPassword = new String(Base64.decode(encodedUserPassword));
            } catch (IOException e) {
                containerRequestContext.abortWith(SERVER_ERROR);
                return;
            }

            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            //Verifying Username and password
            System.out.println(username);
            System.out.println(password);

            //Verify user access
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                //Is user valid?
                if (!isUserAllowed(username, password, rolesSet)) {
                    containerRequestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            }
        }
        */
    }

    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
        boolean isAllowed = false;

        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        //String userRole = userMgr.getUserRole(username);
        String userRole = "ADMIN";

        //Step 2. Verify user role
        if (rolesSet.contains(userRole)) {
            isAllowed = true;
        }
        return isAllowed;
    }

    /**
     * Recuper il metodo chiamato
     *
     * @param containerRequestContext containerRequestContext
     * @return Method
     */
    private Method getMethod(ContainerRequestContext containerRequestContext) {
        ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) containerRequestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
        return methodInvoker.getMethod();
    }

    /**
     * @param method method
     * @return String[]
     */
    private String[] getParameterNames(Method method) {
        Parameter[] parameters = method.getParameters();
        String[] paramNames = new String[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            paramNames[i] = parameters[i].getName();
            LOGGER.info("Parametro [" + i + "] : " + parameters[i].toString());
            /*
            for (Annotation annotation : parameters[i].getAnnotations()) {
                LOGGER.info(paramNames[i] + " : " + annotation.toString());
            }

             */
        }
        return paramNames;
    }

    /**
     * Recupera il nome del metodo chiamato
     *
     * @param containerRequestContext containerRequestContext
     * @return String
     */
    private String getMethodName(ContainerRequestContext containerRequestContext) {
        Method method = getMethod(containerRequestContext);
        return method.getName();
    }

    /**
     * @param containerRequestContext containerRequestContext
     */
    private void getHeaders(ContainerRequestContext containerRequestContext) {
        final MultivaluedMap<String, String> requestHeaders = containerRequestContext.getHeaders();
        for (Map.Entry<String, List<String>> entry : requestHeaders.entrySet()) {
            LOGGER.info(entry.getKey() + " : " + entry.getValue());
        }
        ;
    }

    /**
     * Imposta l'attributo con le info dell'utente in sessione
     *
     * @throws ServletException ServletException
     */
    private void setSessionAttributeUserInfo() {
        if (webRequest.getSession().getAttribute(IrideIdAdapterFilter.IRIDE_ID_SESSIONATTR) == null) {
            String marker = getToken(webRequest);
            if (marker != null) {
                try {
                    Identita identita = new Identita(normalizeToken(marker));
                    LOGGER.debug("[SecurityContainerFilter::setSessionAttributeUserInfo] Inserito in sessione marcatore IRIDE:" + identita);
                    webRequest.getSession().setAttribute(IrideIdAdapterFilter.IRIDE_ID_SESSIONATTR, identita);
                    UserInfo userInfo = new UserInfo();
                    userInfo.setNome(identita.getNome());
                    userInfo.setCognome(identita.getCognome());
                    userInfo.setEnte("--");
                    userInfo.setRuolo("--");
                    userInfo.setCodFisc(identita.getCodFiscale());
                    userInfo.setLivAuth(identita.getLivelloAutenticazione());
                    userInfo.setCommunity(identita.getIdProvider());
                    webRequest.getSession().setAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR, userInfo);
                } catch (Exception e) {
                    LOGGER.error("[SecurityContainerFilter::filter] " + e.toString(), e);
                }
            } else {
                // il marcatore deve sempre essere presente altrimenti e' una
                // condizione di errore (escluse le pagine home e di servizio)
                if (mustCheckPage(webRequest.getRequestURI())) {
                    LOGGER.error("[SecurityContainerFilter::setSessionAttributeUserInfo] Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
                    try {
                        throw new ServletException("Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
                    } catch (ServletException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * Gets session user.
     *
     * @return the session user
     */
    protected UserInfo getSessionUser() {
        UserInfo userInfo = (UserInfo) webRequest.getSession().getAttribute(IrideIdAdapterFilter.USERINFO_SESSIONATTR);
        return userInfo != null ? userInfo : (UserInfo) webRequest.getSession().getAttribute(IrideIdAdapterFilter.AUTH_ID_MARKER);
    }

    /**
     * getToken
     *
     * @param httpreq httpreq
     * @return string token
     */
    public String getToken(HttpServletRequest httpreq) {
        String marker = httpreq.getHeader(IrideIdAdapterFilter.AUTH_ID_MARKER);
        if (marker == null) {
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
        String marker = httpreq.getParameter(IrideIdAdapterFilter.AUTH_ID_MARKER);
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

    /**
     * mustCheckPage
     *
     * @param requestURI requestURI
     * @return booelan
     */
    private boolean mustCheckPage(String requestURI) {
        return true;
    }
}