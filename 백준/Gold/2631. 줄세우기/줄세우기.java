import java.io.*;

/**
 * BOJ 2631
 *
 * LIS (Longest Increasing Subsequence)
 */

public class Main {

    static int N;
    static int[] dp;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N];
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLCS = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            maxLCS = Math.max(maxLCS, dp[i]);
        }
        System.out.println(N - maxLCS);
    }


}
