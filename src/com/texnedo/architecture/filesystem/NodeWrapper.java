package com.texnedo.architecture.filesystem;

public class NodeWrapper {
    private final INode node;

    public NodeWrapper(INode node) {
        this.node = node;
    }

    public boolean isDirectory() {
        return node.isDirectory();
    }

    public String getName() {
        return node.getName();
    }

    public int getSize() {
        return node.getSize();
    }

    @Override
    public String toString() {
        return "Node{" +
                "node=" + node +
                '}';
    }
}
