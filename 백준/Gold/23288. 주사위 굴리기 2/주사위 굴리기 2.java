import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    static int N, M, K;
    static int[][] arr;

    static int[] dice = {1, 6, 5, 2, 4, 3};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cd = 0;
        int cx = 0;
        int cy = 0;

        long ans = 0;

        for (int i = 0; i < K; i++) {
            int nx = cx + dx[cd];
            int ny = cy + dy[cd];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                cd = (cd + 2) % 4;
                nx = cx + dx[cd];
                ny = cy + dy[cd];
            }

            rollDice(cd);

            int cnt = bfs(nx, ny, arr[nx][ny]);
            ans += (long) cnt * arr[nx][ny];

            if (arr[nx][ny] > dice[1]) {
                cd = (cd + 1) % 4;
            } else if (arr[nx][ny] < dice[1]) {
                cd = (cd + 3) % 4;
            }


            cx = nx;
            cy = ny;
        }

        System.out.println(ans);
    }

    private static void rollDice(int direction) {
        int tempTop = dice[0];
        int tempBottom = dice[1];
        int tempFront = dice[2];
        int tempBack = dice[3];
        int tempLeft = dice[4];
        int tempRight = dice[5];

        switch (direction) {
            case 0: // 동쪽
                dice[0] = tempLeft;
                dice[1] = tempRight;
                dice[4] = tempBottom;
                dice[5] = tempTop;
                break;
            case 1: // 북쪽
                dice[0] = tempFront;
                dice[1] = tempBack;
                dice[2] = tempBottom;
                dice[3] = tempTop;
                break;
            case 2: // 서쪽
                dice[0] = tempRight;
                dice[1] = tempLeft;
                dice[4] = tempTop;
                dice[5] = tempBottom;
                break;
            case 3: // 남쪽
                dice[0] = tempBack;
                dice[1] = tempFront;
                dice[2] = tempTop;
                dice[3] = tempBottom;
                break;
        }
    }

    private static int bfs(int x, int y, int value) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();

        visited[x][y] = true;
        queue.add(new int[]{x, y});
        int ans = 1;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny]) continue;

                if (arr[nx][ny] == value) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    ans++;
                }
            }
        }
        return ans;
    }
}