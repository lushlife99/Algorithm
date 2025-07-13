import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * dp[i][j] = dp[i][k] + dp[k+1][j]
 */
public class Main {

    static int N;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1][N+1];
        for (int i = 0; i < N+1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }
        String[] split = br.readLine().split(" ");
        arr[0] = Integer.parseInt(split[0]);
        arr[1] = Integer.parseInt(split[1]);
        for (int i = 2; i < N+1; i++) {
            arr[i] = Integer.parseInt(br.readLine().split(" ")[1]);
        }

        bottomUp();
        System.out.println(dp[1][N]);
    }

    private static void bottomUp() {
        for (int len = 1; len < N+1; len++) {
            for (int i = 1; i + len < N+1; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k+1][j] + arr[i-1] * arr[k] * arr[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
    }
}
