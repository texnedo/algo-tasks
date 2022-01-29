package com.texnedo.architecture.filesystem;

import java.util.Collection;
import java.util.Collections;

class FileNode extends FileSystemNode {
    private final StringBuilder content = new StringBuilder();

    public FileNode(String name, INode parent) {
        super(name, parent);
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public Collection<INode> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public INode getChild(String name) {
        return null;
    }

    @Override
    public boolean addChild(INode node) {
        return false;
    }

    @Override
    public INode addChild(String name, boolean isDirectory) {
        return null;
    }

    @Override
    public int getSize() {
        return content.length();
    }

    public void write(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        content.append(text);
    }

    public String read() {
        return content.toString();
    }
}
