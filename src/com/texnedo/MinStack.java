package com.texnedo;

import java.util.Stack;

public class MinStack {
    private final Stack<Integer> stack = new Stack<>();
    private final Stack<Integer> minValues = new Stack<>();

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        if (minValues.empty()) {
            minValues.push(x);
        } else {
            int lastMin = minValues.peek();
            minValues.push(Math.min(lastMin, x));
        }
    }

    public void pop() {
        stack.pop();
        minValues.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minValues.peek();
    }
}
