package com.texnedo;

import java.util.Arrays;
import java.util.TreeMap;

public class ContainsDuplicateIII {
    private static class Item implements Comparable<Item> {
        private final long value;
        private final int index;

        private Item(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Item o) {
            int result = Long.compare(value, o.value);
            if (result == 0) {
                return Integer.compare(index, o.index);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        ContainsDuplicateIII duplicateIII = new ContainsDuplicateIII();
        int[] data2 = {4,1,6,3};
        System.out.println(duplicateIII.containsNearbyAlmostDuplicate(data2, 100, 1));
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Item[] data = new Item[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = new Item(nums[i], i);
        }
        Arrays.sort(data);
        int wndStart = 0;
        int wndEnd = 1;
        while (wndStart < wndEnd && wndEnd < data.length) {
            System.out.println(wndStart + " -> " + wndEnd);
            long valueDiff = Math.abs(data[wndStart].value - data[wndEnd].value);
            int indexDiff = Math.abs(data[wndStart].index - data[wndEnd].index);
            if (valueDiff <= t && indexDiff <= k) {
                return true;
            } else if (valueDiff > t && indexDiff > k) {
                if (wndEnd - wndStart > 1) {
                    wndStart++;
                    wndEnd = wndStart + 1;
                } else {
                    if (wndEnd < data.length - 1) {
                        wndEnd++;
                    } else {
                        wndStart++;
                        wndEnd = wndStart + 1;
                    }
                }
            } else {
                if (wndEnd < data.length - 1) {
                    wndEnd++;
                } else {
                    wndStart++;
                    wndEnd = wndStart + 1;
                }
            }
        }
        return false;
    }
}
