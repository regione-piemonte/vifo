/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {
	
	static final Logger logger = Logger.getLogger(LocalDateDeserializer.class);

    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    	DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	String value = parser.readValueAs(String.class);
    	
    	logger.info("LocalDateDeserializer: " + value);
    	if(value != null && value.length()> 0) {
    		logger.info("LocalDateDeserializer: " + logger);
    		return LocalDate.parse(value, dtFormatter);
    	}
    	return null;
    }
}
