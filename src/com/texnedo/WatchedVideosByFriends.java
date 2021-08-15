package com.texnedo;

import java.util.*;

public class WatchedVideosByFriends {
    public static void main(String[] args) {
        WatchedVideosByFriends calculate = new WatchedVideosByFriends();
        String[][] watchedVideosArray = {{"A","B"},{"C"},{"B","C"},{"D"}};
        int[][] friends = {{1,2},{0,3},{0,3},{1,2}};
        List<List<String>> watchedVideos = buildWatchedVideoList(watchedVideosArray);
        System.out.println(calculate.watchedVideosByFriends(watchedVideos, friends, 0, 1));

        String[][] watchedVideosArray1 = {{"A","B"},{"C"},{"B","C"},{"D"}};
        int[][] friends1 = {{1,2},{0,3},{0,3},{1,2}};
        List<List<String>> watchedVideos1 = buildWatchedVideoList(watchedVideosArray1);
        System.out.println(calculate.watchedVideosByFriends(watchedVideos1, friends1, 0, 2));
    }

    private static List<List<String>> buildWatchedVideoList(String[][] watchedVideosArray) {
        List<List<String>> watchedVideos = new ArrayList<>(watchedVideosArray.length);
        for (int i = 0; i < watchedVideosArray.length; i++) {
            ArrayList<String> videos = new ArrayList<>(watchedVideosArray[i].length);
            for (int j = 0; j < watchedVideosArray[i].length; j++) {
                //noinspection UseBulkOperation
                videos.add(watchedVideosArray[i][j]);
            }
            watchedVideos.add(videos);
        }
        return watchedVideos;
    }

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos,
                                               int[][] friends,
                                               int id,
                                               int level) {
        if (friends.length < 1) {
            return Collections.emptyList();
        }
        if (level < 0 || id < 0) {
            throw new IllegalArgumentException();
        }
        LinkedList<Integer> levelIds = new LinkedList<>();
        levelIds.add(id);
        int currentLevel = 0;
        boolean[] visited = new boolean[friends.length];
        while (!levelIds.isEmpty() && currentLevel < level) {
            LinkedList<Integer> nextLevelIds = new LinkedList<>();
            for (Integer currentId : levelIds) {
                visited[currentId] = true;
                for (int i = 0; i < friends[currentId].length; i++) {
                    if (!visited[friends[currentId][i]]) {
                        nextLevelIds.add(friends[currentId][i]);
                    }
                }
            }
            levelIds.clear();
            levelIds.addAll(nextLevelIds);
            currentLevel++;
        }
        if (currentLevel != level) {
            return Collections.emptyList();
        }
        return buildWatchedVideosSorted(watchedVideos, levelIds);
    }

    private List<String> buildWatchedVideosSorted(List<List<String>> watchedVideos,
                                                  List<Integer> levelIds) {
        if (levelIds.isEmpty()) {
            return Collections.emptyList();
        }
        HashMap<String, Integer> watchCounts = new HashMap<>();
        for (Integer id : levelIds) {
            for (String video : watchedVideos.get(id)) {
                Integer count = watchCounts.get(video);
                if (count == null) {
                    watchCounts.put(video, 0);
                } else {
                    watchCounts.put(video, count + 1);
                }
            }
        }
        ArrayList<String> result = new ArrayList<>(watchCounts.size());
        result.addAll(watchCounts.keySet());
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int count1 = watchCounts.get(o1);
                int count2 = watchCounts.get(o2);
                if (count1 == count2) {
                    return o1.compareTo(o2);
                }
                return Integer.compare(count1, count2);
            }
        });
        return result;
    }
}
