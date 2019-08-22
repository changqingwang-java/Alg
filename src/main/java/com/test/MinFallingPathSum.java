package com.test;

import java.security.AlgorithmParameterGenerator;

/*
给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。

下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-falling-path-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinFallingPathSum {
    public int minFallingPathSum(int[][] A) {
        if(A == null)
            return 0;

        int rows = A.length;
        int cols = A[0].length;

        int[][] B = new int[rows][cols];

        for(int i = 0; i < cols; i++){
            B[0][i] = A[0][i];
        }

        for(int r = 1; r < rows; r++){
            for(int j = 0; j < cols; j++){
                int left = j - 1 >= 0 ? j - 1 : 0;
                int right = j + 1 < cols ? j + 1 : cols - 1;

                int min = Math.min(Math.min(B[r-1][left],B[r-1][j]),B[r-1][right]);

                B[r][j] = A[r][j] + min;
            }
        }

        int min = B[rows-1][0];

        for(int j = 1; j < cols; j++){
            if(min > B[rows-1][j]){
                min = B[rows-1][j];
            }
        }

        return min;
    }

    public static void main(String[] args) {
        int[][] A = {{1,2,3},{4,5,6},{7,8,9}};
;       MinFallingPathSum minFallingPathSum = new MinFallingPathSum();
        System.out.println(minFallingPathSum.minFallingPathSum(A));
    }
}
