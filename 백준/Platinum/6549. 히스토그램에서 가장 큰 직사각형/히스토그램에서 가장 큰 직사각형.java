import javax.swing.text.Segment;
import java.io.*;
import java.util.StringTokenizer;

/**
 * boj 6549 히스토그램에서 가장 큰 직사각형
 * 분할 정복, 세그먼트 트리
 *
 * (B-A+1) *
 * A ~ B까지 최대 넓이
 * A ~ mid, mid+1 ~ B, A ~ B 최대값
 *
 */

public class Main {

    static class SegmentTree {
        int[] tree;
        int[] arr;
        int n;

        SegmentTree(int[] arr) {
            this.arr = arr;
            this.n = arr.length;
            this.tree = new int[n * 4];
            build(1, 0, n - 1);
        }

        void build(int node, int start, int end) {
            if (start == end) {
                tree[node] = start;
                return;
            }
            int mid = (start + end) / 2;
            build(node * 2, start, mid);
            build(node * 2 + 1, mid + 1, end);
            int left = tree[node * 2];
            int right = tree[node * 2 + 1];
            tree[node] = (arr[left] <= arr[right]) ? left : right;
        }

        int query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) return -1;
            if (left <= start && end <= right) return tree[node];

            int mid = (start + end) / 2;
            int l = query(node * 2, start, mid, left, right);
            int r = query(node * 2 + 1, mid + 1, end, left, right);

            if (l == -1) return r;
            if (r == -1) return l;
            return (arr[l] <= arr[r]) ? l : r;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;

        while (!(input = br.readLine()).equals("0")) {
            StringTokenizer st = new StringTokenizer(input);

            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            SegmentTree segmentTree = new SegmentTree(arr);
            sb.append(getMaxArea(segmentTree, 0, n-1)).append("\n");
        }
        System.out.println(sb.toString());
    }

    static long getMaxArea(SegmentTree tree, int start, int end) {
        if (start > end) return 0;

        int minIndex = tree.query(1, 0, tree.n - 1, start, end);
        long area = (long) tree.arr[minIndex] * (end - start + 1);

        long left = getMaxArea(tree, start, minIndex - 1);
        long right = getMaxArea(tree, minIndex + 1, end);

        return Math.max(area, Math.max(left, right));
    }

}

