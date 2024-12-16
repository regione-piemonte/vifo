/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


/**
 * classe di avvio dell'applicazione
 */
@ApplicationPath("/be")
public class VifoboApplication extends Application{
    private final Set<Object> singletons = new HashSet<>();
    private final Set<Class<?>> empty = new HashSet<>();

    /**
     * costruttore della classe
     */
    public VifoboApplication(){
        // Instatiate class
    }

    /**
     * Non utilizzato
     *
     * @return un set vuoto
     */
    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    /**
     * Non utilizzato
     *
     * @return un set vuoto
     */
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}