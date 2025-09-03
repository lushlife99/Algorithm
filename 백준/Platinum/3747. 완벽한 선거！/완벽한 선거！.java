import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 함수형 프로그래밍 연습
 *
 * boj 3747 완벽한 선거!
 *
 * SCC, 2-SAT
 *
 * 입력이 더러운 문제
 */

public class Main {

    static int N, M;
    static List<Integer>[] graph;
    static Stack<Integer> stk;
    static int[] order;
    static int[] sccId;
    static boolean[] finished;
    static int orderCnt, sccCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = "";

        while ((input = br.readLine()) != null) {
            input = input.trim();
            if (input.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(input);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = IntStream.rangeClosed(0, 2 * N + 1)
                    .mapToObj(p -> new ArrayList<>())
                    .toArray(List[]::new);

            int edgeCount = 0;
            while (edgeCount < M) {
                if (!st.hasMoreTokens()) {
                    String line = br.readLine();
                    if (line == null) break;
                    st = new StringTokenizer(line);
                }

                if (st.hasMoreTokens()) {
                    int u = Integer.parseInt(st.nextToken());
                    if (st.hasMoreTokens()) {
                        int v = Integer.parseInt(st.nextToken());

                        graph[idx(-u)].add(idx(v));
                        graph[idx(-v)].add(idx(u));
                        edgeCount++;
                    }
                }
            }

            stk = new Stack<>();
            order = new int[2 * N + 1];
            sccId = new int[2 * N + 1];
            finished = new boolean[2 * N + 1];
            orderCnt = sccCnt = 0;

            for (int i = 1; i <= 2 * N; i++) {
                if (order[i] == 0) SCC(i);
            }

            boolean ans = IntStream.rangeClosed(1, N)
                    .allMatch(i -> sccId[i] != sccId[N + i]);

            bw.write(ans ? "1\n" : "0\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int idx(int n) {
        return (0 <= n && n <= N) ? n : -n + N;
    }

    private static int SCC(int u) {
        order[u] = ++orderCnt;
        int low = order[u];
        stk.push(u);

        for (int v : graph[u]) {
            if (order[v] == 0) {
                low = Math.min(low, SCC(v));
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