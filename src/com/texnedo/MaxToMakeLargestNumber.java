package com.texnedo;

import java.util.Arrays;
import java.util.Comparator;

public class MaxToMakeLargestNumber {

    public static void main(String[] args) {
        MaxToMakeLargestNumber makeLargestNumber = new MaxToMakeLargestNumber();
        int[] nums = {824,938,1399,5607,6973,5703,9609,4398,8247};
        System.out.println(makeLargestNumber.largestNumber(nums));
    }

    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            strs[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equals(o2)) {
                    return 0;
                }
                String sum1 = o1 + o2;
                String sum2 = o2 + o1;
                if (Long.parseLong(sum1) > Long.parseLong(sum2)) {
                    return -1;
                }
                return 1;
            }
        });
        boolean allZeros = true;
        for (int i = 0; i < nums.length; i++) {
            builder.append(strs[i]);
            if (allZeros && !strs[i].equals("0")) {
                allZeros = false;
            }
        }
        if (allZeros) {
            return "0";
        }
        return builder.toString();
    }
}
