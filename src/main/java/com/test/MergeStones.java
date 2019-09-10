package com.test;

public class MergeStones {
    public static int mergeStones(int[] stones, int K) {
        int len = stones.length;
        int total = 0;

        if(len == 1){
            return 0;
        }

        int[][] table = new int[len][len];

        for(int j = K - 1;j < len; j++){
            for(int i = j - K + 1; i >= 0 ; i--){
                int num = j - i + 1;

                if(num == K){
                    int sum = 0;
                    for(int k = i; k <= j; k++){
                        sum += stones[k];
                    }

                    table[i][j] = sum;
                }
                else if(K == 2) {
                    int seg = num - 1;

                    int min = Integer.MAX_VALUE;

                    for (int k = i; k <= i + 1; k++) {
                        int sum = table[k][k + seg - 1] * 2;

                        for (int k1 = i; k1 < k; k1++) {
                            sum += stones[k1];
                        }

                        for (int k1 = k + seg; k1 <= j; k1++) {
                            sum += stones[k1];
                        }

                        if (min > sum) {
                            min = sum;
                        }
                    }

                    if (i == j - 2) {
                        table[i][j] = Math.min(min, table[j-1][j] * 2 + stones[i]);
                    } else {
                        table[i][j] = Math.min(min, table[j-1][j] * 2 + table[i][j-2] * 2);
                    }
                }
                else if(num % (K-1) == 1){
                    int seg = num / (K-1);

                    int min = Integer.MAX_VALUE;

                    for(int k = i; k <= i + K -1; k++){
                        int sum = table[k][k + (seg - 1) * (K-1)] * 2;

                        for(int k1 = i; k1 < k; k1++){
                            sum += stones[k1];
                        }

                        for(int k1 = k + (seg - 1) * (K-1) + 1; k1 <= j; k1++){
                            sum += stones[k1];
                        }

                        if(min > sum) {
                            min = sum;
                        }
                    }

                    table[i][j] = min;

                    min = Integer.MAX_VALUE;

                    seg = (j - i + 1 - K)/(K-1);

                    for(int k = i; k <= j - K - (K-1); k++){
                        int sum = table[k][k + (seg - 1) * (K-1)] * 2;

                        for(int k1 = i; k1 < k; k1++){
                            sum += stones[k1];
                        }

                        for(int k1 = k + (seg - 1) * (K-1) + 1; k1 <= j - K; k1++){
                            sum += stones[k1];
                        }

                        if(min > sum) {
                            min = sum;
                        }
                    }

                    if(min < Integer.MAX_VALUE)
                        table[i][j] = Math.min(table[i][j],min + table[j-K+1][j] * 2);
                }
            }
        }

        return table[0][len-1] == 0 ? -1 : table[0][len-1];
    }

    public static void main(String[] args) {
        int[] stones = new int[]{3,5,1,2,6};

        System.out.println(mergeStones(stones,3));
    }
}
