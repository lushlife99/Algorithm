import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * boj 7578 공장
 * 세그먼트 트리
 */


public class Main {


    static class SegmentTree {
        int[] tree;
        int n;

        public SegmentTree(int n) {
            this.n = n;
            tree = new int[n * 4];
        }

        public int query(int left, int right) {
            return query(1, 0, n-1, left, right);
        }

        private int query(int node, int start, int end, int left, int right) {
            if (start > right || left > end) return 0;
            if (left <= start && end <= right) return tree[node];

            int mid = (start + end) / 2;
            int lCnt = query(node*2, start, mid, left, right);
            int rCnt = query(node*2+1, mid+1, end, left, right);
            return lCnt + rCnt;
        }

        public void update(int val) {
            update(1, 0, n-1, val);
        }

        private void update(int node, int start, int end, int val) {
            if (val < start || val > end) return;

            if (start == end) {
                tree[node]++;
                return;
            }

            int mid = (start + end) / 2;
            update(node*2, start, mid, val);
            update(node*2+1, mid+1, end, val);
            tree[node] = tree[node*2] + tree[node*2+1];
        }
    }

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        int[] res = new int[N];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            map.put(arr[i], i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(st.nextToken());
            res[map.get(val)] = i;
        }

        SegmentTree segmentTree = new SegmentTree(N+1);

        long answer = 0;
        for (int i = 0; i < N; i++) {
            int val = res[i];
            answer += segmentTree.query(val + 1, N);
            segmentTree.update(val);
        }

        System.out.println(answer);
    }

}
