import java.io.*;
import java.util.*;


/**
 * boj 20058 마법사 상어와 파이어스톰
 * 시뮬레이션
 */

//2 1
//1 2 3 4
//8 7 6 5
//1 2 3 4
//8 7 6 5
//2


public class Main {

    private static int N, Q;
    private static int[][] arr;
    private static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2, N);
        arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            simulate(Integer.parseInt(st.nextToken()), size);
        }

        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum += arr[i][j];
            }
        }

        if (sum == 0) {
            System.out.println(0);
            System.out.println(0);
        } else {
            boolean[][] visited = new boolean[size][size];
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (arr[i][j] != 0 && !visited[i][j]) {
                        max = Math.max(max, bfs(i, j, visited));
                    }
                }
            }

            System.out.println(sum);
            System.out.println(max);
        }
    }

    private static int bfs(int x, int y, boolean[][] visited) {

        visited[x][y] = true;
        int sum = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < directions.length; i++) {
                int nx = current[0] + directions[i][0];
                int ny = current[1] + directions[i][1];

                if (nx < 0 || ny < 0 || nx == visited.length || ny == visited.length) continue;
                if (arr[nx][ny] > 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    sum++;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return sum;
    }

    private static void simulate(int n, int totalSize) {
        int size = (int) Math.pow(2, n);
        for (int x = 0; x < totalSize; x += size) {
            for (int y = 0; y < totalSize; y += size) {
                turn(x, y, size);
            }
        }

        List<int[]> removed = new ArrayList<>();

        for (int i = 0; i < totalSize; i++) {
            for (int j = 0; j < totalSize; j++) {

                if (arr[i][j] == 0) continue;

                int total = 0;
                for (int k = 0; k < directions.length; k++) {
                    int nx = i + directions[k][0];
                    int ny = j + directions[k][1];

                    if (nx < 0 || ny < 0 || nx == totalSize || ny == totalSize) {
                        continue;
                    }
                    if (arr[nx][ny] > 0) {
                        total++;
                    }
                }

                if (total < 3) {
                    removed.add(new int[]{i, j});
                }
            }
        }

        for (int[] remove : removed) {
            arr[remove[0]][remove[1]] -= 1;
        }
    }

    private static void turn(int x, int y, int size) {
        int[][] tmp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmp[j][size - 1 - i] = arr[x + i][y + j];
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[x + i][y + j] = tmp[i][j];
            }
        }
    }

}



