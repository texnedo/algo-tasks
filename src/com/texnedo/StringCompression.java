package com.texnedo;

import java.util.Arrays;

public class StringCompression {
    public static void main(String[] args) {
        StringCompression compression = new StringCompression();
        char[] data = {'a','a','b','b','c','c','c'};
        char[] data1 = {'a','b','c'};
        char[] data2 = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        char[] data3 = {'a','a','a','a','a','b'};
        int newSize = compression.compress(data2);
        System.out.println(newSize);
        for (int i = 0; i < newSize; i++) {
            System.out.print(data2[i] + " ");
        }
    }

    public int compress(char[] chars) {
        Character previous = null;
        int previousCount = 0;
        int length = 0;
        int writeStart = 0;
        for (int i = 0; i < chars.length; i++) {
            char current = chars[i];
            if (previous == null || previous == current) {
                if (previous == null) {
                    previous = current;
                }
                previousCount++;
            } else {
                if (previousCount > 0) {
                    chars[writeStart] = previous;
                    writeStart++;
                    if (previousCount > 1) {
                        String count = Integer.toString(previousCount);
                        for (int j = 0; j < count.length(); j++) {
                            chars[writeStart] = count.charAt(j);
                            writeStart++;
                        }
                        length += 1 + count.length();
                    } else {
                        length++;
                    }
                }
                previousCount = 0;
                previous = current;
                previousCount++;
            }
        }
        if (previousCount > 0) {
            chars[writeStart] = previous;
            writeStart++;
            if (previousCount > 1) {
                String count = Integer.toString(previousCount);
                for (int j = 0; j < count.length(); j++) {
                    chars[writeStart] = count.charAt(j);
                    writeStart++;
                }
                length += 1 + count.length();
            } else {
                length++;
            }
        }
        return length;
    }
}
