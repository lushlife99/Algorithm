import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * boj 3109 빵집
 * 그래프, 그리디
 *
 */


public class Main {

    private static int R, C;
    private static char[][] arr;
    private static boolean[][] visited;
    private static int[] dx = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        visited = new boolean[R][C];
        int answer = 0;
        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) answer++;
        }

        System.out.println(answer);

    }

    private static boolean dfs(int x, int y) {
        if(y == C-1) return true;

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + 1;

            if (nx < 0 || nx == R || ny < 0 || ny == C) continue;

            if (!visited[nx][ny] && arr[nx][ny] == '.') {
                visited[nx][ny] = true;
                if (dfs(nx, ny)) {
                    return true;
                }
            }
        }

        return false;
    }
}

