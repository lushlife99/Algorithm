import java.io.*;
import java.util.*;

/**
 * boj 18809
 * dfs, 백트래킹
 */

public class Main {

    static List<int[]> spots;
    static int N, M, G, R;
    static int[][] garden;
    static int[] greenPos, redPos;
    static boolean[] used;
    static int maxFlowers = 0;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        garden = new int[N][M];
        spots = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                garden[i][j] = Integer.parseInt(st.nextToken());
                if (garden[i][j] == 2) {
                    spots.add(new int[]{j, i});
                }
            }
        }

        used = new boolean[spots.size()];
        greenPos = new int[G];
        redPos = new int[R];
        Arrays.fill(greenPos, -1);
        Arrays.fill(redPos, -1);

        selectGreen(0);
        System.out.println(maxFlowers);
    }

    static void selectGreen(int k) {
        if (k == G) {
            selectRed(0);
            return;
        }
        for (int i = 0; i < spots.size(); i++) {
            if (used[i]) continue;
            if (k > 0 && greenPos[k - 1] > i) continue;
            used[i] = true;
            greenPos[k] = i;
            selectGreen(k + 1);
            greenPos[k] = -1;
            used[i] = false;
        }
    }

    static void selectRed(int k) {
        if (k == R) {
            bfs();
            return;
        }
        for (int i = 0; i < spots.size(); i++) {
            if (used[i]) continue;
            if (k > 0 && redPos[k - 1] > i) continue;
            used[i] = true;
            redPos[k] = i;
            selectRed(k + 1);
            redPos[k] = -1;
            used[i] = false;
        }
    }

    static void bfs() {
        int flowerCount = 0;
        int[][] dist = new int[N][M];
        char[][] state = new char[N][M];
        Queue<int[]> queue = new LinkedList<>();

        for (int g : greenPos) {
            int[] pos = spots.get(g);
            state[pos[1]][pos[0]] = 'G';
            dist[pos[1]][pos[0]] = 1;
            queue.add(new int[]{pos[0], pos[1], 0, 1});
        }

        for (int r : redPos) {
            int[] pos = spots.get(r);
            state[pos[1]][pos[0]] = 'R';
            dist[pos[1]][pos[0]] = 1;
            queue.add(new int[]{pos[0], pos[1], 1, 1});
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], type = cur[2], time = cur[3];
            char color = type == 0 ? 'G' : 'R';

            if (state[y][x] == 'F') continue;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k], ny = y + dy[k];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if (garden[ny][nx] == 0 || state[ny][nx] == 'F') continue;
                if (state[ny][nx] == color) continue;

                if ((color == 'G' && state[ny][nx] == 'R') || (color == 'R' && state[ny][nx] == 'G')) {
                    if (dist[ny][nx] == time + 1) {
                        state[ny][nx] = 'F';
                        flowerCount++;
                    }
                    continue;
                }

                state[ny][nx] = color;
                dist[ny][nx] = time + 1;
                queue.add(new int[]{nx, ny, type, time + 1});
            }
        }

        maxFlowers = Math.max(maxFlowers, flowerCount);
    }
}
