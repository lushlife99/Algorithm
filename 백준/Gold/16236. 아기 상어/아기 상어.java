import java.io.*;
import java.util.*;

/**
 * boj 16236 아기 상어
 */


public class Main {
    static int N;
    static int[][] map;
    static int sharkX, sharkY, sharkSize = 2, eatCnt = 0;
    static int time = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Fish implements Comparable<Fish> {
        int x, y, dist;

        Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.dist != o.dist) return this.dist - o.dist;
            if (this.x != o.x) return this.x - o.x;
            return this.y - o.y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            Fish target = findFish();
            if (target == null) break;

            time += target.dist;
            sharkX = target.x;
            sharkY = target.y;
            map[sharkX][sharkY] = 0;

            eatCnt++;
            if (eatCnt == sharkSize) {
                sharkSize++;
                eatCnt = 0;
            }
        }

        System.out.println(time);
    }

    static Fish findFish() {
        PriorityQueue<Fish> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        pq.add(new Fish(sharkX, sharkY, 0));
        visited[sharkX][sharkY] = true;

        while (!pq.isEmpty()) {
            Fish cur = pq.poll();

            if (map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < sharkSize) {
                return cur;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] || map[nx][ny] > sharkSize) continue;

                visited[nx][ny] = true;
                pq.add(new Fish(nx, ny, cur.dist + 1));
            }
        }

        return null;
    }
}