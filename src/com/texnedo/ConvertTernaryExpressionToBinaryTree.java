package com.texnedo;

import com.texnedo.utils.BaseTreeNode;
import java.util.Stack;

/**
 * a?b:c
 * a?b?c:d:e
 * */
public class ConvertTernaryExpressionToBinaryTree {
    public static class TreeNode extends BaseTreeNode<Character> {
        public TreeNode(char val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ConvertTernaryExpressionToBinaryTree tree = new ConvertTernaryExpressionToBinaryTree();
        TreeNode.print(tree.parse("a?b?c:d:e"));
    }

    public TreeNode parse(String expression) {
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> opStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (current == '?' || current == ':') {
                opStack.push(i);
            } else {
                nodeStack.push(new TreeNode(current));
                if (!opStack.isEmpty() && expression.charAt(opStack.peek()) == ':') {
                    int lastDelimiter = opStack.pop();
                    if (!opStack.isEmpty() && expression.charAt(opStack.peek()) == '?') {
                        if (nodeStack.size() < 3) {
                            throw new IllegalArgumentException();
                        }
                        TreeNode right = nodeStack.pop();
                        TreeNode left = nodeStack.pop();
                        TreeNode parent = nodeStack.peek();
                        parent.left = left;
                        parent.right = right;
                    } else {
                        opStack.push(lastDelimiter);
                    }
                }
            }
        }
        if (nodeStack.size() == 1) {
            return nodeStack.pop();
        }
        throw new IllegalArgumentException();
    }
}
