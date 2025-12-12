import java.io.*;
import java.util.*;

public class Main {

    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree segmentTree = new SegmentTree(arr);
        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int qType = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            if (qType == 1) {
                segmentTree.update(p1-1, p2);
            } else {
                int query = segmentTree.query(p1 - 1, p2 - 1);
                sb.append(query).append("\n");
            }
        }

        System.out.print(sb);
    }
}

class SegmentTree {
    int[] tree;
    int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[n*4];
        build(arr, 1, 0, n-1);
    }

    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, node*2, start, mid);
            build(arr, node*2+1, mid+1, end);
            tree[node] = Math.min(tree[node*2], tree[node*2+1]);
        }
    }

    public int query(int left, int right) {
        return query(1, 0, n-1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {
        if (right < start || left > end) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        int l = query(node*2, start, mid, left, right);
        int r = query(node*2+1, mid+1, end, left, right);

        return Math.min(l, r);
    }

    public void update(int idx, int val) {
        update(1, 0, n-1, idx, val);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(node*2, start, mid, idx, val);
            } else {
                update(node*2+1, mid+1, end, idx, val);
            }

            tree[node] = Math.min(tree[node*2], tree[node*2+1]);
        }
    }
}