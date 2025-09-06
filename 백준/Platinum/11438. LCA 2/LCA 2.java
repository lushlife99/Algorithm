import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * 함수형 프로그래밍으로 푸는 연습
 * boj 1761 정점들의 거리
 * 트리
 *
 * 메모리 초과 -> LCA 문제였음.
 * LCA 학습하고 내일부터 LCA 풀어보기
 */

public class Main {

    static int N, M, H;
    static int[] depth;
    static int[][] parent;
    static List<Integer>[] graph;

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

            graph[u].add(v);
            graph[v].add(u);
        }

        depth = new int[N+1];
        parent = new int[H+1][N+1];

        dfs(1,0);

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sb.append(lca(u, v) + "\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int u, int p) {
        parent[0][u] = p;
        depth[u] = depth[p] + 1;

        for (int v : graph[u]) {
            if (v != p) {
                dfs(v, u);
            }
        }
    }

    private static int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
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

}
