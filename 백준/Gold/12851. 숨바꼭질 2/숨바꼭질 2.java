import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 숨바꼭질 2
 */

public class Main {

    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] answer = bfs();
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    static int[] bfs() {
        int[] visited = new int[100_001];
        int[] count = new int[100_001];
        Arrays.fill(visited, -1);
        visited[N] = 0;
        count[N] = 1;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            int[] nextX = new int[]{current+1, current-1, current*2};
            for (int next : nextX) {
                if (next < 0 || next > 100_000) continue;

                if (visited[next] == -1) {
                    visited[next] = visited[current] + 1;
                    count[next] = count[current];
                    queue.add(next);
                } else if (visited[next] == visited[current] + 1) {
                    count[next] += count[current];
                }
            }
        }

        return new int[]{visited[K], count[K]};
    }
}
