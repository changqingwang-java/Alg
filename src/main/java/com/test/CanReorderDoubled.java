package com.test;

import java.util.*;

/*
 0 1 2 3 4 5 6 7
给定一个长度为偶数的整数数组 A，只有对 A 进行重组后可以满足 “对于每个 0 <= i < len(A) / 2，
都有 A[2 * i + 1] = 2 * A[2 * i]” 时，返回 true；否则，返回 false。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/array-of-doubled-pairs
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanReorderDoubled {
    private void quickSort(int[] A, int left, int right){
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

    public  int binarySearch(int[] A, int a, int left, int right){
       while(left <= right){
           int mid = (left + right) / 2;

           if(A[mid] == a){
               return mid;
           }
           else if(A[mid] < a){
               left = mid + 1;
           }
           else{
               right = mid - 1;
           }
       }

       return -1;
    }

    public  boolean canReorderDoubled(int[] A) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();

        for(int i = 0;  i < A.length; i++){
            Integer v = treeMap.get(A[i]);

            if(v == null){
                treeMap.put(A[i],1);
            }
            else{
                treeMap.put(A[i],v+1);
            }
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = treeMap.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            int key = next.getKey();
            int value = next.getValue();

            if(value <= 0)
                continue;

            if(key < 0){
                if(key % 2 == 0){
                    int key2 = key / 2;

                    Integer find = treeMap.get(key2);

                    if(find == null || find <= 0){
                        return false;
                    }
                    else{
                        if(value > find.intValue()){
                            return false;
                        }

                        treeMap.put(key2, find - value);
                    }
                }
                else{
                    return false;
                }
            }
            else if(key == 0){
                if(value % 2 != 0){
                    return false;
                }
            }
            else{
                int key2 = key * 2;

                Integer find = treeMap.get(key2);

                if(find == null || find <= 0){
                    return false;
                }
                else{
                    if(value > find.intValue()){
                        return false;
                    }

                    treeMap.put(key2, find - value);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,1,2,1,1,1,2,2};

        CanReorderDoubled canReorderDoubled = new CanReorderDoubled();

        System.out.println(canReorderDoubled.canReorderDoubled(arr));
    }
}
