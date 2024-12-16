/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The type Base extended dto.
 *
 * @param <T> the type parameter
 * @author CSI PIEMONTE
 */
public abstract class BaseExtendedDTO<T> extends BaseDTO{

    /**
     * Gets dto.
     *
     * @return the dto
     */
    @JsonIgnore
    public abstract T getDTO();

}