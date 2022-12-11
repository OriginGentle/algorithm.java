package com.leetcode.problem_0201_0400;

import java.util.Iterator;

/**
 * @author ycb
 * @date 2022/12/11-20:51
 */
public class Problem_0284_PeekingIterator {

    class PeekingIterator implements Iterator<Integer> {

        Iterator<Integer> iter;
        Integer next;

        public PeekingIterator(Iterator<Integer> iterator) {
            iter = iterator;
            if (iterator.hasNext())
                next = iterator.next();
        }

        public Integer peek() {
            return next;
        }

        @Override
        public Integer next() {
            Integer ans = next;
            next = iter.hasNext() ? iter.next() : null;
            return ans;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }
    }
}
