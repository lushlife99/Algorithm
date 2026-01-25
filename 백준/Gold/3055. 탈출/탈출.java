import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * boj 3055 탈출
 */

public class Main {

    static int R, C;
    static char[][] board;
    static int[][] water;
    static int[] start, end;

    static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'S') {
                    start = new int[]{i, j};
                } else if (board[i][j] == 'D') {
                    end = new int[]{i, j};
                }
            }
        }

        water = new int[R][C];
        for (int[] row : water) Arrays.fill(row, Integer.MAX_VALUE);

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == '*') {
                    fillWater(i, j);
                }
            }
        }

        int answer = bfs();
        if (answer == Integer.MAX_VALUE) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(answer);
        }
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        int[][] distance = new int[R][C];
        for (int[] row : distance) Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            int[] c = queue.poll();
            if (c[0] == end[0] && c[1] == end[1]) break;
            for (int i = 0; i < directions.length; i++) {
                int nx = c[0] + directions[i][0];
                int ny = c[1] + directions[i][1];
                int curD = distance[c[0]][c[1]];

                if (nx < 0 || ny < 0 || nx == R || ny == C) continue;
                if (distance[nx][ny] <= curD + 1) continue;
                if (board[nx][ny] == 'X') continue;
                if (water[nx][ny] <= curD + 1) continue;

                queue.add(new int[]{nx, ny});
                distance[nx][ny] = curD + 1;
            }
        }

        return distance[end[0]][end[1]];
    }

    static void fillWater(int x, int y) {
        water[x][y] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] c = queue.poll();
            for (int i = 0; i < directions.length; i++) {
                int nx = c[0] + directions[i][0];
                int ny = c[1] + directions[i][1];

                if (nx < 0 || ny < 0 || nx == R || ny == C) continue;
                if (board[nx][ny] != '.') continue;
                if (water[nx][ny] > water[c[0]][c[1]] + 1) {
                    water[nx][ny] = water[c[0]][c[1]] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}
