import java.io.*;
import java.util.*;

/**
 * boj 17398 통신망 분할
 * 분리 집합
 */


public class Main {

    static int N, M, Q;
    static int[] parent, size, remove;
    static int[][] edge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        size = new int[N+1];
        remove = new int[Q];
        edge = new int[M][2];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edge[i][0] = Integer.parseInt(st.nextToken());
            edge[i][1] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> removed = new HashSet<>();

        for (int i = 0; i < Q; i++) {
            remove[i] = Integer.parseInt(br.readLine());
            removed.add(remove[i]);
        }

        for (int i = 0; i < M; i++) {
            if (!removed.contains(i+1)) {
                union(edge[i][0], edge[i][1]);
            }
        }

        long ans = 0;

        for (int i = Q - 1; i >= 0; i--) {
            int target = remove[i];
            int ru = find(edge[target-1][0]);
            int rv = find(edge[target-1][1]);

            if (ru != rv) {
                ans += (long) size[ru] * size[rv];
                union(ru, rv);
            }
        }

        System.out.println(ans);
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;

        if (size[a] < size[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        parent[b] = a;
        size[a] += size[b];
    }

}
