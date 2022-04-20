package com.texnedo;

import java.util.*;

public class CountSort {
    private static class DataHolder {
        final String text;
        final int index;

        private DataHolder(String text, int index) {
            this.text = text;
            this.index = index;
        }
    }

    public static void countSort(List<List<String>> arr) {
        @SuppressWarnings("rawtypes")
        List[] hash = new List[100];
        for (int i = 0; i < arr.size(); i++) {
            List<String> item = arr.get(i);
            int key = Integer.parseInt(item.get(0));
            String value = item.get(1);
            DataHolder holder = new DataHolder(value, i);
            @SuppressWarnings("unchecked")
            List<DataHolder> list = (List<DataHolder>) hash[key];
            if (list == null) {
                list = new LinkedList<>();
                hash[key] = list;
            }
            list.add(holder);
        }
        boolean isFirst = true;
        for (int i = 0; i < hash.length; i++) {
            @SuppressWarnings("unchecked")
            List<DataHolder> list = (List<DataHolder>) hash[i];
            if (list != null) {
                for (DataHolder holder : list) {
                    if (!isFirst) {
                        System.out.print(" ");
                    }
                    if (holder.index < arr.size() / 2) {
                        System.out.print("-");
                    } else {
                        System.out.print(holder.text);
                    }
                    isFirst = false;
                }
            }
        }
    }
}
