import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * boj 10868 최솟값
 * 세그먼트 트리
 */


public class Main {

    static class SegmentTree {
        int[] tree;
        int n;

        public SegmentTree(int[] arr) {
            this.n = arr.length;
            tree = new int[n * 4];
            build(arr, 1, 0, n-1);
        }

        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, node * 2, start, mid);
                build(arr, node * 2 + 1, mid + 1, end);
                tree[node] = Math.min(tree[node*2], tree[node*2+1]);
            }
        }

        public int query(int left, int right) {
            return query(1, 0, n-1, left, right);
        }

        private int query(int node, int start, int end, int left, int right) {
            if (right < start || left > end) {
                return Integer.MAX_VALUE;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            int lQuery = query(node * 2, start ,mid, left, right);
            int rQuery = query(node * 2 + 1, mid + 1, end, left, right);

            return Math.min(lQuery, rQuery);
        }
    }

    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        SegmentTree segmentTree = new SegmentTree(arr);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            int res = segmentTree.query(left-1, right-1);
            sb.append(res).append("\n");
        }

        System.out.println(sb);
    }
}

