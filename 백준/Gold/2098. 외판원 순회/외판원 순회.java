
import java.io.*;
import java.util.StringTokenizer;


/**
 * boj 2098 외판원 순회
 */

public class Main {
    private static int N;
    private static int[][] cost;
    private static int[][] dp;
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < (1 << N); j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 1));
    }

    private static int dfs(int current, int visited) {
        if (visited == (1 << N) - 1) {
            return cost[current][0] == 0 ? INF : cost[current][0];
        }

        if (dp[current][visited] != -1) {
            return dp[current][visited];
        }

        dp[current][visited] = INF;
        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) != 0) continue;
            if (cost[current][next] == 0) continue;

            int nextCost = dfs(next, visited | (1 << next)) + cost[current][next];
            dp[current][visited] = Math.min(dp[current][visited], nextCost);
        }

        return dp[current][visited];
    }
}


