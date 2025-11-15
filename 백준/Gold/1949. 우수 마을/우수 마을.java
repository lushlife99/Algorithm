import java.io.*;
import java.util.*;
import java.util.stream.*;


/**
 * boj 1949 우수 마을
 * 트리, dp
 *
 * dp[u][0] = dp[v][1]
 * dp[u][1] = dp[v1][0] + dp[v2][0] + arr[u]
 */

public class Main {

    private static int N;
    private static int[] arr;
    private static int[][] dp;
    private static List<Integer>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        edges = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList())
                .toArray(List[]::new);

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            edges[n1].add(n2);
            edges[n2].add(n1);
        }

        dp = new int[N+1][2];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        setDp(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void setDp(int current) {
        int sum = 0;
        int sum2 = 0;
        dp[current][0] = 0;

        for (int next : edges[current]) {
            if (dp[next][0] == 0) continue;

            setDp(next);
            sum += dp[next][0];
            sum2 += Math.max(dp[next][0], dp[next][1]);
        }

        dp[current][1] = sum + arr[current];
        dp[current][0] = sum2;
    }
}