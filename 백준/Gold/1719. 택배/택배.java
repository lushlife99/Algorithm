import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 1719 택배
 * 플로이드 워샬, 역추적
 */


public class Main {

    static class Edge {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from; this.to = to; this.cost = cost;
        }
    }

    private static int n, m;
    private static int[][] distance;
    private static int[][] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        distance = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(distance[i], 1_000_000_000);
        }
        parent = new int[n+1][n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            distance[u][v] = c;
            distance[v][u] = c;

            parent[u][v] = v;
            parent[v][u] = u;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        parent[i][j] = parent[i][k];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    sb.append("-");
                } else {
                    sb.append(parent[i][j]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}