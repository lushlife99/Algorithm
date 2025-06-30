import java.util.*;

class Solution {
    public int solution(String[] board) {
        int[] counts = countOX(board);
        int oCount = counts[0];
        int xCount = counts[1];

        if (!validateCount(oCount, xCount)) return 0;
        if (!validateEnd(board, oCount, xCount)) return 0;

        return 1;
    }

    // O, X 개수 세기
    public int[] countOX(String[] board) {
        int oCount = 0;
        int xCount = 0;
        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == 'O') oCount++;
                else if (c == 'X') xCount++;
            }
        }
        return new int[]{oCount, xCount};
    }

    // O, X 순서 및 수 검증
    public boolean validateCount(int oCount, int xCount) {
        return (oCount == xCount || oCount == xCount + 1);
    }

    // 게임 종료 상태 검증
    public boolean validateEnd(String[] board, int oCount, int xCount) {
        int oEndCount = 0;
        Set<Pair> oPairSet = new HashSet<>();
        int xEndCount = 0;
        Set<Pair> xPairSet = new HashSet<>();

        // Row
        for (int i = 0; i < 3; i++) {
            char c = board[i].charAt(0);
            if (c != '.' && board[i].charAt(1) == c && board[i].charAt(2) == c) {
                List<Pair> pairList = Arrays.asList(
                    new Pair(i, 0), new Pair(i, 1), new Pair(i, 2));
                if (c == 'O') {
                    oEndCount++;
                    oPairSet.addAll(pairList);
                } else {
                    xEndCount++;
                    xPairSet.addAll(pairList);
                }
            }
        }

        // Column
        for (int i = 0; i < 3; i++) {
            char c = board[0].charAt(i);
            if (c != '.' && board[1].charAt(i) == c && board[2].charAt(i) == c) {
                List<Pair> pairList = Arrays.asList(
                    new Pair(0, i), new Pair(1, i), new Pair(2, i));
                if (c == 'O') {
                    oEndCount++;
                    oPairSet.addAll(pairList);
                } else {
                    xEndCount++;
                    xPairSet.addAll(pairList);
                }
            }
        }

        // Diagonal
        char c = board[0].charAt(0);
        if (c != '.' && board[1].charAt(1) == c && board[2].charAt(2) == c) {
            List<Pair> pairList = Arrays.asList(
                new Pair(0, 0), new Pair(1, 1), new Pair(2, 2));
            if (c == 'O') {
                oEndCount++;
                oPairSet.addAll(pairList);
            } else {
                xEndCount++;
                xPairSet.addAll(pairList);
            }
        }

        // Diagonal
        c = board[0].charAt(2);
        if (c != '.' && board[1].charAt(1) == c && board[2].charAt(0) == c) {
            List<Pair> pairList = Arrays.asList(
                new Pair(0, 2), new Pair(1, 1), new Pair(2, 0));
            if (c == 'O') {
                oEndCount++;
                oPairSet.addAll(pairList);
            } else {
                xEndCount++;
                xPairSet.addAll(pairList);
            }
        }

        // O와 X가 동시에 이긴 경우는 불가능
        if (oEndCount > 0 && xEndCount > 0) return false;

        // O가 이겼다면 oCount == xCount + 1
        if (oEndCount > 0 && oCount != xCount + 1) return false;

        // X가 이겼다면 oCount == xCount
        if (xEndCount > 0 && oCount != xCount) return false;

        // 선공은 한 번에 3줄 이상 못 만듦 / 후공은 2줄 이상 못 만듦
        if (oEndCount > 2 || xEndCount > 1) return false;

        // 2줄 이상이 동시에 완성되면 교차점 있어야 함
        if (oEndCount == 2 && oPairSet.size() != 5) return false;

        return true;
    }

    class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair other = (Pair) o;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}
