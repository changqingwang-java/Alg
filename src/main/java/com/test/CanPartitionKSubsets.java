package com.test;


import java.util.*;

/*
给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等
 */
public class CanPartitionKSubsets {
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || k <= 0)
            return false;

        if( k == 1 )
            return true;

        int sum = 0;

        for(int i : nums){
            sum += i;
        }

        if(sum % k != 0){
            return false;
        }

        int rows = nums.length;

        for(int seg = 2; seg <= k; seg++){

        }

        return false;
    }

    public static void main(String[] args) {
        // 1 1 3 3 4 5
        int[] nums = new int[]{1,1,2,2,4,5};
        //int[] nums = new int[]{6,6,6,7,7,7,7,7,7,10,10,10};
        //int[] nums = new int[]{1,1,1,1,1,1,1,1,1,1};
        System.out.println(canPartitionKSubsets(nums,3));
    }
}
