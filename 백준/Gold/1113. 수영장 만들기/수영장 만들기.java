import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 1113 수영장 만들기
 */


public class Main {

    private static int N, M;
    private static int[][] arr;

    private static int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int answer = 0;

        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new PriorityQueue<>((a1, a2) -> {
            return arr[a1[0]][a1[1]] - arr[a2[0]][a2[1]];
        });
        for(int r = 0; r < N; r++) {
            queue.add(new int[]{r,0});
            visited[r][0] = true;
            queue.add(new int[]{r,M-1});
            visited[r][M-1] = true;
        }

        for (int c = 0; c < M; c++) {
            queue.add(new int[]{0, c});
            visited[0][c] = true;
            queue.add(new int[]{N-1, c});
            visited[N-1][c] = true;
        }
        
        while(!queue.isEmpty()) {
            int[] c = queue.poll();

            for (int i = 0; i < directions.length; i++) {
                int nx = c[0] + directions[i][0];
                int ny = c[1] + directions[i][1];

                if (nx < 0 || ny < 0 || nx == N || ny == M) continue;
                if (visited[nx][ny]) continue;
                visited[nx][ny] = true;

                if (arr[c[0]][c[1]] > arr[nx][ny]) {
                    answer += arr[c[0]][c[1]] - arr[nx][ny];
                    arr[nx][ny] = arr[c[0]][c[1]];
                }

                queue.add(new int[]{nx, ny});
            }
        }

        return answer;
    }
}
