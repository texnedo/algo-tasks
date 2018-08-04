package com.texnedo;

import java.util.Arrays;

public class GiveBackAtm {
    // Imagine you are an ATM Machine
    // Euro bills/banknotes are of 2, 5, 10, 20, 50, 100 Euro value
    // The machine has the following number of those bills
    // 91 bills of value 2, 26 of value 5, ... 10 of value 100
    public static final int [] VALUES = {2, 5, 10, 20, 50, 100};
    public static final int [] COUNTS = {91, 26, 83, 58, 15, 10};
    // Write a function that similarly returns an array of counts
    // of bills that add up to the requested by user value
    // example solutions are (there can be others):
    // giveBack(75) =>  {0, 1, 0, 1, 1, 0}
    // giveBack(30) =>  {0, 6, 0, 0, 0, 0}
    // You can choose to change the structure, function names, arguments
    // if you think they will better fit your solution.
    public static int[] giveBack(int amount) throws Exception {
        int restAmount = amount;
        int [] result = new int[VALUES.length];
        for (int i = VALUES.length - 1; i >= 0; i--) {
            if (COUNTS[i] <= 0) {
                continue;
            }
            int value = VALUES[i];
            if (restAmount < value) {
                continue;
            }
            int number = restAmount / value;
            int actual = Math.min(number, COUNTS[i]);
            restAmount -= actual * value;
            result[i] = actual;
        }
        if (restAmount > 0) {
            throw new Exception();
        }
        return result;
    }

    public static class ChangeResult {
        int[] change;
        int restAmount;

        public ChangeResult(int[] change, int restAmount) {
            this.change = change;
            this.restAmount = restAmount;
        }
    }

    public static ChangeResult giveBackDp(int amount, int[] counts, int[] change, int billIndex) {
//        int restAmount = amount;
//        for (int i = billIndex; i >= 0; i--) {
//            if (counts[i] <= 0) {
//                continue;
//            }
//            int value = VALUES[i];
//            if (restAmount < value) {
//                continue;
//            }
//            if (restAmount == value) {
//                change[i]++;
//                return new ChangeResult(change, 0);
//            } else {
//                //we can give one this bill
//                //we can move next without this bill
//
//                restAmount -= value;
//                counts[i]--;
//                giveBackDp(restAmount - value, Arrays.copyOf(counts, counts.length), billIndex);
//            }
//        }
//        ChangeResult change = new ChangeResult();
//        change.change = result;
//        change.restAmount = restAmount;
//        if (restAmount == 0) {
//            return change;
//        }
//        return giveBackDp(restAmount, )
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(giveBack(75)));
        System.out.println(Arrays.toString(giveBack(30)));
        System.out.println(Arrays.toString(giveBack(8)));
    }

}
