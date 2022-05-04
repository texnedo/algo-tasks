package com.texnedo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllGroupsFarmland {
    public static void main(String[] args) {
        int[][] data = {
                        {1,0,0},
                        {0,1,1},
                        {0,1,1}
                    };
        System.out.println(findFarmland(data));
        int[][] data2 = {
                {0,1},{1,0}
        };
        System.out.println(findFarmland(data2));
    }

    public static int[][] findFarmland(int[][] land) {
        List<int[]> result = new ArrayList<>();
        boolean[][] used = new boolean[land.length][land[0].length];
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if (land[i][j] == 1 && !used[i][j]) {
                    int imove = i;
                    while (imove < land.length && land[imove][j] == 1) {
                        imove++;
                    }
                    int jmove = j;
                    while (jmove < land[i].length && land[i][jmove] == 1) {
                        jmove++;
                    }
                    for (int k = i; k < imove; k++) {
                        Arrays.fill(used[k], j, jmove, true);
                    }
                    result.add(new int[] {i, j, imove - 1, jmove - 1});
                }
            }
        }
        int[][] resultArray = new int[result.size()][];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }
}
