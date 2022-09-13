package String;

public class Main {
    public static String first = "John Smith";
    public static int age = 24;
    public static float temperature = 100;

    public static void main(String[] args) {
        System.out.println(StringFormat.fmt("My name is {0} and I am {1.2f} years old. I have {$1} or {1:C}", first, age));
        System.out.println(StringFormat.fmt("The temperature is {0:tc} or {1:TF}", temperature, temperature * (9f/5f) + 32f));
    }
}
