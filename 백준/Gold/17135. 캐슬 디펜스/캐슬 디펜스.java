import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * boj 17135 캐슬 디펜스
 * 조합, 시뮬레이션
 * NC3 * M * M
 */

public class Main {

    private static int N, M, D;
    private static boolean[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        arr = new boolean[N + 1][M];

        for (int i = N; i >= 1; i--) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (st.nextToken().equals("1")) {
                    arr[i][j] = true;
                }
            }
        }

        // 조합
        List<List<Integer>> combs = combine(0, M, new ArrayList<>());
        int answer = 0;
        // 시뮬레이션 NC3 * M
        for (List<Integer> comb : combs) {
            boolean[][] arrCopy = new boolean[N+1][M];

            for (int i = 0; i < N+1; i++) {
                for (int j = 0; j < M; j++) {
                    arrCopy[i][j] = arr[i][j];
                }
            }
            int res = 0;

            for (int i = 0; i <= N; i++) {
                // 공격
                Set<Point> attacked = new HashSet<>();
                for (int archerY : comb) {
                    int[] attack = attack(archerY, arrCopy);
                    if (attack != null) attacked.add(new Point(attack[0], attack[1]));
                }

                for (Point p : attacked) {
                    arrCopy[p.x][p.y] = false;
                }
                res += attacked.size();

                // 이동
                for (int row = 1; row < N; row++) {
                    arrCopy[row] = arrCopy[row+1];
                }
                arrCopy[N] = new boolean[M];
            }
            answer = Math.max(answer, res);
        }
        System.out.println(answer);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x; this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Point oP = (Point) o;
            return this.x == oP.x && this.y == oP.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static int[] attack(int y, boolean[][] arr) {
        int[] res = new int[]{-1, -1};
        int minD = Integer.MAX_VALUE;

        for (int i = 1; i <= Math.min(D, N); i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j]) {
                    int distance = i + Math.abs(y - j);
                    if(distance > D) continue;

                    if (minD > distance) {
                        res[0] = i;
                        res[1] = j;
                        minD = distance;
                    } else if (minD == distance && res[1] > j) {
                        res[0] = i;
                        res[1] = j;
                        minD = distance;
                    }
                }
            }
        }

        if (minD == Integer.MAX_VALUE) {
            return null;
        }

        return res;
    }

    private static List<List<Integer>> combine(int c, int cnt, List<Integer> current) {

        List<List<Integer>> res = new ArrayList<>();
        if (current.size() == 3) {
            res.add(new ArrayList<>(current));
            return res;
        }

        for (int i = c; i < cnt; i++) {
            current.add(i);
            res.addAll(combine(i + 1, cnt, current));
            current.remove(current.size() - 1);
        }

        return res;
    }

}