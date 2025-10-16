import java.io.*;
import java.util.*;

/**
 * boj 13460 구슬 탈출 2
 * bfs 다익스트라
 */

public class Main {

    private static int N, M;
    private static char[][] arr;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x; this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Current {
        Point red, blue;
        int cnt;

        public Current(Point red, Point blue, int cnt) {
            this.red = red;
            this.blue = blue;
            this.cnt = cnt;
        }

        public Current(int rx, int ry, int bx, int by) {
            this.red = new Point(rx, ry);
            this.blue = new Point(bx, by);
        }

        @Override
        public String toString() {
            return "red : " + this.red.x + " " + this.red.y + ", blue : " + this.blue.x + " " + this.blue.y + ", cnt : " + cnt;
        }

        @Override
        public boolean equals(Object o) {
            Current oC = (Current) o;

            return this.red.x == oC.red.x &&
                    this.red.y == oC.red.y &&
                    this.blue.x == oC.blue.x &&
                    this.blue.y == oC.blue.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(red, blue);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];

        Point red = null, blue = null, hole = null;

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 'R') {
                    red = new Point(i, j);
                    arr[i][j] = '.';
                }

                if (arr[i][j] == 'B') {
                    blue = new Point(i, j);
                    arr[i][j] = '.';
                }

                if (arr[i][j] == 'O') {
                    hole = new Point(i, j);
                }
            }
        }

        Current c = new Current(red, blue, 0);
        Queue<Current> queue = new LinkedList<>();
        Set<Current> visited = new HashSet<>();

        queue.add(c);
        visited.add(c);

        int answer = -1;

        while (!queue.isEmpty()) {
            c = queue.poll();
            if (c.cnt == 10) break;

            for (int i = 0; i < dx.length; i++) {

                boolean redPriority = c.red.x * dx[i] + c.red.y * dy[i] > c.blue.x * dx[i] + c.blue.y * dy[i];
                Point nB, nR;
                if (redPriority) {
                    nR = moveFirst(i, c.red);
                    if (nR.x == hole.x && nR.y == hole.y) {
                        nB = moveFirst(i, c.blue);
                    } else {
                        nB = moveSecond(i, nR, c.blue);
                    }

                } else {
                    nB = moveFirst(i, c.blue);
                    if (nB.x == hole.x && nB.y == hole.y) {
                        nR = moveFirst(i, c.red);
                    } else {
                        nR = moveSecond(i, nB, c.red);
                    }
                }

                if (nB.x == hole.x && nB.y == hole.y) continue;
                if (nR.x == hole.x && nR.y == hole.y) {
                    answer = c.cnt+1;
                    break;
                }

                Current n = new Current(nR, nB, c.cnt+1);

                if (visited.add(n)) {
                    queue.add(n);
                }
            }

            if (answer != -1) break;
        }

        System.out.println(answer);
    }

    private static Point moveFirst(int dIdx, Point p) {
        int nx = p.x;
        int ny = p.y;

        while (arr[nx + dx[dIdx]][ny + dy[dIdx]] != '#') {
            nx += dx[dIdx];
            ny += dy[dIdx];
            if (arr[nx][ny] == 'O') break;
        }

        return new Point(nx, ny);
    }

    private static Point moveSecond(int dIdx, Point first, Point second) {
        int nx = second.x;
        int ny = second.y;

        while (arr[nx + dx[dIdx]][ny + dy[dIdx]] != '#'
                && !(nx + dx[dIdx] == first.x && ny + dy[dIdx] == first.y)) {
            nx += dx[dIdx];
            ny += dy[dIdx];

            if (arr[nx][ny] == 'O') break;
        }

        return new Point(nx, ny);
    }
}
