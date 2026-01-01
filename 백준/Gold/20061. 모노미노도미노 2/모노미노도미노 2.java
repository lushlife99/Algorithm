import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 20061 모노미노도미노 2
 * 시뮬레이션
 */


public class Main {

    static boolean[][] green = new boolean[6][4];
    static boolean[][] blue = new boolean[4][6];
    static int score = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            dropGreen(t, y);
            dropBlue(t, x);
        }

        System.out.println(score);
        System.out.println(countBlocks());
    }

    static void dropGreen(int t, int y) {
        int x = 0;

        if (t == 1) {
            while (x + 1 < 6 && !green[x + 1][y]) x++;
            green[x][y] = true;
        }
        else if (t == 2) {
            while (x + 1 < 6 && !green[x + 1][y] && !green[x + 1][y + 1]) x++;
            green[x][y] = green[x][y + 1] = true;
        }
        else {
            while (x + 2 < 6 && !green[x + 2][y]) x++;
            green[x][y] = green[x + 1][y] = true;
        }

        clearGreen();
        lightGreen();
    }

    static void clearGreen() {
        for (int r = 5; r >= 2; r--) {
            boolean full = true;
            for (int c = 0; c < 4; c++) {
                if (!green[r][c]) {
                    full = false;
                    break;
                }
            }
            if (full) {
                score++;
                for (int i = r; i > 0; i--) {
                    green[i] = Arrays.copyOf(green[i - 1], 4);
                }
                green[0] = new boolean[4];
                r++;
            }
        }
    }

    static void lightGreen() {
        int cnt = 0;
        for (int r = 0; r <= 1; r++) {
            for (int c = 0; c < 4; c++) {
                if (green[r][c]) {
                    cnt++;
                    break;
                }
            }
        }

        for (int i = 0; i < cnt; i++) {
            for (int r = 5; r > 0; r--) {
                green[r] = Arrays.copyOf(green[r - 1], 4);
            }
            green[0] = new boolean[4];
        }
    }

    static void dropBlue(int t, int x) {
        int y = 0;

        if (t == 1) {
            while (y + 1 < 6 && !blue[x][y + 1]) y++;
            blue[x][y] = true;
        }
        else if (t == 2) {
            while (y + 2 < 6 && !blue[x][y + 2]) y++;
            blue[x][y] = blue[x][y + 1] = true;
        }
        else {
            while (y + 1 < 6 && !blue[x][y + 1] && !blue[x + 1][y + 1]) y++;
            blue[x][y] = blue[x + 1][y] = true;
        }

        clearBlue();
        lightBlue();
    }

    static void clearBlue() {
        for (int c = 5; c >= 2; c--) {
            boolean full = true;
            for (int r = 0; r < 4; r++) {
                if (!blue[r][c]) {
                    full = false;
                    break;
                }
            }
            if (full) {
                score++;
                for (int i = c; i > 0; i--) {
                    for (int r = 0; r < 4; r++) {
                        blue[r][i] = blue[r][i - 1];
                    }
                }
                for (int r = 0; r < 4; r++) blue[r][0] = false;
                c++;
            }
        }
    }

    static void lightBlue() {
        int cnt = 0;
        for (int c = 0; c <= 1; c++) {
            for (int r = 0; r < 4; r++) {
                if (blue[r][c]) {
                    cnt++;
                    break;
                }
            }
        }

        for (int i = 0; i < cnt; i++) {
            for (int c = 5; c > 0; c--) {
                for (int r = 0; r < 4; r++) {
                    blue[r][c] = blue[r][c - 1];
                }
            }
            for (int r = 0; r < 4; r++) blue[r][0] = false;
        }
    }

    static int countBlocks() {
        int cnt = 0;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 4; j++)
                if (green[i][j]) cnt++;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 6; j++)
                if (blue[i][j]) cnt++;

        return cnt;
    }
}
