package com.texnedo;

import com.texnedo.utils.Interval;

import java.util.*;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * find the minimum number of conference rooms required.
 * */
public class MeetingRooms {
    public static void main(String[] args) {
        MeetingRooms rooms = new MeetingRooms();
        System.out.println(rooms.canAttendMeetings(
                Interval.parse("[ [100, 140],  ...  [  0, 30  ], ... [ 35, 40  ]  .., ... [45, 50] ... ]"))
        );
    }

    public boolean canAttendMeetings(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(o -> o.start));
        final ListIterator<Interval> iterator = intervals.listIterator();
        while (iterator.hasNext()) {
            final Interval current = iterator.next();
            if (!iterator.hasNext()) {
                return true;
            }
            final Interval next = iterator.next();
            if (current.end >= next.start) {
                return false;
            }
        }
        return true;
    }
}
