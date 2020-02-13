package com.texnedo;

import java.util.Arrays;

public class AssignCookies {
    public static void main(String[] args) {
        AssignCookies assignCookies = new AssignCookies();
        int[] g1 = {1,2,3};
        int[] s1 = {1,1};
        System.out.println(assignCookies.findContentChildren(g1, s1));

        int[] g2 = {1,2};
        int[] s2 = {1,2,3};
        System.out.println(assignCookies.findContentChildren(g2, s2));
    }

    public int findContentChildren(int[] g, int[] s) {
        if (g == null || g.length == 0) {
            return 0;
        }
        if (s == null || s.length == 0) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int countChildren = 0;
        int gIndex = g.length - 1, sIndex = s.length - 1;
        while (gIndex >= 0 && sIndex >= 0) {
            if (g[gIndex] <= s[sIndex]) {
                countChildren++;
                sIndex--;
            }
            gIndex--;
        }
        return countChildren;
    }
}
