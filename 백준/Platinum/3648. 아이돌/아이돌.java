import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 함수형 프로그래밍 연습
 * 
 * boj 3648 아이돌
 * SCC, 2-SAT
 */


public class Main {
    static int N, M;
    static List<Integer>[] graph;
    static int[] sccId, order;
    static boolean[] finished;
    static Stack<Integer> stack;
    static int orderCnt, sccCnt;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) continue;

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList[2 * N + 1];
            for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[idx(-u)].add(idx(v));
                graph[idx(-v)].add(idx(u));
            }

            graph[N + 1].add(1);

            int size = 2 * N + 1;
            order = new int[size];
            sccId = new int[size];
            finished = new boolean[size];
            stack = new Stack<>();
            orderCnt = 0; sccCnt = 0;

            for (int i = 1; i < size; i++) {
                if (order[i] == 0) SCC(i);
            }

            boolean res = IntStream.rangeClosed(1, N)
                    .allMatch(i -> sccId[i] != sccId[i+N]);

            sb.append(res ? "yes\n" : "no\n");
        }

        System.out.print(sb);
    }

    private static int idx(int n) {
        return (0 < n && n <= N) ? n : -n + N;
    }

    static int SCC(int u) {
        order[u] = ++orderCnt;
        int low = order[u];
        stack.push(u);

        for (int v : graph[u]) {
            if (order[v] == 0) low = Math.min(low, SCC(v));
            else if (!finished[v]) low = Math.min(low, order[v]);
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
