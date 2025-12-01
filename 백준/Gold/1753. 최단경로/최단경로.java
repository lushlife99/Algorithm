import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 1753 최단경로
 * 다익스트라, 역추적
 */

/**
 * 반례 찾기
 * 최대 - O
 * 최소 - O
 */

public class Main {

    static class Edge {
        int to;
        int distance;

        public Edge(int to, int distance) {
            this.to = to; this.distance = distance;
        }
    }

    static class Node implements Comparable<Node> {
        int current;
        int distance;

        public Node(int current, int distance) {
            this.current = current;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node node) {
            return this.distance - node.distance;
        }
    }

    private static int V, E;
    private static List<Edge>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = IntStream.rangeClosed(0, V)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            edges[s].add(new Edge(e, d));
        }

        int[] distance = bfs(start);
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= V; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(distance[i]);
            }

            sb.append("\n");
        }

        System.out.print(sb);

    }

    private static int[] bfs(int start) {
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        int[] distance = new int[V+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            if (distance[n.current] < n.distance) continue;

            for (Edge e : edges[n.current]) {
                int next = e.to;

                if (distance[next] > distance[n.current] + e.distance) {
                    distance[next] = distance[n.current] + e.distance;
                    queue.add(new Node(next, distance[n.current] + e.distance));
                }
            }
        }

        return distance;
    }
}

