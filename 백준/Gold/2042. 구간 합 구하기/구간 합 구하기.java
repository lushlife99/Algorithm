import java.io.*;
import java.util.*;

/**
 * boj 2042 구간 합 구하기
 * 세그먼트 트리
 */

public class Main {

     static class SegmentTree {
        private long[] tree;
        private int n;

        public SegmentTree(long[] arr) {
            n = arr.length;
            tree = new long[4 * n];
            build(arr, 1, 0, n - 1);
        }

        private void build(long[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, 2 * node, start, mid);
                build(arr, 2 * node + 1, mid + 1, end);
                tree[node] = tree[2 * node] + tree[2 * node + 1];
            }
        }

        public long query(int left, int right) {
            return query(1, 0, n - 1, left, right);
        }

        private long query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return 0;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            long lsum = query(2 * node, start, mid, left, right);
            long rsum = query(2 * node + 1, mid + 1, end, left, right);
            return lsum + rsum;
        }

        public void update(int idx, long val) {
            update(1, 0, n - 1, idx, val);
        }

        private void update(int node, int start, int end, int idx, long val) {
            if (start == end) {
                tree[node] = val;
            } else {
                int mid = (start + end) / 2;
                if (idx <= mid) {
                    update(2 * node, start, mid, idx, val);
                } else {
                    update(2 * node + 1, mid + 1, end, idx, val);
                }
                tree[node] = tree[2 * node] + tree[2 * node + 1];
            }
        }
    }

    private static int N, M, K;
    private static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(arr);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M+K; i++) {
            String query = br.readLine();
            st = new StringTokenizer(query);

            int qType = Integer.parseInt(st.nextToken());
            int param1 = Integer.parseInt(st.nextToken()) - 1;
            long param2 = Long.parseLong(st.nextToken());

            if (qType == 2) {
                long res = segmentTree.query(param1, (int)param2 - 1);
                sb.append(res).append("\n");
            } else {
                segmentTree.update(param1, param2);
            }
        }

        System.out.print(sb.toString());
    }

}
