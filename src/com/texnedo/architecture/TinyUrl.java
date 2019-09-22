package com.texnedo.architecture;

import java.util.ArrayList;

public class TinyUrl {
    private static final String SHORT_URL_HOST = "http://texnedo.com/";
    private final ArrayList<Record> records = new ArrayList<>();

    public String getShortUrl(final String originalUrl) {
        final int currentId = records.size();
        final String stringId = convertIdToString(currentId);
        records.add(new Record(originalUrl));
        return SHORT_URL_HOST + stringId;
    }

    public String getOriginalUrl(final String shortUrl) {
        if (!shortUrl.startsWith(SHORT_URL_HOST) ||
                SHORT_URL_HOST.length() == shortUrl.length()) {
            throw new IllegalArgumentException();
        }
        final String stringId = shortUrl.substring(SHORT_URL_HOST.length());
        final long currentId = convertStringToId(stringId);
        if (currentId < 0 || currentId >= records.size()) {
            throw new IllegalArgumentException();
        }
        final Record record = records.get((int)currentId);
        if (record == null) {
            throw new IllegalArgumentException();
        }
        return record.originalUrl;
    }

    private static class Record {
        final String originalUrl;
        final long timestamp;

        private Record(String originalUrl) {
            this.originalUrl = originalUrl;
            this.timestamp = System.currentTimeMillis();
        }
    }

    public static long convertStringToId(final String url) {
        long id = 0;
        for (int i = 0; i < url.length(); i++) {
            final char current = url.charAt(i);
            if (current >= 'a' && current <= 'z') {
                //no shift
                id = id * 62 + current - 'a';
            } else if (current >= 'A' && current <= 'Z') {
                //add shift for a-z
                id = id * 62 + current - 'A' + 26;
            } else if (current >= '0' && current <= '9') {
                //add shift for a-zA-Z
                id = id * 62 + current - '0' + 52;
            } else {
                throw new IllegalArgumentException();
            }
        }
        return id;
    }

    public static String convertIdToString(final long id) {
        long idToConvert = id;
        final StringBuilder builder = new StringBuilder(32);
        while (idToConvert >= 0) {
            int value = (int)(idToConvert % 62);
            char toAppend;
            if (value >= 0 && value < 26) {
                toAppend = (char)('a' + value);
            } else if (value >= 26 && value < 52) {
                toAppend = (char)('A' + value - 26);
            } else if (value >= 52 && value < 62) {
                toAppend = (char)('0' + value - 52);
            } else {
                throw new IllegalArgumentException();
            }
            builder.append(toAppend);
            idToConvert /= 62;
            if (idToConvert == 0) {
                break;
            }
        }
        builder.reverse();
        return builder.toString();
    }
}
