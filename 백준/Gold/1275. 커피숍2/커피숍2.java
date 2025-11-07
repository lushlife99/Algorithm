import java.io.*;
import java.util.*;


/**
 * boj 1275 커피숍2
 * 느리게 갱신되는 세그먼트 트리
 */

public class Main {

    static class SegmentTree {
        long[] tree, lazy;
        int n;

        public SegmentTree(long[] arr) {
            n = arr.length;
            tree = new long[n * 4];
            lazy = new long[n * 4];
            build(arr, 1, 0, n-1);
        }

        private void build(long[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, node * 2, start, mid);
                build(arr, node * 2 + 1, mid+1, end);
                tree[node] = tree[node*2] + tree[node * 2 + 1];
            }
        }

        private void propagate(int node, int start, int end) {
            if (lazy[node] != 0) {
                tree[node] += (end - start + 1) * lazy[node];

                if (start != end) {
                    lazy[node * 2] += lazy[node];
                    lazy[node * 2 + 1] += lazy[node];
                }

                lazy[node] = 0;
            }
        }

        public long query(int left, int right) {
            return query(1, 0, n-1, left, right);
        }

        private long query(int node, int start, int end, int left, int right) {

            if (right < start || left > end) return 0;
            propagate(node, start, end);
            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            long lQ = query(node * 2, start, mid, left, right);
            long rQ = query(node * 2 + 1, mid+1, end, left, right);
            return lQ + rQ;
        }

        public void update(int left, int right, long val) {
            update(1, 0, n-1, left, right, val);
        }

        private void update(int node, int start, int end, int left, int right, long val) {
            propagate(node, start, end);

            if (right < start || left > end) return;
            if (left <= start && end <= right) {
                tree[node] += (end - start + 1) * val;
                if (start != end) {
                    lazy[node*2] += val;
                    lazy[node*2+1] += val;
                }
                return;
            }

            int mid = (start + end) / 2;
            update(node * 2, start, mid, left, right, val);
            update(node * 2+1, mid+1, end, left, right, val);
            tree[node] = tree[node*2] + tree[node*2+1];
        }
    }

    private static int N, Q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        long[] arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree segmentTree = new SegmentTree(arr);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken()) - 1;
            int p2 = Integer.parseInt(st.nextToken()) - 1;
            int p3 = Integer.parseInt(st.nextToken()) - 1;
            long p4 = Long.parseLong(st.nextToken());

            sb.append(segmentTree.query(Math.min(p1, p2), Math.max(p1, p2))).append("\n");
            segmentTree.update(p3, p3, p4-arr[p3]);
            arr[p3] = p4;
        }
        System.out.print(sb);
    }
}
