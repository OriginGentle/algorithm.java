package com.weekly.code_2022_11_3_week;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author ycb
 * @date 2022/11/19-13:58
 * @desc https://leetcode.cn/problems/exam-room/
 * 设计一个叫Bank的类，并提供如下方法
 * Bank(int n) 初始化的时候，准备好0、1、2 ... n-1个座位
 * int hello() :
 * 如果此时所有座位都无人，那么分配0号座位给当前用户
 * 如果此时座位上有人，那么分配一个座位，这个座位保证是所有座位中离最近的人距离最远的座位
 * 如果有多个座位都满足，分配座位编号最小的座位
 * 返回座位编号
 * 如果已经没有座位，返回-1表示无法分配
 * void goodbye(int x) :
 * 如果x号座位上无人，什么也不用做
 * 如果x号座位上有人，现在这个人离开了，该座位又能重新考虑分配
 * 举例 :
 * Bank b = new Bank(10)  0~9号座位被初始化出来
 * b.hello()，返回0，表示给当前用户分配了0座位
 * b.hello()，返回9，因为此时9座位离0座位的人最远，此时
 * 0 1 2 3 4 5 6 7 8 9
 * X                 X
 * b.hello()，虽然座位4和座位5，离最近人的距离都是4(最远)
 * 这种情况，根据描述，分配座位编号最小的座位，返回4，此时
 * 0 1 2 3 4 5 6 7 8 9
 * X       X         X
 * b.hello()，座位2、座位6、座位7，都是离最近人的距离最远的(2)
 * 这种情况，根据描述，分配座位编号最小的座位，返回2，此时
 * 0 1 2 3 4 5 6 7 8 9
 * X   X   X         X
 * b.goodbye(4)，4座位的人离开了，此时
 * 0 1 2 3 4 5 6 7 8 9
 * X   X             X
 * b.hello()，座位5、座位6，都是离最近人的距离最远的(3)
 * 这种情况，根据描述，分配座位编号最小的座位，返回5
 */
public class Code05_FarAwaySuggestion {

    class ExamRoom {

        class FreeSpace {
            public int start;
            public int end;
            public int far;

            public FreeSpace(int s, int e, int f) {
                start = s;
                end = e;
                far = f;
            }
        }

        public int right;
        public TreeSet<FreeSpace> seats;
        public TreeSet<FreeSpace> heads;
        public HashSet<Integer> used;

        public ExamRoom(int n) {
            right = n - 1;
            seats = new TreeSet<>((a, b) -> a.far != b.far ? b.far - a.far : a.start - b.start);
            heads = new TreeSet<>((a, b) -> a.start - b.start);
            used = new HashSet<>();
            add(0, right, Integer.MAX_VALUE);
        }

        private void add(int start, int end, int far) {
            FreeSpace freeSpace = new FreeSpace(start, end, far);
            seats.add(freeSpace);
            heads.add(freeSpace);
        }

        public int seat() {
            if (used.size() == right + 1)
                return -1;

            FreeSpace cur = poll();
            int ans;
            if (cur.start == 0 && cur.end == right) {
                ans = 0;
                add(1, right, right);

            } else if (cur.start == 0) {
                ans = 0;
                int start = 1;
                int end = cur.end;
                if (start <= end)
                    add(start, end, (end - start) / 2 + 1);

            } else if (cur.end == right) {
                ans = right;
                int start = cur.start;
                int end = cur.end - 1;
                if (start <= end)
                    add(start, end, (end - start) / 2 + 1);

            } else {
                ans = (cur.start + cur.end) / 2;
                int start1 = cur.start;
                int end1 = ans - 1;
                if (start1 <= end1) {
                    add(start1, end1, (end1 - start1) / 2 + 1);
                }

                int start2 = ans + 1;
                int end2 = cur.end;
                if (start2 <= end2) {
                    add(start2, end2, (end2 - start2) / 2 + 1);
                }
            }
            used.add(ans);
            return ans;
        }

        private FreeSpace poll() {
            FreeSpace space = seats.pollFirst();
            heads.remove(space);
            return space;
        }

        public void leave(int x) {
            if (used.contains(x)) {
                used.remove(x);
                FreeSpace m = new FreeSpace(x, x, 1);
                FreeSpace l = heads.floor(m);
                FreeSpace r = heads.ceiling(m);
                merge(l, m, r);
            }
        }

        // 左区间、中区间、右区间
        // 能合并就合在一起
        private void merge(FreeSpace l, FreeSpace m, FreeSpace r) {
            int start = m.start;
            int end = m.end;
            if (l != null && l.end == m.start - 1) {
                remove(l);
                start = l.start;
            }
            if (r != null && m.end + 1 == r.start) {
                remove(r);
                end = r.end;
            }
            int far = 0;
            if (start == 0 && end == right) {
                far = Integer.MAX_VALUE;
            } else if (start == 0) {
                far = end + 1;
            } else if (end == right) {
                far = end - start + 1;
            } else {
                far = (end - start) / 2 + 1;
            }
            add(start, end, far);
        }

        private void remove(FreeSpace space) {
            seats.remove(space);
            heads.remove(space);
        }
    }
}
