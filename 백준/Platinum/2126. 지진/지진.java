import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 2126 지진
 * MST
 */


public class Main {
    static int N, M;
    static double F, left, right, mid;
    static int[] parent;
    static List<Edge> edges = new ArrayList<>();

    static class Edge {
        int from, to;
        double cost, time;

        Edge(int from, int to, double cost, double time) {
            this.from = from;
            this.to = to;
            this.cost = cost;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Double.parseDouble(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            double cost = Double.parseDouble(st.nextToken());
            double time = Double.parseDouble(st.nextToken());
            edges.add(new Edge(from, to, cost, time));
        }

        left = 0;
        right = 1000000;

        for (int i = 0; i < 100; i++) {
            mid = (left + right) / 2;
            if (kruskal(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        System.out.printf("%.4f", mid);
    }

    static boolean kruskal(double mid) {
        Collections.sort(edges, (a, b) -> {
            double costA = a.cost + a.time * mid;
            double costB = b.cost + b.time * mid;
            return Double.compare(costA, costB);
        });

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        double totalCost = 0;
        int edgeCount = 0;

        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                totalCost += edge.cost + edge.time * mid;
                edgeCount++;
                if (edgeCount == N - 1) break;
            }
        }

        return totalCost <= F;
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return false;
        parent[rootY] = rootX;
        return true;
    }
}
