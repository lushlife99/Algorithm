import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * times[N] = max(times[K], times[M]) + costs[N]
 */

public class Main {

    static int T;
    static int N;
    static int K;
    static int[] costs;
    static int[] times;
    static Map<Integer, List<Integer>> map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] split = br.readLine().split(" ");
            N = Integer.parseInt(split[0]);
            K = Integer.parseInt(split[1]);

            split = br.readLine().split(" ");
            costs = new int[N+1];
            times = new int[N+1];
            visited = new boolean[N+1];
            map = new HashMap<>();

            for (int j = 0; j < N; j++) {
                costs[j+1] = Integer.parseInt(split[j]);
                map.put(j+1, new ArrayList<>());
            }

            for (int j = 0; j < K; j++) {
                split = br.readLine().split(" ");
                int n1 = Integer.parseInt(split[0]);
                int n2 = Integer.parseInt(split[1]);
                map.get(n2).add(n1);
            }

            int target = Integer.parseInt(br.readLine());
            System.out.println(dfs(target));
        }
    }

    static int dfs(int target) {
        if (visited[target])
            return times[target];

        int max = 0;

        for (Integer parent : map.get(target)) {
            max = Math.max(max, dfs(parent));
        }

        times[target] = max + costs[target];
        visited[target] = true;
        return times[target];
    }

}
