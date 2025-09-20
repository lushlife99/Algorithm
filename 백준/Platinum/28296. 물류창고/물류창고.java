import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.*;

/**
 * boj 28296 물류창고
 * MST, 분리집합
 */


public class Main {

    private static int N, K, M;
    private static int[] company, parent, size;
    private static long[] answer;
    private static Map<Integer, Integer>[] companyCnt;
    private static List<Edge> edges = new ArrayList<>();

    private static class Edge implements Comparable<Edge> {

        int n1, n2, cost;

        public Edge(int n1, int n2, int cost) {
            this.n1 = n1; this.n2 = n2; this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return o.cost - this.cost;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        company = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            company[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(n1, n2, cost));
        }

        answer = new long[K+1];
        companyCnt = new HashMap[N + 1];
        for (int i = 1; i <= N; i++) {
            companyCnt[i] = new HashMap<>();
            companyCnt[i].put(company[i], 1);
        }

        Collections.sort(edges);
        parent = IntStream.rangeClosed(0, N).toArray();
        size = IntStream.rangeClosed(0, N).map(i -> 1).toArray();

        for (Edge e : edges) {
            union(e.n1, e.n2, e.cost);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= K; i++) {
            sb.append(answer[i] + "\n");
        }

        System.out.println(sb.toString());

    }

    private static int find(int a) {
        if (parent[a] == a) return parent[a];
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b, int cost) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb) return;

        if (size[ra] < size[rb]) {
            int tmp = ra; ra = rb; rb = tmp;
        }

        for (Map.Entry<Integer, Integer> entry : companyCnt[rb].entrySet()) {
            int c = entry.getKey();
            int cntRb = entry.getValue();
            int cntRa = companyCnt[ra].getOrDefault(c, 0);

            long pairs = (long) cntRa * cntRb;
            answer[c] += pairs * cost;

            companyCnt[ra].put(c, cntRa + cntRb);
        }

        parent[rb] = ra;
        size[ra] += size[rb];

    }


}
