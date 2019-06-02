package com.texnedo.architecture;

import com.texnedo.utils.TextUtils;

import java.util.*;

/**
 * Implements logic to compute splitting intervals for AB test logic. Any message has an id
 * and a current rollout percentage. Backend logic may use some kind of strategy
 * (for instance rest from division: hash(user_id) % 100) to get user's rollout value.
 * This manager helps to compute rollout ranges to split application's auditory between AB messages.
 * Current logic tries to allocate rollout ranges without intersection when it is possible.
 *
 * A
 * 10
 * |-----A ----|---------------------------------------------#--------------------------------------------|
 * B
 * 10
 * |-----A ----|-----B----|----------------------------------------#---------------------------------------|
 * C
 * 10
 * |-----A ----|-----B----|-----C----|-----------------------------------#----------------------------------|
 *
 * */
public class IntervalManager {
    private static final int MAX_TOTAL_ROLLOUT = 100;
    private static final int MIN_TOTAL_ROLLOUT = 0;
    private static final Comparator<RolloutInterval> SIZE_COMPARATOR
            = new IntervalSizeComparator();
    private static final Comparator<RolloutInterval> ORDER_COMPARATOR
            = new IntervalOrderComparator();

    private int totalRollout = MIN_TOTAL_ROLLOUT;
    private final HashMap<String, MessageRollout> rolloutDict = new HashMap<>();
    private final PriorityQueue<RolloutInterval> availableIntervals
            = new PriorityQueue<>(SIZE_COMPARATOR);

    public IntervalManager() {
        availableIntervals.add(new RolloutInterval(MIN_TOTAL_ROLLOUT, MAX_TOTAL_ROLLOUT));
    }

    /**
     * Sets up current rollout for message
     * @param messageId message unique id
     * @param rollout current rollout value
     * */
    public boolean setMessageRollout(String messageId, int rollout) {
        if (TextUtils.isEmpty(messageId) || rollout < 0) {
            throw new IllegalArgumentException();
        }
        MessageRollout messageRollout = rolloutDict.get(messageId);
        if (messageRollout == null) {
            messageRollout = new MessageRollout(messageId);
            rolloutDict.put(messageId, messageRollout);
        }
        final int currentRollout = messageRollout.getRollout();
        if (rollout == currentRollout) {
           return false;
        } else if (currentRollout < rollout) {
            final int diff = rollout - currentRollout;
            final List<RolloutInterval> allocated = allocateFromAvailable(diff);
            if (allocated.isEmpty()) {
                return false;
            }
            messageRollout.attach(allocated);
            totalRollout += diff;
            return true;
        } else {
            if (rollout == 0) {
                availableIntervals.addAll(messageRollout.detachAll());
                totalRollout -= currentRollout;
            } else {
               availableIntervals.addAll(messageRollout.detachToRollout(rollout));
               totalRollout -= (currentRollout - rollout);
            }
            return true;
        }
    }

