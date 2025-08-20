import java.io.*;
import java.util.*;

/**
 * boj 7570
 * dp, 그리디
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[n + 1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            dp[num[i]] = dp[num[i] - 1] + 1;
            max = Math.max(max, dp[num[i]]);
        }
        System.out.println(n - max);
    }
}
