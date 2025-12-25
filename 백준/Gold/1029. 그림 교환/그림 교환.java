import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * boj 1029 그림 교환
 * dp, 비트마스크
 *
 * 1. 방문 노드
 * 2. 현재 가격
 * 3. 현재 노드
 * dp[1][2][3] =
 */


public class Main {

    static int N;
    static int[][] costs;
    static boolean[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        costs = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                costs[i][j] = s.charAt(j) - '0';
            }
        }

        dp = new boolean[1<<N][10][N];

        bfs();
        int answer = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < N; k++) {
                    if (dp[i][j][k]) {
                        int cnt = 0;
                        for (int l = 0; l < N; l++) {
                            if (((1<<l) & i) != 0) cnt++;
                        }

                        answer = Math.max(answer, cnt);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1,0,0});
        dp[1][0][0] = true;

        while (!queue.isEmpty()) {
            int[] c = queue.poll();
            int mask = c[0];
            int curCost = c[1];
            int curNode = c[2];

            for (int i = 0; i < N; i++) {
                if (curCost <= costs[curNode][i]
                    && (mask & (1<<i)) == 0) {
                    if (!dp[mask|(1<<i)][costs[curNode][i]][i]) {
                        dp[mask | (1 << i)][costs[curNode][i]][i] = true;
                        queue.add(new int[]{mask | (1 << i), costs[curNode][i], i});
                    }
                }
            }
        }
    }

}
