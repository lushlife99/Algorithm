import java.io.*;
import java.util.*;

/**
 * boj 16946 벽 부수고 이동하기 4
 * bfs
 */

/**
 * 반례 생각하기
 */


public class Main {

    private static int N, M;
    private static char[][] arr;
    private static int[][] group;
    private static int groupId = 1;
    private static List<Integer> cnt;

    private static int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        group = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        cnt = new ArrayList<>();
        cnt.add(0);

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == '0' && !visited[i][j]) {
                    bfs(visited, i, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == '1') {
                    int res = 1;
                    Set<Integer> used = new HashSet<>();
                    for (int k = 0; k < direction.length; k++) {
                        int nx = i + direction[k][0];
                        int ny = j + direction[k][1];

                        if (nx < 0 || ny < 0 || nx == N || ny == M) continue;
                        if (arr[nx][ny] == '0') {
                            used.add(group[nx][ny]);
                        }
                    }

                    for (int g : used) {
                        res += cnt.get(g);
                    }

                    sb.append(res%10);
                } else {
                    sb.append('0');
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static void bfs(boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        group[x][y] = groupId;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        int count = 1;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < direction.length; i++) {
                int nx = current[0] + direction[i][0];
                int ny = current[1] + direction[i][1];

                if (nx < 0 || ny < 0 || nx == N || ny == M) continue;
                if (arr[nx][ny] == '0' && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    group[nx][ny] = groupId;
                    queue.add(new int[]{nx, ny});
                    count++;
                }
            }
        }

        cnt.add(count);
        groupId++;
    }

}

