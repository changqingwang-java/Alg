package com.test;

public class MaxProduct {
    public static int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int len = nums.length;

        int[] table1 = new int[len];
        int[] table2 = new int[len];

        if(nums[0] > 0) {
            table1[0] = nums[0];
        }
        else {
            table2[0] = nums[0];
        }

        int max = nums[0];

        for(int i = 1; i < len; i++){
           if(nums[i] > 0){
               table1[i] = Math.max(table1[i-1] * nums[i],nums[i]);
               table2[i] = Math.min(table2[i-1] * nums[i],0);
           }
           else if(nums[i] == 0){
               table1[i] = 0;
               table2[i] = 0;
           }
           else{
               if(table2[i-1] == 0){
                   table1[i] = nums[i];
               }
               else{
                   table1[i] = Math.max(table2[i-1] * nums[i],nums[i]);
               }

               if(table1[i-1] == 0){
                   table2[i] = nums[i];
               }
               else{
                   table2[i] = Math.min(table1[i-1] * nums[i],nums[i]);
               }
           }

           max = Math.max(max,table1[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{
               2,-5,-2,-4,3
        };
        System.out.println(maxProduct(nums));
    }
}
