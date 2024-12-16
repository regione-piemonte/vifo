/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.apache.tika.Tika;

/**
 * The type Up down file util.
 *
 * @author CSI PIEMONTE
 */
public class FileUtils {

    private static final Logger LOGGER = Logger.getLogger(Constants.LOGGER_NAME + ".util");
    private static final String CLASSNAME = "FileUtils";

    /**
     * Copy file.
     *
     * @param source the source
     * @param dest   the dest
     * @throws IOException the io exception
     */
    public static void copyFile(File source, File dest) throws IOException {
        // crea directory di destinazione se non esiste
        dest.getParentFile().mkdirs();
        Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Byte array to file file.
     *
     * @param byteArray the byte array
     * @param filename  the filename
     * @return the file
     */
    public static File byteArrayToFile(byte[] byteArray, String filename) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String inputParam = "Parametro in input filename [" + filename + "]";
        LOGGER.debug("[" + CLASSNAME + "::" + methodName + "] BEGIN");
        LOGGER.debug("[" + CLASSNAME + "::" + methodName + "] " + inputParam);
        File result = new File(filename);
        try {
            org.apache.commons.io.FileUtils.writeByteArrayToFile(result, byteArray);
        } catch (IOException e) {
            LOGGER.error("[" + CLASSNAME + "::" + methodName + "] " + e);
        } finally {
            LOGGER.debug("[" + CLASSNAME + "::" + methodName + "] END");
        }
        return result;
    }

    /**
     * Copy file using stream.
     *
     * @param source the source
     * @param dest   the dest
     * @throws IOException the io exception
     */
    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        FileOutputStream os = null;
        try {
            // crea directory di destinazione se non esiste
            dest.getParentFile().mkdirs();

            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

    /**
     * Delete file boolean.
     *
     * @param filename the filename
     * @return the boolean
     */
    public static boolean deleteFile(String filename) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String inputParam = "Parametro in input filename [" + filename + "]";
        LOGGER.debug("[" + CLASSNAME + "::" + methodName + "] BEGIN");
        LOGGER.debug("[" + CLASSNAME + "::" + methodName + "] " + inputParam);
        boolean result = Boolean.FALSE;
        try {
            File file = new File(filename);
            result = file.delete();
        } catch (Exception e) {
            LOGGER.error("[" + CLASSNAME + "::" + methodName + "] " + e);
        } finally {
            LOGGER.debug("[" + CLASSNAME + "::" + methodName + "] END");
        }
        return result;
    }

    /**
     * Delete folder boolean.
     *
     * @param folder the folder
     * @return the boolean
     */
    public static boolean deleteFolder(String folder) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String inputParam = "Parametro in input folder [" + folder + "]";
        LOGGER.debug("[" + CLASSNAME + "::" + methodName + "] BEGIN");
        LOGGER.debug("[" + CLASSNAME + "::" + methodName + "] " + inputParam);
        boolean result = Boolean.FALSE;
        try {
            org.apache.commons.io.FileUtils.deleteDirectory(new File(folder));
            result = Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error("[" + CLASSNAME + "::" + methodName + "] " + e);
        } finally {
            LOGGER.debug("[" + CLASSNAME + "::" + methodName + "] END");
        }
        return result;
    }


    /**
     * Zip folder file output stream.
     *
     * @param folderPath  the folder path
     * @param zipFilename the zip filename
     * @return the file output stream
     */
    public static FileOutputStream zipFolder(String folderPath, String zipFilename) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        String inputParam = "Parametro in input folderPath [" + folderPath + "] - zipFilename [" + zipFilename + "]";
        LOGGER.debug("[" + CLASSNAME + "::" + methodName + "] BEGIN");
        LOGGER.debug("[" + CLASSNAME + "::" + methodName + "] " + inputParam);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(zipFilename);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(folderPath);
            zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            //fos.close();
        } catch (IOException e) {
            LOGGER.error("[" + CLASSNAME + "::" + methodName + "] " + e);
        } finally {
            LOGGER.debug("[" + CLASSNAME + "::" + methodName + "] END");
        }
        return fos;
    }


    /**
     * Zip file.
     *
     * @param fileToZip the file to zip
     * @param fileName  the file name
     * @param zipOut    the zip out
     * @throws IOException the io exception
     */
    public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            if (children != null) {
                for (File childFile : children) {
                    zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
                }
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }


    /**
     * @param header header
     * @return string
     */
    private static String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                return name[1].trim().replace("\"", "");
            }
        }
        return "unknown";
    }

    /**
     * @param content  content
     * @param filename filename
     * @throws IOException IOException
     */
    private static void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(content);
        fop.flush();
        fop.close();
    }
    
    public static String getMimeTypeFromFileName(String fileName) {
        Tika tika = new Tika();
        return tika.detect(fileName);
      }

}