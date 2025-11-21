import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 1818 책정리
 * LIS
*/

public class Main {

    private static int N;
    private static int[] arr;
    private static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int LIS = 0;
        for (int i = 0; i < N; i++) {
            int idx = binarySearch(LIS, arr[i]);
            dp[idx] = arr[i];
            if (idx == LIS) LIS++;
        }

        System.out.println(N - LIS);
    }

    private static int binarySearch(int e, int value) {
        int s = 0;
        while (s < e) {
            int m = (s + e) / 2;
            if (dp[m] >= value) {
                e = m;
            } else {
                s = m+1;
            }
        }
        return s;
    }
}
