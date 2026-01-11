import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 16234 인구 이동
 */

public class Main {

    static int N, L, R;
    static int[][] arr;
    static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            visited = new boolean[N][N];
            List<List<int[]>> groups = new ArrayList<>();
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (!visited[x][y]) {
                        groups.add(bfs(x, y));
                    }
                }
            }

            boolean sig = false;
            for (List<int[]> group : groups) {
                if (group.size() == 1) continue;
                sig = true;

                int sum = 0;
                for (int[] p : group) {
                    sum += arr[p[0]][p[1]];
                }

                int target = sum / group.size();
                for (int[] p : group) {
                    arr[p[0]][p[1]] = target;
                }
            }

            if (!sig) break;
            answer++;
        }

        System.out.println(answer);
    }

    static List<int[]> bfs(int x, int y) {
        visited[x][y] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        List<int[]> res = new ArrayList<>();
        res.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < directions.length; i++) {
                int nx = current[0] + directions[i][0];
                int ny = current[1] + directions[i][1];

                if (nx < 0 || ny < 0 || nx == N || ny == N) continue;
                if (visited[nx][ny]) continue;

                int gap = Math.abs(arr[current[0]][current[1]] - arr[nx][ny]);
                if (L <= gap && gap <= R) {
                    visited[nx][ny] = true;
                    res.add(new int[]{nx, ny});
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return res;
    }
}