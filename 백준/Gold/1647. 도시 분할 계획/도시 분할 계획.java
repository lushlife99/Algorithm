import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        edges.sort(Comparator.comparingInt(e -> e.cost));

        int total = 0;
        int maxEdge = 0;
        int count = 0;

        for (Edge e : edges) {
            if (find(e.a) != find(e.b)) {
                union(e.a, e.b);
                total += e.cost;
                if (e.cost > maxEdge) maxEdge = e.cost;
                count++;
                if (count == N - 1) break;
            }
        }

        System.out.println(total - maxEdge);
    }

    static class Edge {
        int a, b, cost;
        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }
}
