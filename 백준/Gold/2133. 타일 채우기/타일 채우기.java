import java.io.*;

/**
 * BOJ 2133
 * dp
 *
 * N이 홀수일 때 채우는 방법은 0가지
 * N이 2일 때 채우는 방법 3가지
 * 11 00 11
 * 11 00 00
 * 11 11 00
 *
 * N이 4일 때 채우는 방법 2가지 추가됨 -> 총 3*3+2 = 11가지
 * 1001 0000
 * 1001 1001
 * 0000 1001
 *
 * N이 6일 때 채우는 방법
 * - 기본 dp[4]*3 = 33
 * - 추가되는 것들 생각 = 2 * dp[2] + 2 = 8
 * 001001 100001
 * 001001 100001
 * 000000 000000
 * - 총 33 + 8 = 41
 *
 * N이 8일 때 채우는 방법
 * - 기본 dp[6] * 3 = 123
 * - 추가되는 것들 생각 = 2 * dp[4] + 2 * dp[2]
 * 00001001 10010000 10000001
 * 00001001 10011001 10000001
 * 00000000 00001001 00000000
 * 차례대로 dp[4], dp[2], 2
 *
 * 점화식
 * dp[0] = 1
 * if N % 2 == 1 : dp[N] = 0
 * else dp[N] = dp[N-2]*3 + sum(dp[N-4 ~ 0] * 2)
 */

public class Main {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        dp[0] = 1;
        for (int i = 2; i <= N; i++) {
            if (N % 2 == 1) continue;

            dp[i] = dp[i-2] * 3;

            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[N]);
    }

}
