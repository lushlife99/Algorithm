import java.io.*;
import java.util.*;

/**
 * boj 17071 숨바꼭질 5
 */


public class Main {
    private static final int MAX = 500000;
    private static int[][] visited = new int[MAX + 1][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i <= MAX; i++) {
            visited[i][0] = visited[i][1] = -1;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{N, 0});
        visited[N][0] = 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int currentPos = current[0];
            int time = current[1];
            int nextTime = time + 1;
            int nextParity = nextTime % 2;

            int broPos = K + nextTime * (nextTime + 1) / 2;
            if (broPos > MAX) break;

            int[] nextMoves = {currentPos - 1, currentPos + 1, currentPos * 2};

            for (int nextPos : nextMoves) {
                if (nextPos >= 0 && nextPos <= MAX) {
                    if (visited[nextPos][nextParity] == -1) {
                        visited[nextPos][nextParity] = nextTime;
                        q.add(new int[]{nextPos, nextTime});
                    }
                }
            }
        }

        int result = -1;
        for (int time = 1; ; time++) {
            int broPos = K + time * (time + 1) / 2;

            if (broPos > MAX) break;

            int parity = time % 2;
            int minTime = visited[broPos][parity];

            if (minTime != -1 && minTime <= time) {
                result = time;
                break;
            }
        }

        System.out.println(result);
    }
}