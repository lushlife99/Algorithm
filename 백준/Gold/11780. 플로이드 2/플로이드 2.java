import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 11780 플로이드 2
 * 플로이드 워샬
*/

public class Main {

    static class Edge {
        int s,e,c;

        public Edge(int s, int e, int c) {
            this.s = s; this.e = e; this.c = c;
        }
    }

    private static final int INF = 1_000_000_001;
    private static int n,m;
    private static List<Edge>[] edges;
    private static int[][] distance;
    private static int[][] next;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        edges = IntStream.rangeClosed(0, n)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        distance = new int[n+1][n+1];
        next = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[s].add(new Edge(s, e, c));
        }

        for (int start = 1; start <=n; start++) {
            for (Edge e : edges[start]) {
                if (distance[start][e.e] > e.c) {
                    distance[start][e.e] = e.c;
                    next[start][e.e] = e.e;
                }
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int d1 = distance[i][j];
                    int d2 = distance[i][k] + distance[k][j];
                    if (d1 > d2) {
                        distance[i][j] = d2;
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (distance[i][j] == INF) {
                    sb.append("0 ");
                    continue;
                }
                sb.append(distance[i][j]).append(" ");
            }
            sb.append("\n");
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (distance[i][j] == INF || i == j) {
                    sb.append(0).append("\n");
                    continue;
                }

                List<Integer> path = new ArrayList<>();
                int cur = i;

                while (cur != j) {
                    path.add(cur);
                    cur = next[cur][j];
                }
                path.add(j);

                sb.append(path.size()).append(" ");
                for (int x : path) sb.append(x).append(" ");
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }

}
