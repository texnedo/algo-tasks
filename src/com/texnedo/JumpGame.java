package com.texnedo;

import java.util.HashMap;

public class JumpGame {
    public static void main(String[] args) {
        JumpGame game = new JumpGame();
        if (args.length == 1) {
            String arrayString = args[0];
            String[] tokens = arrayString.split(",");
            int[] nums = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                nums[i] = Integer.parseInt(tokens[i]);
            }
            System.out.println(game.canJump(nums));
            return;
        }
        int[] nums = {3,2,1,0,4};
        System.out.println(game.canJump(nums));
    }

    public boolean canJump(int[] nums) {
        boolean[] computed = new boolean[nums.length];
        return canJumpInternal(nums, 0, computed);
    }

    public boolean canJumpInternal(int[] nums, int start, boolean[] computed) {
        int dist = nums.length - 1 - start;
        if (dist <= 0) {
            return true;
        }
        if (nums[start] >= dist) {
            return true;
        }
        if (nums[start] <= 0) {
            return false;
        }
        boolean checked = computed[start];
        if (checked) {
            return false;
        }
        boolean computedFromStart = false;
        for (int i = nums[start]; i >= 1; i--) {
            if (canJumpInternal(nums, start + i, computed)) {
                computedFromStart = true;
                break;
            }
        }
        computed[start] = true;
        return computedFromStart;
    }
}
