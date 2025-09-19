import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * boj 14621 나만 안되는 연애
 * MST
 */


public class Main {

    private static int N, M;
    private static int[] collegeType, parent;
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
        M = Integer.parseInt(st.nextToken());

        collegeType = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            if (st.nextToken().equals("M")) {
                collegeType[i] = 1;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (collegeType[n1] != collegeType[n2]) {
                edges.add(new Edge(n1, n2, cost));
            }
        }

        Collections.sort(edges);

        parent = IntStream.rangeClosed(0, N).toArray();
        int cnt = 0, ans = 0;

        for (Edge e : edges) {

            int r1 = find(e.n1);
            int r2 = find(e.n2);

            if (r1 != r2) {
                union(r1, r2);
                cnt++;
                ans += e.cost;
            }
        }

        if (cnt == N-1) System.out.println(ans);
        else System.out.println(-1);
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[a] = b;
    }

}
