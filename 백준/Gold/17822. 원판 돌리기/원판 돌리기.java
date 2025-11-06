import java.io.*;
import java.util.*;


/**
 * boj 17822 원판 돌리기
 * 시뮬레이션
 */

public class Main {

    static int N, M, T;
    static int[][] arr;
    static final int EMPTY = Integer.MAX_VALUE;
    static final int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            rotate(x, d, k);

            Set<Point> remove = new HashSet<>();

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] == EMPTY) continue;

                    for (int[] dd : dir) {
                        int nx = i + dd[0];
                        int ny = (j + dd[1] + M) % M;
                        if (nx < 1 || nx > N) continue;

                        if (arr[nx][ny] == arr[i][j]) {
                            remove.add(new Point(i, j));
                            remove.add(new Point(nx, ny));
                        }
                    }
                }
            }

            if (!remove.isEmpty()) {
                for (Point p : remove) {
                    arr[p.x][p.y] = EMPTY;
                }
            } else {
                adjustByAverage();
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] != EMPTY) answer += arr[i][j];
            }
        }

        System.out.println(answer);
    }

    static void rotate(int x, int d, int k) {
        for (int i = 1; i <= N; i++) {
            if (i % x != 0) continue;

            int[] tmp = new int[M];
            for (int j = 0; j < M; j++) {
                if (d == 0) { // 시계 방향
                    tmp[(j + k) % M] = arr[i][j];
                } else { // 반시계 방향
                    tmp[(j + M - k) % M] = arr[i][j];
                }
            }
            arr[i] = tmp;
        }
    }

    static void adjustByAverage() {
        double sum = 0;
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] != EMPTY) {
                    sum += arr[i][j];
                    cnt++;
                }
            }
        }

        if (cnt == 0) return;

        double avg = sum / cnt;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == EMPTY) continue;
                if (arr[i][j] > avg) arr[i][j]--;
                else if (arr[i][j] < avg) arr[i][j]++;
            }
        }
    }
}
