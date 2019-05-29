package com.texnedo.architecture;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class LogStorageSystem {
    private final TreeMap<Long, Integer> logTree = new TreeMap<>();
    private final static int[] EMPTY_RESULT = new int[0];
    public enum Grain {
        SECOND,
        MINUTE,
        HOUR,
        DAY,
        MONTH,
        YEAR
    }
    private static final DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    void put(int id, String timestamp) throws DateTimeParseException {
        final LocalDateTime date = LocalDateTime.parse(timestamp);
        logTree.put(date.toEpochSecond(ZoneOffset.UTC), id);
    }

    int[] retrieve(String from, String to, Grain grain) throws DateTimeParseException {
        final long utcFrom = stripToGrain(from, grain);
        final long utcTo = stripToGrain(to, grain);
        final SortedMap<Long, Integer> items = logTree.subMap(utcFrom, utcTo);
        if (items.isEmpty()) {
            return EMPTY_RESULT;
        }
        final int[] result = new int[items.size()];
        int index = 0;
        for (Integer id : items.values()) {
            result[index++] = id;
        }
        return result;
    }

    public void printLogItems(LocalDateTime now,
                              LocalDateTime back,
                              Grain grain) {
        System.out.println(
                String.format(Locale.US, "from: %s to: %s (grain: %s)",
                        back.format(format), now.format(format), grain.toString()
                )
        );
        System.out.println(
                Arrays.toString(
                        retrieve(back.format(format), now.format(format), grain)
                )
        );
    }

    private static long stripToGrain(String timestamp, Grain grain)
            throws DateTimeParseException {
        final LocalDateTime date = LocalDateTime.parse(timestamp);
        LocalDateTime stripped = null;
        switch (grain) {
            case SECOND: {
                stripped = date;
                break;
            }
            case MINUTE: {
                stripped = LocalDateTime.of(
                        date.getYear(), date.getMonth(),
                        date.getDayOfMonth(), date.getHour(),
                        date.getMinute(), 0
                );
                break;
            }
            case HOUR: {
                stripped = LocalDateTime.of(
                        date.getYear(), date.getMonth(),
                        date.getDayOfMonth(), date.getHour(), 0, 0
                );
                break;
            }
            case DAY: {
                stripped = LocalDateTime.of(
                        date.getYear(), date.getMonth(),
                        date.getDayOfMonth(), 0, 0, 0
                );
                break;
            }
            case MONTH: {
                stripped = LocalDateTime.of(
                        date.getYear(), date.getMonth(),
                        1, 0, 0, 0
                );
                break;
            }
            case YEAR:{
                stripped = LocalDateTime.of(
                        date.getYear(), 1,
                        1, 0, 0, 0
                );
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
        return stripped.toEpochSecond(ZoneOffset.UTC);
    }

    public static void main(String[] args) {
        final LogStorageSystem system = new LogStorageSystem();
        final Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            LocalDateTime date = LocalDateTime.of(2017 + rnd.nextInt(2), 1 + rnd.nextInt(11),
                    1 + rnd.nextInt(27), rnd.nextInt(24), rnd.nextInt(60), rnd.nextInt(60));
            system.put(i, date.format(format));
            System.out.println(date.format(format) + " => " + i);
        }
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime back5Years = now.minusMonths(10);
        system.printLogItems(now, back5Years, Grain.MINUTE);
        system.printLogItems(now, back5Years, Grain.HOUR);
        system.printLogItems(now, back5Years, Grain.DAY);
        system.printLogItems(now, back5Years, Grain.MONTH);
        system.printLogItems(now, back5Years, Grain.YEAR);
    }
}
