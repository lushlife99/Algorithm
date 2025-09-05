import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 함수형 프로그래밍으로 푸는 연습
 * boj 1761 정점들의 거리
 * 트리
 *
 * 메모리 초과 -> LCA 문제였음.
 * LCA 학습하고 내일부터 LCA 풀어보기
 */

public class Main {
    static int N, LOG;
    static List<Edge>[] graph;
    static int[] depth, dist;
    static int[][] parent;

    static class Edge {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to; this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        LOG = (int) Math.ceil(Math.log(N) / Math.log(2));

        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, c));
            graph[v].add(new Edge(u, c));
        }

        depth = new int[N+1];
        dist = new int[N+1];
        parent = new int[LOG+1][N+1];

        dfs(1, 0, 0);

        for (int k = 1; k <= LOG; k++) {
            for (int v = 1; v <= N; v++) {
                parent[k][v] = parent[k-1][parent[k-1][v]];
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(getDistance(u, v)).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int v, int p, int d) {
        parent[0][v] = p;
        depth[v] = depth[p] + 1;
        dist[v] = d;
        for (Edge e : graph[v]) {
            if (e.to != p) {
                dfs(e.to, v, d + e.cost);
            }
        }
    }

    static int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int tmp = a; a = b; b = tmp;
        }
        for (int k = LOG; k >= 0; k--) {
            if (depth[a] - depth[b] >= (1 << k)) {
                a = parent[k][a];
            }
        }
        if (a == b) return a;
        for (int k = LOG; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }
        return parent[0][a];
    }

    static int getDistance(int u, int v) {
        int l = lca(u, v);
        return dist[u] + dist[v] - 2 * dist[l];
    }

}
