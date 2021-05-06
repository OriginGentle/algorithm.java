package com.algorithm.day11;

/**
 * @Author ycb
 * @Date 2021/5/5-23:39
 * @Description 微软面试题
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的
 * 背面。 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 * 例如:N=1时，打印: down N=2时，打印: down down up
 * <p>
 * 对折发现：二叉树的中序遍历
 *                         1凹
 *                 2凹            2凸
 *              3凹   3凸      3凹   3凸
 *
 */
public class PaperFolding {
    public static void printAllFolds(int N) {
        process(1, N, true);
    }

    // 当前来了一个节点，脑海中想象的！
    // 这个节点在第一层，一共有N层，N是固定不变的
    // 这个节点如果是凹的话，down = true
    // 这个节点如果是凸的话，down = false
    // 函数的功能：中序打印以你想象的节点为头的整棵树！
    public static void process(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        process(i + 1, N, true);
        System.out.print(down ? "凹 " : "凸 ");
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 4;
        printAllFolds(N);
    }
}
