package com.texnedo;

public class PartitionArrayIntoDisjointIntervals {
    public static void main(String[] args) {
        PartitionArrayIntoDisjointIntervals partition =
                new PartitionArrayIntoDisjointIntervals();
        int[] data = {0, 1, 3, 2, 4, 3, 2, 5, 6, 7};
        System.out.println(partition.partitionDisjoint(data));
    }

    public int partitionDisjoint(int[] A) {
        int maxLeft = A[0];
        int maxAfterRaise = Integer.MIN_VALUE;
        int startRaiseIndex = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > maxLeft && startRaiseIndex == 0) {
                startRaiseIndex = i;
            }
            if (A[i] > maxAfterRaise && startRaiseIndex != 0) {
                maxAfterRaise = A[i];
            }
            if (A[i] < maxLeft && maxLeft < maxAfterRaise) {
                maxLeft = maxAfterRaise;
                startRaiseIndex = 0;
            }
        }
        return startRaiseIndex;
    }
}
