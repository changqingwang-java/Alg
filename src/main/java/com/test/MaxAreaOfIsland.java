package com.test;

import java.util.Stack;

public class MaxAreaOfIsland {
    public static int maxAreaOfIsland(int[][] grid) {
        Stack<String> stack = new Stack<String>();

        int rows = grid.length;
        int cols = grid[0].length;

        int maxArea = 0;

        for(int i = 0; i < rows; i++){
            for(int j = 0;j < cols; j++){
               if(grid[i][j] == 1){
                   stack.push(i+"_"+j);

                   int area = 0;

                   while(!stack.isEmpty()){
                       String s = stack.pop();

                       String[] splits = s.split("_");

                       int r = Integer.valueOf(splits[0]);
                       int c = Integer.valueOf(splits[1]);

                       if(grid[r][c] == 1){
                           area += 1;
                       }

                       grid[r][c] = 0;

                       if(r - 1 >= 0 && grid[r-1][c] == 1){
                           stack.push((r-1) + "_" + c);
                       }

                       if(c + 1 < cols && grid[r][c+1] == 1){
                           stack.push(r + "_" + (c + 1));
                       }

                       if(r + 1 < rows && grid[r+1][c] == 1){
                           stack.push((r+1) + "_" + c);
                       }

                       if(c - 1 >= 0 && grid[r][c-1] == 1){
                           stack.push(r + "_" + (c-1));
                       }
                   }

                   if(area > maxArea)
                       maxArea = area;
               }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] arr = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};

        System.out.println(maxAreaOfIsland(arr));
    }
}
