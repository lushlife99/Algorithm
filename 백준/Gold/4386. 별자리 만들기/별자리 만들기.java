import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 4386 별자리 만들기
 * MST
 */


public class Main {

    private static class Star {
        float x, y;

        public Star(float x, float y) {
            this.x = x; this.y = y;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int s1, s2;
        float cost;

        public Edge(int s1, int s2, float cost) {
            this.s1 = s1; this.s2 = s2; this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Float.compare(this.cost, o.cost);
        }
    }

    private static int N;
    private static int[] parent;
    private static List<Star> stars;
    private static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = IntStream.range(0, N).toArray();
        stars = new ArrayList<>();
        edges = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());

            stars.add(new Star(x, y));
        }

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                edges.add(new Edge(i, j, getDistance(stars.get(i), stars.get(j))));
            }
        }

        Collections.sort(edges);

        float ans = 0;

        for (Edge e : edges) {
            int r1 = find(e.s1);
            int r2 = find(e.s2);

            if (r1 == r2) continue;

            union(r1, r2);
            ans += e.cost;
        }

        System.out.printf("%.2f\n", ans);

    }

    private static float getDistance(Star s1, Star s2) {
         return (float) Math.sqrt(Math.pow((s1.x - s2.x), 2) + Math.pow((s1.y - s2.y), 2));
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (a > b) {
            int tmp = a; a = b; b = tmp;
        }

        parent[b] = a;
    }

}