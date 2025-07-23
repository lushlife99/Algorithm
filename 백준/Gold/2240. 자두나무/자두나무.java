import java.io.*;
import java.util.Arrays;

/**
 * BOJ 2240
 * dp
 * <p>
 * dp[time][direction][cnt]
 * dp[T][2][cnt] = max(dp[T-1][1][cnt-1], dp[T-1][2][cnt]) + 1
 * <p>
 * 68% 틀림. 반례 찾기, 디버깅
 * <p>
 * dp[1][2][0~2] 가 모두 1
 * 의도대로라면 dp[1][2][1] 만 1이 되어야 함
 */

public class Main {

    static int T;
    static int W;
    static int[] time;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        T = Integer.parseInt(split[0]);
        W = Integer.parseInt(split[1]);
        time = new int[T + 1];
        dp = new int[T + 1][3][W + 1];
        for (int i = 0; i < T; i++) {
            time[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <= T; i++) {
            for (int j = 1; j < 3; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }

        dp[0][1][0] = 0;

        for (int j = 0; j <= W; j++) {
            for (int i = 1; i <= T; i++) {

                if (time[i - 1] == 1) {
                    if (j == 0) {
                        dp[i][1][0] = dp[i - 1][1][0] + 1;
                    } else {
                        int value = Math.max(dp[i - 1][1][j], dp[i - 1][2][j - 1]);
                        if (value != Integer.MIN_VALUE) {
                            dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][2][j - 1]) + 1;
                        }
                    }
                    dp[i][2][j] = dp[i - 1][2][j];
                } else {
                    if (j != 0) {
                        int value = Math.max(dp[i - 1][2][j], dp[i - 1][1][j - 1]);
                        if (value != Integer.MIN_VALUE) {
                            dp[i][2][j] = value + 1;
                        }
                    }
                    dp[i][1][j] = dp[i - 1][1][j];
                }
            }
        }
        int answer = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <= W; j++) {
                answer = Math.max(dp[T][i][j], answer);
            }
        }
        System.out.println(answer);

    }
}
