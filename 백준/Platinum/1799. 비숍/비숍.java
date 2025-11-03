import java.io.*;
import java.util.*;

/**
 * boj 1799 비숍
 * 백트래킹
 */

public class Main {
    static int N;
    static int[][] board;
    static boolean[] left, right;
    static int[] answer = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        left = new boolean[2 * N];
        right = new boolean[2 * N];

        dfs(0, 0, 0);
        dfs(0, 1, 0);

        System.out.println(answer[0] + answer[1]);
    }

    static void dfs(int idx, int color, int cnt) {
        if (idx >= N * N) {
            answer[color] = Math.max(answer[color], cnt);
            return;
        }

        int x = idx / N;
        int y = idx % N;

        if ((x + y) % 2 == color && board[x][y] == 1
                && !left[x + y] && !right[x - y + N]) {
            left[x + y] = right[x - y + N] = true;
            dfs(idx + 1, color, cnt + 1);
            left[x + y] = right[x - y + N] = false;
        }

        dfs(idx + 1, color, cnt);
    }
}
