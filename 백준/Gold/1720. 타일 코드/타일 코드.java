import java.io.*;

public class Main {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        dp = new int[N + 1];

        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 2 * dp[i - 2];
        }

        int result = dp[N];
        int sym = 0;
        if (N % 2 == 0) {
            sym = dp[N / 2] + 2 * dp[N / 2 - 1];
        } else {
            sym = dp[N / 2];
        }

        System.out.println((result + sym) / 2);
    }
}
