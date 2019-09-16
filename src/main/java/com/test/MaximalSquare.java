package com.test;

public class MaximalSquare {
    public static int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] table = new int[rows][cols];
        int max = 0;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                char c = matrix[i][j];

                if(c == '0'){
                    table[i][j] = 0;
                    continue;
                }
                else{
                    table[i][j] = 1;
                }

                if(i -1 >= 0 && j - 1 >= 0 && table[i-1][j-1] > 0){
                    int len = (int)Math.sqrt((int)table[i-1][j-1]);

                    int k = 1;

                    for(; k <= len; k++){
                        if(matrix[i-k][j] == '0'){
                            break;
                        }

                        if(matrix[i][j-k] == '0'){
                            break;
                        }
                    }

                    table[i][j] = Math.max(table[i][j],k * k);
                }

                if(max < table[i][j]){
                    max = table[i][j];
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'0','0','0','1'},
                {'1','1','0','1'},
                {'1','1','1','1'},
                {'0','1','1','1'},
                {'0','1','1','1'}
        };

        System.out.println(maximalSquare(matrix));
    }
}
