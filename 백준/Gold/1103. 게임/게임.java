import java.io.*;
import java.util.Arrays;

/**
 * BOJ 1103
 *
 * dp[N][M] = N,M에서 최대 움직일 수 있는 횟수
 * dp[N][M] = max(dp[nextX][nextY]) + 1
 */

public class Main {

    static int N;
    static int M;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static char[][] arr;
    static int[][] dp; // direction 0 = 위, 1 = 아래, 2 = 왼, 3 = 오
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        
        dp = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        int res = dfs(0, 0);
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    /**
     * return 값
     * 0 = H
     * 1 = 경기장 나감
     * INF = 무한루프
     */

    private static int dfs(int x, int y) {
        if (visited[x][y]) return Integer.MAX_VALUE;   // 사이클
        if (arr[x][y] == 'H') return 0;                 // 구멍 도착
        if (dp[x][y] != -1) return dp[x][y];            // 이미 계산됨

        visited[x][y] = true;
        int jump = arr[x][y] - '0';
        int max = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * jump;
            int ny = y + dy[i] * jump;
            int cnt;
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                cnt = 1;
            } else {
                cnt = dfs(nx, ny);
                if (cnt == Integer.MAX_VALUE) {
                    max = Integer.MAX_VALUE;
                    break;
                }
                cnt += 1;
            }
            max = Math.max(max, cnt);
        }
        visited[x][y] = false;
        return dp[x][y] = max;
    }


}
