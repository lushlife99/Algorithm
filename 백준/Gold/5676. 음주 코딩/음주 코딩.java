import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * boj 5676 음주 코딩
 * 세그먼트 트리
 */

/**
 * 반례
 */

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s;

        while ((s = br.readLine()) != null) {
            if (s.isEmpty()) break;

            StringTokenizer st = new StringTokenizer(s);
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            SegmentTree segmentTree = new SegmentTree(arr);
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                String qType = st.nextToken();
                int p1 = Integer.parseInt(st.nextToken());
                int p2 = Integer.parseInt(st.nextToken());

                if (qType.equals("C")) {
                    segmentTree.update(p1-1, p2);
                } else {
                    int res = segmentTree.query(p1 - 1, p2 - 1);
                    if (res > 0) {
                        sb.append("+");
                    } else if (res == 0) {
                        sb.append("0");
                    } else {
                        sb.append("-");
                    }
                }
            }
            sb.append("\n");
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
        Arrays.fill(tree, 1);

        build(arr, 1, 0, n-1);
    }

    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            if (arr[start] > 0) {
                tree[node] = 1;
            } else if (arr[start] == 0) {
                tree[node] = 0;
            } else {
                tree[node] = -1;
            }
        } else {
            int mid = (start + end) / 2;
            build(arr, node*2, start, mid);
            build(arr, node*2+1, mid+1, end);

            tree[node] = tree[node*2] * tree[node*2+1];
        }
    }

    public int query(int left, int right) {
        return query(1, 0, n-1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {
        if (right < start || left > end) return 1;
        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        int l = query(node*2, start, mid, left, right);
        int r = query(node*2+1, mid+1, end, left, right);

        return l*r;
    }

    public void update(int idx, int val) {
        if (val > 0) val = 1;
        else if (val < 0) val = -1;

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

            tree[node] = tree[node*2]*tree[node*2+1];
        }
    }
}