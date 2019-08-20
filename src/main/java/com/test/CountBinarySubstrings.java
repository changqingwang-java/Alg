package com.test;

import java.math.BigDecimal;

public class CountBinarySubstrings {
    public static int countBinarySubstrings(String s){
        if(s == null || s.trim().equals("")){
            return 0;
        }

        int count = 0;

        int i = 0;
        int num = 0;

        while(i < s.length()){
            int num1 = 0;
            char c1 = s.charAt(i);

            for(int j = i; j < s.length(); j++){
                if(s.charAt(j) == c1){
                    num1++;
                }
                else{
                    break;
                }
            }

            int num2 = 0;

            for(int j = i + num1; j < s.length(); j++){
                if(s.charAt(j) != c1){
                    num2++;
                }
                else{
                    break;
                }

                if(num2 >= num + num1)
                    break;
            }

            count += (num+num1) < num2 ? (num+num1) : num2;
            i += num1;

            if(num2 > 0){
                num = num2 - 1;
                i += num;
            }
            else{
                num = 0;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(countBinarySubstrings("00110"));
    }
}
