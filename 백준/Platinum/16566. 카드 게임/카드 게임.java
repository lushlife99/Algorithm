import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 16566 카드 게임
 * 분리 집합
 */

public class Main {

    static int N, M, K;
    static int[] parent;
    static boolean[] alive;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = IntStream.rangeClosed(0, N)
                .map(i -> i+1)
                .toArray();

        alive = new boolean[N+1];

        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            alive[Integer.parseInt(st.nextToken())] = true;
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            int a = Integer.parseInt(st.nextToken());
            int used = find(a);
            sb.append(used + "\n");
            alive[used] = false;
        }

        System.out.println(sb.toString());
    }

    private static int find(int a) {
        if (alive[parent[a]]) return parent[a];
        return parent[a] = find(parent[a]);
    }
}
