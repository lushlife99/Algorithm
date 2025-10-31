import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * boj 14442 벽 부수고 이동하기 2
 * <p>
 * 0 -> 이동 가능, 1 -> 벽
 * 1,1 -> N,M 최단 경로로 이동
 * 벽을 K번 부술 수 있음
 *
 * 알고리즘 - 다익스트라
 */

/**
 *
 * 반례 생각하기
 * 1. 벽을 부수고 온 Current에서, 벽이 없는 Prev의 distance값이 변경
 * 2. 벽을 부수고 가서 최단 거리가 변경되었지만, 최단 거리를 변경한 Current는 답이 아닌 상황
 */

/**
 * 반례 1
 * (0,0) -> (0,1) 벽 부숨
 * (0,1) -> (0,0) 다시 감. Current x = distance[0][0][1] = 2
 * x는 어차피 최단거리가 안됨.
 */

//6 4 2
//0100
//1110
//1000
//0000
//0111
//0000


/**
 * 반례 2
 * 7 4 2
 * 0000
 * 1110
 * 0000
 * 0111
 * 1111
 * 1111
 * 0000
 *
 * 답 - 16
 * 오답 - 1
 */


public class Main {

    static class Current {
        int x, y, cnt;

        public Current(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    private static int N, M, K;
    private static char[][] arr;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. input
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }


        // 2. 다익스트라
        int[][][] distance = new int[N][M][K+1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<Current> queue = new LinkedList<>();
        queue.add(new Current(0, 0, 0));
        distance[0][0][0] = 1;

        if (N == 1 && M == 1) {
            System.out.println(1);
            return;
        }

        while (!queue.isEmpty()) {
            Current current = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx < 0 || nx == N || ny < 0 || ny == M) continue;

                // 벽이 아닌 경우
                if (arr[nx][ny] == '0') {
                    if (distance[nx][ny][current.cnt] > distance[current.x][current.y][current.cnt] + 1) {
                        distance[nx][ny][current.cnt] = distance[current.x][current.y][current.cnt] + 1;

                        if (!(nx == N - 1 && ny == M - 1)) {
                            queue.offer(new Current(nx, ny, current.cnt));
                        } else {
                            System.out.println(distance[current.x][current.y][current.cnt] + 1);
                            return;
                        }

                    }
                } else { // 벽이 있는 경우
                    if (current.cnt == K) continue;

                    if (distance[nx][ny][current.cnt+1] > distance[current.x][current.y][current.cnt] + 1) {
                        distance[nx][ny][current.cnt+1] = distance[current.x][current.y][current.cnt] + 1;

                        if (!(nx == N - 1 && ny == M - 1)) {
                            queue.offer(new Current(nx, ny, current.cnt + 1));
                        } else {
                            System.out.println(distance[current.x][current.y][current.cnt] + 1);
                            return;
                        }
                    }
                }

//                System.out.println(nx + " " + ny + " " + distance[nx][ny][current.cnt]);
            }
        }

        System.out.println(-1);
    }

}

