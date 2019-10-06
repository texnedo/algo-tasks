package com.texnedo;

import java.util.Arrays;

public class FindMiddleElement {
    int findMiddleElement(int[] data) {
        int[] sorted = Arrays.copyOf(data, data.length);
        Arrays.sort(sorted);
        int maxBefore = Integer.MIN_VALUE;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == sorted[i] && data[i] > maxBefore &&
                    i != 0 && i != data.length - 1) {
                return data[i];
            }
            if (maxBefore < data[i]) {
                maxBefore = data[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindMiddleElement element = new FindMiddleElement();
        int[] data = {4, 3, 2, 7, 8, 9};
        System.out.println(element.findMiddleElement(data));
    }
}
