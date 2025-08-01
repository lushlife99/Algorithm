import java.io.*;
import java.util.Scanner;

/**
 * BOJ 1947 선물 전달
 * dp[N] = N명이 모자를 교환할 수 있는 방법의 수
 *
 * N = 5
 * dp[1] = 0
 * dp[2] = 1
 * dp[3] = 312, 231 = 2
 * dp[4] = 2143, 2341, 2413, 3142, 3412, 3421, 4123, 4312, 4321 = 9
 *
 * dp[N] = (N-1) * (dp[N-1] + dp[N-2])
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long mod = 1000000000;
        int n = Integer.parseInt(br.readLine());
        
        if (n < 2) {
            System.out.println(0);
            return;
        }
        
        long [] dp = new long[n+1];

        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;

        for(int i = 3; i <= n; i++){
            dp[i] = (i-1)*(dp[i-2]+dp[i-1])%mod;
        }
        System.out.println(dp[n]);
    }
}
