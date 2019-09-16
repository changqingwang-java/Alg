package com.test;

import java.util.Stack;

public class MaxDepth {
    private class HeightNode{
        int height;
        TreeNode treeNode;

        public HeightNode(int height, TreeNode treeNode){
            this.height = height;
            this.treeNode = treeNode;
        }
    }

    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;

        Stack<HeightNode> stack = new Stack<>();

        stack.push(new HeightNode(1,root));

        int maxLen = 1;

        while(!stack.empty()){
            HeightNode pop = stack.pop();
            maxLen = Math.max(maxLen,pop.height);

            if(pop.treeNode.right != null){
                stack.push(new HeightNode(pop.height+1,pop.treeNode.right));
            }

            if(pop.treeNode.left != null){
                stack.push(new HeightNode(pop.height+1,pop.treeNode.left));
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {

    }
}
