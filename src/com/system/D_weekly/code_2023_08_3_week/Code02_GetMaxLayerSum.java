package com.system.D_weekly.code_2023_08_3_week;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2023/8/6-14:07
 * @desc 测试链接 : https://leetcode.cn/problems/WInSav/
 */
public class Code02_GetMaxLayerSum {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static class Info {
        public int preSum;
        public int left;
        public int right;
        public int finishId;

        public Info(int a, int b, int c, int d) {
            preSum = a;
            left = b;
            right = c;
            finishId = d;
        }
    }

    public static ArrayList<ArrayList<Info>> levelInfos = new ArrayList<>();

    public static class Job {
        public int nodeId;
        public int level;

        public Job(int s, int e) {
            nodeId = s;
            level = e;
        }
    }

    public static ArrayList<Job> jobs = new ArrayList<>();

    public static int getMaxLayerSum(TreeNode root) {
        levelInfos.clear();
        jobs.clear();
        process(root, 0);
        int height = levelInfos.size() - 1;
        int ans = Integer.MIN_VALUE;
        for (int level = 0; level < height; level++) {
            List<Info> levelList = levelInfos.get(level);
            ans = Math.max(ans, levelList.get(levelList.size() - 1).preSum);
        }
        for (int id = 0; id < jobs.size(); id++) {
            Job job = jobs.get(id);
            int nodeId = job.nodeId;
            int level = job.level;
            int left = nodeId;
            int right = nodeId;
            int curLevelSum = levelInfos.get(level).get(left).preSum - levelInfos.get(level).get(left - 1).preSum;
            for (; level < height; level++) {
                if (left > right) {
                    break;
                }
                List<Info> levelList = levelInfos.get(level);
                if (right - left + 1 == levelList.size() - 1) {
                    break;
                }
                Info leftInfo = levelList.get(left);
                Info rightInfo = levelList.get(right);
                int nextLeft = leftInfo.left;
                int nextRight = rightInfo.right;
                int curLevelAll = levelList.get(levelList.size() - 1).preSum;
                if (leftInfo.finishId != -1 && leftInfo.finishId == rightInfo.finishId) {
                    break;
                }
                leftInfo.finishId = rightInfo.finishId = id;
                int nextLevelSum = nextLeft <= nextRight
                        ? (levelInfos.get(level + 1).get(nextRight).preSum
                        - levelInfos.get(level + 1).get(nextLeft - 1).preSum)
                        : 0;
                ans = Math.max(ans, curLevelAll - curLevelSum + nextLevelSum);
                left = nextLeft;
                right = nextRight;
                curLevelSum = nextLevelSum;
            }
        }
        return ans;
    }

    public static boolean process(TreeNode cur, int level) {
        if (cur == null) {
            return false;
        }
        while (level + 1 >= levelInfos.size()) {
            levelInfos.add(new ArrayList<>(List.of(new Info(0, -1, -1, -1))));
        }
        List<Info> levelList = levelInfos.get(level);
        int preId = levelList.size() - 1;

        levelList.add(new Info(levelList.get(preId).preSum + cur.val,
                levelInfos.get(level + 1).size(), -1, -1));

        boolean hasLeft = process(cur.left, level + 1);
        boolean hasRight = process(cur.right, level + 1);

        int nodeId = levelList.size() - 1;
        if (!hasLeft || !hasRight) {
            jobs.add(new Job(nodeId, level));
        }
        levelList.get(nodeId).right = levelInfos.get(level + 1).size() - 1;
        return true;
    }
}
