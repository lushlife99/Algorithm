import java.io.*;
import java.util.*;


/**
 * boj 2568 전깃줄 - 2
 * LIS, 역추적
 */


public class Main {

    static class Pair implements Comparable<Pair> {
        int n1, n2;

        public Pair(int n1, int n2) {
            this.n1 = n1; this.n2 = n2;
        }

        @Override
        public int compareTo(Pair o) {
            return this.n1 - o.n1;
        }
    }

    private static int N;
    private static List<Pair> pairs = new ArrayList<>();

    private static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            pairs.add(new Pair(n1, n2));
        }

        Collections.sort(pairs);
        dp = new int[N];
        int[] pos = new int[N];
        int LIS = 0;

        for (int i = 0; i < N; i++) {
            int current = pairs.get(i).n2;
            int idx = binarySearch(LIS, current);
            dp[idx] = current;
            pos[i] = idx;
            if (idx == LIS) LIS++;
        }

        System.out.println(N-LIS);
        boolean[] used = new boolean[N];
        int target = LIS-1;
        for (int i = N - 1; i >= 0; i--) {
            if (pos[i] == target) {
                used[i] = true;
                target--;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            if (!used[i]) {
                sb.append(pairs.get(i).n1 + "\n");
            }
        }

        System.out.print(sb);
    }

    private static int binarySearch(int end, int value) {
        int start = 0;
        while (start < end) {
            int mid = (start + end) / 2;
            if (dp[mid] >= value) {
                end = mid;
            } else {
                start = mid+1;
            }
        }
        return start;
    }
}
