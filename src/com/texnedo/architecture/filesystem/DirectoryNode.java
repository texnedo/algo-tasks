package com.texnedo.architecture.filesystem;

import java.util.Collection;
import java.util.TreeMap;

class DirectoryNode extends FileSystemNode {
    private final TreeMap<String, INode> nodes = new TreeMap<>();

    public DirectoryNode(String name, INode parent) {
        super(name, parent);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public Collection<INode> getChildren() {
        return nodes.values();
    }

    @Override
    public INode getChild(String name) {
        return nodes.get(name);
    }

    @Override
    public boolean addChild(INode node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }
        if (nodes.containsKey(node.getName())) {
            return false;
        }
        nodes.put(node.getName(), node);
        return true;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public INode addChild(String name, boolean isDirectory) {
        INode node = nodes.get(name);
        if (node != null) {
            return node;
        }
        if (isDirectory) {
            node = new DirectoryNode(name, this);
        } else {
            node = new FileNode(name, this);
        }
        addChild(node);
        return node;
    }

}
