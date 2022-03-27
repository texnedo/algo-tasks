package com.texnedo;

public class KeysKeyboard {
    public int minSteps(int n) {
        return minStepsInternal(n, 1, 0);
    }

    private int minStepsInternal(int n, int currentLength, int bufferLength) {
        if (n == currentLength) {
            return 0;
        }
        if (bufferLength == 0) {
            return minStepsInternal(n, currentLength, 1) + 1;
        }
        int operationsWithPaste =
                currentLength + bufferLength <= n ?
                        minStepsInternal(n, currentLength + bufferLength, 1) + 1 : 0;
        int operationsWithCopy =
                currentLength != bufferLength ?
                        minStepsInternal(n, currentLength, currentLength) + 1 : 0;
        int minSteps;
        if (operationsWithPaste > 0 && operationsWithCopy > 0) {
            minSteps = Math.min(operationsWithCopy, operationsWithPaste);
        } else {
            minSteps = Math.max(operationsWithCopy, operationsWithPaste);
        }
        System.out.println(minSteps);
        return minSteps;
    }

    public static void main(String[] args) {
        KeysKeyboard keyboard = new KeysKeyboard();
        System.out.println(keyboard.minSteps(30));
    }
}
