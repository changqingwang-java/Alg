package com.test;

public class NumMagicSquaresInside {
    public int numMagicSquaresInside(int[][] grid) {
        if(grid == null)
            return 0;

        int count = 0;
        int cols = grid[0].length;
        int rows = grid.length;

        for(int pos_r = 0;pos_r <= rows - 3; pos_r++){
            for(int pos_c = 0;pos_c <= cols - 3;pos_c++){
                if(grid[pos_r][pos_c] < 1 || grid[pos_r][pos_c] > 9 ||
                        grid[pos_r][pos_c+1] < 1 || grid[pos_r][pos_c+1] > 9 ||
                        grid[pos_r][pos_c+2] < 1 || grid[pos_r][pos_c+2] > 9 ||
                        grid[pos_r+1][pos_c] < 1 || grid[pos_r+1][pos_c] > 9 ||
                        grid[pos_r+1][pos_c+1] < 1 || grid[pos_r+1][pos_c+1] > 9 ||
                        grid[pos_r+1][pos_c+2] < 1 || grid[pos_r+1][pos_c+2] > 9 ||
                        grid[pos_r+2][pos_c] < 1 || grid[pos_r+2][pos_c] > 9 ||
                        grid[pos_r+2][pos_c+1] < 1 || grid[pos_r+2][pos_c+1] > 9 ||
                        grid[pos_r+2][pos_c+2] < 1 || grid[pos_r+2][pos_c+2] > 9){
                    continue;
                }

                if(grid[pos_r][pos_c] == grid[pos_r][pos_c+1] && grid[pos_r][pos_c] == grid[pos_r][pos_c+2] &&
                        grid[pos_r][pos_c] == grid[pos_r+1][pos_c+1] && grid[pos_r][pos_c] == grid[pos_r+1][pos_c+2] &&
                        grid[pos_r][pos_c] == grid[pos_r+2][pos_c+1] && grid[pos_r][pos_c] == grid[pos_r+2][pos_c+2]){
                    continue;
                }

                int sum = grid[pos_r][pos_c] + grid[pos_r][pos_c + 1] + grid[pos_r][pos_c + 2];

                if(grid[pos_r+1][pos_c] + grid[pos_r+1][pos_c + 1] + grid[pos_r+1][pos_c + 2] != sum){
                    continue;
                }

                if(grid[pos_r+2][pos_c] + grid[pos_r+2][pos_c + 1] + grid[pos_r+2][pos_c + 2] != sum){
                    continue;
                }

                if(grid[pos_r][pos_c] + grid[pos_r+1][pos_c] + grid[pos_r+2][pos_c] != sum){
                    continue;
                }

                if(grid[pos_r][pos_c+1] + grid[pos_r+1][pos_c + 1] + grid[pos_r+2][pos_c + 1] != sum){
                    continue;
                }

                if(grid[pos_r][pos_c+2] + grid[pos_r+1][pos_c + 2] + grid[pos_r+2][pos_c + 2] != sum){
                    continue;
                }

                if(grid[pos_r][pos_c] + grid[pos_r+1][pos_c + 1] + grid[pos_r+2][pos_c + 2] != sum){
                    continue;
                }

                if(grid[pos_r+2][pos_c] + grid[pos_r+1][pos_c + 1] + grid[pos_r][pos_c + 2] != sum){
                    continue;
                }

                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumMagicSquaresInside numMagicSquaresInside = new NumMagicSquaresInside();
        int[][] grid = {
                {10, 3, 5},
                {1,6,11},
                {7,9,2}
                };

        int i = numMagicSquaresInside.numMagicSquaresInside(grid);

        System.out.println(i);
    }
}
