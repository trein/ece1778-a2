package com.ackbox.a2.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.SyncFailedException;

import android.content.Context;

/**
 * Utility methods.
 * 
 * @author trein
 * 
 */
public class Utils {

    /**
     * Check if input is a valid number.
     * 
     * @param str String to be tested.
     * @return {@code true} in case of valid number, {@code false} otherwise.
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Check if given input is a valid age.
     * 
     * @param age Integer to be verified as a valid age.
     * @return {@code true} in case of valid age, {@code false} otherwise.
     */
    public static boolean isValidAge(Integer age) {
        return age.intValue() >= 1;
    }

    /**
     * Write a string into a file in the File System.
     * 
     * @param context Current application context.
     * @param fileName File name that will be used to store the data.
     * @param data String to be stored.
     * @throws SyncFailedException In case of failure when persisting file in
     *         the Android's File System.
     * @throws IOException In case of failure when writing file in output
     *         stream.
     */
    public static void writeToFile(Context context, String fileName, String data) throws SyncFailedException,
            IOException {
        FileOutputStream fos = null;
        OutputStreamWriter out = null;

        try {
            File outFile = new File(context.getFilesDir(), fileName);

            fos = new FileOutputStream(outFile);
            out = new OutputStreamWriter(fos);
            out.write(data);
            out.flush();
            fos.getFD().sync();
        } finally {
            out.close();
            fos.close();
        }
    }

    /**
     * Read a string from a file stored in the File System.
     * 
     * @param context Current application context.
     * @param fileName Name of the file to be loaded.
     * @return data that were stored in the file
     * @throws IOException In case of failure when reading from intput stream.
     */
    public static String readFromFile(Context context, String fileName) throws IOException {
        String data = null;
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader buffreader = null;

        try {
            File entriesFile = new File(context.getFilesDir(), fileName);
            fis = new FileInputStream(entriesFile);
            isr = new InputStreamReader(fis);
            buffreader = new BufferedReader(isr);

            String readString = buffreader.readLine();

            if (readString != null) {
                data = readString;
            }
        } finally {
            buffreader.close();
            isr.close();
            fis.close();
        }
        return data;
    }
}
