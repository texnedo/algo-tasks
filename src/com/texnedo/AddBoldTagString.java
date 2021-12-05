package com.texnedo;

import java.util.*;

public class AddBoldTagString {
    private static final int OPEN_TAG = 0;
    private static final int CLOSE_TAG = 1;
    private static final String OPEN_BOLD_TAG = "<b>";
    private static final String CLOSE_BOLD_TAG = "</b>";

    public static void main(String[] args) {
        AddBoldTagString bold = new AddBoldTagString();
        String[] words1 = new String[] {"abc","123"};
        System.out.println(bold.addBoldTag("abcxyz123", words1));
        String[] words2 = new String[] {"abc","bcxy"};
        System.out.println(bold.addBoldTag("abcxyz123", words2));
        String[] words3 = new String[] {"abc","xyz"};
        System.out.println(bold.addBoldTag("abcxyz123", words3));
    }

    public String addBoldTag(String s, String[] words) {
        List<int[]> tags = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            int index = 0;
            while (index >= 0) {
                index = s.indexOf(words[i], index);
                if (index >= 0) {
                    int[] openTag = {index, OPEN_TAG};
                    int[] closeTag = {index + words[i].length(), CLOSE_TAG};
                    tags.add(openTag);
                    tags.add(closeTag);
                } else {
                    break;
                }
                index += words[i].length();
            }
        }
        tags.sort(Comparator.comparingInt((int[] o) -> o[0]).thenComparingInt(o -> o[1]));
        List<int[]> mergedTags = new ArrayList<>(tags.size());
        Stack<int[]> tagStack = new Stack<>();
        for (int[] tag : tags) {
            if (tag[1] == OPEN_TAG) {
                tagStack.push(tag);
            } else {
                if (tagStack.isEmpty()) {
                    throw new IllegalArgumentException();
                }
                int[] lastOpenTag = tagStack.pop();
                if (tagStack.isEmpty()) {
                    mergedTags.add(lastOpenTag);
                    mergedTags.add(tag);
                }
            }
        }
        StringBuilder result = new StringBuilder(s);
        int offset = 0;
        for (int[] tag : mergedTags) {
            if (tag[1] == OPEN_TAG) {
                result.insert(tag[0] + offset, OPEN_BOLD_TAG);
                offset += OPEN_BOLD_TAG.length();
            } else {
                result.insert(tag[0] + offset, CLOSE_BOLD_TAG);
                offset += CLOSE_BOLD_TAG.length();
            }
        }
        return result.toString();
    }
}
