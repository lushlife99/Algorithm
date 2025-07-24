import java.io.*;
import java.util.Map;

/**
 * BOJ 2602
 * <p>
 * dp[idx][N][cnt] = N번 다리의 Idx까지 건널 때 cnt의 갯수
 * <p>
 * if chars[cnt] == A[idx]
 * if len == 0
 * dp[idx][2][0] = 1
 * <p>
 * else dp[idx][2][cnt] = dp[idx-1][1][cnt-1]
 * <p>
 * else dp[idx][N][cnt] = dp[idx-1][N][cnt]
 */

public class Main {

    static char[] chars;
    static char[] A;
    static char[] B;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        chars = br.readLine().toCharArray();
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();

        dp = new int[A.length + 1][2][chars.length + 1];
        for (int k = 1; k <= chars.length; k++) {
            for (int i = 1; i < dp.length; i++) {
                dp[i][0][k] = dp[i-1][0][k];
                dp[i][1][k] = dp[i-1][1][k];

                if (chars[k - 1] == A[i - 1]) {
                    if (k == 1) {
                        dp[i][0][1] = dp[i - 1][0][1] + 1;
                    } else {
                        dp[i][0][k] += dp[i-1][1][k - 1];
                    }
                }

                if (chars[k - 1] == B[i - 1]) {
                    if (k == 1) {
                        dp[i][1][1] = dp[i - 1][1][1] + 1;
                    } else {
                        dp[i][1][k] += dp[i-1][0][k - 1];
                    }
                }
            }
        }

        int answer = 0;
        answer += dp[A.length][0][chars.length] + dp[A.length][1][chars.length];

        System.out.println(answer);
    }
}
