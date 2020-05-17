package com.texnedo;

import java.util.*;

public class ColorfulNumbers {
    public static void main(String[] args) {
        ColorfulNumbers numbers = new ColorfulNumbers();
        System.out.println(numbers.getColorFul(1234));
        System.out.println(numbers.getColorFul(3245));
    }

    /**
     * https://algorithms.tutorialhorizon.com/colorful-numbers/
     * */
    public boolean getColorFul(int number) {
        HashSet<Long> products = new HashSet<>();
        List<Long> digits = getDigits(number);
        List<Long> topLevel = new ArrayList<>(digits.size());
        topLevel.addAll(digits);
        List<Long> level = new ArrayList<>(topLevel.size() - 1);
        for (int i = 0; i < topLevel.size() - 1; i++) {
            level.add(topLevel.get(i));
        }
        for (int length = 1; length <= digits.size() - 1; length++) {
            List<Long> nextLevel = new ArrayList<>(level.size() - 1);
            for (int i = 0; i < level.size() && i + length < topLevel.size(); i++) {
                long product = level.get(i) * topLevel.get(i + length);
                System.out.println(
                        String.format(Locale.US,
                                "%d x %d = %d", level.get(i), topLevel.get(i + length), product));
                if (products.contains(product)) {
                    return false;
                }
                products.add(product);
                nextLevel.add(product);
            }
            level = nextLevel;
        }
        return true;
    }

    private List<Long> getDigits(int number) {
        LinkedList<Long> result = new LinkedList<>();
        while (number > 0) {
            long last = number % 10;
            result.addFirst(last);
            number = number / 10;
        }
        return result;
    }
}
