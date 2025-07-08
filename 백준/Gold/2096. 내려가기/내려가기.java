import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(split[j]);
            }
        }

        int[][] dp = new int[N][3];
        int[][] dp2 = new int[N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = arr[i][j];
                dp2[i][j] = arr[i][j];
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    dp[i][j] = Math.max(dp[i-1][j] + dp[i][j], dp[i-1][j+1] + dp[i][j]);
                    dp2[i][j] = Math.min(dp2[i-1][j] + dp2[i][j], dp2[i-1][j+1] + dp2[i][j]);
                }

                else if (j == 1) {
                    dp[i][j] = Math.max(dp[i-1][j] + dp[i][j], Math.max(dp[i-1][j+1] + dp[i][j], dp[i-1][j-1] + dp[i][j]));
                    dp2[i][j] = Math.min(dp2[i-1][j] + dp2[i][j], Math.min(dp2[i-1][j+1] + dp2[i][j], dp2[i-1][j-1] + dp2[i][j]));
                }

                else {
                    dp[i][j] = Math.max(dp[i-1][j] + dp[i][j], dp[i-1][j-1] + dp[i][j]);
                    dp2[i][j] = Math.min(dp2[i-1][j] + dp2[i][j], dp2[i-1][j-1] + dp2[i][j]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dp[N-1][i]);
            min = Math.min(min, dp2[N-1][i]);
        }

        System.out.printf("%d %d", max, min);
    }

}