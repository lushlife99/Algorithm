import java.io.*;

/**
 * BOJ 1520
 *
 * dp[N][M] = 상 + 하 + 좌 + 우
 *
 * 우,하 먼저 갱신
 * 좌,상 갱신. 이 때 업데이트가 발생하면 그래프를 순회해서 더해진 값만큼 업데이트
 */

public class Main {

    static int N;
    static int M;
    static int[] dx = {1, -1 ,0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        arr = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(split[j]);
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int x, int y) {
        if (x == N - 1 && y == M - 1) return 1;

        if (dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (arr[x][y] > arr[nx][ny]) {
                dp[x][y] += dfs(nx, ny);
            }
        }

        return dp[x][y];
    }



}
