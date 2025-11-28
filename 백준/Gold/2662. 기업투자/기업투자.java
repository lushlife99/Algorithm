import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 2662 기업투자
 * knapsack
 *
 * dp[n] = MAX(dp[n], dp[n-k] + arr[k][3])
 */


public class Main {

    private static int N, M;
    private static int[][] profit;
    private static int[][] dp;
    private static int[][] choice;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        profit = new int[M+1][N+1];
        dp = new int[M+1][N+1];
        choice = new int[M+1][N+1];

        for (int money = 1; money <= N; money++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            for (int company = 1; company <= M; company++) {
                profit[company][money] = Integer.parseInt(st.nextToken());
            }
        }

        for (int comp = 1; comp <= M; comp++) {
            for (int money = 0; money <= N; money++) {
                for (int invest = 0; invest <= money; invest++) {
                    int val = dp[comp - 1][money - invest] + profit[comp][invest];
                    if (dp[comp][money] < val) {
                        dp[comp][money] = val;
                        choice[comp][money] = invest;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[M][N]).append("\n");

        int[] result = new int[M+1];
        int money = N;

        for (int comp = M; comp >= 1; comp--) {
            result[comp] = choice[comp][money];
            money -= result[comp];
        }

        for (int comp = 1; comp <= M; comp++) {
            sb.append(result[comp] + " ");
        }

        System.out.print(sb);
    }
}
