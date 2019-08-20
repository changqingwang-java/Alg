package com.test;

import java.math.BigDecimal;

public class Candies {
    public int[] distributeCandies(int candies, int num_people) {
        int leftCandies = candies;

        int last = 0;

        int[] result = new int[num_people];

        for(int i = 0; i < num_people; i++){
            result[i] = 0;
        }

        int i = 0;

        while(leftCandies > 0){
            result[i++ % num_people] += ++last <= leftCandies ? last : leftCandies;

            leftCandies -= last;
        }

        return result;
    }

    public static void main(String[] args) {
       Double d = new Double("199.68");

       BigDecimal big = new BigDecimal(199.68);
       BigDecimal dd = new BigDecimal(d);

       System.out.println(big.compareTo(dd));
    }
}
