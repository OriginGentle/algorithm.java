package com.system.C_training.day04;

/**
 * @Author ycb
 * @Date 2021/8/1-17:29
 * @Description 生成长度为size的达标数组，什么叫达标？
 * 达标：对于任意的 i < k < j，满足 [i] + [j] != [k] * 2
 * 给定一个正数size，返回长度为size的达标数组
 */
public class Code06_MakeNo {

    /**
     * [...a,b,c...]
     * 如果 a + b != 2c
     * 则 2a + 2b != 2c 满足条件
     * 2a+1 2b+1 2c+1 也满足条件
     * [2a,2b,2b,2a+1,2b+1,2c+1] 一定达标
     * <p>
     * 分治理念：整理左右两侧的规模，想整合逻辑
     *
     * @param size
     * @return
     */
    public static int[] makeNo(int size) {
        if (size == 1) {
            return new int[]{1}; // 可以是任意值
        }
        // 向上取整
        int halfSize = (size + 1) / 2;
        // 求种子
        int[] base = makeNo(halfSize);
        int[] ans = new int[size];
        int index = 0;
        for (; index < halfSize; index++) {
            ans[index] = base[index] * 2 - 1;
        }
        for (int i = 0; index < size; index++, i++) {
            ans[index] = base[i] * 2;
        }
        return ans;
    }

    // 检验函数
    public static boolean isValid(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {
                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int N = 1; N < 1000; N++) {
            int[] arr = makeNo(N);
            if (!isValid(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
        System.out.println(isValid(makeNo(1042)));
        System.out.println(isValid(makeNo(2981)));
    }

}
