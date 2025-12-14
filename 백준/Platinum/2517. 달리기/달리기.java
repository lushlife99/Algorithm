import java.io.*;
import java.util.*;

/**
 * boj 2517 달리기
 * 세그먼트 트리
 */

public class Main {

    private static int N;
    private static int[][] arr;
    private static int[] order;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            arr[i][0] = Integer.parseInt(br.readLine());
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            return a[0] - b[0];
        });

        for (int i = 0; i < N; i++) {
            arr[i][0] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            return a[1] - b[1];
        });

        SegmentTree segmentTree = new SegmentTree(N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            segmentTree.update(arr[i][0]);
            sb.append(segmentTree.gtQuery(arr[i][0])+1).append("\n");
        }

        System.out.print(sb);
    }
}

class SegmentTree {
    int[] tree;
    int n;

    public SegmentTree(int n) {
        this.n = n;
        tree = new int[n*4];
    }

    public int gtQuery(int val) {
        return query(1, 0, n-1, val+1, n-1);
    }

    private int query(int node, int start, int end, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        int l = query(node*2, start, mid, left, right);
        int r = query(node*2+1, mid+1, end, left, right);

        return l+r;
    }

    public void update(int val) {
        update(1, 0, n-1, val);
    }

    private void update(int node, int start, int end, int val) {
        if (start == end) {
            tree[node]++;
        } else {
            int mid = (start + end) / 2;
            if (val <= mid) {
                update(node*2, start, mid, val);
            } else {
                update(node*2+1, mid+1, end, val);
            }

            tree[node] = tree[node*2] + tree[node*2+1];
        }
    }
}