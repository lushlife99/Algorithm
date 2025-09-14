import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 3780 네트워크 연결
 * 분리 집합
 */

public class Main {

    static int T, N;
    static int[] parent, distance;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            String input = "";

            parent = IntStream.rangeClosed(0, N).toArray();
            distance = new int[N+1];
            while (!(input = br.readLine()).equals("O")) {
                StringTokenizer st = new StringTokenizer(input);
                String type = st.nextToken();

                if (type.equals("E")) {
                    int x = Integer.parseInt(st.nextToken());
                    find(x);
                    sb.append(distance[x]).append('\n');
                } else if (type.equals("I")){
                    int u = Integer.parseInt(st.nextToken());
                    int v = Integer.parseInt(st.nextToken());
                    union(u, v);
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static void union(int a, int b) {
        if (a == b) return;

        parent[a] = b;
        distance[a] = (Math.abs(a - b) % 1000);
    }

    private static int find(int a) {
        if (a == parent[a]) return a;

        int p = find(parent[a]);
        distance[a] += distance[parent[a]];
        parent[a] = p;
        return parent[a];
    }
}