package com.texnedo;

import java.util.Arrays;
import java.util.Comparator;

public class CarFleet {
    public static void main(String[] args) {
        int[] position = {10,8,0,5,3};
        int[] speed = {2,4,1,1,3};
        CarFleet fleet = new CarFleet();
        //System.out.println(fleet.carFleet(12, position, speed));
        int[] position1 = {0,4,2};
        int[] speed1 = {2,1,3};
        System.out.println(fleet.carFleet(10, position1, speed1));
    }

    public int carFleet(int target, int[] position, int[] speed) {
        if (target < 0) {
            throw new IllegalArgumentException();
        }
        if (position == null || speed == null) {
            throw new IllegalArgumentException();
        }
        if (position.length != speed.length) {
            throw new IllegalArgumentException();
        }
        Car[] cars = new Car[position.length];
        boolean[] joined = new boolean[position.length];
        for (int i = 0; i < position.length; i++) {
            cars[i] = new Car(position[i], speed[i]);
        }
        Arrays.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return Integer.compare(o2.speed, o1.speed);
            }
        });
        int fleetCount = position.length;
        for (int i = 0; i < position.length; i++) {
            if (joined[i]) {
                continue;
            }
            for (int j = i + 1; j < position.length; j++) {
                if (joined[j]) {
                    continue;
                }
                boolean canBeCaughtUp = canCatchUp(
                        target,
                        cars[i].position, cars[i].speed,
                        cars[j].position, cars[j].speed
                );
                joined[j] = canBeCaughtUp;
                if (canBeCaughtUp) {
                    fleetCount--;
                }
            }
        }
        return fleetCount;
    }

    private static class Car {
        final int position;
        final int speed;

        private Car(int position, int speed) {
            this.position = position;
            this.speed = speed;
        }
    }

    private static boolean canCatchUp(int target, int position1, int speed1,
                                      int position2, int speed2) {
        double sDiff = speed1 - speed2;
        double pDiff = position2 - position1;
        double time = pDiff / sDiff;
        if (time < 0 || Math.floor(time) != time) {
            return false;
        }
        double meet1 = speed1 * time + position1;
        double meet2 = speed2 * time + position2;
        if (meet1 <= target && meet2 <= target) {
            return true;
        }
        return false;
    }
}
