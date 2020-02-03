package com.texnedo.architecture.twitter;

public class Tweet implements Comparable<Tweet> {
    private final int userId;
    private final int tweetId;
    private final long ts;

    public Tweet(int userId, int tweetId) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.ts = System.nanoTime();
    }

    @Override
    public int compareTo(Tweet o) {
        return -Long.compare(ts, o.ts);
    }

    public int getUserId() {
        return userId;
    }

    public int getTweetId() {
        return tweetId;
    }
}
