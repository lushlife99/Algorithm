import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 17835 면접보는 승범이네
 *
 */


public class Main {

    static int N, M, K;
    static List<int[]>[] edges;
    static Queue<Integer> queue = new PriorityQueue<>();
    static long[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        distance = new long[N+1];
        Arrays.fill(distance, Long.MAX_VALUE);

        edges = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[v].add(new int[]{u, c});
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int t = Integer.parseInt(st.nextToken());
            queue.add(t);
            distance[t] = 0;
        }

        bfs();

        long[] answer = new long[]{N, distance[N]};
        for (int i = 1; i <= N; i++) {
            if (distance[i] > answer[1]) {
                answer[0] = i;
                answer[1] = distance[i];
            }
        }

        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    static void bfs() {
        while(!queue.isEmpty()) {
            int current = queue.poll();

            for (int[] e : edges[current]) {
                int next = e[0];
                int cost = e[1];

                if (distance[next] > distance[current] + cost) {
                    distance[next] = distance[current] + cost;
                    queue.add(next);
                }
            }
        }
    }

}