package com.ackbox.a2.model;

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
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isValidAge(Integer age) {
        return age.intValue() >= 1;
    }

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
