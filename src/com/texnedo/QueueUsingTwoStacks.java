package com.texnedo;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class QueueUsingTwoStacks {
    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> auxStack = new Stack<>();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        QueueUsingTwoStacks queueUsingTwoStacks = new QueueUsingTwoStacks();
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            String operation = tokens[0];
            if ("1".equals(operation)) {
                String operand = tokens[1];
                queueUsingTwoStacks.enqueue(Integer.parseInt(operand));
            } else if ("2".equals(operation)) {
                queueUsingTwoStacks.dequeue();
            } else {
                System.out.println(queueUsingTwoStacks.peekFirst());
            }
        }
        scanner.close();
    }

    public void enqueue(int data) {
        mainStack.push(data);
    }

    public int dequeue() {
        if (auxStack.empty()) {
            while (!mainStack.empty()) {
                auxStack.push(mainStack.pop());
            }
        }
        return auxStack.pop();
    }

    public int peekFirst() {
        if (auxStack.empty()) {
            while (!mainStack.empty()) {
                auxStack.push(mainStack.pop());
            }
        }
        return auxStack.peek();
    }
}
