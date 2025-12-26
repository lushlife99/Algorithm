import java.io.*;
import java.util.*;

/**
 * boj 21611 마법사 상어와 블리자드
 * 시뮬레이션
 */


public class Main {

    static int N, M;
    static int[][] arr;
    static int[][] idx;
    static List<Integer> board = new LinkedList<>();
    static int[][] directions = {{-1,0},{0,-1},{1,0},{0,1}};
    static int[] answer = {0,0,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        idx = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = N/2, y = N/2;
        int total = 1;
        int target = 2;
        int cnt = 1;
        int cd = 1;
        while (total < N*N) {

            if (target*target == total) {
                target++;
            }

            if (target == cnt) {
                cd = (cd + 1) % 4;
                cnt = 1;
            }

            x += directions[cd][0];
            y += directions[cd][1];

            board.add(arr[x][y]);
            idx[x][y] = total-1;
            cnt++;
            total++;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int rd = Integer.parseInt(st.nextToken());
            int rs = Integer.parseInt(st.nextToken());

            remove(rd, rs);
            replicate();
        }

        System.out.println(answer[1] + answer[2]*2 + answer[3] * 3);

    }

    static void remove(int rd, int rs) {
        int d = (rd == 1 ? 0 : rd == 2 ? 2 : rd == 3 ? 1 : 3);

        for (int s = 1; s <= rs; s++) {
            int x = N/2 + directions[d][0] * s;
            int y = N/2 + directions[d][1] * s;
            int id = idx[x][y];
            if (id < board.size()) board.set(id, 0);
        }

        List<Integer> next = new ArrayList<>();
        for (int v : board) {
            if (v != 0) next.add(v);
        }
        board = next;

        explode();
    }

    static void explode() {
        boolean exploded;

        do {
            exploded = false;
            List<Integer> next = new ArrayList<>();

            int i = 0;
            while (i < board.size()) {
                int j = i;
                while (j < board.size() && board.get(i).equals(board.get(j))) j++;

                int cnt = j - i;
                int val = board.get(i);

                if (cnt >= 4) {
                    answer[val] += cnt;
                    exploded = true;
                } else {
                    for (int k = 0; k < cnt; k++) {
                        next.add(val);
                    }
                }
                i = j;
            }
            board = next;
        } while (exploded);
    }

    static void replicate() {
        List<Integer> next = new ArrayList<>();

        int i = 0;
        while (i < board.size() && next.size() < N*N-1) {
            int j = i;
            while (j < board.size() && board.get(i).equals(board.get(j))) j++;

            int cnt = j - i;
            next.add(cnt);
            next.add(board.get(i));

            i = j;
        }
        board = next;
    }
}
