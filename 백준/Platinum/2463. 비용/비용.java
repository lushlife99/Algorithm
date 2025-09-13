import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 2463 비용
 * 분리 집합
 *
 * find(u) != find(v) 때까지 비용이 높은 간선부터 차례대로 잇기
 *
 * 또한 모든 노드 순회하면서 find 연산 시 시간 터짐. 규칙 찾아야함
 */

public class Main {

    static int N, M;
    static List<Edge> edgeList = new ArrayList<>();
    static int[] parent, size;

    static class Edge {
        int u, v, c;

        public Edge(int u, int v, int c) {
            this.u = u; this.v = v; this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long sum = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            sum += c;
            edgeList.add(new Edge(u, v, c));
        }

        Collections.sort(edgeList, (e1, e2) -> e2.c - e1.c);

        parent = IntStream.rangeClosed(0, N).toArray();
        size = new int[N+1];
        Arrays.fill(size, 1);

        long answer = 0;

        for (Edge e : edgeList) {
            int ru = find(e.u);
            int rv = find(e.v);

            if (ru != rv) {
                long connected = (long) size[ru] * size[rv];
                answer += connected * sum;
                answer %= 1000000000;

                union(ru, rv);
            }

            sum -= e.c;
        }

        System.out.println(answer);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;
        if (size[a] < size[b]) {
            int tmp = a; a = b; b = tmp;
        }

        parent[b] = a;
        size[a] += size[b];
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}
