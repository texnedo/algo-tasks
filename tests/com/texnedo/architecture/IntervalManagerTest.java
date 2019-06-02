package com.texnedo.architecture;

import com.texnedo.architecture.IntervalManager.MessageRollout;

import java.util.HashMap;

import static junit.framework.TestCase.*;

public class IntervalManagerTest {

    @org.junit.Test
    public void setMessageRollout() {
        IntervalManager manager = new IntervalManager();
        assertEquals(manager.getTotalRollout(), 0);
        assertFalse(manager.getMessageRollouts().iterator().hasNext());
        assertEquals(manager.getAvailableRollout(), 100);
        assertTrue(manager.setMessageRollout("A", 5));
        assertTrue(manager.setMessageRollout("B", 5));
        assertTrue(manager.setMessageRollout("C", 5));
        assertEquals(manager.getTotalRollout(), 15);
        assertEquals(manager.getAvailableRollout(), 85);
        for (MessageRollout rollout : manager.getMessageRollouts()) {
            if ("A".equals(rollout.id)) {
                assertEquals(rollout.getRollout(), 5);
            } else if ("B".equals(rollout.id)) {
                assertEquals(rollout.getRollout(), 5);
            } if ("C".equals(rollout.id)) {
                assertEquals(rollout.getRollout(), 5);
            }
        }
        System.out.println(manager);
        assertTrue(manager.setMessageRollout("A", 20));
        assertTrue(manager.setMessageRollout("B", 20));
        assertTrue(manager.setMessageRollout("C", 20));
        assertEquals(manager.getTotalRollout(), 60);
        assertEquals(manager.getAvailableRollout(), 40);
        System.out.println(manager);
        assertTrue(manager.setMessageRollout("A", 30));
        assertFalse(manager.setMessageRollout("B", 20));
        assertFalse(manager.setMessageRollout("C", 20));
        assertEquals(manager.getTotalRollout(), 70);
        assertEquals(manager.getAvailableRollout(), 30);
        System.out.println(manager);
        assertTrue(manager.setMessageRollout("A", 50));
        assertFalse(manager.setMessageRollout("B", 20));
        assertFalse(manager.setMessageRollout("C", 20));
        assertEquals(manager.getTotalRollout(), 90);
        assertEquals(manager.getAvailableRollout(), 10);
        System.out.println(manager);
        assertTrue(manager.setMessageRollout("A", 60));
        assertFalse(manager.setMessageRollout("B", 20));
        assertFalse(manager.setMessageRollout("C", 20));
        assertEquals(manager.getTotalRollout(), 100);
        assertEquals(manager.getAvailableRollout(), 0);
        System.out.println(manager);
        assertFalse(manager.setMessageRollout("A", 60));
        assertTrue(manager.setMessageRollout("B", 10));
        assertTrue(manager.setMessageRollout("C", 10));
        assertEquals(manager.getTotalRollout(), 80);
        assertEquals(manager.getAvailableRollout(), 20);
        System.out.println(manager);
        assertFalse(manager.setMessageRollout("A", 60));
        assertTrue(manager.setMessageRollout("B", 0));
        assertTrue(manager.setMessageRollout("C", 0));
        assertEquals(manager.getTotalRollout(), 60);
        assertEquals(manager.getAvailableRollout(), 40);
        System.out.println(manager);
        assertTrue(manager.setMessageRollout("A", 100));
        assertFalse(manager.setMessageRollout("B", 0));
        assertFalse(manager.setMessageRollout("C", 0));
        assertEquals(manager.getTotalRollout(), 100);
        assertEquals(manager.getAvailableRollout(), 0);
        System.out.println(manager);
    }

    @org.junit.Test
    public void setMessageRollouts() {
        IntervalManager manager = new IntervalManager();
        HashMap<String, Integer> test = new HashMap<>();
        test.put("A", 10);
        test.put("B", 15);
        test.put("C", 20);
        assertTrue(manager.setMessageRollouts(test));
        for (MessageRollout rollout : manager.getMessageRollouts()) {
            if ("A".equals(rollout.id)) {
                assertEquals(rollout.getRollout(), 10);
            } else if ("B".equals(rollout.id)) {
                assertEquals(rollout.getRollout(), 15);
            } if ("C".equals(rollout.id)) {
                assertEquals(rollout.getRollout(), 20);
            }
        }
        System.out.println(manager);
    }

    @org.junit.Test
    public void setMessageRolloutsError() {
        IntervalManager manager = new IntervalManager();
        HashMap<String, Integer> test = new HashMap<>();
        test.put("A", 10);
        test.put("B", 15);
        test.put("C", 20);
        assertTrue(manager.setMessageRollouts(test));
        for (MessageRollout rollout : manager.getMessageRollouts()) {
            if ("A".equals(rollout.id)) {
                assertEquals(rollout.getRollout(), 10);
            } else if ("B".equals(rollout.id)) {
                assertEquals(rollout.getRollout(), 15);
            } if ("C".equals(rollout.id)) {
                assertEquals(rollout.getRollout(), 20);
            }
        }
        System.out.println(manager);
        assertFalse(manager.setMessageRollout("C", 100));
    }

    @org.junit.Test
    public void testPrint() {
        IntervalManager manager = new IntervalManager();
        manager.setMessageRollout("A", 5);
        manager.setMessageRollout("B", 5);
        manager.setMessageRollout("C", 5);
        manager.printAllRollouts();
    }

    @org.junit.Test
    public void testGoUpThenDown() {
        IntervalManager manager = new IntervalManager();
        assertTrue(manager.setMessageRollout("A", 5));
        assertTrue(manager.setMessageRollout("A", 10));
        assertTrue(manager.setMessageRollout("A", 20));
        assertTrue(manager.setMessageRollout("A", 10));
        assertEquals(manager.getTotalRollout(), 10);
        assertEquals(manager.getAvailableRollout(), 90);
        for (MessageRollout rollout : manager.getMessageRollouts()) {
            if ("A".equals(rollout.id)) {
                assertEquals(rollout.getRollout(), 10);
            }
        }
    }
}