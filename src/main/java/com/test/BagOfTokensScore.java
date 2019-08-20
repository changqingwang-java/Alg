package com.test;

public class BagOfTokensScore {
    private static void quickSort(int[] A, int left, int right){
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

        quickSort(A,left,li);
        quickSort(A,li+1,right);
    }

    public static int bagOfTokensScore(int[] tokens, int P) {
        quickSort(tokens,0,tokens.length-1);

        int sum = 0;

        for(int i = 0; i< tokens.length; i++){
            sum += tokens[i];
        }

        int[] scores = new int[sum];

        scores[0] = 0;
        int lastInd = 0;
        int ptoken = 0;

        for(int i = 1; i < sum; i++){
            if( i <= ptoken){
                scores[i] = scores[i-1];
            }
            else {
                if(lastInd == tokens.length)
                    break;

                ptoken += tokens[lastInd];
                scores[i] = scores[i-1] + 1;
                lastInd++;
            }
        }

        int[] ptokes = new int[tokens.length];

        for(int i = 0; i < tokens.length; i++){
            int t1 = 0;
            int t2 = 0;

            if(tokens[i] <= P){
                if(i == 0){
                    t1 = 1;
                }
                else{
                    t1 += ptokes[i-1] + 1;
                }

                P -= tokens[i];
            }
            else{
                if(i == 0){
                    t1 = 0;
                }
                else{
                    t1 += ptokes[i-1] + 1;
                }
            }

        }

        return 0;
    }

    public static void main(String[] args) {

    }
}
