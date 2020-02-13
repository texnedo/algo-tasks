package com.texnedo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class VideoStitching {
    public static void main(String[] args) {
        int[][] data = {{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
        int[][] data1 = {{0,4},{2,8}};
        int[][] data2 = {{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};
        VideoStitching stitching = new VideoStitching();
        //System.out.println(stitching.videoStitching(data, 10));
        //System.out.println(stitching.videoStitching(data1, 5));
        System.out.println(stitching.videoStitching(data2, 9));
    }

    public int videoStitching(int[][] clips, int T) {
        if (clips.length == 0 || T <= 0) {
            return -1;
        }
        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1.length != o2.length) {
                    throw new IllegalArgumentException();
                }
                int diff = Integer.compare(o1[0], o2[0]);
                if (diff != 0) {
                    return diff;
                }
                return Integer.compare(o1[1], o2[1]);
            }
        });
        if (clips[0][0] != 0) {
            return -1;
        }
        if (clips[clips.length - 1][1] < T) {
            return -1;
        }
        Stack<int[]> intervalsToCover = new Stack<>();
        intervalsToCover.push(clips[0]);
        if (intervalsToCover.peek()[1] >= T) {
            return intervalsToCover.size();
        }
        for (int i = 1; i < clips.length; i++) {
            if (!hasIntersection(intervalsToCover.peek(), clips[i])) {
                return -1;
            }
            if (contains(intervalsToCover.peek(), clips[i])) {
                continue;
            }
            while (!intervalsToCover.isEmpty() && contains(clips[i], intervalsToCover.peek())) {
                intervalsToCover.pop();
            }
            if (intervalsToCover.size() >= 2) {
                int[] previous = intervalsToCover.pop();
                if (!hasIntersection(intervalsToCover.peek(), clips[i])) {
                    intervalsToCover.push(previous);
                }
            }
            intervalsToCover.push(clips[i]);
            if (intervalsToCover.peek()[1] >= T) {
                return intervalsToCover.size();
            }
        }
        if (intervalsToCover.peek()[1] >= T) {
            return intervalsToCover.size();
        }
        return -1;
    }

    private boolean hasIntersection(int[] o1, int[] o2) {
        if (o2[0] <= o1[1]) {
            return true;
        }
        return false;
    }

    private boolean contains(int[] o1, int[] o2) {
        if (o1[0] <= o2[0] && o1[1] >= o2[1]) {
            return true;
        }
        return false;
    }
}
