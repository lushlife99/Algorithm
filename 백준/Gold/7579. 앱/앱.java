import java.io.*;
import java.util.StringTokenizer;


/**
 * boj 7579 앱
 * dp
 *
 * dp[a][cost] = a까지 cost를 사용해 해제할 수 있는 최대 메모리
 * dp[a][cost] = dp[a-1][cost-k] + m
 */

public class Main {

    private static int N, M;
    private static int[] memory;
    private static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memory = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        cost = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][10_001];
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int m = memory[i];
            int c = cost[i];

            for (int j = 0 ; j < dp[0].length; j++) {
                if (i == 0) {
                    if (j >= c) {
                        dp[i][j] = m;
                    }
                } else {
                    if (j >= c) {
                        dp[i][j] = Math.max(dp[i-1][j-c] + m, dp[i-1][j]);
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }

                if (dp[i][j] >= M) {
                    answer = Math.min(answer, j);
                }
            }
        }

        System.out.println(answer);
    }
}


