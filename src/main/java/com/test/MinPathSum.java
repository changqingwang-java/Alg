package com.test;

public class MinPathSum {
    public static int minPathSum(int[][] grid) {
        if(grid == null){
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        for(int i = 0; i< rows; i++){
            for(int j = 0; j < cols; j++){
                if(i == 0 && j == 0){
                    continue;
                }

                if(i == 0){
                    grid[i][j] += grid[i][j-1];
                }
                else if(j == 0){
                    grid[i][j] += grid[i-1][j];
                }
                else{
                    grid[i][j] += Math.min(grid[i][j-1],grid[i-1][j]);
                }
            }
        }

        return grid[rows-1][cols-1];
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };

        System.out.println(minPathSum(arr));
    }
}
