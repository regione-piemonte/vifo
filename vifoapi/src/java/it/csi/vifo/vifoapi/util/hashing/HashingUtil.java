/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifoapi.util.hashing;

import weblogic.utils.encoders.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

/**
 * The type Hashing util.
 *
 * @author CSI PIEMONTE
 */
public class HashingUtil {

    /**
     * Encode sh 3 string.
     *
     * @param stringToHash stringToHash
     * @return string string
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    public static String encodeSH3(String stringToHash) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final byte[] hashbytes = digest.digest((stringToHash + generateRandomAlphanumericString()).getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashbytes);
    }

    /**
     * Encode md 5 string.
     *
     * @param stringToHash the string to hash
     * @return the string
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static String encodeMD5(String stringToHash) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("MD5");
        final byte[] hashbytes = digest.digest((stringToHash + generateRandomAlphanumericString()).getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashbytes);
    }

    /**
     * Encode sha 256 hash string.
     *
     * @param stringToHash the string to hash
     * @return the string
     * @throws NoSuchAlgorithmException     the no such algorithm exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public static String encodeSHA256Hash(String stringToHash) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        final byte[] hashbytes = digest.digest((stringToHash + generateRandomAlphanumericString()).getBytes(StandardCharsets.UTF_8));
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encodeBuffer(hashbytes);
    }

    /**
     * Encode sha 256 hash string.
     *
     * @param contentToHash the content to hash
     * @return the string
     * @throws NoSuchAlgorithmException     the no such algorithm exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public static String encodeSHA256Hash(byte[] contentToHash) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        final byte[] hashbytes = digest.digest(concatByteArray(contentToHash, generateRandomAlphanumericString().getBytes(StandardCharsets.UTF_8)));
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encodeBuffer(hashbytes);
    }

    /**
     * @param hash hash
     * @return string
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


    /**
     * @return  string
     */
    private static String generateRandomAlphanumericString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static byte[] concatByteArray(byte[] a, byte[] b) {
        int lenA = a.length;
        int lenB = b.length;
        byte[] c = Arrays.copyOf(a, lenA + lenB);
        System.arraycopy(b, 0, c, lenA, lenB);
        return c;
    }
}