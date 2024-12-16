/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util.otp;

import it.csi.vifo.vifoapi.util.Constants;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * The type Otp utils.
 *
 * @author CSI PIEMONTE
 */
public class OtpUtils {
    private static final Logger LOGGER = Logger.getLogger(Constants.LOGGER_NAME + ".util");

    /**
     * Generate otp string.
     *
     * @param size size
     * @return String string
     */
    public static String generateOTP(int size) {
        StringBuilder generatedToken = new StringBuilder();
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            // Generate 20 integers 0..20
            for (int i = 0; i < size; i++) {
                generatedToken.append(number.nextInt(9));
            }
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("OtpUtils::generateOTP", e);
        }
        return generatedToken.toString();
    }
}