import javax.swing.text.Segment;
import java.io.*;
import java.util.*;
import java.util.stream.*;


/**
 * boj 1194 달이 차오른다 가자.
 * 다익스트라
 */


public class Main {

    private static int N, M;
    private static char[][] maze;
    private static int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x; this.y = y;
        }
    }

    static class Current {
        Point p;
        int keys;

        public Current(Point p, int keys) {
            this.p = p;
            this.keys = keys;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][M];
        Queue<Current> queue = new LinkedList<>();
        int[][][] distance = new int[N][M][1 << 6];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }

        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == '0') {
                    queue.add(new Current(new Point(i, j), 0));
                    distance[i][j][0] = 0;
                    maze[i][j] = '.';
                }
            }
        }

        int answer = -1;

        while(!queue.isEmpty()) {
            Current current = queue.poll();
            for (int i = 0; i < directions.length; i++) {
                int nx = current.p.x + directions[i][0];
                int ny = current.p.y + directions[i][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (maze[nx][ny] == '#') continue;

                if (maze[nx][ny] == '.' ) {
                    if (distance[nx][ny][current.keys] > distance[current.p.x][current.p.y][current.keys] + 1) {
                        distance[nx][ny][current.keys] = distance[current.p.x][current.p.y][current.keys] + 1;
                        queue.add(new Current(new Point(nx, ny), current.keys));
                    }

                } else if ('a' <= maze[nx][ny] && maze[nx][ny] <= 'f') {
                    int nKey = (int) Math.pow(2, (maze[nx][ny] - 'a')) | current.keys;
                    if (distance[nx][ny][nKey] > distance[current.p.x][current.p.y][current.keys] + 1) {
                        distance[nx][ny][nKey] = distance[current.p.x][current.p.y][current.keys] + 1;
                        queue.add(new Current(new Point(nx, ny), nKey));
                    }
                } else if ('A' <= maze[nx][ny] && maze[nx][ny] <= 'F') {
                    if ((current.keys & (int) Math.pow(2, (maze[nx][ny] - 'A'))) == (int) Math.pow(2, (maze[nx][ny] - 'A'))) {
                        if (distance[nx][ny][current.keys] > distance[current.p.x][current.p.y][current.keys] + 1) {
                            distance[nx][ny][current.keys] = distance[current.p.x][current.p.y][current.keys] + 1;
                            queue.add(new Current(new Point(nx, ny), current.keys));
                        }
                    }
                } else if (maze[nx][ny] == '1') {
                    answer = distance[current.p.x][current.p.y][current.keys] + 1;
                    break;
                }
            }

            if (answer != -1) {
                break;
            }
        }

        System.out.println(answer);
    }
}