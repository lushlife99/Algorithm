import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * boj 11505 구간 곱 구하기
 * 세그먼트 트리
 */


public class Main {

    static class SegmentTree {
        long[] tree;
        int n;

        public SegmentTree(long[] arr) {
            this.n = arr.length;
            tree = new long[n * 4];
            build(arr, 1, 0, n-1);
        }

        private void build(long[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, node * 2, start, mid);
                build(arr, node * 2 + 1, mid+1, end);
                tree[node] = (tree[node*2] % MOD * tree[node*2+1] % MOD) % MOD;
            }
        }

        public long query(int left, int right) {
            return query(1, 0, n-1, left, right);
        }

        private long query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return 1;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            long lQuery = query(node * 2, start, mid, left, right);
            long rQuery = query(node * 2 + 1, mid+1, end, left, right);
            return lQuery * rQuery % MOD;
        }

        public void update(int idx, long val) {
            update(1, 0, n-1, idx, val);
        }

        private void update(int node, int start, int end, int idx, long val) {
            if (start == end) {
                tree[node] = val;
            } else {
                int mid = (start + end) / 2;
                if (idx <= mid) {
                    update(2 * node, start ,mid, idx, val);
                } else {
                    update(2 * node + 1, mid+1, end, idx, val);
                }
                tree[node] = tree[node * 2] * tree[node * 2 + 1] % MOD;
            }
        }
    }

    private static int N, M, K;
    private static int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(arr);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int qType = Integer.parseInt(st.nextToken());
            int param1 = Integer.parseInt(st.nextToken());
            int param2 = Integer.parseInt(st.nextToken());

            if (qType == 1) {
                segmentTree.update(param1-1, param2);
            } else {
                long res = segmentTree.query(param1-1, param2-1);
                sb.append(res).append("\n");
            }
        }

        System.out.print(sb);

    }
}
