import java.io.*;
import java.util.*;

/**
 * boj 1014 컨닝
 * dp
 */


public class Main {
    static int N, M;
    static char[][] board;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            board = new char[N][M];
            for (int i = 0; i < N; i++) {
                board[i] = br.readLine().toCharArray();
            }

            int maxMask = 1 << M;
            dp = new int[N][maxMask];
            for (int[] row : dp) Arrays.fill(row, -1);

            for (int mask = 0; mask < maxMask; mask++) {
                if (valid(mask, 0)) {
                    dp[0][mask] = Integer.bitCount(mask);
                }
            }

            for (int r = 1; r < N; r++) {
                for (int mask = 0; mask < maxMask; mask++) {
                    if (!valid(mask, r)) continue;

                    for (int pmask = 0; pmask < maxMask; pmask++) {
                        if (dp[r - 1][pmask] == -1) continue;
                        if ((mask & (pmask << 1)) != 0) continue;
                        if ((mask & (pmask >> 1)) != 0) continue;

                        dp[r][mask] = Math.max(
                                dp[r][mask],
                                dp[r - 1][pmask] + Integer.bitCount(mask)
                        );
                    }
                }
            }

            int ans = 0;
            for (int mask = 0; mask < maxMask; mask++) {
                ans = Math.max(ans, dp[N - 1][mask]);
            }
            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }

    static boolean valid(int mask, int r) {
        if ((mask & (mask << 1)) != 0) return false;

        for (int c = 0; c < M; c++) {
            if (((mask >> c) & 1) == 1 && board[r][c] == 'x') {
                return false;
            }
        }
        return true;
    }
}
