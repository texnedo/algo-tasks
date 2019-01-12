package com.texnedo;

import com.texnedo.utils.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyCalendar {
    public static void main(String[] arr) {
        MyCalendar calendar = new MyCalendar();
        System.out.println(calendar.book(47, 50));
        System.out.println(calendar.book(33, 41));
        System.out.println(calendar.book(39, 45));
        System.out.println(calendar.book(33, 42));
        System.out.println(calendar.book(25, 32));
        System.out.println(calendar.book(26, 35));
    }


    private final List<Interval> intervals = new ArrayList<>();

    public MyCalendar() {
    }

    public boolean book(int start, int end) {
        Interval event = new Interval(start, end);
        if (intervals.size() == 0) {
            intervals.add(event);
            return true;
        }
        int index = Collections.binarySearch(intervals, event);
        //has exact match by start
        if (index >= 0) {
            return false;
        }
        int nextIndex = -index - 1;
        int prevIndex = nextIndex - 1;
        if (prevIndex >= 0) {
            Interval prev = intervals.get(prevIndex);
            if (prev.end > event.start) {
                //conflict with previous
                return false;
            }
        }
        if (nextIndex < intervals.size()) {
            Interval next = intervals.get(nextIndex);
            if (event.end > next.start) {
                //conflict with next
                return false;
            }
        }
        //no conflicts
        intervals.add(nextIndex, event);
        return true;
    }
}
