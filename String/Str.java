package String;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Str {
    public static void print(String... values) {
        print(" ".toCharArray(), "\n".toCharArray(), values);
    }

    public static void print(char[] separationChars, char[] endChars, String... values) {
        String string = "";
        for (int i = 0; i < values.length; i++) {
            string += values[i];

            if (i < values.length - 1)
                string += fromCharArray(separationChars);
        }
        
        System.out.print(string + fromCharArray(endChars));
    }

    public static String fromCharArray(char[] charArray) {
        StringBuilder sb = new StringBuilder();
 
        for (Character ch : charArray) {
            sb.append(ch);
        }

        return sb.toString();
    }

    public static String fmt(String string, Object... args) {
        String newString = string;

        String decimal = "";
        boolean currency = false;
        boolean tempC = false;
        boolean tempF = false;

        Matcher m = Pattern.compile("\\{(.*?)\\d(.*?)\\}").matcher(string);
        while (m.find()) {
            String match = m.group();

            decimal = "";
            Matcher dm = Pattern.compile("\\:\\d[fF]").matcher(match);
            while (dm.find())
                decimal = dm.group();
            
            currency = Pattern.compile("\\:[cC]|\\$").matcher(match).find();
            tempC = Pattern.compile("\\:[tT][cC]").matcher(match).find();
            tempF = Pattern.compile("\\:[tT][fF]").matcher(match).find();

            int index = Integer.parseInt(match.replace(decimal, "").replaceAll("[^0-9]", ""));
            String value = args[index].toString();

            if (decimal != "") {
                decimal = decimal.replace(":", ".").replace("F", "f");
                value = String.format("%" + decimal, Float.parseFloat(value));
            }

            if (currency) {
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                value = formatter.format(Float.parseFloat(value));
            }

            if (tempC)
                value += " °C";
            if (tempF)
                value += " °F";

            newString = newString.replace(match, value);
        }

        return newString;
    }
}