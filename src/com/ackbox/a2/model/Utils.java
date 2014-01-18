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
        File outFile = new File(context.getFilesDir(), fileName);
        FileOutputStream fos = new FileOutputStream(outFile);
        OutputStreamWriter out = new OutputStreamWriter(fos);

        out.write(data);
        out.flush();
        fos.getFD().sync();
        out.close();
        fos.close();
    }

    public static String readFromFile(Context context, String fileName) throws IOException {
        String data = null;

        File entriesFile = new File(context.getFilesDir(), fileName);
        FileInputStream fis = new FileInputStream(entriesFile);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader buffreader = new BufferedReader(isr);
        String readString = buffreader.readLine();

        if (readString != null) {
            data = readString;
        }
        buffreader.close();
        isr.close();
        fis.close();

        return data;
    }
}
