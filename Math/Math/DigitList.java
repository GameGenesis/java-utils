package Math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DigitList {

    private List<Integer> digits;

    public DigitList(int number) {
        setNumber(number);
    }

    public void setNumber(int number) {
        digits = new ArrayList<>();

        while (number > 0) {
            digits.add(number % 10);
            number /= 10;
        }

        Collections.reverse(digits);
    } 

    public List<Integer> getDigits() {
        return digits;
    }

    public int get(int index) {
        return digits.get(index);
    }

    public void set(int index, int digit) {
        digits.set(index, digit);
    }

    public String toString() {
        return digits.toString();
    } 
}