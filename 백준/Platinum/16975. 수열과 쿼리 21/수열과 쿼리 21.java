import javax.swing.text.Segment;
import java.io.*;
import java.util.*;
import java.util.stream.*;


/**
 * boj 16975 수열과 쿼리 21
 * 느리게 갱신되는 세그먼트 트리
 *
 * 반례 생각
 */


public class Main {

    static class SegmentTree {
        long[] tree, lazy;
        int n;

        public SegmentTree(long[] arr) {
            n = arr.length;
            tree = new long[n*4];
            lazy = new long[n*4];
            build(arr, 1, 0, n-1);
        }

        private void build(long[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, node*2, start, mid);
                build(arr, node*2+1, mid+1, end);
                tree[node] = tree[node * 2] + tree[node * 2 + 1];
            }
        }

        private void propagate(int node, int start, int end) {
            if (lazy[node] != 0) {
                tree[node] += (end - start + 1) * lazy[node];

                if (start != end) {
                    lazy[node*2] += lazy[node];
                    lazy[node*2+1] += lazy[node];
                }

                lazy[node] = 0;
            }
        }

        public long query(int x) {
            return query(1, 0, n-1, x, x);
        }

        private long query(int node, int start, int end, int left, int right) {
            propagate(node, start, end);

            if (right < start || left > end) {
                return 0;
            }

            if (start == end && start == left) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            long l = query(node * 2, start, mid, left, right);
            long r = query(node * 2+1, mid+1, end, left, right);

            return l+r;
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

    private static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        SegmentTree segmentTree = new SegmentTree(arr);

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int qType = Integer.parseInt(st.nextToken());

            if (qType == 1) {
                int p1 = Integer.parseInt(st.nextToken()) - 1;
                int p2 = Integer.parseInt(st.nextToken()) - 1;
                long p3 = Long.parseLong(st.nextToken());

                segmentTree.update(p1, p2, p3);
            } else {
                int p1 = Integer.parseInt(st.nextToken()) - 1;
                long query = segmentTree.query(p1);
                sb.append(query).append("\n");
            }
        }

        System.out.print(sb);
    }



}