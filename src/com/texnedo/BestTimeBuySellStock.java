package com.texnedo;

public class BestTimeBuySellStock {
    public static void main(String[] args) {
        BestTimeBuySellStock stock = new BestTimeBuySellStock();
        int[] data = {7,1,5,3,6,4};
        int[] data1 = {7,6,4,3,1};
        int[] data2 = {1,2,4,2,5,7,2,4,9,0,9};
        System.out.println(stock.maxProfit(data));
        System.out.println(stock.maxProfit(data1));
        System.out.println(stock.maxProfit(data2));
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] <= minPrice) {
                minPrice = prices[i];
            } else {
                int diff = prices[i] - minPrice;
                if (maxProfit < diff) {
                    maxProfit = diff;
                }
            }
        }
        return maxProfit;
    }

    public int maxProfit2(int[] prices) {
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

    public int maxProfitTotal(int[] prices) {
        int startIndex = -1;
        int endIndex = -1;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (startIndex == -1) {
                if (i < prices.length - 1 && prices[i + 1] > prices[i]) {
                    startIndex = i;
                }
            } else if (endIndex == -1) {
                if (prices[i] > prices[startIndex]) {
                    endIndex = i;
                }
            } else {
                if (prices[i] > prices[endIndex]) {
                    endIndex = i;
                } else if (prices[i] <= prices[startIndex]) {
                    profit += prices[endIndex] - prices[startIndex];
                    endIndex = -1;
                    startIndex = -1;
                }
            }
        }
        if (startIndex != -1 && endIndex != -1) {
            profit += prices[endIndex] - prices[startIndex];
        }
        return profit;
    }
}
