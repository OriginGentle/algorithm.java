package com.system.III_training.day38;

/**
 * @author ycb
 * @since 2021/10/25-10:59
 */
public class Problem_0621_TaskScheduler {

    public static int leastInterval(char[] tasks, int free) {
        int[] count = new int[256];
        // 出现最多次的任务，出现多少次
        int maxCount = 0;
        for (char task : tasks) {
            count[task]++;
            maxCount = Math.max(maxCount, count[task]);
        }
        // 有多少种任务，都出现了最多次
        int maxKind = 0;
        for (int i = 0; i < 256; i++) {
            if (count[i] == maxCount) {
                maxKind++;
            }
        }
        // 砍掉最后一组任务数
//        int tasksExceptFinalTeam = tasks.length - maxKind;
        // 所有的坑位
//        int spaces = (free + 1) * (maxCount - 1);
        // 还剩余的坑位
//        int restSpace = Math.max(0, spaces - tasksExceptFinalTeam);
        return Math.max(tasks.length, (free + 1) * (maxCount - 1) + maxKind);
    }
}
