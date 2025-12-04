import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * boj 2268 수들의 합 7
 * 세그먼트 트리
 */


public class Main {

    static class SegmentTree {
        long[] tree;
        int n;

        public SegmentTree(int n) {
            this.n = n;
            tree = new long[n*4];
        }

        public long query(int left, int right) {
            int l = Math.min(left, right);
            int r = Math.max(left, right);
            return query(1, 0, n-1, l, r);
        }

        private long query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) return 0;
            if (left <= start && end <= right) return tree[node];

            int mid = (start + end) / 2;
            long l = query(node*2, start, mid, left, right);
            long r = query(node*2+1, mid+1, end, left, right);

            return l+r;
        }

        public void update(int idx, int val) {
            update(1, 0, n-1, idx, val);
        }

        private void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }

            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(node*2, start, mid, idx, val);
            } else {
                update(node*2+1, mid+1, end, idx, val);
            }

            tree[node] = tree[node*2] + tree[node*2+1];
        }
    }

    private static int N,M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        SegmentTree segmentTree = new SegmentTree(N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int qType = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            if (qType == 0) {
                sb.append(segmentTree.query(p1-1, p2-1)).append("\n");
            } else {
                segmentTree.update(p1-1, p2);
            }
        }

        System.out.print(sb);
    }
}