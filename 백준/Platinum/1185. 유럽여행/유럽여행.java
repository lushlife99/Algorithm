import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 1185 유럽여행
 * MST
 *
 * 왕복 최소 비용 구하기
 * -
 */


public class Main {

    private static int N, P;
    private static int[] cost, parent;
    private static List<Edge> edges = new ArrayList<>();

    private static class Edge implements Comparable<Edge> {
        int n1, n2, cost;

        public Edge(int n1, int n2, int cost) {
            this.n1 = n1; this.n2 = n2; this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        cost = new int[N+1];

        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(u, v, 2 * c + cost[v] + cost[u]));
        }

        Collections.sort(edges);
        parent = IntStream.rangeClosed(0, N).toArray();

        int ans = IntStream.rangeClosed(1, N)
                .map(i -> cost[i])
                .min().getAsInt();

        for (Edge e : edges) {
            int r1 = find(e.n1);
            int r2 = find(e.n2);

            if (r1 != r2) {
                union(r1, r2);
                ans += e.cost;
            }
        }

        System.out.println(ans);
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        parent[a] = b;
    }

}
