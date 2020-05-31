package com.texnedo.architecture;

public interface TimeMap {
    void set(String key, String value, int timestamp);

    String get(String key, int timestamp);

    void clear();
}
