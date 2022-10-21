package binaryTree;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * <p>
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 */
public class zigzagTree103 {
    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20, node3, node4);
        TreeNode root = new TreeNode(3, node1, node2);
        List<List<Integer>> list = zigzagTree103.zigzagLevelOrder(root);
        list.forEach(System.out::print);
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> top = new ArrayList<>(1);
        top.add(root.val);
        list.add(top);
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            while (!stack1.isEmpty()) {
                // pop
                // add item
                TreeNode node = stack1.pop();
                if (node.right != null) {
                    tmp.add(node.right.val);
                    stack2.push(node.right);
                }
                if (node.left != null) {
                    tmp.add(node.left.val);
                    stack2.push(node.left);
                }
            }
            if (tmp.size()>0) {
                list.add(tmp);
                tmp = new ArrayList<>();
            }
            while (!stack2.isEmpty()) {
                TreeNode node = stack2.pop();
                if (node.left !=null) {
                    tmp.add(node.left.val);
                    stack1.add(node.left);
                }
                if (node.right != null) {
                    tmp.add(node.right.val);
                    stack1.add(node.right);
                }
            }
            if (tmp.size()>0) {
                list.add(tmp);
                tmp = null;
            }
        }
        return list;
    }

    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        if (root == null) {
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>> list = bfs(root, new ArrayList<>(), 0);
        if (list == null || list.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 1) {
                Collections.reverse(list.get(i));
            }
        }
        return list;
    }

    public static List<List<Integer>> bfs(TreeNode node, List<List<Integer>> list, int height) {
        if (node == null) {
            return list;
        }
        if (height >= list.size() || list.get(height) == null) {
            LinkedList<Integer> subList = new LinkedList<>();
            subList.add(node.val);
            list.add(subList);
        } else {
            list.get(height).add(node.val);
        }
        bfs(node.left, list, height + 1);
        bfs(node.right, list, height + 1);
        return list;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
