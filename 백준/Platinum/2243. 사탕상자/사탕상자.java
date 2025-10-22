import java.io.*;
import java.util.StringTokenizer;


/**
 * boj 2243 사탕상자
 */

public class Main {

    static class SegmentTree {

        private int[] tree;
        int size = 1_000_001 * 4;

        public SegmentTree() {
            int n = 0;
            this.tree = new int[size];
        }

        public void update(int node, int start, int end, int idx, int diff) {
            if (idx < start || idx > end) return;

            tree[node] += diff;
            if (start == end) return;

            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, diff);
            update(node * 2 + 1, mid + 1, end, idx, diff);
        }

        public int query(int node, int start, int end, int k) {
            if (start == end) return start;

            int mid = (start + end) / 2;
            if (tree[node * 2] >= k) {
                return query(node * 2, start, mid, k);
            } else {
                return query(node * 2 + 1, mid+1, end, k - tree[node * 2]);
            }
        }
    }

    private static int N;
    private static int MAX = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        SegmentTree segmentTree = new SegmentTree();
        StringBuilder sb = new StringBuilder();

        while (N-- > 0) {
            String query = br.readLine();
            StringTokenizer st = new StringTokenizer(query);
            int qType = Integer.parseInt(st.nextToken());

            if (qType == 1) {
                int param = Integer.parseInt(st.nextToken());
                int taste = segmentTree.query(1, 0, MAX, param);
                segmentTree.update(1, 0, MAX, taste, -1);

                sb.append(taste).append("\n");
            } else {
                int param1 = Integer.parseInt(st.nextToken());
                int param2 = Integer.parseInt(st.nextToken());

                segmentTree.update(1, 0, MAX, param1, param2);
            }
        }

        System.out.print(sb);
    }
}


