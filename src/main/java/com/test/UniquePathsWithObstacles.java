package com.test;

import java.util.ArrayList;
import java.util.List;

public class UniquePathsWithObstacles {
    public  static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null){
            return 0;
        }

        if(obstacleGrid[0][0] == 1){
            return 0;
        }

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(i == 0 && j == 0){
                    obstacleGrid[0][0] = 1;
                    continue;
                }

                if(obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = 0;
                    continue;
                }

                if(i == 0){
                    obstacleGrid[i][j] = obstacleGrid[0][j-1];
                }
                else if(j == 0){
                    obstacleGrid[i][j] = obstacleGrid[i-1][j];
                }
                else {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                }
            }
        }

        return  obstacleGrid[rows-1][cols-1];
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };

        System.out.println(uniquePathsWithObstacles(arr));
    }
}
