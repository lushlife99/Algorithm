import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 2573 빙산
 */

public class Main {

    static int N, M;
    static int[][] arr;
    static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        int answer = 0;
        while (true) {
            melt();
            cnt++;

            int val = validate();

            if (val != -1) {
                if (val == 0) {
                    answer = 0;
                    break;
                } else if (val == 1) {
                    answer = cnt;
                    break;
                }
            }
        }

        System.out.println(answer);
    }

    static void melt() {
        int[][] newArr = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    newArr[i][j] = 0;
                    continue;
                }

                int cnt = 0;
                for (int k = 0; k < directions.length; k++) {
                    int nx = i + directions[k][0];
                    int ny = j + directions[k][1];

                    if (nx < 0 || ny < 0 || nx == N || ny == M) continue;
                    if (arr[nx][ny] == 0) cnt++;
                }

                newArr[i][j] = Math.max(0, arr[i][j] - cnt);
            }
        }

        arr = newArr;
    }

    static int validate() {

        int[] target = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] != 0) {
                    target = new int[]{i, j};
                    break;
                }
            }

            if (target != null) break;
        }

        if (target == null) return 0;
        boolean[][] visited = bfs(target[0], target[1]);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] != 0 && !visited[i][j]) return 1;
            }
        }

        return -1;
    }

    static boolean[][] bfs(int x, int y) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < directions.length; i++) {
                int nx = current[0] + directions[i][0];
                int ny = current[1] + directions[i][1];

                if (nx < 0 || ny < 0 || nx == N || ny == M) continue;
                if (visited[nx][ny]) continue;
                if (arr[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }

        return visited;
    }
}