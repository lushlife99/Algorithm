import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 14688 문명
 * 분리 집합
 */

public class Main {

    static int N, K;
    static int[][] world;
    static int[] parent;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return p.x == this.x && p.y == this.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return this.x + " " + this.y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        world = new int[N+1][N+1];

        parent = IntStream.rangeClosed(0, N*(N+1)+1).toArray();
        Queue<Point> queue = new LinkedList<>();
        Point[] first = new Point[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point p = new Point(x, y);
            queue.add(p);
            first[i] = p;

            world[x][y] = 1;
            for (int j = 0; j < dx.length; j++) {
                Point next = new Point(p.x + dx[j], p.y + dy[j]);

                if (1 > next.x || 1 > next.y || N < next.x || N < next.y) continue;
                if (world[next.x][next.y] == 1) {
                    union(getParentIdx(p), getParentIdx(next));
                }
            }
        }

        int day = 0;

        while (!isConnected(first)) {
            Set<Point> updated = new HashSet<>();
            while (!queue.isEmpty()) {
                Point current = queue.poll();

                for (int i = 0; i < dx.length; i++) {
                    Point next = new Point(current.x + dx[i], current.y + dy[i]);

                    if (1 > next.x || 1 > next.y || N < next.x || N < next.y) continue;
                    if (world[next.x][next.y] == 0 &&
                            find(getParentIdx(current)) != find(getParentIdx(next))) {
                        updated.add(next);
                    }
                }
            }

            for (Point p : updated) {
                world[p.x][p.y] = 1;
                for (int i = 0; i < dx.length; i++) {
                    Point next = new Point(p.x + dx[i], p.y + dy[i]);
                    if (1 > next.x || 1 > next.y || N < next.x || N < next.y) continue;

                    if (world[next.x][next.y] == 1 &&
                            find(getParentIdx(p)) != find(getParentIdx(next))) {

                        union(getParentIdx(p), getParentIdx(next));
                    }
                }
            }

            queue.addAll(updated);
            day++;
        }

        System.out.println(day);

    }

    private static boolean isConnected(Point[] p) {
        int root = find(getParentIdx(p[0]));

        return IntStream.range(0 ,p.length)
                .allMatch(i -> find(getParentIdx(p[i])) == root);
    }

    private static int getParentIdx(Point p) {
        return p.x * N + p.y;
    }

    private static int find(int a) {
        if (a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

}