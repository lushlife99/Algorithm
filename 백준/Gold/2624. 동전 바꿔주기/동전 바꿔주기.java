import java.io.*;
import java.util.StringTokenizer;

/**
 * BOJ 2624
 *
 * dp[cost][cnt] += dp[cost-K][cnt]
 */

public class Main {

    static int T;
    static int K;
    static int[][] dp;
    static Coin[] coins;

    static class Coin {
        int val, cnt;

        Coin(int val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        dp = new int[T+1][K+1];
        coins = new Coin[K];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int val = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            coins[i] = new Coin(val, cnt);
        }

        for (int i = 1; i <= K; i++) {
            int val = coins[i-1].val;
            int cnt = coins[i-1].cnt;
            dp[0][i-1] = 1;

            for (int j = 1; j <= cnt; j++) {
                for (int l = val*j; l <= T; l++) {
                    dp[l][i] += dp[l-val*j][i-1];
                }
            }

            for (int j = 1; j <= T; j++) {
                dp[j][i] += dp[j][i-1];
            }
        }

        System.out.println(dp[T][K]);
    }


}
