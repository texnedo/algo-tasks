package com.texnedo.architecture.twitter;

import java.util.*;

class Twitter {
    private static final int MAX_FEED_SIZE = 10;
    private final HashMap<Integer, HashSet<Integer>> followers = new HashMap<>();
    private final HashMap<Integer, TreeSet<Tweet>> tweets = new HashMap<>();

    /** Initialize your data structure here. */
    public Twitter() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        final TreeSet<Tweet> existing = tweets.computeIfAbsent(userId, k -> new TreeSet<>());
        existing.add(new Tweet(userId, tweetId));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        final HashSet<Integer> existing = followers.get(userId);
        final TreeSet<Tweet> selfTweets = tweets.get(userId);
        final ArrayList<Integer> result = new ArrayList<>(10);
        if (existing == null || existing.isEmpty()) {
            //no followers
            if (selfTweets == null || selfTweets.isEmpty()) {
                return Collections.emptyList();
            }
            for (Tweet tweet : selfTweets) {
                result.add(tweet.getTweetId());
                if (result.size() == MAX_FEED_SIZE) {
                    break;
                }
            }
            return result;
        }
        List<Iterator<Tweet>> sources = new ArrayList<>(followers.size() + 1);
        if (selfTweets != null) {
            sources.add(selfTweets.iterator());
        }
        for (Integer followeeId : existing) {
            final TreeSet<Tweet> followeeTweets = tweets.get(followeeId);
            if (followeeTweets != null && !followeeTweets.isEmpty()) {
                sources.add(followeeTweets.iterator());
            }
        }
        Tweet[] latestTweets = new Tweet[sources.size()];
        while (result.size() < MAX_FEED_SIZE) {
            Tweet latestTweet = null;
            int latestTweetIndex = -1;
            for (int i = 0; i < sources.size(); i++) {
                final Iterator<Tweet> current = sources.get(i);
                if (latestTweets[i] == null && current.hasNext()) {
                    latestTweets[i] = current.next();
                }
                if (latestTweets[i] != null &&
                        (latestTweet == null || latestTweet.compareTo(latestTweets[i]) > 0)) {
                    latestTweet = latestTweets[i];
                    latestTweetIndex = i;
                }
            }
            if (latestTweet == null) {
                break;
            }
            latestTweets[latestTweetIndex] = null;
            result.add(latestTweet.getTweetId());
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followeeId == followerId) {
            return;
        }
        final HashSet<Integer> existing = followers.computeIfAbsent(followerId, k -> new HashSet<>());
        existing.add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followeeId == followerId) {
            return;
        }
        final HashSet<Integer> existing = followers.get(followerId);
        if (existing == null) {
            return;
        }
        existing.remove(followeeId);
    }
}
