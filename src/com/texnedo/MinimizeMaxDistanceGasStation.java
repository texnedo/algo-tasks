package com.texnedo;

import java.util.PriorityQueue;

public class MinimizeMaxDistanceGasStation {

    public static void main(String[] args) {
        MinimizeMaxDistanceGasStation gas = new MinimizeMaxDistanceGasStation();
        double[] data = {1, 5, 10};
        System.out.println(gas.getMinDistance(data, 50));
    }

    public static class Interval implements Comparable<Interval> {
        public final double start;
        public final double stop;

        public Interval(double start, double stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Interval o) {
            double length = stop - start;
            double lengthO = o.stop - o.start;
            return Double.compare(lengthO, length);
        }
    }

    public double getMinDistance(double[] stations, int newStations) {
        PriorityQueue<Interval> intervals = new PriorityQueue<>();
        for (int i = 1; i < stations.length; i++) {
            Interval interval = new Interval(stations[i - 1], stations[i]);
            intervals.offer(interval);
        }
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < newStations; i++) {
            Interval biggest = intervals.poll();
            if (intervals.peek() == null) {
                return minDistance;
            }
            double middle = biggest.start + (biggest.stop - biggest.start) / 2;
            intervals.offer(new Interval(biggest.start, middle));
            intervals.offer(new Interval(middle, biggest.stop));
            double distance = Math.min(middle - biggest.start, biggest.stop - middle);
            if (minDistance > distance) {
                minDistance = distance;
            }
        }
        return minDistance;
    }
}
