import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * arr
 * 1 = 치즈
 * 0 = 외부
 * -1 =
 */
public class Main {

    static int N;
    static int M;
    static int[][] arr;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        arr = new int[N][M];
        visited = new boolean[N][M];
        int answer = 0;
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(split[j]);
                if (arr[i][j] == 1) cnt++;
            }
        }

        bfs(0, 0);
        while(cnt > 0) {
            answer++;
            List<int[]> removes = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == 1) {
                        int cnt2 = 0;
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (0 > nx || 0 > ny || nx >= N || ny >= M) continue;

                            if (visited[nx][ny]) cnt2++;
                        }

                        if (cnt2 > 1) {
                            removes.add(new int[]{i, j});
                        }
                    }
                }
            }

            for (int[] remove : removes) {
                cnt--;
                arr[remove[0]][remove[1]] = 0;
                visited[remove[0]][remove[1]] = true;
                bfs(remove[0], remove[1]);
            }
        }

        System.out.println(answer);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while(!queue.isEmpty()) {
            int[] c = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = c[0] + dx[i];
                int ny = c[1] + dy[i];

                if (0 > nx || 0 > ny || nx >= N || ny >= M) continue;

                if (arr[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

}
