package com.test;

public class ToolUtils {
    public static Node produceTree(){
        Node root = new Node(1);
        Node l1 = new Node(2);
        Node r1 = new Node(3);
        root.left = l1;
        root.right = r1;

        Node l1_1 = new Node(4);
        Node l1_2 = new Node(5);
        l1.left = l1_1;
        l1.right = l1_2;

        Node r1_1 = new Node(6);
        Node r1_2 = new Node(7);
        r1.left = r1_1;
        r1.right = r1_2;

        Node l1_1_1 = new Node(8);
        l1_1.left = l1_1_1;

        Node l1_1_1_2 = new Node(9);
        l1_1_1.right = l1_1_1_2;

        Node l1_2_2 = new Node(10);
        l1_2.right = l1_2_2;

        return root;
    }

    public static void quickSort(int[] A, int left, int right){
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
}
