package com.texnedo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class FindMedianFromDataStream {
    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        finder.addNum(3);
        finder.addNum(4);
        finder.addNum(5);
        System.out.println(finder.findMedian());

        MedianFinder finder1 = new MedianFinder();
        finder1.addNum(1);
        finder1.addNum(2);
        System.out.println(finder1.findMedian());

        MedianFinder finder2 = new MedianFinder();
        finder2.addNum(5);
        finder2.addNum(4);
        finder2.addNum(3);
        finder2.addNum(2);
        finder2.addNum(1);
        System.out.println(finder2.findMedian());

        MedianFinder finderRnd = new MedianFinder();
        Random rndNumber = new Random();
        int N = rndNumber.nextInt(100);
        int[] data = new int[N];
        for (int i = 0; i < data.length; i++) {
            data[i] = rndNumber.nextInt();
            finderRnd.addNum(data[i]);
        }
        Arrays.sort(data);
        //0 1 2 3 4
        //1 2 3 4 5
        double testMedian;
        if (N % 2 == 0) {
            testMedian = (data[data.length / 2 - 1] + data[data.length / 2]) / 2.0;
        } else {
            testMedian = data[data.length / 2];
        }
        System.out.println(Arrays.toString(data));
        System.out.println(testMedian);
        System.out.println(finderRnd.findMedian());
    }

    static class MedianFinder {
        private final PriorityQueue<Integer> low;
        private final PriorityQueue<Integer> high;

        public MedianFinder() {
            low = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return -Integer.compare(o1, o2);
                }
            });
            high = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o1, o2);
                }
            });
        }

        public void addNum(int num) {
            //keep same size and all in low < high
            if (low.size() == 0 && high.size() == 0) {
                low.add(num);
            } else {
                Integer lowTop = low.peek();
                Integer highBottom = high.peek();
                if (lowTop == null) {
                    throw new IllegalStateException();
                }
                if (highBottom == null) {
                    if (lowTop <= num) {
                        high.add(num);
                    } else {
                        high.add(low.poll());
                        low.add(num);
                    }
                } else {
                    if (num >= highBottom) {
                        high.add(num);
                    } else {
                        low.add(num);
                    }
                }
            }
            int diff = low.size() - high.size();
            if (diff > 1) {
                high.add(low.poll());
            } else if (diff < -1) {
                low.add(high.poll());
            }
        }

        public double findMedian() {
            if (low.size() == 0 && high.size() == 0) {
                throw new IllegalStateException();
            }
            if (low.size() == high.size()) {
                //noinspection ConstantConditions
                return (low.peek() + high.peek()) / 2.0;
            }
            if (low.size() > high.size()) {
                //noinspection ConstantConditions
                return low.peek();
            } else {
                //noinspection ConstantConditions
                return high.peek();
            }
        }
    }
}
