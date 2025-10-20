import java.io.*;
import java.util.*;

/**
 * boj 2146 다리 만들기
 */

public class Main {

    private static int N;
    private static int islandId = 2;
    private static int[][] arr;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++){
                if (arr[i][j] != 0 && !visited[i][j]){
                    mark(i, j, islandId++);
                }
            }
        }

        // 2 ~ islandId
        boolean[] used = new boolean[islandId+1];

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0 && !used[arr[i][j]]) {
                    used[arr[i][j]] = true;
                    int distance = getMinDistance(i, j, arr[i][j]);
                    answer = Math.min(distance, answer);
                }
            }
        }

        System.out.println(answer);
    }

    public static void mark(int x, int y, int markId) {
        arr[x][y] = markId;
        visited[x][y] = true;

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx == N || ny < 0 || ny == N) continue;
            if (arr[nx][ny] == 1) {
                mark(nx, ny, markId);
            }
        }
    }

    public static int getMinDistance(int x, int y, int mark) {

        Queue<Current> queue = new LinkedList<>();
        int[][] distance = new int[N][N];

        for(int i = 0; i < N; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        distance[x][y] = 0;
        queue.add(new Current(x, y));

        int answer = Integer.MAX_VALUE;

        while(!queue.isEmpty()) {
            Current c = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];

                if (nx < 0 || nx == N || ny < 0 || ny == N) continue;
                int cost = arr[nx][ny] == 0 ? 1 : 0;
                if (arr[nx][ny] != mark && arr[nx][ny] != 0) {
                    answer = Math.min(answer, distance[c.x][c.y]);
                    continue;
                }

                if (distance[nx][ny] > distance[c.x][c.y] + cost) {
                    distance[nx][ny] = distance[c.x][c.y] + cost;
                    queue.add(new Current(nx, ny));
                }
            }

        }

        return answer;
    }

    static class Current {
        int x, y;

        public Current(int x, int y) {
            this.x = x; this.y = y;
        }
    }
}

