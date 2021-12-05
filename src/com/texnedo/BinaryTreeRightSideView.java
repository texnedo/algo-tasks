package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        BinaryTreeRightSideView view = new BinaryTreeRightSideView();
        Integer[] data = {1,2,3,null,5,null,4};
        TreeNode root = TreeNode.parse(data);
        System.out.println(view.rightSideView(root));
    }

    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<TreeNode> layer = new LinkedList<>();
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> nextLayer = new LinkedList<>();
        if (root != null) {
            layer.add(root);
        }
        while (!layer.isEmpty()) {
            result.add(layer.getLast().val);
            for (TreeNode node : layer) {
                if (node.left != null) {
                    nextLayer.add(node.left);
                }
                if (node.right != null) {
                    nextLayer.add(node.right);
                }
            }
            layer.clear();
            layer.addAll(nextLayer);
            nextLayer.clear();
        }
        return result;
    }
}
