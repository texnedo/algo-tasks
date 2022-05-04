package com.texnedo;

import java.util.Arrays;
import java.util.Comparator;

public class Search2DMatrix {
    public static void main(String[] args) {
        int[][] data = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println(searchMatrix(data, 3));
        System.out.println(searchMatrix(data, 11));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int rowIndex = Arrays.binarySearch(
                matrix,
                new int[]{target, target},
                (o1, o2) -> {
                    if (o1[o1.length - 1] < o2[0]) {
                        return -1;
                    }
                    else if (o1[0] <= o2[0] && o2[o2.length - 1] <= o1[o1.length - 1] ||
                            o2[0] <= o1[0] && o1[o1.length - 1] <= o2[o2.length - 1]) {
                        return 0;
                    }
                    return 1;
                });
        if (rowIndex < 0) {
            return false;
        }
        int finalIndex = Arrays.binarySearch(matrix[rowIndex], target);
        if (finalIndex < 0) {
            return false;
        }
        return true;
    }
}
