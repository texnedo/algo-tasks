package com.texnedo;

import java.util.*;

public class CountIslands {
    private final int[][] matrix;
    private int count = 0;

    public CountIslands(int[][] matrix) {
        this.matrix = matrix;
    }

    public static void main(String[] args) {
        final int[][] matrix =
                {
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                };
        final CountIslands countIslands = new CountIslands(matrix);
        countIslands.addIsland(1, 2);
        countIslands.addIsland(1, 3);
        countIslands.addIsland(3, 3);
        countIslands.addIsland(2, 3);
        countIslands.addIsland(3, 0);
        countIslands.addIsland(2, 1);
        countIslands.addIsland(2, 2);
        countIslands.addIsland(0, 0);
    }

    public int addIsland(int i, int j) {
        final int change = getIslandCountChange(i, j);
        matrix[i][j] = 1;
        count += change;
        printMap();
        printStats(change);
        return count;
    }

    public int getCount() {
        return count;
    }

    private void printMap() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private void printStats(int change) {
        System.out.println(String.format(Locale.US, "Island count: %d (change: %d)", count, change));
    }

    private int getIslandCountChange(int i, int j) {
        final Point point = new Point(i, j);
        final List<Point> neighbours = getUnvisited(point, Collections.emptySet());
        if (neighbours.isEmpty()) {
            return 1;
        }
        if (neighbours.size() == 1) {
            return 0;
        }
        if (neighbours.size() > 4) {
            throw new IllegalArgumentException();
        }
        int maxChange = -(neighbours.size() - 1);
        while (neighbours.size() > 0) {
            final Point last = neighbours.remove(neighbours.size() - 1);
            final List<Point> connected = findConnectedIslands(last, neighbours);
            if (connected.size() == neighbours.size()) {
                break;
            }
            neighbours.removeAll(connected);
            maxChange += connected.size();
        }
        return maxChange;
    }

    private List<Point> findConnectedIslands(Point start,
                                             List<Point> targets) {
        final List<Point> result = new ArrayList<>(4);
        final Set<Point> visited = new HashSet<>();
        final List<Point> children = new LinkedList<>();
        children.add(start);
        while (!children.isEmpty() && !targets.isEmpty()) {
            List<Point> nextLevel = new LinkedList<>();
            for (Point child : children) {
                List<Point> toRemove = new ArrayList<>();
                for (Point target : targets) {
                    if (isConnected(child, target)) {
                        result.add(target);
                        toRemove.add(target);
                    }
                }
                for(Point point : toRemove) {
                    targets.remove(point);
                }
                nextLevel.addAll(getUnvisited(child, visited));
                visited.add(child);
            }
            children.clear();
            children.addAll(nextLevel);
        }
        return result;
    }

    private boolean isConnected(Point l, Point r) {
        if (l.i == r.i && Math.abs(l.j - r.j) == 1) {
            return true;
        } else if (l.j == r.j && Math.abs(l.i - r.i) == 1) {
            return true;
        }
        return false;
    }

    private List<Point> getUnvisited(Point p, Set<Point> visited) {
        final List<Point> result = new ArrayList<>(4);
        if (p.i > 0) {
            final Point pUp = new Point(p.i - 1, p.j);
            if (matrix[pUp.i][pUp.j] == 1 && !visited.contains(pUp)) {
                result.add(pUp);
            }
        }
        if (p.i < matrix.length - 1) {
            final Point pDown = new Point(p.i + 1, p.j);
            if (matrix[pDown.i][pDown.j] == 1 && !visited.contains(pDown)) {
                result.add(pDown);
            }
        }
        if (p.j > 0) {
            final Point pLeft = new Point(p.i, p.j - 1);
            if (matrix[pLeft.i][pLeft.j] == 1 && !visited.contains(pLeft)) {
                result.add(pLeft);
            }
        }
        if (p.j < matrix[p.i].length - 1) {
            final Point pRight = new Point(p.i, p.j + 1);
            if (matrix[pRight.i][pRight.j] == 1 && !visited.contains(pRight)) {
                result.add(pRight);
            }
        }
        return result;
    }

    private static class Point {
        final int i;
        final int j;

        Point(int i, int j) {
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

        @Override
        public String toString() {
            return String.format(Locale.US, "(%d, %d)", i, j);
        }
    }
}
