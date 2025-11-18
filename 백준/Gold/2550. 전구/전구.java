import java.io.*;
import java.util.*;


/**
 * boj 2550 전구
 * LIS
 */


public class Main {

    static class Pair implements Comparable<Pair> {
        int id, n1, n2;

        public Pair(int id, int n1, int n2) {
            this.id = id; this.n1 = n1; this.n2 = n2;
        }

        @Override
        public int compareTo(Pair o) {
            return this.n1 - o.n1;
        }

        @Override
        public String toString() {
            return this.id + " " + n1 + " " + n2;
        }
    }

    private static int N;
    private static List<Pair> pairs = new ArrayList<>();
    private static int[] dp;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[Integer.parseInt(st2.nextToken())] = i;
        }

        for (int n1 = 1; n1 <= N; n1++) {
            int id = Integer.parseInt(st1.nextToken());
            int n2 = arr[id];
            pairs.add(new Pair(id, n1, n2));
        }

        int LIS = 0;
        int[] pos = new int[N];
        for (int i = 0; i < N; i++) {
            Pair current = pairs.get(i);
            int idx = binarySearch(LIS, current.n2);
            dp[idx] = current.n2;
            pos[i] = idx;

            if (LIS == idx) LIS++;
        }

        System.out.println(LIS);
        int target = LIS-1;
        List<Integer> answer = new ArrayList<>();

        for (int i = N - 1; i >= 0; i--) {
            if (pos[i] == target) {
                answer.add(pairs.get(i).id);
                target--;
            }
        }

        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();

        for (int ans : answer) {
            sb.append(ans).append(" ");
        }
        System.out.print(sb);

    }

    private static int binarySearch(int end, int value) {
        int start = 0;
        while (start < end) {
            int mid = (start + end) / 2;
            if (dp[mid] < value) {
                start = mid+1;
            } else {
                end = mid;
            }
        }

        return start;
    }

}
