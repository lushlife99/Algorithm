import java.io.*;
import java.util.*;

/**
 * boj 3653 영화 수집
 */

public class Main {

    static int TC, N, M;
    static int[] pos;
    static SegmentTree seg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            pos = new int[N + 1];
            seg = new SegmentTree(N + M);

            for (int i = 1; i <= N; i++) {
                pos[i] = M + i;
                seg.update(1, 1, N + M, pos[i], 1);
            }

            int top = M;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int movie = Integer.parseInt(st.nextToken());

                int curPos = pos[movie];
                int above = seg.query(1, 1, N + M, curPos + 1, N + M);
                sb.append(N-1-above).append(" ");

                seg.update(1, 1, N + M, curPos, 0);
                seg.update(1, 1, N + M, top, 1);

                pos[movie] = top;
                top--;
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}

class SegmentTree {
    int[] tree;
    int size;

    SegmentTree(int size) {
        this.size = size;
        tree = new int[size * 4];
    }

    void update(int node, int start, int end, int idx, int val) {
        if (idx < start || idx > end) return;

        if (start == end) {
            tree[node] = val;
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, idx, val);
        update(node * 2 + 1, mid + 1, end, idx, val);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    int query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right)
                + query(node * 2 + 1, mid + 1, end, left, right);
    }
}
