package com.test;

import java.util.Stack;

public class alg {
    public static void midVisitByRecursor(Node node){
        if(node == null){
            return;
        }

        midVisitByRecursor(node.left);
        System.out.print(node.value + ",");
        midVisitByRecursor(node.right);
    }

    public static void midVisit(Node root){
        if(root == null){
            return;
        }

        Node node = root;

        Stack<Node> stack = new Stack<Node>();

        while(true){
            while(node != null){
                stack.push(node);
                node = node.left;
            }

            if(stack.isEmpty())
                break;

            Node temp = stack.pop();
            System.out.print(temp.value + ",");

            if(temp.right != null){
                node = temp.right;
            }
            else{
                node = null;
            }
        }
    }

    public static void preVisit(Node root){
        if(root == null)
            return;

        System.out.print(root.value+",");
        preVisit(root.left);
        preVisit(root.right);
    }

    public static void preVisitByStack(Node root){
        if(root == null)
            return;

        Node node = root;

        Stack<Node> stack = new Stack<Node>();
        stack.push(node);

        while(!stack.isEmpty()){
            node = stack.pop();

            System.out.print(node.value + ",");

            if(node.right != null){
                stack.push(node.right);
            }

            if(node.left != null){
                stack.push(node.left);
            }
        }
    }

    public static void postVisit(Node root){
        if(root == null)
            return;

        postVisit(root.left);
        postVisit(root.right);
        System.out.print(root.value+",");
    }

    public static void postVisitByStack(Node root){
        if(root == null)
            return;

        Node node = root;
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);
        Node temp = null;

        while(true){
            if(stack.isEmpty())
                break;

            node = stack.peek();

            while (node.left != null && ((node.right != null && node.right != temp) || node.right == null) && ((node.left != null && node.left != temp) || node.left == null)){
                stack.push(node.left);
                node = node.left;
            }

            if(node.right != null && node.right != temp){
                stack.push(node.right);
            }
            else{
                node = stack.pop();
                temp = node;
                System.out.print(node.value+",");
            }
        }
    }

    public static void main(String[] args) {
        Node root = ToolUtils.produceTree();

        System.out.println("mid visit begin----------------------------------");
        midVisitByRecursor(root);
        System.out.println("");
        midVisit(root);

        System.out.println("mid visit endd---------------------------------------");

        System.out.println("pre visit begin----------------------------");
        preVisit(root);
        System.out.println("");
        preVisitByStack(root);
        System.out.println("");
        System.out.println("pre visit end---------------------------");

        System.out.println("post visit begin----------------------------");
        postVisit(root);
        System.out.println("");
        postVisitByStack(root);
        System.out.println("");
        System.out.println("post visit end---------------------------");
    }
}
