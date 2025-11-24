import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 12014 주식
 * LIS
 */


public class Main {

    private static int T;
    private static int N, K;
    private static int[] arr;
    private static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N];
            dp = new int[N];

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            int LIS = 0;
            for (int j = 0; j < N; j++) {
                int idx = binarySearch(LIS, arr[j]);
                dp[idx] = arr[j];
                if (idx == LIS) LIS++;
            }

            int answer = LIS >= K ? 1 : 0;
            sb.append("Case #").append(i+1).append("\n");
            sb.append(answer).append("\n");

        }

        System.out.print(sb);
    }

    private static int binarySearch(int end, int value) {
        int start = 0;

        while (start < end) {
            int mid = (start + end) / 2;

            if (dp[mid] < value) {
                start = mid+1;
            } else {
                end = mid;
            }
        }

        return start;
    }

}
