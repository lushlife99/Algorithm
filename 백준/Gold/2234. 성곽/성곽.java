import java.io.*;
import java.util.*;


/**
 * boj 2234 성곽
 * bfs, 비트마스크
 */


public class Main {

    private static int N, M;
    private static int[][] arr;
    private static int[][] assigned;
    private static int[] sizes;

    private static int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        assigned = new int[N][M];
        sizes = new int[N*M+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[N][M];
        int id = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, id++, visited);
                }
            }
        }

        // debug
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.printf(assigned[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(id);
        int answer2 = 0;
        for (int i = 0; i < id; i++) {
            answer2 = Math.max(answer2, sizes[i]);
        }

        System.out.println(answer2);
        int answer3 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < directions.length; k++) {
                    int nx = i + directions[k][0];
                    int ny = j + directions[k][1];

                    if (nx < 0 || ny < 0 || nx == N || ny == M) continue;
                    if (assigned[i][j] != assigned[nx][ny]) {
                        answer3 = Math.max(answer3, sizes[assigned[i][j]] + sizes[assigned[nx][ny]]);
                    }
                }
            }
        }

        System.out.println(answer3);

    }

    private static void bfs(int x, int y, int id, boolean[][] visited) {
        visited[x][y] = true;
        assigned[x][y] = id;
        sizes[id]++;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] c = queue.poll();
            if ((arr[c[0]][c[1]] & 1) != 1 && c[1] != 0) {
                int nx = c[0];
                int ny = c[1] - 1;

                if (!visited[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    sizes[id]++;
                    assigned[nx][ny] = id;
                }
            }

            if ((arr[c[0]][c[1]] & 2) != 2 && c[0] != 0) {
                int nx = c[0] - 1;
                int ny = c[1];

                if (!visited[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    sizes[id]++;
                    assigned[nx][ny] = id;
                }
            }

            if ((arr[c[0]][c[1]] & 4) != 4 && c[1] != M-1) {
                int nx = c[0];
                int ny = c[1] + 1;

                if (!visited[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    sizes[id]++;
                    assigned[nx][ny] = id;
                }
            }

            if ((arr[c[0]][c[1]] & 8) != 8 && c[0] != N-1) {
                int nx = c[0] + 1;
                int ny = c[1];

                if (!visited[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    sizes[id]++;
                    assigned[nx][ny] = id;
                }
            }
        }
    }

}