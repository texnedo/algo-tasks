package com.texnedo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class QueueWithMax {
    private final Queue<Item> queue = new LinkedList<>();
    private final LinkedList<Item> indexes = new LinkedList<>();

    void offer(int value) {
        Item item = new Item();
        item.index = queue.size();
        item.value = value;
        queue.offer(item);
        if (indexes.peek() != null) {
            //remove outdated from the end
            while (!indexes.isEmpty() && indexes.peekLast().value <= value) {
                indexes.removeLast();
            }
        }
        indexes.offer(item);
    }

    int poll() {
        Item item = queue.poll();
        if (item == null || indexes.peek() == null) {
            throw new IndexOutOfBoundsException();
        }
        if (item.index == indexes.peek().index) {
            indexes.poll();
        }
        return item.value;
    }

    int max() {
        Item item = indexes.peek();
        if (item == null) {
            throw new IndexOutOfBoundsException();
        }
        return item.value;
    }

    int peek() {
        Item item = queue.peek();
        if (item == null) {
            throw new IndexOutOfBoundsException();
        }
        return item.value;
    }

    private static class Item {
        int value;

        int index;
    }

    public static void main(String[] args) {
        QueueWithMax queueWithMax = new QueueWithMax();
        queueWithMax.offer(1);
        queueWithMax.offer(2);
        queueWithMax.offer(3);
        System.out.println(queueWithMax.max());
        queueWithMax.offer(2);
        queueWithMax.offer(0);
        queueWithMax.offer(9);
        queueWithMax.offer(11);
        queueWithMax.offer(8);
        queueWithMax.offer(2);
        queueWithMax.offer(1);
        queueWithMax.poll();
        queueWithMax.poll();
        queueWithMax.poll();
        System.out.println(queueWithMax.max());
    }
}
