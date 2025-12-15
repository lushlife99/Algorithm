import java.io.*;
import java.util.*;

/**
 * boj 4256 트리
 *  1
 * 2 3
 *
 * pre : 123
 * in : 213
 * post : 231
 */


public class Main {

    static int T;
    static int N;
    static int[] preOrder;
    static int[] inOrder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            preOrder = new int[N+1];
            for (int j = 0; j < N; j++) {
                preOrder[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            inOrder = new int[N+1];
            for (int j = 0; j < N; j++) {
                inOrder[j] = Integer.parseInt(st.nextToken());
            }

            traversal(0,0,N);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void traversal(int rootIdx, int s, int e) {
        int root = preOrder[rootIdx];
        for (int i = s; i < e; i++) {
            if (inOrder[i] == root) {
                traversal(rootIdx+1, s, i);
                traversal(rootIdx+1+i-s, i+1, e);
                sb.append(root).append(" ");
            }
        }
    }
}