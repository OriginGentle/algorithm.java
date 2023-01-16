package com.system.III_training.day34;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author ycb
 * @since 2021/10/22-9:24
 */
public class Problem_0341_FlattenNestedListIterator {

    public interface NestedInteger {

        public boolean isInteger();

        public Integer getInteger();

        public List<NestedInteger> getList();
    }

    /*
    ====================================================================================================================
     */

    public class NestedIterator implements Iterator<Integer> {

        private List<NestedInteger> list;
        private Stack<Integer> stack;
        private boolean used;

        public NestedIterator(List<NestedInteger> nestedList) {
            list = nestedList;
            stack = new Stack<>();
            stack.push(-1);
            used = true;
            hasNext();
        }

        @Override
        public Integer next() {
            Integer ans = null;
            if (!used) {
                ans = get(list, stack);
                used = true;
                hasNext();
            }
            return ans;
        }

        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }
            if (!used) {
                return true;
            }
            if (findNext(list, stack)) {
                used = false;
            }
            return !used;
        }

        private Integer get(List<NestedInteger> nestedList, Stack<Integer> stack) {
            int index = stack.pop();
            Integer ans = null;
            if (!stack.isEmpty()) {
                ans = get(nestedList.get(index).getList(), stack);
            } else {
                ans = nestedList.get(index).getInteger();
            }
            stack.push(index);
            return ans;
        }

        private boolean findNext(List<NestedInteger> nestedList, Stack<Integer> stack) {
            int index = stack.pop();
            if (!stack.isEmpty() && findNext(nestedList.get(index).getList(), stack)) {
                stack.push(index);
                return true;
            }
            for (int i = index + 1; i < nestedList.size(); i++) {
                if (pickFirst(nestedList.get(i), i, stack)) {
                    return true;
                }
            }
            return false;
        }

        private boolean pickFirst(NestedInteger nested, int position, Stack<Integer> stack) {
            if (nested.isInteger()) {
                stack.add(position);
                return true;
            } else {
                List<NestedInteger> actualList = nested.getList();
                for (int i = 0; i < actualList.size(); i++) {
                    if (pickFirst(actualList.get(i), i, stack)) {
                        stack.add(position);
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
