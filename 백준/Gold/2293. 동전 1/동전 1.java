import java.io.*;

/**
 * dp 문제
 * dp[K] += dp[K-arr[i]]
 */
public class Main {

    static int N;
    static int K;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);
        dp = new int[K + 1];
        dp[0] = 1;
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            int v = arr[i];
            for (int j = v; j <= K; j++) {
                dp[j] += dp[j - v];
            }
        }

        System.out.println(dp[K]);
    }
}