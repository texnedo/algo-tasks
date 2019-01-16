package com.texnedo;

import com.texnedo.utils.Graph;

public class CheckGraphIsTree {
    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        System.out.println(g.isTree());
    }
}
