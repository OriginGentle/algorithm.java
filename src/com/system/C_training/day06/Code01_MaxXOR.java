package com.system.C_training.day06;

/**
 * @author ycb
 * @date 2021/8/13-8:16
 * @description 数组中所有数都异或起来的结果，叫做异或和
 * 给定一个数组arr，返回arr的最大子数组异或和
 */
public class Code01_MaxXOR {

    // 暴力解:O(N^2)
    public static int maxXorSubArray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        // 生成arr的前缀异或和数组
        // eor[i]代表arr[0..i]的异或和
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i <= j; i++) { // 依次尝试arr[0..j]、arr[1..j]..arr[i..j]..arr[j..j]
                max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }

    /*
    ====================================================================================================================
     */

    // 前缀树 + 贪心
    public static int maxXorSubArray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int eor = 0;
        NumTrie numTrie = new NumTrie();
        // 一个数都不选的时候，异或和就为0
        numTrie.add(0);
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            max = Math.max(max, numTrie.maxXor(eor));
            numTrie.add(eor);
        }
        return max;
    }

    // 前缀树的节点:来了一个数，使用其二进制进行表示
    // node[0] 代表 0 位置的路
    // node[1] 代表 1 位置的路
    // node[0] == null 代表0方向上没有路
    // node[0] != null 代表0方向上有路，可以跳下一个节点
    // node[1] == null 代表1方向上没有路
    // node[1] != null 代表1方向上有路，可以跳下一个节点
    public static class Node {
        public Node[] nexts = new Node[2];
    }

    // 前缀树的实现
    public static class NumTrie {
        public Node head = new Node();

        public void add(int newNum) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                // 提取newNum中第move位的状态:0 或 1
                int path = ((newNum >> move) & 1);
                // 无路就建路，有路就复用
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                // 跳到下一个节点
                cur = cur.nexts[path];
            }
        }

        // 之前收集了一票数字，建好了前缀树
        // num和谁 ^ 的结果最大，把结果返回
        public int maxXor(int num) {
            Node cur = head;
            int ans = 0;
            for (int move = 31; move >= 0; move--) {
                // 提取num中第move位的状态,不是0就是1
                int path = (num >> move) & 1;
                // 希望遇到的(贪心的点)
                // 注意:最高位是符号位,根据异或运算的特点,相同为0,不同为1
                // 因为正数结果比负数结果大,通过符号位的判断，可以辅助得到最后的答案
                int best = move == 31 ? path : path ^ 1;
                // 实际遇到的东西
                best = cur.nexts[best] != null ? best : best ^ 1;
                // (path ^ best) 当前位位异或完的结果
                ans |= (best ^ path) << move;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int comp = maxXorSubArray1(arr);
            int res = maxXorSubArray2(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
