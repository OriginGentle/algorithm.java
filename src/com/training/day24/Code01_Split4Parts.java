package com.training.day24;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ycb
 * @date 2021/10/9-8:23
 * @description 给定一个正数数组arr，长度一定大于6（>=7）
 * 一定要选3个数字做分割点，从而分出4个部分，并且每部分都有数
 * 分割点的数字直接删除，不属于任何4个部分中的任何一个。
 * 返回有没有可能分出的4个部分累加和一样大
 * 如：{3,2,3,7,4,4,3,1,1,6,7,1,5,2}
 * 可以分成{3,2,3}、{4,4}、{1,1,6}、{1,5,2}。分割点是不算的！
 */
public class Code01_Split4Parts {

    public static boolean canSplits1(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        HashSet<String> set = new HashSet<String>();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int leftSum = arr[0];
        for (int i = 1; i < arr.length - 1; i++) {
            set.add(String.valueOf(leftSum) + "_" + String.valueOf(sum - leftSum - arr[i]));
            leftSum += arr[i];
        }
        int l = 1;
        int lSum = arr[0];
        int r = arr.length - 2;
        int rSum = arr[arr.length - 1];
        while (l < r - 3) {
            if (lSum == rSum) {
                String lKey = String.valueOf(lSum * 2 + arr[l]);
                String rKey = String.valueOf(rSum * 2 + arr[r]);
                if (set.contains(lKey + "_" + rKey)) {
                    return true;
                }
                lSum += arr[l++];
            } else if (lSum < rSum) {
                lSum += arr[l++];
            } else {
                rSum += arr[r--];
            }
        }
        return false;
    }

    /*
    ====================================================================================================================
     */

    public static boolean canSplits2(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        // key : 某一个累加和   value : 出现的位置
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            map.put(sum, i);
            sum += arr[i];
        }
        int lSum = arr[0]; // 第一刀左侧的累加和
        for (int s1 = 1; s1 < arr.length - 5; s1++) {
            int checkSum = lSum * 2 + arr[s1];
            if (map.containsKey(checkSum)) {
                int s2 = map.get(checkSum);
                checkSum += lSum + arr[s2];
                if (map.containsKey(checkSum)) { // 100 * 3 + x + y
                    int s3 = map.get(checkSum); // k -> z
                    if (checkSum + arr[s3] + lSum == sum) {
                        return true;
                    }
                }
            }
            lSum += arr[s1];
        }
        return false;
    }

    // for test
    public static int[] generateRandomArray() {
        int[] res = new int[(int) (Math.random() * 10) + 7];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 10) + 1;
        }
        return res;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 3000000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray();
            if (canSplits1(arr) ^ canSplits2(arr)) {
                printArr(arr);
                System.out.println(canSplits1(arr));
                System.out.println(canSplits2(arr));
                System.out.println("Error");
                break;
            }
        }
        System.out.println("测试通过");
    }
}
