import java.io.*;
import java.util.*;

/**
 * boj 2616 소형기관차
 * dp, 부분합
 * dp[N][M] = Max(dp[N-K][M-1] + sum, prev)
 */

public class Main {

    private static int N;
    private static int[] sum;
    private static int K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        sum = new int[N+1];
        sum[1] = Integer.parseInt(st.nextToken());

        for (int i = 2; i <= N; i++) {
            sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
        }

        K = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][4];

        for (int i = 1; i <= 3; i++) {
            for (int j = K*i; j <= N; j++) {
                dp[j][i] = Math.max(dp[j-K][i-1] + sum[j] - sum[j-K], dp[j-1][i]);
            }
        }

        System.out.println(dp[N][3]);
    }
}