package com.texnedo;

import java.util.*;

public class FindRightInterval {
    public static void main(String[] args) {
        FindRightInterval rightInterval = new FindRightInterval();
        //int[][] data = {{1,4}, {2,3}, {3,4}};
        //int[][] data = {{3,4}, {2,3}, {1,2}};
        int[][] data = {{4,5},{2,3},{1,2}};
        //int[][] data = {{-100,-98},{-99,-97},{-98,-96},{-97,-95},{-96,-94},{-95,-93},{-94,-92},{-93,-91},{-92,-90},{-91,-89},{-90,-88},{-89,-87},{-88,-86},{-87,-85},{-86,-84},{-85,-83},{-84,-82},{-83,-81},{-82,-80},{-81,-79},{-80,-78},{-79,-77},{-78,-76},{-77,-75},{-76,-74},{-75,-73},{-74,-72},{-73,-71},{-72,-70},{-71,-69},{-70,-68},{-69,-67},{-68,-66},{-67,-65},{-66,-64},{-65,-63},{-64,-62},{-63,-61},{-62,-60},{-61,-59},{-60,-58},{-59,-57},{-58,-56},{-57,-55},{-56,-54},{-55,-53},{-54,-52},{-53,-51},{-52,-50},{-51,-49},{-50,-48},{-49,-47},{-48,-46},{-47,-45},{-46,-44},{-45,-43},{-44,-42},{-43,-41},{-42,-40},{-41,-39},{-40,-38},{-39,-37},{-38,-36},{-37,-35},{-36,-34},{-35,-33},{-34,-32},{-33,-31},{-32,-30},{-31,-29},{-30,-28},{-29,-27},{-28,-26},{-27,-25},{-26,-24},{-25,-23},{-24,-22},{-23,-21},{-22,-20},{-21,-19},{-20,-18},{-19,-17},{-18,-16},{-17,-15},{-16,-14},{-15,-13},{-14,-12},{-13,-11},{-12,-10},{-11,-9},{-10,-8},{-9,-7},{-8,-6},{-7,-5},{-6,-4},{-5,-3},{-4,-2},{-3,-1},{-2,0},{-1,1},{0,2},{1,3},{2,4},{3,5},{4,6},{5,7},{6,8},{7,9},{8,10},{9,11},{10,12},{11,13},{12,14},{13,15},{14,16},{15,17},{16,18},{17,19},{18,20},{19,21},{20,22},{21,23},{22,24},{23,25},{24,26},{25,27},{26,28},{27,29},{28,30},{29,31},{30,32},{31,33},{32,34},{33,35},{34,36},{35,37},{36,38},{37,39},{38,40},{39,41},{40,42},{41,43},{42,44},{43,45},{44,46},{45,47},{46,48},{47,49},{48,50},{49,51},{50,52},{51,53},{52,54},{53,55},{54,56},{55,57},{56,58},{57,59},{58,60},{59,61},{60,62},{61,63},{62,64},{63,65},{64,66},{65,67},{66,68},{67,69},{68,70},{69,71},{70,72},{71,73},{72,74},{73,75},{74,76},{75,77},{76,78},{77,79},{78,80},{79,81},{80,82},{81,83},{82,84},{83,85},{84,86},{85,87},{86,88},{87,89},{88,90},{89,91},{90,92},{91,93},{92,94},{93,95},{94,96},{95,97},{96,98},{97,99},{98,100},{99,101}};
        System.out.println(Arrays.toString(rightInterval.findRightInterval(data)));
    }

    private class Interval implements Comparable<Interval> {
        final int start;
        final int stop;
        final int index;

        private Interval(int start, int stop, int index) {
            this.start = start;
            this.stop = stop;
            this.index = index;
        }

        @Override
        public int compareTo(Interval o) {
            return Integer.compare(start, o.start);
        }
    }

    private static class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval o1, Interval o2) {
            return Integer.compare(o1.start, o2.start);
        }
    }

    public int[] findRightInterval(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            throw new IllegalArgumentException();
        }
        if (intervals.length == 1) {
            return new int[] {-1};
        }
        Interval[] intervalData = new Interval[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            intervalData[i] = new Interval(intervals[i][0], intervals[i][1], i);
        }
        Arrays.sort(intervalData);
        int[] result = new int[intervals.length];
        IntervalComparator comparator = new IntervalComparator();
        for (int i = 0; i < intervalData.length; i++) {
            Interval current = intervalData[i];
            Interval target = new Interval(current.stop, 0, 0);
            int index = Arrays.binarySearch(
                    intervalData, i,
                    intervalData.length,
                    target, comparator
            );
            if (index > 0) {
                result[current.index] = intervalData[index].index;
            } else {
                index = -index - 1;
                if (index >= intervalData.length) {
                    result[current.index] = -1;
                } else {
                    result[current.index] = intervalData[index].index;
                }
            }
        }
        return result;
    }

    public int[] findRightInterval2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            throw new IllegalArgumentException();
        }
        if (intervals.length == 1) {
            return new int[] {-1};
        }
        Interval[] intervalData = new Interval[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            intervalData[i] = new Interval(intervals[i][0], intervals[i][1], i);
        }
        Arrays.sort(intervalData);
        int[] result = new int[intervals.length];
        LinkedList<Interval> intervalStack = new LinkedList<>();
        for (int i = 0; i < intervals.length; i++) {
            Interval interval = intervalData[i];
            if (!intervalStack.isEmpty()) {
                Interval previous = intervalStack.getFirst();
                while (previous.stop <= interval.start) {
                    intervalStack.pollFirst();
                    result[previous.index] = interval.index;
                    if (intervalStack.isEmpty()) {
                        break;
                    }
                    previous = intervalStack.getFirst();
                }
            }
            if (!intervalStack.isEmpty()) {
                Interval previous = intervalStack.getLast();
                while (previous.stop <= interval.start) {
                    intervalStack.pollLast();
                    result[previous.index] = interval.index;
                    if (intervalStack.isEmpty()) {
                        break;
                    }
                    previous = intervalStack.getLast();
                }
            }
            intervalStack.offerLast(interval);
        }
        while (!intervalStack.isEmpty()) {
            result[intervalStack.pop().index] = -1;
        }
        return result;
    }
}
