package com.test;

import java.util.HashSet;
import java.util.Set;

public class FindSubstringInWraproundString {
    public static int findSubstringInWraproundString(String p) {
        if(p == null){
            return 0;
        }

        int length = p.length();

        int[] set = new int[26];

        for(int i = 0; i < length ;){
            int curInd = p.charAt(i) - 'a';
            int maxLen = 1;

            for(int j = i + 1; j < length; j++){
                int nextInd = p.charAt(j) - 'a';
                int tmpInd = p.charAt(j-1) - 'a';
                if(nextInd - tmpInd == 1 || tmpInd - nextInd == 25){
                    maxLen++;
                }
                else{
                    break;
                }
            }

            set[curInd] = Math.max(maxLen,set[curInd]);

            for(int j = 1; j < maxLen; j++){
                int nextInd = p.charAt(i + j) - 'a';
                set[nextInd] = Math.max(maxLen - j,set[nextInd]);
            }

            i += maxLen;
        }

        int sum = 0;
        for(int i = 0; i < set.length; i++){
            sum += set[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(findSubstringInWraproundString("cdefghefghijklmnopqrstuvwxmnijklmnopqrstuvbcdefghijklmnopqrstuvwabcddefghijklfghijklmabcdefghijklmnopqrstuvwxymnopqrstuvwxyz"));
    }
}
