import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 함수형 프로그래밍으로 푸는 연습
 *
 * boj 6543 그래프의 싱크
 * SCC
 */
public class Main {

    static int N, M;
    static List<Integer>[] graph;
    static int[] outDegree;
    static int[] sccId;
    static Stack<Integer> stk = new Stack<>();
    static int[] order;
    static boolean[] finished;
    static int orderCnt, sccCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;

        while (true) {
            input = br.readLine();
            if (input.equals("0")) break;

            StringTokenizer st = new StringTokenizer(input);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = IntStream.rangeClosed(0, N+1)
                    .mapToObj(p -> new ArrayList<>())
                    .toArray(List[]::new);

            stk.clear();
            sccId = new int[N+1];
            order = new int[N+1];
            finished = new boolean[N+1];

            orderCnt = sccCnt = 0;


            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < M; i++) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
            }

            for (int i = 1; i <= N; i++) {
                if (order[i] == 0) SCC(i);
            }

            outDegree = new int[sccCnt];

            for (int u = 1; u <= N; u++) {
                for (int v : graph[u]) {
                    if (sccId[u] != sccId[v]) {
                        outDegree[sccId[u]]++;
                    }
                }
            }

            for (int u = 1; u <= N; u++) {
                if (outDegree[sccId[u]] == 0) {
                    bw.write(u + " ");
                }
            }
            bw.write("\n");

        }
        bw.flush();
        bw.close();
        br.close();

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
                sccId[v] = sccCnt;
                finished[v] = true;
                if (u == v) break;
            }
            sccCnt++;
        }

        return low;
    }

}
