import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] arr;
    static int n;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // 상, 좌, 우, 하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        Point current = null;
        int sharkSize = 2;
        int eatCount = 0;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(split[j]);
                if (arr[i][j] == 9) {
                    current = new Point(i, j);
                    arr[i][j] = 0;
                }
            }
        }

        while (true) {
            Fish next = bfs(current, sharkSize);
            if (next == null) break;

            answer += next.dist;
            eatCount++;
            current = new Point(next.x, next.y);
            arr[next.x][next.y] = 0;

            if (eatCount == sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
        }

        System.out.println(answer);
    }

    static Fish bfs(Point start, int sharkSize) {
        boolean[][] visited = new boolean[n][n];
        Queue<Fish> q = new LinkedList<>();
        List<Fish> eatable = new ArrayList<>();

        visited[start.x][start.y] = true;
        q.offer(new Fish(start.x, start.y, 0));

        while (!q.isEmpty()) {
            Fish cur = q.poll();

            for (int[] d : dir) {
                int nx = cur.x + d[0];
                int ny = cur.y + d[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny]) continue;
                if (arr[nx][ny] > sharkSize) continue;

                visited[nx][ny] = true;

                if (arr[nx][ny] != 0 && arr[nx][ny] < sharkSize) {
                    eatable.add(new Fish(nx, ny, cur.dist + 1));
                }

                q.offer(new Fish(nx, ny, cur.dist + 1));
            }
        }

        if (eatable.isEmpty()) return null;
        Collections.sort(eatable);
        return eatable.get(0);
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Fish implements Comparable<Fish> {
        int x, y, dist;
        Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish f) {
            if (this.dist != f.dist)
                return Integer.compare(this.dist, f.dist);
            if (this.x != f.x)
                return Integer.compare(this.x, f.x);
            return Integer.compare(this.y, f.y);
        }
    }
}
