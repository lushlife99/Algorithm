import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 11376 열혈강호 2
 */

public class Main {

    private static int N, M;
    private static List<Integer>[] edges;
    private static int[] connected;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            for (int j = 0; j < n; j++) {
                edges[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        connected = new int[M+1];

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 2; j++) {
                visited = new boolean[M + 1];
                if(dfs(i)) answer++;
            }
        }

        System.out.println(answer);

    }

    private static boolean dfs(int x) {

        for (int i = 0; i < edges[x].size(); i++) {
            int p = edges[x].get(i);

            if (visited[p]) continue;
            visited[p] = true;

            if (connected[p] == 0 || dfs(connected[p])) {
                connected[p] = x;
                return true;
            }
        }

        return false;
    }
}
