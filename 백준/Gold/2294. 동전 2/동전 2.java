import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 2294 동전 2
 * dp
 *
 * dp[k] = dp[a] + dp[b] (k = a+b)
 *
 */

public class Main {

    static int n, k;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp = new int[k+1];
        Arrays.fill(dp, 1_000_000_000);

        for (int coin : arr) {
            for (int c = 1; c * coin <= k; c++) {
                dp[c*coin] = Math.min(dp[c*coin], c);
            }
        }

        for (int v = 2; v <= k; v++) {
            for (int a = 1; a <= v; a++) {
                dp[v] = Math.min(dp[a] + dp[v-a], dp[v]);
            }
        }

        int answer = dp[k] == 1_000_000_000 ? -1 : dp[k];
        System.out.println(answer);
    }
}