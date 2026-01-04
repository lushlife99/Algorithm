import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 11066 파일 합치기
 * dp
 */


public class Main {

    static int T, K;
    static int[] arr, sum;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (T-->0) {
            K = Integer.parseInt(br.readLine());
            arr = new int[K+1];
            sum = new int[K+1];
            dp = new int[K+1][K+1];

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            sum[1] = arr[1];
            for (int i = 1; i < K; i++) {
                sum[i+1] = sum[i] + arr[i+1];
            }

            for (int i = 1; i < K; i++) {
                for (int start = 1; start + i <= K; start++) {
                    int end = start+i;
                    dp[start][end] = Integer.MAX_VALUE;

                    for (int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid+1][end] + sum[end] - sum[start-1]);
                    }
                }
            }

            sb.append(dp[1][K]).append("\n");
        }

        System.out.print(sb);
    }
}
