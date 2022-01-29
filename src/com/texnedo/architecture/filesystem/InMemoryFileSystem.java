package com.texnedo.architecture.filesystem;

import java.awt.geom.IllegalPathStateException;
import java.util.*;

public class InMemoryFileSystem {
    final INode root = new DirectoryNode("", null);

    private INode getNode(String path, boolean createIfNotExisting) {
        if (path == null || path.length() == 0 || path.charAt(0) != '/') {
            throw new IllegalArgumentException();
        }
        INode current = root;
        int start = 1;
        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == '/') {
                if (current == null || !current.isDirectory()) {
                    throw new IllegalPathStateException(path);
                }
                final String token = path.substring(start, i);
                if (token.isEmpty()) {
                    throw new IllegalPathStateException(path);
                }
                INode nextNode = current.getChild(token);
                if (nextNode == null && createIfNotExisting) {
                    nextNode = current.addChild(token, true);
                }
                current = nextNode;
                start = i + 1;
            }
        }
        if (start <= path.length() - 1) {
            if (current == null || !current.isDirectory()) {
                throw new IllegalPathStateException(path);
            }
            final String token = path.substring(start);
            current = current.getChild(token);
        }
        return current;
    }

    public List<String> ls(String path) {
        final INode node = getNode(path, false);
        if (node == null) {
            throw new IllegalPathStateException(path);
        }
        if (node.isDirectory()) {
            final Collection<INode> nodes = node.getChildren();
            final ArrayList<String> result = new ArrayList<>(nodes.size());
            for (INode item : nodes) {
                result.add(item.getName());
            }
            return result;
        } else {
            return Collections.singletonList(node.getName());
        }
    }

    public List<NodeWrapper> lsExt(String path) {
        final INode node = getNode(path, false);
        if (node == null) {
            throw new IllegalPathStateException(path);
        }
        if (node.isDirectory()) {
            final Collection<INode> nodes = node.getChildren();
            final ArrayList<NodeWrapper> result = new ArrayList<>(nodes.size());
            for (INode item : nodes) {
                result.add(new NodeWrapper(item));
            }
            return result;
        } else {
            return Collections.singletonList(new NodeWrapper(node));
        }
    }

    public void mkdir(String path) {
        final int lastParentIndex = path.lastIndexOf("/");
        if (lastParentIndex == -1) {
            throw new IllegalArgumentException();
        }
        final String dirToCreate = path.substring(lastParentIndex + 1);
        if (dirToCreate.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final String dirToFind = path.substring(0, lastParentIndex + 1);
        final INode node = getNode(dirToFind, true);
        if (node == null || !node.isDirectory()) {
            throw new IllegalPathStateException(path);
        }
        node.addChild(dirToCreate, true);
    }

    public void addContentToFile(String filePath, String content) {
        final int lastParentIndex = filePath.lastIndexOf("/");
        if (lastParentIndex == -1) {
            throw new IllegalArgumentException();
        }
        final String fileToAdd = filePath.substring(lastParentIndex + 1);
        if (fileToAdd.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final String dirToFind = filePath.substring(0, lastParentIndex + 1);
        final INode node = getNode(dirToFind, false);
        if (node == null || !node.isDirectory()) {
            throw new IllegalPathStateException(filePath);
        }
        INode newNode = node.getChild(fileToAdd);
        if (newNode == null) {
            newNode = node.addChild(fileToAdd, false);
        } else if (newNode.isDirectory()) {
            throw new IllegalPathStateException(filePath);
        }
        final FileNode fileNode = (FileNode) newNode;
        fileNode.write(content);
    }

    public String readContentFromFile(String filePath) {
        final INode node = getNode(filePath, false);
        if (node == null) {
            throw new IllegalPathStateException(filePath);
        }
        if (node.isDirectory()) {
            throw new IllegalPathStateException(filePath);
        }
        final FileNode fileNode = (FileNode) node;
        return fileNode.read();
    }

    public static void main(String[] args) {
        InMemoryFileSystem system = new InMemoryFileSystem();
        System.out.println(system.ls("/"));
        system.mkdir("/x/y/z");
        system.addContentToFile("/x/y/z/g", "Hello");
        system.addContentToFile("/x/y/z/g", "Hello2");
        System.out.println(system.readContentFromFile("/x/y/z/g"));
        system.mkdir("/a");
        system.mkdir("/a/b");
        system.mkdir("/a/c");
        system.mkdir("/a/d");
        system.mkdir("/a/d");
        system.mkdir("/a/d/e");
        System.out.println(system.ls("/"));
        System.out.println(system.ls("/x/y"));
        System.out.println(system.lsExt("/x/y/z"));
        System.out.println(system.ls("/a"));
        System.out.println(system.ls("/a/d"));
        system.addContentToFile("/a/d/f", "Some text");
        System.out.println(system.ls("/a/d"));
        System.out.println(system.readContentFromFile("/a/d/f"));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.isEmpty()) {
                continue;
            }
            String[] tokens = command.split(" ");
            if (tokens.length == 2) {
                if ("cat".equals(tokens[0])) {
                    System.out.println(system.readContentFromFile(tokens[1]));
                } else if ("mkdir".equals(tokens[0])) {
                    system.mkdir(tokens[1]);
                } else if ("ls".equals(tokens[0])) {
                    System.out.println(system.ls(tokens[1]));
                }
            } else if (tokens.length == 3) {
                if ("wr".equals(tokens[0])) {
                    system.addContentToFile(tokens[1], tokens[2]);
                }
            }
        }
    }
}
