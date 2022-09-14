package com.leetcode.problem_0201_0400;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/9/14-10:40
 */
public class Problem_0297_SerializeAndDeserializeBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Codec {

        public String serialize(TreeNode root) {

            return ser(root, "");
        }

        public String ser(TreeNode node, String s) {
            if (node == null)
                s += "None,";
            else {
                s += node.val + ",";
                s = ser(node.left, s);
                s = ser(node.right, s);
            }
            return s;
        }

        public TreeNode deserialize(String data) {
            String[] str = data.split(",");
            List<String> dataArr = new LinkedList<>(Arrays.asList(str));
            return des(dataArr);
        }

        private TreeNode des(List<String> dataArr) {
            if (dataArr.get(0).equals("None")) {
                dataArr.remove(0);
                return null;
            }

            TreeNode node = new TreeNode(Integer.parseInt(dataArr.get(0)));
            dataArr.remove(0);

            node.left = des(dataArr);
            node.right = des(dataArr);
            return node;
        }
    }
}
