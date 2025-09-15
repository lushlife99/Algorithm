import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 25402 트리와 쿼리
 * 분리 집합
 */


public class Main {

    static int N, M;
    static List<Integer>[] edge;
    static int[] treeParent;
    static boolean[] visited;

    static int[] parent;
    static long[] cnt;
    static int[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        edge = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) edge[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edge[u].add(v);
            edge[v].add(u);
        }

        treeParent = new int[N + 1];
        visited = new boolean[N + 1];
        dfs(1, -1);

        parent = new int[N + 1];
        cnt = new long[N + 1];
        used = new int[N + 1];

        M = Integer.parseInt(br.readLine());
        for (int q = 1; q <= M; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            int[] nodes = new int[K];
            for (int i = 0; i < K; i++) {
                nodes[i] = Integer.parseInt(st.nextToken());
                used[nodes[i]] = q;
                parent[nodes[i]] = nodes[i];
                cnt[nodes[i]] = 1;
            }

            for (int i = 0; i < K; i++) {
                int node = nodes[i];
                int p = treeParent[node];
                if (p != -1 && used[p] == q) {
                    union(node, p);
                }
            }

            long result = 0;
            for (int i = 0; i < K; i++) {
                int node = nodes[i];
                if (find(node) == node) {
                    long c = cnt[node];
                    result += c * (c - 1) / 2;
                }
            }

            sb.append(result).append("\n");
        }

        System.out.print(sb.toString());
    }

    static void dfs(int u, int p) {
        visited[u] = true;
        treeParent[u] = p;
        for (int v : edge[u]) {
            if (!visited[v]) dfs(v, u);
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        parent[a] = b;
        cnt[b] += cnt[a];
    }
}
