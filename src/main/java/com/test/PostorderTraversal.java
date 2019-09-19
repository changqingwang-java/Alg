package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PostorderTraversal {
    public static List<Integer> postorderTraversal(TreeNode root) {
        if(root == null){
            return Collections.emptyList();
        }

        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        TreeNode last = null;

        while(!stack.isEmpty()){
            TreeNode node = stack.peek();

            boolean pop = true;

            if(node.right != null && last != node.right){
                stack.push(node.right);
                pop = false;
            }

            if(node.left != null && last != node.left && ((node.right != null && last != node.right) || node.right == null)){
                stack.push(node.left);
                pop = false;
            }

            if(pop){
                node = stack.pop();
                list.add(node.val);
                last = node;
            }
        }

        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.right = right;

        right.left = new TreeNode(3);

        List<Integer> list = postorderTraversal(root);

        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + ",");
        }
    }
}
