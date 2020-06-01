package com.texnedo;

public class MinimumCostForTickets {
    public static void main(String[] args) {
        int[] days = {1,4,6,7,8,20};
        int[] costs = {2,7,15};
        MinimumCostForTickets tickets = new MinimumCostForTickets();
        System.out.println(tickets.mincostTickets(days, costs));
        int[] days1 = {1,2,3,4,5,6,7,8,9,10,30,31};
        int[] costs1 = {2,7,15};
        System.out.println(tickets.mincostTickets(days1, costs1));
    }

    /**
     * a 1-day pass is sold for costs[0] dollars;
     * a 7-day pass is sold for costs[1] dollars;
     * a 30-day pass is sold for costs[2] dollars.
     *
     * 1,3,4,5,9
     * */
    public int mincostTickets(int[] days, int[] costs) {
        int[] costCache = new int[365];
        return mincostTickets(days, costs, 0, costCache);
    }

    private int mincostTickets(int[] days, int[] costs, int dayIndex, int[] costCache) {
        if (dayIndex == days.length - 1) {
            return costs[0];
        }
        if (costCache[dayIndex] != 0) {
            return costCache[dayIndex];
        }
        int dayDiff = days[dayIndex + 1] - days[dayIndex];
        int minCostForDay;
        if (dayDiff > getTicketDays(2)) {
            int minCostTicketType = getMinCostTicketType(costs);
            int nextDayIndex = findNextUncoveredDay(days, dayIndex, minCostTicketType);
            minCostForDay = costs[minCostTicketType] +
                    mincostTickets(days, costs, nextDayIndex, costCache);
        } else {
            int nextDayIndexType0 = findNextUncoveredDay(days, dayIndex, 0);
            int nextDayIndexType1 = findNextUncoveredDay(days, dayIndex, 1);
            int nextDayIndexType2 = findNextUncoveredDay(days, dayIndex, 2);
            int costType0 = costs[0] + mincostTickets(days, costs, nextDayIndexType0, costCache);
            int costType1 = costs[1] + mincostTickets(days, costs, nextDayIndexType1, costCache);
            int costType2 = costs[2] + mincostTickets(days, costs, nextDayIndexType2, costCache);
            minCostForDay = Math.min(costType0, Math.min(costType1, costType2));
        }
        costCache[dayIndex] = minCostForDay;
        return minCostForDay;
    }

    private int getMinCostTicketType(int[] costs) {
        int minCost = Integer.MAX_VALUE;
        int minCostIndex = -1;
        for (int i = 0; i < costs.length; i++) {
            if (minCost > costs[i]) {
                minCost = costs[i];
                minCostIndex = i;
            }
        }
        return minCostIndex;
    }

    private int getTicketDays(int costType) {
        switch (costType) {
            case 0:
                return 1;
            case 1:
                return 7;
            case 2:
                return 30;
            default:
                throw new IllegalArgumentException();
        }
    }

    private int findNextUncoveredDay(int[] days, int dayIndex, int costType) {
        int maxDaysDiff = getTicketDays(costType);
        for (int i = dayIndex + 1; i < days.length; i++) {
            int dayDiff = days[i] - days[dayIndex] + 1;
            if (dayDiff > maxDaysDiff) {
                return i;
            }
        }
        return days.length - 1;
    }
}
