package com.texnedo;

import java.util.Arrays;

public class ReorderDataInLogFiles {
    static class LogRecord implements Comparable<LogRecord> {
        private String id;
        private String postfix;
        private String original;
        private boolean isDigital;

        private LogRecord(String logRecord) {
            if (logRecord == null || logRecord.length() == 0) {
                throw new IllegalArgumentException();
            }
            int fistDelimiter = logRecord.indexOf(" ");
            if (fistDelimiter == -1) {
                throw new IllegalArgumentException();
            }
            id = logRecord.substring(0, fistDelimiter);
            original = logRecord;
            postfix = logRecord.substring(fistDelimiter + 1);
            isDigital = true;
            for (int i = fistDelimiter + 1; i < logRecord.length(); i++) {
                char current = logRecord.charAt(i);
                if (current != ' ' && !Character.isDigit(current)) {
                    isDigital = false;
                    break;
                }
            }
        }

        @Override
        public int compareTo(LogRecord o) {
            if (isDigital && o.isDigital) {
                return 0;
            }
            if (!isDigital && o.isDigital) {
                return -1;
            }
            if (isDigital) {
                return 1;
            }
            int result = postfix.compareTo(o.postfix);
            if (result != 0) {
                return result;
            }
            return id.compareTo(o.postfix);
        }
    }

    public String[] reorderLogFiles(String[] logs) {
        LogRecord[] records = new LogRecord[logs.length];
        for (int i = 0; i < logs.length; i++) {
            records[i] = new LogRecord(logs[i]);
        }
        Arrays.sort(records);
        String[] result = new String[logs.length];
        for (int i = 0; i < records.length; i++) {
            result[i] = records[i].original;
        }
        return result;
    }

    public static void main(String[] args) {
        String[] data = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        ReorderDataInLogFiles logs = new ReorderDataInLogFiles();
        System.out.println(Arrays.toString(logs.reorderLogFiles(data)));
    }
}
