import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 함수형 프로그래밍으로 푸는 연습
 * boj 16915 호텔 관리
 * SCC, 2-SAT
 *
 */

public class Main {

    static int N, M;
    static List<Integer>[] graph;
    static int[] order;
    static int[] sccId;
    static Stack<Integer> stk = new Stack<>();
    static boolean[] finished;
    static int sccCnt, orderCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = IntStream.rangeClosed(0, 2 * M)
                .mapToObj(p -> new ArrayList<>())
                .toArray(List[]::new);

        int[] first = new int[N+1];
        List<Integer>[] switches = IntStream.rangeClosed(0, N)
                .mapToObj(p -> new ArrayList())
                .toArray(List[]::new);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            first[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int u = Integer.parseInt(st.nextToken());
                switches[u].add(i);
            }
        }

        for (int i = 1; i <= N; i++) {
            int s1 = switches[i].get(0);
            int s2 = switches[i].get(1);

            if (first[i] == 0) {
                graph[idx(-s1)].add(idx(s2));
                graph[idx(-s2)].add(idx(s1));
                graph[idx(s1)].add(idx(-s2));
                graph[idx(s2)].add(idx(-s1));
            } else {
                graph[idx(s1)].add(idx(s2));
                graph[idx(s2)].add(idx(s1));
                graph[idx(-s1)].add(idx(-s2));
                graph[idx(-s2)].add(idx(-s1));
            }
        }

        order = new int[2 * M + 1];
        sccId = new int[2 * M + 1];
        finished = new boolean[2 * M + 1];

        for (int i = 1; i <= 2 * M; i++) {
            if (order[i] == 0) SCC(i);
        }

        String ans = IntStream.rangeClosed(1, M)
                .allMatch(i -> sccId[i] != sccId[i+M]) ? "1" : "0";

        System.out.println(ans);
    }

    private static int idx(int n) {
        return (0 < n && n <= M) ? n : -n + M;
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
                sccId[v] = sccCnt;
                if (u == v) break;
            }
            sccCnt++;
        }
        return low;
    }

}
