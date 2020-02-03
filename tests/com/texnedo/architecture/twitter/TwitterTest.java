package com.texnedo.architecture.twitter;

import org.junit.Test;

import static org.junit.Assert.*;

public class TwitterTest {
    @Test
    public void postTweet() {
        Twitter twitter = new Twitter();
        twitter.postTweet(1,5);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1));
    }

    @Test
    public void postTweet1() {
        Twitter twitter = new Twitter();
        twitter.postTweet(1,1);
        System.out.println(twitter.getNewsFeed(1));
        twitter.follow(2, 1);
        System.out.println(twitter.getNewsFeed(2));
    }

    @Test
    public void postTweet2() {
        Twitter twitter = new Twitter();
        twitter.postTweet(1,5);
        twitter.postTweet(1,3);
        System.out.println(twitter.getNewsFeed(1));
    }
}