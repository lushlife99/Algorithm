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
    static int[] depth, dist;
    static int[][] parent;
    static List<Integer>[] graph;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        H = (int) Math.ceil(Math.log(N) / Math.log(2));
        graph = IntStream.rangeClosed(0, N)
                .mapToObj(p -> new ArrayList<>())
                .toArray(List[]::new);

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        depth = new int[N+1];
        dist = new int[N+1];
        parent = new int[H+1][N+1];

        dfs(1,0,0);

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }

        M = Integer.parseInt(br.readLine());

        int from = 1;
        int res = 0;
        for (int i = 0; i < M; i++) {
            int to = Integer.parseInt(br.readLine());
            res += getDistance(from, to);
            from = to;
        }

        System.out.println(res);
    }

    private static void dfs(int u, int p, int d) {
        parent[0][u] = p;
        depth[u] = depth[p] + 1;
        dist[u] = d;
        for (int v : graph[u]) {
            if (v != p) {
                dfs(v, u, d+1);
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

    private static int getDistance(int u, int v) {
        int lca = lca(u, v);

        return dist[u] + dist[v] - 2 * dist[lca];
    }

}
