import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 6086 최대 유량
 *
 */


public class Main {

    static final int MAX = 52;
    static int[][] capacity = new int[MAX][MAX];
    static List<Integer>[] graph = new ArrayList[MAX];

    static int charToIdx(char c) {
        if (c >= 'A' && c <= 'Z') return c - 'A';
        return c - 'a' + 26;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < MAX; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = charToIdx(st.nextToken().charAt(0));
            int v = charToIdx(st.nextToken().charAt(0));
            int c = Integer.parseInt(st.nextToken());

            capacity[u][v] += c;
            capacity[v][u] += c;

            graph[u].add(v);
            graph[v].add(u);
        }

        System.out.println(maxFlow(charToIdx('A'), charToIdx('Z')));
    }

    static int maxFlow(int source, int sink) {
        int totalFlow = 0;

        while (true) {
            int[] parent = new int[MAX];
            Arrays.fill(parent, -1);
            parent[source] = source;

            Queue<Integer> q = new ArrayDeque<>();
            q.add(source);

            while (!q.isEmpty() && parent[sink] == -1) {
                int cur = q.poll();
                for (int next : graph[cur]) {
                    if (parent[next] == -1 && capacity[cur][next] > 0) {
                        parent[next] = cur;
                        q.add(next);
                        if (next == sink) break;
                    }
                }
            }

            if (parent[sink] == -1) break;

            int flow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                flow = Math.min(flow, capacity[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                capacity[u][v] -= flow;
                capacity[v][u] += flow;
            }

            totalFlow += flow;
        }

        return totalFlow;
    }
}
