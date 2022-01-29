package com.texnedo.architecture.filesystem;

abstract class FileSystemNode implements INode {
    protected String name;
    protected INode parent;

    protected FileSystemNode(String name, INode parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public INode getParent() {
        return parent;
    }

    @Override
    public String toString() {
        if (isDirectory()) {
            return "Directory {" +
                    "name='" + name + '\'' +
                    ", parent=" + parent +
                    '}';
        } else {
            return "File {" +
                    "name='" + name + '\'' +
                    ", parent=" + parent +
                    ", size=" + getSize() +
                    '}';
        }
    }
}
