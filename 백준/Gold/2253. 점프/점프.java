import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * BOJ 2253
 * dp
 *
 * dp[N][jump] = min(dp[N-jump][jump-1 ~ jump+1])
 */

public class Main {

    static int N;
    static int M;
    static int[] arr;
    static int[][] dp;
    static Set<Integer> smallStone = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");

        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        arr = new int[M];
        dp = new int[N+1][201];
        for (int i = 0; i < N+1; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(br.readLine());
            smallStone.add(n);
        }

        dp[1][0] = 0;

        for (int i = 2; i <= N; i++) {
            if (smallStone.contains(i)) continue;

            for (int j = Math.min(199, i); j > 0; j--) {
                int value = Integer.MAX_VALUE;
                for (int k = -1; k <= 1; k++) {
                    if (dp[i-j][j+k] != -1) {
                        value = Math.min(value, dp[i-j][j+k]);
                    }
                }
                if (value != Integer.MAX_VALUE) {
                    dp[i][j] = value+1;
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 1; i < 200; i++) {
            if (dp[N][i] != -1) {
                answer = Math.min(answer, dp[N][i]);
            }
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
