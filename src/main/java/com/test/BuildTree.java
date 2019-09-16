package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BuildTree {
    private static TreeNode buildTree(int[] preorder, int[] inorder, int l1, int r1, int l2, int r2){
        if(l1 > r1 || l2 > r2 || l1 < 0 || r1 >= preorder.length || l2 < 0 || r2 >= inorder.length){
            return null;
        }

        TreeNode root = new TreeNode(preorder[l1]);

        int j = l2;
        for(; j <= r2; j++){
            if(inorder[j] == preorder[l1]){
                break;
            }
        }

        int len = j - l2;

        root.left = buildTree(preorder,inorder,l1 + 1, l1 + len, l2, j - 1);
        root.right = buildTree(preorder,inorder,l1 + len + 1, r1, j + 1, r2);

        return root;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null)
            return null;

        return buildTree(preorder,inorder,0,preorder.length - 1,0, inorder.length - 1);
    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        TreeNode root = buildTree(preorder,inorder);

        List<TreeNode> list = new ArrayList<>();

        list.add(root);

        while(!list.isEmpty()){
            TreeNode first = list.remove(0);

            System.out.println(first.val);

            if(first.left != null){
                list.add(first.left);
            }

            if(first.right != null){
                list.add(first.right);
            }
        }
    }
}
