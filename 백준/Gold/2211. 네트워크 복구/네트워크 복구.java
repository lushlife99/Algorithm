import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 2211 네트워크 복구
 */


public class Main {

    static int N,M;
    static List<int[]>[] edges;
    static int[] parents, distances;

    static class Pair {
        int u,v;

        Pair(int u, int v) {
            this.u = u; this.v = v;
        }

        @Override
        public int hashCode() {
            return Objects.hash(u, v);
        }

        @Override
        public boolean equals(Object o) {
            Pair op = (Pair) o;
            return u == op.u && v == op.v;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);
        parents = new int[N+1];
        distances = new int[N+1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[u].add(new int[]{v,c});
            edges[v].add(new int[]{u,c});
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        distances[1] = 0;
        parents[1] = 1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int[] edge : edges[u]) {
                int v = edge[0];
                int cost = edge[1];

                if (distances[v] > distances[u] + cost) {
                    distances[v] = distances[u] + cost;
                    parents[v] = u;
                    queue.add(v);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Set<Pair> pairSet = new HashSet<>();
        for (int i = 2; i <= N; i++) {
            int current = i;

            while (current != 1) {
                pairSet.add(new Pair(Math.min(current, parents[current]), Math.max(current, parents[current])));
                current = parents[current];
            }
        }

        sb.append(pairSet.size()).append("\n");
        for (Pair p : pairSet) {
            sb.append(p.u).append(" ").append(p.v).append("\n");
        }

        System.out.print(sb);
    }
}

