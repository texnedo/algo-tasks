package com.texnedo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingOffers {
    public static void main(String[] args) {
        ShoppingOffers offers = new ShoppingOffers();
        List<Integer> price = List.of(2,5);
        List<List<Integer>> special = List.of(List.of(3,0,5), List.of(1,2,10));
        List<Integer> needs = List.of(3,2);
        System.out.println(offers.shoppingOffers(price, special, needs));

        List<Integer> price1 = List.of(2,3,4);
        List<List<Integer>> special1 = List.of(List.of(1,1,0,4), List.of(2,2,1,9));
        List<Integer> needs1 = List.of(1,2,1);
        System.out.println(offers.shoppingOffers(price1, special1, needs1));

        List<Integer> price2 = List.of(9,9);
        List<List<Integer>> special2 = List.of(List.of(1,1,1));
        List<Integer> needs2 = List.of(2,2);
        System.out.println(offers.shoppingOffers(price2, special2, needs2));
    }

    public int shoppingOffers(List<Integer> price,
                              List<List<Integer>> special,
                              List<Integer> needs) {
        if (price == null || special == null || needs == null) {
            throw new IllegalArgumentException();
        }
        if (price.size() != needs.size()) {
            throw new IllegalArgumentException();
        }
        for (List<Integer> offer : special) {
            if (offer.size() != price.size() + 1) {
                throw new IllegalArgumentException();
            }
        }
        return shoppingOffersInternal(price, special, needs, 0);
    }

    public int shoppingOffersInternal(List<Integer> price,
                                      List<List<Integer>> special,
                                      List<Integer> needs,
                                      int alreadySpent) {
        boolean hasNeeds = false;
        for (Integer item : needs) {
            if (item > 0) {
                hasNeeds = true;
                break;
            }
        }
        if (!hasNeeds) {
            return alreadySpent;
        }
        int totalNoOffer = alreadySpent;
        for (int i = 0; i < price.size(); i++) {
            totalNoOffer += price.get(i) * needs.get(i);
        }
        int minTotalSpentWithOffers = Integer.MAX_VALUE;
        for (List<Integer> offer : special) {
            Iterator<Integer> iterator = offer.listIterator();
            boolean canUseOffer = true;
            for (int i = 0; i < needs.size(); i++) {
                int offerItem = iterator.next();
                if (needs.get(i) - offerItem < 0) {
                    canUseOffer = false;
                    break;
                }
            }
            if (canUseOffer) {
                iterator = offer.listIterator();
                List<Integer> needsLocal = new ArrayList<>(needs);
                for (int i = 0; i < needsLocal.size(); i++) {
                    int offerItem = iterator.next();
                    needsLocal.set(i, needsLocal.get(i) - offerItem);
                }
                int currentSpent =
                        shoppingOffersInternal(
                                price,
                                special,
                                needsLocal,
                                alreadySpent + iterator.next()
                        );
                if (currentSpent < minTotalSpentWithOffers) {
                    minTotalSpentWithOffers = currentSpent;
                }
            }
        }
        if (minTotalSpentWithOffers == Integer.MAX_VALUE || minTotalSpentWithOffers > totalNoOffer) {
            return totalNoOffer;
        }
        return minTotalSpentWithOffers;
    }
}
