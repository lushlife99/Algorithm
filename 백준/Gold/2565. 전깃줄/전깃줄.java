import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * boj 2565 전깃줄
 * lis
 */


public class Main {

    static int N;
    static int[][] edge;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        edge = new int[N][2];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            edge[i][0] = Integer.parseInt(st.nextToken());
            edge[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(edge, (a, b) -> {
            return a[0] - b[0];
        });

        dp = new int[N];
        int LIS = 0;

        for (int i = 0; i < N; i++) {
            int val = edge[i][1];
            int idx = binarySearch(val, 0, LIS, LIS+1);
            if (idx == -1) dp[LIS++] = val;
            else dp[idx] = val;
        }

        System.out.println(N - LIS);
    }

    static int binarySearch(int val, int start, int end, int size) {
        int res = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (val <= dp[mid]) {
                res = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }

        if (start == size) return -1;
        return res;
    }
}
