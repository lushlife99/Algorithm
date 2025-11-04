import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 9370 미확인 도착지
 * bfs
 */

/**
 * 반례 생각
 * 최단거리가 2개 이상
 */

//1
//7 7 2
//2 1 3
//2 4 1
//4 3 1
//2 1 1
//1 3 1
//3 5 2
//5 6 5
//6 7 11
//7
//6

public class Main {

    static class Edge {

        private static int size = 0;

        int id,n1,n2,cost;
        public Edge(int n1, int n2, int cost) {
            this.n1 = n1; this.n2 = n2; this.cost = cost;
            this.id = ++size;
        }
    }

    private static int T;
    private static int n,m,t;
    private static int s,g,h;
    private static List<Edge>[] road;
    private static int[] destinations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            road = IntStream.rangeClosed(0, n)
                    .mapToObj(i -> new ArrayList<>())
                    .toArray(List[]::new);

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                Edge edge = new Edge(n1, n2, cost);
                road[n1].add(edge);
                road[n2].add(edge);
            }

            destinations = new int[t];
            for (int i = 0; i < t; i++) {
                destinations[i] = Integer.parseInt(br.readLine());
            }

            int[] sDistance = bfs(s);
            int[] gDistance = bfs(g);
            int[] hDistance = bfs(h);
            Arrays.sort(destinations);

            for (int dest : destinations) {
                int d1 = sDistance[g] + gDistance[h] + hDistance[dest];
                int d2 = sDistance[h] + hDistance[g] + gDistance[dest];
                if (d1 == sDistance[dest] || d2 == sDistance[dest]) {
                    sb.append(dest + " ");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int[] bfs(int node) {
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[node] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for (Edge e : road[current]) {
                int next = e.n1 != current ? e.n1 : e.n2;

                if (distance[next] > distance[current] + e.cost) {
                    distance[next] = distance[current] + e.cost;
                    queue.add(next);
                }
            }
        }

        return distance;
    }
}
