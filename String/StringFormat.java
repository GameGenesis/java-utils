package String;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormat {
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
            Matcher dm = Pattern.compile(".\\df").matcher(match);
            while (dm.find())
                decimal = dm.group();
            
            currency = Pattern.compile("\\:[cC]|\\$").matcher(match).find();
            tempC = Pattern.compile("\\:[tT][cC]").matcher(match).find();
            tempF = Pattern.compile("\\:[tT][fF]").matcher(match).find();

            int index = Integer.parseInt(match.replace(decimal, "").replaceAll("[^0-9]", ""));
            String value = args[index].toString();

            if (decimal != "")
                value = String.format("%" + decimal, Float.parseFloat(value));

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