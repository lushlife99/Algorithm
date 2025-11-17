import java.io.*;
import java.util.*;


/**
 * boj 2568 전깃줄 - 2
 * LIS, 역추적
 */


public class Main {

    static class Pair implements Comparable<Pair> {
        int a, b;
        Pair(int a, int b) { this.a = a; this.b = b; }

        @Override
        public int compareTo(Pair o) {
            return this.a - o.a;
        }
    }

    static int N;
    static List<Pair> pairs = new ArrayList<>();

    static int[] dp;
    static int[] pos;
    static int[] trace;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pairs.add(new Pair(a, b));
        }

        Collections.sort(pairs);

        dp = new int[N];
        pos = new int[N];
        trace = new int[N];

        int LIS = 0;

        for (int i = 0; i < N; i++) {
            int now = pairs.get(i).b;

            int idx = lowerBound(dp, LIS, now);

            dp[idx] = now;
            pos[i] = idx;
            trace[idx] = i;

            if (idx == LIS) LIS++;
        }

        boolean[] isLIS = new boolean[N];
        int target = LIS - 1;

        for (int i = N - 1; i >= 0; i--) {
            if (pos[i] == target) {
                isLIS[i] = true;
                target--;
            }
        }

        List<Integer> removed = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (!isLIS[i]) removed.add(pairs.get(i).a);
        }

        Collections.sort(removed);

        System.out.println(removed.size());
        for (int x : removed) System.out.println(x);
    }

    private static int lowerBound(int[] arr, int end, int target) {
        int s = 0, e = end;
        while (s < e) {
            int m = (s + e) / 2;
            if (arr[m] < target) s = m + 1;
            else e = m;
        }
        return s;
    }
}
