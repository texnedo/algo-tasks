package com.texnedo;

import java.util.Arrays;

public class DistributeCandiesToPeople {
    public static void main(String[] asgs) {
        DistributeCandiesToPeople candies = new DistributeCandiesToPeople();
        System.out.println(Arrays.toString(candies.distributeCandies(60, 4)));
    }

    public int[] distributeCandies(int candies, int num_people) {
        int[] distribution = new int[num_people];
        int candiesToGive = 0;
        while (candies > 0) {
            for (int i = 0; i < distribution.length; i++) {
                candiesToGive++;
                if (candiesToGive > candies) {
                    distribution[i] += candies;
                    candies = 0;
                    break;
                }
                distribution[i] += candiesToGive;
                candies -= candiesToGive;
            }
        }
        return distribution;
    }

    public int[] distributeCandies2(int candies, int num_people) {
        int[] distribution = new int[num_people];
        int sumFirstPass = (1 + num_people) * 2;
        if (sumFirstPass >= candies) {
            int totalDistributed = 0;
            for (int i = 0; i < num_people; i++) {
                int current = i + 1;
                totalDistributed += current;
                if (totalDistributed > candies) {
                    break;
                }
                distribution[i] = current;
            }
        } else {
            //TODO - calculate how much full rows we can do to speed up the computation
            int fullPasses = (candies - sumFirstPass) / (num_people * num_people);
            int totalDistributed = 0;
            for (int i = 0; i < num_people; i++) {
                distribution[i] = i + 1;
                for (int j = 0; j < fullPasses; j++) {
                    distribution[i] += distribution[i] + num_people;
                }
                totalDistributed += distribution[i];
            }
            for (int i = 0; i < num_people; i++) {
                int current = distribution[i] + 1;
                totalDistributed += current;
                if (totalDistributed > candies) {
                    break;
                }
                distribution[i] = current;
            }
        }
        return distribution;
    }
}
