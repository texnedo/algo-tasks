package com.texnedo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MajorityElement {

    public static void main(String[] args) {
        MajorityElement element = new MajorityElement();
        int[] nums = {0, 0, 0};
        System.out.println(element.majorityElement(nums));
    }

    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        int[] majority = new int[2];
        int[] counters = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == majority[0]) {
                counters[0]++;
            } else if (nums[i] == majority[1]){
                counters[1]++;
            } else if (counters[0] == 0) {
                majority[0] = nums[i];
                counters[0]++;
            } else if (counters[1] == 0) {
                majority[1] = nums[i];
                counters[1]++;
            } else {
                counters[0]--;
                counters[1]--;
            }
        }
        int[] check = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == majority[0] && counters[0] != 0) {
                check[0]++;
            }
            if (nums[i] == majority[1] && counters[1] != 0) {
                check[1]++;
            }
        }
        List<Integer> result = new ArrayList<>(2);
        if (check[0] > nums.length / 3) {
            result.add(majority[0]);
        }
        if (check[1] > nums.length / 3) {
            result.add(majority[1]);
        }
        return result;
    }
}
