package com.test;

public class CountBattleships {
    public static int countBattleships(char[][] board) {
        int count = 0;

        int rows = board.length;
        int cols = board[0].length;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(board[i][j] == 'x'){
                    int r = i;
                    int c = j;

                    count++;
                    board[i][j] = '.';

                    while(++c < cols && board[i][c] == 'x'){
                       board[i][c] = '.';
                       j = c;
                    }

                    while(++r < rows && board[r][j] == 'x'){
                        board[r][j] = '.';
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        char[][] arr = {
                {'x','.','x','.'},
                {'.','.','.','x'},
                {'.','.','.','x'}
        };

        System.out.println(countBattleships(arr));
    }
}
