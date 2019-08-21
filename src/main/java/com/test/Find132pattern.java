package com.test;

public class Find132pattern {
    public boolean find132pattern(int[] nums) {
        if(nums == null || nums.length < 3){
            return false;
        }

        for(int i = 0; i < nums.length-2; i++){
            for(int j = i+1; j < nums.length-1; j++){
                if(nums[i] >= nums[j]){
                    continue;
                }

                for(int k = j+1; k < nums.length; k++){
                    if(nums[i] < nums[k] && nums[k] < nums[j]){
                        return true;
                    }
                }

                if(nums[nums.length-1] <= nums[i]){
                    return false;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Find132pattern find132pattern = new Find132pattern();

        int[] nums = {3,1,4,2};

        System.out.println(find132pattern.find132pattern(nums));
    }
}
