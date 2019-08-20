package com.test;

public class Jump {
    public static int jump(int[] nums) {
        int[] backup = new int[nums.length];

        backup[0] = 0;

        int lastMaxJumpPos = 0;

        for(int i = 0; i < nums.length - 1; i++){
            int jumpPos = nums[i] + i;

            if(jumpPos <= lastMaxJumpPos){
                continue;
            }

            if(jumpPos >= nums.length - 1){
                return backup[i] + 1;
            }

            int pos = Math.max(lastMaxJumpPos, i+1);

            for(int j = pos; j <= jumpPos;j++){
                if(backup[j] > 0){
                    if(backup[j] > backup[i] + 1){
                        backup[j] = backup[i] + 1;
                    }
                }
               else{
                    backup[j] = backup[i] + 1;
                }
            }

            lastMaxJumpPos = jumpPos;
        }

        return backup[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {1};

        System.out.println(jump(nums));
    }
}
