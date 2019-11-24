package com.texnedo;

import java.util.*;

public class RangeModule {
    public static void main(String[] args) {
        //TODO - fix insertion of interval with the same point
        RangeModule module0 = new RangeModule();
        module0.addRange(10, 20);
        module0.print();
        module0.removeRange(14, 16);
        module0.print();
        System.out.println(module0.queryRange(10, 14));
        System.out.println(module0.queryRange(13, 15));
        System.out.println(module0.queryRange(16, 17));
        System.out.println(module0.queryRange(16, 20));
        System.out.println(module0.queryRange(16, 19));
        module0.addRange(20, 22);
        module0.print();

        System.out.println();

        RangeModule module = new RangeModule();
        module.addRange(0, 10);
        module.print();
        module.addRange(15, 20);
        module.print();
        module.addRange(-1, 30);
        module.print();
        module.addRange(40, 50);
        module.print();
        module.addRange(0, 45);
        module.print();
        module.removeRange(10, 20);
        module.print();
        module.removeRange(30, 40);
        module.print();
        module.removeRange(25, 35);
        module.print();
        module.removeRange(-5, 0);
        module.print();
        module.removeRange(45, 55);
        module.print();
        System.out.println(module.queryRange(4, 5));
        System.out.println(module.queryRange(-20, 5));
        System.out.println(module.queryRange(7, 11));
    }

    final TreeSet<Boundary> points = new TreeSet<>(new Comparator<Boundary>() {
        @Override
        public int compare(Boundary o1, Boundary o2) {
            return Integer.compare(o1.value, o2.value);
        }
    });

    public RangeModule() {

    }

    public void addRange(int left, int right) {
        final Boundary leftB = new Boundary(true, left);
        final Boundary rightB = new Boundary(false, right);
        final NavigableSet<Boundary> subSet =
                points.subSet(leftB, true, rightB, true);
        if (subSet.isEmpty()) {
            //nothing intersects with the current interval
            points.add(leftB);
            points.add(rightB);
        } else {
            subSet.clear();
            final Boundary lowerB = points.lower(leftB);
            final Boundary higherB = points.higher(rightB);
            if (lowerB == null || !lowerB.start) {
                points.add(leftB);
            }
            if (higherB == null || higherB.start) {
                points.add(rightB);
            }
        }
    }

    public boolean queryRange(int left, int right) {
        final Boundary leftB = new Boundary(true, left);
        final Boundary rightB = new Boundary(false, right);
        final Boundary lowerB = points.floor(leftB);
        final Boundary higherB = points.higher(rightB);
        if (lowerB == null || higherB == null) {
            return false;
        }
        if (!lowerB.start || higherB.start) {
            return false;
        }
        final NavigableSet<Boundary> subSet =
                points.subSet(leftB, false, rightB, true);
        return subSet.isEmpty();
    }

    public void removeRange(int left, int right) {
        //swap roles as we are going to exclude the range between
        final Boundary leftB = new Boundary(false, left);
        final Boundary rightB = new Boundary(true, right);
        final NavigableSet<Boundary> subSet =
                points.subSet(leftB, true, rightB, true);
        if (!subSet.isEmpty()) {
            subSet.clear();
        }
        final Boundary lowerB = points.lower(leftB);
        final Boundary higherB = points.higher(rightB);
        if (lowerB != null && lowerB.start) {
            points.add(leftB);
        }
        if (higherB != null && !higherB.start) {
            points.add(rightB);
        }
    }

    public void print() {
        for (Boundary boundary : points) {
            if (boundary.start) {
                System.out.print("|");
            }
            if (!boundary.start) {
                System.out.print("---");
            }
            System.out.print(boundary.value);
            if (!boundary.start) {
                System.out.print("|");
            }
        }
        System.out.println();
    }

    private static class Boundary {
        private final boolean start;
        private final int value;

        private Boundary(boolean start, int value) {
            this.start = start;
            this.value = value;
        }
    }
}
