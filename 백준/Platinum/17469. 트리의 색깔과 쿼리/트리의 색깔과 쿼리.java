import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 17469 트리의 색깔과 쿼리
 * 분리 집합
 */

public class Main {

    private static int N, Q;
    private static int[] p, parent;
    private static Set<Integer>[] color;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        p = new int[N+1];
        color = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new HashSet<>())
                .toArray(Set[]::new);

        for (int i = 2; i <= N; i++) {
            p[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            color[i].add(Integer.parseInt(br.readLine()));
        }

        List<String> queries = new ArrayList<>();

        for (int i = 0; i < N + Q - 1; i++) {
            queries.add(br.readLine());
        }

        Collections.reverse(queries);

        parent = IntStream.rangeClosed(0, N).toArray();
        Stack<String> stk = new Stack<>();

        for (String query : queries) {
            st = new StringTokenizer(query);
            int qType = Integer.parseInt(st.nextToken());
            int parameter = Integer.parseInt(st.nextToken());

            if (qType == 1) {
                union(parameter, p[parameter]);
            } else {
                stk.add(color[find(parameter)].size() + "\n");
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }

        System.out.println(sb.toString());
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (color[a].size() < color[b].size()) {
            int tmp = a; a = b; b = tmp;
        }

        parent[b] = a;
        color[a].addAll(color[b]);
    }


}