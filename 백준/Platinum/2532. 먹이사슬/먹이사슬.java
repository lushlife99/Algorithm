import java.io.*;
import java.util.*;


/**
 * boj 2532 먹이사슬
 * LIS
 *
 * 최대로 겹치는 개수 구하기
 */


public class Main {

    static class Pair implements Comparable<Pair> {
        int id, n1, n2;

        public Pair(int id, int n1, int n2) {
            this.id = id; this.n1 = n1; this.n2 = n2;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.n1 != o.n1) return this.n1 - o.n1;
            return o.n2 - this.n2;
        }

        @Override
        public String toString() {
            return this.id + " " + n1 + " " + n2;
        }

        @Override
        public boolean equals(Object o) {
            Pair op = (Pair) o;
            return this.n1 == op.n1 && this.n2 == op.n2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(n1, n2);
        }
    }

    private static int N;
    private static Set<Pair> pairSet = new HashSet<>();
    private static List<Pair> pairList = new ArrayList<>();
    private static int[] dp;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            pairSet.add(new Pair(id, n1, n2));
        }

        dp = new int[N];
        pairList = new ArrayList<>(pairSet);
        Collections.sort(pairList);

        int LIS = 0;
        for (int i = 0; i < pairList.size(); i++) {
            Pair p = pairList.get(i);
            int idx = upperBound(LIS, p.n2);
            dp[idx] = p.n2;
            if (idx == LIS) LIS++;
        }

        System.out.println(LIS);

    }

    private static int upperBound(int end, int value) {
        int start = 0;
        while (start < end) {
            int mid = (start + end) / 2;
            if (dp[mid] < value) {
                end = mid;
            } else {
                start = mid+1;
            }
        }

        return start;
    }

}
