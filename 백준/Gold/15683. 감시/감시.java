import java.io.*;
import java.util.*;

/**
 * boj 15683 감시
 * dfs, 조합
 */

public class Main {

    private static int[][] arr;
    private static List<int[]> cctv = new ArrayList<>();
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] >= 1 && arr[i][j] <= 5) {
                    cctv.add(new int[]{i, j});
                }
            }
        }

        List<List<Integer>> combs = combine(cctv.size(), new ArrayList<>());
        int ans = Integer.MAX_VALUE;
        for (List<Integer> c : combs) {
            int res = dfs(c, 0);
            ans = Math.min(ans, res);
        }

        System.out.println(ans);
    }

    private static int dfs(List<Integer> comb, int current) {
        if (current == comb.size()) {
            int cnt = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] == 0) cnt++;
                }
            }
            return cnt;
        }

        int res = Integer.MAX_VALUE;

        int[] p = cctv.get(current);
        write(p[0], p[1], comb.get(current), 0, -1);
        res = Math.min(dfs(comb, current+1), res);
        write(p[0], p[1], comb.get(current), -1, 0);

        return res;
    }

    private static void write(int x, int y, int type, int from, int to) {
        boolean[] writable = new boolean[4];

        if (arr[x][y] == 1) {
            writable[type] = true;
        } else if (arr[x][y] == 2) {
            writable[type] = true;
            writable[(type+2) % 4] = true;
        } else if (arr[x][y] == 3) {
            writable[type] = true;
            writable[(type+1) % 4] = true;
        } else if (arr[x][y] == 4) {
            writable[type] = true;
            writable[(type+1) % 4] = true;
            writable[(type+2) % 4] = true;
        } else if (arr[x][y] == 5) {
            writable[type] = true;
            writable[(type+1) % 4] = true;
            writable[(type+2) % 4] = true;
            writable[(type+3) % 4] = true;
        }

        for (int i = 0; i < 4; i++) {
            int cx = x;
            int cy = y;

            if (!writable[i]) continue;
            while (true) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (0 > nx || nx == arr.length || 0 > ny || ny == arr[0].length) break;
                if (arr[nx][ny] == 6) break;

                if (arr[nx][ny] == from) {
                    arr[nx][ny] = to;
                }
                cx = nx;
                cy = ny;
            }

        }
    }

    private static List<List<Integer>> combine(int size, List<Integer> current) {

        List<List<Integer>> comb = new ArrayList<>();
        if (current.size() == size) {
            comb.add(new ArrayList<>(current));
            return comb;
        }

        for (int i = 0; i < 4; i++) {
            current.add(i);
            comb.addAll(combine(size, current));
            current.remove(current.size()-1);
        }

        return comb;
    }
}
