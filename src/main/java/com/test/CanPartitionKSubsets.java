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

        int seg = sum / k;

        int[][] table = new int[rows+1][seg+1];

        for(int j = 1; j <= seg; j++){
            for(int i = 0; i < rows; i++){
                int num = nums[i];

                if(num == j){
                    table[i+1][j]++;
                }
                else if(num < j){
                    Set<Integer> set = new HashSet<Integer>();
                    set.add(i+1);

                    int left = j - num;

                    int max = 0;

                    for(int m = 0; m < i; m++){
                       if(max < table[m+1][left] && !set.contains(m+1)){
                           max = table[m+1][left];
                           set.add(m+1);
                       }
                    }

                   if(max > 0){
                       table[i+1][j] = Math.max(max,table[i+1][j]);
                   }
                }
            }
        }

        for(int i = 0; i < table.length; i++){
           for(int j = 0; j < table[0].length; j++){
               System.out.print(table[i][j] + " ");
           }

            System.out.println("");
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
