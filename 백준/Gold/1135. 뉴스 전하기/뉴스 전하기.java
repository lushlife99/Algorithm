import java.io.*;
import java.util.*;

/**
 * BOJ 1135
 * dp
 */

public class Main {

    static int N;
    static Map<Integer, List<Integer>> childMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] split = br.readLine().split(" ");

        for (int i = 1; i < N; i++) {
            int parent = Integer.parseInt(split[i]);
            childMap.computeIfAbsent(parent, k -> new ArrayList<>()).add(i);
        }

        System.out.println(dfs(0));
    }

    private static int dfs(int current) {
        if (!childMap.containsKey(current)) return 0;

        List<Integer> times = new ArrayList<>();
        for (int child : childMap.get(current)) {
            times.add(dfs(child));
        }

        times.sort(Collections.reverseOrder());

        int maxTime = 0;
        for (int i = 0; i < times.size(); i++) {
            maxTime = Math.max(maxTime, times.get(i) + i + 1);
        }

        return maxTime;
    }
}
