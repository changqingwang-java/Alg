package com.test;

public class NumTrees {
    public static int numTrees(int n) {
        if(n <= 0)
            return 0;

        if(n == 1){
            return 1;
        }

        int[][] table = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            table[i][i] = 1;
        }

        for(int i = 1; i <= n; i++){
            //找一个j，计算table[j][i]
            for(int j = i - 1; j >= 1; j--){
                //为了计算table[j][i],找个k

                for(int k = j ; k <= i; k++){
                    if(k == j){
                        table[j][i] += table[k + 1][i];
                    }
                    else if(k == i){
                        table[j][i] += table[j][k-1];
                    }
                    else {
                        table[j][i] += table[j][k - 1] * table[k + 1][i];
                    }
                }
            }
        }

        return table[1][n];
    }

    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }
}
