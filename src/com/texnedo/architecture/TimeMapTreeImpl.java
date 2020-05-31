package com.texnedo.architecture;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class TimeMapTreeImpl implements TimeMap {
    private final HashMap<String, TreeMap<Integer, String>> map = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public TimeMapTreeImpl() {
    }

    @Override
    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> times = map.computeIfAbsent(key, k -> new TreeMap<>());
        times.put(timestamp, value);
    }

    @Override
    public String get(String key, int timestamp) {
        TreeMap<Integer, String> times = map.get(key);
        if (times == null) {
            return "";
        }
        Map.Entry<Integer, String> entry = times.floorEntry(timestamp);
        if (entry == null) {
            return "";
        }
        return entry.getValue();
    }

    @Override
    public void clear() {
        map.clear();
    }
}
