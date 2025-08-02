import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * BOJ 1938
 * 빡구현
 */

/*
반례 생각
6
BBB000
0001E0
0000E0
0001E0
000000
000000
 */

public class Main {

    private static int N;
    private static char[][] arr;
    private static int[] dx = {1, -1 ,0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int firstState = 0; // 통나무 상태 0 가로, 1 세로
        int targetState = 0;
        int[] first = new int[2]; // 현재 통나무 중심 좌표
        int[] target = new int[2]; // 타깃 중심 좌표

        // 초기 통나무 위치, 타깃 위치 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (i > 0 && i < N-1) {
                    if (arr[i][j] == 'B') {
                        if (arr[i - 1][j] == arr[i + 1][j] && arr[i - 1][j] == 'B') {
                            firstState = 1;
                            first[0] = i;
                            first[1] = j;
                        }
                    }
                }

                if (j > 0 && j < N-1) {
                    if (arr[i][j] == 'B') {
                        if (arr[i][j - 1] == arr[i][j + 1] && arr[i][j - 1] == 'B') {
                            firstState = 0;
                            first[0] = i;
                            first[1] = j;
                        }
                    }
                }

                if (i > 0 && i < N-1) {
                    if (arr[i][j] == 'E') {
                        if (arr[i - 1][j] == arr[i + 1][j] && arr[i - 1][j] == 'E') {
                            targetState = 1;
                            target[0] = i;
                            target[1] = j;
                        }
                    }
                }

                if (j > 0 && j < N-1) {
                    if (arr[i][j] == 'E') {
                        if (arr[i][j - 1] == arr[i][j + 1] && arr[i][j - 1] == 'E') {
                            targetState = 0;
                            target[0] = i;
                            target[1] = j;
                        }
                    }
                }
            }
        }

        int[][][] distance = new int[2][N][N];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }

        distance[firstState][first[0]][first[1]] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return distance[a[0]][a[1]][a[2]] - distance[b[0]][b[1]][b[2]];
        }); // index 0 : state, 1 : x, 2 : y

        pq.add(new int[]{firstState, first[0], first[1]});

        while(!pq.isEmpty()) {
            int[] current = pq.poll();

            if (current[0] == targetState && current[1] == target[0] && current[2] == target[1]) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = current[1] + dx[i];
                int ny = current[2] + dy[i];
                if (current[0] == 0) { // 통나무 가로일 때
                    if (nx < 0 || ny <= 0 || nx > N-1 || ny >= N-1) continue;
                    if (arr[nx][ny-1] == '1' || arr[nx][ny+1] == '1' || arr[nx][ny] == '1') continue;

                    if (distance[current[0]][nx][ny] > distance[current[0]][current[1]][current[2]] + 1) {
                        distance[current[0]][nx][ny] = distance[current[0]][current[1]][current[2]] + 1;
                        pq.add(new int[]{current[0], nx, ny});
                    }
                } else { // 통나무 세로일 때
                    if (nx <= 0 || ny < 0 || nx >= N-1 || ny > N-1) continue;
                    if (arr[nx-1][ny] == '1' || arr[nx+1][ny] == '1' || arr[nx][ny] == '1') continue;

                    if (distance[current[0]][nx][ny] > distance[current[0]][current[1]][current[2]] + 1) {
                        distance[current[0]][nx][ny] = distance[current[0]][current[1]][current[2]] + 1;
                        pq.add(new int[]{current[0], nx, ny});
                    }
                }
            }

            if (isRotatable(current[1], current[2])) {
                int nextState = (current[0] + 1) % 2;
                if (distance[nextState][current[1]][current[2]] > distance[current[0]][current[1]][current[2]] + 1) {
                    distance[nextState][current[1]][current[2]] = distance[current[0]][current[1]][current[2]] + 1;
                    pq.add(new int[]{nextState, current[1], current[2]});
                }
            }
        }

        int answer = distance[targetState][target[0]][target[1]] == Integer.MAX_VALUE ? 0 : distance[targetState][target[0]][target[1]];
        System.out.println(answer);
    }

    private static boolean isRotatable(int x, int y) {
        if (0 == x || 0 == y || x == N-1 || y == N-1) return false;

        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if (arr[i][j] == '1') return false;
            }
        }

        return true;
    }

}
