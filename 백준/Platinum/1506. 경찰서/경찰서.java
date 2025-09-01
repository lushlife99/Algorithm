import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 1508 경찰서
 * SCC
 * 함수형 프로그래밍으로 푸는 연습
 */
public class Main {

    static int N;
    static int[] cost;
    static List<Integer>[] graph;
    static boolean[] finished;
    static Stack<Integer> stk = new Stack<>();
    static int orderCnt;
    static int[] order;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String[] tokens = br.readLine().split(" ");

        graph = IntStream.rangeClosed(0, N+1)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        cost = Arrays.stream(tokens)
                .mapToInt(Integer::parseInt)
                .toArray();

        order = new int[N+1];
        finished = new boolean[N+1];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == '1') {
                    graph[i+1].add(j+1);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (order[i] == 0) {
                SCC(i);
            }
        }

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
            int res = 1000000;
            while(true) {
                int v = stk.pop();
                finished[v] = true;
                res = Math.min(res, cost[v-1]);
                if (u == v) break;
            }
            answer += res;
        }

        return low;
    }
}
