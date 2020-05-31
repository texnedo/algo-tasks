package com.texnedo;

import java.util.LinkedList;
import java.util.Queue;

public class NumberRecentCalls {
    class RecentCounter {
        private final Queue<Integer> pingTimes = new LinkedList<>();

        public RecentCounter() {

        }

        public int ping(int t) {
            while (!pingTimes.isEmpty()) {
                int first = pingTimes.peek();
                int diff = t - first;
                if (diff <= 3000) {
                    break;
                }
                pingTimes.poll();
            }
            pingTimes.offer(t);
            return pingTimes.size();
        }
    }
}
