import java.io.*;
import java.util.Arrays;

/**
 * BOJ 2643
 * dp
 * dp[N] = N으로 시작하는 가장 큰 색종이 갯수
 * if arr[N] >= arr[K]
 *  dp[N] = max(dp[N], dp[K]+1)
 */

public class Main {

    static int N;
    static int[][] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N];
        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            int max = Math.max(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            int min = Math.min(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            arr[i][0] = max;
            arr[i][1] = min;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (j == k) continue;

                    if (arr[j][0] >= arr[k][0] && arr[j][1] >= arr[k][1]) {
                        dp[j] = Math.max(dp[j], dp[k]+1);
                    }
                }
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt()+1);
    }
}
