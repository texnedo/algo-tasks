package com.texnedo;

import java.util.*;

class Item {
    int num;
    int count;
}
class ItemComparator implements Comparator<Item> {
    @Override
    public int compare(Item l, Item r) {
        return Integer.compare(r.count, l.count);
    }
}
class TopKFrequentNumbers {
    public static void main(String[] args) {
        int [] data = {1,1,1,2,2,3,4,4,4,4,4,4,4,5,5,5,5,5};
        System.out.println(topKFrequent(data, 3));
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> dict = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer existing = dict.get(nums[i]);
            if (existing != null) {
                dict.put(nums[i], existing + 1);
            } else {
                dict.put(nums[i], 1);
            }
        }
        PriorityQueue<Item> ordered = new PriorityQueue<>(dict.size(), new ItemComparator());
        for (Map.Entry<Integer, Integer> pair : dict.entrySet()) {
            Item item = new Item();
            item.num = pair.getKey();
            item.count = pair.getValue();
            ordered.offer(item);
        }
        List<Integer> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(ordered.poll().num);
        }
        return result;
    }
}