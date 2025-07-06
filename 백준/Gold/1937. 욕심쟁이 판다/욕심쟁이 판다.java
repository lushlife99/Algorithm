import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] arr, dp;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] tokens = bf.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                answer = Math.max(answer, dfs(i, j));

        System.out.println(answer);
    }

    private static int dfs(int x, int y) {
        if (dp[x][y] != 0) return dp[x][y];
        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 > nx || nx >= N || 0 > ny || ny >= N) continue;
            if (arr[x][y] < arr[nx][ny]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
            }
        }

        return dp[x][y];
    }
}
