package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.*;

public class VerticalOrderTraversalBinaryTree {
    public static void main(String[] args) {
        VerticalOrderTraversalBinaryTree tree = new VerticalOrderTraversalBinaryTree();
        Integer[] data = {3,9,20,null,null,15,7};
        TreeNode root = TreeNode.parse(data);
        System.out.println(tree.verticalTraversal(root));
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, List<Integer>> columns = new TreeMap<>();
        verticalTraversalInternal(root, 0, columns);
        List<List<Integer>> result = new ArrayList<>(columns.size());
        result.addAll(columns.values());
        String[][] print = printToMatrix(columns);
        printMatrix(print);
        return result;
    }

    public void verticalTraversalInternal(
            TreeNode root,
            int level,
            TreeMap<Integer, List<Integer>> columns) {
        if (root == null) {
            return;
        }
        List<Integer> column = columns.computeIfAbsent(level, k -> new LinkedList<>());
        column.add(root.val);
        //TODO - store depth to make proper offset during print phase
        verticalTraversalInternal(root.left, level - 1, columns);
        verticalTraversalInternal(root.right, level + 1, columns);
    }

    private void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private String[][] printToMatrix(TreeMap<Integer, List<Integer>> columns) {
        int maxLength = 0;
        for (List<Integer> column : columns.values()) {
            if (column.size() > maxLength) {
                maxLength = column.size();
            }
        }
        int lower = Math.abs(columns.firstKey());
        int higher = columns.lastKey();
        String[][] result = new String[columns.size()][Math.max(maxLength, Math.max(lower, higher)) + 1];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = "  ";
            }
        }
        int i = 0;
        for (Map.Entry<Integer, List<Integer>> item : columns.entrySet()) {
            int j = Math.abs(item.getKey());
            for (Integer value : item.getValue()) {
                result[i][j] = Integer.toString(value);
                j++;
            }
            i++;
        }
        return result;
    }
}
