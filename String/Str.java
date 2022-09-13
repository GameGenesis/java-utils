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
        String percent = "";
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

            percent = "";
            Matcher pm = Pattern.compile("\\:[pP]\\d").matcher(match);
            while (pm.find())
                percent = pm.group();
            
            currency = Pattern.compile("\\:[cC]|\\$").matcher(match).find();
            tempC = Pattern.compile("\\:[tT][cC]").matcher(match).find();
            tempF = Pattern.compile("\\:[tT][fF]").matcher(match).find();

            String updatedMatch = match.replace(decimal, "").replace(percent, "");
            int index = Integer.parseInt(updatedMatch.replaceAll("[^0-9]", ""));
            String value = args[index].toString();

            if (decimal != "") {
                decimal = decimal.replace(":", ".").replace("F", "f");
                value = String.format("%" + decimal, Float.parseFloat(value));
            }

            else if (percent != "") {
                NumberFormat defaultFormat = NumberFormat.getPercentInstance();
		        defaultFormat.setMinimumFractionDigits(Integer.parseInt(percent.replaceAll("[^0-9]", "")));
                value = defaultFormat.format(Float.parseFloat(value));
            }

            else if (currency) {
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                value = formatter.format(Float.parseFloat(value));
            }

            else if (tempC)
                value += " °C";
            else if (tempF)
                value += " °F";

            newString = newString.replace(match, value);
        }

        return newString;
    }
}