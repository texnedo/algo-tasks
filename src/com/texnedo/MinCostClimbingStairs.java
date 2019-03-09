package com.texnedo;

public class MinCostClimbingStairs {
    public static void main(String[] args) {
        MinCostClimbingStairs stairs = new MinCostClimbingStairs();
        int[] data = {10, 15, 20};
        int[] data1 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(stairs.minCostClimbingStairs(data));
        System.out.println(stairs.minCostClimbingStairs(data1));
    }

    public int minCostClimbingStairs(int[] cost) {
        Integer[] computed = new Integer[cost.length];
        return Math.min(
                minCostClimbingStairs(cost, computed, 0, 0),
                minCostClimbingStairs(cost, computed, 1, 0)
        );
    }

    public int minCostClimbingStairs(int[] cost, Integer[] computed, int index, int payed) {
        if (index >= cost.length) {
            return payed;
        }
        if (computed[index] != null) {
            return computed[index] + cost[index];
        }
        int result = Math.min(
                minCostClimbingStairs(cost, computed, index + 1, payed),
                minCostClimbingStairs(cost, computed, index + 2, payed)
        );
        computed[index] = result;
        return result + cost[index];
    }
}
