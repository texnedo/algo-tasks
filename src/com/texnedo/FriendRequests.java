package com.texnedo;

public class FriendRequests {
    /**
     * 20,30,100,110,120
     * ---------57---------100----------------------
     * ---------57---------100----------------------
     * */
    public int numFriendRequests(int[] ages) {
        int[] ageDict = new int[120];
        return 0;
    }

    /**
     * age[B] <= 0.5 * age[A] + 7
     * age[B] > age[A]
     * age[B] > 100 && age[A] < 100
     * */
    private static boolean isFriend(int ageA, int ageB) {
        if (ageB <= 0.5 * ageA + 7) {
            return false;
        }
        if (ageB > 100 && ageA < 100) {
            return false;
        }
        if (ageB > ageA){
            return false;
        }
        return true;
    }
}
