import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 2225 합분해
 * dp
 */


public class Main {

    static int MOD = 1000000000;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int dp[][] = new int[N+1][K+1];

        for(int i=0;i<=N;i++){
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        for(int i=0;i<=K;i++){
            dp[1][i] = i;
        }

        for(int i=2;i<=N;i++){
            for(int j=2;j<=K;j++){
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
            }
        }

        System.out.println(dp[N][K]);
    }
}
