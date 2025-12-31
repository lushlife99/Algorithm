import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 24042 횡단보도
 * 그래프
 */


public class Main {

    static int N, M;
    static List<int[]>[] edges;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edges[u].add(new int[]{v, i});
            edges[v].add(new int[]{u, i});
        }

        System.out.println(bfs());
    }

    static long bfs() {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));

        dist[1] = 0;
        pq.add(new long[]{1, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int now = (int) cur[0];
            long time = cur[1];

            if (dist[now] < time) continue;

            for (int[] e : edges[now]) {
                int next = e[0];
                long nextTime = getNextTime(e[1], time);

                if (dist[next] > nextTime) {
                    dist[next] = nextTime;
                    pq.add(new long[]{next, nextTime});
                }
            }
        }
        return dist[N];
    }

    static long getNextTime(int idx, long cur) {
        if (cur <= idx) return idx + 1;

        long k = (cur - idx + M - 1) / M;
        return idx + k * M + 1;
    }
}
