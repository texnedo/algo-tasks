package com.texnedo;

import java.util.*;

public class AlienDictionary {
    private static class ListNode {
        final char symbol;
        ListNode next;

        private ListNode(char symbol) {
            this.symbol = symbol;
        }
    }

    private static class Segment {
        ListNode segmentHead = null;
        ListNode segmentLast = null;
        final HashMap<Character, ListNode> map = new HashMap<>();

        ListNode addNode(char symbol) {
            if (segmentHead == null) {
                segmentHead = new ListNode(symbol);
                segmentLast = segmentHead;
            } else {
                segmentLast.next = new ListNode(symbol);
                segmentLast = segmentLast.next;
            }
            map.put(symbol, segmentLast);
            return segmentLast;
        }

        boolean join(Segment segment) {
            ListNode iterator = segment.segmentHead;
            return true;
        }
    }

    public static void main(String[] args) {
        AlienDictionary dictionary = new AlienDictionary();
        String[] words = {"wrt", "wrf", "er", "etf", "rftt"};
        System.out.println(dictionary.getSorted(words));
    }

    public String getSorted(String[] dictionary) {
        List<Segment> result = getSegment(dictionary, 0, 0, dictionary.length - 1);
        HashMap<Character, List<Segment>> map = new HashMap<>();
        for (Segment segment : result) {
            for (Character item : segment.map.keySet()) {
                List<Segment> segments = map.computeIfAbsent(item, k -> new LinkedList<>());
                segments.add(segment);
            }
        }
        //TODO - complete joining all segments
        //use array with alphabet, put all segments onto it, each cell contains two links (src, dst)
        //find cell with empty src, this will be a start, then just pass through linked list to trace
        //all alphabet
        return "";
    }

    public List<Segment> getSegment(String[] dictionary,
                                     int level,
                                     int start,
                                     int end) {
        LinkedList<Segment> result = new LinkedList<>();
        Character current = null;
        Integer startSegment = null;
        Integer endSegment = null;
        Segment segment = new Segment();
        for(int i = start; i <= end; i++) {
            String word = dictionary[i];
            if (word.length() > level) {
                char item = word.charAt(level);
                if (current == null || item != current) {
                    current = item;
                    if (startSegment == null) {
                        startSegment = i;
                    } else {
                        endSegment = i - 1;
                        result.addAll(processSegment(dictionary, level, startSegment, endSegment, segment));
                        startSegment = i;
                    }
                }
            } else {
                if (startSegment != null) {
                    endSegment = i - 1;
                    result.addAll(processSegment(dictionary, level, startSegment, endSegment, segment));
                    startSegment = null;
                }
                current = null;
            }
        }
        if (startSegment != null) {
            endSegment = end;
            result.addAll(processSegment(dictionary, level, startSegment, endSegment, segment));
        }
        if (segment.segmentHead != null) {
            result.add(segment);
        }
        return result;
    }

    private List<Segment> processSegment(String[] dictionary,
                                int level,
                                Integer startSegment,
                                Integer endSegment,
                                Segment segment) {
        System.out.println(String.format("Segment (level: %d): %d - %d", level, startSegment, endSegment));
        char symbol = dictionary[startSegment].charAt(level);
        segment.addNode(symbol);
        return getSegment(dictionary, level + 1, startSegment, endSegment);
    }
}
