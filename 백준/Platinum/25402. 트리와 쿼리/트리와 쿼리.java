import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 25402 트리와 쿼리
 * 분리 집합
 */

public class Main {

    static int N, Q;
    static List<Integer>[] edge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        edge = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);
        
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edge[u].add(v);
            edge[v].add(u);
        }

        Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            int[] parent = IntStream.rangeClosed(0, N).toArray();

            Set<Integer> S = new HashSet<>();
            while (st.hasMoreTokens()) {
                S.add(Integer.parseInt(st.nextToken()));
            }

            for (int node : S) {
                for (int neighbor : edge[node]) {
                    if (S.contains(neighbor)) {
                        union(parent, node, neighbor);
                    }
                }
            }

            Map<Integer, Integer> componentSize = new HashMap<>();
            for (int node : S) {
                int root = find(parent, node);
                componentSize.put(root, componentSize.getOrDefault(root, 0) + 1);
            }

            long result = 0;
            for (int size : componentSize.values()) {
                result += (long) size * (size - 1) / 2;
            }
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int find(int[] parent, int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent, parent[a]);
    }

    private static void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}