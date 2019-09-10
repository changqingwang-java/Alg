package com.test;

public class CountNumbersWithUniqueDigits {
    public static int countNumbersWithUniqueDigits(int n) {
        if(n == 0){
            return 1;
        }

        if(n == 1){
            return 10;
        }

        if(n == 2){
            return 91;
        }

        int[] lastDiff = new int[n];
        lastDiff[0] = 10;
        lastDiff[1] = 81;

        for(int i = 3; i<= n; i++){
            int bit = i-1;
            lastDiff[bit] = lastDiff[bit-1] * (10 - bit);

            System.out.println(bit + ":" + lastDiff[bit]);
        }

        int diff = 0;

        for(int i = 0; i < lastDiff.length; i++){
            diff += lastDiff[i];
        }

        return diff;
    }

    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(4));
    }
}
