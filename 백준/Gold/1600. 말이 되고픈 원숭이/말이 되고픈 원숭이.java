
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * boj 1600 말이 되고픈 원숭이
 * 다익스트라
 */

public class Main {

    private static int K, W, H;
    private static int[][] arr;

    private static int[] monkeyDx = {1, -1, 0, 0};
    private static int[] monkeyDy = {0, 0, 1, -1};

    private static int[] horseDx = {2, 2, -2, -2, 1, -1, 1, -1};
    private static int[] horseDy = {1, -1, 1, -1, 2, 2, -2, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        arr = new int[W][H];
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < H; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int x = 0, y = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});
        int[][][] distance = new int[W][H][K+1];
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }

        distance[x][y][0] = 0;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int nk = current[2];

            for (int i = 0; i < monkeyDx.length; i++) {
                int nx = current[0] + monkeyDx[i];
                int ny = current[1] + monkeyDy[i];

                if (nx < 0 || nx >= W || ny < 0 || ny >= H) continue;
                if (arr[nx][ny] == 1) continue;

                if (distance[nx][ny][nk] > distance[current[0]][current[1]][nk] + 1) {
                    distance[nx][ny][nk] = distance[current[0]][current[1]][nk] + 1;
                    queue.add(new int[]{nx, ny, nk});
                }
            }

            if (nk < K) {
                for (int i = 0; i < horseDx.length; i++) {
                    int nx = current[0] + horseDx[i];
                    int ny = current[1] + horseDy[i];

                    if (nx < 0 || nx >= W || ny < 0 || ny >= H) continue;
                    if (arr[nx][ny] == 1) continue;

                    if (distance[nx][ny][nk+1] > distance[current[0]][current[1]][nk] + 1) {
                        distance[nx][ny][nk+1] = distance[current[0]][current[1]][nk] + 1;
                        queue.add(new int[]{nx, ny, nk+1});
                    }
                }
            }
        }
        int res = Arrays.stream(distance[W - 1][H - 1]).min().getAsInt();
        return res != Integer.MAX_VALUE ? res : -1;
    }

}

