import java.io.*;

/**
 * BOJ 1915
 * 구간합?
 * 열, 행 연속된 1 횟수 구하는 시간복잡도 2 * 10^6
 *
 * arr1
 * 0 1 0 0
 * 0 3 2 1
 * 3 2 1 0
 * 0 0 1 0
 *
 * arr2
 * 0 3 0 0
 * 0 2 3 1
 * 1 1 2 0
 * 0 0 1 0
 *
 * dp[n][m] = min(min(arr1[n][m], arr2[n][m]), dp[n+1][m+1])
 */

public class Main {
    static int n;
    static int m;
    static int[][] dp;
    static int[][] arr;
    static int[][] sum1;
    static int[][] sum2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }




        sum1 = new int[n][m];
        sum2 = new int[n][m];

        // arrange sum1
        for (int i = 0; i < n; i++) {
            sum1[i][m-1] = arr[i][m-1];
            for (int j = m-2; j >= 0; j--) {
                if (arr[i][j] == 1) {
                    sum1[i][j] = sum1[i][j+1] + 1;
                } else {
                    sum1[i][j] = 0;
                }
            }
        }

        // arrange sum2
        for (int i = 0; i < m; i++) {
            sum2[n-1][i] = arr[n-1][i];
            for (int j = n-2; j >= 0; j--) {
                if (arr[j][i] == 1) {
                    sum2[j][i] = sum2[j+1][i] + 1;
                } else {
                    sum2[j][i] = 0;
                }
            }
        }

        dp = new int[n][m];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    dp[i][j] = 1;
                    answer = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            dp[i][m - 1] = arr[i][m - 1];
        }
        for (int j = 0; j < m; j++) {
            dp[n - 1][j] = arr[n - 1][j];
        }

        for (int i = n-2; i >= 0; i--) {
            for (int j = m-2; j >= 0; j--) {
                dp[i][j] = Math.min(Math.min(sum1[i][j], sum2[i][j]), dp[i+1][j+1]+1);
                answer = Integer.max(answer, dp[i][j]);
            }
        }

        System.out.println(answer * answer);
    }

}
