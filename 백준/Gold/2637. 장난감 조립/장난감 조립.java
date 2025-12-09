import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 2637 장난감 조립
 */


public class Main {

    private static int N, M;
    private static List<int[]>[] edges;
    private static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edges = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        int[] inDegree = new int[N+1];
        boolean[] middle = new boolean[N+1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            edges[x].add(new int[]{y, k});
            inDegree[y]++;
            middle[x] = true;
        }


        cnt = new int[N];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{N,1});

        while (!queue.isEmpty()) {
            int[] u = queue.poll();

            for (int[] v : edges[u[0]]) {
                cnt[v[0]] += v[1] * u[1];
                inDegree[v[0]]--;

                if (inDegree[v[0]] == 0) {
                    queue.add(new int[]{v[0], cnt[v[0]]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < N; i++) {
            if (!middle[i]) {
                sb.append(i).append(" ").append(cnt[i]).append("\n");
            }
        }

        System.out.print(sb);
    }
}