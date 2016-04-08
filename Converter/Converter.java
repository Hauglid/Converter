package com.hauglidtech.converter;

/**
 * Converter class to convert between bases
 * Created by Karl Thomas on 25.03.2016.
 */
class Converter {

    public static boolean isValid(String num, int base){
        try {
            //noinspection ResultOfMethodCallIgnored
            Integer.parseInt(num, base);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Converts a number in String format to given base
     * @param num  the String value to convert
     * @param base  the base to convert to
     * @return String   the converted number
     */
    public static String toDecimal(String num, int base){
        return Integer.toString(Integer.parseInt(num, base));
    }


    /**
     * Converts decimal string to binary string
     * @param dec decimal string value to convert
     * @return String the binary value
     */
    public static String decimalToBinary(String dec){
        return Integer.toBinaryString(Integer.parseInt(dec));
    }

    /**
     * Converts decimal string to hexadecimal string
     * @param dec decimal string value to convert
     * @return String the hexadecimal value
     */
    public static String decimalToHexadecimal(String dec){
        return Integer.toHexString(Integer.parseInt(dec)).toUpperCase();
    }

    /**
     * Converts decimal string to octal string
     * @param dec decimal string value to convert
     * @return  String the octal value
     */
    public static String decimalToOctal(String dec){
        return Integer.toOctalString(Integer.parseInt(dec));
    }

}
