package com.texnedo;

public class SplitStringBalancedStrings {
    public int balancedStringSplit(String s) {
        int rCount = 0;
        int lCount = 0;
        int equalCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == 'L') {
                lCount++;
            }
            if (current == 'R') {
                rCount++;
            }
            if (rCount == lCount) {
                equalCount++;
                rCount = 0;
                lCount = 0;
            }
        }
        return equalCount;
    }
}
