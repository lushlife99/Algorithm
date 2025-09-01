import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 함수형 프로그래밍으로 푸는 연습
 *
 * boj 26146 즉흥 여행
 * SCC
 */
public class Main {

    static int N, M;
    static List<Integer>[] graph;
    static Stack<Integer> stk = new Stack<>();
    static int[] order;
    static boolean[] finished;
    static int orderCnt, sccCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        order = new int[N+1];
        finished = new boolean[N+1];

        graph = IntStream.rangeClosed(0, N+1)
                .mapToObj(k -> new ArrayList())
                .toArray(List[]::new);

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
        }

        for (int i = 1; i <= N; i++) {
            if (order[i] == 0) SCC(i);
        }

        if (sccCnt == 1) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
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
            while (!stk.isEmpty()) {
                int v = stk.pop();
                finished[v] = true;
                if (v == u) break;
            }
            sccCnt++;
        }

        return low;
    }
}
