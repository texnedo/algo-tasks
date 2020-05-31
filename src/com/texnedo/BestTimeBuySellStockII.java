package com.texnedo;

public class BestTimeBuySellStockII {
    public static void main(String[] args) {
        BestTimeBuySellStockII stockII = new BestTimeBuySellStockII();
        int[] data = {7,1,5,3,6,4};
        System.out.println(stockII.maxProfit(data));
    }

    public int maxProfit(int[] prices) {
        int profit = 0;
        Integer minPriceLocal = null;
        for (int i = 0; i < prices.length; i++) {
            if (minPriceLocal == null) {
                minPriceLocal = prices[i];
            } else if (minPriceLocal > prices[i]) {
                minPriceLocal = prices[i];
            }
            if (prices[i] > minPriceLocal) {
                boolean hasNextLowerThanThis = false;
                if (i < prices.length - 1 && prices[i + 1] < prices[i]) {
                    hasNextLowerThanThis = true;
                }
                if (i == prices.length - 1) {
                    hasNextLowerThanThis = true;
                }
                if (hasNextLowerThanThis) {
                    profit += prices[i] - minPriceLocal;
                    minPriceLocal = null;
                }
            }
        }
        return profit;
    }
}
