import java.io.*;
import java.util.*;

/**
 * boj 4991 로봇 청소기
 * dp, 비트마스크
 */


public class Main {

    static int w,h;
    static char[][] arr;
    static int[][] distance;
    static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while ((s = br.readLine()) != null) {
            st = new StringTokenizer(s);
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;
            arr = new char[h][w];
            List<int[]> destinations = new ArrayList<>();

            for (int i = 0; i < h; i++) {
                arr[i] = br.readLine().toCharArray();

                for (int j = 0; j < w; j++) {
                    if (arr[i][j] == 'o') {
                        destinations.add(0, new int[]{i, j});
                    }

                    else if (arr[i][j] == '*') {
                        destinations.add(new int[]{i, j});
                    }
                }
            }

            if (!setDistance(destinations)) {
                sb.append(-1).append("\n");
                continue;
            }

            sb.append(traversal(destinations.size())).append("\n");
        }

        System.out.print(sb);
    }

    private static int traversal(int n) {

        if (n == 1) return 0;

        int cnt = n-1;
        int max = 1 << cnt;

        int[][] dp = new int[max][cnt];
        for (int[] row : dp) Arrays.fill(row, 1_000_000_000);

        for (int i = 0; i < cnt; i++) {
            dp[1<<i][i] = distance[0][i+1];
        }

        for (int mask = 0; mask < max; mask++) {
            for (int i = 0; i < cnt; i++) {
                if ((mask & (1 << i)) == 0) continue;
                for (int j = 0; j < cnt; j++) {
                    if ((mask & (1 << j)) != 0) continue;

                    int next = mask | (1 << j);
                    dp[next][j] = Math.min(dp[next][j], dp[mask][i] + distance[i+1][j+1]);
                }
            }
        }

        return Arrays.stream(dp[max-1]).min().getAsInt();
    }

    private static boolean setDistance(List<int[]> destinations) {
        distance = new int[destinations.size()][destinations.size()];

        for (int i = 0; i < destinations.size(); i++) {
            int[] start = destinations.get(i);
            int[][] d = bfs(start);

            for (int j = 0; j < destinations.size(); j++) {
                int[] next = destinations.get(j);
                distance[i][j] = d[next[0]][next[1]];
                if (distance[i][j] == Integer.MAX_VALUE) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int[][] bfs(int[] start) {
        int[][] distance = new int[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int[] c = queue.poll();

            for (int i = 0; i < directions.length; i++) {
                int nx = c[0] + directions[i][0];
                int ny = c[1] + directions[i][1];

                if (nx < 0 || ny < 0 || nx == h || ny == w) continue;
                if (arr[nx][ny] == 'x') continue;
                if (distance[nx][ny] > distance[c[0]][c[1]] + 1) {
                    distance[nx][ny] = distance[c[0]][c[1]] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }

        }

        return distance;
    }

}

