package com.texnedo;

import com.texnedo.utils.Interval;

import java.util.*;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * find the minimum number of conference rooms required.
 * */
public class MeetingRooms2 {
    public static void main(String[] args) {
        MeetingRooms2 rooms = new MeetingRooms2();
        System.out.println(rooms.minRoomsRequired(
                Interval.parse("[ [100, 140],  ...  [  0, 30  ], ... [ 20, 40  ]  .., ... [45, 50] ..., [21, 23], [22, 27]]"))
        );
    }

    static class PositionHolder {
        final Interval interval;
        final int point;
        final boolean isStart;

        PositionHolder(Interval interval, int point, boolean isStart) {
            this.interval = interval;
            this.point = point;
            this.isStart = isStart;
        }
    }

    public int minRoomsRequired(List<Interval> intervals) {
        List<PositionHolder> holders = new ArrayList<>(intervals.size() * 2);
        for (Interval interval : intervals) {
            holders.add(new PositionHolder(interval, interval.start, true));
            holders.add(new PositionHolder(interval, interval.end, false));
        }
        holders.sort(Comparator.comparingInt(o -> o.point));
        Stack<PositionHolder> parallelIntervals = new Stack<>();
        int maxParallel = 0;
        for (PositionHolder holder : holders) {
            if (holder.isStart) {
                parallelIntervals.push(holder);
            } else  {
                parallelIntervals.pop();
            }
            if (maxParallel < parallelIntervals.size()) {
                maxParallel = parallelIntervals.size();
            }
        }
        return maxParallel;
    }
}
