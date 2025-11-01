import java.io.*;
import java.util.*;

/**
 * boj 10999 구간 합 구하기 2
 * 세그먼트 트리
 */

/**
 * 반례 생각하기
 */


public class Main {

    static class SegmentTree {
        long[] tree, lazy;
        int n;

        public SegmentTree(long[] arr) {
            n = arr.length;
            this.tree = new long[n * 4];
            this.lazy = new long[n * 4];
            build(arr, 1, 0, n-1);
        }

        private void build(long[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, node * 2, start, mid);
                build(arr, node * 2 + 1, mid+1, end);
                tree[node] = tree[node * 2] + tree[node * 2 + 1];
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

            propagate(node, start, end);

            if (right < start || left > end) {
                return 0;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            long l = query(node * 2, start, mid, left, right);
            long r = query(node * 2 + 1, mid+1, end, left, right);

            return l + r;
        }

        public void update(int left, int right, long val) {
            update(1, 0, n-1, left, right, val);
        }

        private void update(int node, int start, int end, int left, int right, long val) {
            propagate(node, start, end);

            if (right < start || left > end) return;

            if (right < start || end < left) return;

            if (left <= start && end <= right) {
                tree[node] += (end - start + 1) * val;
                if (start != end) {
                    lazy[node * 2] += val;
                    lazy[node * 2 + 1] += val;
                }
                return;
            }

            int mid = (start + end) / 2;
            update(node * 2, start, mid, left, right, val);
            update(node * 2 + 1, mid+1, end, left, right, val);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    private static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(arr);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int qType = Integer.parseInt(st.nextToken());

            if (qType == 1) {
                int param1 = Integer.parseInt(st.nextToken());
                int param2 = Integer.parseInt(st.nextToken());
                long param3 = Long.parseLong(st.nextToken());

                segmentTree.update(param1-1, param2-1, param3);
            } else {
                int param1 = Integer.parseInt(st.nextToken());
                int param2 = Integer.parseInt(st.nextToken());
                long res = segmentTree.query(param1 - 1, param2 - 1);

                sb.append(res).append("\n");
            }
        }

        System.out.print(sb);
    }
}

