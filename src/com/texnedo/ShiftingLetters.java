package com.texnedo;

public class ShiftingLetters {
    private static final int alphabetSize = 'z' - 'a';

    public static void main(String[] args) {
        ShiftingLetters letters = new ShiftingLetters();
        int[] shifts = {3,5,9};
        System.out.println(letters.shiftingLetters("abc", shifts));
    }

    public String shiftingLetters(String S, int[] shifts) {
        int totalSum = 0;
        for (int i = 0; i < shifts.length; i++) {
            totalSum += shifts[i];
        }
        int offset = 0;
        final StringBuffer buffer = new StringBuffer(S.length());
        for (int i = 0; i < S.length(); i++) {
            char current = S.charAt(i);
            int shift = totalSum - offset;
            buffer.append(shiftLetter(current, shift));
            offset += shifts[i];
        }
        return buffer.toString();
    }

    public static char shiftLetter(char letter, int shift) {
        int realShift = shift % alphabetSize;
        int shifted = letter + realShift;
        if (shifted <= 'z') {
            return (char)shifted;
        }
        int offset = shifted - 'z' - 1;
        int wrappedResult = 'a' + offset;
        return (char)wrappedResult;
    }
}
