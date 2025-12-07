import java.io.*;
import java.util.*;

/**
 * boj 13459 구슬 탈출
 */

public class Main {

    private static int N, M;
    private static char[][] board;
    private static int[] red, blue;
    private static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'R') {
                    red = new int[]{i,j};
                    board[i][j] = '.';
                }

                if (board[i][j] == 'B') {
                    blue = new int[]{i,j};
                    board[i][j] = '.';
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{red[0], red[1], blue[0], blue[1]});

        boolean valid = false;
        int cnt = 0;
        while (!valid && cnt < 10) {
            cnt++;
            int size = queue.size();
            for (int t = 0; t < size; t++) {
                int[] c = queue.poll();
                int rx = c[0];
                int ry = c[1];
                int bx = c[2];
                int by = c[3];

                for (int i = 0; i < directions.length; i++) {
                    boolean isRedPriority = isRedPriority(rx, ry, bx, by, directions[i]);
                    int rnx = rx;
                    int rny = ry;
                    int bnx = bx;
                    int bny = by;

                    boolean isBlueHoleIn = false;
                    boolean isRedHoleIn = false;
                    if (isRedPriority) {
                        while(board[rnx + directions[i][0]][rny + directions[i][1]] != '#') {
                            if (board[rnx + directions[i][0]][rny + directions[i][1]] == 'O') {
                                isRedHoleIn = true;
                                rnx = -1;
                                rny = -1;
                                break;
                            }
                            rnx += directions[i][0];
                            rny += directions[i][1];
                        }

                        while(board[bnx + directions[i][0]][bny + directions[i][1]] != '#') {
                            if (board[bnx + directions[i][0]][bny + directions[i][1]] == 'O') {
                                isBlueHoleIn = true;
                                break;
                            }

                            if (bnx + directions[i][0] == rnx && bny + directions[i][1] == rny) break;
                            bnx += directions[i][0];
                            bny += directions[i][1];
                        }

                        if (isBlueHoleIn) continue;
                    } else {
                        while(board[bnx + directions[i][0]][bny + directions[i][1]] != '#') {
                            if (board[bnx + directions[i][0]][bny + directions[i][1]] == 'O') {
                                isBlueHoleIn = true;
                                bnx = -1;
                                bny = -1;
                                break;
                            }
                            bnx += directions[i][0];
                            bny += directions[i][1];
                        }

                        while(board[rnx + directions[i][0]][rny + directions[i][1]] != '#') {
                            if (board[rnx + directions[i][0]][rny + directions[i][1]] == 'O') {
                                isRedHoleIn = true;
                                break;
                            }

                            if (bnx == rnx + directions[i][0] && bny == rny + directions[i][1]) break;
                            rnx += directions[i][0];
                            rny += directions[i][1];
                        }

                        if (isBlueHoleIn) continue;
                    }

                    if (isRedHoleIn && !isBlueHoleIn) {
                        valid = true;
                        break;
                    }

                    queue.add(new int[]{rnx, rny, bnx, bny});
                }

                if (valid) {
                    break;
                }
            }
        }

        if (valid) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean isRedPriority(int rx, int ry, int bx, int by, int[] dir) {
        if (dir[0] == 1) return rx > bx;
        if (dir[0] == -1) return rx < bx;
        if (dir[1] == 1) return ry > by;
        return ry < by;
    }
}