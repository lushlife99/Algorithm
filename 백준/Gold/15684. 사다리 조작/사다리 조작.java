import java.io.*;
import java.util.*;


/**
 * boj 15684 사다리 조작
 */

public class Main {

    static int N, M, H;
    static boolean[][] ladder;
    static int answer = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true;
        }

        dfs(0, 1, 1);
        System.out.println(answer == 4 ? -1 : answer);
    }

    static void dfs(int cnt, int x, int y) {
        if (cnt >= answer) return;
        if (check()) {
            answer = cnt;
            return;
        }

        if (cnt == 3) return;

        for (int i = x; i <= H; i++) {
            for (int j = (i == x ? y : 1); j < N; j++) {
                if (ladder[i][j] || ladder[i][j - 1] || ladder[i][j + 1]) continue;
                ladder[i][j] = true;
                dfs(cnt + 1, i, j + 2);
                ladder[i][j] = false;
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= N; i++) {
            int pos = i;
            for (int j = 1; j <= H; j++) {
                if (ladder[j][pos]) pos++;
                else if (pos > 1 && ladder[j][pos - 1]) pos--;
            }
            if (pos != i) return false;
        }
        return true;
    }
}
