import java.io.*;

/**
 * BOJ 2698
 * dp
 * dp[N][K][M] = 수열의 크기가 N이고 마지막을 M으로 채웠을 때 인접한 비트의 개수가 K개인 수열의 갯수
 * dp[5][2][0] =
 *  dp[4][2][1] + (전에 1일때 0을 채운다)
 *  dp[4][2][0] (전에 0일때 0을 채운다)
 * M = 0이면 K-1에서 올 수 없음.
 *
 * dp[5][2][1] =
 *  dp[4][2][0] + (1110 + 1)
 *  dp[4][1][1] (0011 + 1)
 *
 */

public class Main {

    static int T;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] split = br.readLine().split(" ");
            int N = Integer.parseInt(split[0]);
            int K = Integer.parseInt(split[1]);

            dp = new int[N+1][K+1][2];
            dp[1][0][0] = 1;
            dp[1][0][1] = 1;

            for (int j = 2; j <= N; j++) {
                for (int k = 0; k <= K; k++) {

                    if (k == 0) {
                        dp[j][0][1] = dp[j-1][0][0];
                        dp[j][0][0] = dp[j-1][0][0] + dp[j-1][0][1];
                        continue;
                    }

                    dp[j][k][0] = dp[j-1][k][1] + dp[j-1][k][0];
                    dp[j][k][1] = dp[j-1][k][0] + dp[j-1][k-1][1];
                }
            }

            bw.write(dp[N][K][0] + dp[N][K][1] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

}