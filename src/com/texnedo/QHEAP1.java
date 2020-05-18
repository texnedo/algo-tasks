package com.texnedo;

import java.util.ArrayList;

public class QHEAP1 {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        heap.insert(10);
        heap.insert(4);
        heap.insert(6);
        heap.insert(3);
        heap.insert(8);
        System.out.println(heap.getMin());
        System.out.println(heap.removeMin());
        System.out.println(heap.getMin());
    }

    public static class MinHeap {
        private ArrayList<Integer> heap = new ArrayList<>();

        public void insert(int data) {
            heap.add(data);
            int index = heap.size() - 1;
            while (index > 0 && data < heap.get(getParent(index))) {
                heap.set(index, heap.get(getParent(index)));
                index = getParent(index);
            }
            heap.set(index, data);
        }

        public int getMin() {
            return heap.get(0);
        }

        public int removeMin() {
            int min = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            int index = 0;
            while (index < heap.size() - 1) {
                int minChildValue = Math.min(
                        heap.get(leftChild(index)),
                        heap.get(rightChild(index))
                );
                //check heap property
                if (minChildValue > heap.get(index)) {
                    break;
                }
                int minChildIndex;
                if (minChildValue == heap.get(leftChild(index))) {
                    minChildIndex = leftChild(index);
                } else {
                    minChildIndex = rightChild(index);
                }
                heap.set(minChildIndex, heap.get(index));
                heap.set(index, minChildValue);
                index = minChildIndex;
            }
            return min;
        }

        private int getParent(int index) {
            return index / 2;
        }

        private int leftChild(int index) {
            return (2 * index);
        }

        private int rightChild(int index) {
            return (2 * index) + 1;
        }
    }
}
