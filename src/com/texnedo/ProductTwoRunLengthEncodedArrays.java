package com.texnedo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductTwoRunLengthEncodedArrays {
    public static void main(String[] args) {
        ProductTwoRunLengthEncodedArrays arrays = new ProductTwoRunLengthEncodedArrays();
        int[][] data1 = {{1,1},{2,1},{1,1},{2,1},{1,1}};
        int[][] data2 = {{1,1},{2,1},{1,1},{2,1},{1,1}};
        System.out.println(arrays.findRLEArray(data1, data2));
    }

    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        int index1 = 0;
        int index2 = 0;
        LinkedList<List<Integer>> result = new LinkedList<>();
        while (index1 < encoded1.length && index2 < encoded2.length) {
            List<Integer> product = null;
            //equal length parts
            if (encoded1[index1][1] == encoded2[index2][1]) {
                product = new ArrayList<>(2);
                product.add(encoded1[index1][0] * encoded2[index2][0]);
                product.add(encoded1[index1][1]);
                index1++;
                index2++;
            } else if (encoded1[index1][1] > encoded2[index2][1]) {
                product = new ArrayList<>(2);
                product.add(encoded1[index1][0] * encoded2[index2][0]);
                product.add(encoded2[index2][1]);
                encoded1[index1][1] -= encoded2[index2][1];
                index2++;
            } else if (encoded1[index1][1] < encoded2[index2][1]) {
                product = new ArrayList<>(2);
                product.add(encoded1[index1][0] * encoded2[index2][0]);
                product.add(encoded1[index1][1]);
                encoded2[index2][1] -= encoded1[index1][1];
                index1++;
            }
            if (product == null) {
                throw new IllegalArgumentException();
            }
            if (!result.isEmpty()) {
                List<Integer> last = result.getLast();
                if (last.get(0).equals(product.get(0))) {
                    last.set(1, last.get(1) + product.get(1));
                } else {
                    result.add(product);
                }
            } else {
                result.add(product);
            }
        }
        return result;
    }
}
