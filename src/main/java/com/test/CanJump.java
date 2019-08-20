package com.test;

public class CanJump {
    public static boolean canJump(int[] nums) {
        boolean[] backup = new boolean[nums.length];

        backup[0] = true;

        int lastJumpPos = 0;

        for(int i = 0; i < nums.length; i++){
            if(backup[i]){
                int maxJumpPos = nums[i] + i;

                if(maxJumpPos <= lastJumpPos){
                    continue;
                }

                if(maxJumpPos < nums.length){
                    for(int j = lastJumpPos + 1; j <= maxJumpPos; j++){
                        backup[j] = true;
                    }
                }
                else {
                    return true;
                }

                lastJumpPos = maxJumpPos;
            }
            else{
                return false;
            }
        }

        return backup[nums.length - 1];
    }

    public static void main(String[] args) {
      int[] nums = new int[5];

      for(int i = 0; i < nums.length; i++){
          System.out.println(nums[i]);
      }
    }
}
