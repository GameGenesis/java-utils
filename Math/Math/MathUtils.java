package Math;

import java.util.ArrayList;
import java.util.List;

public class MathUtils {
    public static boolean isPrime(int number) {
        if (number <= 1)
            return false;

        for (int i = 2; i < number; i++) {
            if (number % i == 0)
                return false;
        }

        return true;
    }

    public static List<Integer> getPrimeNumbers(int upperBound) {
        List<Integer> primeList = new ArrayList<Integer>();

        for (int i = 2; i <= upperBound; i++) {
            if(isPrime(i)) {
                primeList.add(i);
            }
        }

        return primeList;
    }
}
