import java.io.*;

/**
 * BOJ 1958
 *
 * LCS -> dp
 *
 * dp[i][j][k] = A의 0 ~ i-1까지, B의 0 ~ j-1까지, C의 0~k-1 까지의 LCS 길이
 *
 * if A[i-1] == B[j-1] == C[k-1] -> dp[i][j][k] = dp[i-1][j-1][k-1] + 1
 * else -> dp[i][j][k] = max(dp[i-1][j][k], dp[i][j-1][k], dp[i][j][k-1])
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        String C = br.readLine();
        int[][][] dp = new int[A.length()+1][B.length()+1][C.length()+1];

        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                for (int k = 1; k <= C.length(); k++) {
                    if (A.charAt(i-1) == B.charAt(j-1) && B.charAt(j-1) == C.charAt(k-1)) {
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i-1][j][k], Math.max(dp[i][j-1][k], dp[i][j][k-1]));
                    }
                }
            }
        }

        System.out.println(dp[A.length()][B.length()][C.length()]);
    }

}
