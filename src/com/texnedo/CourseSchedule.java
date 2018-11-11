package com.texnedo;

import java.util.*;

public class CourseSchedule {
    public static void main(String[] args) {
        CourseSchedule schedule = new CourseSchedule();
        int[][] data = {{0,1},{3,1},{1,3},{3,2}};
        System.out.println(schedule.canFinish(4, data));
    }

    private static class Vertex {
        private final int number;
        private Set<Vertex> connections = new HashSet<>();

        private Vertex(int number) {
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return number == vertex.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }
    }

    public boolean hasCycle(Vertex vertex,
                            HashSet<Vertex> visited,
                            HashSet<Vertex> dfsStarted) {
        if (visited.contains(vertex)) {
            return false;
        }
        dfsStarted.add(vertex);
        visited.add(vertex);
        for (Vertex child : vertex.connections) {
            if (!visited.contains(child) && hasCycle(child, visited, dfsStarted)) {
                return true;
            } else if (dfsStarted.contains(child)) {
                return true;
            }
        }
        dfsStarted.remove(vertex);
        return false;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        final Vertex[] vertices = new Vertex[numCourses];
        for (int i = 0; i < numCourses; i++) {
            vertices[i] = new Vertex(i);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            final Vertex src = vertices[prerequisites[i][0]];
            final Vertex dst = vertices[prerequisites[i][1]];
            src.connections.add(dst);
        }
        final HashSet<Vertex> dfsStarted = new HashSet<>();
        final HashSet<Vertex> visited = new HashSet<>();
        for (int i = 0; i < vertices.length; i++) {
            if (hasCycle(vertices[i], visited, dfsStarted)) {
                return false;
            }
        }
        return true;
    }
}
