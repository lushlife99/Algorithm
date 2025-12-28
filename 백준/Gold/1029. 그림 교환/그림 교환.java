import java.io.*;

/**
 * boj 1029 그림 교환
 * dp, 비트마스크
 */


public class Main {

    static int N;
    static int[][] costs;
    static boolean[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costs = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                costs[i][j] = s.charAt(j) - '0';
            }
        }

        dp = new boolean[1<<N][N][10];

        dfs(1,0,0);
        int answer = 0;

        for (int mask = 0; mask < 1 << N; mask++) {
            for (int node = 0; node < N; node++) {
                for (int cost = 0; cost < 10; cost++) {
                    if (dp[mask][node][cost]) {
                        answer = Math.max(answer, Integer.bitCount(mask));
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static void dfs(int mask, int current, int cost) {
        if (dp[mask][current][cost]) return;
        dp[mask][current][cost] = true;
        for (int next = 0; next < N; next++) {
            if (costs[current][next] >= cost && (mask & 1 << next) == 0) {
                dfs(mask | (1 << next), next, costs[current][next]);
            }
        }
    }
}
