import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;
        while (true) {
            input = br.readLine();
            if (input == null) break;
            input = input.trim();
            if (input.isEmpty()) continue;

            int n = Integer.parseInt(input);

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int LIS = 0;
            int[] dp = new int[n];

            for (int i = 0; i < n; i++) {
                int pos = lowerBound(dp, LIS, arr[i]);
                dp[pos] = arr[i];
                if (pos == LIS) LIS++;
            }

            sb.append(LIS).append("\n");
        }

        System.out.print(sb);
    }

    private static int lowerBound(int[] dp, int len, int target) {
        int s = 0, e = len;
        while (s < e) {
            int mid = (s + e) / 2;
            if (dp[mid] >= target) e = mid;
            else s = mid + 1;
        }
        return s;
    }
}
