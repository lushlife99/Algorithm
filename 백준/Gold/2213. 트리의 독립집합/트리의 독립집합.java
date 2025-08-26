import java.io.*;
import java.util.*;

/**
 * boj 2213 트리의 독립집합
 * dp
 *
 */


public class Main {
    static int N;
    static int[] w;
    static List<List<Integer>> edges = new ArrayList<>();
    static int[][] dp;
    static boolean[] visited;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        w = new int[N+1];
        dp = new int[N+1][2];
        visited = new boolean[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= N; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            edges.get(n1).add(n2);
            edges.get(n2).add(n1);
        }

        dfs(1);
        bw.write(Math.max(dp[1][0], dp[1][1]) + "\n");

        visited = new boolean[N+1];
        reconstruct(1, false);
        Collections.sort(result);
        for (int x : result) {
            bw.write(x + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int u) {
        visited[u] = true;
        dp[u][0] = 0;
        dp[u][1] = w[u];

        for (int v : edges.get(u)) {
            if (!visited[v]) {
                dfs(v);
                dp[u][0] += Math.max(dp[v][0], dp[v][1]);
                dp[u][1] += dp[v][0];
            }
        }
    }

    private static void reconstruct(int u, boolean parentTaken) {
        visited[u] = true;

        boolean take = false;
        if (!parentTaken && dp[u][1] > dp[u][0]) {
            take = true;
            result.add(u);
        }

        for (int v : edges.get(u)) {
            if (!visited[v]) {
                reconstruct(v, take);
            }
        }
    }
}
