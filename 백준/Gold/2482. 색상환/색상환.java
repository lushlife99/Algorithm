import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 2482 색상환
 * dp
*/

public class Main {

    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
        }

        for (int i = 3; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
            }
        }

        int result = (dp[N - 1][K] + dp[N - 3][K - 1]) % MOD;

        System.out.println(result);
    }
}
