package com.texnedo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShortestDistanceToCharacter {

    public static void main(String[] args) {
        ShortestDistanceToCharacter distance = new ShortestDistanceToCharacter();
        System.out.println(Arrays.toString(distance.shortestToChar("loveleetcode", 'e')));
    }

    public int[] shortestToChar(String S, char C) {
        int[] result = new int[S.length()];
        LinkedList<Integer> positions = new LinkedList<>();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C) {
                positions.offer(i);
            }
        }
        for (int i = 0; i < S.length(); i++) {
            Integer pos = positions.peekFirst();
            if (i < pos) {
                result[i] = pos - i;
            } else if (positions.size() > 1) {
                int leftPos = positions.pollFirst();
                int rightPos = positions.peekFirst();
                if (Math.abs(i - leftPos) < Math.abs(i - rightPos)) {
                    positions.offerFirst(leftPos);
                    result[i] = i - leftPos;
                } else {
                    result[i] = rightPos - i;
                }
            } else {
                result[i] = i - pos;
            }
        }
        return result;
    }
}
