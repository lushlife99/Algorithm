import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ 1726
 * 구현
 */


public class Main {

    static int N;
    static int M;
    static int[][] arr;
    static int[] dx = {0, 0, 1, -1}; // 동 서 남 북
    static int[] dy = {1, -1, 0, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i-1][j-1] = Integer.parseInt(st.nextToken());
            }
        }

        int[] first = new int[3];
        int[] target = new int[3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            first[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            target[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int[][][] distance = new int[N][M][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return distance[a[0]][a[1]][a[2]] - distance[b[0]][b[1]][b[2]];
        });

        pq.add(new int[]{first[0], first[1], first[2]});
        distance[first[0]][first[1]][first[2]] = 0;

        while(!pq.isEmpty()) {
            int[] current = pq.poll();

            if (current[0] == target[0] && current[1] == target[1] && current[2] == target[2]) {
                break;
            }

            if (current[2] == 2 || current[2] == 3) { // 북, 남일 때 회전
                if (distance[current[0]][current[1]][0] > distance[current[0]][current[1]][current[2]] + 1) {
                    distance[current[0]][current[1]][0] = distance[current[0]][current[1]][current[2]] + 1;
                    pq.add(new int[]{current[0], current[1], 0});
                }

                if (distance[current[0]][current[1]][1] > distance[current[0]][current[1]][current[2]] + 1) {
                    distance[current[0]][current[1]][1] = distance[current[0]][current[1]][current[2]] + 1;
                    pq.add(new int[]{current[0], current[1], 1});
                }
            }

            if (current[2] == 0 || current[2] == 1) { // 동, 서일 때 회전
                if (distance[current[0]][current[1]][2] > distance[current[0]][current[1]][current[2]] + 1) {
                    distance[current[0]][current[1]][2] = distance[current[0]][current[1]][current[2]] + 1;
                    pq.add(new int[]{current[0], current[1], 2});
                }

                if (distance[current[0]][current[1]][3] > distance[current[0]][current[1]][current[2]] + 1) {
                    distance[current[0]][current[1]][3] = distance[current[0]][current[1]][current[2]] + 1;
                    pq.add(new int[]{current[0], current[1], 3});
                }
            }

            // 현재 방향으로 진행
            for (int i = 1; i <= 3; i++) {
                int nx = current[0] + dx[current[2]] * i;
                int ny = current[1] + dy[current[2]] * i;

                if (0 > nx || 0 > ny || nx >= N || ny >= M) break;
                if (arr[nx][ny] == 1) break;

                if (distance[nx][ny][current[2]] > distance[current[0]][current[1]][current[2]] + 1) {
                    distance[nx][ny][current[2]] = distance[current[0]][current[1]][current[2]] + 1;
                    pq.add(new int[]{nx, ny, current[2]});
                }
            }
        }

        System.out.println(distance[target[0]][target[1]][target[2]]);
    }

}
