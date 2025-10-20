import java.io.*;
import java.util.*;

/**
 * boj 11066 파일 합치기
 * dp
 */

public class Main {

    private static int T;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] files = new int[K+1];
            int[] fileSum = new int[K+1];
            int[][] dp = new int[K+1][K+1];

            for (int i = 1; i <= K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
            }

            fileSum[1] = files[1];

            for (int i = 1; i < K; i++) {
                fileSum[i+1] = fileSum[i] + files[i+1];
            }

            for (int range = 1; range < K; range++) {
                for (int start = 1; start + range <= K; start++) {
                    int end = start + range;
                    dp[start][end] = Integer.MAX_VALUE;

                    for (int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid+1][end] + fileSum[end] - fileSum[start-1]);
                    }
                }
            }

            sb.append(dp[1][K]).append("\n");
        }

        System.out.println(sb.toString());
    }
}

