package com.texnedo;

public class UglyNumber {
    public static void main(String[] args) {
        UglyNumber nums = new UglyNumber();
        System.out.println(nums.isUgly(-2147483648));
    }

    public boolean isUgly(int num) {
        System.out.println(num);
        if (num == 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        if (num % 2 == 0) {
            return isUgly(num / 2);
        }
        if (num % 3 == 0) {
            return isUgly(num / 3);
        }
        if (num % 5 == 0) {
            return isUgly(num / 5);
        }
        return false;
    }

}
