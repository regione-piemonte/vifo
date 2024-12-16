/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.util.builder;

/**
 *
 * @author 71740 (Simone Cornacchia)
 *
 * @param <T> built type
 */
public interface GenericBuilder<T> {

    /**
     *
     * @return
     */
    T build();

}
