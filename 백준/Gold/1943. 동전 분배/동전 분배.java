import java.io.*;
import java.util.*;

/**
 * BOJ 1943 동전 분배
 * 60%쯤에 메모리 초과됨. 답지봄
 *
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int tc = 0; tc < 3; tc++) {
            int N = Integer.parseInt(br.readLine());
            List<int[]> coins = new ArrayList<>();
            int total = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int value = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                coins.add(new int[]{value, count});
                total += value * count;
            }

            if ((total & 1) == 1) {
                bw.write("0\n");
                continue;
            }

            int target = total / 2;
            int[] dp = new int[target + 1];
            Arrays.fill(dp, -1);
            dp[0] = 0;

            for (int[] coin : coins) {
                int value = coin[0], count = coin[1];

                for (int i = 0; i <= target; i++) {
                    if (dp[i] >= 0) {
                        dp[i] = count;
                    }
                    else if (i >= value && dp[i - value] > 0) {
                        dp[i] = dp[i - value] - 1;
                    }
                    else {
                        dp[i] = -1;
                    }
                }
            }

            bw.write(dp[target] >= 0 ? "1\n" : "0\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
