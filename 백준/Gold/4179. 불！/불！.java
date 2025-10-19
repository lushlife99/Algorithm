import java.io.*;
import java.sql.Array;
import java.util.*;

/**
 * boj 4179 불!
 * 그래프
 */

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] fireTime, jihunTime;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fireTime = new int[R][C];
        jihunTime = new int[R][C];

        Queue<int[]> fireQ = new LinkedList<>();
        Queue<int[]> jihunQ = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            Arrays.fill(fireTime[i], -1);
            Arrays.fill(jihunTime[i], -1);
        }

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == 'F') {
                    fireQ.offer(new int[]{i, j});
                    fireTime[i][j] = 0;
                }

                if (map[i][j] == 'J') {
                    jihunQ.offer(new int[]{i, j});
                    jihunTime[i][j] = 0;
                }
            }
        }

        while (!fireQ.isEmpty()) {
            int[] cur = fireQ.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if (map[nx][ny] == '#' || fireTime[nx][ny] != -1) continue;
                fireTime[nx][ny] = fireTime[cur[0]][cur[1]] + 1;
                fireQ.offer(new int[]{nx, ny});
            }
        }

        while (!jihunQ.isEmpty()) {
            int[] cur = jihunQ.poll();

            if (cur[0] == 0 || cur[0] == R - 1 || cur[1] == 0 || cur[1] == C - 1) {
                System.out.println(jihunTime[cur[0]][cur[1]] + 1);
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if (map[nx][ny] == '#' || jihunTime[nx][ny] != -1) continue;

                if (fireTime[nx][ny] != -1 && fireTime[nx][ny] <= jihunTime[cur[0]][cur[1]] + 1) continue;

                jihunTime[nx][ny] = jihunTime[cur[0]][cur[1]] + 1;
                jihunQ.offer(new int[]{nx, ny});
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}

