package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class EqualTreePartition {

    public static void main(String[] args) {
        EqualTreePartition partition = new EqualTreePartition();
        Integer[] data = {5, 10, 10, null, null, 2, 3};
        Integer[] data1 = {1, 2, 10, null, null, 2, 10};
        Integer[] data2 = {5, 10, 10, 30, null, 2, 3};
        TreeNode node = TreeNode.parse(data);
        System.out.println(partition.checkPartition(node));
        System.out.println(partition.checkPartitionFast(node));
        System.out.println();
        TreeNode node1 = TreeNode.parse(data1);
        System.out.println(partition.checkPartition(node1));
        System.out.println(partition.checkPartitionFast(node1));
        System.out.println();
        TreeNode node2 = TreeNode.parse(data2);
        System.out.println(partition.checkPartition(node2));
        System.out.println(partition.checkPartitionFast(node2));
        System.out.println();
    }

    public boolean checkPartition(TreeNode node) {
        int sum = calcTreeSum(node);
        if (sum % 2 != 0) {
            return false;
        }
        return checkPartition(node, sum / 2);
    }

    public boolean checkPartitionFast(TreeNode node) {
        HashSet<Integer> sums = new HashSet<>();
        int sum = calcTreeSumFast(node, sums);
        if (sum % 2 != 0) {
            return false;
        }
        return sums.contains(sum / 2);
    }

    private boolean checkPartition(TreeNode node, int partitionSum) {
        if (node == null) {
            return false;
        }
        if (calcTreeSum(node.left) == partitionSum) {
            return true;
        }
        if (calcTreeSum(node.right) == partitionSum) {
            return true;
        }
        return checkPartition(node.left, partitionSum)
                || checkPartition(node.right, partitionSum);
    }

    private int calcTreeSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return calcTreeSum(node.left) +
                calcTreeSum(node.right) +
                node.val;
    }

    private int calcTreeSumFast(TreeNode node, Set<Integer> sums) {
        if (node == null) {
            return 0;
        }
        int sum = calcTreeSumFast(node.left, sums) +
                calcTreeSumFast(node.right, sums) +
                node.val;
        sums.add(sum);
        return sum;
    }
}
