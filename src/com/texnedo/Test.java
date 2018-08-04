package com.texnedo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Test {
    class Payment {
        int src;
        int dst;
        int value;
    }

    class Result {
        int src;
        int debt;
    }

    Payment[] splitBills(int[] amounts) {
        int total = 0;
        for (int i = 0; i < amounts.length; i++) {
            total += amounts[i];
        }
        int price = total / amounts.length;
        Result[] results = new Result[amounts.length];
        for (int i = 0; i < results.length; i++) {
            Result result = new Result();
            result.debt = price - amounts[i];
            result.src = i;
            results[i] = result;
        }
        List<Payment> payments = new LinkedList<>();
        for (int i = 0; i < results.length; i++) {
            if (results[0].debt == 0) {
                return payments;
            }
            Arrays.sort(results, new Comparator<Result>() {
                @Override
                public int compare(Result o1, Result o2) {
                    return Integer.compare(o1.debt, o2.debt);
                }
            });
             int closest = -results[i].debt;
             int index = Arrays.binarySearch(results, closest, new Comparator<Result>() {
                 @Override
                 public int compare(Result o1, Result o2) {
                     return 0;
                 }
             });
             if (index > 0) {
                 Payment payment = new Payment();
                 if (results[i].debt < 0) {
                     payment.value = closest;
                     payment.dst = results[index].src;
                     payment.src = results[i].src;
                 } else {
                     payment.value = -closest;
                     payment.src = results[index].src;
                     payment.dst = results[i].src;
                 }
                 results[i].debt = 0;
                 results[index].debt = 0;
             } else {
                 index = -index;
                 if (results[index].debt == 0) {
                     //go to first not zero element
                 } else {
                     results[i].debt = -= results[index].debt;
                     results[index].debt = 0;
                 }
             }
        }

    }

    public static void main(String[] args) {

    }
}
