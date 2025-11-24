import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 14226 이모티콘
 */

/**
 * 반례 - 문제 조건 상 복사, 붙여넣기가 원자적으로 이루어지지 않음
 */


public class Main {

    private static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        boolean[][] visited = new boolean[2001][2001];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 0, 0}); // size, clip, time
        visited[1][0] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int s = cur[0];
            int c = cur[1];
            int t = cur[2];

            if (s == S) {
                System.out.println(t);
                return;
            }

            // 1. copy
            if (!visited[s][s]) {
                visited[s][s] = true;
                q.add(new int[]{s, s, t+1});
            }

            // 2. paste
            if (c > 0 && s + c < 2000 && !visited[s+c][c]) {
                visited[s+c][c] = true;
                q.add(new int[]{s+c, c, t+1});
            }

            // 3. delete
            if (s > 0 && !visited[s-1][c]) {
                visited[s-1][c] = true;
                q.add(new int[]{s-1, c, t+1});
            }
        }

    }
}
