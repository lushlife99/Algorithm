import java.io.*;
import java.util.*;


/**
 * boj 18500 미네랄 2
 */


public class Main {

    private static int R, C;
    private static char[][] arr;

    private static int N;
    private static int[] arr2;

    private static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr2 = new int[N];

        for (int i = 0; i < N; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int row = R - arr2[i];

            if (i % 2 == 0) {
                for (int j = 0; j < C; j++) {
                    if (arr[row][j] == 'x') {
                        arr[row][j] = '.';
                        break;
                    }
                }
            } else {
                for (int j = C - 1; j >= 0; j--) {
                    if (arr[row][j] == 'x') {
                        arr[row][j] = '.';
                        break;
                    }
                }
            }

            bfs();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.print(sb);
    }

    private static void bfs() {
        boolean[][] visited = new boolean[R][C];

        Queue<int[]> queue = new LinkedList<>();
        for (int j = 0; j < C; j++) {
            if (arr[R - 1][j] == 'x') {
                visited[R - 1][j] = true;
                queue.add(new int[]{R - 1, j});
            }
        }

        while (!queue.isEmpty()) {
            int[] c = queue.poll();
            for (int i = 0; i < directions.length; i++) {
                int nx = c[0] + directions[i][0];
                int ny = c[1] + directions[i][1];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (visited[nx][ny]) continue;
                if (arr[nx][ny] != 'x') continue;
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }

        List<int[]> nonVisited = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 'x' && !visited[i][j]) {
                    nonVisited.add(new int[]{i, j});
                }
            }
        }

        if (nonVisited.isEmpty()) return;

        for (int[] c : nonVisited) {
            arr[c[0]][c[1]] = '.';
        }

        int min = Integer.MAX_VALUE;
        for (int[] c : nonVisited) {
            int dist = 0;

            for (int i = c[0] + 1; i < R; i++) {
                if (arr[i][c[1]] == 'x') {
                    dist = i - c[0] - 1;
                    break;
                }

                if (i == R-1) {
                    dist = i - c[0];
                }
            }
            min = Math.min(dist, min);
        }

        for (int[] c : nonVisited) {
            arr[c[0] + min][c[1]] = 'x';
        }
    }


}

