package com.texnedo;

import com.texnedo.utils.TreeNode;

public class ConvertBSTGreaterTree {
    public static void main(String[] args) {
        ConvertBSTGreaterTree tree = new ConvertBSTGreaterTree();
        Integer[] data = {5, 2, 13};
        TreeNode.print(tree.convertBST(TreeNode.parse(data)));
    }

    private static class BSTContext {
        public long sumFromRight = 0;
    }

    public TreeNode convertBST(TreeNode root) {
        convertBSTInternal(root, new BSTContext());
        return root;
    }

    public void convertBSTInternal(TreeNode root, BSTContext context) {
        if (root == null) {
            return;
        }
        if (root.right != null) {
            convertBSTInternal(root.right, context);
        }
        int previousValue = root.val;
        root.val += context.sumFromRight;
        context.sumFromRight += previousValue;
        if (root.left != null) {
            convertBSTInternal(root.left, context);
        }
    }
}
