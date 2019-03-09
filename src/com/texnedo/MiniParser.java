package com.texnedo;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MiniParser {
    public static void main(String[] args) {
        MiniParser parser = new MiniParser();
        String data = "[1,1,[2,3],4,[3,6,[3,4]]]";
        NestedIntegerImpl.prettyPrint(parser.deserialize(data), 0);
    }

    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    private static class NestedIntegerImpl implements NestedInteger {
        private LinkedList<NestedInteger> list;
        private Integer value;

        NestedIntegerImpl() {
        }

        NestedIntegerImpl(int value) {
            this.value = value;
        }

        @Override
        public boolean isInteger() {
            if (value != null && list != null) {
                throw new IllegalStateException();
            }
            return value != null;
        }

        @Override
        public Integer getInteger() {
            return value;
        }

        @Override
        public void setInteger(int value) {
            if (list != null) {
                throw new IllegalStateException();
            }
            this.value = value;
        }

        @Override
        public void add(NestedInteger ni) {
            if (value != null) {
                throw new IllegalStateException();
            }
            if (list == null) {
                list = new LinkedList<>();
            }
            list.add(ni);
        }

        @Override
        public List<NestedInteger> getList() {
            return list;
        }

        public static void prettyPrint(NestedInteger ni, int shift) {
           if (ni.isInteger()) {
               move(shift);
               System.out.println(ni.getInteger());
           } else {
               move(shift);
               System.out.println("[");
               if (ni.getList() != null) {
                   for (NestedInteger internal : ni.getList()) {
                       prettyPrint(internal, shift + 1);
                   }
               }
               move(shift);
               System.out.println("]");
           }
        }

        private static void move(int shift) {
            for (int i = 0; i < shift; i++) {
                System.out.print(" ");
            }
        }
    }

    public NestedInteger deserialize(String s) {
        Stack<NestedInteger> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            switch (current) {
                case ',': {
                    processInteger(stack, builder);
                    break;
                }
                case '[': {
                    if (builder.length() != 0) {
                        throw new IllegalArgumentException();
                    }
                    NestedInteger wrapper = new NestedIntegerImpl();
                    stack.push(wrapper);
                    break;
                }
                case ']': {
                    processInteger(stack, builder);
                    if (stack.empty()) {
                        throw new IllegalArgumentException();
                    }
                    NestedInteger completed = stack.pop();
                    if (stack.empty()) {
                        //the end of iteration
                        stack.push(completed);
                    } else {
                        NestedInteger wrapper = stack.peek();
                        wrapper.add(completed);
                    }
                    break;
                }
                default: {
                    if (!Character.isDigit(current)) {
                        throw new IllegalArgumentException();
                    }
                    builder.append(current);
                    break;
                }
            }
        }
        if (stack.empty()) {
            throw new IllegalArgumentException();
        }
        return stack.peek();
    }

    private void processInteger(Stack<NestedInteger> stack, StringBuilder builder) {
        if (builder.length() != 0) {
            int value = Integer.parseInt(builder.toString());
            builder.delete(0, builder.length());
            NestedInteger integer = new NestedIntegerImpl(value);
            NestedInteger wrapper = stack.peek();
            if (wrapper == null) {
                throw new IllegalArgumentException();
            }
            wrapper.add(integer);
        }
    }
}
