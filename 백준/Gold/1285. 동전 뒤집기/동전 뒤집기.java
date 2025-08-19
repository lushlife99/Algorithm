import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * boj 1285 동전 뒤집기
 * 그리디, DFS, 백트래킹
 */

public class Main {
    static int N;
    static int[][] board;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = (line.charAt(j) == 'T') ? 1 : 0;
            }
        }

        dfs(0);
        System.out.println(ans);
    }

    static void dfs(int row) {
        if (row == N) {
            int sum = 0;
            for (int col = 0; col < N; col++) {
                int cnt = 0;
                for (int r = 0; r < N; r++) {
                    cnt += board[r][col];
                }
                sum += Math.min(cnt, N - cnt);
            }
            ans = Math.min(ans, sum);
            return;
        }

        dfs(row + 1);
        flipRow(row);
        dfs(row + 1);
        flipRow(row);
    }

    static void flipRow(int row) {
        for (int col = 0; col < N; col++) {
            board[row][col] ^= 1;
        }
    }
}
