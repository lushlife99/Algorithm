import java.io.*;
import java.util.*;


/**
 * boj 19238 스타트 택시
 * bfs
 */

public class Main {

    static class Point {
        int x,y;

        public Point(int x, int y) {
            this.x=x; this.y=y;
        }

        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }
    }

    private static int N, M, F;
    private static int[][] arr;
    private static Point current;
    private static Map<Point, Point> clientMap = new HashMap<>();

    private static int INF = 10000;
    private static int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        current = new Point(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int p1x = Integer.parseInt(st.nextToken()) - 1;
            int p1y = Integer.parseInt(st.nextToken()) - 1;
            int p2x = Integer.parseInt(st.nextToken()) - 1;
            int p2y = Integer.parseInt(st.nextToken()) - 1;

            clientMap.put(new Point(p1x, p1y), new Point(p2x, p2y));
        }

        int[][][][] distance = new int[N][N][N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int l = 0; l < N; l++) {
                    Arrays.fill(distance[i][j][l], INF);
                }
            }
        }

        // 1. bfs
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1) continue;

                bfs(distance, i, j);
            }
        }

        int answer = -1;

        while(M > 0) {
            Point nClientStart = getNextClients(distance);
            if (distance[current.x][current.y][nClientStart.x][nClientStart.y] == INF) break;
            if (F - distance[current.x][current.y][nClientStart.x][nClientStart.y] <= 0) break;

            F -= distance[current.x][current.y][nClientStart.x][nClientStart.y];
            current = nClientStart;

            Point nClientEnd = clientMap.get(nClientStart);

            if (distance[current.x][current.y][nClientEnd.x][nClientEnd.y] == INF) break;
            if (F - distance[current.x][current.y][nClientEnd.x][nClientEnd.y] < 0) break;

            F += distance[current.x][current.y][nClientEnd.x][nClientEnd.y];
            current = nClientEnd;

            M--;
            clientMap.remove(nClientStart);

            if (M == 0) {
                answer = F;
            }
        }

        System.out.println(answer);
    }

    private static void bfs(int[][][][] distance, int x, int y) {
        distance[x][y][x][y] = 0;

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            for (int i = 0; i < directions.length; i++) {
                int nx = p.x + directions[i][0];
                int ny = p.y + directions[i][1];

                if (nx < 0 || ny < 0 || nx == N || ny == N) continue;
                if (arr[nx][ny] == 1) continue;

                if (distance[x][y][nx][ny] > distance[x][y][p.x][p.y] + 1) {
                    distance[x][y][nx][ny] = distance[x][y][p.x][p.y] + 1;
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }

    private static Point getNextClients(int[][][][] distance) {
        List<Point> starts = new ArrayList<>(clientMap.keySet());
        Collections.sort(starts, (p1, p2) -> {
            int d1 = distance[current.x][current.y][p1.x][p1.y];
            int d2 = distance[current.x][current.y][p2.x][p2.y];

            if (d1 != d2) {
                return d1-d2;
            }

            if (p1.x != p2.x) {
                return p1.x-p2.x;
            }

            return p1.y-p2.y;
        });

        return starts.get(0);
    }

}
