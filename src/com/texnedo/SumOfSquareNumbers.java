package com.texnedo;

public class SumOfSquareNumbers {
    public static void main(String[] args) {
        SumOfSquareNumbers nums = new SumOfSquareNumbers();
        System.out.println(nums.judgeSquareSum(0));
    }

    public boolean judgeSquareSum(int c) {
        double max = Math.sqrt(c);
        for (int i = 0; i <= max; i++) {
            int square = i * i;
            int diff = c - square;
            double res = Math.floor(Math.sqrt(diff));
            if ((res * res) == diff) {
                return true;
            }
        }
        return false;
    }
}
