import java.io.*;
import java.util.*;

/**
 * boj 2933 미네랄
 */


public class Main {

    private static int R,C,N;
    private static char[][] arr;
    private static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {
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

        for (int i = 0; i < N; i++) {
            int r =  R-Integer.parseInt(st.nextToken());

            if (i % 2 == 0) {
                for (int c = 0; c < C; c++) {
                    if (arr[r][c] == 'x') {
                        arr[r][c] = '.';
                        break;
                    }
                }
            } else {
                for (int c = C - 1; c >= 0; c--) {
                    if (arr[r][c] == 'x') {
                        arr[r][c] = '.';
                        break;
                    }
                }
            }

            down();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void down() {
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < C; i++) {
            if (arr[R-1][i] == 'x') {
                visited[R-1][i] = true;
                queue.add(new int[]{R-1, i});
            }
        }

        while (!queue.isEmpty()) {
            int[] c = queue.poll();

            for (int i = 0; i < directions.length; i++) {
                int nr = c[0] + directions[i][0];
                int nc = c[1] + directions[i][1];

                if (nr < 0 || nc < 0 || nr == R || nc == C) continue;
                if (arr[nr][nc] != 'x') continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
            }
        }

        List<int[]> maxRNodes = new ArrayList<>();

        for (int i = 0; i < C; i++) {
            for (int j = R-1; j >= 0; j--) {
                if (arr[j][i] == 'x' && !visited[j][i]) {
                    maxRNodes.add(new int[]{j, i});
                    break;
                }
            }
        }

        if (maxRNodes.isEmpty()) return;

        int minMove = R;

        for (int[] maxRNode : maxRNodes) {
            int r = maxRNode[0];
            int c = maxRNode[1];

            int nr = r+1;
            for (; nr < R; nr++) {
                if (arr[nr][c] == 'x') {
                    break;
                }
            }

            minMove = Math.min(minMove, nr-r-1);
        }

        for (int r = R-1; r >= 0; r--) {
            for (int c = 0; c < C; c++) {
                if (arr[r][c] == 'x' && !visited[r][c]) {
                    arr[r+minMove][c] = 'x';
                    arr[r][c] = '.';
                }
            }
        }
    }
}