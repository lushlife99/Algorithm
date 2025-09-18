import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 1774 우주신과의 교감
 * MST
 */


public class Main {

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x; this.y = y;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int s1, s2;
        double cost;

        public Edge(int s1, int s2, double cost) {
            this.s1 = s1; this.s2 = s2; this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }

    private static int N, M;
    private static int[] parent;
    private static List<Node> nodes;
    private static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = IntStream.rangeClosed(0, N).toArray();
        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            nodes.add(new Node(x, y));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            union(u, v);
        }

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (find(i) == find(j)) continue;

                edges.add(new Edge(i, j, getDistance(nodes.get(i), nodes.get(j))));
            }
        }

        Collections.sort(edges);

        double ans = 0;

        for (Edge e : edges) {
            int r1 = find(e.s1);
            int r2 = find(e.s2);

            if (r1 == r2) continue;

            union(r1, r2);
            ans += e.cost;
        }

        System.out.printf("%.2f\n", ans);

    }

    private static double getDistance(Node n1, Node n2) {
         return Math.sqrt(Math.pow((n1.x - n2.x), 2) + Math.pow((n1.y - n2.y), 2));
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