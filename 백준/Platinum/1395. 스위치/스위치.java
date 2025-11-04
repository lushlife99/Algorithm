import java.io.*;
import java.util.*;


/**
 * boj 1395 스위치
 * 느리게 갱신되는 세그먼트 트리
 */

public class Main {

    static class SegmentTree {
        int[] tree, lazy;
        int n;

        public SegmentTree(int[] arr) {
            n = arr.length;
            this.tree = new int[n*4];
            this.lazy = new int[n*4];
            build(arr, 1, 0, n-1);
        }

        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, node * 2, start, mid);
                build(arr, node * 2 + 1, mid+1, end);
                tree[node] = tree[node*2] + tree[node*2+1];
            }
        }

        private void propagate(int node, int start, int end) {
            if (lazy[node] != 0) {
                tree[node] = (end - start + 1) - tree[node];

                if (start != end) {
                    lazy[node * 2] = (lazy[node * 2] + 1) % 2;
                    lazy[node * 2 + 1] = (lazy[node * 2 + 1] + 1) % 2;
                }

                lazy[node] = 0;
            }
        }

        public int query(int left, int right) {
            return query(1, 0, n - 1, left, right);
        }

        private int query(int node, int start, int end, int left, int right) {
            propagate(node, start, end);

            if (right < start || left > end) {
                return 0;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            int l = query(node * 2, start, mid, left, right);
            int r = query(node * 2 + 1, mid + 1, end, left, right);

            return l+r;
        }

        public void update(int left, int right) {
            update(1, 0, n-1, left, right);
        }

        private void update(int node, int start, int end, int left, int right) {
            propagate(node, start, end);

            if (right < start || left > end) return;
            if (left <= start && end <= right) {
                tree[node] = (end - start + 1) - tree[node];
                if (start != end) {
                    lazy[node * 2] = (lazy[node * 2] + 1) % 2;
                    lazy[node * 2 + 1] = (lazy[node * 2 + 1] + 1) % 2;
                }
                return;
            }

            int mid = (start + end) / 2;
            update(node * 2, start, mid, left, right);
            update(node * 2+1, mid+1, end, left, right);
            tree[node] = tree[node*2] + tree[node*2+1];
        }
    }

    private static int N, M;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        SegmentTree segmentTree = new SegmentTree(new int[N]);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int qType = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken()) - 1;
            int p2 = Integer.parseInt(st.nextToken()) - 1;

            if (qType == 0) {
                segmentTree.update(p1, p2);
            } else {
                int res = segmentTree.query(p1, p2);
                sb.append(res).append("\n");
            }
        }

        System.out.print(sb);
    }

}
