import java.io.*;
import java.util.*;

/**
 * boj 2636 치즈
 *
 */


public class Main {
    static int N, M;
    static int[][] board;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        int totalCheese = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    totalCheese++;
                }
            }
        }

        int time = 0;
        int lastCount = totalCheese;

        while (totalCheese > 0) {
            lastCount = totalCheese;
            totalCheese -= meltCheese();
            time++;
        }

        System.out.println(time);
        System.out.println(lastCount);
    }

    static int meltCheese() {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        visited[0][0] = true;

        int melted = 0;
        List<int[]> meltList = new ArrayList<>();

        while (!queue.isEmpty()) {
            int[] c = queue.poll();

            for (int i = 0; i < dr.length; i++) {
                int nr = c[0] + dr[i];
                int nc = c[1] + dc[i];

                if (nr < 0 || nc < 0 || nr == N || nc == M) continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                if (board[nr][nc] == 0) {
                    queue.add(new int[]{nr, nc});
                } else if (board[nr][nc] == 1) {
                    meltList.add(new int[]{nr, nc});
                }
            }
        }

        melted = meltList.size();
        for (int[] melt : meltList) {
            board[melt[0]][melt[1]] = 0;
        }

        return melted;
    }
}
