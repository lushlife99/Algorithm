import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 2098 외판원 순회
 * dp, 비트마스크
 */


public class Main {

    private static int N;
    private static int[][] arr;
    private static int[][] dp;
    private static int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 1));
    }

    private static int dfs(int current, int visited) {

        if (visited == (1 << N) - 1) {
            return arr[current][0] == 0 ? INF : arr[current][0];
        }

        if (dp[current][visited] != -1) return dp[current][visited];

        dp[current][visited] = INF;

        for (int next = 0; next < N; next++) {
            if (arr[current][next] != 0) {
                if ((visited & (1 << next)) == 1 << next) continue;
                if (arr[current][next] == 0) continue;

                int nextVisited = visited | (1 << next);
                int nextCost = dfs(next, nextVisited) + arr[current][next];

                dp[current][visited] = Math.min(dp[current][visited], nextCost);
            }
        }

        return dp[current][visited];
    }
}