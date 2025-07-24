import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BOJ 2533
 * 감이 안잡힘
 *
 * 정답 봄. dfs, dp 문제
 * dp[N][K] = 자식들이 모두 수용 가능한 최소 얼리 어답터의 수
 * K == 0 -> N이 얼리 어답터가 아닐때
 * K == 1 -> N이 얼리 어답터일때
 */

public class Main {

    static int N;
    static boolean[] visited;
    static Map<Integer, ArrayList<Integer>> graphMap = new HashMap<>();
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][2];
        visited = new boolean[N+1];
        for (int i = 0; i < N-1; i++) {
            String[] split = br.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            graphMap.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            graphMap.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }

    private static void dfs(int current) {
        visited[current] = true;
        dp[current][0] = 0;
        dp[current][1] = 1;

        for (Integer child : graphMap.get(current)) {
            if (!visited[child]) {
                dfs(child);
                dp[current][0] += dp[child][1]; // 내가 얼리어답터가 아니면, 자식들은 얼리어답터 이어야 함
                dp[current][1] += Math.min(dp[child][1], dp[child][0]);
            }
        }

    }
}
