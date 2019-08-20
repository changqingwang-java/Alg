package com.test;

public class MovesToMakeZigzag {
    public static int movesToMakeZigzag(int[] nums) {
        int odd = 0, even = 0;
        int left = 0;

        for(int i = 0; i < nums.length; i++){
            if(i % 2 == 1)
                continue;

            if( i == 0){
                if(nums[i] > nums[i+1]){
                    odd = 0;
                    left = nums[i+1];
                }
                else if(nums[i] == nums[i+1]){
                    odd = 1;
                    left = nums[i+1] - 1;
                }
                else{
                    odd += nums[i+1] - nums[i] + 1;
                    left = nums[i] - 1;
                }
            }
            else if(i == nums.length - 1){
                if(nums[i] == left){
                    odd += 1;
                }
                else if(nums[i] < left){
                    odd += left - nums[i] + 1;
                }
            }
            else {
                int right = nums[i+1];

                if(left > nums[i]){
                    if(nums[i] < right){
                        odd += left - nums[i] + 1 + right - nums[i] + 1;
                        left = nums[i] - 1;
                    }
                    else if(nums[i] == right){
                        odd += left - nums[i] + 1 + right - nums[i] + 1;
                        left = nums[i] - 1;
                    }
                    else{
                        odd += left - nums[i] + 1;
                        left = nums[i+1];
                    }
                }
                else if(left == nums[i]){
                    if(nums[i] < right){
                        odd += right - nums[i] + 2;
                        left = nums[i] - 1;
                    }
                    else if(right == nums[i]){
                        odd += 2;
                        left = nums[i] - 1;
                    }
                    else{
                        odd += 1;
                        left = nums[i+1];
                    }
                }
                else{
                    if(nums[i] < right){
                        odd += right - nums[i] + 1;
                        left = nums[i] - 1;
                    }
                    else if(nums[i] == right){
                        odd +=  1;
                        left = nums[i] - 1;
                    }
                    else{
                        left = nums[i+1];
                    }
                }
            }
        }

        for(int i = 0; i < nums.length; i++){
            if(i % 2 == 1)
                continue;

            if( i == 0){
                if(nums[i] > nums[i+1]){
                    even = nums[i] - nums[i+1] + 1;
                }
                else if(nums[i] == nums[i+1]){
                    even = 1;
                }
            }
            else if(i == nums.length - 1){
                if(nums[i] > nums[i-1]){
                    even += nums[i] - nums[i-1] + 1;
                }
                else if(nums[i] == nums[i-1]){
                    even += 1;
                }
            }
            else {
                int right = nums[i+1];

                if(nums[i-1] > nums[i]){
                    if(nums[i] == right){
                        even += 1;
                    }
                    else if(nums[i] > right){
                        even += nums[i] - right + 1;
                    }
                }
                else if(nums[i-1] == nums[i]){
                    if(nums[i] < right){
                        even += 1;
                    }
                    else if(right == nums[i]){
                        even += 1;
                    }
                    else{
                        even += nums[i] - right + 1;
                    }
                }
                else{
                    if(nums[i] < right){
                        even += nums[i] - nums[i-1] + 1;
                    }
                    else if(nums[i] == right){
                        even += nums[i] - nums[i-1] + 1;
                    }
                    else{
                        even += Math.max(nums[i] - nums[i-1] + 1, nums[i] - right + 1);
                    }
                }
            }
        }

        return Math.min(odd,even);
    }

    public static void main(String[] args) {
        int[] nums = {9,6,1,6,2};
        System.out.println(movesToMakeZigzag(nums));
    }
}
