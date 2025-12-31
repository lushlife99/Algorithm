import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 13302 리조트
 * dp
 * 쿠폰 수, 쿠폰 사용 수, 현재 날짜
 * dp[day][c1][c2] = 최소 비용
 * 반례 - 최소
 * memory = 4*100*40*40 = 640,000
 */


public class Main {

    static int N, M;
    static boolean[] notUsed;
    static int[][][] dp;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        notUsed = new boolean[N + 1];

        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int day = Integer.parseInt(st.nextToken());
                notUsed[day] = true;
            }
        }

        dp = new int[N + 1][40][40];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 40; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});

        while (!queue.isEmpty()) {
            int[] c = queue.poll();
            int day = c[0];
            int totalCoupon = c[1];
            int usedCoupon = c[2];

            if (day == N) continue;


            if (notUsed[day + 1]) { // notUsed
                if (dp[day + 1][totalCoupon][usedCoupon] > dp[day][totalCoupon][usedCoupon]) {
                    dp[day + 1][totalCoupon][usedCoupon] = dp[day][totalCoupon][usedCoupon];
                    queue.add(new int[]{day + 1, totalCoupon, usedCoupon});
                }
            }


            // 쿠폰 사용 가능한경우
            if (totalCoupon >= usedCoupon+3) {
                if (dp[day + 1][totalCoupon][usedCoupon + 3] > dp[day][totalCoupon][usedCoupon]) {
                    dp[day + 1][totalCoupon][usedCoupon + 3] = dp[day][totalCoupon][usedCoupon];
                    queue.add(new int[]{day + 1, totalCoupon, usedCoupon + 3});
                }
            }

            // 1일권 구매

            if (dp[day + 1][totalCoupon][usedCoupon] > dp[day][totalCoupon][usedCoupon] + 10000) {
                dp[day + 1][totalCoupon][usedCoupon] = dp[day][totalCoupon][usedCoupon] + 10000;
                queue.add(new int[]{day + 1, totalCoupon, usedCoupon});
            }


            if (dp[Math.min(day + 3, N)][totalCoupon + 1][usedCoupon] > dp[day][totalCoupon][usedCoupon] + 25000) {
                dp[Math.min(day + 3, N)][totalCoupon + 1][usedCoupon] = dp[day][totalCoupon][usedCoupon] + 25000;
                queue.add(new int[]{Math.min(day + 3, N), totalCoupon + 1, usedCoupon});
            }

            if (dp[Math.min(day + 5, N)][totalCoupon + 2][usedCoupon] > dp[day][totalCoupon][usedCoupon] + 37000) {
                dp[Math.min(day + 5, N)][totalCoupon + 2][usedCoupon] = dp[day][totalCoupon][usedCoupon] + 37000;
                queue.add(new int[]{Math.min(day + 5, N), totalCoupon + 2, usedCoupon});
            }

        }

        int res = Integer.MAX_VALUE;

        for (
                int i = 0;
                i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                res = Math.min(res, dp[N][i][j]);
            }
        }

        return res;
    }

}
