import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 17144 미세먼지 안녕!
 * 시뮬레이션
 */


public class Main {

    static int R,C,T;
    static int[][] board;
    static List<int[]> cleaner;
    static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        cleaner = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1) {
                    cleaner.add(new int[]{i, j});
                }
            }
        }

        while (T--> 0) {
            spreadOut();
            clean();
        }

        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) answer += board[i][j];
            }
        }

        System.out.println(answer);
    }
    
    static void spreadOut() {

        List<int[]> dirtySpot = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) {
                    dirtySpot.add(new int[]{i, j});
                }
            }
        }

        int[][] change = new int[R][C];
        for (int[] spot : dirtySpot) {
            for (int i = 0; i < directions.length; i++) {
                int nx = spot[0] + directions[i][0];
                int ny = spot[1] + directions[i][1];

                if (nx < 0 || ny < 0 || nx == R || ny == C) continue;
                boolean sig = false;

                for (int[] c : cleaner) {
                    if (nx == c[0] && ny == c[1]) {
                        sig = true;
                        break;
                    }
                }

                if (sig) continue;

                change[nx][ny] += board[spot[0]][spot[1]] / 5;
                change[spot[0]][spot[1]] -= board[spot[0]][spot[1]] / 5;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                board[i][j] += change[i][j];
            }
        }
    }

    static void clean() {
        rotate();
        reverseRotate();
    }

    static void reverseRotate() {
        int[] c = cleaner.get(0);
        int[][] newBoard = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        for (int col = 1; col < C-1; col++) {
            newBoard[c[0]][col+1] = board[c[0]][col];
        }

        for (int row = c[0]; row > 0; row--) {
            newBoard[row-1][C-1] = board[row][C-1];
        }

        for (int col = C-1; col > 0; col--) {
            newBoard[0][col-1] = board[0][col];
        }

        for (int row = 0; row < c[0]-1; row++) {
            newBoard[row+1][0] = board[row][0];
        }

        newBoard[c[0]][c[1]+1] = 0;

        for (int i = 0; i <= c[0]; i++) {
            board[i] = newBoard[i];
        }
    }

    static void rotate() {
        int[] c = cleaner.get(1);
        int[][] newBoard = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        for (int col = 1; col < C-1; col++) {
            newBoard[c[0]][col+1] = board[c[0]][col];
        }

        for (int row = c[0]; row < R-1; row++) {
            newBoard[row+1][C-1] = board[row][C-1];
        }

        for (int col = C-1; col > 0; col--) {
            newBoard[R-1][col-1] = board[R-1][col];
        }

        for (int row = R-1; row > c[0]+1; row--) {
            newBoard[row-1][0] = board[row][0];
        }

        newBoard[c[0]][c[1]+1] = 0;

        for (int i = c[0]; i < newBoard.length; i++) {
            board[i] = newBoard[i];
        }
    }
}
