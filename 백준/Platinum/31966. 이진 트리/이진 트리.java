import java.io.*;
import java.util.*;

/**
 * boj 31966
 * dp, 트리
 * 답지봄
 */

public class Main {
    static final int MOD = 1000000007;
    static int N;
    static Node[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        tree = new Node[N + 1];

        tree[0] = new Node();

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Node L = tree[a];
            Node R = tree[b];

            Node cur = new Node();

            cur.ret = (int) (((L.ret + R.ret) % MOD
                    + (L.right * R.sz) % MOD
                    + (R.left * L.sz) % MOD
                    - 1 + MOD) % MOD);

            cur.sz = (int) ((L.sz + R.sz) % MOD);
            cur.left = (int) ((L.left + (R.left + R.sz) - 1 + MOD) % MOD);
            cur.right = (int) ((R.right + L.right + (L.sz - 1) + MOD) % MOD);

            tree[i] = cur;

            sb.append(cur.ret).append("\n");
        }

        System.out.print(sb);
    }

    static class Node {
        long left, right, sz, ret;
        Node() { left = right = sz = ret = 1; }
    }
}
