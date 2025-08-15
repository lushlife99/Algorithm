import java.io.*;

/**
 * boj 2718 타일채우기
 * dp
 *
 *
 */

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int T;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++){
            N = Integer.parseInt(br.readLine());
            dp = new int[N+2][5]; //

            dp[1][0] = 1;
            for(int j =2; j < N+2; j++){

                dp[j][0] = dp[j - 2][0] + dp[j - 1][0] + dp[j - 1][1] + dp[j - 1][4] + dp[j - 1][3];
                dp[j][1] = dp[j - 1][0] + dp[j - 1][4];
                dp[j][2] = dp[j - 1][3];
                dp[j][3] = dp[j - 1][0] + dp[j - 1][2];
                dp[j][4] = dp[j - 1][0] + dp[j - 1][1];
            }

            sb.append(dp[N+1][0]+ "\n");
        }
        System.out.print(sb);
        br.close();
    }
}