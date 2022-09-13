package String;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Str {
    private static Scanner scanner = new Scanner(System.in);

    public static String inputf(String message, Object... args) {
        printf(" ".toCharArray(), "".toCharArray(), message, args);
        return input();
    }

    public static String input(String message) {
        print(" ".toCharArray(), "".toCharArray(), message);
        return input();
    }

    public static String input() {
        return scanner.nextLine();
    }

    public static void printf(String string, Object... args) {
        print(fmt(string, args));
    }

    public static void printf(char[] separationChars, char[] endChars, String string, Object... args) {
        print(separationChars, endChars, fmt(string, args));
    }

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
            Matcher dm = Pattern.compile("\\:[fF]\\d?").matcher(match);
            while (dm.find())
                decimal = dm.group();

            percent = "";
            Matcher pm = Pattern.compile("\\:[pP]\\d?|%").matcher(match);
            while (pm.find())
                percent = pm.group();
            percent = percent.replace("%", ":P1");
            
            currency = Pattern.compile("\\:[cC]|\\$").matcher(match).find();
            tempC = Pattern.compile("\\:[tT][cC]").matcher(match).find();
            tempF = Pattern.compile("\\:[tT][fF]").matcher(match).find();

            String updatedMatch = match.replace(decimal, "").replace(percent, "");
            int index = Integer.parseInt(updatedMatch.replaceAll("[^0-9]", ""));
            String value = args[index].toString();

            if (decimal != "") {
                decimal = decimal.replaceAll("[^0-9]", "");
                if (decimal == "")
                    decimal = "1";
                value = String.format("%." + decimal + "f", Float.parseFloat(value));
            }

            else if (percent != "") {
                NumberFormat defaultFormat = NumberFormat.getPercentInstance();
                percent = percent.replaceAll("[^0-9]", "");
                if (percent == "")
                    percent = "1";
		        defaultFormat.setMinimumFractionDigits(Integer.parseInt(percent));
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