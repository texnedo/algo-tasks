package com.texnedo;

import java.util.HashSet;

public class Pawn {
    public static void main(String[] args) {
        int [] arr = {0};
        System.out.println(solution(arr));
    }

    public static int solution(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        HashSet<Integer> visited = new HashSet<>();
        int position = 0;
        int countJumps = 0;
        while (true) {
            if (visited.contains(position)) {
                return -1;
            }
            visited.add(position);
            position = position + A[position];
            countJumps++;
            if (position >= A.length || position < 0) {
                break;
            }
        }
        return countJumps;
    }
}
