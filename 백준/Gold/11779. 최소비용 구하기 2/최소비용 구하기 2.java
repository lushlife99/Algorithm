import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Map<Integer, List<Edge>> edgeMap = new HashMap<>();
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            String[] split = br.readLine().split(" ");
            int f = Integer.parseInt(split[0]);
            int t = Integer.parseInt(split[1]);
            int d = Integer.parseInt(split[2]);

            edgeMap.computeIfAbsent(f, k -> new ArrayList<>()).add(new Edge(f, t, d));
        }

        String[] split = br.readLine().split(" ");
        int start = Integer.parseInt(split[0]);
        int end = Integer.parseInt(split[1]);

        DistanceInfo dInfo = dijkstra(start, end);
        System.out.println(dInfo.distance);
        System.out.println(dInfo.path.size());
        for (int node : dInfo.path) {
            System.out.print(node + " ");
        }
    }

    public static DistanceInfo dijkstra(int start, int end) {
        PriorityQueue<DistanceInfo> pq = new PriorityQueue<>();
        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        pq.offer(new DistanceInfo(0, new ArrayList<>(List.of(start))));

        while (!pq.isEmpty()) {
            DistanceInfo currentInfo = pq.poll();
            int currentNode = currentInfo.path.get(currentInfo.path.size() - 1);

            if (currentNode == end) {
                return currentInfo;
            }

            List<Edge> edges = edgeMap.getOrDefault(currentNode, Collections.emptyList());
            for (Edge edge : edges) {
                int newDist = currentInfo.distance + edge.distance;

                if (newDist < distances[edge.to]) {
                    distances[edge.to] = newDist;
                    List<Integer> newPath = new ArrayList<>(currentInfo.path);
                    newPath.add(edge.to);
                    pq.offer(new DistanceInfo(newDist, newPath));
                }
            }
        }

        return null; // end까지 도달 불가한 경우
    }

    static class DistanceInfo implements Comparable<DistanceInfo> {
        int distance;
        List<Integer> path;

        public DistanceInfo(int distance, List<Integer> path) {
            this.distance = distance;
            this.path = path;
        }

        @Override
        public int compareTo(DistanceInfo other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    static class Edge {
        int from;
        int to;
        int distance;

        public Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }
}
