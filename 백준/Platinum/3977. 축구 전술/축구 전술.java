import java.io.*;
import java.util.*;

/**
 * boj 3977 축구 전술
 * SCC
 */
public class Main {

    static int TC;
    static boolean[] finished;
    static Stack<Integer> stk;
    static int[] order;
    static int[] sccId;
    static List<Integer>[] graph;
    static int orderCnt, sccCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            orderCnt = sccCnt = 0;
            sccId = new int[N];
            graph = new List[N];
            finished = new boolean[N];
            stk = new Stack<>();
            order = new int[N];

            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
            }

            if (TC > 0) {
                br.readLine();
            }

            for (int i = 0; i < N; i++) {
                if (order[i] == 0) dfs(i);
            }

            int[] inDegree = new int[sccCnt];
            for (int u = 0; u < N; u++) {
                for (int v : graph[u]) {
                    if (sccId[u] != sccId[v]) {
                        inDegree[sccId[v]]++;
                    }
                }
            }

            int cnt = 0;
            int target = 0;

            for (int i = 0; i < sccCnt; i++) {
                if (inDegree[i] == 0) {
                    target = i;
                    cnt++;
                }
            }

            if (cnt > 1) {
                bw.write("Confused\n");
            } else {
                for (int i = 0; i < N; i++) {
                    if (sccId[i] == target) {
                        bw.write(i + "\n");
                    }
                }
            }

            bw.write("\n");
        }
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

        if (low == order[u]) {
            while (true) {
                int v = stk.pop();
                finished[v] = true;
                sccId[v] = sccCnt;
                if (u == v) break;
            }
            sccCnt++;
        }

        return low;
    }

}
