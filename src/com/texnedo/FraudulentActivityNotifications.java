package com.texnedo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FraudulentActivityNotifications {
    public static void main(String[] args) {
        System.out.println(activityNotifications(Arrays.asList(2, 3, 4, 2, 3, 6, 8, 4, 5), 5));
        System.out.println(activityNotifications(Arrays.asList(10, 20, 30, 40, 50), 3));
        System.out.println(activityNotifications(Arrays.asList(1, 2, 3, 4, 4), 4));
    }

    public static int activityNotifications(List<Integer> expenditure, int d) {
        if (d == 0 || d == 1) {
            return 0;
        }
        ArrayList<Integer> window = new ArrayList<>(d);
        int notificationCount = 0;
        for (int i = 0; i < expenditure.size(); i++) {
            Integer current = expenditure.get(i);
            if (window.size() == d) {
                double median = getMedian(window);
                if (current >= median * 2.0) {
                    notificationCount++;
                }
            }
            int indexToAdd = Collections.binarySearch(window, current);
            if (indexToAdd >= 0) {
                window.add(indexToAdd + 1, current);
            } else {
                indexToAdd = -indexToAdd - 1;
                window.add(indexToAdd, current);
            }
            if (window.size() > d) {
                Integer toRemove = expenditure.get(i - d);
                int index = Collections.binarySearch(window, toRemove);
                if (index < 0) {
                    throw new IllegalStateException();
                }
                window.remove(index);
            }
        }
        return notificationCount;
    }

    private static double getMedian(ArrayList<Integer> window) {
        double median;
        int middle = window.size() / 2;
        if (window.size() % 2 == 0) {
            median = (window.get(middle) + window.get(middle - 1)) / 2.0;
        } else {
            median = window.get(middle);
        }
        return median;
    }
}
