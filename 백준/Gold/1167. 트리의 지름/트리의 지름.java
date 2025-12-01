import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 1167 트리의 지름
 *
 */


public class Main {

    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to; this.cost = cost;
        }
    }

    private static int V;
    private static List<Edge>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        edges = IntStream.rangeClosed(0, V)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;

                int cost = Integer.parseInt(st.nextToken());
                edges[from].add(new Edge(to, cost));
            }
        }

        int[] distance = bfs(1);
        int max = 0;
        int node = 0;

        for (int i = 0; i < distance.length; i++) {
            if (distance[i] != Integer.MAX_VALUE) {
                if (distance[i] > max) {
                    max = distance[i];
                    node = i;
                }
            }
        }

        distance = bfs(node);

        max = 0;
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] != Integer.MAX_VALUE) {
                if (distance[i] > max) {
                    max = distance[i];
                }
            }
        }

        System.out.println(max);
    }

    private static int[] bfs(int start) {
        int[] distance = new int[V+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        Queue<Integer> queue = new PriorityQueue<>((i1, i2) -> {
            return distance[i1] - distance[i2];
        });
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Edge e : edges[current]) {
                if (distance[e.to] > distance[current] + e.cost) {
                    distance[e.to] = distance[current] + e.cost;
                    queue.add(e.to);
                }
            }
        }

        return distance;
    }
}

