package com.texnedo;

import java.util.Arrays;

public class MaxProfit {
    public static void main(String[] args) {
        MaxProfit profit = new MaxProfit();
        int[] prices = {1,2};
        System.out.println(profit.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[] maximums = new int[prices.length];
        maximums[prices.length - 1] = prices[prices.length - 1];
        for (int  i = prices.length - 2; i >= 0; i--) {
            maximums[i] = Math.max(maximums[i + 1], prices[i]);
        }
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            int profit = maximums[i] - prices[i];
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }
}
