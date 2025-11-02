import java.io.*;
import java.util.*;

/**
 * boj 11812 K진 트리
 * lca, dp
 * (child + K - 2) / K = parent
 */

/**
 * 반례 생각하기
 * 1 1
 */


public class Main {

    private static long N;
    private static int K, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long distance = getDistance(a, b);
            sb.append(distance).append("\n");
        }

        System.out.println(sb);
    }

    private static long getDistance(long a, long b) {
        if (a > b) {
            long temp = a;
            a = b;
            b = temp;
        }

        if (K == 1) {
            return b - a;
        }

        long res = 0;

        // 1. 같은 depth 만들기
        long aDepth = getDepth(a);
        long bDepth = getDepth(b);

        while (aDepth != bDepth) {
            b = getParent(b);
            bDepth--;
            res++;
        }

        while (a != b) {
            a = getParent(a);
            b = getParent(b);
            res+=2;
        }

        return res;
    }

    private static long getParent(long node) {
        if (node == 1) {
            return 1;
        }

        return (node + K - 2) / K;
    }

    private static long getDepth(long node) {
        if (node == 1) return 0;

        long max = 1;
        long h = 1;
        while(true) {
            max += (long)Math.pow(K, h++);
            if (node <= max) break;
        }

        return h-1;
    }
}

