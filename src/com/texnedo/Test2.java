package com.texnedo;

import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
        System.out.println(addIsland(matrix, 1, 1));
    }

    public static int addIsland(int[][] matrix, int i, int j) {
        matrix[i][j] = 1;
        return 0;
    }

    static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return i == point.i &&
                    j == point.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    public static List<Point> findConnectedIslands(int[][] matrix, Point start, List<Point> targets) {
        List<Point> result = new ArrayList<>(4);
        Set<Point> visited = new HashSet<>();
        List<Point> childs = new LinkedList<>();
        childs.add(start);
        while (true) {
            for (Point point : childs) {

            }

            List<Point> toRemove = new ArrayList<>();
            for (Point point : targets) {
                if (isConnected(start, point)) {
                    result.add(point);
                    toRemove.add(point);
                }
            }
            for(Point point : toRemove) {
                targets.remove(point);
            }
        }
    }

    public static boolean isConnected(Point l, Point r) {
        if (l.i == r.i && Math.abs(l.j - r.j) == 1) {
            return true;
        } else if (l.j == r.j && Math.abs(l.i - r.i) == 1) {
            return true;
        }
        return false;
    }

    public static List<Point> getUnvisited(int[][] matrix, Point p, Set<Point> visited) {
        List<Point> result = new ArrayList<>(4);
        Point pUp = new Point(p.i - 1, p.j);
        Point pDown = new Point(p.i + 1, p.j);
        Point pLeft = new Point(p.i, p.j - 1);
        Point pRight = new Point(p.i, p.j + 1);
        if (matrix[pUp.i][pUp.j] == 1 && !visited.contains(pUp)) {
            result.add(pUp);
        }
        if (matrix[pDown.i][pDown.j] == 1 && !visited.contains(pDown)) {
            result.add(pDown);
        }
        if (matrix[pLeft.i][pLeft.j] == 1 && !visited.contains(pUp)) {
            result.add(pLeft);
        }
        if (matrix[pRight.i][pRight.j] == 1 && !visited.contains(pRight)) {
            result.add(pRight);
        }
    }
}
