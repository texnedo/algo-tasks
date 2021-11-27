package com.texnedo;

import java.util.HashSet;

public class LowestCommonAncestorBinaryTree_v2 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        HashSet<Node> pParents = new HashSet<>();
        Node pIterator = p;
        while (pIterator != null) {
            pParents.add(pIterator);
            pIterator = pIterator.parent;
        }
        Node qIterator = q;
        while (qIterator != null) {
            if (pParents.contains(qIterator)) {
                return qIterator;
            }
            qIterator = qIterator.parent;
        }
        return null;
    }
}
