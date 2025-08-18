import java.io.*;
import java.util.*;

/**
 * boj 12100
 * 구현, 시뮬, dfs, 백트래킹
 */

public class Main {

    static int max = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        int[][] inputBoardNumber = new int[size][size];
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < size; j++) {
                inputBoardNumber[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        Board originBoard = Board.generateBoardBy(inputBoardNumber, 0);
        dfs(originBoard);

        System.out.println(max);
    }

    private static void dfs(Board board) {
        max = Math.max(max, board.getMax());
        if (board.isCountMoreThanFive()) return;

        Board snapshot = board.getSnapshot();

        for (Direction dir : Direction.values()) {
            dfs(move(snapshot, dir));
        }
    }

    private static Board move(Board snapshot, Direction dir) {
        int size = snapshot.getBoard().length;
        int[][] board = getCopyOf(snapshot, size);

        for (int i = 0; i < size; i++) {
            int[] curArr = new int[size];

            // 행 또는 열 추출
            for (int j = 0; j < size; j++) {
                int r = dir.isRow ? i : (dir.reverse ? size - 1 - j : j);
                int c = dir.isRow ? (dir.reverse ? size - 1 - j : j) : i;
                curArr[j] = board[r][c];
                board[r][c] = 0;
            }

            int[] result = getResultNumbersFrom(curArr, size);

            for (int j = 0; j < size; j++) {
                int r = dir.isRow ? i : (dir.reverse ? size - 1 - j : j);
                int c = dir.isRow ? (dir.reverse ? size - 1 - j : j) : i;
                board[r][c] = result[j];
            }
        }
        return new Board(board, snapshot.getMoveCount() + 1);
    }

    private static int[][] getCopyOf(Board snapshot, int boardSize) {
        int[][] board = new int[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            board[row] = snapshot.getBoard()[row].clone();
        }
        return board;
    }

    private static int[] getResultNumbersFrom(int[] arr, int size) {
        ArrayList<Integer> merged = new ArrayList<>();
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = size - 1; i >= 0; i--) {
            if (arr[i] == 0) continue;

            if (!dq.isEmpty() && dq.peekFirst() == arr[i]) {
                dq.pollFirst();
                dq.offerFirst(arr[i] * 2);
                dq.offerFirst(0);
            } else {
                dq.offerFirst(arr[i]);
            }
        }

        while (!dq.isEmpty()) {
            int n = dq.pollFirst();
            if (n != 0) merged.add(n);
        }

        int[] result = new int[size];
        for (int j = 0; j < merged.size(); j++) {
            result[size - merged.size() + j] = merged.get(j);
        }
        return result;
    }

    enum Direction {
        RIGHT(true, false),
        LEFT(true, true),
        DOWN(false, false),
        UP(false, true);

        boolean isRow;   // true면 행 이동, false면 열 이동
        boolean reverse; // true면 뒤집어서 처리

        Direction(boolean isRow, boolean reverse) {
            this.isRow = isRow;
            this.reverse = reverse;
        }
    }

    static class Board {
        private final int[][] board;
        private final int moveCount;
        private final int max;

        private Board(int[][] board, int count) {
            this.board = board;
            this.moveCount = count;
            this.max = calculateMax(board);
        }

        private int calculateMax(int[][] input) {
            int m = 0;
            for (int[] row : input) {
                for (int val : row) {
                    m = Math.max(m, val);
                }
            }
            return m;
        }

        public static Board generateBoardBy(int[][] input, int count) {
            return new Board(input, count);
        }

        public int[][] getBoard() { return board; }
        public int getMoveCount() { return moveCount; }
        public int getMax() { return max; }
        public boolean isCountMoreThanFive() { return moveCount >= 5; }

        public Board getSnapshot() {
            int[][] snapshot = new int[board.length][board.length];
            for (int i = 0; i < board.length; i++) {
                snapshot[i] = board[i].clone();
            }
            return new Board(snapshot, moveCount);
        }
    }
}
