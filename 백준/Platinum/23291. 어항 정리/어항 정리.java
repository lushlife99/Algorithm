import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * boj 23291 어항 정리
 * 구현, 시뮬
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[1]);

        int MAX_COLS = 25;
        int[][] aquarium = new int[n][MAX_COLS];
        String[] fishLine = br.readLine().split(" ");
        for (int i = 0; i < n; i++) aquarium[i][0] = Integer.parseInt(fishLine[i]);

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int steps = 0;

        while (true) {
            int minFish = Integer.MAX_VALUE;
            int maxFish = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                minFish = Math.min(minFish, aquarium[i][0]);
                maxFish = Math.max(maxFish, aquarium[i][0]);
            }
            if (maxFish - minFish <= k) break;

            steps++;

            for (int i = 0; i < n; i++) {
                if (aquarium[i][0] == minFish) aquarium[i][0]++;
            }

            int startX = 0, vertical = 1, horizontal = 1;
            while (startX + vertical + horizontal <= n) {
                for (int i = vertical - 1; i >= 0; i--) {
                    for (int j = 0; j < horizontal; j++) {
                        int newX = startX + vertical + j;
                        int newY = vertical - i;
                        aquarium[newX][newY] = aquarium[startX + i][j];
                        aquarium[startX + i][j] = 0;
                    }
                }
                startX += vertical;
                if (vertical == horizontal) horizontal++;
                else vertical++;
            }
            
            int[][] delta = new int[n][MAX_COLS];
            boolean[][] visited = new boolean[n][MAX_COLS];
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < MAX_COLS; y++) {
                    visited[x][y] = true;
                    if (aquarium[x][y] == 0) continue;

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = x + dx[dir], ny = y + dy[dir];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < MAX_COLS && !visited[nx][ny] && aquarium[nx][ny] != 0) {
                            int diff = (aquarium[x][y] - aquarium[nx][ny]) / 5;
                            if (diff != 0) {
                                delta[x][y] -= diff;
                                delta[nx][ny] += diff;
                            }
                        }
                    }
                }
            }
            for (int x = 0; x < n; x++)
                for (int y = 0; y < MAX_COLS; y++)
                    aquarium[x][y] += delta[x][y];

            int[] temp = new int[n];
            int index = 0;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < MAX_COLS; j++)
                    if (aquarium[i][j] != 0) temp[index++] = aquarium[i][j];
            for (int i = 0; i < n; i++) {
                aquarium[i][0] = temp[i];
                for (int j = 1; j < MAX_COLS; j++) aquarium[i][j] = 0;
            }

            for (int i = 0; i < n / 2; i++) {
                aquarium[n - 1 - i][1] = aquarium[i][0];
                aquarium[i][0] = 0;
            }
            for (int i = 0; i < n / 4; i++) {
                for (int j = 0; j < 2; j++) {
                    aquarium[n - 1 - i][3 - j] = aquarium[n / 2 + i][j];
                    aquarium[n / 2 + i][j] = 0;
                }
            }

            delta = new int[n][MAX_COLS];
            visited = new boolean[n][MAX_COLS];
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < MAX_COLS; y++) {
                    visited[x][y] = true;
                    if (aquarium[x][y] == 0) continue;

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = x + dx[dir], ny = y + dy[dir];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < MAX_COLS && !visited[nx][ny] && aquarium[nx][ny] != 0) {
                            int diff = (aquarium[x][y] - aquarium[nx][ny]) / 5;
                            if (diff != 0) {
                                delta[x][y] -= diff;
                                delta[nx][ny] += diff;
                            }
                        }
                    }
                }
            }
            for (int x = 0; x < n; x++)
                for (int y = 0; y < MAX_COLS; y++)
                    aquarium[x][y] += delta[x][y];

            temp = new int[n];
            index = 0;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < MAX_COLS; j++)
                    if (aquarium[i][j] != 0) temp[index++] = aquarium[i][j];
            for (int i = 0; i < n; i++) {
                aquarium[i][0] = temp[i];
                for (int j = 1; j < MAX_COLS; j++) aquarium[i][j] = 0;
            }
        }

        System.out.println(steps);
    }
}
