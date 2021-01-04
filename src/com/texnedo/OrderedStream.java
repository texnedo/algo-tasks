package com.texnedo;

import java.util.ArrayList;
import java.util.List;

public class OrderedStream {
    private final String[] data;
    private int currentIndex = 1;
    private int maxIndex = 1;


    public OrderedStream(int n) {
        data = new String[n];
    }

    public List<String> insert(int id, String value) {
        if (id <= 0 || id > data.length || data[id - 1] != null) {
            throw new IllegalArgumentException();
        }
        data[id - 1] = value;
        maxIndex = Math.max(id, maxIndex);
        List<String> result = new ArrayList<>();
        int offset = 0;
        for (int i = currentIndex; i <= maxIndex; i++) {
            if (data[i - 1] == null) {
                break;
            }
            result.add(data[i - 1]);
            offset++;
        }
        if (currentIndex == id) {
            currentIndex += offset;
        }
        return result;
    }

    public static void main(String[] args) {
        OrderedStream os = new OrderedStream(5);
        System.out.println(os.insert(3, "ccccc")); // Inserts (3, "ccccc"), returns [].
        System.out.println(os.insert(1, "aaaaa")); // Inserts (1, "aaaaa"), returns ["aaaaa"].
        System.out.println(os.insert(2, "bbbbb")); // Inserts (2, "bbbbb"), returns ["bbbbb", "ccccc"].
        System.out.println(os.insert(5, "eeeee")); // Inserts (5, "eeeee"), returns [].
        System.out.println(os.insert(4, "ddddd")); // Inserts (4, "ddddd"), returns ["ddddd", "eeeee"].
    }
}
