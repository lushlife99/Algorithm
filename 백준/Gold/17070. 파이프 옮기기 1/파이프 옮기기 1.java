import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 17070 파이프 옮기기 1
 * dp
 */

public class Main {

    static int N;
    static int[][] board;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][N][3];
        dp[0][1][0] = 1;

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (board[x][y] != 0) continue;
                if (y-1 >= 0) {
                    dp[x][y][0] += dp[x][y - 1][0];
                    dp[x][y][0] += dp[x][y - 1][1];
                }

                if (x-1 >= 0 && y-1 >= 0) {
                    if (board[x-1][y] == 0 && board[x][y-1] == 0) {
                        dp[x][y][1] += dp[x - 1][y - 1][0];
                        dp[x][y][1] += dp[x - 1][y - 1][1];
                        dp[x][y][1] += dp[x - 1][y - 1][2];
                    }
                }

                if (x-1 >= 0) {
                    dp[x][y][2] += dp[x - 1][y][1];
                    dp[x][y][2] += dp[x - 1][y][2];
                }
            }
        }

        int answer = Arrays.stream(dp[N - 1][N - 1]).sum();
        System.out.println(answer);
    }
}
