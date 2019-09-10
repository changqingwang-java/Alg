package com.test;

import java.util.HashMap;
import java.util.Map;

/*
 *有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。

现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindCheapestPrice {
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if(src == dst){
            return 0;
        }

        Map<String,Integer> flightMap = new HashMap<String,Integer>();

        for(int i = 0; i < flights.length; i++){
            flightMap.put(flights[i][0] + "_" + flights[i][1], flights[i][2]);
        }

        int min  = flightMap.get(src + "_" + dst) == null ? Integer.MAX_VALUE : flightMap.get(src + "_" + dst);

        if(K == 0){
            return min == Integer.MAX_VALUE ? -1 : min;
        }

        int[][] feeTable = new int[n][K+1];

        for(int i = 0; i < n; i++){
            if(i == src || i == dst){
                continue;
            }

            Integer v = flightMap.get(src + "_" + i);

            if(v != null){
                Integer d = flightMap.get(i + "_" + dst);
                feeTable[i][1] = v;

                if(d != null){
                    min = Math.min(min,feeTable[i][1] + d);
                }
            }
        }

        for(int j = 2; j <= K ; j++){
            for(int i = 0; i < n; i++){
                for(int k = 0; k < n; k++){
                    if(i == src || i == dst || k == src || i == k || k == dst){
                        continue;
                    }

                    Integer fee = flightMap.get(i + "_" + k);

                    if(fee != null){
                        if(feeTable[i][j-1] > 0){
                            if(feeTable[k][j] == 0) {
                                feeTable[k][j] = feeTable[i][j - 1] + fee;
                            }
                            else{
                                feeTable[k][j] =  Math.min(feeTable[i][j - 1] + fee,feeTable[k][j]);
                            }

                            if(flightMap.get(k + "_" + dst) != null){
                                min = Math.min(min,feeTable[k][j] + flightMap.get(k + "_" + dst));
                            }
                        }
                    }
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        int[][] flights = new int[][]{
                {3,4,4},
                {2,5,6},
                {4,7,10},
                {9,6,5},
                {7,4,4},
                {6,2,10},
                {6,8,6},
                {7,9,4},
                {1,5,4},
                {1,0,4},
                {9,7,3},
                {7,0,5},
                {6,5,8},
                {1,7,6},
                {4,0,9},
                {5,9,1},
                {8,7,3},{1,2,6},{4,1,5},{5,2,4},{1,9,1},{7,8,10},{0,4,2},{7,2,8}
        };

        int src = 6;
        int dest = 0;
        int k = 7;
        int n = 10;

        System.out.println(findCheapestPrice(n,flights,src,dest,k));
    }
}
