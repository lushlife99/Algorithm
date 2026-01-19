import java.io.*;
import java.util.*;

/**
 * boj 16928 뱀과 사다리 게임
 *
 */

public class Main {

    static int N, M;
    static Map<Integer, Integer> ladder = new HashMap<>();
    static Map<Integer, Integer> snake = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            ladder.put(key, value);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            snake.put(key, value);
        }

        Queue<int[]> queue = new PriorityQueue<>((n1, n2) -> {
            return n1[0] - n2[0];
        });
        queue.add(new int[]{0, 1});
        int[] distance = new int[101];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        while (!queue.isEmpty()) {
            int[] c = queue.poll();
            if (c[0] == 100) break;

            for (int cnt = 1; cnt <= 6; cnt++) {
                int next = c[1] + cnt;
                if (next > 100) break;

                if (ladder.containsKey(next)) {
                    next = ladder.get(next);
                } else if (snake.containsKey(next)) {
                    next = snake.get(next);
                }

                if (distance[next] > c[0] + 1) {
                    distance[next] = c[0] + 1;
                    queue.add(new int[]{c[0] + 1, next});
                }
            }
        }

        System.out.println(distance[100]);
    }
}
