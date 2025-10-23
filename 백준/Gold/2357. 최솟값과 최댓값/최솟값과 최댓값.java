import java.io.*;
import java.util.StringTokenizer;


/**
 * boj 2357 최솟값과 최댓값
 * 세그먼트 트리
 */

public class Main {

    static class SegmentTree {
        int[][] tree;
        int n;

        public SegmentTree(int[] arr) {
            n = arr.length;
            this.tree = new int[4 * n][2];
            build(arr, 1, 0, n-1);
        }

        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node][0] = arr[start];
                tree[node][1] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, node * 2, start, mid);
                build(arr, node * 2 + 1, mid+1, end);
                tree[node][0] = Math.min(tree[node * 2][0], tree[node * 2 + 1][0]);
                tree[node][1] = Math.max(tree[node * 2][1], tree[node * 2 + 1][1]);
            }
        }

        public int[] query(int left, int right) {
            return query(1, 0, n - 1, left, right);
        }

        private int[] query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            int[] l = query(node * 2, start, mid, left, right);
            int[] r = query(node * 2 + 1, mid + 1, end, left, right);
            return new int[]{Math.min(l[0], r[0]), Math.max(l[1], r[1])};
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

        SegmentTree segmentTree = new SegmentTree(arr);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int[] query = segmentTree.query(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            sb.append(query[0]).append(" ").append(query[1]).append("\n");
        }

        System.out.print(sb);
    }
}


