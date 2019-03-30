package com.texnedo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AllPathsFromSourceToTarget {
    public static void main(String[] args) {
        AllPathsFromSourceToTarget paths = new AllPathsFromSourceToTarget();
        int[][] data = {{1,2}, {3}, {3}, {}};
        System.out.println(paths.allPathsSourceTarget(data));
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int[] visited = new int[graph.length];
        return allPathsSourceTargetInternal(graph, 0, new LinkedList<>(), visited);
    }

    private List<List<Integer>> allPathsSourceTargetInternal(int[][] graph,
                                                             int index,
                                                             LinkedList<Integer> path,
                                                             int[] visited) {
        path.addLast(index);
        if (index == graph.length - 1) {
            List<Integer> result = new ArrayList<>(path);
            path.removeLast();
            return Collections.singletonList(result);
        }
        visited[index] = 1;
        List<List<Integer>> result = new ArrayList<>(graph.length * 2);
        for (Integer next : graph[index]) {
            if (visited[next] == 1) {
                continue;
            }
            result.addAll(allPathsSourceTargetInternal(graph, next, path, visited));
        }
        visited[index] = 0;
        path.removeLast();
        return result;
    }
}
