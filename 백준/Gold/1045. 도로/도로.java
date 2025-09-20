import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.*;

/**
 * boj 1045 도로
 * MST
 */


public class Main {

    private static int N, M;
    private static List<Edge> edges = new ArrayList<>();
    private static int[] parent;
    private static Set<Edge> result = new HashSet<>();

    private static class Edge {
        int id, n1, n2;

        public Edge(int id, int n1, int n2) {
            this.id = id; this.n1 = n1; this.n2 = n2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public boolean equals(Object o) {
            Edge e = (Edge) o;

            return this.id == e.id;
        }

        @Override
        public String toString() {
            return "id :" + this.id + " n1 :" + this.n1 + " n2 :" + this.n2;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int id = 1;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(j) == 'Y') {
                    edges.add(new Edge(id++, i, j));
                }
            }
        }

        Collections.sort(edges, (e1, e2) -> {
            if (e1.n1 != e2.n1) return Integer.compare(e1.n1, e2.n1);

            return Integer.compare(e1.n2, e2.n2);
        });


        parent = IntStream.range(0, N).toArray();

        for (Edge e : edges) {
            union(e);
        }

        if (edges.size() < M || result.size() < N-1) {
            System.out.println(-1);
            return;
        }

        for (Edge e : edges) {
            result.add(e);
            if (result.size() == M) break;
        }

        int[] ans = new int[N];

        for (Edge e : result) {
            ans[e.n1]++;
            ans[e.n2]++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(ans[i] + " ");
        }

        System.out.println(sb.toString());

    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(Edge e) {
        int a = find(e.n1);
        int b = find(e.n2);

        if (a == b) return;

        if (a > b) {
            int tmp = a; a = b; b = tmp;
        }

        parent[b] = a;
        result.add(e);
    }
}
