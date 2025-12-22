import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 16947 서울 지하철 2호선
 */


public class Main {

    static int N;
    static List<int[]>[] edges;
    static Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edges = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edges[u].add(new int[]{v, i});
            edges[v].add(new int[]{u, i});
        }

        for (int i = 1; i <= N; i++) {
            if (dfs(i, i, new boolean[N + 1])) {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        int[] answer = bfs();

        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb);
    }

    static int[] bfs() {
        int[] distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();

        for (int v : visited) {
            distance[v] = 0;
            queue.add(v);
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int[] next : edges[current]) {
                if (distance[next[0]] > distance[current] + 1) {
                    distance[next[0]] = distance[current] + 1;
                    queue.add(next[0]);
                }
            }
        }

        return distance;
    }

    static boolean dfs(int start, int c, boolean[] used) {
        visited.add(c);

        for (int[] next : edges[c]) {
            if (!used[next[1]]) {
                if (start == next[0]) return true;

                used[next[1]] = true;
                if (dfs(start, next[0], used)) return true;
                used[next[1]] = false;
            }
        }

        visited.remove(c);
        return false;
    }
}