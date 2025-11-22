import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 5719 거의 최단 경로
 * 다익스트라
 */

/**
 * 반례
 * 중간 경유지에 대해 다양한 최단 경로가 존재하는 경우
 */

//8 11
//0 7
//0 1 1
//0 2 1
//0 3 2
//0 4 3
//1 5 2
//2 6 4
//3 6 2
//4 6 4
//5 6 1
//6 7 1
//4 7 11
//0 0

public class Main {

    static class Edge {
        int id, s, e, c;
        boolean removed = false;

        public Edge(int id, int s, int e, int c) {
            this.id = id;
            this.s = s;
            this.e = e;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            Edge oE = (Edge) o;
            return this.id == oE.id;
        }

        @Override
        public int hashCode() {
            return this.id;
        }
    }

    static class Current implements Comparable<Current> {
        int c;
        int d = 0;

        public Current(int c) {
            this.c = c;
            this.d = 0;
        }

        public Current(int c, int d) {
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Current c) {
            return this.d - c.d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            List<Edge>[] edges = IntStream.range(0, N).mapToObj(i -> new ArrayList<>()).toArray(List[]::new); // 정방향: s -> e
            List<Edge>[] revEdges = IntStream.range(0, N).mapToObj(i -> new ArrayList<>()).toArray(List[]::new); // 역방향: e -> s

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                Edge forward = new Edge(i, s, e, c);
                edges[s].add(forward);

                Edge backward = new Edge(i, e, s, c);
                revEdges[e].add(backward);
            }

            // 1. 다익스트라
            int[] distance = new int[N];


            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[start] = 0;

            Queue<Current> queue = new PriorityQueue<>();
            queue.add(new Current(start));

            while (!queue.isEmpty()) {
                Current current = queue.poll();
                if (current.d > distance[current.c]) continue;

                for (Edge e : edges[current.c]) {
                    int n = e.e;

                    if (distance[n] > current.d + e.c) {
                        distance[n] = current.d + e.c;
                        queue.add(new Current(n, current.d + e.c));
                    }
                }
            }

            Queue<Integer> q = new LinkedList<>();
            q.add(end);
            boolean[] visited = new boolean[N];
            visited[end] = true;

            while (!q.isEmpty()) {
                int v = q.poll();

                for (Edge revE : revEdges[v]) {
                    int u = revE.e;
                    int cost = revE.c;

                    if (distance[u] != Integer.MAX_VALUE && distance[u] + cost == distance[v]) {
                        for (Edge forwardE : edges[u]) {
                            if (forwardE.id == revE.id) {
                                forwardE.removed = true;
                                break;
                            }
                        }

                        if (!visited[u]) {
                            visited[u] = true;
                            q.add(u);
                        }
                    }
                }
            }

            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[start] = 0;
            queue.clear();
            queue.add(new Current(start));

            while (!queue.isEmpty()) {
                Current current = queue.poll();
                if (current.d > distance[current.c]) continue;

                for (Edge e : edges[current.c]) {
                    int n = e.e;
                    if (e.removed) continue;

                    if (distance[n] > current.d + e.c) {
                        distance[n] = current.d + e.c;
                        queue.add(new Current(n, current.d + e.c));
                    }
                }
            }

            int answer = distance[end] == Integer.MAX_VALUE ? -1 : distance[end];
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

}
