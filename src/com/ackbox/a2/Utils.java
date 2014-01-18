package com.ackbox.a2;

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
}
