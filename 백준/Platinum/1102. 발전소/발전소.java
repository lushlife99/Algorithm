import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 1102 발전소
 * dp, 비트마스크
 *
 * dp[mask][node] = cost
 */


public class Main {

    static int N, P;
    static int[][] costs;
    static int[] dp;
    static String s;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        costs = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[1<<N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        s = br.readLine();
        P = Integer.parseInt(br.readLine());

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        int visitedMask = 0;


        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'Y') {
                visitedMask = visitedMask | 1<<i;
            }
        }

        queue.add(visitedMask);
        dp[visitedMask] = 0;

        while(!queue.isEmpty()) {
            int curMask = queue.poll();

            for (int current = 0; current < N; current++) {
                if ((curMask & (1<<current)) == 0) continue;

                for (int next = 0; next < N; next++) {
                    if ((curMask & (1<<next)) != 0) continue;
                    int nextMask = curMask | (1<<next);
                    if (dp[nextMask] > dp[curMask] + costs[current][next]) {
                        dp[nextMask] = dp[curMask] + costs[current][next];
                        queue.add(nextMask);
                    }
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int mask = 0; mask < 1 << N; mask++) {
            if (Integer.bitCount(mask) < P) continue;
            res = Math.min(res, dp[mask]);
        }


        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
