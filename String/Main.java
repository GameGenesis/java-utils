package String;

public class Main {
    public static String name = "John Smith";
    public static int age = 24;
    public static double money = 100038490593.7684;
    public static float temperature = 100;
    public static double percent = 0.34786;

    public static void main(String[] args) {
        // name = Str.input("Enter your name: ");`
        Str.print(Str.toTitleCase("aNNie in the big city"));
        Str.printf("|{0,14}|", age);
        Str.printf("|{0, -14:f1}|", age);
        Str.printf("|{0:p,-14}|", age);
        Str.printf("I am {0:D} or {0:d5} or {0:e} or {0:E2} years old", age);
        Str.print(Str.fmt("My name is {0} and I am {1:F3} or {1:f} years old. I have {$1} or {1:C}. I have {2:N0}", name, age, money));
        Str.printf("The temperature is {0:tc} or {1:TF}", temperature, temperature * (9f/5f) + 32f);
        Str.printf("The percent is {0:P1} or {0:p} or {0%}", percent);
        Str.print("This", "is", "an", "example", "of", "the", "print", "method");
        Str.print(", ".toCharArray(), ".".toCharArray(), "Element0", "Element1", "Element2");
    }
}