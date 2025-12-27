import java.io.*;
import java.util.*;

/**
 * boj 14427 수열과 쿼리 15
 * 세그먼트 트리
 */


public class Main {

    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree segmentTree = new SegmentTree(arr);
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int qType = Integer.parseInt(st.nextToken());
            if (qType == 1) {
                int p1 = Integer.parseInt(st.nextToken()) - 1;
                int p2 = Integer.parseInt(st.nextToken());
                segmentTree.update(p1, p2);
            } else {
                sb.append(segmentTree.query()[1] + 1).append("\n");
            }
        }

        System.out.print(sb);
    }
}

class SegmentTree {

    int[][] tree;
    int n;

    public SegmentTree(int[] arr) {
        this.n = arr.length;
        tree = new int[n*4][2];
        build(arr, 1, 0, n-1);
    }

    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node][0] = arr[start];
            tree[node][1] = start;
        } else {
            int mid = (start + end) / 2;
            build(arr, node*2, start, mid);
            build(arr, node*2+1, mid+1, end);
            if (tree[node*2][0] < tree[node*2+1][0] ||
                    (tree[node*2][0] == tree[node*2+1][0] &&
                            tree[node*2][1] < tree[node*2+1][1])) {

                tree[node][0] = tree[node*2][0];
                tree[node][1] = tree[node*2][1];
            } else {
                tree[node][0] = tree[node*2+1][0];
                tree[node][1] = tree[node*2+1][1];
            }
        }
    }

    public void update(int idx, int val) {
        update(1, 0, n-1, idx, val);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node][0] = val;
            tree[node][1] = start;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(node*2, start, mid, idx, val);
            } else {
                update(node*2+1, mid+1, end, idx, val);
            }

            if (tree[node*2][0] < tree[node*2+1][0] ||
                    (tree[node*2][0] == tree[node*2+1][0] &&
                            tree[node*2][1] < tree[node*2+1][1])) {

                tree[node][0] = tree[node*2][0];
                tree[node][1] = tree[node*2][1];
            } else {
                tree[node][0] = tree[node*2+1][0];
                tree[node][1] = tree[node*2+1][1];
            }
        }
    }

    public int[] query() {
        return tree[1];
    }
}
