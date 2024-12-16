/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import it.csi.vifo.vifoapi.dto.LocalDateFormat;

public class LocalDateDefaultDeserializer extends StdDeserializer<LocalDate> {

    protected LocalDateDefaultDeserializer() {
        super(LocalDate.class);
    }
    
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
    	LocalDateFormat dateMapped = mapper.readValue(jp, LocalDateFormat.class);
        return LocalDate.of(dateMapped.getYear(), Month.of(dateMapped.getMonthValue()), dateMapped.getDayOfMonth());
    }
}
