package String;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormat {
    public static String fmt(String string, Object... args) {
        String newString = string;

        String decimal = "";
        Matcher m = Pattern.compile("\\{\\d(.*?)\\}").matcher(string);
        while (m.find()) {
            String match = m.group();

            decimal = "";
            Matcher dm = Pattern.compile(".\\df").matcher(match);
            while (dm.find())
                decimal = dm.group();

            int index = Integer.parseInt(match.replace(decimal, "").replaceAll("[^0-9]", ""));
            String value = args[index].toString();

            if (decimal != "")
                value = String.format("%" + decimal, Float.parseFloat(value));

            newString = newString.replace(match, value);
        }

        return newString;
    }
}