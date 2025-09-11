import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 3830 교수님은 기다리지 않는다
 * 분리 집합
 */

public class Main {

    static int[] parent;
    static int[] weight; // parent와 무게 차이

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;

            parent = IntStream.rangeClosed(0, N).toArray();
            weight = new int[N+1];

            for (int i = 0; i < M; i++) {
                String[] s = br.readLine().split(" ");
                int u = Integer.parseInt(s[1]);
                int v = Integer.parseInt(s[2]);
                if (s[0].equals("!")) {
                    int c = Integer.parseInt(s[3]);

                    union(u, v, c);
                } else {
                    int rootU = find(u);
                    int rootV = find(v);

                    if (rootU != rootV) {
                        sb.append("UNKNOWN" + "\n");
                    } else {
                        sb.append((weight[u] - weight[v]) + "\n");
                    }
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static void union(int a, int b, int c) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootA] = rootB;
            weight[rootA] = weight[b] + c - weight[a];
        }
    }

    private static int find(int a) {
        if (a == parent[a]) return a;
        int root = find(parent[a]);
        weight[a] += weight[parent[a]];
        return parent[a] = root;
    }
}
