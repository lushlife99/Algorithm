import java.io.*;

/**
 * boj 1725 히스토그램
 * 세그먼트 트리
 */


public class Main {

    static class SegmentTree {
        int[] tree;
        int[] arr;
        int n;

        public SegmentTree(int[] arr) {
            this.n = arr.length;
            tree = new int[n * 4];
            this.arr = arr;
            build(arr, 1, 0, n-1);
        }

        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = start;
            } else {
                int mid = (start + end) / 2;
                build(arr, node * 2, start, mid);
                build(arr, node * 2 + 1, mid + 1, end);

                if (arr[tree[node * 2]] < arr[tree[node * 2 + 1]]) {
                    tree[node] = tree[node * 2];
                } else {
                    tree[node] = tree[node * 2 + 1];
                }
            }
        }

        public int query(int left, int right) {
            return query(1, 0, n-1, left, right);
        }

        private int query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return -1;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            int lQuery = query(node * 2, start, mid, left, right);
            int rQuery = query(node * 2 + 1, mid + 1, end, left, right);

            if (lQuery == -1) return rQuery;
            if (rQuery == -1) return lQuery;

            if (arr[lQuery] < arr[rQuery]) return lQuery;
            else return rQuery;
        }
    }


    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(arr);
        int res = getMaxArea(0, N - 1, segmentTree);
        System.out.println(res);

    }

    private static int getMaxArea(int left, int right, SegmentTree segmentTree) {
        if (left > right) {
            return 0;
        }

        if (left == right) {
            return segmentTree.arr[left];
        }

        int mid = segmentTree.query(left, right);

        int area = segmentTree.arr[mid] * (right - left + 1);
        int leftArea = getMaxArea(left, mid-1, segmentTree);
        int rightArea = getMaxArea(mid+1, right, segmentTree);

        return Math.max(Math.max(area, leftArea), rightArea);
    }
}

