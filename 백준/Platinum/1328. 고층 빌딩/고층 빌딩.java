import java.io.*;
import java.util.*;

/**
 * boj 1328 고층 빌딩
 * N = 1
 * 1,1 = 1
 *
 * N = 2
 * 2,1 = 1
 * 1,2 = 1
 *
 * N = 3
 * 3,1 = 1
 * 2,2 = 2
 * 1,3 = 1
 */


public class Main {

    private static int N, L, R;
    private static int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        long[][][] dp = new long[N+1][N+1][N+1];
        dp[1][1][1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][i][1] = dp[i][1][i] = 1;
            for (int j = 1; j <= L; j++) {
                for (int k = 1; k <= R; k++) {
                    dp[i][j][k] = (dp[i - 1][j][k-1] +dp[i - 1][j - 1][k]
                            + (dp[i - 1][j][k] * (i-2))) % MOD;
                }
            }
        }

        System.out.println(dp[N][L][R]);
    }
}