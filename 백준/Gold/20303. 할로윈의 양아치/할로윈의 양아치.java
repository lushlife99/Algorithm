import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 20303 할로윈의 양아치
 * knapsack, union-find
 *
 * k, c
 * dp[n] = dp[n-k] + c
 */

/**
 * 반례
 * N이 3000보다 작을 경우, 1일경우
 * M이 0일경우
 * 사탕 최대 int 범위 넘음
 */

public class Main {

    private static int N, M, K;
    private static long[] candy;
    private static int[] cnt;
    private static List<Integer>[] edges;
    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        candy = new long[N+1];
        cnt = new int[N+1];
        parent = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
            cnt[i] = 1;
        }

        edges = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }

        // root 개수 구하기
        List<Integer> root = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (find(i) == i) {
                root.add(i);
            }
        }

        long[] candy2 = new long[root.size()];
        int[] cnt2 = new int[root.size()];
        for (int i = 0; i < root.size(); i++) {
            candy2[i] = candy[root.get(i)];
            cnt2[i] = cnt[root.get(i)];
        }
        candy = candy2;
        cnt = cnt2;
        candy2 = null;
        cnt2 = null;

        long[] dp = new long[K];

        for (int i = 0; i < root.size(); i++) {
            for (int j = K-1; j - cnt[i] >= 0; j--) {
                if (dp[j - cnt[i]] > 0) {
                    dp[j] = Math.max(dp[j - cnt[i]] + candy[i], dp[j]);
                }
            }

            if (cnt[i] < K) {
                dp[cnt[i]] = Math.max(dp[cnt[i]], candy[i]);
            }
        }


        long answer = 0;
        for (int i = 0; i < K; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }

    private static int find(int a) {
        if (a != parent[a]) {
            parent[a] = find(parent[a]);
        }

        return parent[a];
    }

    private static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb) return;
        if (ra > rb) {
            int temp = ra; ra = rb; rb = temp;
        }

        parent[rb] = ra;
        candy[ra] += candy[rb];
        cnt[ra] += cnt[rb];
    }
}