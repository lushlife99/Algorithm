import java.io.*;

/**
 * BOJ 1727
 *
 * 악수하는 방법
 * N = 2 -> 1
 * N = 4 -> 2
 * N = 6 -> 2 * dp[4] + d[2] * dp[2] = 5
 * N = 8 -> 2 * dp[6] + dp[4] * dp[4] = 14
 *
 * dp[N] = dp[n-2] * dp[2] + dp[n-4] * dp[2] ... n-k >= 4
 *
 */

public class Main {

    static int N;
    static int[] dp;
    static int DIVIDED_NUMBER = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        dp[2] = 1;
        for (int i = 4; i <= N; i += 2) {
            dp[i] = (int)((2L * dp[i - 2]) % DIVIDED_NUMBER);
            for (int j = 2; i - j - 2 >= 2; j += 2) {
                dp[i] = (int)((dp[i] + (dp[i - j - 2] * 1L * dp[j]) % DIVIDED_NUMBER) % DIVIDED_NUMBER);
            }
        }
        System.out.println(dp[N]);
    }
}
