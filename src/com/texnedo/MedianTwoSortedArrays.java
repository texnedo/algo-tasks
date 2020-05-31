package com.texnedo;

public class MedianTwoSortedArrays {
    public static void main(String[] args) {
        MedianTwoSortedArrays arrays = new MedianTwoSortedArrays();
        int[] data1 = {1,2,3,7,9,10,11,12};
        int[] data2 = {3,4,6,8};
        System.out.println(arrays.findMedianSortedArrays(data1, data2));
        int[] data3 = {1,2,3};
        int[] data4 = {};
        System.out.println(arrays.findMedianSortedArrays(data3, data4));
        int[] data5 = {};
        int[] data6 = {1,2,3};
        System.out.println(arrays.findMedianSortedArrays(data5, data6));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalSize = nums1.length + nums2.length;
        if (totalSize == 0) {
            throw new IllegalArgumentException();
        }
        int targetSize = totalSize / 2 + 1;
        int[] result = new int[targetSize];
        int index1 = 0, index2 = 0;
        int insertIndex = 0;
        while (insertIndex < result.length &&
                (index1 < nums1.length || index2 < nums2.length)) {
            if (index1 == nums1.length) {
                while (insertIndex < result.length) {
                    result[insertIndex++] = nums2[index2++];
                }
            } else if (index2 == nums2.length) {
                while (insertIndex < result.length) {
                    result[insertIndex++] = nums1[index1++];
                }
            } else {
                if (nums1[index1] <= nums2[index2]) {
                    result[insertIndex++] = nums1[index1++];
                } else {
                    result[insertIndex++] = nums2[index2++];
                }
            }
        }
        if (totalSize % 2 == 0) {
            return (result[result.length - 1] + result[result.length - 2]) / 2.0;
        }
        return result[result.length - 1];
    }
}
