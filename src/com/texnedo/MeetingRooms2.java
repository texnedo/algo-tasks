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
        int[][] data = {{13,15},{1,13}};
        System.out.println(rooms.minRoomsRequired2(data));
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

    static class PositionHolder2 {
        final int[] interval;
        final int point;
        final boolean isStart;

        PositionHolder2(int[] interval, int point, boolean isStart) {
            this.interval = interval;
            this.point = point;
            this.isStart = isStart;
        }
    }

    public int minRoomsRequired2(int[][] intervals) {
        List<PositionHolder2> holders = new ArrayList<>(intervals.length * 2);
        for (int i = 0; i < intervals.length; i++) {
            holders.add(new PositionHolder2(intervals[i], intervals[i][0], true));
            holders.add(new PositionHolder2(intervals[i], intervals[i][1], false));
        }
        holders.sort(new Comparator<PositionHolder2>() {
            @Override
            public int compare(PositionHolder2 o1, PositionHolder2 o2) {
                int result = Integer.compare(o1.point, o2.point);
                if (result != 0) {
                    return result;
                }
                return Boolean.compare(o1.isStart, o2.isStart);
            }
        });
        Stack<PositionHolder2> parallelIntervals = new Stack<>();
        int maxParallel = 0;
        for (PositionHolder2 holder : holders) {
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
