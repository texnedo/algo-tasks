package com.texnedo;

import java.util.Arrays;

public class TaskScheduler {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        //char[] tasks = {'A','A','A','B','B','B'};
        char[] tasks = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        System.out.println(scheduler.leastInterval(tasks, 2));
    }

    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            int index = tasks[i] - 'A';
            counts[index]++;
        }
        int coolDownCount = 0;
        while (true) {
            int unique = 0;
            int nextUnique = 0;
            Arrays.sort(counts);
            System.out.println(Arrays.toString(counts));
            for (int i = counts.length - 1; i >=0 ; i--) {
                if (counts[i] != 0) {
                    unique++;
                    counts[i]--;
                    if (unique > n) {
                        break;
                    }
                    if (counts[i] != 0) {
                        nextUnique++;
                    }
                } else {
                    break;
                }
            }
            if (nextUnique == 0) {
                break;
            }
            int sequence = unique - 1;
            int diff = n > sequence ? n - sequence : 0;
            coolDownCount += diff;
        }
        return tasks.length + coolDownCount;
    }

    public int leastInterval2(char[] tasks, int n) {
        int[] counts = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            int index = tasks[i] - 'A';
            counts[index]++;
        }
        int coolDownCount = 0;
        while (true) {
            int unique = 0;
            int nextUnique = 0;
            Arrays.sort(counts);
            System.out.println(Arrays.toString(counts));
            for (int i = counts.length - 1; i >=0 ; i--) {
                if (counts[i] != 0) {
                    unique++;
                    counts[i]--;
                    if (counts[i] != 0) {
                        nextUnique++;
                    }
                } else {
                    break;
                }
            }
            if (nextUnique == 0) {
                break;
            }
            int sequence = unique - 1;
            int diff = n > sequence ? n - sequence : 0;
            coolDownCount += diff;
        }
        return tasks.length + coolDownCount;
    }
}