    /**
     * Allows to batch message rollout updates
     * @param messages map with message id and message rollout value
     * */
    public boolean setMessageRollouts(Map<String, Integer> messages) {
        if (messages == null) {
            throw new IllegalArgumentException();
        }
        if (messages.isEmpty()) {
            return false;
        }
        for (Map.Entry<String, Integer> item : messages.entrySet()) {
            if (!setMessageRollout(item.getKey(), item.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return current rollouts across all messages
     * */
    public Iterable<MessageRollout> getMessageRollouts() {
        return rolloutDict.values();
    }

    /**
     * @return total rollouts across all messages
     * */
    public int getTotalRollout() {
        return totalRollout;
    }

    /**
     * @return available rollout
     * */
    public int getAvailableRollout() {
        return MAX_TOTAL_ROLLOUT - totalRollout;
    }

    /**
     * Prints all rollouts ordered by range to std out
     * */
    public void printAllRollouts() {
        final ArrayList<RolloutInterval> toPrint = new ArrayList<>();
        final HashMap<RolloutInterval, String> map = new HashMap<>();
        for (MessageRollout rollout : getMessageRollouts()) {
            for (RolloutInterval interval : rollout.getIntervals()) {
                map.put(interval, rollout.id);
                toPrint.add(interval);
            }
        }
        for (RolloutInterval interval : availableIntervals) {
            toPrint.add(interval);
            map.put(interval, "#");
        }
        toPrint.sort(ORDER_COMPARATOR);
        System.out.print("|");
        for (RolloutInterval interval : toPrint) {
            final int size = interval.getSize();
            final int middle = size / 2;
            for (int i = 0; i < size; i++) {
                if (i == middle) {
                    System.out.print(map.get(interval));
                } else {
                    System.out.print("-");
                }
            }
            System.out.print("|");
        }
        System.out.println();
    }

    private List<RolloutInterval> allocateFromAvailable(int rolloutValue) {
        if (rolloutValue <= 0) {
            throw new IllegalArgumentException();
        }
        final int availableRollout = getAvailableRollout();
        if (availableRollout < rolloutValue) {
            System.out.println(String.format(Locale.US,"can't allocate rollout: %d (available: %d)",
                    rolloutValue, availableRollout));
            return Collections.emptyList();
        }
        int toAllocate = rolloutValue;
        final LinkedList<RolloutInterval> result = new LinkedList<>();
        while (toAllocate > 0 && !availableIntervals.isEmpty()) {
            final RolloutInterval interval = availableIntervals.peek();
            final int diff = interval.getSize() - toAllocate;
            RolloutInterval allocated;
            if (diff > 0) {
                allocated = interval.sliceFromStart(toAllocate);
                toAllocate = 0;
            } else {
                allocated = availableIntervals.poll();
                if (allocated == null) {
                    break;
                }
                toAllocate -= allocated.getSize();
            }
            result.add(allocated);
        }
        if (toAllocate != 0) {
            System.out.println(String.format(Locale.US,"can't allocate rollout: %d (available: %d), rest: %d",
                    rolloutValue, availableRollout, toAllocate));
            return Collections.emptyList();
        }
        return result;
    }

    @Override
    public String toString() {
        return "IntervalManager{\n" +
                "   totalRollout=" + totalRollout +
                "   ,\n availableIntervals=" + availableIntervals +
                "   ,\n intervals=" + rolloutDict +
                "\n}";
    }

    public static class RolloutInterval {
        private int start;
        private int end;

        RolloutInterval(int startRollout, int endRollout) {
            if (startRollout >= endRollout) {
                throw new IllegalArgumentException();
            }
            this.start = startRollout;
            this.end = endRollout;
        }

        public int getSize() {
            return end - start;
        }

        void attach(RolloutInterval source) {
            if (source == null) {
                throw new IllegalArgumentException();
            }
            final int previousEnd = end;
            end += source.getSize();
            System.out.println(String.format(Locale.US, "attached to end (%d, %d) -> (%d, %d)",
                    start, previousEnd, start, end));
            source.detach();
        }

        RolloutInterval sliceFromEnd(int diff) {
            if (diff <= 0) {
                throw new IllegalArgumentException();
            }
            if (diff >= getSize()) {
                throw new IllegalArgumentException();
            }
            final int previousEnd = end;
            end -= diff;
            System.out.println(String.format(Locale.US, "sliced from end (%d, %d) -> (%d, %d)",
                    start, previousEnd, start, end));
            return new RolloutInterval(end, previousEnd);
        }

        RolloutInterval sliceFromStart(int diff) {
            if (diff <= 0) {
                throw new IllegalArgumentException();
            }
            if (diff >= getSize()) {
                throw new IllegalArgumentException();
            }
            final int previousStart = start;
            start += diff;
            System.out.println(String.format(Locale.US, "sliced from start (%d, %d) -> (%d, %d)",
                    previousStart, end, start, end));
            return new RolloutInterval(previousStart, start);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RolloutInterval that = (RolloutInterval) o;
            return start == that.start &&
                    end == that.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public String toString() {
            return String.format(Locale.US, "(%d, %d)", start, end);
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        private void detach() {
            System.out.println(String.format(Locale.US, "detached (%d, %d)", start, end));
            start = 0;
            end = 0;
        }
    }

    public static class MessageRollout {
        public final String id;
        private final PriorityQueue<RolloutInterval> intervals = new PriorityQueue<>(SIZE_COMPARATOR);

        MessageRollout(String id) {
            this.id = id;
        }

        void attach(List<RolloutInterval> toAttach) {
            System.out.println("before attach: " + toString());
            intervals.addAll(toAttach);
            System.out.println("after attach: " + toString());
        }

        List<RolloutInterval> detachAll() {
            System.out.println("before detach all: " + toString());
            final ArrayList<RolloutInterval> detached = new ArrayList<>(intervals.size());
            while (!intervals.isEmpty()) {
                detached.add(intervals.poll());
            }
            return detached;
        }

        List<RolloutInterval> detachToRollout(int rollout) {
            if (rollout <= 0) {
                throw new IllegalArgumentException();
            }
            final int currentRollout = getRollout();
            if (rollout >= currentRollout) {
                throw new IllegalArgumentException();
            }
            System.out.println(
                    String.format(Locale.US, "before detach to %d: %s", rollout, toString())
            );
            int diff = currentRollout - rollout;
            final List<RolloutInterval> result = new LinkedList<>();
            while (diff != 0) {
                final RolloutInterval interval = intervals.poll();
                if (interval == null) {
                    throw new IllegalStateException();
                }
                if (interval.getSize() <= diff) {
                    diff -= interval.getSize();
                    result.add(interval);
                } else {
                    final RolloutInterval sliced = interval.sliceFromEnd(diff);
                    diff -= sliced.getSize();
                    result.add(sliced);
                    intervals.add(interval);
                }
            }
            System.out.println(
                    String.format(Locale.US, "after detach to %d: %s", rollout, toString())
            );
            return result;
        }

        public Iterable<RolloutInterval> getIntervals() {
            return intervals;
        }

        public int getRollout() {
            int rollout = 0;
            for (RolloutInterval interval : intervals) {
                rollout += interval.getSize();
            }
            return rollout;
        }

        @Override
        public String toString() {
            return "Rollout{" +
                    "id='" + id + '\'' +
                    ", intervals=" + intervals +
                    '}';
        }
    }

    public static class IntervalOrderComparator implements Comparator<RolloutInterval> {
        @Override
        public int compare(RolloutInterval o1, RolloutInterval o2) {
            return Integer.compare(o1.start, o2.start);
        }
    }

    public static class IntervalSizeComparator implements Comparator<RolloutInterval> {
        @Override
        public int compare(RolloutInterval o1, RolloutInterval o2) {
            return -Integer.compare(o1.getSize(), o2.getSize());
        }
    }
}
