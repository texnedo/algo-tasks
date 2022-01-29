package com.texnedo.architecture.filesystem;

import java.util.Collection;

interface INode {
    String getName();

    boolean isDirectory();

    INode getParent();

    Collection<INode> getChildren();

    INode getChild(String name);

    boolean addChild(INode node);

    int getSize();

    INode addChild(String name, boolean isDirectory);
}
