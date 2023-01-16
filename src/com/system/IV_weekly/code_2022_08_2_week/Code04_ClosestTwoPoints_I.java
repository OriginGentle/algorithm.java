package com.system.IV_weekly.code_2022_08_2_week;

import java.io.*;
import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/8/12-09:12
 * @desc 给定平面上n个点，x和y坐标都是整数
 * 找出其中的一对点的距离，使得在这n个点的所有点对中，该距离为所有点对中最小的
 * 返回最短距离，精确到小数点后面4位
 * 测试链接 : https://www.luogu.com.cn/problem/P1429
 * T(N) = 2*T(N/2) + O(N*logN)
 * 表达式的时间复杂度是O(N*(logN的平方))
 * 复杂度证明 : https://math.stackexchange.com/questions/159720/
 */
public class Code04_ClosestTwoPoints_I {

    public static int N = 200001;

    public static Point[] points = new Point[N];

    public static Point[] deals = new Point[N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                double x = in.nval;
                in.nextToken();
                double y = in.nval;
                points[i] = new Point(x, y);
            }
            Arrays.sort(points, 0, n, (a, b) -> a.x <= b.x ? -1 : 1);
            double ans = nearest(0, n - 1);
            out.println(String.format("%.4f", ans));
            out.flush();
        }
    }

    public static double nearest(int left, int right) {
        double ans = Double.MIN_VALUE;
        if (left == right) {
            return ans;
        }
        int mid = (left + right) / 2;
        ans = Math.min(nearest(left, mid), nearest(mid + 1, right));
        int l = mid;
        int r = mid + 1;
        int size = 0;
        while (l >= left && points[mid].x - points[l].x <= ans) {
            deals[size++] = points[l--];
        }
        while (r <= right && points[r].x - points[mid].x <= ans) {
            deals[size++] = points[r++];
        }
        Arrays.sort(deals, 0, size, (a, b) -> a.y <= b.y ? -1 : 1);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (deals[j].y - deals[i].y >= ans) {
                    break;
                }
                ans = Math.min(ans, distance(deals[i], deals[j]));
            }
        }
        return ans;
    }

    public static class Point {
        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double distance(Point a, Point b) {
        double x = a.x - b.x;
        double y = a.y - b.y;
        return Math.sqrt(x * x + y * y);
    }
}
