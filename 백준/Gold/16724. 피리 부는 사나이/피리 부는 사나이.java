import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static char[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        arr = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            arr[i] = s.toCharArray();
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    if (dfs(i, j, new HashSet<>())) {
                        answer++;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean dfs(int x, int y, Set<String> path) {
        visited[x][y] = true;
        String key = x + "," + y;

        if (path.contains(key)) return true;
        path.add(key);

        int nx = x, ny = y;
        char dir = arr[x][y];

        if (dir == 'D') nx++;
        else if (dir == 'U') nx--;
        else if (dir == 'L') ny--;
        else if (dir == 'R') ny++;

        if (!visited[nx][ny]) {
            return dfs(nx, ny, path);
        } else {
            return path.contains(nx + "," + ny);
        }
    }
}
