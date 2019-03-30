package com.texnedo;

import java.util.*;

public class ReconstructItinerary {
    private static String START_POINT = "JFK";

    private static class StringListComparator implements Comparator<List<String>> {
        @Override
        public int compare(List<String> o1, List<String> o2) {
            ListIterator<String> iter1 = o1.listIterator();
            ListIterator<String> iter2 = o2.listIterator();
            while (iter1.hasNext() && iter2.hasNext()) {
                String s1 = iter1.next();
                String s2 = iter2.next();
                if (s1.equals(s2)) {
                    continue;
                }
                return s1.compareTo(s2);
            }
            if (!iter1.hasNext() && iter2.hasNext()) {
                return 1;
            } else if (iter1.hasNext()) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        ReconstructItinerary reconstructItinerary = new ReconstructItinerary();
        String[][] data = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
        String[][] data1 = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        System.out.println(reconstructItinerary.findItinerary(data));
    }

    public List<String> findItinerary(String[][] tickets) {
        int[] visited = new int[tickets.length];
        HashMap<String, List<Integer>> starts = new HashMap<>();
        for (int i = 0; i < tickets.length; i++) {
            starts.computeIfAbsent(tickets[i][0], k-> new LinkedList<>()).add(i);
        }
        LinkedList<String> path = new LinkedList<>();
        return findItineraryInternal(tickets, START_POINT, starts, visited, path);
    }

    private List<String> findItineraryInternal(String[][] tickets,
                                                String node,
                                                HashMap<String, List<Integer>> starts,
                                                int[] visited,
                                                LinkedList<String> path) {
        List<Integer> options = starts.get(node);
        System.out.println("Node: " + node + " Path: " + path);
        path.addLast(node);
        if (options == null || options.isEmpty()) {
            List<String> emptyResult = new ArrayList<>(path);
            path.removeLast();
            return emptyResult;
        }
        List<List<String>> result = new ArrayList<>();
        for (Integer option : options) {
            if (visited[option] != 1) {
                visited[option] = 1;
                //TODO - chose the smallest option on each step to optimize performance
                List<String> optionPath = findItineraryInternal(tickets, tickets[option][1], starts, visited, path);
                if (optionPath.size() == tickets.length + 1) {
                    result.add(optionPath);
                }
                visited[option] = 0;
            }
        }
        if (result.isEmpty()) {
            List<String> emptyResult = new ArrayList<>(path);
            path.removeLast();
            return emptyResult;
        }
        path.removeLast();
        result.sort(new StringListComparator());
        return result.get(0);
    }
}
