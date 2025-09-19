import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * boj 2887 행성 터널
 * MST
 */


public class Main {

    private static int N;
    private static List<Node> nodes = new ArrayList<>();
    private static List<Edge> edges = new ArrayList<>();

    private static int[] parent;

    private static class Node {
        int id, x, y, z;
        public Node(int id, int x, int y, int z) {
            this.id = id;
            this.x = x; this.y = y; this.z = z;
        }
    }

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
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            nodes.add(new Node(i, x, y, z));
        }

        Collections.sort(nodes, (n1, n2) -> {
            return n1.x - n2.x;
        });

        for (int i = 0; i < N-1; i++) {
            Node n1 = nodes.get(i);
            Node n2 = nodes.get(i+1);

            edges.add(new Edge(n1.id, n2.id, Math.abs(n1.x - n2.x)));
        }

        Collections.sort(nodes, (n1, n2) -> {
            return n1.y - n2.y;
        });

        for (int i = 0; i < N-1; i++) {
            Node n1 = nodes.get(i);
            Node n2 = nodes.get(i+1);

            edges.add(new Edge(n1.id, n2.id, Math.abs(n1.y - n2.y)));
        }

        Collections.sort(nodes, (n1, n2) -> {
            return n1.z - n2.z;
        });

        for (int i = 0; i < N-1; i++) {
            Node n1 = nodes.get(i);
            Node n2 = nodes.get(i+1);

            edges.add(new Edge(n1.id, n2.id, Math.abs(n1.z - n2.z)));
        }

        parent = IntStream.range(0, N).toArray();
        Collections.sort(edges);

        long ans = 0;

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
