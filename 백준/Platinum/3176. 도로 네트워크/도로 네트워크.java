import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 함수형 프로그래밍으로 푸는 연습
 * boj 3176 도로 네트워크
 * LCA
 */

public class Main {

    static int N, M, H;
    static int[] depth;
    static int[][] parent, minEdge, maxEdge;
    static List<Edge>[] graph;

    static class Edge {
        int from, to ,cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = IntStream.rangeClosed(0, N)
                .mapToObj(p -> new ArrayList<>())
                .toArray(List[]::new);

        H = (int) Math.ceil(Math.log(N) / Math.log(2));

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[v].add(new Edge(v, u, c));
            graph[u].add(new Edge(u, v, c));
        }

        depth = new int[N+1];
        parent = new int[H+1][N+1];
        minEdge = new int[H+1][N+1];
        maxEdge = new int[H+1][N+1];

        dfs(1,0, 0);

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
                minEdge[i][j] = Math.min(minEdge[i-1][j], minEdge[i-1][parent[i-1][j]]);
                maxEdge[i][j] = Math.max(maxEdge[i-1][j], maxEdge[i-1][parent[i-1][j]]);
            }
        }

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int[] res = lca(u, v);
            sb.append(res[0] + " " + res[1] + "\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int u, int p, int c) {
        parent[0][u] = p;
        depth[u] = depth[p] + 1;
        minEdge[0][u] = (p == 0 ? Integer.MAX_VALUE : c);
        maxEdge[0][u] = (p == 0 ? Integer.MIN_VALUE : c);

        for (Edge e : graph[u]) {
            if (e.to != p) {
                dfs(e.to, u, e.cost);
            }
        }
    }

    private static int[] lca(int u, int v) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }


        for (int i = H; i >= 0; i--) {
            if (depth[u] - depth[v] >= (1 << i)) {
                min = Math.min(min, minEdge[i][u]);
                max = Math.max(max, maxEdge[i][u]);
                u = parent[i][u];
            }
        }

        if (u == v) return new int[]{min, max};

        for (int i = H; i >= 0; i--) {
            if (parent[i][u] != parent[i][v]) {
                min = Math.min(min, Math.min(minEdge[i][u], minEdge[i][v]));
                max = Math.max(max, Math.max(maxEdge[i][u], maxEdge[i][v]));
                u = parent[i][u];
                v = parent[i][v];
            }
        }

        min = Math.min(min, Math.min(minEdge[0][u], minEdge[0][v]));
        max = Math.max(max, Math.max(maxEdge[0][u], maxEdge[0][v]));

        return new int[]{min, max};
    }


}
