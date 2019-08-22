package com.test;

public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        if(s.equals(""))
            return "";

        int max = 1;
        int ind = 0;

        for(int i = 0; i < s.length() - 1; i++){
            char c = s.charAt(i);

            if(c == s.charAt(i+1)){
                int len = 2;;

                int j = i + 2;
                int k = i - 1;

                while(j < s.length() && k >= 0){
                    if(s.charAt(j) == s.charAt(k)){
                        len += 2;

                        j++;
                        k--;
                    }
                    else{
                        break;
                    }
                }

                if(max < len){
                    max = len;
                    ind = i;
                }
            }

            int j = i + 1;
            int k = i - 1;
            int len = 1;

            while(j < s.length() && k >= 0){
                if(s.charAt(j) == s.charAt(k)){
                    len += 2;

                    j++;
                    k--;
                }
                else{
                    break;
                }
            }

            if(max < len){
                max = len;
                ind = i;
            }
        }


        if(max == 1){
            return s.charAt(0) + "";
        }

        if(max % 2 == 0){
            return s.substring(ind - max / 2 + 1, ind + max / 2 + 1);
        }
        else{
            // 0 1 2 3 4
            return s.substring(ind - max / 2, ind + max / 2 + 1);
        }
    }

    // c a b a d
    public static void main(String[] args) {
        String s = "ccc";

        System.out.println(longestPalindrome(s));
    }
}
