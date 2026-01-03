import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 14502 연구소
 * 조합
 */


public class Main {

    static int N, M;
    static int[][] board;
    static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<List<int[]>> combine = combine(0, new ArrayList<>());

        int answer = 0;
        for (List<int[]> comb : combine) {
            for (int[] p : comb) {
                board[p[0]][p[1]] = 1;
            }

            int res = bfs();
            answer = Math.max(res, answer);

            for (int[] p : comb) {
                board[p[0]][p[1]] = 0;
            }
        }

        System.out.println(answer);
    }

    static int bfs() {

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 2) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
                else if (board[i][j] == 1) {
                    visited[i][j] = true;
                } else cnt++;
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < directions.length; i++) {
                int nx = cur[0] + directions[i][0];
                int ny = cur[1] + directions[i][1];

                if (nx < 0 || ny < 0 || nx == N || ny == M) continue;
                if (visited[nx][ny]) continue;
                queue.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                cnt--;
            }
        }
        return cnt;
    }

    static List<List<int[]>> combine(int point, List<int[]> current) {
        List<List<int[]>> res = new ArrayList<>();
        if (current.size() == 3) {
            res.add(new ArrayList<>(current));
            return res;
        }

        for (; point < N*M; point++) {
            int x = point/M;
            int y = point%M;
            if (board[x][y] != 0) continue;

            current.add(new int[]{x, y});
            res.addAll(combine(point+1, current));
            current.remove(current.size()-1);
        }

        return res;
    }
}
