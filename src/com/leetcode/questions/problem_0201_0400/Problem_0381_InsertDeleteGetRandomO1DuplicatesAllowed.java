package com.leetcode.questions.problem_0201_0400;

import java.util.*;

/**
 * @author ycb
 * @date 2022/11/10-10:50
 */
public class Problem_0381_InsertDeleteGetRandomO1DuplicatesAllowed {

    class RandomizedCollection {

        private final Map<Integer, Set<Integer>> indexMap;
        private final List<Integer> numsCmt;

        public RandomizedCollection() {
            indexMap = new HashMap<>();
            numsCmt = new ArrayList<>();
        }

        public boolean insert(int val) {
            numsCmt.add(val);
            Set<Integer> set = indexMap.getOrDefault(val, new HashSet<>());
            set.add(numsCmt.size() - 1);
            indexMap.put(val, set);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            if (!indexMap.containsKey(val))
                return false;

            Iterator<Integer> it = indexMap.get(val).iterator();
            int next = it.next();
            int lastNum = numsCmt.get(numsCmt.size() - 1);
            numsCmt.set(next, lastNum);

            indexMap.get(val).remove(next);
            indexMap.get(lastNum).remove(numsCmt.size() - 1);

            if (next < numsCmt.size() - 1)
                indexMap.get(lastNum).add(next);

            if (indexMap.get(val).size() == 0)
                indexMap.remove(val);
            numsCmt.remove(numsCmt.size() - 1);
            return true;
        }

        public int getRandom() {
            return numsCmt.get((int) (Math.random() * numsCmt.size()));
        }
    }
}
