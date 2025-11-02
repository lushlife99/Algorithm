import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * boj 11437 LCA
 * LCA, DP
 *
 * parent[node][h] = parent[parent[node][h-1]][h-1]
 */

/**
 * 반례 생각하기
 */


public class Main {

    private static int N, M;
    private static List<Integer>[] edges;
    private static int[][] parent;
    private static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edges = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            edges[n1].add(n2);
            edges[n2].add(n1);
        }

        int k = (int)Math.ceil(Math.log(N) / Math.log(2)) + 1;
        depth = new int[N+1];
        parent = new int[N+1][k];

        dfs(new boolean[N+1], 0, 1);
        setSparseTable(k);

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            sb.append(LCA(n1, n2, k)).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(boolean[] visited, int curDepth, int node) {
        visited[node] = true;
        depth[node] = curDepth;

        for (int child : edges[node]) {
            if (!visited[child]) {
                parent[child][0] = node;
                dfs(visited, curDepth + 1, child);
            }
        }
    }

    private static void setSparseTable(int k) {
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }

    private static int LCA(int n1, int n2, int k) {
        if (depth[n1] > depth[n2]) {
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }

        for (int i = k - 1; i >= 0; i--) {
            if (depth[n2] - depth[n1] >= Math.pow(2,i)) {
                n2 = parent[n2][i];
            }
        }

        if (n1 == n2) return n1;

        for (int i = k-1; i >= 0; i--) {
            if (parent[n1][i] != parent[n2][i]) {
                n1 = parent[n1][i];
                n2 = parent[n2][i];
            }
        }

        return parent[n1][0];
    }
}

