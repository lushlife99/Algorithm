import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 11266 단절점
 *
 * V기준 인접한 정점들을 bfs
 */

public class Main {

    static int V, E;
    static List<Integer>[] graph;
    static int[] disc, low;
    static boolean[] isCut;
    static int time = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        disc = new int[V+1];
        low = new int[V+1];
        isCut = new boolean[V+1];

        for (int i = 1; i <= V; i++) {
            if (disc[i] == 0) dfs(i, true, -1);
        }

        StringBuilder sb = new StringBuilder();
        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i <= V; i++)
            if (isCut[i]) answer.add(i);

        sb.append(answer.size()).append("\n");
        for (int x : answer) sb.append(x).append(" ");

        System.out.println(sb);
    }

    static void dfs(int u, boolean isRoot, int parent) {
        disc[u] = low[u] = time++;
        int childCount = 0;

        for (int v : graph[u]) {
            if (v == parent) continue;

            if (disc[v] == 0) {
                childCount++;
                dfs(v, false, u);

                low[u] = Math.min(low[u], low[v]);

                if (!isRoot && low[v] >= disc[u])
                    isCut[u] = true;

            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (isRoot && childCount >= 2)
            isCut[u] = true;
    }
}