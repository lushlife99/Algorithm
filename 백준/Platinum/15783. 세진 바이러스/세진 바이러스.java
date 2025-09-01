import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 함수형 프로그래밍으로 푸는 연습
 *
 * boj 15783 세진 바이러스
 * SCC
 */
public class Main {

    static int N, M;
    static List<Integer>[] graph;
    static int[] inDegree;
    static int[] sccId;
    static Stack<Integer> stk = new Stack<>();
    static int[] order;
    static boolean[] finished;
    static int orderCnt, sccCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = IntStream.range(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        order = new int[N];
        finished = new boolean[N];
        sccId = new int[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
        }

        for (int i = 0; i < N; i++) {
            if (order[i] == 0) SCC(i);
        }

        inDegree = new int[sccCnt];

        for (int u = 0; u < N; u++) {
            for (int v : graph[u]) {
                if (sccId[u] != sccId[v]) {
                    inDegree[sccId[v]]++;
                }
            }
        }

        // 바이러스 개수 = SCC에서 진입 차수가 0인 요소들의 합
        long answer = Arrays.stream(inDegree)
                .filter(i -> i == 0)
                .count();

        System.out.println(answer);

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
