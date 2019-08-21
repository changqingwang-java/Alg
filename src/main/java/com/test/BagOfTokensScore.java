package com.test;

public class BagOfTokensScore {
    private  void quickSort(int[] A, int left, int right){
        if(left >= right){
            return;
        }
        int li = left;
        int ri = right;
        int m = A[li];

        while(true){
            while(ri > li){
                if(A[ri] >= A[li]){
                    ri--;
                }
                else{
                    int n = A[li];
                    A[li] = A[ri];
                    A[ri] = n;
                    break;
                }
            }

            while(li < ri){
                if(A[li] <= A[ri]){
                    li++;
                }
                else{
                    int n = A[li];
                    A[li] = A[ri];
                    A[ri] = n;
                    break;
                }
            }

            if(li == ri){
                A[li] = m;
                break;
            }
        }

        quickSort(A,left,li-1);
        quickSort(A,li+1,right);
    }

    public  int bagOfTokensScore(int[] tokens, int P) {
        if(tokens.length == 0)
            return 0;

        quickSort(tokens,0,tokens.length-1);

        if(tokens[0] > P)
            return 0;

        int score = 0;

        int i = 0;
        int j = tokens.length;

        while(true){
            if(score <= 0){
                if(i < j && tokens[i] <= P){
                    score++;
                    P -= tokens[i];
                    i++;
                }
            }
            else if(i < j && tokens[i] <= P){
                score++;
                P -= tokens[i];
                i++;
            }
            else{
                if(j - 1 > i){
                    score--;
                    P += tokens[--j];
                }
                else{
                    break;
                }
            }
        }

        return score;
    }

    public static void main(String[] args) {
       int[] tokens = {26};
       int P = 51;
        BagOfTokensScore bagOfTokensScore = new BagOfTokensScore();

        System.out.println(bagOfTokensScore.bagOfTokensScore(tokens,P));

    }
}
