package com.system.C_training.day36;

import java.util.HashMap;

/**
 * @author ycb
 * @date 2021/10/24-17:24
 * @description 来自美团
 * 有一棵树，给定头节点h，和结构数组m，下标0弃而不用
 * 比如h = 1, m = [ [] , [2,3], [4], [5,6], [], [], []]
 * 表示1的孩子是2、3; 2的孩子是4; 3的孩子是5、6; 4、5和6是叶节点，都不再有孩子
 * 每一个节点都有颜色，记录在c数组里，比如c[i] = 4, 表示节点i的颜色为4
 * 一开始只有叶节点是有权值的，记录在w数组里，
 * 比如，如果一开始就有w[i] = 3, 表示节点i是叶节点、且权值是3
 * 现在规定非叶节点i的权值计算方式：
 * 根据i的所有直接孩子来计算，假设i的所有直接孩子，颜色只有a,b,k
 * w[i] = Max {
 * (颜色为a的所有孩子个数 + 颜色为a的孩子权值之和),
 * (颜色为b的所有孩子个数 + 颜色为b的孩子权值之和),
 * (颜色为k的所有孩子个数 + 颜色k的孩子权值之和)
 * }
 * 请计算所有孩子的权值并返回
 */
public class Code06_NodeWeight {

    // 当前来到h节点，
    // h的直接孩子，在哪呢？m[h] = {a,b,c,d,e}
    // 每个节点的颜色在哪？比如i号节点，c[i]就是i号节点的颜色
    // 每个节点的权值在哪？比如i号节点，w[i]就是i号节点的权值
    // void : 把w数组填满就是这个函数的目标
    public static void w(int h, int[][] m, int[] w, int[] c) {
        if (m[h].length == 0) { // 当前节点是叶节点
            return;
        }
        HashMap<Integer, Integer> colors = new HashMap<>();
        HashMap<Integer, Integer> weights = new HashMap<>();
        for (int child : m[h]) {
            w(child, m, w, c);
            colors.put(c[child], colors.getOrDefault(c[child], 0) + 1);
            weights.put(c[child], weights.getOrDefault(c[child], 0) + w[c[child]]);
        }
        for (int color : colors.keySet()) {
            w[h] = Math.max(w[h], colors.get(color) + weights.get(color));
        }
    }
}
