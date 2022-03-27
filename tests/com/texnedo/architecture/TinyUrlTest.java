package com.texnedo.architecture;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

public class TinyUrlTest {

    @Test
    public void convertToInt() {
        final String url = TinyUrl.convertIdToString(123123234);
        assertEquals(TinyUrl.convertStringToId(url), 123123234);
    }

    @Test
    public void convertToIntSmall() {
        final String url = TinyUrl.convertIdToString(10);
        assertEquals(TinyUrl.convertStringToId(url), 10);
    }

    @Test
    public void testGetShortUrl() {
        final TinyUrl url = new TinyUrl();
        for (int i = 0; i < 10000; i++) {
            final String originalUrl = "https://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/?utm_source="
                    + UUID.randomUUID().toString();
            final String shortUrl = url.getShortUrl(originalUrl);
            assertEquals(originalUrl, url.getOriginalUrl(shortUrl));
            System.out.println(originalUrl + " -> " + shortUrl);
        }
    }
}