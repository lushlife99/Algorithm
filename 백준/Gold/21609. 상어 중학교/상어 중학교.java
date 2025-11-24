import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 21609 상어 중학교
 * 시뮬레이션, bfs
 * 44 25 19 -> 9 10
 */


public class Main {

    static class BlockGroup implements Comparable<BlockGroup> {
        int color; // 일반 블록의 색
        int cnt; // 일반 블록의 개수
        int rainbowCnt; // 무지개 블록의 개수
        int x = Integer.MAX_VALUE; // 기준 x
        int y = Integer.MAX_VALUE; // 기준 y

        public BlockGroup(int color, int cnt, int rainbowCnt, int x, int y) {
            this.color = color;
            this.cnt = cnt;
            this.rainbowCnt = rainbowCnt;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(BlockGroup blockGroup) {
            int total = cnt + rainbowCnt;
            int total2 = blockGroup.cnt + blockGroup.rainbowCnt;

            if (total != total2) return total2 - total;
            if (rainbowCnt != blockGroup.rainbowCnt) return blockGroup.rainbowCnt - rainbowCnt;

            if (x != blockGroup.x) return blockGroup.x - x;

            return blockGroup.y - y;
        }
    }

    private static int N, M;
    private static int[][] arr;
    private static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) {
            // 1. 블록 그룹 구하기
            BlockGroup blockGroup = getBiggestBlockGroup();
            if (blockGroup == null) break;

            // 2. 블록 그룹 선택하고 제거
            remove(blockGroup.x, blockGroup.y);

            int total = blockGroup.cnt + blockGroup.rainbowCnt;
            answer += total * total;


            // 3. 중력
            drop();

            // 4. 반시게 90도 회전
            leftRotate();

            // 5. 중력
            drop();
        }

        System.out.println(answer);
    }

    private static void leftRotate() {
        int[][] newArr = new int[N][N];
        // 40 = 00, 30 = 01
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newArr[N-1-j][i] = arr[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            arr[i] = newArr[i];
        }
    }

    private static void drop() {
        for (int col = 0; col < N; col++) {
            for (int row = N-1; row >= 0; row--) {
                if (arr[row][col] < 0) continue;

                int cnt = 1;
                for (; row + cnt < N; cnt++) {
                    if (arr[row+cnt][col] != -2) {
                        break;
                    }
                }

                if (cnt == 1) continue;

                arr[row+cnt-1][col] = arr[row][col];
                arr[row][col] = -2;
            }
        }
    }

    private static BlockGroup getBiggestBlockGroup() {
        boolean[][] visited = new boolean[N][N];
        List<BlockGroup> blockGroups = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (0 < arr[i][j] && !visited[i][j]) {
                    BlockGroup blockGroup = bfs(i, j, visited);
                    if (blockGroup.cnt > 0 && blockGroup.cnt + blockGroup.rainbowCnt >= 2) {
                        blockGroups.add(blockGroup);
                    }
                }
            }
        }

        if (blockGroups.size() == 0) return null;

        Collections.sort(blockGroups);
        return blockGroups.get(0);
    }

    private static void remove(int x, int y) {
        int color = arr[x][y];
        arr[x][y] = -2;
        boolean[][] visited = new boolean[N][N];
        visited[x][y] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while(!queue.isEmpty()) {
            int[] c = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = c[0] + directions[i][0];
                int ny = c[1] + directions[i][1];

                if (nx < 0 || ny < 0 || nx == N || ny == N) continue;
                if (visited[nx][ny]) continue;

                if (arr[nx][ny] == 0 || arr[nx][ny] == color) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    arr[nx][ny] = -2;
                }
            }
        }
    }

    private static BlockGroup bfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        int color = arr[x][y];
        int[] point = new int[]{x,y};
        int cnt = 0;
        int rainbowCnt = 0;

        boolean[][] rainbowVisited = new boolean[N][N];

        while(!queue.isEmpty()) {
            int[] c = queue.poll();

            if(arr[c[0]][c[1]] == 0) rainbowCnt++;
            else {
                cnt++;
                if (point[0] > c[0]) {
                    point[0] = c[0]; point[1] = c[1];
                } else if (point[0] == c[0] && point[1] > c[1]) {
                    point[1] = c[1];
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = c[0] + directions[i][0];
                int ny = c[1] + directions[i][1];

                if (nx < 0 || ny < 0 || nx == N || ny == N) continue;

                if (arr[nx][ny] == 0 && !rainbowVisited[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                    rainbowVisited[nx][ny] = true;
                    continue;
                }

                if (arr[nx][ny] == color && !visited[nx][ny]) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        return new BlockGroup(color, cnt, rainbowCnt, point[0], point[1]);
    }
}
