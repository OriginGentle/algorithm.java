package com.system.III_training.day37;

import java.util.HashMap;

/**
 * @author ycb
 * @since 2021/10/25-8:53
 */
public class Problem_0437_PathSumIII {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static int pathSum(TreeNode root, int targetSum) {
        // key:某一路径的前缀和
        // value:出现的次数
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        return process(root, targetSum, 0, preSum);
    }

    public static int process(TreeNode x, int sum, int preAll, HashMap<Integer, Integer> preSum) {
        if (x == null) {
            return 0;
        }
        int all = preAll + x.val;
        int ans = 0;
        if (preSum.containsKey(all - sum)) {
            ans = preSum.get(all - sum);
        }
        if (!preSum.containsKey(all)) {
            preSum.put(all, 1);
        } else {
            preSum.put(all, preSum.get(all) + 1);
        }
        ans += process(x.left, sum, all, preSum);
        ans += process(x.right, sum, all, preSum);
        if (preSum.get(all) == 1) {
            preSum.remove(all);
        } else {
            preSum.put(all, preSum.get(all) - 1);
        }
        return ans;
    }
}
