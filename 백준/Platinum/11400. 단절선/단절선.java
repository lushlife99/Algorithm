import java.io.*;
import java.util.*;

public class Main {
    static int V, E;
    static ArrayList<Integer>[] graph;
    static int[] dfsOrder, low;
    static boolean[] visited;
    static int orderCnt;
    static ArrayList<int[]> bridges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfsOrder = new int[V + 1];
        low = new int[V + 1];
        visited = new boolean[V + 1];
        bridges = new ArrayList<>();
        orderCnt = 0;

        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                dfs(i, -1);
            }
        }

        Collections.sort(bridges, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        StringBuilder sb = new StringBuilder();
        sb.append(bridges.size()).append("\n");
        for (int[] e : bridges) {
            sb.append(e[0]).append(" ").append(e[1]).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void dfs(int curr, int parent) {
        visited[curr] = true;
        dfsOrder[curr] = ++orderCnt;
        low[curr] = dfsOrder[curr];

        for (int nxt : graph[curr]) {
            if (nxt == parent) continue;

            if (!visited[nxt]) {
                dfs(nxt, curr);
                low[curr] = Math.min(low[curr], low[nxt]);

                if (dfsOrder[curr] < low[nxt]) {
                    int a = Math.min(curr, nxt);
                    int b = Math.max(curr, nxt);
                    bridges.add(new int[]{a, b});
                }
            } else {
                low[curr] = Math.min(low[curr], dfsOrder[nxt]);
            }
        }
    }
}
