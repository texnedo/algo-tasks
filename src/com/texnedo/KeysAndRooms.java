package com.texnedo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        HashMap<Integer, List<Integer>> unvisitedRooms = new HashMap<>();
        int roomIndex = 0;
        for (List<Integer> roomKeys : rooms) {
            unvisitedRooms.put(roomIndex++, roomKeys);
        }
        visitAllRooms(unvisitedRooms, 0);
        return unvisitedRooms.isEmpty();
    }

    private void visitAllRooms(HashMap<Integer, List<Integer>> unvisitedRooms, int currentRoom) {
        List<Integer> roomKeys = unvisitedRooms.remove(currentRoom);
        if (roomKeys == null) {
            return;
        }
        for (Integer key : roomKeys) {
            visitAllRooms(unvisitedRooms, key);
        }
    }
}
