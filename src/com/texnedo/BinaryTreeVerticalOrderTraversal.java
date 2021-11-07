package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.*;

public class BinaryTreeVerticalOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeVerticalOrderTraversal val = new BinaryTreeVerticalOrderTraversal();
        System.out.println(val.verticalOrder(TreeNode.parse(new Integer[] {3,9,20,null,null,15,7})));
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        TreeMap<Integer, List<Integer>> result = new TreeMap<>();
        traverse(root, result, 0);
        ArrayList<List<Integer>> data = new ArrayList<>(result.size());
        for (Map.Entry<Integer, List<Integer>> item : result.entrySet()) {
            data.add(item.getValue());
        }
        return data;
    }

    private void traverse(TreeNode root,
                          TreeMap<Integer, List<Integer>> result,
                          int offset) {
        if (root == null) {
            return;
        }
        List<Integer> existing = result.get(offset);
        if (existing == null) {
            existing = new LinkedList<>();
            result.put(offset, existing);
        }
        existing.add(root.val);
        traverse(root.right, result, offset + 1);
        traverse(root.left, result, offset - 1);
    }
}
