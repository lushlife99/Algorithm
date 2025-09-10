import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 3197 백조의 호수
 * 분리 집합, dfs
 */

public class Main {

    static int R, C;
    static char[][] arr;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        parent = IntStream.range(0, R * C).toArray();
        arr = new char[R][C];

        int[] swan1 = null, swan2 = null;

        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 'L') {
                    if (swan1 == null) {
                        swan1 = new int[]{i, j};
                    } else {
                        swan2 = new int[]{i, j};
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == '.' || arr[i][j] == 'L') {
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                        if (arr[nx][ny] == '.' || arr[nx][ny] == 'L') {
                            union(idx(i, j), idx(nx, ny));
                        }
                    }
                }
            }
        }

        Queue<int[]> waterQ = new ArrayDeque<>();
        boolean[][] inWaterQ = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 'X') continue;

                boolean isBoundary = false;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if (arr[nx][ny] == 'X') {
                        isBoundary = true;
                        break;
                    }
                }
                if (isBoundary) {
                    waterQ.add(new int[]{i, j});
                    inWaterQ[i][j] = true;
                }

            }
        }

        int days = 0;
        Queue<int[]> nextQ = new ArrayDeque<>();

        while (!isConnected(idx(swan1[0], swan1[1]), idx(swan2[0], swan2[1]))) {
            while (!waterQ.isEmpty()) {
                int[] cur = waterQ.poll();
                int x = cur[0], y = cur[1];
                inWaterQ[x][y] = false;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d], ny = y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if (arr[nx][ny] == 'X') {
                        arr[nx][ny] = '.';
                        int meltedIdx = idx(nx, ny);
                        for (int dd = 0; dd < 4; dd++) {
                            int ax = nx + dx[dd], ay = ny + dy[dd];
                            if (ax < 0 || ay < 0 || ax >= R || ay >= C) continue;
                            if (arr[ax][ay] == '.' || arr[ax][ay] == 'L') {
                                union(meltedIdx, idx(ax, ay));
                            }
                        }

                        if (!inWaterQ[nx][ny]) {
                            nextQ.add(new int[]{nx, ny});
                            inWaterQ[nx][ny] = true;
                        }
                    }
                }
            }

            waterQ = nextQ;
            nextQ = new ArrayDeque<>();
            days++;
        }

        System.out.println(days);
    }

    private static boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }

    private static int idx(int x, int y) {
        return x * C + y;
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }
}
