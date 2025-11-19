import java.io.*;
import java.util.*;


/**
 * boj 14923 미로 탈출
 * 다익스트라
 */


public class Main {

    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x = x; this.y = y;
        }
    }

    static class Current {
        Point p;
        int b;

        public Current(Point p, int b) {
            this.p = p; this.b = b;
        }
    }

    private static int N, M;
    private static int[][] arr;
    private static Point start, end;

    private static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        st = new StringTokenizer(br.readLine());
        start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        st = new StringTokenizer(br.readLine());
        end = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] distance = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(distance[i][j], 1_000_0001);
            }
        }

        distance[start.x][start.y][0] = 0;
        Queue<Current> queue = new LinkedList<>();
        queue.offer(new Current(start, 0));
        int answer = -1;

        while(!queue.isEmpty()) {
            Current c = queue.poll();
            if (c.p.x == end.x && c.p.y == end.y) {
                answer = distance[c.p.x][c.p.y][c.b];
                break;
            }
            for (int i = 0; i < directions.length; i++) {
                int nx = c.p.x + directions[i][0];
                int ny = c.p.y + directions[i][1];

                if (nx < 0 || ny < 0 || nx == N || ny == M) continue;
                if (arr[nx][ny] == 1) {
                    if (c.b == 1) continue;

                    if (distance[nx][ny][1] > distance[c.p.x][c.p.y][0] + 1) {
                        distance[nx][ny][1] = distance[c.p.x][c.p.y][0] + 1;
                        queue.add(new Current(new Point(nx, ny), 1));
                    }
                } else {
                    if (distance[nx][ny][c.b] > distance[c.p.x][c.p.y][c.b] + 1) {
                        distance[nx][ny][c.b] = distance[c.p.x][c.p.y][c.b] + 1;
                        queue.add(new Current(new Point(nx, ny), c.b));
                    }
                }
            }
        }

        System.out.println(answer);

    }


}
