import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    static int[][] board = new int[9][9];
    static List<int[]> blanks = new ArrayList<>();
    static boolean solved = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j) - '0';
                if (board[i][j] == 0) {
                    blanks.add(new int[]{i, j});
                }
            }
        }

        solve(0);
    }

    static void solve(int idx) {
        if (idx == blanks.size()) {
            printBoard();
            solved = true;
            System.exit(0);
            return;
        }

        int[] pos = blanks.get(idx);
        int r = pos[0];
        int c = pos[1];

        for (int num = 1; num <= 9; num++) {
            if (canPlace(r, c, num)) {
                board[r][c] = num;
                solve(idx + 1);
                if (solved) return;
                board[r][c] = 0;
            }
        }
    }

    static boolean canPlace(int r, int c, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == num) return false;
            if (board[i][c] == num) return false;
        }
        int br = (r / 3) * 3;
        int bc = (c / 3) * 3;
        for (int i = br; i < br + 3; i++) {
            for (int j = bc; j < bc + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }
        return true;
    }

    static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
