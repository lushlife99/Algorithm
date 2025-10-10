import java.util.*;

public class Main {
    static final int MOD = 1_000_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        long[][][] dp = new long[N+1][10][1 << 10];

        for (int j = 1; j <= 9; j++) {
            dp[1][j][1 << j] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int last = 0; last < 10; last++) {
                for (int mask = 0; mask < (1 << 10); mask++) {
                    long curCount = dp[i-1][last][mask];
                    if (curCount == 0) continue;

                    if (last > 0) {
                        int nxt = last - 1;
                        int newMask = mask | (1 << nxt);
                        dp[i][nxt][newMask] = (dp[i][nxt][newMask] + curCount) % MOD;
                    }
                    if (last < 9) {
                        int nxt = last + 1;
                        int newMask = mask | (1 << nxt);
                        dp[i][nxt][newMask] = (dp[i][nxt][newMask] + curCount) % MOD;
                    }
                }
            }
        }

        int fullMask = (1 << 10) - 1;
        long answer = 0;
        for (int j = 0; j < 10; j++) {
            answer = (answer + dp[N][j][fullMask]) % MOD;
        }

        System.out.println(answer);
    }
}
