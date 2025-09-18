import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * boj 1994 등차수열
 * dp
 * 
 * 계속 40% 틀림. 답지봄 
 */


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] arr = new int[n];
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(br.readLine().trim());
            arr[i] = v;
            freq.put(v, freq.getOrDefault(v, 0) + 1);
        }

        if (n == 1) {
            System.out.println(1);
            return;
        }

        int maxFreq = 0;
        for (int c : freq.values()) if (c > maxFreq) maxFreq = c;

        int m = freq.size();
        int[] uniq = new int[m];
        int idx = 0;
        for (int key : freq.keySet()) uniq[idx++] = key;
        Arrays.sort(uniq);

        if (m == 1) {
            System.out.println(maxFreq);
            return;
        }

        int[][] dp = new int[m][m];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], 2);

        int ans = 2;
        for (int j = m - 2; j >= 1; j--) {
            int i = j - 1;
            int k = j + 1;
            while (i >= 0 && k < m) {
                long sum = (long)uniq[i] + uniq[k];
                long mid2 = 2L * uniq[j];
                if (sum == mid2) {
                    dp[i][j] = dp[j][k] + 1;
                    if (dp[i][j] > ans) ans = dp[i][j];
                    i--; k++;
                } else if (sum < mid2) {
                    k++;
                } else {
                    i--;
                }
            }
        }

        ans = Math.max(ans, maxFreq);
        System.out.println(ans);
    }
}
