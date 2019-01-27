package com.texnedo;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        ProductOfArrayExceptSelf self = new ProductOfArrayExceptSelf();
        int[] data = {1,2,3,4};
        System.out.println(Arrays.toString(self.productExceptSelf(data)));
    }


    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        int[] upstream = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            upstream[i] = product;
            product *= nums[i];
        }
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            upstream[i] *= product;
            product *= nums[i];
        }
        return upstream;
    }
}
