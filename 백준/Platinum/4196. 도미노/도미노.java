import java.io.*;
import java.util.*;

/**
 * boj 4196 도미노
 * SCC, 위상정렬
 * SCC 개념 몰라서 정답 봄. -> 관련 문제 내일 풀어보기
 */
public class Main {

    static int N, M;
    static List<Integer>[] graph;
    static int[] order, sccId;
    static boolean[] finished;
    static Stack<Integer> stack;
    static int orderCnt, sccCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N+1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
            }

            order = new int[N+1];
            sccId = new int[N+1];
            finished = new boolean[N+1];
            stack = new Stack<>();
            orderCnt = 0;
            sccCnt = 0;

            for (int i = 1; i <= N; i++) {
                if (order[i] == 0) dfs(i);
            }

            boolean[] indegree = new boolean[sccCnt];
            for (int u = 1; u <= N; u++) {
                for (int v : graph[u]) {
                    if (sccId[u] != sccId[v]) {
                        indegree[sccId[v]] = true;
                    }
                }
            }

            int answer = 0;
            for (int i = 0; i < sccCnt; i++) {
                if (!indegree[i]) answer++;
            }

            bw.write(answer + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(int u) {
        order[u] = ++orderCnt;
        int low = order[u];
        stack.push(u);

        for (int v : graph[u]) {
            if (order[v] == 0) {
                low = Math.min(low, dfs(v));
            } else if (!finished[v]) {
                low = Math.min(low, order[v]);
            }
        }

        if (low == order[u]) {
            while (true) {
                int x = stack.pop();
                finished[x] = true;
                sccId[x] = sccCnt;
                if (x == u) break;
            }
            sccCnt++;
        }
        return low;
    }
}
