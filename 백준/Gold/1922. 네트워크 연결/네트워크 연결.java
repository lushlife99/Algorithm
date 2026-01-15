import java.io.*;
import java.util.*;

/**
 * boj 1922 네트워크 연결
 */

public class Main {
    static int N, M;
    static int[] parent;

    static class Edge {
        int u, v, cost;
        Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[b] = a;
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges.add(new Edge(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }

        edges.sort(Comparator.comparingInt(e -> e.cost));

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        int answer = 0;
        int cnt = 0;

        for (Edge e : edges) {
            if (union(e.u, e.v)) {
                answer += e.cost;
                cnt++;
                if (cnt == N - 1) break;
            }
        }

        System.out.println(answer);
    }
}
