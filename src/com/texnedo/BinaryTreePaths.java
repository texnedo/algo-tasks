package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePaths {
    public static void main(String[] args) {
        BinaryTreePaths paths = new BinaryTreePaths();
        Integer[] data = {1, 2, 3, null, 5, null, null};
        TreeNode node = TreeNode.parse(data);
        TreeNode.print(node);
        System.out.println(paths.binaryTreePaths(node));
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<StringBuilder> result = binaryTreePathsInternal(root);
        List<String> stringList = new ArrayList<>(result.size());
        for (StringBuilder builder : result) {
            stringList.add(builder.toString());
        }
        return stringList;
    }

    public List<StringBuilder> binaryTreePathsInternal(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<StringBuilder> left = binaryTreePathsInternal(root.left);
        List<StringBuilder> right = binaryTreePathsInternal(root.right);
        left.addAll(right);
        if (left.isEmpty()) {
            left.add(new StringBuilder(Integer.toString(root.val)));
        } else {
            for (StringBuilder builder : left) {
                if (builder.length() != 0) {
                    builder.insert(0, "->");
                }
                builder.insert(0, root.val);
            }
        }
        return left;
    }
}
