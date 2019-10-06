package com.texnedo.architecture;

import org.junit.Test;

import static org.junit.Assert.*;

public class HitCounterTest {

    @Test
    public void getHits() {
        HitCounter counter = new HitCounter();
        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        assertEquals(counter.getHits(4), 3);
        counter.hit(300);
        assertEquals(counter.getHits(300), 4);
        assertEquals(counter.getHits(301), 3);
        assertEquals(counter.getHits(302), 2);
        assertEquals(counter.getHits(304), 1);
        assertEquals(counter.getHits(600), 0);
    }
}