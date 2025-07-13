import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * O A B C
 * A
 * B
 * C
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

        System.out.println(topDown(1, N));
    }

    private static int topDown(int a, int b) {
        if (a==b) return 0;
        if (dp[a][b] != Integer.MAX_VALUE) return dp[a][b];
        for (int k = a; k < b; k++) {
            int value = topDown(a, k) + topDown(k+1, b) +
                    arr[a-1] * arr[k] * arr[b];
            dp[a][b] = Math.min(dp[a][b], value);
        }

        return dp[a][b];
    }
}
