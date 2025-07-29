import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 1727
 * greedy, dp
 *
 * if (male == female)
 *  dp[2][2] = dp[1][1] + gap(male[1], female[1])
 * 
 * if (male > female)
 *  dp[4][2] = min(dp[3][2], gap(male[3], female[1]) + dp[3][1]
 */

public class Main {

    static int N;
    static int M;
    static int[] male;
    static int[] female;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        dp = new int[N+1][M+1];
        male = new int[N];
        female = new int[M];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            male[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            female[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(male);
        Arrays.sort(female);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (i == j) {
                    dp[i][j] = Math.abs(male[i-1] - female[j-1]) + dp[i-1][j-1];
                } else if (i > j) {
                    dp[i][j] = Math.min(dp[i-1][j], Math.abs(male[i-1] - female[j-1]) + dp[i-1][j-1]);
                } else {
                    dp[i][j] = Math.min(dp[i][j-1], Math.abs(male[i-1] - female[j-1]) + dp[i-1][j-1]);
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}
