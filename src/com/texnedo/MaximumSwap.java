package com.texnedo;

import jnr.ffi.annotations.In;

import java.util.*;

public class MaximumSwap {
    public static void main(String[] args) {
        MaximumSwap swap = new MaximumSwap();
        int[] data = swap.parseInteger(1993);
        System.out.println(Arrays.toString(data));
        System.out.println(swap.buildInteger(data));
        //
        System.out.println(swap.maximumSwapFast(1993));
        System.out.println(swap.maximumSwapFast(98368));
        System.out.println(swap.maximumSwapFast(23424));
        System.out.println(swap.maximumSwapFast(23));
        System.out.println(swap.maximumSwapFast(32));
        System.out.println(swap.maximumSwapFast(0));
        System.out.println(swap.maximumSwapFast(1000));
        //
        System.out.println(swap.maximumSwap(1993));
        System.out.println(swap.maximumSwap(98368));
        System.out.println(swap.maximumSwap(23424));
        System.out.println(swap.maximumSwap(23));
        System.out.println(swap.maximumSwap(32));
        System.out.println(swap.maximumSwap(0));
        System.out.println(swap.maximumSwap(1000));
    }

    public int maximumSwap(int num) {
        StringBuilder strNum = new StringBuilder(String.valueOf(num));
        int max = num;
        for (int i = 0; i < strNum.length(); i++) {
            for (int j = i + 1; j < strNum.length(); j++) {
                char buffer = strNum.charAt(i);
                strNum.setCharAt(i, strNum.charAt(j));
                strNum.setCharAt(j, buffer);
                int value = Integer.parseInt(strNum.toString());
                if (value > max) {
                    max = value;
                }
                buffer = strNum.charAt(j);
                strNum.setCharAt(j, strNum.charAt(i));
                strNum.setCharAt(i, buffer);
            }
        }
        return max;
    }

    private int[] parseInteger(int num) {
        int countDigits = 0;
        int copyNum = num;
        while (copyNum != 0) {
            countDigits++;
            copyNum = copyNum / 10;
        }
        copyNum = num;
        int[] result = new int[countDigits];
        while (copyNum != 0) {
            countDigits--;
            result[countDigits] = copyNum % 10;
            copyNum = copyNum / 10;
        }
        return result;
    }

    private int buildInteger(int[] parsed) {
        int result = 0;
        int multiplicator = 1;
        for (int i = parsed.length - 1; i >= 0; i--) {
            result += parsed[i] * multiplicator;
            multiplicator *= 10;
        }
        return result;
    }

    public int maximumSwapFast(int num) {
        int[] numData = parseInteger(num);
        LinkedList<Integer>[] numbers = new LinkedList[10];
        int maxIndex = -1;
        for (int i = 0; i < numData.length; i++) {
            int current = numData[i];
            if (numbers[current] == null) {
                numbers[current] = new LinkedList<>();
            }
            numbers[current].add(i);
            if (current > maxIndex) {
                maxIndex = current;
            }
        }
        for (int i = 0; i < numData.length; i++) {
            int current = numData[i];
            if (current != maxIndex) {
                int buffer = numData[i];
                numData[i] = maxIndex;
                numData[numbers[maxIndex].getLast()] = buffer;
                break;
            }
            numbers[maxIndex].removeFirst();
            if (numbers[maxIndex].isEmpty() && maxIndex > 0) {
                maxIndex--;
                while (maxIndex > 0 && numbers[maxIndex] == null) {
                    maxIndex--;
                }
            }
        }
        return buildInteger(numData);
    }

    public int maximumMediumSwap(int num) {
        StringBuilder strNum = new StringBuilder(String.valueOf(num));
        LinkedList<Integer>[] numbers = new LinkedList[10];
        int maxIndex = -1;
        for (int i = 0; i < strNum.length(); i++) {
            int current = Character.getNumericValue(strNum.charAt(i));
            if (numbers[current] == null) {
                numbers[current] = new LinkedList<>();
            }
            numbers[current].add(i);
            if (current > maxIndex) {
                maxIndex = current;
            }
        }
        for (int i = 0; i < strNum.length(); i++) {
            int current = Character.getNumericValue(strNum.charAt(i));
            if (current != maxIndex) {
                char buffer = strNum.charAt(i);
                strNum.setCharAt(i, Character.forDigit(maxIndex, 10));
                strNum.setCharAt(numbers[maxIndex].getLast(), buffer);
                break;
            }
            numbers[maxIndex].removeFirst();
            if (numbers[maxIndex].isEmpty() && maxIndex > 0) {
                maxIndex--;
                while (maxIndex > 0 && numbers[maxIndex] == null) {
                    maxIndex--;
                }
            }
        }
        return Integer.parseInt(strNum.toString());
    }
}
