import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 함수형 프로그래밍으로 푸는 연습
 * boj 13511 트리와 쿼리 2
 * LCA
 */

public class Main {

    static int N, M, H;
    static int[] depth;
    static long[] dist;
    static int[][] parent;
    static List<Edge>[] graph;

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        H = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        graph = IntStream.rangeClosed(0, N)
                .mapToObj(p -> new ArrayList<>())
                .toArray(List[]::new);

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[u].add(new Edge(u, v, c));
            graph[v].add(new Edge(v, u, c));
        }

        depth = new int[N+1];
        dist = new long[N+1];
        parent = new int[H+1][N+1];

        dfs(1,0,0);

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }

        M = Integer.parseInt(br.readLine());

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String query = br.readLine();
            StringTokenizer st = new StringTokenizer(query);

            if (st.nextToken().equals("1")) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                res.append(getDistance(u, v) + "\n");
            } else {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                res.append(getKthNode(u, v, k) + "\n");
            }
        }

        System.out.println(res.toString());
    }

    private static void dfs(int u, int p, long d) {
        parent[0][u] = p;
        dist[u] = d;
        for (Edge e : graph[u]) {
            if (e.to != p) {
                depth[e.to] = depth[u] + 1;
                dfs(e.to, u, d + e.cost);
            }
        }
    }

    private static int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int i = H; i >= 0; i--) {
            if (depth[u] - depth[v] >= (1 << i)) {
                u = parent[i][u];
            }
        }

        if (u == v) return u;

        for (int i = H; i >= 0; i--) {
            if (parent[i][u] != parent[i][v]) {
                u = parent[i][u];
                v = parent[i][v];
            }
        }

        return parent[0][u];
    }

    private static long getDistance(int u, int v) {
        int lca = lca(u, v);

        return dist[u] + dist[v] - 2 * dist[lca];
    }

    private static int getKthNode(int s, int e, int k) {
        int lca = lca(s, e);

        int d1 = depth[s] - depth[lca];
        if (k <= d1 + 1) {
            return moveUp(s, k-1);
        } else {
            int d2 = depth[e] - depth[lca];
            int target = d2 - (k - 1 - d1);
            return moveUp(e, target);
        }
    }

    private static int moveUp(int u, int k) {
        for (int i = H; i >= 0; i--) {
            if ((k & (1 << i)) != 0) {
                u = parent[i][u];
            }
        }

        return u;
    }

}
