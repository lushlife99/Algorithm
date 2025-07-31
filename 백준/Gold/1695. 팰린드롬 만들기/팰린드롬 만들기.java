import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 1695 팰린드롬 만들기
 * 왼쪽, 오른쪽 채워서 팰린드롬을 만들 수 있음
 * if (arr[left] == arr[right])
 *  dp[left][right] = dp[left+1][right-1]
 * else
 *  dp[left][right] = min(dp[left+1][right], dp[left][right-1])+1
 */

public class Main {

    static int N;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N][N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        }

        System.out.println(setDp(0, N-1));
    }

    private static int setDp(int left, int right) {
        if (left > right) return 0;
        if (dp[left][right] != -1) return dp[left][right];

        if (arr[left] == arr[right]) {
            dp[left][right] = setDp(left+1, right-1);
        } else {
            dp[left][right] = Math.min(setDp(left+1, right), setDp(left, right-1)) + 1;
        }

        return dp[left][right];
    }
}
