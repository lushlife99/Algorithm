import java.io.*;
import java.util.*;


/**
 * boj 2618 경찰차
 * dp
 */


public class Main {

    static int N, W;
    static Point[] events;
    static int[][] dp;
    static StringBuilder answer = new StringBuilder();

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        events = new Point[W + 1];
        dp = new int[W + 1][W + 1];

        for (int i = 1; i <= W; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            events[i] = new Point(x, y);
        }

        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        System.out.println(solve(0,0));
        reconstruct(0,0);
        System.out.print(answer);

    }

    private static int solve(int car1, int car2) {
        int next = Math.max(car1, car2) + 1;
        if (next > W) return 0;

        if (dp[car1][car2] != -1) return dp[car1][car2];

        int d1 = getDistance(car1, next, 1) + solve(next, car2);
        int d2 = getDistance(car2, next, 2) + solve(car1, next);
        dp[car1][car2] = Math.min(d1, d2);
        return dp[car1][car2];
    }

    private static int getDistance(int from, int to, int carNum) {
        Point a, b;
        if (from == 0) {
            a = (carNum == 1) ? new Point(1,1) : new Point(N, N);
        } else {
            a = events[from];
        }
        b = events[to];

        return Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
    }

    private static void reconstruct(int car1, int car2) {
        int next = Math.max(car1, car2) + 1;
        if (next > W) return;

        int d1 = getDistance(car1, next, 1) + solve(next, car2);
        int d2 = getDistance(car2, next, 2) + solve(car1, next);
        if (d1 > d2) {
            answer.append("2\n");
            reconstruct(car1, next);
        } else {
            answer.append("1\n");
            reconstruct(next, car2);
        }
    }
}


