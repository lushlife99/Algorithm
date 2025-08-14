import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static char[][] gameBoard;
    static String targetWord;
    static long[][][] memo;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        gameBoard = new char[N][M];
        for (int i = 0; i < N; i++) {
            gameBoard[i] = br.readLine().toCharArray();
        }

        targetWord = br.readLine();
        memo = new long[N][M][targetWord.length()];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(memo[i][j], -1L);
            }
        }

        long totalPaths = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (gameBoard[i][j] == targetWord.charAt(0)) {
                    totalPaths += findPaths(i, j, 0);
                }
            }
        }

        System.out.println(totalPaths);
    }
    
    static long findPaths(int x, int y, int wordIndex) {
        if (wordIndex == targetWord.length() - 1) {
            return 1;
        }

        if (memo[x][y][wordIndex] != -1L) {
            return memo[x][y][wordIndex];
        }

        memo[x][y][wordIndex] = 0;

        for (int i = 0; i < 4; i++) {
            for (int k = 1; k <= K; k++) {
                int nx = x + dx[i] * k;
                int ny = y + dy[i] * k;

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && gameBoard[nx][ny] == targetWord.charAt(wordIndex + 1)) {
                    memo[x][y][wordIndex] += findPaths(nx, ny, wordIndex + 1);
                }
            }
        }

        return memo[x][y][wordIndex];
    }
}