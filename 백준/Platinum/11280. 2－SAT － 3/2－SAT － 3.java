import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 함수형 프로그래밍 연습
 *
 * boj 11280 2-SAT-3
 * SCC, 2-SAT
 */


public class Main {
    static int N, M;
    static List<Integer>[] graph;
    static int[] sccId, order;
    static boolean[] finished;
    static Stack<Integer> stack = new Stack<>();
    static int orderCnt, sccCnt;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = IntStream.rangeClosed(0, 2*N)
                .mapToObj(p -> new ArrayList<>())
                .toArray(List[]::new);

        order = new int[2*N+1];
        sccId = new int[2*N+1];
        finished = new boolean[2*N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[idx(-u)].add(idx(v));
            graph[idx(-v)].add(idx(u));
        }

        for (int i = 1; i <= 2 * N; i++) {
            if (order[i] == 0) SCC(i);
        }

        boolean ans = IntStream.rangeClosed(1, N)
                .allMatch(i -> sccId[i] != sccId[N+i]);

        bw.write(ans ? "1" : "0");
        bw.flush();
        bw.close();
        br.close();
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
