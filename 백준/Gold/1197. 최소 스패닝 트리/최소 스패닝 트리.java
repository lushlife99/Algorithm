import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 1197 최소 스패닝 트리
 * MST
 */


public class Main {

    private static class Edge implements Comparable<Edge> {
        private int n1, n2, cost;

        public Edge(int n1, int n2, int cost) {
            this.n1 = n1; this.n2 = n2; this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static int V, E;
    private static List<Edge> edges;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edges = new ArrayList<>();
        parent = IntStream.rangeClosed(0, V).toArray();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, c));
        }

        Collections.sort(edges);

        int ans = 0, cnt = 1;

        for (Edge e : edges) {
            int r1 = find(e.n1);
            int r2 = find(e.n2);

            if (r1 != r2) {
                union(r1, r2);
                ans += e.cost;
                cnt++;
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
        if (a > b) {
            int tmp = a; a = b; b = tmp;
        }

        parent[b] = a;
    }

}