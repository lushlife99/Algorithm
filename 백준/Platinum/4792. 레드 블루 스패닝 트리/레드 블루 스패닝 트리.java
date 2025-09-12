import java.io.*;
import java.util.*;

/**
 * boj 4792 레드 블루 스패닝 트리
 * 분리 집합, MST
 *
 * Blue K개 뽑고 나머지를 레드에서 뽑아서 MST 체크 -> 메모리 초과
 *
 * kruskal 2번사용하는 풀이 (정답 봄)
 */

public class Main {
    static int N, M, K;
    static List<Edge> edges;

    static class Edge {
        int u, v;
        boolean blue;
        Edge(int u, int v, boolean blue) {
            this.u = u; this.v = v; this.blue = blue;
        }
    }

    static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }

    static boolean union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a == b) return false;
        parent[b] = a;
        return true;
    }

    static int kruskal(boolean prioritizeBlue) {
        edges.sort((e1, e2) -> {
            int w1 = e1.blue ? (prioritizeBlue ? 0 : 1) : (prioritizeBlue ? 1 : 0);
            int w2 = e2.blue ? (prioritizeBlue ? 0 : 1) : (prioritizeBlue ? 1 : 0);
            if (w1 != w2) return w1 - w2;
            return 0;
        });

        int[] parent = new int[N+1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        int blueCount = 0;
        int cnt = 0;
        for (Edge e : edges) {
            if (union(parent, e.u, e.v)) {
                cnt++;
                if (e.blue) blueCount++;
                if (cnt == N-1) break;
            }
        }
        return blueCount;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            if (N == 0) break;

            edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                String c = st.nextToken();
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                edges.add(new Edge(u, v, c.equals("B")));
            }

            int minBlue = kruskal(false);
            int maxBlue = kruskal(true);

            if (minBlue <= K && K <= maxBlue) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.print(sb.toString());
    }
}
