import java.io.*;
import java.util.*;

/**
 * boj 2150 Strongly Connected Component
 * SCC
 */
public class Main {

    static int V, E;
    static List<Integer>[] graph;
    static int[] order;
    static boolean[] finished;
    static int orderCnt;
    static Stack<Integer> stk = new Stack<>();
    static List<List<Integer>> answer = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new List[V+1];
        order = new int[V+1];
        finished = new boolean[V+1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
        }

        for (int i = 1; i <= V; i++) {
            if (order[i] == 0) {
                dfs(i);
            }
        }

        Collections.sort(answer, (a1, a2) -> {
            return a1.get(0) - a2.get(0);
        });
        bw.write(answer.size() + "\n");

        for (List<Integer> res : answer) {
            for (int u : res) {
                bw.write(u + " ");
            }
            bw.write("-1\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(int u) {
        order[u] = ++orderCnt;
        int low = order[u];
        stk.push(u);

        for (int v : graph[u]) {
            if (order[v] == 0) {
                low = Math.min(low, dfs(v));
            } else if (!finished[v]) {
                low = Math.min(low, order[v]);
            }
        }

        if (order[u] == low) {
            List<Integer> scc = new ArrayList<>();
            while(true) {
                int v = stk.pop();
                scc.add(v);
                finished[v] = true;
                if (u == v) break;
            }
            Collections.sort(scc);
            answer.add(scc);
        }

        return low;
    }

}
