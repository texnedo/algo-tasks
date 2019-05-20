package com.texnedo;

import com.texnedo.utils.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new LinkedList<>();
        List<Node> level = new LinkedList<>();
        List<Node> nextLevel = new LinkedList<>();
        level.add(root);
        while (!level.isEmpty()) {
            List<Integer> levelValues = new ArrayList<>(level.size());
            for (Node node : level) {
                levelValues.add(node.val);
                if (node.children != null) {
                    nextLevel.addAll(node.children);
                }
            }
            result.add(levelValues);
            level.clear();
            level.addAll(nextLevel);
            nextLevel.clear();
        }
        return result;
    }
}
