import java.io.*;
import java.util.*;

/**
 * boj 1520 내리막 길
 */


public class Main {

    static int M, N;
    static int[][] maze;
    static int[][] dp;
    static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        maze = new int[M][N];
        dp = new int[M][N];
        for (int[] row : dp) Arrays.fill(row, -1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0,0));
    }

    static int dfs(int x, int y) {
        if (dp[x][y] != -1) return dp[x][y];
        if (x == M-1 && y == N-1) return 1;

        int res = 0;
        for (int i = 0; i < directions.length; i++) {
            int nx = x + directions[i][0];
            int ny = y + directions[i][1];

            if (nx < 0 || ny < 0 || nx == M || ny == N) continue;
            if (maze[x][y] <= maze[nx][ny]) continue;
            res += dfs(nx, ny);
        }

        return dp[x][y] = res;
    }

}
