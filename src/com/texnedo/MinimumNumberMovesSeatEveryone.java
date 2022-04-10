package com.texnedo;

import java.util.Arrays;
import java.util.HashSet;

public class MinimumNumberMovesSeatEveryone {
    public static void main(String[] args) {
        MinimumNumberMovesSeatEveryone meet = new MinimumNumberMovesSeatEveryone();
        int[] seats = {3,1,5};
        int[] students = {2,7,4};
        System.out.println(meet.minMovesToSeat(seats, students));
    }

    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        HashSet<Integer> available = new HashSet<>();
        for (int i = 0; i < seats.length; i++) {
            available.add(seats[i]);
        }
        int moves = 0;
        int indexSeats = 0;
        for (int indexStudents = 0; indexStudents < students.length; indexStudents++) {
            if (available.contains(students[indexStudents])) {
                available.remove(students[indexStudents]);
            } else {
                while (!available.contains(seats[indexSeats])) {
                    indexSeats++;
                }
                int diff = Math.abs(students[indexStudents] - seats[indexSeats]);
                moves += diff;
                indexSeats++;
            }
        }
        return moves;
    }
}
