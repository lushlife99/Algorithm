import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 18808 스티커 붙이기
 * 시뮬레이션
 */


public class Main {

    private static int N, M, K;
    private static List<int[][]> stickers = new ArrayList<>();
    private static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[r][c];
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < c; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            stickers.add(sticker);
        }

        for (int[][] s : stickers) {
            int[][] sticker = s;
            int cnt = 4;
            while (cnt-- > 0) {
                if (put(sticker)) {
                    break;
                }
                sticker = rotate(sticker);
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    answer++;
                }
            }
        }

        System.out.println(answer);


    }

    private static int[][] rotate(int[][] sticker) {
        int[][] res = new int[sticker[0].length][sticker.length];

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                res[i][res[0].length-1-j] = sticker[j][i];
            }
        }

        return res;
    }

    private static boolean put(int[][] sticker) {
        for (int r = 0; r + sticker.length-1 < N; r++) {
            for (int c = 0; c + sticker[0].length-1 < M; c++) {
                boolean sig = true;
                for (int sr = 0; sr < sticker.length; sr++) {
                    for (int sc = 0; sc < sticker[0].length; sc++) {
                        if (sticker[sr][sc] == 1 && board[r+sr][c+sc] == 1) {
                            sig = false;
                            break;
                        }
                    }

                    if (!sig) {
                        break;
                    }
                }

                if (sig) {
                    for (int sr = 0; sr < sticker.length; sr++) {
                        for (int sc = 0; sc < sticker[0].length; sc++) {
                            if (sticker[sr][sc] == 1) {
                                board[r+sr][c+sc] = 1;
                            }
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }

}