package com.texnedo;

import com.texnedo.utils.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BSTVerticalOrder {
    public static void main(String[] args) {
        BSTVerticalOrder order = new BSTVerticalOrder();
        Integer[] data = {3,9,20,5,10,15,7};
        System.out.println(order.getVerticalOrder(data));
    }

    public List<List<Integer>> getVerticalOrder(Integer[] data) {
        List<List<Integer>> orderedRight = getVerticalOrder(data, true);
        List<List<Integer>> orderedLeft = getVerticalOrder(data, false);
        return merge(orderedLeft, orderedRight);
    }

    private List<List<Integer>> merge(List<List<Integer>> left, List<List<Integer>> right) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        Iterator<List<Integer>> it = ((LinkedList<List<Integer>>)right).descendingIterator();
        while (it.hasNext()) {
            result.add(it.next());
        }
        it = left.iterator();
        //TODO - fix middle layer mering
        if (!result.isEmpty() && it.hasNext()) {
            result.peekLast().addAll(it.next());
        }
        while (it.hasNext()) {
            result.add(it.next());
        }
        return result;
    }

    private List<List<Integer>> getVerticalOrder(Integer[] data, boolean rightSide) {
        LinkedList<List<Integer>> ordered = new LinkedList<>();
        LinkedList<Integer> level = new LinkedList<>();
        LinkedList<Integer> nextLevel = new LinkedList<>();
        LinkedList<Integer> levelIndexes = new LinkedList<>();
        LinkedList<Integer> nextLevelIndexes = new LinkedList<>();
        Integer root = rightSide ? TreeNode.getRightMost(data) : TreeNode.getLeftMost(data);
        if (root == null) {
            return ordered;
        }
        level.add(data[root]);
        levelIndexes.add(root);
        while (!level.isEmpty()) {
            ordered.push(new LinkedList<>(level));
            while (!level.isEmpty()) {
                Integer value = level.poll();
                Integer currentIndex = levelIndexes.poll();
                if (currentIndex == null) {
                    break;
                }
                Integer parentIndex = TreeNode.getParent(data, currentIndex);
                if (parentIndex == null) {
                    level.clear();
                    break;
                }
                nextLevel.add(data[parentIndex]);
                nextLevelIndexes.add(parentIndex);
                Integer childIndex = rightSide
                        ? TreeNode.getLeftChild(data, currentIndex)
                        : TreeNode.getRightChild(data, currentIndex);
                if (childIndex != null) {
                    nextLevel.add(data[childIndex]);
                    nextLevelIndexes.add(childIndex);
                }
            }
            level.addAll(nextLevel);
            levelIndexes.addAll(nextLevelIndexes);
            nextLevel.clear();
            nextLevelIndexes.clear();
        }
        return ordered;
    }
}
