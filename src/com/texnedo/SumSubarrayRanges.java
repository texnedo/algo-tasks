package com.texnedo;

import java.util.*;

public class SumSubarrayRanges {
    public static void main(String[] args) {
        int[] data = {1,3,3};
        System.out.println(subArrayRanges(data));
        System.out.println(subArrayRangesNaive(data));
        int[] data1 = {4,-2,-3,4,1};
        System.out.println(subArrayRanges(data1));
        System.out.println(subArrayRangesNaive(data1));
        int[] data2 = {9911,-90135,-89738,35019,-56095,-18837,-81924,96768,83709,42105,49101,-83708,29516,33928,-5479,-44214,-22153,-76747,-99356,76807,-12231,46985,-12087,21060,43150,-23670,-23204,-65344,81818,76776,30956,18479,-44424,-76224,-87457,-96291,94823,-39334,-43175,-29222,20058,-44259,-95096,14744,38890,96360,10996,70237,-22899,46971,93496,55180,32236,98831,-14677,39989,-1402,-67256,-52962,-604,62508,-68350,-12018,-17920,-54431,93459,-15339,6145,-10386,-75337,-84230,-79745,73742,-87198,-65921,-63230,-68034,60918,69939,93521,45833,-75382,38376,-8957,-44605,1517,78042,-30046,-51203,1268,64486,-88572,78765,-43640,22977,35918,-60240,-6190,-24904,66546,56998,56506,-84878,-25106,53291,25918,-73575,13181,22302,54475,-76365,-1235,-39069,8193,-35294,-24390,26334,-33051,10653,30843,-42931,-73080,74495,-58822,-89474,-58196,39872,-82220,4470,-87717,-7033,73446,30723,52996,97766,-80240,-96249,-71354,-2716,66330,-57731,-4861,77914,-75169,15435,36157,-31931,37208,31071,-77016,66022,-83770,78090,26773,-3918,-87435,-72431,12594,70775,-64570,6119,-49410,-96924,-18961,40174,-78097,73845,88870,94993,66352,-30670,65667,-47589,49699,11263,69895,-48291,-40686,-65865,96078,54011,57242,619,97356,7051,-42711,40022,-22064,-63468,-60426,-76583,-88342,71161,3485,39743,-99920,-19040,-53913,-11736,63224,-88721,35258,-41322,24492,29518,91582,-94250,-26142,-3023,90755,97269,92330,1596,-2177,-21206,72675,42519,-79034,-39951,-78073,-58455,-72284,66996,-72352,81964,73171,2110,-2433,-52821,-3283,-54448,41248,67560};
        System.out.println(subArrayRanges(data2));
        System.out.println(subArrayRangesNaive(data2));
    }

    public static long subArrayRanges(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return 0;
        }
        long sum = 0;
        LinkedList<Integer> window = new LinkedList<>();
        for (int windowSize = 2; windowSize <= nums.length; windowSize++) {
            for (int i = 0; i <= nums.length; i++) {
                if (window.size() == windowSize) {
                    ArrayList<Integer> sorted = new ArrayList<>(window);
                    Collections.sort(sorted);
                    long hi = sorted.get(sorted.size() - 1);
                    long low = sorted.get(0);
                    sum += hi - low;
                    window.pollFirst();
                }
                if (window.size() < windowSize && i < nums.length) {
                    window.offerLast(nums[i]);
                }
            }
            window.clear();
        }
        return sum;
    }

    public static long subArrayRangesNaive(int[] nums) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int hi = nums[i];
            int low = nums[i];
            for (int j = i; j < nums.length; j++) {
                hi = Math.max(hi, nums[j]);
                low = Math.min(low, nums[j]);
                sum += hi - low;
            }
        }
        return sum;
    }

    public static long subArrayRangesFast(int[] nums) {
        long[] result = subArrayRangesFastInternal(nums, 0, nums.length - 1);
        return result[3];
    }

    public static long[] subArrayRangesFastInternal(int[] nums, int start, int end) {
        if (start == end || start > end) {
            //min, max, range, sum
            return new long[] {0, 0, 0, 0};
        }
        long[] previous = subArrayRangesFastInternal(nums, 0, end - 1);
        long pmin = previous[0];
        long pmax = previous[1];
        long prange = previous[2];
        long psum = previous[3];
        if (nums[end] >= pmin && nums[end] <= pmax) {
            return new long[] {pmin, pmax, prange, psum + prange};
        } else if (nums[end] < pmin) {
            long range = pmax - nums[end];
            return new long[] {nums[end], pmax, range, psum + range};
        } else {
            long range = nums[end] - pmin;
            return new long[] {pmin, nums[end], range, psum + range};
        }
    }
}
