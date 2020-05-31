package com.texnedo;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointsOrigin {
    public static void main(String[] args) {
        KClosestPointsOrigin closestPointsOrigin = new KClosestPointsOrigin();
        int[][] data = {{3,3},{5,-1},{-2,4}};
        System.out.println(Arrays.toString(closestPointsOrigin.kClosest(data, 2)));
    }

    private static class Point implements Comparable<Point> {
        final int index;
        final double distance;

        private Point(int index, int[] point) {
            this.index = index;
            this.distance = Math.sqrt(point[0] * point[0] + point[1] * point[1]);
        }

        @Override
        public int compareTo(Point o) {
            return Double.compare(distance, o.distance);
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> queue = new PriorityQueue<>();
        for (int i = 0; i < points.length; i++) {
            queue.add(new Point(i, points[i]));
        }
        int[][] result = new int[K][2];
        for (int i = 0; i < result.length; i++) {
            if (queue.isEmpty()) {
                throw new IllegalArgumentException();
            }
            result[i] = points[queue.poll().index];
        }
        return result;
    }
}
