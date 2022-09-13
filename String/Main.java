package String;

public class Main {
    public static String name = "John Smith";
    public static int age = 24;
    public static float temperature = 100;
    public static double percent = 0.34786;

    public static void main(String[] args) {
        // name = Str.input("Enter your name: ");
        Str.printf("|{0, 61}|", age);
        Str.print(Str.fmt("My name is {0} and I am {1:F3} or {1:f} years old. I have {$1} or {1:C}", name, age));
        Str.printf("The temperature is {0:tc} or {1:TF}", temperature, temperature * (9f/5f) + 32f);
        Str.printf("The percent is {0:P1} or {0:p}", percent);
        Str.print("This", "is", "an", "example", "of", "the", "print", "method");
        Str.print(", ".toCharArray(), ".".toCharArray(), "Element0", "Element1", "Element2");
    }
}