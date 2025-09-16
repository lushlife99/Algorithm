import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 12963 달리기
 * 분리 집합
 */

public class Main {

    private static final int MOD = 1000000007;
    private static int N, M;
    private static List<Edge> edge = new ArrayList<>();
    private static int[] parent;
    private static long[] pow3;

    private static class Edge {
        int id, n1, n2;

        public Edge(int id, int n1, int n2) {
            this.id = id;
            this.n1 = n1;
            this.n2 = n2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (M == 0) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edge.add(new Edge(i, u, v));
        }

        pow3 = new long[M];
        pow3[0] = 1;
        for (int i = 1; i < M; i++) {
            pow3[i] = (pow3[i-1] * 3) % MOD;
        }

        parent = IntStream.range(0, N).toArray();
        long answer = 0;
        for (int i = edge.size()-1; i >= 0; i--) {
            Edge e = edge.get(i);

            int ru = find(e.n1);
            int rv = find(e.n2);

            if (ru > rv) {
                int tmp = ru; ru = rv; rv = tmp;
            }

            if (ru == 0 && rv == N-1) {
                answer = (answer + pow3[e.id]) % MOD;
            } else {
                union(ru, rv);
            }
        }

        System.out.println(answer);
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        if (a == b) return;

        if (a == 0) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
}