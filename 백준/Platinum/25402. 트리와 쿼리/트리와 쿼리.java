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
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        parent = IntStream.rangeClosed(0, N).toArray();

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }

        Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            Set<Integer> S = new HashSet<>();

            while (st.hasMoreTokens()) {
                S.add(Integer.parseInt(st.nextToken()));
            }

            List<Integer> sList = new ArrayList<>(S);
            Map<Integer, Integer> sCntMap = new HashMap<>();
            for (int j = 0; j < K; j++) {
                int root = find(sList.get(j), S);
                sCntMap.put(root, sCntMap.getOrDefault(root, 0) + 1);
            }

            int res = 0;

            for (int key : sCntMap.keySet()) {
                int cnt = sCntMap.get(key);

                if (cnt == 1) continue;

                res += cnt * (cnt-1) / 2;
            }
            sb.append(res + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int find(int a) {
        if (a == parent[a]) return a;
        return find(parent[a]);
    }

    private static void union(int a, int b) {
        if (a > b) {
            int tmp = a; a = b; b = tmp;
        }

        if (parent[b] == b) parent[b] = a;
        else  parent[a] = b;
    }

    private static int find(int a, Set<Integer> S) {
        if (a == parent[a]) return a;
        if (!S.contains(parent[a])) return a;

        return find(parent[a], S);
    }

}