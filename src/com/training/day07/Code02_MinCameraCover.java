package com.training.day07;

/**
 * @author ycb
 * @date 2021/8/15-12:48
 * @description https://leetcode.com/problems/binary-tree-cameras/
 */
public class Code02_MinCameraCover {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static int minCameraCover1(TreeNode root) {
        Info data = process1(root);
        return (int) Math.min(data.unCovered + 1, Math.min(data.coveredNoCamera, data.coveredHasCamera));
    }

    // X是头结点,X下方的点都被覆盖的情况下
    public static class Info {
        public long unCovered; // x没有被覆盖，x为头的树至少需要几个相机
        public long coveredNoCamera; // x被相机覆盖，但是x没相机，x为头的树至少需要几个相机
        public long coveredHasCamera; // x被相机覆盖了，并且x上放了相机，x为头的树至少需要几个相机

        public Info(long un, long no, long has) {
            this.unCovered = un;
            this.coveredNoCamera = no;
            this.coveredHasCamera = has;
        }
    }

    public static Info process1(TreeNode root) {
        // 空节点认为被覆盖,但是没有相机
        if (root == null) {
            return new Info(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        }
        Info left = process1(root.left);
        Info right = process1(root.right);
        // root unCovered  root自己不被覆盖，root下方所有节点，都被覆盖
        // 左孩子： 左孩子没被覆盖，左孩子以下的点都被覆盖
        // 左孩子被覆盖但没相机，左孩子以下的点都被覆盖
        // 左孩子被覆盖也有相机，左孩子以下的点都被覆盖
        long unCovered = left.coveredNoCamera + right.coveredNoCamera;
        // x下方的点都被covered，x也被cover，但x上没相机
        long coveredNoCamera = Math.min(left.coveredHasCamera + right.coveredHasCamera,
                Math.min(left.coveredHasCamera + right.coveredNoCamera, left.coveredNoCamera + right.coveredHasCamera));
        // x下方的点都被covered，x也被cover，且x上有相机
        long coveredHasCamera = Math.min(left.unCovered, Math.min(left.coveredNoCamera, left.coveredHasCamera)) +
                Math.min(right.unCovered, Math.min(right.coveredNoCamera, right.coveredHasCamera)) + 1;
        return new Info(unCovered, coveredNoCamera, coveredHasCamera);
    }

    /*
    ====================================================================================================================
     */

    // 贪心
    public static int minCameraCover2(TreeNode root) {
        Data data = process(root);
        return data.cameras + (data.status == Status.UNCOVERED ? 1 : 0);
    }

    // 以x为头，x下方的节点都是被covered，x自己的状况，分三种
    public static enum Status {
        UNCOVERED, COVERED_NO_CAMERA, COVERED_HAS_CAMERA
    }

    public static class Data {
        public Status status;
        public int cameras;

        public Data(Status status, int cameras) {
            this.cameras = cameras;
            this.status = status;
        }
    }

    public static Data process(TreeNode X) {
        if (X == null) {
            return new Data(Status.COVERED_NO_CAMERA, 0);
        }
        Data left = process(X.left);
        Data right = process(X.right);
        int cameras = left.cameras + right.cameras;
        // 左、或右，哪怕有一个没覆盖
        if (left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
            return new Data(Status.COVERED_HAS_CAMERA, cameras + 1);
        }
        // 左右孩子，不存在没被覆盖的情况
        if (left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA) {
            return new Data(Status.COVERED_NO_CAMERA, cameras);
        }
        // 左右孩子，不存在没被覆盖的情况，也都没有相机
        return new Data(Status.UNCOVERED, cameras);
    }

}
