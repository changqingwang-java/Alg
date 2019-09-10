package com.test;

/*
给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 */
public class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        if(amount == 0)
            return 0;

        int[] table = new int[amount+1];

        for(int j = 0; j < amount+1; j++){
            table[j] = Integer.MAX_VALUE;
        }

        int cols = amount;
        int rows = coins.length;

        for(int j = 1; j <= cols; j++){
            for(int i = 0; i < rows; i++){
                if(coins[i] == j){
                    table[j] = 1;
                }
                else if(coins[i] < j){
                    int m = 1;
                    int left = j;

                    while(true){
                        left -= coins[i];

                        if(left < 0)
                            break;

                        if(left == 0){
                            table[j] = Math.min(table[j],m);
                            break;
                        }

                        if(table[left] < Integer.MAX_VALUE){
                            table[j] = Math.min(table[j],table[left] + m);
                        }

                        m++;
                    }
                }
            }
        }


        return table[cols] < Integer.MAX_VALUE ? table[cols] : -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,5};

        System.out.println(coinChange(arr,11));
    }
}
