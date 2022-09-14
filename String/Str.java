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

        String alignment = "";
        String fixedPoint = "";
        String number = "";
        String percent = "";
        boolean currency = false;
        boolean tempC = false;
        boolean tempF = false;

        Matcher m = Pattern.compile("\\{(.*?)\\d+(.*?)\\}").matcher(string);
        while (m.find()) {
            String match = m.group();

            alignment = "";
            Matcher am = Pattern.compile("\\,\s?\\-?\\d+").matcher(match);
            while (am.find())
                alignment = am.group();

            fixedPoint = "";
            Matcher dm = Pattern.compile("\\:\s?[fF](\\d+)?").matcher(match);
            while (dm.find())
                fixedPoint = dm.group();

            number = "";
            Matcher nm = Pattern.compile("\\:\s?[nN](\\d+)?").matcher(match);
            while (nm.find())
                number = nm.group();

            percent = "";
            Matcher pm = Pattern.compile("\\:\s?[pP](\\d+)?|%").matcher(match);
            while (pm.find())
                percent = pm.group();
            percent = percent.replace("%", ":P1");
            
            currency = Pattern.compile("\\:\s?[cC]|\\$").matcher(match).find();
            tempC = Pattern.compile("\\:\s?[tT][cC]").matcher(match).find();
            tempF = Pattern.compile("\\:\s?[tT][fF]").matcher(match).find();

            String updatedMatch = match.replace(alignment, "").replace(fixedPoint, "").replace(percent, "");
            int index = Integer.parseInt(updatedMatch.replaceAll("[^0-9]", ""));
            String value = args[index].toString();

            if (fixedPoint != "") {
                fixedPoint = fixedPoint.replaceAll("[^0-9]", "");
                if (fixedPoint == "")
                    fixedPoint = "2";
                String newFloat = String.format("%." + fixedPoint + "f", Float.parseFloat(value));
                value = value.replace(value.replaceAll("\\s+",""), newFloat);
            }

            else if (number != "") {
                number = number.replaceAll("[^0-9]", "");
                if (number == "")
                    number = "2";
                String newFloat = String.format("%,." + number + "f", Double.parseDouble(value));
                value = value.replace(value.replaceAll("\\s+",""), newFloat);
            }

            else if (percent != "") {
                NumberFormat formatter = NumberFormat.getPercentInstance();
                percent = percent.replaceAll("[^0-9]", "");
                if (percent == "")
                    percent = "1";
                    formatter.setMinimumFractionDigits(Integer.parseInt(percent));
                value = formatter.format(Float.parseFloat(value));
            }

            else if (currency) {
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                value = formatter.format(Float.parseFloat(value));
            }

            else if (tempC)
                value += " °C";
            else if (tempF)
                value += " °F";

            if (alignment != "") {
                String alignmentSign = alignment.contains("-") ? "-" : "";
                alignment = alignment.replaceAll("[^0-9]", "");
                value = String.format("%" + alignmentSign + alignment + "s", value);
            }
        
            newString = newString.replace(match, value);
        }

        return newString;
    }
}