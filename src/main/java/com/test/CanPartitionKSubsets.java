package com.test;

/*
给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等
 */
public class CanPartitionKSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || k <= 0)
            return false;

        if(k == 1 )
            return true;

        int sum = 0;

        for(int i : nums){
            sum += i;
        }

        if(sum % k != 0){
            return false;
        }

        int seg = sum / k;


        return false;
    }

    public static void main(String[] args) {

    }
}
