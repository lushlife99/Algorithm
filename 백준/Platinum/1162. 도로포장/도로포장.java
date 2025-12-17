import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 1162 도로포장
 * 다익스트라
 */


public class Main {

    static class State {
        int node, pave;
        long dist;
        State(int node, int pave, long dist) {
            this.node = node;
            this.pave = pave;
            this.dist = dist;
        }
    }

    static int N,M,K;
    static List<int[]>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        edges = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        long[][] distance = new long[N+1][K+1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(distance[i], Long.MAX_VALUE);
        }
        distance[1][0] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[s].add(new int[]{e, c});
            edges[e].add(new int[]{s, c});
        }

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.dist));
        pq.add(new State(1, 0, 0));
        distance[1][0] = 0;

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (distance[cur.node][cur.pave] < cur.dist) continue;

            for (int[] e : edges[cur.node]) {
                int next = e[0];
                int cost = e[1];

                // 포장 안 함
                if (distance[next][cur.pave] > cur.dist + cost) {
                    distance[next][cur.pave] = cur.dist + cost;
                    pq.add(new State(next, cur.pave, distance[next][cur.pave]));
                }

                // 포장 함
                if (cur.pave < K && distance[next][cur.pave + 1] > cur.dist) {
                    distance[next][cur.pave + 1] = cur.dist;
                    pq.add(new State(next, cur.pave + 1, cur.dist));
                }
            }
        }

        System.out.println(Arrays.stream(distance[N]).min().getAsLong());
    }
}

