package com.texnedo;

import java.util.HashMap;
import java.util.Objects;

public class UniquePaths {
    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(3, 2));
    }

    public static class Point {
        final int m;
        final int n;

        public Point(int m, int n) {
            this.m = m;
            this.n = n;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return m == point.m &&
                    n == point.n;
        }

        @Override
        public int hashCode() {
            return Objects.hash(m, n);
        }
    }

    public int uniquePaths(int m, int n) {
        return uniquePathsInternal(m - 1, n - 1, new HashMap<>());
    }

    public int uniquePathsInternal(int m, int n, HashMap<Point, Integer> computed) {
        if (m == 0 && n == 0) {
            return 1;
        }
        if (m < 0 || n < 0) {
            return 0;
        }
        Point point = new Point(m, n);
        Integer existing = computed.get(point);
        if (existing != null) {
            return existing;
        }
        int pathCount = uniquePathsInternal(m - 1, n, computed) +
                uniquePathsInternal(m, n - 1, computed);
        computed.put(point, pathCount);
        return pathCount;
    }
}
