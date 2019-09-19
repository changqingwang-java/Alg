package com.test;

import java.util.List;

public class SortedListToBST {
    private TreeNode sortedListToBST(ListNode head, ListNode tail){
        TreeNode root = null;
        ListNode headNode = head;

        int len = 0;

        while(headNode != tail){
            len++;
            headNode = headNode.next;
        }

        if(len == 1){
            root = new TreeNode(head.val);
        }
        else if(len == 2){
            root = new TreeNode(head.next.val);
            TreeNode node = new TreeNode(head.val);
            root.left = node;
        }
        else if(len == 3){
            root = new TreeNode(head.next.val);
            root.left = new TreeNode(head.val);
            root.right = new TreeNode(head.next.next.val);
        }
        else{
            int mid = len / 2;

            ListNode node = head;
            int i = 0;
            while(i < mid){
                node = node.next;
                i++;
            }

            root = new TreeNode(node.val);
            root.left = sortedListToBST(head,node);
            root.right = sortedListToBST(node.next,tail);
        }

        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)
            return null;

        return sortedListToBST(head,null);
    }

    public static void main(String[] args) {

    }
}
